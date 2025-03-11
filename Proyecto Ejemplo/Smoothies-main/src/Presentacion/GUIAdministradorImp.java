package Presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import javax.swing.table.TableModel;

import Negocio.TransferPedido;
import Negocio.TransferProducto;

public class GUIAdministradorImp extends GUIAdministrador {
	private Controlador cntr  ; 
	private Object datos  ;
	private int pos; 
	private  JFrame menuFrame ; 
	private  JFrame Frame ;

	
	public GUIAdministradorImp(Controlador contr ) {
		this.cntr = contr  ; 
		
		
		menuFrame = new JFrame("Menú");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800, 400); // Tamaño del menú
        menuFrame.setLocationRelativeTo(null); // Centrar en la pantalla


        // Crear un panel para contener los botones del menú
        JPanel menuPanel = new JPanel(new BorderLayout());
     // Crear un panel para los botones principales del menú
        JPanel mainButtonsPanel = new JPanel(new GridLayout(1, 2));

        // Crear botones para las opciones principales del menú
        JButton botonPedidos = new JButton("Pedidos");
        JButton botonstock= new JButton("Stock");
        JButton botonAñadir = new JButton("Añadir") ; 
        mainButtonsPanel.add(botonPedidos);
        mainButtonsPanel.add(botonstock);
        mainButtonsPanel.add(botonAñadir);

        // Agregar el panel de los botones principales al panel del menú en la parte central
        menuPanel.add(mainButtonsPanel, BorderLayout.CENTER);

        // Crear un panel para contener los botones adicionales
        JPanel additionalButtonsPanel = new JPanel(new GridLayout(1, 2));

        // Crear botones adicionales para salir y para el carrito
        JButton salirButton = new JButton("Salir");
        
        // Agregar oyentes de evento a los botones adicionales
        salirButton.addActionListener(e -> {
            // Lógica para cerrar el programa al presionar el botón "Salir"
            System.exit(0);
        });

        botonPedidos.addActionListener(e -> {
            // Obtener la lista de pedidos del controlador
        	 Iterator<TransferPedido> listaPedidos = cntr.obtenerIteradorLista("pedidos",false);
          	
        	 DefaultListModel<String> pedListModel = new DefaultListModel<>();
             JList<String> listaPedidosJList = new JList<>(pedListModel) ; 
             listaPedidosJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
             JScrollPane scrollPane = new JScrollPane(listaPedidosJList);
             scrollPane.setPreferredSize(new Dimension(200, 200));
             
            while(listaPedidos.hasNext()) {
                TransferPedido pedido = listaPedidos.next();
                // Formatear la representación del pedido como desees
                String pedidoStr = "ID: " + pedido.getIdPedido() + " - Batidos: " + pedido.getBatidos();
                pedListModel.addElement(pedidoStr);
              
            }
            
         

  
            Frame = new JFrame () ;
            Frame.setSize(550, 400); // Tamaño del menú
            Frame.setLocationRelativeTo(null);
            JPanel jp = new JPanel() ; 
            jp.add(listaPedidosJList) ; 
            JButton listoButton = new JButton("Hecho");
            listoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = listaPedidosJList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        if(cntr.eliminarPedido(pedListModel.elementAt(selectedIndex)))  pedListModel.remove(selectedIndex);
                        
                 
                    }
                }
            });
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(listoButton);

            Frame.getContentPane().setLayout(new BorderLayout());
            Frame.getContentPane().add(jp, BorderLayout.CENTER);
           Frame. getContentPane().add(buttonPanel, BorderLayout.SOUTH);
            Frame.setVisible(true) ; 
           
        });
        
        botonstock.addActionListener(e->{
        	 List<String> columns = new ArrayList<String>();
             List<String[]> values = new ArrayList<String[]>();
             Iterator<TransferProducto> listaIngredientes = cntr.obtenerIteradorLista("ingredientes",false);
         	
             columns.add("Ingrediente");
             columns.add("Estado");
           

             
             while(listaIngredientes.hasNext() && listaIngredientes != null) {
            	 TransferProducto ing  = listaIngredientes.next() ; 
            	 String nombre = ing.getNombre() ; 
            	 String ingdisp = "Activo"  ; 
                 if(!ing.getDisp()) {
                 	ingdisp = "Desactivado" ;
                 }
            	 values.add(new String[] {nombre,ingdisp}); 
             }
          
            
             TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());

              JTable table = new JTable(tableModel);
              table.getColumn("Estado").setCellRenderer(new ButtonRenderer());
              table.getColumn("Estado").setCellEditor(new ButtonEditor(new JCheckBox()));
      
//             
            
            // Mostrar la lista de pedidos en un JOptionPane
             Frame = new JFrame () ;
             Frame.setSize(550, 400); // Tamaño del menú
             Frame.setLocationRelativeTo(null);
             JPanel jp = new JPanel() ; 
             jp.add(new JScrollPane(table)) ; 
           
             Frame.getContentPane().add(new JScrollPane(jp)) ; 
             Frame.setVisible(true) ; 
           // jp.showMessageDialog(this.panel, new JScrollPane(table), "Lista de ingredientes", JOptionPane.PLAIN_MESSAGE);
        	//sacar lista ingredienetes
            
        
        });
        botonAñadir.addActionListener(e -> {
        	JFrame añadirFrame= new JFrame () ; 
            
        	añadirFrame.setSize(800, 400); // Tamaño del menú
        	añadirFrame.setLocationRelativeTo(null);
        	
        	JPanel jp= mostrarPanel() ; 
           
        	añadirFrame.getContentPane().add(new JScrollPane(jp)) ; 
        	añadirFrame.setVisible(true) ; 
        	
        	
        	
        }) ; 


        

        // Agregar los botones adicionales al panel de botones adicionales
        additionalButtonsPanel.add(salirButton);
      
        // Agregar el panel de botones adicionales al panel del menú en la parte inferior
        menuPanel.add(additionalButtonsPanel, BorderLayout.SOUTH);
 
        // Agregar el panel del menú al JFrame
        menuFrame.getContentPane().add(menuPanel);
        menuFrame.setVisible(true);
	}
	private JPanel mostrarPanel() {
	
		JPanel jp = new JPanel(new GridLayout(1, 2)) ; 
        JButton Ingrediente = new JButton("Ingrediente") ; 
        JButton Smoothie = new JButton("Smoothie") ;
        
       
        Ingrediente.addActionListener( e ->{
        	JFrame Frame1 = new JFrame() ; 
        	JPanel accion  = mostrarPanelIngredientes(Frame1) ; 
        	Frame1.add(accion) ; 
        	
        	Frame1.setSize(300, 150);
        	Frame1.setLocationRelativeTo(null);
        	Frame1.setVisible(true) ; 
        } ) ;
        Smoothie.addActionListener(e ->{
        	JFrame frame2 = new JFrame() ; 
        	JPanel accion2 = mostrarPanelSmoothie() ; 
        	frame2.add(accion2) ;
     	   
        }) ;
       
        jp.add(Ingrediente) ;
        jp.add(Smoothie) ; 
        
        return jp ; 
	}

	private JPanel mostrarPanelIngredientes(JFrame frame2) {
		JPanel Ingredientes = new JPanel() ; 
		
		JTextField nombreField = new JTextField(20);
        JTextField caloríasField = new JTextField(20);
        
		Ingredientes.add(new JLabel("Nombre:"));
		Ingredientes.add(nombreField);
		Ingredientes.add(new JLabel("Calorías:"));
		Ingredientes.add(caloríasField);
       
		
   
        
        JButton Aceptar = new JButton("Aceptar") ; 
        Aceptar.addActionListener(e ->{
        	String nombre = nombreField.getText();
            String calorías = caloríasField.getText();
         // Utilizar el controlador para añadir un cliente
            HashMap<String, String> datos = new HashMap<>();
            datos.put("nombre", nombre);
            datos.put("calorías", calorías);
        	 cntr.accion(Eventos.AÑADIR_INGREDIENTES, datos);
        	 frame2.setVisible(false) ;
        	 
        
        });
      
       
        Ingredientes.add(Aceptar) ; 
        Ingredientes.setVisible(true);
        return Ingredientes ; 
      
	}

	private JPanel  mostrarPanelSmoothie() {
		JPanel smoothie = new JPanel() ; 
		
		return smoothie ; 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizar(int evento, Object datos) {
		// TODO Auto-generated method stub
		
	}
	
	class ButtonRenderer extends JButton implements TableCellRenderer {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ButtonRenderer() {
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        if (isSelected) {
	            setForeground(table.getSelectionForeground());
	            setBackground(table.getSelectionBackground());
	        } else {
	            setForeground(table.getForeground());
	            setBackground(UIManager.getColor("Button.background"));
	        }
	        setText((value == null) ? "" : value.toString());
	        return this;
	    }
	}

	class ButtonEditor extends DefaultCellEditor {

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected JButton button;
	    private String label;
	    private boolean isPushed;

	    public ButtonEditor(JCheckBox checkBox) {
	        super(checkBox);
	        button = new JButton();
	        button.setOpaque(true);
	        button.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                fireEditingStopped();
	                
	                
	            }

				
	        });
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value,
	            boolean isSelected, int row, int column) {
	    	String disponibilidad ; 
	    	String ing ;
	    
	        if (isSelected) {
	            button.setForeground(table.getSelectionForeground());
	            button.setBackground(table.getSelectionBackground());
	        } else {
	            button.setForeground(table.getForeground());
	            button.setBackground(table.getBackground());
	        }
	        label = (value == null) ? "" : value.toString();
	        pos = row;
	        ing=(String) table.getValueAt(pos,0); 
	        disponibilidad  = (String ) value ; 
	        datos = ing + "," + disponibilidad ; 
	        button.setText(label);
	        isPushed = true;
	        return button;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        if (isPushed) {
	        	int respuesta =JOptionPane.showConfirmDialog(button, "¿Quieres cambiar de estado?",null, JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                	cntr.accion(Eventos.CAMBIAR_DISPONIBILIDAD,datos);
                	Frame.setVisible(false);
                } 
	        }
	        isPushed = false;
	        return label;
	    }

	    @Override
	    public boolean stopCellEditing() {
	        isPushed = false;
	        return super.stopCellEditing();
	    }
	}
}
