package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import Negocio.TransferPedido;

public class IngresosPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelContenedor;
	private CardLayout cardLayout;
	private Controlador controlador;
	private JTable tabla;
	private List<TransferPedido> pedidos;
	private JLabel labelTotalIngresos; // NUEVO: Label para el total

	public IngresosPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
		this.panelContenedor = panelContenedor;
		this.cardLayout = cardLayout;
		this.controlador = controlador;
		this.pedidos = new ArrayList<>();
		initComponents();
	}

	private void cargarPedidosEnTabla() {
		String[] columnas = { "ID", "Fecha", "Hora", "Pago", "Tipo", "Dirección", "Precio (€)" };
		Object[][] datos = new Object[pedidos.size()][7];

		double totalIngresos = 0.0; // acumulador de ingresos

		for (int i = 0; i < pedidos.size(); i++) {
			TransferPedido p = pedidos.get(i);

			double precioPedido = 0.0;
			if (p.getPlatos() != null) {
				for (var plato : p.getPlatos()) {
					precioPedido += plato.getPrecio();
				}
			}

			datos[i][0] = p.getId();
			datos[i][1] = p.getFecha().toString();
			datos[i][2] = p.getHora();
			datos[i][3] = p.getMetodoPago() ? "Efectivo" : "Tarjeta";
			datos[i][4] = p.getTipo() ? "Aquí" : "Domicilio";
			datos[i][5] = p.getDireccion();
			datos[i][6] = String.format("%.2f", precioPedido);

			totalIngresos += precioPedido;
		}

		tabla.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));

		// Actualizar el label del total
		labelTotalIngresos.setText("Total ingresos: " + String.format("%.2f", totalIngresos) + " €");
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());
		JPanel panelSuperior = new JPanel(new BorderLayout()); // Panel de botón atrás y logo
		JPanel panelCentral = new JPanel(new BorderLayout());

		JLabel tituloPlantilla = new JLabel("Vista de Ingresos", SwingConstants.CENTER);
		tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
		panelCentral.add(tituloPlantilla, BorderLayout.NORTH);

		tabla = new JTable();
		JScrollPane scrollTabla = new JScrollPane(tabla);
		panelCentral.add(scrollTabla, BorderLayout.CENTER);

		// Panel para el total de ingresos debajo de la tabla
		labelTotalIngresos = new JLabel("Total ingresos: 0.00 €");
		labelTotalIngresos.setFont(new Font("Arial", Font.BOLD, 16));
		labelTotalIngresos.setForeground(new Color(0, 128, 0)); // Color verde
		labelTotalIngresos.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panelTotal = new JPanel(new BorderLayout());
		panelTotal.add(labelTotalIngresos, BorderLayout.EAST);
		panelCentral.add(panelTotal, BorderLayout.SOUTH);

		JPanel panelFiltros = new JPanel(new FlowLayout());
		JLabel dateText = new JLabel("Selecciona 2 fechas");
		JDateChooser dateChooser1 = new JDateChooser();
		JDateChooser dateChooser2 = new JDateChooser();

		JButton btnBuscar = new JButton("Buscar Pedidos");
		btnBuscar.setBackground(new Color(255, 69, 58));
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Arial", Font.BOLD, 13));
		btnBuscar.addActionListener(e -> {
			Date inicio = dateChooser1.getDate();
			Date fin = dateChooser2.getDate();
			pedidos = controlador.listaPedidos(inicio, fin);
			cargarPedidosEnTabla();
		});

		panelFiltros.add(dateText);
		panelFiltros.add(dateChooser1);
		panelFiltros.add(dateChooser2);
		panelFiltros.add(btnBuscar);

		ImageIcon volverIcono = new ImageIcon("resources/botonAtras.png");
		Image volverIconoImagen = volverIcono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		JButton btnVolver = new JButton("Volver", new ImageIcon(volverIconoImagen));
		btnVolver.setFont(new Font("Arial", Font.BOLD, 10));
		btnVolver.setForeground(Color.white);
		btnVolver.setPreferredSize(new Dimension(110, 20));
		btnVolver.setContentAreaFilled(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setFocusPainted(false);
		btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnVolver.setVerticalAlignment(SwingConstants.CENTER);
		btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVolver.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVolver.addActionListener(ev -> {
			cardLayout.show(panelContenedor, "menu");
		});

		ImageIcon logo = new ImageIcon("resources/logo.png");
		Image logoImagenEscalado = logo.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
		ImageIcon logoIconoEscalado = new ImageIcon(logoImagenEscalado);
		JLabel etiquetaImagen = new JLabel(logoIconoEscalado);

		panelSuperior.add(btnVolver, BorderLayout.LINE_START);
		panelSuperior.setBackground(new Color(100, 180, 255));
		panelSuperior.add(etiquetaImagen, BorderLayout.LINE_END);

		panelCentral.add(panelFiltros, BorderLayout.NORTH);

		this.add(panelSuperior, BorderLayout.PAGE_START);
		this.add(panelCentral);
		this.setVisible(true);
	}
}

