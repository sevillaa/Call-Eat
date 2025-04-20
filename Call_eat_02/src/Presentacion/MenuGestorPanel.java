package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
        JLabel titulo = new JLabel("Panel de Gestión", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(titulo, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnPlantilla = new JButton("Plantilla");
        btnPlantilla.addActionListener(e -> {
            //this.setVisible(false); // Ocultar la ventana actual del gestor
        	//System.out.println("fffsfs");
            cardLayout.show(panelContenedor,"empleados");
        });
        
		JButton btnInventario = new JButton("Inventario (Ingredientes y Platos)");
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

        JButton btnActividadEconomica = new JButton("Actividad Económica");
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

        this.add(panelOpciones, BorderLayout.CENTER);

        ////////////////////////// Botón para volver o cerrar//////////////////
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
        	SwingUtilities.getWindowAncestor(this).dispose();
            GUIGestor.resetInstancia();
            new GUIMenuImp(controlador, datos); // Reabre el menú con el usuario logueado

        });

        this.add(btnVolver, BorderLayout.SOUTH);
	}
}
