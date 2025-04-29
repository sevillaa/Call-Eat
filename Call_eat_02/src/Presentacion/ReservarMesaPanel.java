package Presentacion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
import Negocio.TransferMesa;

public class ReservarMesaPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private final Controlador controlador;
    private final JFrame frame;
    private final JPanel panelMesas;
    private List<TransferMesa> mesas;
    private TransferMesa mesaSeleccionada;
    private JCalendar calendar;

    
    public ReservarMesaPanel(Controlador controlador, Object datos) {
        this.controlador = controlador;
        this.mesas = this.controlador.listaMesas();
        
        // Configuración del frame
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

        // Panel para JCalendar y botones
        JPanel sidePanel = new JPanel(new BorderLayout());
        
        // Añadir JCalendar
        calendar = new JCalendar();
        calendar.setTodayButtonVisible(true);
        sidePanel.add(calendar, BorderLayout.NORTH);

        // Panel de botones de acción
        JPanel actionPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JButton btnReservar = new JButton("Reservar");
        btnReservar.setBackground(new Color(255, 99, 71)); // Rojo
        JButton btnLiberar = new JButton("Liberar");
        btnLiberar.setBackground(new Color(144, 238, 144)); // Verde

        actionPanel.add(btnReservar);
        actionPanel.add(btnLiberar);
        sidePanel.add(actionPanel, BorderLayout.CENTER);

        mainPanel.add(sidePanel, BorderLayout.EAST);

        // Botón de volver
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new GUIMenuPrincipalImp(controlador, datos);
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnVolver);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Listeners para los botones
        btnReservar.addActionListener(x -> {
            if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa para reservar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (mesaSeleccionada.isReservada()) {
                JOptionPane.showMessageDialog(frame, "La mesa ya está reservada o ocupada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                mesaSeleccionada.setReservada(true);
                Date selectedDate = calendar.getDate();
                JOptionPane.showMessageDialog(frame, "Mesa " + mesaSeleccionada.getId() + " reservada para " + selectedDate, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizar(0, mesas);
            }
        });

        btnLiberar.addActionListener(t -> {
            if (mesaSeleccionada == null) {
                JOptionPane.showMessageDialog(frame, "Por favor, seleccione una mesa para liberar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (!mesaSeleccionada.isReservada()) {
                JOptionPane.showMessageDialog(frame, "La mesa ya está libre", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                mesaSeleccionada.setReservada(false);
                JOptionPane.showMessageDialog(frame, "Mesa " + mesaSeleccionada.getId() + " liberada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizar(0, mesas);
            }
        });

        actualizar(0, mesas);
    }

    public void actualizar(int evento, Object datos) {
        SwingUtilities.invokeLater(() -> {
            panelMesas.removeAll();
            // Validar datos
                try {
                    this.mesas = controlador.listaMesas();
                } catch (ClassCastException e) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar: Los datos no son una lista de mesas.", "Error", JOptionPane.ERROR_MESSAGE);
                    this.mesas = new ArrayList<>();
                }
             

            for (TransferMesa mesa : mesas) {
                JButton mesaButton = new JButton();
                mesaButton.setFont(new Font("Arial", Font.PLAIN, 12));
                String estado = getEstadoMesa(mesa); // Método auxiliar para determinar el estado
                mesaButton.setText("<html>Mesa " + mesa.getId() + "<br>" + estado + "</html>");

                // Asignar color según estado
                switch (estado) {
                    case "Reservada":
                        mesaButton.setBackground(Color.YELLOW);
                        break;
                    case "Ocupada":
                        mesaButton.setBackground(Color.RED);
                        break;
                    case "Libre":
                        mesaButton.setBackground(Color.GREEN);
                        break;
                }

                // Añadir listener para seleccionar la mesa
                mesaButton.addActionListener(e -> {
                    mesaSeleccionada = mesa;
                    // Resaltar la mesa seleccionada
                    for (Component component : panelMesas.getComponents()) {
                        JButton btn = (JButton) component;
                        String btnEstado = getEstadoMesa(mesas.get(panelMesas.getComponentZOrder(component)));
                        switch (btnEstado) {
                            case "Reservada":
                                btn.setBackground(Color.YELLOW);
                                break;
                            case "Ocupada":
                                btn.setBackground(Color.RED);
                                break;
                            case "Libre":
                                btn.setBackground(Color.GREEN);
                                break;
                        }
                    }
                    mesaButton.setBackground(new Color(173, 216, 230)); // Azul claro para selección
                });

                panelMesas.add(mesaButton);
            }

            panelMesas.revalidate();
            panelMesas.repaint();
        });
    }

    // Método auxiliar para determinar el estado de la mesa
    private String getEstadoMesa(TransferMesa mesa) {
        // Asumiendo que TransferMesa solo tiene isReservada() por ahora
        // Si tienes un método para "Ocupada", cámbialo aquí
        if (mesa.isDisponible() &&!mesa.isReservada()) {
            return "Libre"; // Podría ser "Ocupada" si añades lógica
        } else if(!mesa.isReservada() && !mesa.isDisponible()){
            return "Ocupada";
        }else {
        	return "Reservada";
        }
        // Nota: Para "Ocupada", necesitarías un método como mesa.isOcupada()
        // Ejemplo: return mesa.isOcupada() ? "Ocupada" : mesa.isReservada() ? "Reservada" : "Libre";
    }

    public void dispose() {
        frame.dispose();
    }
}