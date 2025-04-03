package Presentacion;

import javax.swing.*;
import java.awt.*;

public class GUIUsuariosImp extends GUIUsuarios {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private Controlador controlador; // Guardamos el controlador

<<<<<<< HEAD:Proyecto Call-Eat/Call_Eat/src/Presentacion/GUIClienteImp.java
    public GUIClienteImp(Controlador controlador, Object datos) {
        this.controlador = controlador;
=======
    public GUIUsuariosImp(Controlador controlador, Object datos) {
>>>>>>> 0bd3ff1f93bcda9471a762bc8163217f13b25340:Proyecto Call-Eat/Call_Eat/src/Presentacion/GUIUsuariosImp.java
        initGUI(controlador);
    }

    private void initGUI(Controlador controlador) {
        // Configuración de la ventana principal
        frame = new JFrame("Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Tamaño fijo para mantener uniformidad
        frame.setLocationRelativeTo(null);

        // Panel contenedor con CardLayout para gestionar las diferentes vistas
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Se asume que InicioPanel, LoginPanel y RegistroPanel están implementados
        InicioPanel inicioPanel = new InicioPanel(panelContenedor, cardLayout);
        LoginPanel loginPanel = new LoginPanel(panelContenedor, cardLayout, controlador);
        //RegistroPanel registroPanel = new RegistroPanel(panelContenedor, cardLayout, controlador);

        // Agregar paneles al contenedor con un identificador único para cada uno
        panelContenedor.add(inicioPanel, "inicio");
        panelContenedor.add(loginPanel, "login");
        //panelContenedor.add(registroPanel, "registro");

        // Añadir el panel contenedor a la ventana
        frame.add(panelContenedor);
        frame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Al iniciar sesión o registrarse, se cierra la ventana actual y se abre GUIMenuImp
        if (evento == Eventos.CLIENTE_REGISTRADO || evento == Eventos.INICIAR_SESION) {
            JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso");
            frame.dispose();
            // Se reinicia la instancia del menú mediante un método público en GUIMenu
            GUIMenu.resetInstancia();
            // Se crea o se recupera la instancia del menú, pasando el controlador y los datos del usuario
            GUIMenu.getInstancia(controlador, datos);
        } else {
            // Puedes incluir otros eventos aquí
            cardLayout.show(panelContenedor, "inicio");
        }
    }
}



