package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
        setLayout(new BorderLayout());
        JPanel panelSuperior=new JPanel(new BorderLayout());
        JPanel panelCentral=new JPanel();
        JPanel panelInferior=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentral.setLayout(new BoxLayout(panelCentral,BoxLayout.Y_AXIS));
        
        JLabel tituloLabel = new JLabel("         Call&Eat", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 25));
        tituloLabel.setForeground(Color.WHITE);
        
        Dimension d = new Dimension(200,25);
        
        JLabel titulo =new JLabel("Iniciar Sesión",SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD,15));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelCorreo =new JPanel();
        panelCorreo.setLayout(new BoxLayout(panelCorreo,BoxLayout.X_AXIS));
        JPanel panelContrasena =new JPanel();
        panelContrasena.setLayout(new BoxLayout(panelContrasena,BoxLayout.X_AXIS));
        
        JLabel lblCorreo = new JLabel("Correo: ");
        txtCorreo = new JTextField();
        txtCorreo.setMinimumSize(d);
        txtCorreo.setPreferredSize(d);
        txtCorreo.setMaximumSize(d);
        JLabel lblContraseña = new JLabel("Contraseña: ");
        txtContraseña = new JPasswordField();
        txtContraseña.setMinimumSize(d);
        txtContraseña.setPreferredSize(d);
        txtContraseña.setMaximumSize(d);
        JButton btnIniciar = new JButton("Iniciar Sesión");
        btnIniciar.setBackground(new Color(100, 180, 255));
        btnIniciar.setFont(new Font("Arial",Font.BOLD,15));
        btnIniciar.setForeground(Color.WHITE);
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
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBackground(Color.GRAY);
        btnVolver.setFont(new Font("Arial",Font.BOLD,15));
        btnVolver.setForeground(Color.WHITE);
        // Acción del botón "Volver": regresa al panel de inicio
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "inicio");
            }
        });
        ImageIcon logoIcon = new ImageIcon("resources/logo.png");
        Image imagenLogoEscalada = logoIcon.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
        JLabel logoLabel=new JLabel(new ImageIcon(imagenLogoEscalada));
        panelSuperior.setBackground(new Color(100, 180, 255));
        panelSuperior.add(logoLabel,BorderLayout.LINE_END);
        panelSuperior.add(tituloLabel,BorderLayout.CENTER);
        // Agregamos los componentes
       // panelCorreo.setAlignmentY(BOTTOM_ALIGNMENT);
        //panelContrasena.setAlignmentY(TOP_ALIGNMENT);
        panelInferior.add(btnVolver);
        panelInferior.add(btnIniciar);
        panelCorreo.add(lblCorreo);
        panelCorreo.add(Box.createRigidArea(new Dimension(25,0)));
        panelCorreo.add(txtCorreo);
        lblCorreo.setAlignmentY(BOTTOM_ALIGNMENT);
        txtCorreo.setAlignmentY(BOTTOM_ALIGNMENT);
        panelContrasena.add(lblContraseña);
        panelContrasena.add(txtContraseña);
        panelCentral.add(Box.createRigidArea(new Dimension(0,10)));
        panelCentral.add(titulo);
        panelCentral.add(Box.createRigidArea(new Dimension(0,10)));
        panelCentral.add(panelCorreo);
        panelCentral.add(Box.createRigidArea(new Dimension(0,5)));
        panelCentral.add(panelContrasena);
       panelCentral.add(panelInferior);
       //panelCentral.add(btnIniciar);
        add(panelSuperior,BorderLayout.NORTH);
        add(panelCentral,BorderLayout.CENTER);
        //add(panelInferior,BorderLayout.SOUTH);
        
    }
}
