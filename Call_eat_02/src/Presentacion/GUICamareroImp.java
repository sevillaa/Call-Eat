package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUICamareroImp extends GUICamarero {

    private JFrame frame;
    private Controlador controlador;
    private Object usuario;

    private JTextArea pedidoArea;
    private JLabel totalLabel;
    private JComboBox<String> selectorMesa;
    private double total = 0.0;

    private List<String> platosEnPedido = new ArrayList<>();

    public GUICamareroImp(Controlador controlador, Object datos) {
        this.controlador = controlador;
        this.usuario = datos;
        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("Panel del Camarero");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel Principal dividido en 2 partes: izquierda y derecha
        JPanel mainPanel = new JPanel(new BorderLayout());

        // --- IZQUIERDA: Pedido Actual ---
        JPanel izquierdaPanel = new JPanel();
        izquierdaPanel.setLayout(new BorderLayout());
        izquierdaPanel.setPreferredSize(new Dimension(300, 600)); // 1/3 de 900px

        JLabel tituloPedido = new JLabel("Pedido Actual", SwingConstants.CENTER);
        tituloPedido.setFont(new Font("Arial", Font.BOLD, 16));
        izquierdaPanel.add(tituloPedido, BorderLayout.NORTH);

        pedidoArea = new JTextArea();
        pedidoArea.setEditable(false);
        JScrollPane scrollPedido = new JScrollPane(pedidoArea);
        izquierdaPanel.add(scrollPedido, BorderLayout.CENTER);

        // Panel inferior con total y opciones (dentro del panel izquierda)
        JPanel infoPedido = new JPanel(new GridLayout(4, 1));
        selectorMesa = new JComboBox<>(new String[]{"Mesa 1", "Mesa 2", "Mesa 3", "Para llevar"});
        infoPedido.add(new JLabel("Mesa:"));
        infoPedido.add(selectorMesa);
        totalLabel = new JLabel("Total: 0.00€");
        infoPedido.add(totalLabel);
        JButton tramitarBtn = new JButton("Tramitar Pedido");
        tramitarBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Pedido tramitado.\n(No implementado aún)");
        });
        infoPedido.add(tramitarBtn);
        izquierdaPanel.add(infoPedido, BorderLayout.SOUTH);

        // --- DERECHA: Platos y categorías ---
        JPanel derechaPanel = new JPanel();
        derechaPanel.setLayout(new BorderLayout());
        JPanel platosPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // simulación de platos

        // Mock platos
        for (int i = 1; i <= 9; i++) {
            String nombre = "Plato " + i;
            double precio = 5.0 + i;
            JButton botonPlato = new JButton(nombre + " - " + precio + "€");
            botonPlato.addActionListener(e -> {
                platosEnPedido.add(nombre);
                total += precio;
                actualizarPedido();
            });
            platosPanel.add(botonPlato);
        }
        derechaPanel.add(platosPanel, BorderLayout.CENTER);

        // --- Botones de categoría ---
        JPanel categoriasPanel = new JPanel(new GridLayout(1, 5));
        String[] categorias = {"Menú del Día", "Carta", "Bebidas", "Postres", "Ofertas"};
        for (String cat : categorias) {
            JButton btn = new JButton(cat);
            btn.addActionListener(e -> {
                JOptionPane.showMessageDialog(frame, "Mostrar categoría: " + cat + "\n(No implementado)");
            });
            categoriasPanel.add(btn);
        }
        derechaPanel.add(categoriasPanel, BorderLayout.SOUTH);

        // --- Montaje final del mainPanel ---
        mainPanel.add(izquierdaPanel, BorderLayout.WEST);
        mainPanel.add(derechaPanel, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);

        // --- Panel de navegación: Botón "Volver al menú" ---
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVolverAlMenu = new JButton("Volver al menú");
        btnVolverAlMenu.addActionListener(e -> {
            frame.dispose(); // Cierra la ventana actual del camarero
            new GUIMenuImp(controlador, usuario); // Abre el menú principal
        });
        panelInferior.add(btnVolverAlMenu);
        frame.add(panelInferior, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void actualizarPedido() {
        StringBuilder sb = new StringBuilder();
        for (String p : platosEnPedido) {
            sb.append(p).append("\n");
        }
        pedidoArea.setText(sb.toString());
        totalLabel.setText(String.format("Total: %.2f€", total));
    }

    @Override
    public void actualizar(int evento, Object datos) {
        JOptionPane.showMessageDialog(frame, "Evento recibido en GUICamareroImp: " + evento);
    }
}
