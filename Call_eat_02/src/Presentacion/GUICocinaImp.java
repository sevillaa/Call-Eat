package Presentacion;

import javax.swing.*;
import Negocio.TransferPedido;
import Presentacion.Eventos;

import java.awt.*;
import java.util.List;
import java.util.Objects;

public class GUICocinaImp extends GUICocina {
    private static final long serialVersionUID = 1L;

    private final Controlador controlador;
    private final JFrame frame;
    private final JPanel panelPrincipal;
    private final JPanel panelPedidos;

    // Constructor configura la UI y carga los pedidos
    public GUICocinaImp(Controlador controlador, Object datos) {
        this.controlador = Objects.requireNonNull(controlador, "El controlador no puede ser nulo");

        frame = new JFrame("Panel de Cocina");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelPedidos = new JPanel();
        panelPedidos.setLayout(new BoxLayout(panelPedidos, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panelPedidos);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new GUIMenuImp(controlador, datos);
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panelBotones.add(btnVolver);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        frame.add(panelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cargarPedidos();
    }

    /**
     * Consulta todos los pedidos y los muestra en el panelPedidos.
     */
    private void cargarPedidos() {
        panelPedidos.removeAll();

        List<TransferPedido> lista = controlador.listaPedidos(null, null);

        boolean hayPedidos = false;

        if (lista != null && !lista.isEmpty()) {
            for (TransferPedido pedido : lista) {
                if (!pedido.getPreparado()) {
                    agregarPedido(pedido);
                    hayPedidos = true;
                }
            }
        }

        if (!hayPedidos) {
            JLabel lblVacio = new JLabel("No hay pedidos pendientes.", SwingConstants.CENTER);
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 14));
            panelPedidos.add(lblVacio);
        }

        panelPedidos.revalidate();
        panelPedidos.repaint();
    }

    /**
     * Crea una tarjeta de UI para un pedido con botón de completar.
     */
    private void agregarPedido(TransferPedido pedido) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Pedido #" + pedido.getId()),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        card.setBackground(new Color(240, 240, 240));
        // card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); // ¡Quitado para evitar recorte!

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        // Mostrar platos
        StringBuilder sb = new StringBuilder();
        for (var plato : pedido.getPlatos()) {
            sb.append("• ").append(plato.getNombre()).append("\n");
        }
        JLabel lblPlatos = new JLabel("<html><b>Platos:</b><br>" +
            sb.toString().replace("\n", "<br>") + "</html>");
        infoPanel.add(lblPlatos);

        // Mostrar dirección (mesa o para llevar)
        infoPanel.add(Box.createVerticalStrut(5));
        JLabel lblDireccion = new JLabel("<html><b>Destino:</b> " + pedido.getDireccion() + "</html>");
        infoPanel.add(lblDireccion);

        // Mostrar observaciones si existen
        if (pedido.getNotas() != null && !pedido.getNotas().isEmpty()) {
            infoPanel.add(Box.createVerticalStrut(5));
            JLabel lblObs = new JLabel("<html><i>Observaciones:</i> " + pedido.getNotas() + "</html>");
            infoPanel.add(lblObs);
        }

        // Botón para marcar como completado
        JButton btnCompletado = new JButton("Marcar como completado");
        btnCompletado.addActionListener(e -> {
            pedido.setPreparado(true);
            controlador.modificarPedido(pedido);
            controlador.eliminarPedido(pedido); // Pondría solo esta porque sino el JSON de pedidos se va llenando
            cargarPedidos();
        });

        card.add(infoPanel, BorderLayout.CENTER);
        card.add(btnCompletado, BorderLayout.SOUTH);

        panelPedidos.add(card);
        panelPedidos.add(Box.createVerticalStrut(10));
    }

    @Override
    public void actualizar(int evento, Object datos) {
        SwingUtilities.invokeLater(() -> {
            if (evento == Eventos.PEDIDOS_ACTUALIZADOS) {
                cargarPedidos();
            }
        });
    }
}


