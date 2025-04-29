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
	private String anotacion;
	// Flags para opciones de pago y tipo de entrega
	private boolean pagarConTarjeta = false;
	private boolean pedidoADomicilio = false;

	// Lista que almacena los platos seleccionados en el pedido
	private List<TransferPlato> platosSeleccionados = new ArrayList<>();

	// Constructor recibe controlador y datos de usuario
	public GUICamareroImp(Controlador controlador, Object datos) {
		this.controlador = controlador;
		this.usuario = datos;
		initGUI(); // Inicializa la interfaz gráfica
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
		selectorMesa = new JComboBox<>(new String[] { "Mesa 1", "Mesa 2", "Mesa 3", "Para llevar" });
		infoPedido.add(new JLabel("Mesa:"));
		infoPedido.add(selectorMesa);

		totalPedido = new JLabel("Total: 0.00€");
		infoPedido.add(totalPedido);

		// Botón para tramitar el pedido y crear TransferPedido
		JButton tramitarBtn = new JButton("Tramitar Pedido");
		tramitarBtn.addActionListener(e -> {
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
		    JButton botonPlato = new JButton();
		    ImageIcon icon = new ImageIcon(plato.getIconPath());
		    icon = new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
		    botonPlato.setIcon(icon);
		    botonPlato.putClientProperty("categoria", plato.getCategoria());
		    botonPlato.addActionListener(ev -> {
		        // Verificamos si hay suficientes ingredientes para el plato
		        if (controlador.compruebaIngredientes(plato)) {
		            // Si hay ingredientes, agregamos el plato al pedido
		            agregarPedido(plato);
		            total += plato.getPrecio(); // Incrementamos el total
		            totalPedido.setText(String.format("Total: %.2f€", total));
		        } else {
		            // Si no hay ingredientes, mostramos un mensaje de advertencia
		            JOptionPane.showMessageDialog(frame, 
		                "No se puede añadir el plato '" + plato.getNombre() + "'\nFaltan ingredientes.",
		                "Plato no disponible", JOptionPane.WARNING_MESSAGE);
		        }
		    });
		    botonesPlatos.add(botonPlato);
		    platosPanel.add(botonPlato);
		}


		// Ajuste dinámico de tamaño de botones al redimensionar
		scrollPlatos.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int buttonHeight = scrollPlatos.getViewport().getHeight() / 3;
				int buttonWidth = scrollPlatos.getViewport().getWidth() / 3;

				for (JButton btn : botonesPlatos) {
					// Ajustar el tamaño del icono según el tamaño del botón
					ImageIcon icon = (ImageIcon) btn.getIcon();
					if (icon != null) {
						icon = new ImageIcon(
								icon.getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_SMOOTH));
						btn.setIcon(icon);
					}

					btn.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
					btn.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
				}
				platosPanel.revalidate();
			}
		});

		// Filtro por categoría: Todos, Platos, Bebidas, Postres
		JPanel categoriasPanel = new JPanel(new GridLayout(1, 5));
		for (String cat : new String[] { "Todos", "Platos", "Bebidas", "Postres" }) {
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
			new GUIMenuPrincipalImp(controlador, usuario);
		});
		panelInferior.add(btnVolverAlMenu);
		mainPanel.add(panelInferior, BorderLayout.SOUTH);

		return mainPanel;
	}

	// Crea el panel de confirmación donde se muestra el total y opciones de pago
	private JPanel crearPanelConfirmacion() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());

		// Panel Superior con nombre de la apliación
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBackground(new Color(100, 180, 255));
		panelSuperior.setPreferredSize(new Dimension(0, 63));
		JLabel titulo = new JLabel("Call&Eat", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 25));
		titulo.setForeground(Color.WHITE);
		panelSuperior.add(titulo, BorderLayout.CENTER);

		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

		// Panel Central
		JPanel panelCentral = new JPanel(new GridLayout(1, 2));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 0));

		// Lado izquierdo: confirmar pedido, total ,checkboxes e informacion sobre mesas
		// y num. clientes
		JPanel panelIzquierdo = new JPanel(new GridLayout(4, 1));

		JPanel panelConfirmarPedido = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel confirmarPedido = new JLabel("Confirmar Pedido");
		confirmarPedido.setFont(new Font("Arial", Font.BOLD, 40));
		panelConfirmarPedido.add(confirmarPedido);
		panelIzquierdo.add(panelConfirmarPedido);

		JPanel panelTotal = new JPanel(new FlowLayout());
		totalConfirmar = new JLabel("Precio Total: 0.00€");
		totalConfirmar.setFont(new Font("Arial", Font.BOLD, 20));
		panelTotal.add(totalConfirmar);
		panelIzquierdo.add(panelTotal);

		JPanel panelCheckboxes = new JPanel();
		panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));
		JCheckBox checkTarjeta = new JCheckBox("Pagar con tarjeta");
		checkTarjeta.setFont(new Font("Arial", Font.BOLD, 20));
		checkTarjeta.addItemListener(ev -> pagarConTarjeta = checkTarjeta.isSelected());
		panelCheckboxes.add(checkTarjeta);
		JCheckBox checkDomicilio = new JCheckBox("Pedido a domicilio");
		checkDomicilio.setFont(new Font("Arial", Font.BOLD, 20));
		checkDomicilio.addItemListener(ev -> pedidoADomicilio = checkDomicilio.isSelected());
		panelCheckboxes.add(checkDomicilio);
		panelIzquierdo.add(panelCheckboxes);

		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		JLabel totalClientes = new JLabel("Total Clientes: 5");
		totalClientes.setFont(new Font("Arial", Font.BOLD, 20));
		panelInfo.add(totalClientes);
		JLabel mesaAsignada = new JLabel("Mesa asignada: " + selectorMesa.getSelectedItem().toString());
		mesaAsignada.setFont(new Font("Arial", Font.BOLD, 20));
		panelInfo.add(mesaAsignada);
		panelIzquierdo.add(panelInfo);

		// Lado derecho: lista de platos y botones
		JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
		JScrollPane scrollConfirm = new JScrollPane(pedidosPanelAux);
		panelDerecho.add(scrollConfirm);
		scrollConfirm.setBorder(null);

		JPanel panelBotones = new JPanel(new GridBagLayout());

		// Configuración para centrar los botones
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE; // No expandir el botón
		gbc.insets = new Insets(10, 10, 10, 10); // Espaciado alrededor del botón

		// Botón "Modificar Pedido"
		JButton btnModificarPedido = new JButton(new ImageIcon("resources/GUICamarero/modificar_pedido.png"));
		btnModificarPedido.setContentAreaFilled(false);
		btnModificarPedido.setBorderPainted(false);
		btnModificarPedido.setFocusPainted(false);
		btnModificarPedido.setOpaque(false);
		btnModificarPedido.addActionListener(ev -> cardLayout.show(cards, "camarero"));
		panelBotones.add(btnModificarPedido, gbc);
		gbc.gridx++;

		// Botón "Anotacion"
		JButton btnAnotacion = new JButton(new ImageIcon("resources/GUICamarero/anotacion.png"));
		btnAnotacion.setContentAreaFilled(false);
		btnAnotacion.setBorderPainted(false);
		btnAnotacion.setFocusPainted(false);
		btnAnotacion.setOpaque(false);
		btnAnotacion.addActionListener(ev -> {
			JDialog dialogoAnotacion = new JDialog(frame, "Añadir anotación", true);
			dialogoAnotacion.setSize(500, 200);
			dialogoAnotacion.setLocationRelativeTo(frame);
			dialogoAnotacion.setLayout(new BorderLayout());

			// Área de texto para la anotación
			JTextArea areaTexto = new JTextArea();
			areaTexto.setLineWrap(true);
			areaTexto.setWrapStyleWord(true);
			JScrollPane scroll = new JScrollPane(areaTexto);
			dialogoAnotacion.add(scroll, BorderLayout.CENTER);

			// Panel de botones
			JPanel panelBotonesDialogo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JButton btnConfirmar = new JButton("Confirmar");
			JButton btnCancelar = new JButton("Cancelar");

			btnConfirmar.addActionListener(e -> {
				anotacion = areaTexto.getText().trim();
				dialogoAnotacion.dispose();
			});

			btnCancelar.addActionListener(e -> dialogoAnotacion.dispose());

			panelBotonesDialogo.add(btnCancelar);
			panelBotonesDialogo.add(btnConfirmar);

			dialogoAnotacion.add(panelBotonesDialogo, BorderLayout.SOUTH);
			dialogoAnotacion.setVisible(true);
		});
		panelBotones.add(btnAnotacion, gbc);
		gbc.gridx++;

		// Botón "Pagar"
		JButton btnPagar = new JButton(new ImageIcon("resources/GUICamarero/pagar.png"));
		btnPagar.setContentAreaFilled(false);
		btnPagar.setBorderPainted(false);
		btnPagar.setFocusPainted(false);
		btnPagar.setOpaque(false);
		btnPagar.addActionListener(ev -> {
			int opcion = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de que deseas pagar?",
					"Confirmación de Pago", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcion == JOptionPane.YES_OPTION) {

				// Creamos el objeto TransferPedido con todos los datos
				TransferPedido pedido = new TransferPedido();
				pedido.setId("PED" + System.currentTimeMillis()); // ID único basado en timestamp
				pedido.setFecha(new Date()); // Fecha y hora actual
				pedido.setPlatos(new ArrayList<>(platosSeleccionados)); // Copiamos lista de platos
				pedido.setMetodoPago(pagarConTarjeta); // True = tarjeta, False = efectivo

				// Siempre se guarda el destino en el campo direccion, sea mesa o para llevar
				String seleccionMesa = selectorMesa.getSelectedItem().toString();
				pedido.setDireccion(seleccionMesa);
				pedido.setTipo(pedidoADomicilio); // true si es para llevar, false si es en el local

				pedido.setNotas(anotacion); // Campo de notas vacío por ahora
				pedido.setPreparado(false); // Inicialmente no preparado

				// Llamada al controlador para guardar el pedido
				controlador.crearPedido(pedido);

				JOptionPane.showMessageDialog(frame, "¡Pedido confirmado! El pago ha sido realizado.");

				// Reiniciar datos del pedido
				platosSeleccionados.clear();
				total = 0.0;
				totalPedido.setText("Total: 0.00€");

				// Limpiar paneles visuales
				pedidosPanel.removeAll();
				pedidosPanelAux.removeAll();
				pedidosPanel.revalidate();
				pedidosPanel.repaint();
				pedidosPanelAux.revalidate();
				pedidosPanelAux.repaint();

				// Reset de selección de mesa y checkboxes
				selectorMesa.setSelectedIndex(0);
				pagarConTarjeta = false;
				pedidoADomicilio = false;

				// Mostrar de nuevo el panel principal del camarero
				cardLayout.show(cards, "camarero");
			}
		});

		panelBotones.add(btnPagar, gbc);
		panelDerecho.add(panelBotones);

		panelCentral.add(panelIzquierdo);
		panelCentral.add(panelDerecho);

		panelPrincipal.add(panelCentral, BorderLayout.CENTER);

		return panelPrincipal;
	}

	// Agrega un plato a la lista visual y al modelo de datos
	private void agregarPedido(TransferPlato plato) {

		if (this.controlador.compruebaIngredientes(plato)) { // Si hay suficiente ingredientes para hacer el plato
			// Reducimos la cantidad de los ingredientes usados
			controlador.restaIngredientes(plato);

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
				controlador.sumaIngredientes(plato);
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
			JLabel nombrePlato = new JLabel(plato.getNombre() + " - " + plato.getPrecio() + "€");
			nombrePlato.setFont(new Font("Arial", Font.BOLD, 20));
			nuevoPedidoAux.add(nombrePlato, BorderLayout.CENTER);
			nuevoPedidoAux.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
			nuevoPedidoAux.setPreferredSize(new Dimension(280, 40));
			pedidosPanelAux.add(nuevoPedidoAux);

			// Agregar al modelo de datos
			platosSeleccionados.add(plato);
		} else {
			JOptionPane.showMessageDialog(frame,
					"No se puede añadir el plato '" + plato.getNombre() + "'\nFaltan ingredientes.",
					"Plato no disponible", JOptionPane.WARNING_MESSAGE);

		}

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
