package Presentacion;

import javax.swing.*;
import java.awt.*;

public class GUIClienteImp extends GUICliente {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel panelContenedor;

    public GUIClienteImp(Controlador controlador, Object datos) {
        initGUI(controlador);
    }

    private void initGUI(Controlador controlador) {
        // Configuración de la ventana principal
        frame = new JFrame("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Tamaño fijo para mantener uniformidad
        frame.setLocationRelativeTo(null);

        // Panel contenedor con CardLayout para gestionar las diferentes vistas
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Crear los paneles individuales
        // Se asume que InicioPanel, LoginPanel y RegistroPanel están implementados para mostrar sus formularios
        InicioPanel inicioPanel = new InicioPanel(panelContenedor, cardLayout);
        LoginPanel loginPanel = new LoginPanel(panelContenedor, cardLayout, controlador);
        RegistroPanel registroPanel = new RegistroPanel(panelContenedor, cardLayout, controlador);

        // Agregar paneles al contenedor con un identificador único para cada uno
        panelContenedor.add(inicioPanel, "inicio");
        panelContenedor.add(loginPanel, "login");
        panelContenedor.add(registroPanel, "registro");

        // Añadir el panel contenedor a la ventana
        frame.add(panelContenedor);
        frame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Se actualiza la interfaz en función del evento recibido
        if (evento == Eventos.CLIENTE_REGISTRADO) {
            JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso");
            // Volvemos al panel de inicio tras un registro exitoso
            cardLayout.show(panelContenedor, "inicio");
        } else if (evento == Eventos.INICIAR_SESION) {
            JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso.");
            // Aquí puedes, por ejemplo, cambiar a otra vista (como un panel de pedidos o dashboard)
            // cardLayout.show(panelContenedor, "otroPanel");
        }
    }
}


