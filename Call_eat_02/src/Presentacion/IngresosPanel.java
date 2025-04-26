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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelContenedor;
	private CardLayout cardLayout;
	private Controlador controlador;
	private JTable tabla;
	private List<TransferPedido> pedidos;

	public IngresosPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
		this.panelContenedor = panelContenedor;
		this.cardLayout = cardLayout;
		this.controlador = controlador;
		this.pedidos = new ArrayList<>();
		initComponents();
	}


	/** Crea (o actualiza) el model de la tabla con el contenido de `pedidos`. */
	private void cargarPedidosEnTabla() {
	    String[] columnas = { "ID", "Fecha", "Hora", "Pago", "Tipo", "Dirección" };
	    Object[][] datos = new Object[pedidos.size()][6];

	    for (int i = 0; i < pedidos.size(); i++) {
	        TransferPedido p = pedidos.get(i);
	        datos[i][0] = p.getId();
	        datos[i][1] = p.getFecha().toString();
	        datos[i][2] = p.getHora();
	        datos[i][3] = p.getMetodoPago() ? "Efectivo" : "Tarjeta";
	        datos[i][4] = p.getTipo()       ? "Aquí"     : "Domicilio";
	        datos[i][5] = p.getDireccion();
	    }

	    tabla.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
	}


	private void initComponents() {
		this.setLayout(new BorderLayout());
		JPanel panelSuperior = new JPanel(new BorderLayout());// panel de boton atras y logo
		
		JPanel panelCentral = new JPanel(new BorderLayout());
		JLabel tituloPlantilla = new JLabel("Vista de Ingresos", SwingConstants.CENTER);
		tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
		panelCentral.add(tituloPlantilla, BorderLayout.NORTH);
		

		tabla = new JTable();
		cargarPedidosEnTabla();
		JScrollPane scrollTabla = new JScrollPane(tabla);
		panelCentral.add(scrollTabla, BorderLayout.CENTER);
		
		
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
		    Date fin    = dateChooser2.getDate();
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
		btnVolver.setContentAreaFilled(false); // elimina el fondo estirado
		btnVolver.setBorderPainted(false); // elimina el borde
		btnVolver.setFocusPainted(false); // quita ese borde de foco azul
		btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnVolver.setVerticalAlignment(SwingConstants.CENTER);
		btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVolver.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnVolver.addActionListener(ev -> {
			// plantillaFrame.dispose(); // Cerrar ventana de plantilla
			// frame.setVisible(true); // Volver a mostrar el panel gestor
			cardLayout.show(panelContenedor, "menu");
		});
		ImageIcon logo = new ImageIcon("resources/logo.png");
		Image locoImagenEscalado = logo.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
		ImageIcon logoIconoEscalado = new ImageIcon(locoImagenEscalado);
		JLabel etiquetaImagen = new JLabel(logoIconoEscalado);

		panelSuperior.add(btnVolver, BorderLayout.LINE_START);
		panelSuperior.setBackground(new Color(100, 180, 255));
		panelSuperior.add(etiquetaImagen, BorderLayout.LINE_END);
		panelCentral.add(panelFiltros, BorderLayout.SOUTH);
		
		this.add(panelSuperior, BorderLayout.PAGE_START);
		this.add(panelCentral);
		this.setVisible(true);
	}
}
