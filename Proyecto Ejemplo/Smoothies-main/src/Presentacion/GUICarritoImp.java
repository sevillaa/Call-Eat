package Presentacion;
import javax.swing.*;

import Negocio.TransferPedido;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICarritoImp extends GUICarrito {
    private Controlador cntr;
    private JFrame carritoFrame;
    private JPanel panel;
    private JTextField totalField;
    private JList<String> productosList;
   
    private TransferPedido ped ; 

    public GUICarritoImp(Controlador controlador, Object datos, TransferPedido pedido) {
        this.cntr = controlador;
        this.ped =  pedido ; 
     
        
        
        // Crear un nuevo JFrame para la GUI del carrito
        carritoFrame = new JFrame("Carrito");
        carritoFrame.setSize(600, 400);
        carritoFrame.setLocationRelativeTo(null);

        // Crear un nuevo JPanel para el contenido del carrito
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear un botón para volver atrás
        JButton volverAtrasButton = new JButton("Volver Atrás");
        volverAtrasButton.addActionListener(e -> {
            // Cerrar la ventana del carrito
            carritoFrame.dispose();
        });

        // Crear un modelo de lista para los productos
        DefaultListModel<String> productosListModel = new DefaultListModel<>();
        productosList = new JList<>(productosListModel);
        productosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(productosList);
        scrollPane.setPreferredSize(new Dimension(200, 200));
    

        // Mostrar cada producto en la lista
        String productosStr = ped.getBatidos();
	        if(productosStr != null ) {
	        	String[] productos = productosStr.split("/");
	        for (String producto : productos) {
	        	
	            productosListModel.addElement(producto);
	           
        }
        }

     // Crear un botón para eliminar un producto del carrito
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = productosList.getSelectedIndex();
                if (selectedIndex != -1) {
                    ped.eliminarProducto(productosListModel.elementAt(selectedIndex));
                    productosListModel.remove(selectedIndex);
                    
                    // Actualizar el campo de texto con el nuevo precio del pedido
                    totalField.setText(String.valueOf(ped.getPrecio()));
                }
            }
        });


        // Crear un JPanel para contener el botón de eliminar
        JPanel eliminarPanel = new JPanel();
        eliminarPanel.add(eliminarButton);

        // Crear un JPanel para la parte inferior del carrito
        JPanel bottomPanel = new JPanel(new GridLayout(2, 2));

        // Crear un JLabel para mostrar el total del pedido
        JLabel totalLabel = new JLabel("Total:");
        totalField = new JTextField(String.valueOf(ped.getPrecio()));
        totalField.setEditable(false);

        // Crear un botón para tramitar el pedido
        JButton tramitarButton = new JButton("Tramitar");
        tramitarButton.addActionListener(e -> {
               	
        	cntr.accion(Eventos.CREAR_PEDIDO, ped);       	
            System.exit(0);
        });

        // Agregar componentes al panel del carrito
        panel.add(volverAtrasButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(eliminarPanel, BorderLayout.EAST);
        bottomPanel.add(totalLabel);
        bottomPanel.add(totalField);
        bottomPanel.add(new JLabel()); // Espacio en blanco
        bottomPanel.add(tramitarButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Agregar el panel al JFrame y hacerlo visible
        carritoFrame.add(panel);
        carritoFrame.setVisible(true);
    }

    @Override
    public void actualizar(int evento, Object datos) {
        // Actualizar la GUI del carrito si es necesario
    }
}
