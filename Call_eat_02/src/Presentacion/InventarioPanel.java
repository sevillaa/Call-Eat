package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;

public class InventarioPanel extends JPanel{
	
	private JPanel panelContenedor;
    private CardLayout cardLayout;
	private Controlador controlador;
	
	public InventarioPanel(JPanel panelContenedor, CardLayout cardLayout,Controlador controlador) {
		this.panelContenedor=panelContenedor;
		this.cardLayout=cardLayout;
		this.controlador=controlador;
		initComponents();
	}
	
	private void initComponents() {
		this.setSize(1000, 500);
		this.setLayout(new BorderLayout());
		JLabel tituloPlantilla = new JLabel("Gestión de Inventario", SwingConstants.CENTER);
        tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(tituloPlantilla, BorderLayout.NORTH);
        
        
        List<TransferIngrediente> ingredientes = controlador.listaIngredientes();
        
        // Crear la tabla con los empleados
        String[] columnas = {"Nombre de producto", "Cantidad"};
        Object[][] datosIngredientes = new Object[ingredientes.size()][2];

        for (int i = 0; i < ingredientes.size(); i++) {
            TransferIngrediente ingrediente = ingredientes.get(i);
            datosIngredientes[i][0] = ingrediente.getNombre();
            datosIngredientes[i][1]= ingrediente.getCantidad();
        }
        JTable tabla = new JTable(datosIngredientes, columnas);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        this.add(scrollTabla, BorderLayout.CENTER);
        
        
        JPanel panelBotones = new JPanel(new FlowLayout());

        
        /////////////////////////BOTON DE CREAR UN NUEVO INGREDIENTE///////////////////////
        JButton btonAnadir = new JButton("Crear");
        btonAnadir.addActionListener(e -> {
            JFrame crearFrame = new JFrame("Crear Ingrediente");
            
            crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            crearFrame.setSize(400, 300);
            crearFrame.setLocationRelativeTo(null);
            crearFrame.setVisible(true);
            
            
            
            JPanel aux = new JPanel();
            aux.setLayout(new BorderLayout());
            crearFrame.add(aux);
            
            JLabel titulo = new JLabel("Creacion de ingrediente", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            aux.add(titulo, BorderLayout.NORTH);
            
            
            JPanel centro = new JPanel(new FlowLayout());
            JPanel abajo = new JPanel(new FlowLayout());
            JLabel texto1 = new JLabel("Nombre : ");
            JTextField texto2 = new JTextField(30);
            JLabel texto3 = new JLabel("Cantidad: ");
    		JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
    		JButton ok = new JButton("Aceptar");
    		ok.addActionListener(ee ->{
    			TransferIngrediente in = new TransferIngrediente(controlador.generarCodigoRandom(),texto2.getText(), (int) spinner.getValue());
        		if(controlador.crearIngrediente(in)) {
        			JOptionPane.showMessageDialog(null, "Ingrediente creado correctamente.");
        		}
        		else {
        			JOptionPane.showMessageDialog(null, "Error.");
        		}
        		crearFrame.dispose();
        		SwingUtilities.getWindowAncestor(InventarioPanel.this).dispose();
        		GUIGestor.resetInstancia();
        		GUIGestor.getInstancia(controlador,null);
;    		});
    		JButton cancelar = new JButton("Cancelar");
    		cancelar.addActionListener(ee ->{
    			crearFrame.dispose();
    		});
            centro.add(texto1);
            centro.add(texto2);
            centro.add(texto3);
            centro.add(spinner);
            abajo.add(ok);
            abajo.add(cancelar);
            aux.add(centro,BorderLayout.CENTER);
            aux.add(abajo,BorderLayout.PAGE_END);
            
        });
/////////////////////////BOTON DE AUMENTAR UN INGREDIENTE///////////////////////
        
        JButton btnAumentar = new JButton("Aumentar");
        btnAumentar.addActionListener(e->{        	    	       	
        	JFrame crearFrame = new JFrame("Aumentar Ingrediente");
            crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            crearFrame.setSize(400, 300);
            crearFrame.setLocationRelativeTo(null);
            crearFrame.setVisible(true);
            JPanel aux = new JPanel();
            aux.setLayout(new BorderLayout());
            crearFrame.add(aux);            
            JLabel titulo = new JLabel("Aumentar suministro", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            aux.add(titulo, BorderLayout.NORTH);
        	 JPanel centro = new JPanel(new FlowLayout());
             JPanel abajo = new JPanel(new FlowLayout());
             JLabel texto1 = new JLabel("Producto : ");
             List<TransferIngrediente> combo = controlador.listaIngredientes();
             TransferIngrediente [] combolist  = new TransferIngrediente[combo.size()];         	
         	 for(int i=0;i<combo.size();i++){
         		combolist[i]=combo.get(i);
         	}
         	 JComboBox<TransferIngrediente> comboIngredientes = new JComboBox<>(combolist);
         	 comboIngredientes.setEditable(true);
             JLabel texto3 = new JLabel("Cantidad: ");
     		 JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));    		 
     		JButton ok = new JButton("Aceptar");
    		ok.addActionListener(ee ->{    			
    			TransferIngrediente in=(TransferIngrediente) comboIngredientes.getSelectedItem();    			
    			in.setCantidad(in.getCantidad()+ (int) spinner.getValue());
    			controlador.modificarIngrediente(in);  			
    			crearFrame.dispose();
        		SwingUtilities.getWindowAncestor(InventarioPanel.this).dispose();
        		GUIGestor.resetInstancia();
        		GUIGestor.getInstancia(controlador,null);
    		});
    		JButton cancelar = new JButton("Cancelar");
    		cancelar.addActionListener(ee ->{
    			crearFrame.dispose();
    		});
    		centro.add(texto1);
    		centro.add(comboIngredientes);
    		centro.add(texto3);
    		centro.add(spinner);
    		abajo.add(ok);
    		abajo.add(cancelar);
    		aux.add(centro,BorderLayout.CENTER);
    		aux.add(abajo,BorderLayout.PAGE_END);
        });
        
/////////////////////////BOTON DE ELIMINAR UN INGREDIENTE///////////////////////
        JButton btnEliminar= new JButton("Eliminar");
        btnEliminar.addActionListener(e->{
        	JFrame crearFrame = new JFrame("Eliminar Ingrediente");
            crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            crearFrame.setSize(400, 300);
            crearFrame.setLocationRelativeTo(null);
            crearFrame.setVisible(true);
            JPanel aux = new JPanel();
            aux.setLayout(new BorderLayout());
            crearFrame.add(aux);            
            JLabel titulo = new JLabel("¿Quieres eliminar este producto?", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            aux.add(titulo, BorderLayout.NORTH);
        	 JPanel centro = new JPanel(new FlowLayout());
             JPanel abajo = new JPanel(new FlowLayout());
             JLabel texto1 = new JLabel("Producto");
             List<TransferIngrediente> combo = controlador.listaIngredientes();
             TransferIngrediente [] combolist  = new TransferIngrediente[combo.size()];         	
         	 for(int i=0;i<combo.size();i++){
         		combolist[i]=combo.get(i);
         	}
         	 JComboBox<TransferIngrediente> comboIngredientes = new JComboBox<>(combolist);
         	 comboIngredientes.setEditable(true);   		 
     		JButton ok = new JButton("Aceptar");
    		ok.addActionListener(ee ->{    			
    			TransferIngrediente in=(TransferIngrediente) comboIngredientes.getSelectedItem();
    			controlador.eliminarIngrediente(in);
    			crearFrame.dispose();
        		SwingUtilities.getWindowAncestor(InventarioPanel.this).dispose();
        		GUIGestor.resetInstancia();
        		GUIGestor.getInstancia(controlador,null);
    		});
    		JButton cancelar = new JButton("Cancelar");
    		cancelar.addActionListener(ee ->{
    			crearFrame.dispose();
    		});
    		centro.add(texto1);
    		centro.add(comboIngredientes);
    		abajo.add(ok);
    		abajo.add(cancelar);
    		aux.add(centro,BorderLayout.CENTER);
    		aux.add(abajo,BorderLayout.PAGE_END);
        });
        
/////////////////////////BOTON DE REGRESAR EL MENU DEL GESTOR///////////////////////
        JButton btnAtras = new JButton("Atrás");
        btnAtras.addActionListener(ev -> {
        	cardLayout.show(panelContenedor, "menu");
        });

        panelBotones.add(btonAnadir);
        panelBotones.add(btnAumentar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAtras);

        this.add(panelBotones, BorderLayout.SOUTH);
	}
}
