package Presentacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import Negocio.TransferMesa;

public class GUIMesaImp extends GUIMesa {
    private final Controlador controlador;
    private final JFrame frame;
    private final JPanel panelMesas;
    private List<TransferMesa> mesas;
    private TransferMesa mesaSeleccionada;

    @SuppressWarnings("unchecked")
    public GUIMesaImp(Controlador controlador, Object datos) {
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
        JButton btnAnadir = new JButton("Añadir");
        btnAnadir.setBackground(new Color(144, 238, 144)); // Verde claro
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(34, 139, 34)); // Verde oscuro
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 99, 71)); // Rojo
        
        actionPanel.add(btnAnadir);
        actionPanel.add(btnEditar);
        actionPanel.add(btnEliminar);
        mainPanel.add(actionPanel, BorderLayout.EAST);
        
        // Botón de volver
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            resetInstancia();
            controlador.mostrarMenuPrincipal();
        });
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnVolver);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH); // Corrección: Usar bottomPanel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Añadir listeners para los botones
        btnAnadir.addActionListener(e -> mostrarDialogoAnadir());
        btnEditar.addActionListener(e -> {
            if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                mostrarDialogoEditar();
            }
        });
        btnEliminar.addActionListener(e -> {
            if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                mostrarDialogoEliminar();
            }
        });
        
        actualizar(0, mesas);
    }

    @SuppressWarnings("unchecked")
    @Override
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

    private void mostrarDialogoAnadir() {
        JDialog dialog = new JDialog(frame, "Añadir Mesa", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(frame);

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
            controlador.anadirMesa(nuevaMesa);
            dialog.dispose();
        });
        
        dialog.setVisible(true);
    }

    private void mostrarDialogoEditar() {
        JDialog dialog = new JDialog(frame, "Editar Mesa", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setLocationRelativeTo(frame);

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
            controlador.editarMesa(mesaSeleccionada);
            mesaSeleccionada = null; // Limpiar selección
            dialog.dispose();
        });
        
        dialog.setVisible(true);
    }

    private void mostrarDialogoEliminar() {
        JDialog dialog = new JDialog(frame, "¿Eliminar Mesa?", true);
        dialog.setSize(300, 100);
        dialog.setLayout(new FlowLayout());
        dialog.setLocationRelativeTo(frame);

        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(255, 99, 71));
        
        dialog.add(btnCancelar);
        dialog.add(btnEliminar);
        
        btnCancelar.addActionListener(e -> dialog.dispose());
        btnEliminar.addActionListener(e -> {
            controlador.eliminarMesa(mesaSeleccionada);
            mesaSeleccionada = null; // Limpiar selección
            panelMesas.repaint();
            dialog.dispose();
        });
        
        dialog.setVisible(true);
    }

    @Override
    public void dispose() {
        frame.dispose();
    }
}