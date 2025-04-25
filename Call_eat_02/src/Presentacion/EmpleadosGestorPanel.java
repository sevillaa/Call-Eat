package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Negocio.TransferEmpleado;

public class EmpleadosGestorPanel extends JPanel{

	private JPanel panelContenedor;
	private CardLayout cardLayout;
	private Controlador controlador;
	private JFrame frame;
	private Object datos;
	private JTable tabla;
	private List<TransferEmpleado> empleados;

	public EmpleadosGestorPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador, Object datos) {
		this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;
        this.controlador = controlador;
        this.datos=datos;
        initComponents();
	}
	
	private void cargarEmpleados() {
	    empleados = controlador.listaEmpleados();

	    String[] columnas = {"ID", "Nombre", "Correo", "Rol"};
	    Object[][] datosEmpleados = new Object[empleados.size()][4];

	    for (int i = 0; i < empleados.size(); i++) {
	        TransferEmpleado empleado = empleados.get(i);
	        datosEmpleados[i][0] = empleado.getId();
	        datosEmpleados[i][1] = empleado.getNombre();
	        datosEmpleados[i][2] = empleado.getCorreo();
	        datosEmpleados[i][3] = empleado.getRol();
	    }

	    tabla.setModel(new javax.swing.table.DefaultTableModel(datosEmpleados, columnas));
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
       

        tabla = new JTable();
        cargarEmpleados();
        
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
            
            registroFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    cargarEmpleados();
                }
            });
            
        });

        JButton btnModificar = new JButton("Modificar Empleado");
        btnModificar.setBackground(new Color(0, 128, 0)); 
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial",Font.BOLD,13));
        btnModificar.addActionListener(ev -> {
        	
        	JFrame framePrincipal=new JFrame("Modificar Empleado");
        	framePrincipal.setSize(400, 350);
        	framePrincipal.setLayout(new BorderLayout());
        	framePrincipal.setLocationRelativeTo(null);
        	 framePrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JPanel panelCentro=new JPanel();
            panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
            JPanel panelCentro1=new JPanel();
            JPanel panelCentro2=new JPanel();
            JPanel panelCentro3=new JPanel();
            JPanel panelCentro4=new JPanel();
            JPanel panelCentro5=new JPanel();
            JPanel panelInferior=new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel panelArriba=new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelInferior.setBackground(new Color(173, 216, 230));
            panelArriba.setBackground(Color.WHITE);
            framePrincipal.setBackground(Color.white);
            panelCentro1.setBackground(Color.white);
            panelCentro2.setBackground(Color.white);
            panelCentro3.setBackground(Color.white);
            panelCentro4.setBackground(Color.white);
            panelCentro5.setBackground(Color.WHITE);
            panelCentro.setBackground(Color.white);
            JLabel tituloPrincipal = new JLabel("Modificar usuario", SwingConstants.CENTER);
            tituloPrincipal.setFont(new Font("Arial", Font.BOLD, 20));
            //panelPrincipal.add(tituloPrincipal,BorderLayout.NORTH);
            
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
                
                
                Dimension d = new Dimension(200,25);
                
                JLabel lblID = new JLabel("ID:       ");
                JLabel lblIDvalor = new JLabel(String.valueOf(id));
                
                JLabel lblNombre = new JLabel("Nombre:      ");
                JTextField campoNombre = new JTextField(nombre);
                campoNombre.setMinimumSize(d);
                campoNombre.setPreferredSize(d);
                campoNombre.setMaximumSize(d);
                              
                JLabel lblCorreo = new JLabel("Correo:       ");
                JTextField campoCorreo = new JTextField(correo);
                campoCorreo.setMinimumSize(d);
                campoCorreo.setPreferredSize(d);
                campoCorreo.setMaximumSize(d);
                               
                JLabel lblRol = new JLabel("Rol:              ");
                JComboBox<String> campoRol =  new JComboBox<>(new String[] {"Camarero", "Cocinero", "Gerente"});
                campoRol.setMinimumSize(d);
                campoRol.setPreferredSize(d);
                campoRol.setMaximumSize(d);
                
                JLabel lblContrasena = new JLabel("Contraseña: ");
                JPasswordField campoContraseña = new JPasswordField(contraseña); // editable
                campoContraseña.setMinimumSize(d);
                campoContraseña.setPreferredSize(d);
                campoContraseña.setMaximumSize(d);
                
                JButton btnCancelar = new JButton("Cancelar");
                btnCancelar.setBackground(Color.gray); 
                btnCancelar.setFont(new Font("Arial",Font.BOLD,15));
                btnCancelar.setForeground(Color.WHITE);
                btnCancelar.addActionListener(e->{
                	framePrincipal.dispose();                	
                });
                JButton btnGuardar = new JButton("Guardar Cambios");
                btnGuardar.setBackground(new Color(0, 128, 0)); 
                btnGuardar.setFont(new Font("Arial",Font.BOLD,15));
                btnGuardar.setForeground(Color.WHITE);
                btnGuardar.addActionListener(evGuardar -> {
                    String nuevaContraseña = new String(campoContraseña.getPassword());

                    TransferEmpleado modificado = new TransferEmpleado(
                        id,
                        campoNombre.getText(),
                        campoCorreo.getText(), 
                        nuevaContraseña,
                        (String) campoRol.getSelectedItem()
                    );

                    controlador.modificarEmpleado(modificado);

                    framePrincipal.dispose();
                   // plantillaFrame.dispose();
                    JOptionPane.showMessageDialog(frame, "Empleado modificado correctamente.");
                    
                    cargarEmpleados();
                
                });
                framePrincipal.setVisible(true);
                panelArriba.add(tituloPrincipal);
                panelInferior.add(btnCancelar);
                panelInferior.add(btnGuardar);
         		panelCentro1.add(lblID);
         		panelCentro1.add(lblIDvalor);
         		panelCentro2.add(lblNombre);
         		panelCentro2.add(campoNombre);
         		panelCentro3.add(lblCorreo);
         		panelCentro3.add(campoCorreo);
         		panelCentro4.add(lblRol);
         		panelCentro4.add(campoRol);
         		panelCentro5.add(lblContrasena);
         		panelCentro5.add(campoContraseña);
         		panelCentro.add(Box.createVerticalGlue());
         		panelCentro.add(panelCentro1);
         		panelCentro.add(panelCentro2);
         		panelCentro.add(panelCentro3);
         		panelCentro.add(panelCentro4);
         		panelCentro.add(panelCentro5);
         		panelCentro.add(Box.createVerticalGlue());
         		framePrincipal.add(panelArriba,BorderLayout.NORTH);
         		framePrincipal.add(panelCentro,BorderLayout.CENTER);
         		framePrincipal.add(panelInferior,BorderLayout.SOUTH);
            }
            else {
            	JOptionPane.showMessageDialog(null, "Selecciona un empleado primero.");
            	
            }
            //panelInferior.add(btnCancelar);
     		
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
                           
                            cargarEmpleados();
                            //JOptionPane.getFrameForComponent(btnEliminar).dispose(); 
                            
                            
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
        panelBotones.setBackground(new Color(173, 216, 230));
        panelBotones.add(btonAnadir);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        
        panelCentral.add(panelBotones, BorderLayout.SOUTH);
        this.add(panelSuperior,BorderLayout.PAGE_START);
        this.add(panelCentral);
        this.setVisible(true);
	}
}