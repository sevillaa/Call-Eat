package Presentacion;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GUICocinaImp extends GUICocina {
    
    private static final long serialVersionUID = 1L;
	private final Controlador controlador;
    private final JFrame frame;
    private final JPanel panelPrincipal;
    private final JPanel panelPedidos;
    
    public GUICocinaImp(Controlador controlador, Object datos) {
        this.controlador = Objects.requireNonNull(controlador, "El controlador no puede ser nulo");
        
        // Configuración del marco principal
        frame = new JFrame("Panel de Cocina");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel principal con BorderLayout
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de pedidos con scroll
        panelPedidos = new JPanel();
        panelPedidos.setLayout(new BoxLayout(panelPedidos, BoxLayout.Y_AXIS));
        
        JScrollPane scrollPane = new JScrollPane(panelPedidos);
        scrollPane.setPreferredSize(new Dimension(800, 500)); // Reducido para dejar espacio al botón
        
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        
        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        // Botón "Volver al Menú"
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose(); // Cierra la ventana actual
            new GUIMenuImp(controlador, datos); // Abre el menú principal 
        });
        
        panelBotones.add(btnVolver);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        
        frame.add(panelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        cargarPedidosDeEjemplo();
    }
    
    private void cargarPedidosDeEjemplo() {
        panelPedidos.removeAll();
        
        String[][] pedidosEjemplo = {
            {"1", "Hamburguesa, Papas fritas", "Sin cebolla"},
            {"2", "Pizza Pepperoni", "Bien cocida"},
            {"3", "Ensalada César", "Sin croutones"}
        };
        
        for (String[] pedido : pedidosEjemplo) {
            agregarPedido(pedido[0], pedido[1], pedido[2]);
        }
        
        panelPedidos.revalidate();
        panelPedidos.repaint();
    }
    
    private void agregarPedido(String id, String platos, String observaciones) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Pedido #" + id),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        card.setBackground(new Color(240, 240, 240));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        // Panel de información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        
        JLabel lblPlatos = new JLabel("<html><b>Platos:</b> " + platos.replace(",", "<br>• ") + "</html>");
        JLabel lblObs = observaciones.isEmpty() ? null : 
            new JLabel("<html><i>Observaciones:</i> " + observaciones + "</html>");
        
        infoPanel.add(lblPlatos);
        if (lblObs != null) {
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(lblObs);
        }
        
        // Botón de completado
        JButton btnCompletado = new JButton("Marcar como completado");
        btnCompletado.addActionListener(e -> {
            //controlador.marcarPedidoCompletado(Integer.parseInt(id));
            panelPedidos.remove(card);
            panelPedidos.revalidate();
            panelPedidos.repaint();
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
                cargarPedidosDeEjemplo();
            }
        });
    }
}
