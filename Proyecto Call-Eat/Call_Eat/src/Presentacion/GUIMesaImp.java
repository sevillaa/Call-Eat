package Presentacion;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class GUIMesaImp extends GUIMesa {
    private final Controlador controlador;
    private final JFrame frame;
    private final JPanel panelMesas;

    public GUIMesaImp(Controlador controlador, Object datos) {
        this.controlador = controlador;
        
        // Configuración básica del frame
        frame = new JFrame("Estado de Mesas");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel para las mesas (3 columnas)
        panelMesas = new JPanel(new GridLayout(0, 3, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(panelMesas);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Botón de volver
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            resetInstancia();
            //controlador.mostrarMenuPrincipal();
        });
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnVolver);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        actualizar(0, datos); // Usamos actualizar para cargar los datos iniciales
    }

    @Override
    public void actualizar(int evento, Object datos) {
        SwingUtilities.invokeLater(() -> {
            panelMesas.removeAll();
            
            // Ejemplo básico con datos simulados
            //List<Map<String, String>> mesas = new ArrayList<>();
            
            
        });
    }
}