package Presentacion;
import javax.imageio.ImageIO;
import javax.swing.*;

import Negocio.TransferPedido;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GUIClientesImp extends GUIClientes {
//   
	
	private JFrame loginFrame;
	private JButton atrasButton; // Botón de "Atrás" para acceso y registro
	private JPanel currentPanel; // Para realizar un seguimiento del panel actual
	private JPanel panel; // Variable de instancia para el panel principal
	private Controlador cntr; // Agregar referencia al controlador

	private TransferPedido ped;
	
    public GUIClientesImp(Controlador controlador,Object datos) {
        this.cntr = controlador;
        this.ped = new TransferPedido();
        ped.setIdUsuario(controlador.buscarIdCliente(datos));
        ped.setId((int) (Math.random() * 900000) + 100000);
     
        // Crear un nuevo JFrame para el menú
        JFrame menuFrame = new JFrame("Menú");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800, 400); // Tamaño del menú
        menuFrame.setLocationRelativeTo(null); // Centrar en la pantalla

        // Crear un panel para contener los botones del menú
        JPanel menuPanel = new JPanel(new BorderLayout());

        // Crear un panel para los botones principales del menú
        JPanel mainButtonsPanel = new JPanel(new GridLayout(1, 2));

        // Crear botones para las opciones principales del menú
        JButton opcion1Button = new JButton("Menu Smoothies");
        JButton opcion2Button = new JButton("Smoothies Personalizados");

        // Establecer el ícono para cada botón
        ImageIcon fotoMenu = new ImageIcon("archivos/menu.jpg");
        ImageIcon fotoPersonalizado = new ImageIcon("archivos/personalizado.jpg");

        opcion1Button.setIcon(fotoMenu);
        opcion2Button.setIcon(fotoPersonalizado);

        // Alinear el texto del botón en la parte superior
        opcion1Button.setHorizontalTextPosition(SwingConstants.CENTER);
        opcion1Button.setVerticalTextPosition(SwingConstants.TOP);

        opcion2Button.setHorizontalTextPosition(SwingConstants.CENTER);
        opcion2Button.setVerticalTextPosition(SwingConstants.TOP);

        
        opcion1Button.addActionListener(e -> {

        	GUIMenuImp guiMenu = new GUIMenuImp(cntr,datos,ped);
            //GUI Para pedir batidos
        });

        opcion2Button.addActionListener(e -> {
            // Lógica para Smoothies Personalizados
     	   
     	    GUIProductosImp guiProductos = new GUIProductosImp(cntr,ped);

        });

        // Agregar los botones principales al panel de los botones principales
        mainButtonsPanel.add(opcion1Button);
        mainButtonsPanel.add(opcion2Button);

        // Agregar el panel de los botones principales al panel del menú en la parte central
        menuPanel.add(mainButtonsPanel, BorderLayout.CENTER);

        // Crear un panel para contener los botones adicionales
        JPanel additionalButtonsPanel = new JPanel(new GridLayout(1, 2));

        JButton salirButton = new JButton("Salir");
        JButton carritoButton = new JButton("Carrito");
        JButton CuentaButton = new JButton("Cuenta") ; 

        salirButton.addActionListener(e -> {
            // Lógica para cerrar el programa al presionar el botón "Salir"
            System.exit(0);
        });
       

        CuentaButton.addActionListener(e -> {
            // Crear un nuevo panel para las opciones de la cuenta con BorderLayout
            JPanel cuentaPanel = new JPanel(new BorderLayout());

            // Crear botón para volver atrás
            JButton volverAtrasButton = new JButton("Volver Atrás");
            volverAtrasButton.addActionListener(a -> {
                // Remplazar el panel actual del menú con el panel de los botones principales
                menuPanel.remove(cuentaPanel);
                menuPanel.add(mainButtonsPanel, BorderLayout.CENTER);
                menuPanel.revalidate();
                menuPanel.repaint();
            });

            // Crear un panel para los botones de las opciones de la cuenta
            JPanel botonesCuentaPanel = new JPanel(new GridLayout(2, 1));

            // Crear botones para las opciones de la cuenta
            JButton verPedidosButton = new JButton("Ver Pedidos");
            JButton cerrarCuentaButton = new JButton("Cerrar Cuenta");

            verPedidosButton.addActionListener(e1 -> {
            	String idCliente = cntr.buscarIdCliente(datos);
                // Obtener la lista de pedidos del usuario actual del controlador
                Iterator<TransferPedido> listaPedidos = controlador.obtenerIteradorLista(idCliente,false); 
                
                // Crear un arreglo de strings para almacenar la representación de los pedidos
                String[] pedidosArray = new String[100];
                int i = 0  ; 
                while(listaPedidos.hasNext()) {
                    TransferPedido pedidoUsuario = listaPedidos.next();
                    // Formatear la representación del pedido como desees
                    String pedidoStr = "ID: " + pedidoUsuario.getIdPedido() + " - Batidos: " + pedidoUsuario.getBatidos();
                    pedidosArray[i] = pedidoStr;
                }
                
                // Crear una JList con los pedidos
                JList<String> listaPedidosJList = new JList<>(pedidosArray);
                
                // Mostrar la lista de pedidos en un JOptionPane
                JOptionPane.showMessageDialog(null, new JScrollPane(listaPedidosJList), "Lista de Pedidos", JOptionPane.PLAIN_MESSAGE);
            });


            cerrarCuentaButton.addActionListener(e2 -> {
                // Mostrar una ventana de confirmación
                int respuesta = JOptionPane.showConfirmDialog(menuFrame,
                        "¿Estás seguro de que quieres cerrar tu cuenta?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (respuesta == JOptionPane.YES_OPTION) {
                    controlador.accion(Eventos.ELIMINAR_CLIENTE,datos);
                    System.exit(0);   
                } else {
                    // El usuario ha cancelado la operación
                    JOptionPane.showMessageDialog(menuFrame, "Operación cancelada.");
                }
            });

            // Agregar los botones al panel de las opciones de la cuenta
            botonesCuentaPanel.add(verPedidosButton);
            botonesCuentaPanel.add(cerrarCuentaButton);

            // Agregar el panel de los botones de las opciones de la cuenta al panel de la cuenta en el centro
            cuentaPanel.add(botonesCuentaPanel, BorderLayout.CENTER);

            // Agregar el botón "Volver Atrás" al panel de la cuenta en la región sur
            cuentaPanel.add(volverAtrasButton, BorderLayout.SOUTH);

            // Remplazar el panel actual del menú con el panel de la cuenta
            menuPanel.remove(mainButtonsPanel);
            menuPanel.add(cuentaPanel, BorderLayout.CENTER);
            menuPanel.revalidate();
            menuPanel.repaint();
        });




        carritoButton.addActionListener(e -> {
        	
        	actualizar(Eventos.VER_CARRITO, datos);
        });

        // Agregar los botones adicionales al panel de botones adicionales
        additionalButtonsPanel.add(salirButton);
        additionalButtonsPanel.add(CuentaButton) ; 
        additionalButtonsPanel.add(carritoButton);
    

        // Agregar el panel de botones adicionales al panel del menú en la parte inferior
        menuPanel.add(additionalButtonsPanel, BorderLayout.SOUTH);

        // Agregar el panel del menú al JFrame
        menuFrame.getContentPane().add(menuPanel);

        // Hacer visible el menú
        menuFrame.setVisible(true);
    }

   

    @Override
    public void actualizar(int evento, Object datos) {
    	switch (evento) {
        	case (Eventos.VER_CARRITO): {
        		GUICarritoImp guiCarrito = new GUICarritoImp(cntr,datos,ped); 		
        	break;
        	}
        }
    }
}
