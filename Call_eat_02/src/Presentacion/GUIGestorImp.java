package Presentacion;

import javax.swing.*;

import Negocio.TransferEmpleado;

import java.awt.*;
import java.util.List;


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
            frame.setVisible(false); // Ocultar la ventana actual del gestor

            JFrame plantillaFrame = new JFrame("Gestión de Empleados");
            plantillaFrame.setSize(700, 500);
            plantillaFrame.setLocationRelativeTo(null);
            plantillaFrame.setLayout(new BorderLayout());

            JLabel tituloPlantilla = new JLabel("Gestión de Plantilla", SwingConstants.CENTER);
            tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
            plantillaFrame.add(tituloPlantilla, BorderLayout.NORTH);

            // Obtener la lista de empleados desde el controlador
            List<TransferEmpleado> empleados = controlador.listaEmpleados();
            
            // Crear la tabla con los empleados
            String[] columnas = {"ID", "Nombre", "Correo", "Rol"};
            Object[][] datosEmpleados = new Object[empleados.size()][4];

            for (int i = 0; i < empleados.size(); i++) {
                TransferEmpleado empleado = empleados.get(i);
                datosEmpleados[i][0] = empleado.getId();
                datosEmpleados[i][1] = empleado.getNombre();
                datosEmpleados[i][2] = empleado.getCorreo();
                datosEmpleados[i][3] = empleado.getRol();
            }

            JTable tabla = new JTable(datosEmpleados, columnas);
            JScrollPane scrollTabla = new JScrollPane(tabla);
            plantillaFrame.add(scrollTabla, BorderLayout.CENTER);

            // Panel inferior con botones
            JPanel panelBotones = new JPanel(new FlowLayout());

            JButton btnAñadir = new JButton("Añadir Empleado");
            btnAñadir.addActionListener(ev -> {
                JFrame registroFrame = new JFrame("Registrar Empleado");
                registroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                registroFrame.setSize(400, 300);
                registroFrame.setLocationRelativeTo(null);

                // Crear un contenedor con CardLayout solo para este frame
                JPanel panelContenedor = new JPanel(new CardLayout());
                CardLayout cardLayout = (CardLayout) panelContenedor.getLayout();

                RegistroPanel registroPanel = new RegistroPanel(panelContenedor, cardLayout, controlador);
                panelContenedor.add(registroPanel, "registro");

                registroFrame.add(panelContenedor);
                registroFrame.setVisible(true);

                // Mostrar directamente el panel de registro
                cardLayout.show(panelContenedor, "registro");
            });

            JButton btnModificar = new JButton("Modificar Empleado");
            btnModificar.addActionListener(ev -> {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener datos del empleado seleccionado
                    String id = (String) tabla.getValueAt(filaSeleccionada, 0);
                    String nombre = (String) tabla.getValueAt(filaSeleccionada, 1);
                    String correo = (String) tabla.getValueAt(filaSeleccionada, 2);
                    String rol = (String) tabla.getValueAt(filaSeleccionada, 3);

                    // Aquí necesitamos obtener también la contraseña
                    // Opciones:
                    // 1. Si tabla no la tiene, puedes buscarla desde el objeto original
                    TransferEmpleado empleado = empleados.get(filaSeleccionada); // <- Usa esto
                    String contraseña = empleado.getContraseña(); // <- si tienes getter

                    // Crear frame para editar
                    JFrame editarFrame = new JFrame("Modificar Empleado");
                    editarFrame.setSize(400, 350);
                    editarFrame.setLocationRelativeTo(null);
                    editarFrame.setLayout(new GridLayout(6, 2, 10, 10));

                    JTextField campoNombre = new JTextField(nombre);
                    JTextField campoCorreo = new JTextField(correo);
                    JTextField campoRol = new JTextField(rol);
                    JPasswordField campoContraseña = new JPasswordField(contraseña); // editable

                    editarFrame.add(new JLabel("ID:"));
                    editarFrame.add(new JLabel(String.valueOf(id)));

                    editarFrame.add(new JLabel("Nombre:"));
                    editarFrame.add(campoNombre);

                    editarFrame.add(new JLabel("Correo:"));
                    editarFrame.add(campoCorreo);

                    editarFrame.add(new JLabel("Rol:"));
                    editarFrame.add(campoRol);

                    editarFrame.add(new JLabel("Contraseña:"));
                    editarFrame.add(campoContraseña);

                    JButton btnGuardar = new JButton("Guardar Cambios");
                    btnGuardar.addActionListener(evGuardar -> {
                        String nuevaContraseña = new String(campoContraseña.getPassword());

                        TransferEmpleado modificado = new TransferEmpleado(
                            id,
                            campoNombre.getText(),
                            campoCorreo.getText(),
                            campoRol.getText(),
                            nuevaContraseña
                        );

                        controlador.modificarEmpleado(modificado);

                        editarFrame.dispose();
                        plantillaFrame.dispose();
                        JOptionPane.showMessageDialog(frame, "Empleado modificado correctamente.");
                        frame.setVisible(true);
                    });

                    editarFrame.add(new JLabel()); // Espacio
                    editarFrame.add(btnGuardar);

                    editarFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(plantillaFrame, "Selecciona un empleado primero.");
                }
            });


            JButton btnEliminar = new JButton("Eliminar Empleado");
            btnEliminar.addActionListener(ev -> {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    String idEmpleado = (String) tabla.getValueAt(filaSeleccionada, 0);

                    TransferEmpleado empleadoAEliminar = null;
                    for (TransferEmpleado emp : empleados) {
                        if (emp.getId().equals(idEmpleado)) {
                            empleadoAEliminar = emp;
                            break;
                        }
                    }

                    if (empleadoAEliminar != null) {
                        int confirmacion = JOptionPane.showConfirmDialog(
                            plantillaFrame,
                            "¿Estás seguro de que deseas eliminar a " + empleadoAEliminar.getNombre() + "?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                        );

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            boolean eliminado = controlador.eliminarEmpleado(empleadoAEliminar);
                            if (eliminado) {
                                JOptionPane.showMessageDialog(plantillaFrame, "Empleado eliminado correctamente.");
                                plantillaFrame.dispose(); // recarga la tabla
                                new GUIGestorImp(controlador, datos);
                            } else {
                                JOptionPane.showMessageDialog(plantillaFrame, "Error al eliminar el empleado.");
                            }
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(plantillaFrame, "Selecciona un empleado primero.");
                }
            });


            JButton btnAtras = new JButton("Atrás");
            btnAtras.addActionListener(ev -> {
                plantillaFrame.dispose(); // Cerrar ventana de plantilla
                frame.setVisible(true);  // Volver a mostrar el panel gestor
            });

            panelBotones.add(btnAñadir);
            panelBotones.add(btnModificar);
            panelBotones.add(btnEliminar);
            panelBotones.add(btnAtras);

            plantillaFrame.add(panelBotones, BorderLayout.SOUTH);
            plantillaFrame.setVisible(true);
        });

        // Aquí puedes añadir más botones o acciones adicionales para otros apartados como Inventario y Actividad Económica

       

        JButton btnInventario = new JButton("Inventario (Ingredientes y Platos)");
        btnInventario.addActionListener(e -> {
            // Aquí se abrirá la GUI de inventario en el futuro
        	/*
        	 * 1.Debe sacar una lista con los ingredientes asi como de su stock
        	 * 2.Debe permitir modificar los ingredientes de la lista
        	 * 3.Debe poder añadir nuevos ingredientes
        	 */
            JOptionPane.showMessageDialog(frame, "Inventario aún no implementado");
            // controlador.accion(Eventos.MOSTRAR_INVENTARIO, null);
        });

        JButton btnActividadEconomica = new JButton("Actividad Económica");
        btnActividadEconomica.addActionListener(e -> {
            // En el futuro se puede abrir un panel para filtrar pedidos, etc.
        	/*
        	 * Este apartado deberia poder filtrar de la base de datos de pedidos para poder obtener 
        	 * un registro de beenficios mensuales/diarios
        	 */
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

