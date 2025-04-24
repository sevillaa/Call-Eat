package Presentacion;

import javax.swing.*;
import Negocio.TransferPlato;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class GUICamareroImp extends GUICamarero {

	private Controlador controlador;
	private Object usuario;

	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel cards;

	private double total = 0.0;
	private JLabel totalPedido;
	private JComboBox<String> selectorMesa;
	private JPanel pedidosPanel;
	private JPanel platosPanel;
	private List<JButton> botonesPlatos = new ArrayList<>();

	private JLabel totalConfirmar;
	private JPanel pedidosPanelAux;
	private boolean pagarConTarjeta = false;
	private boolean pedidoADomicilio = false;

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

		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);

		JPanel panelCamarero = crearPanelCamarero();
		JPanel panelConfirmacion = crearPanelConfirmacion();

		cards.add(panelCamarero, "camarero");
		cards.add(panelConfirmacion, "confirmacion");

		frame.add(cards);
		frame.setVisible(true);
	}

	private JPanel crearPanelCamarero() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		// --- IZQUIERDA: Pedido Actual ---
		JPanel izquierdaPanel = new JPanel();
		izquierdaPanel.setLayout(new BorderLayout());
		izquierdaPanel.setPreferredSize(new Dimension(300, 600)); // 1/3 de 900px

		JLabel tituloPedido = new JLabel("Pedido Actual", SwingConstants.CENTER);
		tituloPedido.setFont(new Font("Arial", Font.BOLD, 16));
		izquierdaPanel.add(tituloPedido, BorderLayout.NORTH);

		// Lista de pedidos
		pedidosPanel = new JPanel();
		pedidosPanel.setLayout(new BoxLayout(pedidosPanel, BoxLayout.Y_AXIS));

		JScrollPane scrollPedido = new JScrollPane(pedidosPanel);
		izquierdaPanel.add(scrollPedido, BorderLayout.CENTER);

		// Lista de pedidos auxiliar para usar posteriormente
		pedidosPanelAux = new JPanel();
		pedidosPanelAux.setLayout(new BoxLayout(pedidosPanelAux, BoxLayout.Y_AXIS));

		// Informacion de pedido + boton de tramitar
		JPanel infoPedido = new JPanel(new GridLayout(4, 1));
		////////REVISAR///////////
		///////////REVISAR///////////
		///////////REVISAR///////////
		///////////REVISAR///////////
		///////////REVISAR///////////
		///////////REVISAR///////////
////////REVISAR///////////
///	TODO
		selectorMesa = new JComboBox<>(new String[] { "Mesa 1", "Mesa 2", "Mesa 3", "Para llevar" });
		infoPedido.add(new JLabel("Mesa:"));
		infoPedido.add(selectorMesa);
		totalPedido = new JLabel("Total: 0.00€");
		infoPedido.add(totalPedido);

		JButton tramitarBtn = new JButton("Tramitar Pedido");
		tramitarBtn.addActionListener(e -> {
			totalConfirmar.setText("Precio Total: " + String.format("%.2f€", total));
			cardLayout.show(cards, "confirmacion");
		});
		infoPedido.add(tramitarBtn);

		izquierdaPanel.add(infoPedido, BorderLayout.SOUTH);

		// --- DERECHA: Platos y categorías ---
		JPanel derechaPanel = new JPanel();
		derechaPanel.setLayout(new BorderLayout());

		// Agregar platos (mock)
		platosPanel = new JPanel(new GridLayout(0, 3, 10, 10));
		JScrollPane scrollPlatos = new JScrollPane(platosPanel);
		scrollPlatos.getVerticalScrollBar().setUnitIncrement(40);
		derechaPanel.add(scrollPlatos, BorderLayout.CENTER);

		for (TransferPlato plato : controlador.obtenerPlatos()) {
			JButton botonPlato = new JButton();
			// Redimensionar la imagen del plato
			ImageIcon icon = new ImageIcon(plato.getIconPath());
			icon = new ImageIcon(icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)); // Cambiar tamaño de
																									// la imagen
			botonPlato.setIcon(icon);

			botonPlato.putClientProperty("categoria", plato.getCategoria());
			botonPlato.addActionListener(e -> {
				this.agregarPedido(plato);
				total += plato.getPrecio();
				totalPedido.setText(String.format("Total: %.2f€", total));
			});

			botonesPlatos.add(botonPlato);
			platosPanel.add(botonPlato);
		}

		// Redimensionar botones al cambiar tamaño
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

		// Botones de categoría
		JPanel categoriasPanel = new JPanel(new GridLayout(1, 5));
		String[] categorias = { "Todos", "Platos", "Bebidas", "Postres" };
		for (String cat : categorias) {
			JButton btn = new JButton(cat);
			btn.addActionListener(e -> filtrarPorCategoria(cat));
			categoriasPanel.add(btn);
		}
		derechaPanel.add(categoriasPanel, BorderLayout.SOUTH);

		// Montaje final
		mainPanel.add(izquierdaPanel, BorderLayout.WEST);
		mainPanel.add(derechaPanel, BorderLayout.CENTER);

		// Botón Volver al Menú
		JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnVolverAlMenu = new JButton("Volver al menú");
		btnVolverAlMenu.addActionListener(e -> {
			frame.dispose();
			new GUIMenuImp(controlador, usuario);
		});
		panelInferior.add(btnVolverAlMenu);
		mainPanel.add(panelInferior, BorderLayout.SOUTH);

		return mainPanel;
	}

	private JPanel crearPanelConfirmacion() {
		JPanel panel = new JPanel(new BorderLayout());

		// Panel principal dividido en izquierda y derecha
		JPanel panelPrincipal = new JPanel(new GridLayout(1, 2));

		// Izquierda (GridLayout de 1 columna y 3 filas)
		JPanel panelIzquierdo = new JPanel(new GridLayout(3, 1));
		
		totalConfirmar = new JLabel("Precio Total: " + String.format("%.2f€", total), SwingConstants.CENTER);
		panelIzquierdo.add(totalConfirmar);
		
		// Panel para los checkboxes (para no alterar el GridLayout principal)
		JPanel panelCheckboxes = new JPanel();
		panelCheckboxes.setLayout(new BoxLayout(panelCheckboxes, BoxLayout.Y_AXIS));
	
	    JCheckBox checkTarjeta = new JCheckBox("Pagar con tarjeta");
	    checkTarjeta.setAlignmentX(Component.CENTER_ALIGNMENT);
	    checkTarjeta.addItemListener(e -> pagarConTarjeta = checkTarjeta.isSelected());
	    panelCheckboxes.add(checkTarjeta);

	    JCheckBox checkDomicilio = new JCheckBox("Pedido a domicilio");
	    checkDomicilio.setAlignmentX(Component.CENTER_ALIGNMENT);
	    checkDomicilio.addItemListener(e -> pedidoADomicilio = checkDomicilio.isSelected());
	    panelCheckboxes.add(checkDomicilio);

	    panelIzquierdo.add(panelCheckboxes);

		// Derecha (GridLayout de 1 columna y 2 filas)
		JPanel panelDerecho = new JPanel(new GridLayout(2, 1));
		JScrollPane scrollPlatosConfirmacion = new JScrollPane(pedidosPanelAux);
		panelDerecho.add(scrollPlatosConfirmacion);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnModificarPedido = new JButton("Modificar Pedido");
		btnModificarPedido.addActionListener(e -> {
			cardLayout.show(cards, "camarero");
		});
		panelBotones.add(btnModificarPedido);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(e -> {
		    int opcion = JOptionPane.showConfirmDialog(frame,
		            "¿Estás seguro de que deseas pagar?",
		            "Confirmación de Pago",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE);

		    if (opcion == JOptionPane.YES_OPTION) {
		        JOptionPane.showMessageDialog(frame, "¡Pedido Confirmado! El pago ha sido realizado.");
		        frame.dispose();
		    }
		});
		panelBotones.add(btnPagar);
		
		panelDerecho.add(panelBotones);

		// Añadir ambos paneles al principal
		panelPrincipal.add(panelIzquierdo);
		panelPrincipal.add(panelDerecho);

		panel.add(panelPrincipal, BorderLayout.CENTER);

		return panel;
	}

	private void agregarPedido(TransferPlato plato) {
		JPanel nuevoPedido = new JPanel(new BorderLayout());
		nuevoPedido.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel nuevoPedidoAux = new JPanel(new BorderLayout());
		nuevoPedidoAux.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Lista origanal con los platos y su boton de eliminar
		nuevoPedido.add(new JLabel(plato.getNombre() + " - " + plato.getPrecio() + "€"), BorderLayout.CENTER);
		nuevoPedido.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		nuevoPedido.setPreferredSize(new Dimension(280, 40));

		JButton btnEliminar = new JButton("X");
		btnEliminar.setContentAreaFilled(false);
		btnEliminar.setBorderPainted(false);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setOpaque(false);
		btnEliminar.addActionListener(e -> {
			pedidosPanel.remove(nuevoPedido);
			pedidosPanelAux.remove(nuevoPedidoAux);
			total -= plato.getPrecio();
			totalPedido.setText(String.format("Total: %.2f€", total));
			pedidosPanel.revalidate();
			pedidosPanel.repaint();
		});

		nuevoPedido.add(btnEliminar, BorderLayout.EAST);
		pedidosPanel.add(nuevoPedido);
		pedidosPanel.revalidate();
		pedidosPanel.repaint();

		// Lista auxiliar con solo los platos
		nuevoPedidoAux.add(new JLabel(plato.getNombre() + " - " + plato.getPrecio() + "€"), BorderLayout.CENTER);
		nuevoPedidoAux.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		nuevoPedidoAux.setPreferredSize(new Dimension(280, 40));
		pedidosPanelAux.add(nuevoPedidoAux);
	}

	private void filtrarPorCategoria(String cat) {
		platosPanel.removeAll();

		for (JButton b : botonesPlatos) {
			String categoriaBoton = (String) b.getClientProperty("categoria");
			if (cat.equals("Todos") || (categoriaBoton != null && categoriaBoton.equals(cat))) {
				platosPanel.add(b);
			}
		}

		int totalPlatos = platosPanel.getComponentCount();
		for (int i = totalPlatos; i < 9; i++) {
			JPanel panelVacio = new JPanel();
			panelVacio.setOpaque(false);
			platosPanel.add(panelVacio);
		}

		platosPanel.revalidate();
		platosPanel.repaint();
	}

	@Override
	public void actualizar(int evento, Object datos) {
		JOptionPane.showMessageDialog(frame, "Evento recibido en GUICamareroImp: " + evento);
	}
}
