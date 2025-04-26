package Presentacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Negocio.TransferMesa;

public class ReservarMesaPanel extends JPanel {
    
	private static final long serialVersionUID = 1L;
	private final Controlador controlador;
    private final JFrame frame;
    private final JPanel panelMesas;
    private List<TransferMesa> mesas;
    private TransferMesa mesaSeleccionada;

    
	@SuppressWarnings("unchecked")
	public ReservarMesaPanel(Controlador controlador, Object datos) {
        this.controlador = controlador;

        // Validar que datos sea una lista de TransferMesa
        if (datos instanceof List) {
            try {
                this.mesas = (List<TransferMesa>) datos;
            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(null, "Error: Los datos proporcionados no son una lista de mesas.", "Error", JOptionPane.ERROR_MESSAGE);
                this.mesas = new ArrayList<>();
            }
        } else {
            //JOptionPane.showMessageDialog(null, "Error: Se esperaba una lista de mesas, pero se recibió: " + (datos != null ? datos.getClass().getSimpleName() : "null"), "Error", JOptionPane.ERROR_MESSAGE);
            this.mesas = new ArrayList<>();
        }

        // Configuración básica del frame
        frame = new JFrame("Estado de Mesas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Título
        JLabel titleLabel = new JLabel("Seleccionar Mesa", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Panel para las mesas (3 columnas)
        panelMesas = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(panelMesas);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones de acción (Añadir, Editar, Eliminar)
        JPanel actionPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton btnReservar = new JButton("Reservar");
        btnReservar.setBackground(new Color(255, 99, 71)); // Rojo
        JButton btnLiberar = new JButton("Liberar");
        btnLiberar.setBackground(new Color(144, 238, 144)); // Verde
        
        actionPanel.add(btnReservar);
        actionPanel.add(btnLiberar);
        mainPanel.add(actionPanel, BorderLayout.EAST);
        
        // Botón de volver
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            //resetInstancia();
            new GUIMenuPrincipalImp(controlador, datos); // Reabre el menú con el usuario logueado
            //controlador.mostrarMenuPrincipal();
        });
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnVolver);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); // Corrección: Usar bottomPanel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Añadir listeners para los botones
        btnReservar.addActionListener(e -> {
        	
            if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa para resevar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } if(mesaSeleccionada.isReservada()) {
            	JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa Libre", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            else {
            	mesaSeleccionada.setReservada(true);
            }
        });
        btnLiberar.addActionListener(e -> {
        	if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa para liberar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } if(mesaSeleccionada.isReservada()) {
            	JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa Ocupada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            else {
            	mesaSeleccionada.setReservada(false);
            }
        });
        
        //actualizar(0, mesas);
    }

    @SuppressWarnings("unchecked")
    public void actualizar(int evento, Object datos) {
        SwingUtilities.invokeLater(() -> {
            panelMesas.removeAll();
            
            // Validar nuevamente los datos en el método actualizar
            if (datos instanceof List) {
                try {
                    this.mesas = (List<TransferMesa>) datos;
                } catch (ClassCastException e) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: Los datos no son una lista de mesas.", "Error", JOptionPane.ERROR_MESSAGE);
                    this.mesas = new ArrayList<>();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar: Se esperaba una lista de mesas, pero se recibió: " + (datos != null ? datos.getClass().getSimpleName() : "null"), "Error", JOptionPane.ERROR_MESSAGE);
                this.mesas = new ArrayList<>();
            }

            for (TransferMesa mesa : mesas) {
                JPanel mesaPanel = new JPanel(new BorderLayout());
                mesaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JLabel label = new JLabel("Mesa: " + mesa.getId() + "\nCapacidad: " + mesa.getCapacidad());
                label.setHorizontalAlignment(SwingConstants.CENTER);
                mesaPanel.add(label, BorderLayout.CENTER);

                // Añadir listener para seleccionar la mesa al hacer clic
                mesaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        mesaSeleccionada = mesa;
                        // Resaltar la mesa seleccionada
                        for (Component component : panelMesas.getComponents()) {
                            component.setBackground(null);
                        }
                        mesaPanel.setBackground(new Color(173, 216, 230)); // Color de selección (azul claro)
                    }
                });

                panelMesas.add(mesaPanel);
            }

            panelMesas.revalidate();
            panelMesas.repaint();
        });
    }
    
    

    public void dispose() {
        frame.dispose();
    }
}
