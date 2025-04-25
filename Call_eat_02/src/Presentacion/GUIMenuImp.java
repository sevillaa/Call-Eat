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
        JPanel panelCentralBotones=new JPanel();
        panelCentralBotones.setLayout(new BoxLayout(panelCentralBotones,BoxLayout.Y_AXIS));
        panelCentralBotones.setBackground(Color.white);
        //Dimension d= new Dimension(100,30);
        Dimension d = new Dimension(200,40);
        JPanel panelGestor=new JPanel(new BorderLayout());
        panelGestor.setBorder(BorderFactory.createLineBorder(Color.white,1));
        panelGestor.setPreferredSize(d);
        panelGestor.setMaximumSize(d);
        panelGestor.setMinimumSize(d);
        JPanel panelCamarero=new JPanel(new BorderLayout());
        panelCamarero.setBorder(BorderFactory.createLineBorder(Color.white,1));
        panelCamarero.setPreferredSize(d);
        panelCamarero.setMaximumSize(d);
        panelCamarero.setMinimumSize(d);
        JPanel panelCocina=new JPanel(new BorderLayout());
        panelCocina.setBorder(BorderFactory.createLineBorder(Color.white,1));
        panelCocina.setPreferredSize(d);
        panelCocina.setMaximumSize(d);
        panelCocina.setMinimumSize(d);
        JPanel panelMesas=new JPanel(new BorderLayout());
        panelMesas.setBorder(BorderFactory.createLineBorder(Color.white,1));
        panelMesas.setPreferredSize(d);
        panelMesas.setMaximumSize(d);
        panelMesas.setMinimumSize(d);
        
        JPanel panelSuperior=new JPanel(new BorderLayout());
        JPanel panelInferior=new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
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
        botonGestor.setBackground(new Color(100, 180, 255));
        botonGestor.setFont(new Font("Arial",Font.BOLD,20));
        botonGestor.setForeground(Color.WHITE);
        botonGestor.addActionListener(e -> {
            frame.dispose();
            new GUIGestorImp(controlador, usuario);
        });
        panelGestor.add(botonGestor,BorderLayout.CENTER);

        // Sección central: Panel Camarero
        //JPanel panelCamarero = new JPanel();
        JButton botonCamarero = new JButton("Panel Camarero");
        botonCamarero.setBackground(new Color(100, 180, 255));
        botonCamarero.setFont(new Font("Arial",Font.BOLD,20));
        botonCamarero.setForeground(Color.WHITE);
        botonCamarero.addActionListener(e -> {
            frame.dispose();
            new GUICamareroImp(controlador, usuario);
        });
        panelCamarero.add(botonCamarero,BorderLayout.CENTER);

        // Sección derecha: Panel Cocina
        //JPanel panelCocina = new JPanel();
        JButton botonCocina = new JButton("Panel Cocina");
        botonCocina.setBackground(new Color(100, 180, 255));
        botonCocina.setFont(new Font("Arial",Font.BOLD,20));
        botonCocina.setForeground(Color.WHITE);
        botonCocina.addActionListener(e -> {
            frame.dispose();
            new GUICocinaImp(controlador, usuario);
        });
        panelCocina.add(botonCocina,BorderLayout.CENTER);

        // Nueva sección: Panel Mesas
       // JPanel panelMesas = new JPanel();
        JButton botonMesas = new JButton("Panel Mesas");
        botonMesas.setBackground(new Color(100, 180, 255));
        botonMesas.setFont(new Font("Arial",Font.BOLD,20));
        botonMesas.setForeground(Color.WHITE);
        botonMesas.addActionListener(e -> {
            // Aquí se debería abrir GUIMesasImp
            frame.dispose();
            new GUIMesaImp(controlador, usuario);  // Esto abrirá el panel de las mesas
        });
        panelMesas.add(botonMesas,BorderLayout.CENTER);

        // Agregar los paneles al panel principal
        //panelSuperior.add(btnVolver, BorderLayout.LINE_START);
        ImageIcon logo = new ImageIcon("resources/logo.png"); 
        Image locoImagenEscalado = logo.getImage().getScaledInstance(63,63, Image.SCALE_SMOOTH);
        ImageIcon logoIconoEscalado=new ImageIcon(locoImagenEscalado);
        JLabel etiquetaImagen = new JLabel(logoIconoEscalado);
        
        panelSuperior.setBackground(new Color(100, 180, 255));
        panelSuperior.add(etiquetaImagen,BorderLayout.LINE_END);
        panelCentralBotones.add(Box.createVerticalGlue());
        panelCentralBotones.add(panelGestor);
        panelCentralBotones.add(panelCamarero);
        panelCentralBotones.add(panelCocina);
        panelCentralBotones.add(panelMesas);
        panelCentralBotones.add(Box.createVerticalGlue());
        panelInferior.setBackground(new Color(173, 216, 230));
        frame.add(panelCentralBotones, BorderLayout.CENTER);
        frame.add(panelSuperior,BorderLayout.NORTH);
        frame.add(panelInferior,BorderLayout.SOUTH);

        // Botón de cerrar sesión
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.setBackground(Color.gray); 
        cerrarSesionButton.setFont(new Font("Arial",Font.BOLD,15));
        cerrarSesionButton.setForeground(Color.WHITE);
        cerrarSesionButton.addActionListener(e -> {
            frame.dispose();
            GUIMenu.resetInstancia();  // Método que reinicia la instancia del menú
            GUIEmpleado.resetInstancia();
            GUIEmpleado.getInstancia(controlador, null);
        });
        frame.add(cerrarSesionButton, BorderLayout.SOUTH);
        frame.setVisible(true);
        //Aqui activamos y desactivamos los botones dependiendo del rol del empleado
        botonesDisponibles(botonGestor,botonCamarero,botonCocina,botonMesas);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Implementa actualizaciones dinámicas en la interfaz si fuera necesario
        JOptionPane.showMessageDialog(frame, "Evento recibido: " + evento);
    }
    private void botonesDisponibles(JButton gestor, JButton camarero, JButton cocina, JButton mesas) {
    	TransferEmpleado e = (TransferEmpleado) usuario;
    	String rol=e.getRol();
    	if(rol.equals("Gestor")) {
    		gestor.setEnabled(true);
    		camarero.setEnabled(true);
    		cocina.setEnabled(true);
    		mesas.setEnabled(true);
    	}
    	else if(rol.equals("Camarero")) {
    		gestor.setEnabled(false);
    		camarero.setEnabled(true);
    		cocina.setEnabled(false);
    		mesas.setEnabled(true);
    	}
    	else if(rol.equals("Cocinero")) {
    		gestor.setEnabled(false);
    		camarero.setEnabled(false);
    		cocina.setEnabled(true);
    		mesas.setEnabled(false);
    	}
    }
}


