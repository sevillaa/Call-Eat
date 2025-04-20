package Presentacion;

import javax.swing.*;
import java.awt.*;

public class GUIEmpleadoImp extends GUIEmpleado {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private Controlador controlador;

    public GUIEmpleadoImp(Controlador controlador, Object datos) {
        this.controlador = controlador;
        initGUI(controlador);
    }

    private void initGUI(Controlador controlador) {
        frame = new JFrame("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        InicioPanel inicioPanel = new InicioPanel(panelContenedor, cardLayout);
        LoginPanel loginPanel = new LoginPanel(panelContenedor, cardLayout, controlador);
        RegistroPanel registroPanel = new RegistroPanel(panelContenedor, cardLayout, controlador);

        panelContenedor.add(inicioPanel, "inicio");
        panelContenedor.add(loginPanel, "login");
        panelContenedor.add(registroPanel, "registro");

        frame.add(panelContenedor);
        frame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        if (evento == Eventos.CLIENTE_REGISTRADO || evento == Eventos.INICIAR_SESION) {
            JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso");
            frame.dispose();
            // Delegamos al controlador para que abra el menú principal
            controlador.accion(Eventos.MOSTRAR_MENU_PRINCIPAL, datos);
        } else {
            cardLayout.show(panelContenedor, "inicio");
        }
    }
}


