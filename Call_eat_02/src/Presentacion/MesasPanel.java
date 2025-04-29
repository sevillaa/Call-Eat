package Presentacion;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Negocio.TransferMesa;

public class MesasPanel extends JPanel {

    private static final long serialVersionUID = 1L;
	private JPanel panelContenedor;
	private CardLayout cardLayout;
	private Controlador controlador;
	//private Object datos;
	//private JPanel panelMesas;
	private List<TransferMesa> mesas;
	private TransferMesa mesaSeleccionada;
	private JPanel PanelMesas;
	private List<JButton> botonesMesas = new ArrayList<>();
	private JButton botonMesaSeleccionado;


	public MesasPanel(CardLayout cardLayout, JPanel panelContenedor, Controlador controlador) {
    	this.panelContenedor = panelContenedor;
        this.cardLayout = cardLayout;
        this.controlador = controlador;
        //this.datos=datos;
        
        initComponentes();
        
    }
        
    private void initComponentes(){
    	mesas = controlador.listaMesas();
    	this.setLayout(new BorderLayout());

        JPanel panelSuperior =new JPanel(new BorderLayout());//panel de boton atras y logo
        
        ImageIcon volverIcono = new ImageIcon("resources/botonAtras.png");
        Image volverIconoImagen = volverIcono.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        JButton btnVolver = new JButton("Volver",new ImageIcon(volverIconoImagen));
        btnVolver.setFont(new Font("Arial",Font.BOLD,10));
        btnVolver.setForeground(Color.white);
        btnVolver.setPreferredSize(new Dimension(110,20));
        btnVolver.setContentAreaFilled(false); // elimina el fondo estirado
        btnVolver.setBorderPainted(false);    // elimina el borde
        btnVolver.setFocusPainted(false);     // quita ese borde de foco azul
        btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
        btnVolver.setVerticalAlignment(SwingConstants.CENTER);
        btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolver.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnVolver.addActionListener(ev -> {
        	cardLayout.show(panelContenedor, "menu");
        });
        
        ImageIcon logo = new ImageIcon("resources/logo.png"); 
        Image locoImagenEscalado = logo.getImage().getScaledInstance(63,63, Image.SCALE_SMOOTH);
        ImageIcon logoIconoEscalado=new ImageIcon(locoImagenEscalado);
        JLabel etiquetaImagen = new JLabel(logoIconoEscalado);
        
        panelSuperior.add(btnVolver, BorderLayout.LINE_START);
        panelSuperior.setBackground(new Color(100, 180, 255));
        panelSuperior.add(etiquetaImagen,BorderLayout.LINE_END);
        
        this.add(panelSuperior,BorderLayout.PAGE_START);
        
        
        
     // Panel de botones de acción (Añadir, Editar, Eliminar)
        JPanel actionPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        actionPanel.setBackground(Color.gray);
        JButton btnAnadir = new JButton("Añadir");
        btnAnadir.setBackground(new Color(144, 238, 144)); // Verde claro
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(34, 139, 34)); // Verde oscuro
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 99, 71)); // Rojo
        
        actionPanel.add(btnAnadir);
        actionPanel.add(btnEditar);
        actionPanel.add(btnEliminar);
        
        this.add(actionPanel,BorderLayout.LINE_END);
        
        btnAnadir.addActionListener(e -> mostrarDialogoAnadir());
        btnEditar.addActionListener(e -> {
            if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una mesa para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                mostrarDialogoEditar();
            }
        });
        btnEliminar.addActionListener(e -> {
            if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una mesa para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                mostrarDialogoEliminar();
            }
        });
      
        
     // Panel Mesas
        PanelMesas = new JPanel(new GridLayout(0, 4, 10, 10));
        JScrollPane scrollPanelMesas = new JScrollPane(PanelMesas, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanelMesas.setPreferredSize(new Dimension(600, 400)); // Tamaño fijo para el JScrollPane
        this.add(scrollPanelMesas, BorderLayout.CENTER);

        // Añadir botones iniciales
        for (TransferMesa m : controlador.listaMesas()) {
        	creaBotones(m);
        }
        PanelMesas.revalidate();
        PanelMesas.repaint();
        scrollPanelMesas.revalidate(); // Asegura que el JScrollPane se actualice
        
        
        scrollPanelMesas.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int alturaBoton = scrollPanelMesas.getViewport().getHeight() / 2;
				int anchuraBoton = (scrollPanelMesas.getViewport().getWidth()) / 4;

				for (JButton boton : botonesMesas) {
					boton.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));
					boton.setMaximumSize(new Dimension(anchuraBoton, alturaBoton));
				}
				PanelMesas.revalidate();
			}
		});
        
    }   

    private void mostrarDialogoAnadir() {
        JDialog dialog = new JDialog();
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(this);
        //dialog.setVisible(true);

        JPanel formPanel = new JPanel(new GridLayout(1, 2));
        formPanel.add(new JLabel("Capacidad"));
        JSpinner capacidadSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 20, 1));
        formPanel.add(capacidadSpinner);
        
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnAnadir = new JButton("Añadir");
        btnAnadir.setBackground(new Color(144, 238, 144));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnAnadir);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        btnAnadir.addActionListener(e -> {
            int capacidad = (int) capacidadSpinner.getValue();
            String id = String.valueOf(mesas.size() + 1); // Generar ID automáticamente
            TransferMesa nuevaMesa = new TransferMesa(id, capacidad);
            controlador.crearMesa(nuevaMesa);
            creaBotones(nuevaMesa);
        	PanelMesas.revalidate();
        	PanelMesas.repaint();
            dialog.dispose();
        });
        dialog.setVisible(true);
    }

    private void mostrarDialogoEditar() {
        JDialog dialog = new JDialog();
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel(new GridLayout(1, 2));
        formPanel.add(new JLabel("Capacidad"));
        JSpinner capacidadSpinner = new JSpinner(new SpinnerNumberModel(mesaSeleccionada.getCapacidad(), 1, 20, 1));
        formPanel.add(capacidadSpinner);
        
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(34, 139, 34));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnEditar);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        btnEditar.addActionListener(e -> {
            int capacidad = (int) capacidadSpinner.getValue();
            mesaSeleccionada.setCapacidad(capacidad);
            if (botonMesaSeleccionado != null) {
                botonMesaSeleccionado.setText("Mesa " + mesaSeleccionada.getId() + " Capacidad " + capacidad);
            }
            PanelMesas.revalidate();
            PanelMesas.repaint();
            mesaSeleccionada = null; // Limpiar selección
            botonMesaSeleccionado = null;
            dialog.dispose();
        });
        
        dialog.setVisible(true);
    }

    private void mostrarDialogoEliminar() {
        JDialog dialog = new JDialog();
        dialog.setSize(300, 100);
        dialog.setLayout(new FlowLayout());
        dialog.setLocationRelativeTo(this);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 99, 71));
        
        dialog.add(btnCancelar);
        dialog.add(btnEliminar);
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        btnEliminar.addActionListener(e -> {
        	controlador.eliminarMesa(mesaSeleccionada);
            mesaSeleccionada = null; // Limpiar selección
            this.PanelMesas.remove(botonMesaSeleccionado);
            PanelMesas.revalidate();
            PanelMesas.repaint();
            dialog.dispose();
        });
        
        dialog.setVisible(true);
    }

    // Método auxiliar para crear y configurar botones de mesa
    private void creaBotones(TransferMesa mesa) {
        JButton botonMesa = new JButton("Mesa " + mesa.getId() + " Capacidad " + mesa.getCapacidad());
        botonMesa.setBackground(Color.WHITE);
        botonMesa.addActionListener(e -> {
            if (botonMesaSeleccionado != null) {
                botonMesaSeleccionado.setBackground(Color.WHITE);
            }
            mesaSeleccionada = mesa;
            botonMesaSeleccionado = botonMesa;
            botonMesaSeleccionado.setBackground(new Color(173, 216, 230));
        });
        botonesMesas.add(botonMesa);
        PanelMesas.add(botonMesa);
    }
}