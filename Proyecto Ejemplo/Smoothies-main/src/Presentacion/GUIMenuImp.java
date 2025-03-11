package Presentacion;

import javax.swing.*;

import Negocio.TransferPedido;
import Negocio.TransferSmoothies;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GUIMenuImp extends GUIMenu {
    private Controlador cntr;
    private JFrame menuFrame;
    private List<TransferSmoothies> listaSmoothies;
    private DefaultListModel<String> smoothiesListModel;
    private JList<String> smoothiesList;
    private TransferPedido ped;
    private JComboBox<String> sizeComboBox; // Declaración del JComboBox

    public GUIMenuImp(Controlador controlador, Object datos,TransferPedido pedido) {
        this.ped = pedido; 
        this.cntr = controlador;
        this.listaSmoothies = new ArrayList<>();
        Iterator<TransferSmoothies> it = cntr.obtenerIteradorLista("smoothies",false);
        smoothiesListModel = new DefaultListModel<>();
        while(it.hasNext()) {
        	TransferSmoothies smoothie = it.next() ; 
        	listaSmoothies.add(smoothie) ; 
        	smoothiesListModel.addElement(smoothie.getNombre() + " ->  " + smoothie.getDescripcion());
        	 
        }

        menuFrame = new JFrame("Menu Smoothies");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800, 600);
        menuFrame.setLocationRelativeTo(null);

        JPanel contentPanel = new JPanel(new BorderLayout());

        // Panel para la lista de smoothies
        JPanel smoothiesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        
       
        smoothiesList = new JList<>(smoothiesListModel);
        smoothiesList.setFont(new Font("Arial", Font.BOLD, 16));
        smoothiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        smoothiesPanel.add(new JScrollPane(smoothiesList), gbc);

        // Panel para botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton addToCartButton = new JButton("Añadir al carrito");
        addToCartButton.setPreferredSize(new Dimension(150, 40));
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSmoothie = smoothiesList.getSelectedValue();
                String selectedSize = (String) sizeComboBox.getSelectedItem();

                if (selectedSmoothie != null) {
                    // Aquí puedes añadir la lógica para agregar el smoothie seleccionado al carrito
                    // Primero, necesitas obtener el ID del smoothie seleccionado
                    int smoothieIndex = smoothiesList.getSelectedIndex();
                    TransferSmoothies smoothie = listaSmoothies.get(smoothieIndex);
                    String smoothieID = smoothie.getNombre(); // Suponiendo que TransferSmoothies tenga un método para obtener el ID

                    // Crear el string que representa el producto en el carrito
                    String producto = "0-" + selectedSize+ "-"+ smoothieID ;// Concatenar los valores para crear el producto

                    // Luego, puedes llamar al método para agregar el smoothie al carrito
                    ped.agregarProducto(producto);

                    if(selectedSize == "Pequeño") {
                    	ped.sumarBatido(4);
                    }else if(selectedSize == "Mediano") {
                    	ped.sumarBatido(5);
                    }else {
                    	ped.sumarBatido(6);
                    }
                   // Concatenar los valores para crear el producto

                    
                    JOptionPane.showMessageDialog(menuFrame, "Smoothie '" + smoothie.getNombre() + "' añadido al carrito.");
                } else {
                    JOptionPane.showMessageDialog(menuFrame, "Por favor, selecciona un smoothie primero.");
                }
            }
        });

        JLabel sizeLabel = new JLabel("Tamaño:");
        sizeComboBox = new JComboBox<>(new String[]{"Pequeño", "Mediano", "Grande"});
        sizeComboBox.setPreferredSize(new Dimension(100, 40));
        buttonPanel.add(sizeLabel);
        buttonPanel.add(sizeComboBox);
        buttonPanel.add(addToCartButton);

        // Botón para volver atrás
        JButton backButton = new JButton("Volver");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
            }
        });
        buttonPanel.add(backButton);

        JButton cartButton = new JButton("Ver carrito");
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar(Eventos.VER_CARRITO, datos);
            }
        });
        buttonPanel.add(cartButton);

        contentPanel.add(smoothiesPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Establecer fuente para el panel principal
        Font font = new Font("Arial", Font.PLAIN, 18);
        setComponentFont(contentPanel, font);

        // Establecer color de fondo para el panel principal
        contentPanel.setBackground(Color.WHITE);

        // Agregar panel principal al frame
        menuFrame.add(contentPanel);
        menuFrame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        switch (evento) {
            case (Eventos.VER_CARRITO): {
                GUICarritoImp guiCarrito = new GUICarritoImp(cntr, datos,ped);
                break;
            }
        }
    }

    // Método para establecer la fuente de los componentes de un contenedor y sus subcomponentes
    private void setComponentFont(Container container, Font font) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                setComponentFont((Container) component, font);
            }
            component.setFont(font);
        }
    }
}
