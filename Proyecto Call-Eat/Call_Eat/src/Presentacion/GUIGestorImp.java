package Presentacion;

import javax.swing.*;
import java.awt.*;

public class GUIGestorImp extends GUIGestor {

    private JFrame frame;
    private Controlador controlador;
    private Object datos;

    public GUIGestorImp(Controlador controlador,Object datos) {
        this.controlador = controlador;
        this.datos = datos;
        initGUI();
    }

    private void initGUI() {
        frame = new JFrame("Panel Gestor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Panel de Gestión", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titulo, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnPlantilla = new JButton("Plantilla");
        btnPlantilla.addActionListener(e -> {
            // Aquí irá la GUI o acción correspondiente más adelante
            JOptionPane.showMessageDialog(frame, "Funcionalidad de plantilla aún no implementada");
            // controlador.accion(Eventos.MOSTRAR_PLANTILLA, null);
        });

        JButton btnInventario = new JButton("Inventario (Ingredientes y Platos)");
        btnInventario.addActionListener(e -> {
            // Aquí se abrirá la GUI de inventario en el futuro
            JOptionPane.showMessageDialog(frame, "Inventario aún no implementado");
            // controlador.accion(Eventos.MOSTRAR_INVENTARIO, null);
        });

        JButton btnActividadEconomica = new JButton("Actividad Económica");
        btnActividadEconomica.addActionListener(e -> {
            // En el futuro se puede abrir un panel para filtrar pedidos, etc.
            JOptionPane.showMessageDialog(frame, "Actividad económica aún no implementada");
            // controlador.accion(Eventos.MOSTRAR_ESTADISTICAS, null);
        });

        panelOpciones.add(btnPlantilla);
        panelOpciones.add(btnInventario);
        panelOpciones.add(btnActividadEconomica);

        frame.add(panelOpciones, BorderLayout.CENTER);

        // Botón para volver o cerrar
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            GUIGestor.resetInstancia();
            new GUIMenuImp(controlador, datos); // Reabre el menú con el usuario logueado

        });

        frame.add(btnVolver, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Aquí podrías actualizar info si es necesario más adelante
    }
}

