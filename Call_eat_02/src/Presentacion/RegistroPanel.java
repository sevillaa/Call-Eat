package Presentacion;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class RegistroPanel extends JPanel {

    private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
    private JTextField txtCorreo;
    private JPasswordField txtContraseña;
    private JComboBox<String> cmbRol;  // Añadimos el JComboBox para seleccionar el rol
    private JPanel panelContenedor;
    private CardLayout cardLayout;
    private Controlador controlador;

    public RegistroPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
        this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;
        this.controlador = controlador;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new GridLayout(5, 2, 10, 10));  // Cambiamos a 5 filas para incluir el rol
        
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();
        
        JLabel lblContraseña = new JLabel("Contraseña:");
        txtContraseña = new JPasswordField();
        
        JLabel lblRol = new JLabel("Rol:");  // Etiqueta para el rol
        cmbRol = new JComboBox<>(new String[] {"Camarero", "Cocinero", "Gerente"});  // ComboBox con las opciones de rol
        
        JButton btnRegistrar = new JButton("Registrarse");
        JButton btnVolver = new JButton("Volver");
        
        // Acción del botón "Registrarse"
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String contraseña = new String(txtContraseña.getPassword());
                String rol = (String) cmbRol.getSelectedItem();  // Obtenemos el rol seleccionado
                
                HashMap<String, String> datos = new HashMap<>();
                datos.put("nombre", nombre);
                datos.put("correo", correo);
                datos.put("contraseña", contraseña);
                datos.put("rol", rol);  // Añadimos el rol al HashMap
                
                // Llamamos al controlador para registrar al usuario
                controlador.accion(Eventos.ADD_CLIENTE, datos);
                
                // Mostramos un mensaje de confirmación
                JOptionPane.showMessageDialog(RegistroPanel.this, 
                    "Intento de registro para: " + nombre + " con rol: " + rol);
                       JOptionPane.getFrameForComponent(btnVolver).dispose();         
            }
        });
        
        
        
        
        // Acción del botón "Volver": regresa al panel de inicio
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.getFrameForComponent(btnVolver).dispose();  
                
            	
            }
        });
        
        // Añadimos los componentes al panel
        add(lblNombre);
        add(txtNombre);
        add(lblCorreo);
        add(txtCorreo);
        add(lblContraseña);
        add(txtContraseña);
        add(lblRol);  // Añadimos la etiqueta de rol
        add(cmbRol);  // Añadimos el JComboBox de rol
        add(btnVolver);
        add(btnRegistrar);
    }
    
    
}