package Presentacion;

import javax.swing.*;
import Negocio.TransferPlato;
import Negocio.TransferPedido;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GUICamareroImp extends GUICamarero {

    private Controlador controlador;
    private Object usuario;

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cards;

    // Total acumulado del pedido actual
    private double total = 0.0;
    private JLabel totalPedido;
    private JComboBox<String> selectorMesa;
    private JPanel pedidosPanel;
    private JPanel platosPanel;
    private List<JButton> botonesPlatos = new ArrayList<>();

    private JLabel totalConfirmar;
    private JPanel pedidosPanelAux;
    // Flags para opciones de pago y tipo de entrega
    private boolean pagarConTarjeta = false;
    private boolean pedidoADomicilio = false;

    // Lista que almacena los platos seleccionados en el pedido
    private List<TransferPlato> platosSeleccionados = new ArrayList<>();

    // Constructor recibe controlador y datos de usuario
    public GUICamareroImp(Controlador controlador, Object datos) {
        this.controlador = controlador;
        this.usuario = datos;
        initGUI();  // Inicializa la interfaz gráfica
    }

    // Método principal para inicializar la ventana y sus vistas en CardLayout
    private void initGUI() {
        frame = new JFrame("Panel del Camarero");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Creamos el CardLayout para cambiar entre panel de selección y confirmación
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        // Panel con la selección de platos
        JPanel panelCamarero = crearPanelCamarero();
        // Panel de confirmación de pedido
        JPanel panelConfirmacion = crearPanelConfirmacion();

        cards.add(panelCamarero, "camarero");
        cards.add(panelConfirmacion, "confirmacion");

        frame.add(cards);
        frame.setVisible(true);
    }

    // Crea el panel donde el camarero selecciona platos y ve el pedido actual
    private JPanel crearPanelCamarero() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // --- IZQUIERDA: Pedido Actual ---
        JPanel izquierdaPanel = new JPanel(new BorderLayout());
        izquierdaPanel.setPreferredSize(new Dimension(300, 600));

        // Título "Pedido Actual"
        JLabel tituloPedido = new JLabel("Pedido Actual", SwingConstants.CENTER);
        tituloPedido.setFont(new Font("Arial", Font.BOLD, 16));
        izquierdaPanel.add(tituloPedido, BorderLayout.NORTH);

        // Panel con la lista de platos agregados
        pedidosPanel = new JPanel();
        pedidosPanel.setLayout(new BoxLayout(pedidosPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPedido = new JScrollPane(pedidosPanel);
        izquierdaPanel.add(scrollPedido, BorderLayout.CENTER);

        // Panel auxiliar para mostrar en la confirmación
        pedidosPanelAux = new JPanel();
        pedidosPanelAux.setLayout(new BoxLayout(pedidosPanelAux, BoxLayout.Y_AXIS));

        // Panel inferior con información de mesa y total
        JPanel infoPedido = new JPanel(new GridLayout(4, 1));
        selectorMesa = new JComboBox<>(new String[]{"Mesa 1", "Mesa 2", "Mesa 3", "Para llevar"});
        infoPedido.add(new JLabel("Mesa:"));
        infoPedido.add(selectorMesa);

        totalPedido = new JLabel("Total: 0.00€");
        infoPedido.add(totalPedido);

        // Botón para tramitar el pedido y crear TransferPedido
        JButton tramitarBtn = new JButton("Tramitar Pedido");
        tramitarBtn.addActionListener(e -> {
            // Creamos el objeto TransferPedido con todos los datos
            TransferPedido pedido = new TransferPedido();
            pedido.setId("PED" + System.currentTimeMillis());  // ID único basado en timestamp
            pedido.setFecha(new Date());  // Fecha y hora actual
            pedido.setPlatos(new ArrayList<>(platosSeleccionados));  // Copiamos lista de platos
            pedido.setMetodoPago(pagarConTarjeta);  // True = tarjeta, False = efectivo

            // Tipo de entrega: true = en local, false = domicilio
            boolean tipoLocal = !"Para llevar".equals(selectorMesa.getSelectedItem());
            pedido.setTipo(tipoLocal);
            if (!tipoLocal) {
                pedido.setDireccion("Domicilio");  // Direccion ficticia o recoger de usuario
            }

            pedido.setNotas("");  // Campo de notas vacío por ahora
            pedido.setPreparado(false);  // Inicialmente no preparado

            // Llamada al controlador para guardar el pedido
            controlador.crearPedido(pedido);

            // Actualizamos la vista de confirmación y cambiamos de panel
            totalConfirmar.setText("Precio Total: " + String.format("%.2f€", total));
            cardLayout.show(cards, "confirmacion");
        });
        infoPedido.add(tramitarBtn);
        izquierdaPanel.add(infoPedido, BorderLayout.SOUTH);

        // --- DERECHA: Selección de platos ---
        JPanel derechaPanel = new JPanel(new BorderLayout());
        platosPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPlatos = new JScrollPane(platosPanel);
        scrollPlatos.getVerticalScrollBar().setUnitIncrement(40);
        derechaPanel.add(scrollPlatos, BorderLayout.CENTER);

        // Creamos botones de plato dinámicamente desde el controlador
        for (TransferPlato plato : controlador.obtenerPlatos()) {
            JButton botonPlato = new JButton(plato.getNombre());
            botonPlato.putClientProperty("categoria", plato.getCategoria());
            botonPlato.addActionListener(ev -> {
                // Cuando se pulsa, agregamos plato al pedido
                agregarPedido(plato);
                total += plato.getPrecio();  // Incrementamos total
                totalPedido.setText(String.format("Total: %.2f€", total));
            });
            botonesPlatos.add(botonPlato);
            platosPanel.add(botonPlato);
        }

        // Ajuste dinámico de tamaño de botones al redimensionar
        scrollPlatos.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int cols = 3;
                int buttonWidth = scrollPlatos.getViewport().getWidth() / cols;
                int rows = (int) Math.ceil(botonesPlatos.size() / (double) cols);
                int buttonHeight = scrollPlatos.getViewport().getHeight() / rows;
                for (JButton btn : botonesPlatos) {
                    btn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                }
                platosPanel.revalidate();
            }
        });

        // Filtro por categoría: Todos, Platos, Bebidas, Postres
        JPanel categoriasPanel = new JPanel(new GridLayout(1, 5));
        for (String cat : new String[]{"Todos", "Platos", "Bebidas", "Postres"}) {
            JButton btn = new JButton(cat);
            btn.addActionListener(ev -> filtrarPorCategoria(cat));
            categoriasPanel.add(btn);
        }
        derechaPanel.add(categoriasPanel, BorderLayout.SOUTH);

        // Montaje final del panel principal
        mainPanel.add(izquierdaPanel, BorderLayout.WEST);
        mainPanel.add(derechaPanel, BorderLayout.CENTER);

        // Botón para volver al menú principal
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVolverAlMenu = new JButton("Volver al menú");
        btnVolverAlMenu.addActionListener(ev -> {
            frame.dispose();
            new GUIMenuImp(controlador, usuario);
        });
        panelInferior.add(btnVolverAlMenu);
        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        return mainPanel;
    }

    // Crea el panel de confirmación donde se muestra el total y opciones de pago
    private JPanel crearPanelConfirmacion() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));

        // Lado izquierdo: total y checkboxes
        JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1));
        totalConfirmar = new JLabel("Precio Total: 0.00€", SwingConstants.CENTER);
        panelIzquierdo.add(totalConfirmar);

        JPanel panelCheckboxes = new JPanel();
        panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));

        JCheckBox checkTarjeta = new JCheckBox("Pagar con tarjeta");
        checkTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkTarjeta.addItemListener(ev -> pagarConTarjeta = checkTarjeta.isSelected());
        panelCheckboxes.add(checkTarjeta);

        JCheckBox checkDomicilio = new JCheckBox("Pedido a domicilio");
        checkDomicilio.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkDomicilio.addItemListener(ev -> pedidoADomicilio = checkDomicilio.isSelected());
        panelCheckboxes.add(checkDomicilio);

        panelIzquierdo.add(panelCheckboxes);

        // Lado derecho: lista de platos y botones
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
        JScrollPane scrollConfirm = new JScrollPane(pedidosPanelAux);
        panelDerecho.add(scrollConfirm);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnModificarPedido = new JButton("Modificar Pedido");
        btnModificarPedido.addActionListener(ev -> cardLayout.show(cards, "camarero"));
        panelBotones.add(btnModificarPedido);

        JButton btnPagar = new JButton("Pagar");
        btnPagar.addActionListener(ev -> {
            int opcion = JOptionPane.showConfirmDialog(frame,
                    "¿Estás seguro de que deseas pagar?",
                    "Confirmación de Pago", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(frame, "¡Pedido confirmado! El pago ha sido realizado.");
                frame.dispose();
            }
        });
        panelBotones.add(btnPagar);
        panelDerecho.add(panelBotones);

        panelPrincipal.add(panelIzquierdo);
        panelPrincipal.add(panelDerecho);
        panel.add(panelPrincipal, BorderLayout.CENTER);
        return panel;
    }

    // Agrega un plato a la lista visual y al modelo de datos
    private void agregarPedido(TransferPlato plato) {
        // Panel para el listado en la vista principal
        JPanel nuevoPedido = new JPanel(new BorderLayout());
        nuevoPedido.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Panel auxiliar para la vista de confirmación
        JPanel nuevoPedidoAux = new JPanel(new BorderLayout());
        nuevoPedidoAux.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Etiqueta con nombre y precio
        nuevoPedido.add(new JLabel(plato.getNombre() + " - " + plato.getPrecio() + "€"), BorderLayout.CENTER);
        nuevoPedido.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nuevoPedido.setPreferredSize(new Dimension(280, 40));

        // Botón para eliminar el plato seleccionado
        JButton btnEliminar = new JButton("X");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setOpaque(false);
        btnEliminar.addActionListener(ev -> {
            // Eliminar de la vista y del modelo de datos
            pedidosPanel.remove(nuevoPedido);
            pedidosPanelAux.remove(nuevoPedidoAux);
            total -= plato.getPrecio();
            platosSeleccionados.remove(plato);
            totalPedido.setText(String.format("Total: %.2f€", total));
            pedidosPanel.revalidate();
            pedidosPanel.repaint();
        });

        // Agregar componentes a los paneles
        nuevoPedido.add(btnEliminar, BorderLayout.EAST);
        pedidosPanel.add(nuevoPedido);
        pedidosPanel.revalidate();
        pedidosPanel.repaint();

        // Vista confirmación solo con etiqueta
        nuevoPedidoAux.add(new JLabel(plato.getNombre() + " - " + plato.getPrecio() + "€"), BorderLayout.CENTER);
        nuevoPedidoAux.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nuevoPedidoAux.setPreferredSize(new Dimension(280, 40));
        pedidosPanelAux.add(nuevoPedidoAux);

        // Agregar al modelo de datos
        platosSeleccionados.add(plato);
    }

    // Filtra los botones de plato por categoría seleccionada
    private void filtrarPorCategoria(String cat) {
        platosPanel.removeAll();
        for (JButton btn : botonesPlatos) {
            String categoria = (String) btn.getClientProperty("categoria");
            if ("Todos".equals(cat) || cat.equals(categoria)) {
                platosPanel.add(btn);
            }
        }
        // Rellenar con espacios vacíos para mantener la cuadrícula
        int totalComp = platosPanel.getComponentCount();
        for (int i = totalComp; i < 9; i++) {
            JPanel vacio = new JPanel();
            vacio.setOpaque(false);
            platosPanel.add(vacio);
        }
        platosPanel.revalidate();
        platosPanel.repaint();
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Método para recibir actualizaciones del controlador
        JOptionPane.showMessageDialog(frame, "Evento recibido en GUICamareroImp: " + evento);
    }
}

