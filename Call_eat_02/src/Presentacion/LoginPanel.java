package Presentacion;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*;

public class LoginPanel extends JPanel {

    private static final long serialVersionUID = 1L;
	private JTextField txtCorreo;
    private JPasswordField txtContraseña;
    private JPanel panelContenedor;
    private CardLayout cardLayout;
    private Controlador controlador; // Si necesitas interactuar con el controlador

    public LoginPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
        this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;
        this.controlador = controlador;
        initComponents();
    }
    
    private void initComponents() {
        // Usamos un GridLayout para organizar los componentes
        setLayout(new GridLayout(3, 2, 10, 10));
        
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField();
        
        JLabel lblContraseña = new JLabel("Contraseña:");
        txtContraseña = new JPasswordField();
        
        JButton btnIniciar = new JButton("Iniciar Sesión");
        JButton btnVolver = new JButton("Volver");
        
        // Acción del botón "Iniciar Sesión"
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recoger datos del formulario
                String correo = txtCorreo.getText();
                String contraseña = new String(txtContraseña.getPassword());
                HashMap<String, String> datos = new HashMap<>();
                datos.put("correo", correo);
                datos.put("contraseña", contraseña);
                
                // Llamada al controlador (descomenta la línea si tienes implementado el controlador)
                 controlador.accion(Eventos.INICIAR_SESION, datos);
                
                // Para este ejemplo, mostramos un mensaje:
                //JOptionPane.showMessageDialog(LoginPanel.this, "Intento de login para: " + correo);
            }
        });
        
        // Acción del botón "Volver": regresa al panel de inicio
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "inicio");
            }
        });
        
        // Agregamos los componentes
        add(lblCorreo);
        add(txtCorreo);
        add(lblContraseña);
        add(txtContraseña);
        add(btnVolver);
        add(btnIniciar);
    }
}
