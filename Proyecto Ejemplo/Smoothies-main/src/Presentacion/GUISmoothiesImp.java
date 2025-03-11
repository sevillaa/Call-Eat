package Presentacion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class GUISmoothiesImp extends GUISmoothies {
	
	private JFrame loginFrame;
	private JButton accesoButton;
	private JButton registroButton;
	private JButton atrasButton; // Botón de "Atrás" para acceso y registro
	private JPanel currentPanel; // Para realizar un seguimiento del panel actual
	private JPanel panel; // Variable de instancia para el panel principal
	private Controlador cntr; // Agregar referencia al controlador

	public GUISmoothiesImp(Controlador controlador) {
		this.cntr = controlador;

        // Crear la ventana principal
        loginFrame = new JFrame("SMOOTHIES");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);
        loginFrame.setLocationRelativeTo(null); // Centrar en la pantalla

        // Crear un panel para contener los componentes
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        loginFrame.getContentPane().add(panel);

        // Cargar la imagen
        ImageIcon imageIcon = new ImageIcon("archivos/smoothie.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        panel.add(imageLabel, BorderLayout.CENTER); // Añadir la imagen al centro del panel

        // Crear un panel para los botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        // Crear los botones de Acceso y Registro
        accesoButton = new JButton("Acceso");
        accesoButton.addActionListener(e -> mostrarPanelAcceso());///
        buttonPanel.add(accesoButton);

        registroButton = new JButton("Registro");
        registroButton.addActionListener(e -> mostrarPanelRegistro());///
        buttonPanel.add(registroButton);

        // Agregar el panel de botones al panel principal
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Hacer visible el loginFrame
        loginFrame.setVisible(true);

        // Inicializar el panel actual con el panel principal
        currentPanel = panel;
   
	}

    private void mostrarPanelRegistro() {
        // Ocultar el panel actual
        currentPanel.setVisible(false);

        // Mostrar el nuevo panel para el registro
        JPanel registroPanel = new JPanel(new GridLayout(5, 2));
        JTextField nombreField = new JTextField(20);
        JTextField correoField = new JTextField(20);
        JPasswordField contraseñaField = new JPasswordField(20);
        JButton registrarButton = new JButton("Registrar");
        atrasButton = new JButton("Atrás"); // Añadir botón "Atrás" al panel de registro

        registroPanel.add(new JLabel("Nombre:"));
        registroPanel.add(nombreField);
        registroPanel.add(new JLabel("Correo:"));
        registroPanel.add(correoField);
        registroPanel.add(new JLabel("Contraseña:"));
        registroPanel.add(contraseñaField);
        registroPanel.add(new JLabel());
        registroPanel.add(registrarButton);
        registroPanel.add(new JLabel());
        registroPanel.add(atrasButton); // Agregar botón "Atrás" al panel de registro

        loginFrame.getContentPane().add(registroPanel, BorderLayout.SOUTH);
        loginFrame.revalidate();
        loginFrame.repaint();

        // Agregar oyente de evento al botón de Registrar
        registrarButton.addActionListener(e -> {
            // Lógica para el botón de Registrar
            String nombre = nombreField.getText();
            String correo = correoField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            // Generar un id de usuario aleatorio de 6 cifras
            int idUsuario = (int) (Math.random() * 900000) + 100000;

            // Utilizar el controlador para añadir un cliente
            HashMap<String, String> datos = new HashMap<>();
            datos.put("nombre", nombre);
            datos.put("correo", correo);
            datos.put("contraseña", contraseña);
            datos.put("idUsuario", String.valueOf(idUsuario));
            cntr.accion(Eventos.AÑADIR_CLIENTE, datos);
            cntr.accion(Eventos.INICIAR_SESION, datos) ; 
          
            
            
        });

        // Agregar oyente de evento al botón de Atrás en el panel de registro
        atrasButton.addActionListener(e -> mostrarPanelAnterior());
        // Actualizar el panel actual
        currentPanel = registroPanel;
    }

    private void mostrarPanelAnterior() {
        // Ocultar el panel actual
        currentPanel.setVisible(false);

        // Mostrar el panel principal con los botones de acceso y registro
        currentPanel = panel;
        currentPanel.setVisible(true);
    }

    private void mostrarPanelAcceso() {
        // Ocultar el panel actual
        currentPanel.setVisible(false);

        // Mostrar el nuevo panel para el acceso
        JPanel accesoPanel = new JPanel(new GridLayout(4, 2));
        JTextField correoField = new JTextField(20);
        JPasswordField contraseñaField = new JPasswordField(20);
        JButton accederButton = new JButton("Acceder");
        atrasButton = new JButton("Atrás"); // Añadir botón "Atrás" al panel de acceso

        accesoPanel.add(new JLabel("Correo:"));
        accesoPanel.add(correoField);
        accesoPanel.add(new JLabel("Contraseña:"));
        accesoPanel.add(contraseñaField);
        accesoPanel.add(new JLabel());
        accesoPanel.add(accederButton);
        accesoPanel.add(new JLabel());
        accesoPanel.add(atrasButton); // Agregar botón "Atrás" al panel de acceso

        loginFrame.getContentPane().add(accesoPanel, BorderLayout.SOUTH);
        loginFrame.revalidate();
        loginFrame.repaint();

     // Dentro del método accion(Eventos.INICIAR_SESION, datos)
        accederButton.addActionListener(e -> {
            // Lógica para el botón de Acceder
            String correo = correoField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            //AQUI SE IMPLEMENTA GUARDAR EN LA BASE DE DATOS
            // Utilizar el controlador para iniciar sesión
            HashMap<String, String> datos = new HashMap<>();
            datos.put("correo", correo);
            datos.put("contraseña", contraseña);
            if(correo.equals("administrador")  && contraseña.equals("secreta")) {
            	cntr.accion(Eventos.INICIAR_ADMINISTRADOR, datos);
            }else {
            	cntr.accion(Eventos.INICIAR_SESION, datos);
            }
            
            
        });


        // Agregar oyente de evento al botón de Atrás en el panel de acceso
        atrasButton.addActionListener(e -> mostrarPanelAnterior());
        // Actualizar el panel actual
        currentPanel = accesoPanel;
    }

    @Override
    public void actualizar(int evento, Object datos) {
        switch (evento) {
        case (Eventos.AÑADIR_CLIENTE): {
        	GUIClientesImp guiClientes = new GUIClientesImp(cntr,datos);
            break;
        }
        case (Eventos.CLIENTE_REGISTRADO):{
        	//GUIAdministradorImp guiAdmin = new GUIAdministradorImp(cntr);     	
        	GUIClientesImp guiClientes = new GUIClientesImp(cntr,datos);
           break;          
        }
        case (Eventos.INICIAR_ADMINISTRADOR):{
        	GUIAdministradorImp guiAdministrador = new GUIAdministradorImp(cntr);
        }
    }

    }
}

