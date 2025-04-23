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
	private JFrame frame;
	private List<TransferPedido> pedidos;

	public IngresosPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
		this.panelContenedor = panelContenedor;
		this.cardLayout = cardLayout;
		this.controlador = controlador;
		this.pedidos = new ArrayList<>();
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new BorderLayout());
		JPanel panelSuperior = new JPanel(new BorderLayout());// panel de boton atras y logo
		JPanel panelCentral = new JPanel(new BorderLayout());
		JLabel tituloPlantilla = new JLabel("Vista de Ingresos", SwingConstants.CENTER);
		tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
		panelCentral.add(tituloPlantilla, BorderLayout.NORTH);

		// Crear la tabla con los empleados
		String[] columnas = { "ID", "Nombre", "Correo", "Rol" };
		Object[][] datosEmpleados = new Object[pedidos.size()][6];

		for (int i = 0; i < pedidos.size(); i++) {
			TransferPedido pedido = pedidos.get(i);
			datosEmpleados[i][0] = pedido.getID();
			datosEmpleados[i][1] = pedido.getFecha();
			datosEmpleados[i][2] = pedido.getHora();
			
			if(pedido.getMetodoPago()) {
				datosEmpleados[i][3] = "Efectivo";
			} else {
				datosEmpleados[i][3] = "Targeta";
			}
			
			if(pedido.getTipo()) {
				datosEmpleados[i][4] = "Aqui";
			} else {
				datosEmpleados[i][4] = "Domicilio";
			}
			datosEmpleados[i][5] = pedido.getDireccion();

		}

		JTable tabla = new JTable(datosEmpleados, columnas);
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
			pedidos = controlador.listaPedidos(dateChooser1.getDate(), dateChooser2.getDate());
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
		panelCentral.add(panelFiltros, BorderLayout.CENTER);
		
		this.add(panelSuperior, BorderLayout.PAGE_START);
		this.add(panelCentral);
		this.setVisible(true);
	}
}
