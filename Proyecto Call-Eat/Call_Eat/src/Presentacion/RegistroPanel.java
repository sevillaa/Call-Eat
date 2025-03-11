package Presentacion;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class RegistroPanel extends JPanel {

    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JPasswordField txtContraseña;
    private JPanel panelContenedor;
    private CardLayout cardLayout;
    private Controlador controlador; // Se usará para invocar la lógica de registro

    public RegistroPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
        this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;
        this.controlador = controlador;
        initComponents();
    }
    
    private void initComponents() {
        // Configuramos un GridLayout con 4 filas y 2 columnas
        setLayout(new GridLayout(4, 2, 10, 10));
        
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();
        
        JLabel lblContraseña = new JLabel("Contraseña:");
        txtContraseña = new JPasswordField();
        
        JButton btnRegistrar = new JButton("Registrarse");
        JButton btnVolver = new JButton("Volver");
        
        // Acción del botón "Registrarse"
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String correo = txtCorreo.getText();
                String contraseña = new String(txtContraseña.getPassword());
                
                // No se genera el ID aquí. Se delega su asignación en el controlador o en la capa de negocio.
                HashMap<String, String> datos = new HashMap<>();
                datos.put("nombre", nombre);
                datos.put("correo", correo);
                datos.put("contraseña", contraseña);
                
                // Invoca al controlador para registrar al usuario
                controlador.accion(Eventos.ADD_CLIENTE, datos);
                
                // Se muestra un mensaje de confirmación (sin mostrar el ID asignado)
                JOptionPane.showMessageDialog(RegistroPanel.this, 
                    "Intento de registro para: " + nombre);
            }
        });
        
        // Acción del botón "Volver": regresa al panel de inicio
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "inicio");
            }
        });
        
        add(lblNombre);
        add(txtNombre);
        add(lblCorreo);
        add(txtCorreo);
        add(lblContraseña);
        add(txtContraseña);
        add(btnVolver);
        add(btnRegistrar);
    }
}



