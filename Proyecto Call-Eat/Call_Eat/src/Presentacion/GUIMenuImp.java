package Presentacion;

import javax.swing.*;
import java.awt.*;
import Negocio.TransferCliente; // Asegúrate de que este import existe

public class GUIMenuImp extends GUIMenu {

    private JFrame frame;
    private Controlador controlador;
    private Object usuario;

    public GUIMenuImp(Controlador controlador, Object datos) {
        this.controlador = controlador;
        this.usuario = datos;
        initGUI();
    }

    private void initGUI() {
        // Configuración de la ventana principal
        frame = new JFrame("Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Determinar el nombre a mostrar en el mensaje de bienvenida
        String nombreUsuario;
        if (usuario instanceof TransferCliente) {
            nombreUsuario = ((TransferCliente) usuario).getNombre();
        } else {
            nombreUsuario = usuario.toString();
        }
        JLabel bienvenida = new JLabel("Bienvenido, " + nombreUsuario, SwingConstants.CENTER);
        frame.add(bienvenida, BorderLayout.NORTH);

        // Panel principal con tres secciones
        JPanel panelPrincipal = new JPanel(new GridLayout(1, 3));

        // Sección izquierda: Panel Gestor
        JPanel panelGestor = new JPanel();
        JButton botonGestor = new JButton("Panel Gestor");
        botonGestor.addActionListener(e -> {
            // Aquí se debería abrir GUIGestorImp. Por ejemplo:
            // new GUIGestorImp(controlador).setVisible(true);
            // frame.dispose();
            JOptionPane.showMessageDialog(frame, "Acción para Panel Gestor (a implementar)");
        });
        panelGestor.add(botonGestor);

        // Sección central: Panel Camarero
        JPanel panelCamarero = new JPanel();
        JButton botonCamarero = new JButton("Panel Camarero");
        botonCamarero.addActionListener(e -> {
            // Aquí se debería abrir GUICamareroImp. Por ejemplo:
            // new GUICamareroImp(controlador).setVisible(true);
            // frame.dispose();
            JOptionPane.showMessageDialog(frame, "Acción para Panel Camarero (a implementar)");
        });
        panelCamarero.add(botonCamarero);

        // Sección derecha: Panel Cocina
        JPanel panelCocina = new JPanel();
        JButton botonCocina = new JButton("Panel Cocina");
        botonCocina.addActionListener(e -> {
            // Aquí se debería abrir GUICocinaImp. Por ejemplo:
            // new GUICocinaImp(controlador).setVisible(true);
            // frame.dispose();
            JOptionPane.showMessageDialog(frame, "Acción para Panel Cocina (a implementar)");
        });
        panelCocina.add(botonCocina);

        // Agregar los paneles al panel principal
        panelPrincipal.add(panelGestor);
        panelPrincipal.add(panelCamarero);
        panelPrincipal.add(panelCocina);
        frame.add(panelPrincipal, BorderLayout.CENTER);

        // Botón de cerrar sesión
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.addActionListener(e -> {
            frame.dispose();
            GUIMenu.resetInstancia();  // Método que reinicia la instancia del menú
            GUICliente.getInstancia(controlador, null);
        });
        frame.add(cerrarSesionButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Implementa actualizaciones dinámicas en la interfaz si fuera necesario
        JOptionPane.showMessageDialog(frame, "Evento recibido: " + evento);
    }
}


