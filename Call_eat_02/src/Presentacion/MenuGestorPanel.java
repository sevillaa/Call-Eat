package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MenuGestorPanel extends JPanel {
	private JPanel panelContenedor;
	private CardLayout cardLayout;
	//private JFrame frame;
	private Controlador controlador;
	private Object datos;

	public MenuGestorPanel(CardLayout cardLayout, JPanel panelContenedor, Controlador controlador, Object datos) {
		this.panelContenedor = panelContenedor;
		this.cardLayout = cardLayout;
		this.controlador=controlador;
		this.datos=datos;
		initComponents();
	}
	
	private void initComponents() {
		/*frame = new JFrame("Panel Gestor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());*/
		this.setLayout(new BorderLayout());
		JPanel panelSuperior=new JPanel(new BorderLayout());
		JPanel panelSubSuperior=new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Panel de Gestión", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // top, left, bottom, right
        panelSuperior.add(titulo, BorderLayout.CENTER);
        panelSuperior.setBackground(Color.WHITE);
        JPanel panelOpciones = new JPanel(new GridLayout(1, 3, 10, 10));
        
        ImageIcon iconoOriginal1 = new ImageIcon("resources/Botones_menu_gestor/empleado.png");
        Image imagenEscalada1 = iconoOriginal1.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        JButton btnPlantilla = new JButton("Empleados",new ImageIcon(imagenEscalada1));
        btnPlantilla.setPreferredSize(new Dimension(100, 100));
        btnPlantilla.setContentAreaFilled(false); // elimina el fondo estirado
        btnPlantilla.setBorderPainted(false);    // elimina el borde
        btnPlantilla.setFocusPainted(false);     // quita ese borde de foco azul
        btnPlantilla.setHorizontalAlignment(SwingConstants.CENTER);
        btnPlantilla.setVerticalAlignment(SwingConstants.CENTER);
        btnPlantilla.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPlantilla.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPlantilla.addActionListener(e -> {
            //this.setVisible(false); // Ocultar la ventana actual del gestor
        	//System.out.println("fffsfs");
            cardLayout.show(panelContenedor,"empleados");
        });
        
        ImageIcon iconoOriginal2 = new ImageIcon("resources/Botones_menu_gestor/inventario.png");
        Image imagenEscalada2 = iconoOriginal2.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		JButton btnInventario = new JButton("Inventario",new ImageIcon(imagenEscalada2));
		btnInventario.setPreferredSize(new Dimension(100, 100));
		btnInventario.setContentAreaFilled(false); // elimina el fondo estirado
		btnInventario.setBorderPainted(false);    // elimina el borde
		btnInventario.setFocusPainted(false);     // quita ese borde de foco azul
		btnInventario.setHorizontalAlignment(SwingConstants.CENTER);
		btnInventario.setVerticalAlignment(SwingConstants.CENTER);
		btnInventario.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInventario.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnInventario.addActionListener(e -> {
            // Aquí se abrirá la GUI de inventario en el futuro
        	/*
        	 * 1.Debe sacar una lista con los ingredientes asi como de su stock
        	 * 2.Debe permitir modificar los ingredientes de la lista
        	 * 3.Debe poder añadir nuevos ingredientes
        	 */
           // JOptionPane.showMessageDialog(frame, "Inventario aún no implementado");
            // controlador.accion(Eventos.MOSTRAR_INVENTARIO, null);
        	cardLayout.show(panelContenedor,"inventario");
        });
        ImageIcon iconoOriginal3 = new ImageIcon("resources/Botones_menu_gestor/ingresos.png");
        Image imagenEscalada3 = iconoOriginal3.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        JButton btnActividadEconomica = new JButton("Ingresos",new ImageIcon(imagenEscalada3));
        btnActividadEconomica.setPreferredSize(new Dimension(100, 100));
        btnActividadEconomica.setContentAreaFilled(false); // elimina el fondo estirado
        btnActividadEconomica.setBorderPainted(false);    // elimina el borde
        btnActividadEconomica.setFocusPainted(false);     // quita ese borde de foco azul
        btnActividadEconomica.setHorizontalAlignment(SwingConstants.CENTER);
        btnActividadEconomica.setVerticalAlignment(SwingConstants.CENTER);
		btnActividadEconomica.setHorizontalTextPosition(SwingConstants.CENTER);
		btnActividadEconomica.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnActividadEconomica.addActionListener(e -> {
            // En el futuro se puede abrir un panel para filtrar pedidos, etc.
        	/*
        	 * Este apartado deberia poder filtrar de la base de datos de pedidos para poder obtener 
        	 * un registro de beenficios mensuales/diarios
        	 */
            //JOptionPane.showMessageDialog(frame, "Actividad económica aún no implementada");
        	cardLayout.show(panelContenedor,"ingresos");
            // controlador.accion(Eventos.MOSTRAR_ESTADISTICAS, null);
        });

        panelOpciones.add(btnPlantilla);
        panelOpciones.add(btnInventario);
        panelOpciones.add(btnActividadEconomica);
        panelOpciones.setBackground(Color.WHITE);
        this.add(panelOpciones, BorderLayout.CENTER);

        ////////////////////////// Botón para volver o cerrar//////////////////
        ImageIcon volverIcono = new ImageIcon("resources/botonAtras.png");
        Image volverIconoImagen = volverIcono.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        JButton btnVolver = new JButton("Volver",new ImageIcon(volverIconoImagen));
        btnVolver.setFont(new Font("Arial",Font.BOLD,10));
        btnVolver.setForeground(Color.white);
       // btnVolver.setPreferredSize(new Dimension(100, 100));
        btnVolver.setContentAreaFilled(false); // elimina el fondo estirado
        btnVolver.setBorderPainted(false);    // elimina el borde
        btnVolver.setFocusPainted(false);     // quita ese borde de foco azul
        btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
        btnVolver.setVerticalAlignment(SwingConstants.CENTER);
        btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolver.setVerticalTextPosition(SwingConstants.BOTTOM);
        //btnVolver.setPreferredSize(new Dimension(100,20));
        //btnVolver.setMinimumSize(new Dimension(100,20));
        btnVolver.addActionListener(e -> {
        	SwingUtilities.getWindowAncestor(this).dispose();
            GUIGestor.resetInstancia();
            new GUIMenuImp(controlador, datos); // Reabre el menú con el usuario logueado

        });
        ImageIcon logo = new ImageIcon("resources/logo.png"); 
        Image locoImagenEscalado = logo.getImage().getScaledInstance(52,52, Image.SCALE_SMOOTH);
        ImageIcon logoIconoEscalado=new ImageIcon(locoImagenEscalado);
        JLabel etiquetaImagen = new JLabel(logoIconoEscalado);
        
        
        
        panelSubSuperior.add(btnVolver, BorderLayout.LINE_START);
        panelSubSuperior.setBackground(new Color(100, 180, 255));
        //panelSuperior.setBackground(new Color(173, 216, 230));
        panelSubSuperior.add(etiquetaImagen,BorderLayout.LINE_END);
        panelSuperior.add(panelSubSuperior,BorderLayout.PAGE_START);
        this.add(panelSuperior,BorderLayout.NORTH);
	}
}
