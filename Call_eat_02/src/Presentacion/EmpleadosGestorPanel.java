package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Negocio.TransferEmpleado;

public class EmpleadosGestorPanel extends JPanel{

	private JPanel panelContenedor;
	private CardLayout cardLayout;
	private Controlador controlador;
	private JFrame frame;
	private Object datos;

	public EmpleadosGestorPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador, Object datos) {
		this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;
        this.controlador = controlador;
        this.datos=datos;
        initComponents();
	}
	
	private void initComponents() {
		/*JFrame plantillaFrame = new JFrame("Gestión de Empleados");
        plantillaFrame.setSize(700, 500);
        plantillaFrame.setLocationRelativeTo(null);
        plantillaFrame.setLayout(new BorderLayout());*/
		//this.setSize(700, 500);
        //this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        JPanel panelSuperior =new JPanel(new BorderLayout());//panel de boton atras y logo
        JPanel panelCentral=new JPanel(new BorderLayout());
        JLabel tituloPlantilla = new JLabel("Gestión de Plantilla", SwingConstants.CENTER);
        tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
        panelCentral.add(tituloPlantilla, BorderLayout.NORTH);

        // Obtener la lista de empleados desde el controlador
        List<TransferEmpleado> empleados = controlador.listaEmpleados();
        
        // Crear la tabla con los empleados
        String[] columnas = {"ID", "Nombre", "Correo", "Rol"};
        Object[][] datosEmpleados = new Object[empleados.size()][4];

        for (int i = 0; i < empleados.size(); i++) {
            TransferEmpleado empleado = empleados.get(i);
            datosEmpleados[i][0] = empleado.getId();
            datosEmpleados[i][1] = empleado.getNombre();
            datosEmpleados[i][2] = empleado.getCorreo();
            datosEmpleados[i][3] = empleado.getRol();
        }

        JTable tabla = new JTable(datosEmpleados, columnas);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btonAnadir = new JButton("Añadir Empleado");
        //btonAnadir.setPreferredSize(botonDimension);
       // btonAnadir.setMinimumSize(botonDimension);
       // btonAnadir.setMaximumSize(botonDimension);
        btonAnadir.setBackground(new Color(50, 205, 50)); 
        btonAnadir.setForeground(Color.WHITE);
        btonAnadir.setFont(new Font("Arial",Font.BOLD,13));
        btonAnadir.addActionListener(ev -> {
            JFrame registroFrame = new JFrame("Registrar Empleado");
            registroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            registroFrame.setSize(400, 300);
            registroFrame.setLocationRelativeTo(null);

            // Crear un contenedor con CardLayout solo para este frame
            JPanel panelContenedor = new JPanel(new CardLayout());
            CardLayout cardLayout = (CardLayout) panelContenedor.getLayout();

            RegistroPanel registroPanel = new RegistroPanel(panelContenedor, cardLayout, controlador);
            panelContenedor.add(registroPanel, "registro");

            registroFrame.add(panelContenedor);
            registroFrame.setVisible(true);

            // Mostrar directamente el panel de registro
            cardLayout.show(panelContenedor, "registro");
        });

        JButton btnModificar = new JButton("Modificar Empleado");
        btnModificar.setBackground(new Color(0, 128, 0)); 
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial",Font.BOLD,13));
        btnModificar.addActionListener(ev -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                // Obtener datos del empleado seleccionado
                String id = (String) tabla.getValueAt(filaSeleccionada, 0);
                String nombre = (String) tabla.getValueAt(filaSeleccionada, 1);
                String correo = (String) tabla.getValueAt(filaSeleccionada, 2);
                String rol = (String) tabla.getValueAt(filaSeleccionada, 3);

                // Aquí necesitamos obtener también la contraseña
                // Opciones:
                // 1. Si tabla no la tiene, puedes buscarla desde el objeto original
                TransferEmpleado empleado = empleados.get(filaSeleccionada); // <- Usa esto
                String contraseña = empleado.getContraseña(); // <- si tienes getter

                // Crear frame para editar
                JFrame editarFrame = new JFrame("Modificar Empleado");
                editarFrame.setResizable(false);
                editarFrame.setSize(400, 350);
                editarFrame.setLocationRelativeTo(null);
                editarFrame.setLayout(new GridLayout(6, 2, 10, 10));

                JTextField campoNombre = new JTextField(nombre);
                JTextField campoCorreo = new JTextField(correo);
                JTextField campoRol = new JTextField(rol);
                JPasswordField campoContraseña = new JPasswordField(contraseña); // editable

                editarFrame.add(new JLabel("ID:"));
                editarFrame.add(new JLabel(String.valueOf(id)));

                editarFrame.add(new JLabel("Nombre:"));
                editarFrame.add(campoNombre);

                editarFrame.add(new JLabel("Correo:"));
                editarFrame.add(campoCorreo);

                editarFrame.add(new JLabel("Rol:"));
                editarFrame.add(campoRol);

                editarFrame.add(new JLabel("Contraseña:"));
                editarFrame.add(campoContraseña);

                JButton btnGuardar = new JButton("Guardar Cambios");
                btnGuardar.addActionListener(evGuardar -> {
                    String nuevaContraseña = new String(campoContraseña.getPassword());

                    TransferEmpleado modificado = new TransferEmpleado(
                        id,
                        campoNombre.getText(),
                        campoCorreo.getText(),
                        campoRol.getText(),
                        nuevaContraseña
                    );

                    controlador.modificarEmpleado(modificado);

                    editarFrame.dispose();
                   // plantillaFrame.dispose();
                    JOptionPane.showMessageDialog(frame, "Empleado modificado correctamente.");
                    frame.setVisible(true);
                });

                editarFrame.add(new JLabel()); // Espacio
                editarFrame.add(btnGuardar);

                editarFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un empleado primero.");
            }
        });


        JButton btnEliminar = new JButton("Eliminar Empleado");
        btnEliminar.setBackground(new Color(255, 69, 58));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial",Font.BOLD,13));
        btnEliminar.addActionListener(ev -> {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada != -1) {
                String idEmpleado = (String) tabla.getValueAt(filaSeleccionada, 0);

                TransferEmpleado empleadoAEliminar = null;
                for (TransferEmpleado emp : empleados) {
                    if (emp.getId().equals(idEmpleado)) {
                        empleadoAEliminar = emp;
                        break;
                    }
                }

                if (empleadoAEliminar != null) {
                    int confirmacion = JOptionPane.showConfirmDialog(
                        null,
                        "¿Estás seguro de que deseas eliminar a " + empleadoAEliminar.getNombre() + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        boolean eliminado = controlador.eliminarEmpleado(empleadoAEliminar);
                        if (eliminado) {
                            JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
                           //crearFrame.dispose();
                   		SwingUtilities.getWindowAncestor(this).dispose();
                   		
                		GUIGestor.resetInstancia();
                		//GUIGestor.getInstancia(controlador,null);
                            new GUIGestorImp(controlador, datos);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado.");
                        }
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecciona un empleado primero.");
            }
        });


        ImageIcon volverIcono = new ImageIcon("resources/botonAtras.png");
        Image volverIconoImagen = volverIcono.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        JButton btnVolver = new JButton("Volver",new ImageIcon(volverIconoImagen));
        btnVolver.setFont(new Font("Arial",Font.BOLD,10));
        btnVolver.setForeground(Color.white);
        btnVolver.setPreferredSize(new Dimension(110,20));
        btnVolver.setContentAreaFilled(false); // elimina el fondo estirado
        btnVolver.setBorderPainted(false);    // elimina el borde
        btnVolver.setFocusPainted(false);     // quita ese borde de foco azul
        btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
        btnVolver.setVerticalAlignment(SwingConstants.CENTER);
        btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolver.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnVolver.addActionListener(ev -> {
           // plantillaFrame.dispose(); // Cerrar ventana de plantilla
           // frame.setVisible(true);  // Volver a mostrar el panel gestor
        	cardLayout.show(panelContenedor, "menu");
        });
        ImageIcon logo = new ImageIcon("resources/logo.png"); 
        Image locoImagenEscalado = logo.getImage().getScaledInstance(63,63, Image.SCALE_SMOOTH);
        ImageIcon logoIconoEscalado=new ImageIcon(locoImagenEscalado);
        JLabel etiquetaImagen = new JLabel(logoIconoEscalado);
        
        panelSuperior.add(btnVolver, BorderLayout.LINE_START);
        panelSuperior.setBackground(new Color(100, 180, 255));
        panelSuperior.add(etiquetaImagen,BorderLayout.LINE_END);
        panelBotones.add(btonAnadir);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        
        panelCentral.add(panelBotones, BorderLayout.SOUTH);
        this.add(panelSuperior,BorderLayout.PAGE_START);
        this.add(panelCentral);
        this.setVisible(true);
	}
}
