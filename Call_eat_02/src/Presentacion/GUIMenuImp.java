package Presentacion;

import javax.swing.*;
import java.awt.*;
import Negocio.TransferEmpleado; // Asegúrate de que este import existe

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
        JPanel panelBotonesArriba=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelBotonesAbajo=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelSuperior=new JPanel(new BorderLayout());
        JPanel panelPrincipal=new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
        // Determinar el nombre a mostrar en el mensaje de bienvenida
        String nombreUsuario;
        if (usuario instanceof TransferEmpleado) {
            nombreUsuario = ((TransferEmpleado) usuario).getNombre();
        } else {
            nombreUsuario = usuario.toString();
        }
        JLabel bienvenida = new JLabel("Bienvenido, " + nombreUsuario, SwingConstants.CENTER);
        frame.add(bienvenida, BorderLayout.NORTH);

        // Panel principal con cuatro secciones
        //JPanel panelPrincipal = new JPanel(new GridLayout(1, 4)); // Cambié el layout para 4 secciones

        // Sección izquierda: Panel Gestor
        //JPanel panelGestor = new JPanel();
        JButton botonGestor = new JButton("Panel Gestor");
        botonGestor.addActionListener(e -> {
            frame.dispose();
            new GUIGestorImp(controlador, usuario);
        });
        panelBotonesArriba.add(botonGestor);

        // Sección central: Panel Camarero
        //JPanel panelCamarero = new JPanel();
        JButton botonCamarero = new JButton("Panel Camarero");
        botonCamarero.addActionListener(e -> {
            frame.dispose();
            new GUICamareroImp(controlador, usuario);
        });
        panelBotonesArriba.add(botonCamarero);

        // Sección derecha: Panel Cocina
        //JPanel panelCocina = new JPanel();
        JButton botonCocina = new JButton("Panel Cocina");
        botonCocina.addActionListener(e -> {
            frame.dispose();
            new GUICocinaImp(controlador, usuario);
            JOptionPane.showMessageDialog(frame, "Acción para Panel Cocina (a implementar)");
        });
        panelBotonesAbajo.add(botonCocina);

        // Nueva sección: Panel Mesas
       // JPanel panelMesas = new JPanel();
        JButton botonMesas = new JButton("Ver Mesas");
        botonMesas.addActionListener(e -> {
            // Aquí se debería abrir GUIMesasImp
            frame.dispose();
            new GUIMesaImp(controlador, usuario);  // Esto abrirá el panel de las mesas
        });
        panelBotonesAbajo.add(botonMesas);

        // Agregar los paneles al panel principal
        //panelSuperior.add(btnVolver, BorderLayout.LINE_START);
        ImageIcon logo = new ImageIcon("resources/logo.png"); 
        Image locoImagenEscalado = logo.getImage().getScaledInstance(63,63, Image.SCALE_SMOOTH);
        ImageIcon logoIconoEscalado=new ImageIcon(locoImagenEscalado);
        JLabel etiquetaImagen = new JLabel(logoIconoEscalado);
        
        panelSuperior.setBackground(new Color(100, 180, 255));
        panelSuperior.add(etiquetaImagen,BorderLayout.LINE_END);
        panelPrincipal.add(panelBotonesArriba);
        panelPrincipal.add(panelBotonesAbajo);
        frame.add(panelPrincipal, BorderLayout.CENTER);
        frame.add(panelSuperior,BorderLayout.NORTH);

        // Botón de cerrar sesión
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.addActionListener(e -> {
            frame.dispose();
            GUIMenu.resetInstancia();  // Método que reinicia la instancia del menú
            GUIEmpleado.getInstancia(controlador, null);
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


