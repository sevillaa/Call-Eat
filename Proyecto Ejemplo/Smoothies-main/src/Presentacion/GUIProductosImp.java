package Presentacion;

import javax.swing.*;

import Negocio.TransferPedido;
import Negocio.TransferProducto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class GUIProductosImp extends GUIProductos {

    private Controlador cntr;
    private JFrame menuFrame;
    
    private JLabel totalUnidadesLabel;
    private int totalUnidades;
    private TransferPedido ped;


    public GUIProductosImp(Controlador controlador, TransferPedido pedido) {
        this.ped = pedido ; 

        this.cntr = controlador;
        
        this.totalUnidades = 0;
        Iterator<TransferProducto> it = cntr.obtenerIteradorLista("ingredientes", true) ;
        // Obtener la lista de ingredientes desde el controlador
        
        

        // Crear el JFrame principal
        menuFrame = new JFrame("Smoothie Personalizado");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800, 400);
        menuFrame.setLocationRelativeTo(null);

        // Crear un JPanel para contener todo el contenido
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Crear un JPanel para los controles de tamaño del batido y tipo de leche
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Componentes para el tamaño del batido
        JLabel sizeLabel = new JLabel("Tamaño del batido:");
        String[] sizes = {"Pequeño", "Mediano", "Grande"};
        JComboBox<String> sizeComboBox = new JComboBox<>(sizes);

        // Componentes para el tipo de leche
        JLabel milkLabel = new JLabel("Tipo de leche:");
        String[] milkTypes = {"Entera", "Desnatada", "Semi-desnatada"};
        JComboBox<String> milkComboBox = new JComboBox<>(milkTypes);

        // Agregar componentes al panel de controles
        controlsPanel.add(sizeLabel);
        controlsPanel.add(sizeComboBox);
        controlsPanel.add(milkLabel);
        controlsPanel.add(milkComboBox);

        // Agregar el panel de controles al JPanel de contenido
        contentPanel.add(controlsPanel, BorderLayout.NORTH);

        // Crear un JPanel para la lista de ingredientes
        JPanel listaPanel = new JPanel(new GridBagLayout());

        // Crear un JPanel para mostrar las unidades totales añadidas
        JPanel unidadesPanel = new JPanel(new BorderLayout());
        totalUnidadesLabel = new JLabel("Ingredientes añadidos: " + totalUnidades);
        unidadesPanel.add(totalUnidadesLabel, BorderLayout.CENTER);

        // Calcular el ancho máximo de los nombres de los productos
        int maxWidth = 0;
      
        // Agregar la lista de ingredientes al JPanel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        StringBuilder ingredientesString = new StringBuilder();

        while(it.hasNext()) {
    		TransferProducto producto =  it.next() ;
	        JLabel tempLabel = new JLabel(producto.getNombre());
	        maxWidth = Math.max(maxWidth, tempLabel.getPreferredSize().width);
            final TransferProducto currentProducto = producto; // Captura la referencia final al producto actual

            JPanel productoPanel = new JPanel(new BorderLayout());
            JLabel nombreLabel = new JLabel(producto.getNombre());
            // Establecer el mismo ancho mínimo para todos los nombres de productos
            nombreLabel.setPreferredSize(new Dimension(maxWidth, nombreLabel.getPreferredSize().height));

            JTextField contadorTextField = new JTextField("0", 3); // Campo de texto para el contador individual
            JButton addButton = new JButton("+");
            JButton removeButton = new JButton("-");

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para añadir una unidad del producto
                    int contador = Integer.parseInt(contadorTextField.getText());
                    if(totalUnidades < 5) {
                        contador++;
                        contadorTextField.setText(Integer.toString(contador));

                        totalUnidades++;
                        totalUnidadesLabel.setText("Total ingredientes: " + totalUnidades);

                        // Añadir el ingrediente al string
                        ingredientesString.append(currentProducto.getNombre()).append("-");
                    }
                    else {
                        JOptionPane.showMessageDialog(menuFrame, "No se pueden agregar más de 5 ingredientes", "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                }
            });

            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para quitar una unidad del producto si el contador es mayor que 0
                    int contador = Integer.parseInt(contadorTextField.getText());
                    if (contador > 0) {
                        contador--;
                        contadorTextField.setText(Integer.toString(contador));

                        totalUnidades--;
                        totalUnidadesLabel.setText("Total ingredientes: " + totalUnidades);

                        // Quitar el ingrediente del string
                        String nombreProducto = currentProducto.getNombre();
                        int index = ingredientesString.indexOf(nombreProducto);
                        if (index != -1) {
                            ingredientesString.delete(index, index + nombreProducto.length() + 1); // +1 para eliminar también el "-"
                        }
                    }
                }
            });

            // Agregar componentes al panel de producto
            JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
            buttonsPanel.add(removeButton);
            buttonsPanel.add(addButton);
            productoPanel.add(nombreLabel, BorderLayout.WEST);
            productoPanel.add(buttonsPanel, BorderLayout.EAST);
            productoPanel.add(contadorTextField, BorderLayout.CENTER);
            gbc.gridy++;
            listaPanel.add(productoPanel, gbc);
        }

        // Agregar la lista de ingredientes al JScrollPane
        JScrollPane scrollPane = new JScrollPane(listaPanel);

        // Agregar el JScrollPane al JPanel de contenido
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Agregar el JPanel de unidades al JPanel de contenido
        contentPanel.add(unidadesPanel, BorderLayout.NORTH);

        // Crear un botón para volver atrás
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana actual
                menuFrame.dispose();
                // Volver a la pantalla anterior
                // Implementa la lógica para volver a la pantalla anterior aquí
            }
        });

    
     // Crear un botón para añadir ingredientes
        JButton addButton = new JButton("Siguiente");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear un nuevo JFrame para el panel de selección
                JFrame selectFrame = new JFrame("Seleccionar Tamaño del Batido y Tipo de Leche");
                selectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                selectFrame.setSize(400, 200);
                selectFrame.setLocationRelativeTo(null);

                // Crear un JPanel para el contenido del panel de selección
                JPanel selectPanel = new JPanel(new GridLayout(3, 2));

                // Componentes para el tamaño del batido
                JLabel sizeLabel = new JLabel("Tamaño del batido:");
                String[] sizes = {"Pequeño", "Mediano", "Grande"};
                JComboBox<String> sizeComboBox = new JComboBox<>(sizes);

                // Componentes para el tipo de leche
                JLabel milkLabel = new JLabel("Tipo de leche:");
                String[] milkTypes = {"Entera", "Desnatada", "Semi-desnatada"};
                JComboBox<String> milkComboBox = new JComboBox<>(milkTypes);

                // Agregar componentes al panel de selección
                selectPanel.add(sizeLabel);
                selectPanel.add(sizeComboBox);
                selectPanel.add(milkLabel);
                selectPanel.add(milkComboBox);

                // Crear un botón para volver atrás
                JButton backButton = new JButton("Volver Atrás");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Cerrar el panel de selección
                        selectFrame.dispose();
                    }
                });
                selectPanel.add(backButton);

                // Crear un botón para finalizar
                JButton finishButton = new JButton("Finalizar");
                finishButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtener el tamaño del batido seleccionado
                        String selectedSize = (String) sizeComboBox.getSelectedItem();

                        // Obtener el tipo de leche seleccionado
                        String selectedMilk = (String) milkComboBox.getSelectedItem();

                        String producto = "1-" + selectedSize + "-" + selectedMilk + "(" + ingredientesString + ")";
                        
                        
                        ped.agregarProducto(producto);
                        
                        if(selectedSize == "Pequeño") {
                        	ped.sumarBatido(4);
                        }
                        else if(selectedSize == "Mediano") {
                        	ped.sumarBatido(5);
                        }
                        else {
                        	ped.sumarBatido(6);
                        }
                        
                        menuFrame.dispose();
                        
                        selectFrame.dispose();
                    }
                });
                selectPanel.add(finishButton);

                // Agregar el panel de selección al JFrame
                selectFrame.add(selectPanel);
                selectFrame.setVisible(true);
            }
        });



        // Crear un panel para contener los botones
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(addButton);
        buttonsPanel.add(backButton);

        // Agregar el panel de botones al JPanel de contenido
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Agregar el JPanel de contenido al JFrame principal
        menuFrame.add(contentPanel);
        menuFrame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Implementa la lógica para actualizar la interfaz gráfica cuando sea necesario
    }
}
