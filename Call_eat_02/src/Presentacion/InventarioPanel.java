package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
		//this.setSize(1000, 500);
		this.setLayout(new BorderLayout());
		JPanel panelSuperior = new JPanel(new BorderLayout());
		JPanel panelLista = new JPanel(new BorderLayout());
		panelLista.setBackground(Color.white);
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS)); 
		//panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); 
		
		JLabel tituloPlantilla = new JLabel("Inventario", SwingConstants.CENTER);
        tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
        panelLista.add(tituloPlantilla, BorderLayout.NORTH);
        
        
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
        
        
        TableColumnModel columnModel = tabla.getColumnModel();
        TableColumn col1 = columnModel.getColumn(0); // Columna 1 (Nombre de producto)
        TableColumn col2 = columnModel.getColumn(1); // Columna 2 (Cantidad)
        
        //col1.setPreferredWidth(200); // Ajusta el tamaño de la primera columna (Nombre de producto)
        col2.setPreferredWidth(60); // Ajusta el tamaño de la segunda columna (Cantidad)
        col2.setMinWidth(40);
        col2.setMaxWidth(100);
        // Centrar el contenido de la columna "Cantidad"
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);  // Centra el contenido
        col2.setCellRenderer(renderer);  // Aplica el renderizador a la columna "Cantidad"
        
        
        
        
        
        JScrollPane scrollTabla = new JScrollPane(tabla);
        panelLista.add(scrollTabla, BorderLayout.CENTER);
        this.add(panelLista,BorderLayout.CENTER);
        
        //JPanel panelBotones = new JPanel(new FlowLayout());

        Dimension botonDimension = new Dimension(150, 40); // Tamaño común para los botones
        Dimension jtextYcomco = new Dimension(200,25);
        /////////////////////////BOTON DE CREAR UN NUEVO INGREDIENTE///////////////////////
        JButton btonAnadir = new JButton("Crear");
        btonAnadir.setPreferredSize(botonDimension);
        btonAnadir.setMinimumSize(botonDimension);
        btonAnadir.setMaximumSize(botonDimension);
        btonAnadir.setBackground(new Color(144, 238, 144)); 
        btonAnadir.setForeground(Color.WHITE);
        btonAnadir.setFont(new Font("Arial",Font.BOLD,15));
        btonAnadir.addActionListener(e -> {
            JFrame crearFrame = new JFrame("Crear Ingrediente");
            crearFrame.setResizable(false);
            crearFrame.setSize(400, 200);
            crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            crearFrame.setLocationRelativeTo(null);
            crearFrame.setVisible(true);
            
            
            
            JPanel aux = new JPanel();
            aux.setBackground(Color.WHITE);
            aux.setLayout(new BorderLayout());
            crearFrame.add(aux);
            
            JLabel titulo = new JLabel("Creacion de ingrediente", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            aux.add(titulo, BorderLayout.NORTH);
            
            
            JPanel centro = new JPanel();
       	 	centro.setLayout(new BoxLayout(centro,BoxLayout.Y_AXIS));
       	 	JPanel centroArriba = new JPanel();
       	 	JPanel centroAbajo= new JPanel();
       	 	centroArriba.setBackground(Color.white);
       	 	centroAbajo.setBackground(Color.white);
       	 	centro.setBackground(Color.white);
            centro.setBackground(Color.WHITE);
            
            
            JPanel abajo = new JPanel(new FlowLayout());
            abajo.setBackground(Color.WHITE);
            JLabel texto1 = new JLabel("Nombre : ");
            JTextField texto2 = new JTextField();
            texto2.setMinimumSize(jtextYcomco);
            texto2.setPreferredSize(jtextYcomco);
            texto2.setMaximumSize(jtextYcomco);
            JLabel texto3 = new JLabel("Cantidad : ");
    		JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
    		JButton ok = new JButton("Aceptar");
    		ok.setBackground(new Color(144, 238, 144));
    		ok.setFont(new Font("Arial",Font.BOLD,15));
    		ok.setForeground(Color.WHITE);
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
    		cancelar.setBackground(Color.GRAY);
    		cancelar.setFont(new Font("Arial",Font.BOLD,15));
    		cancelar.setForeground(Color.WHITE);
    		cancelar.addActionListener(ee ->{
    			crearFrame.dispose();
    		});
            centroArriba.add(texto1);
            centroArriba.add(texto2);
            //centro.add(Box.createHorizontalStrut(100));
            centroAbajo.add(texto3);
            centroAbajo.add(Box.createHorizontalStrut(115));
            centroAbajo.add(spinner);      
            centro.add(centroArriba);
            centro.add(centroAbajo);
            abajo.add(cancelar);
            abajo.add(ok);
            aux.add(centro,BorderLayout.CENTER);
            aux.add(abajo,BorderLayout.PAGE_END);
            
        });
/////////////////////////BOTON DE AUMENTAR UN INGREDIENTE///////////////////////
        
        JButton btnAumentar = new JButton("Aumentar");
        btnAumentar.setPreferredSize(botonDimension);
        btnAumentar.setMinimumSize(botonDimension);
        btnAumentar.setMaximumSize(botonDimension);
        btnAumentar.setBackground(new Color(0, 128, 0)); 
        btnAumentar.setForeground(Color.WHITE);
        btnAumentar.setFont(new Font("Arial",Font.BOLD,15));
        btnAumentar.addActionListener(e->{        	    	       	
        	JFrame crearFrame = new JFrame("Aumentar Ingrediente");
        	crearFrame.setResizable(false);
            crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            crearFrame.setSize(400, 200);
            crearFrame.setLocationRelativeTo(null);
            crearFrame.setVisible(true);
            crearFrame.setBackground(Color.white);
            
            JPanel aux = new JPanel();
            aux.setLayout(new BorderLayout());
            aux.setBackground(Color.WHITE);
            crearFrame.add(aux);            
            JLabel titulo = new JLabel("Aumentar suministro", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            aux.add(titulo, BorderLayout.NORTH);
        	 //JPanel centro = new JPanel(new FlowLayout());
        	 JPanel centro = new JPanel();
        	 centro.setLayout(new BoxLayout(centro,BoxLayout.Y_AXIS));
        	 JPanel centroArriba = new JPanel();
        	 JPanel centroAbajo= new JPanel();
        	 centroArriba.setBackground(Color.white);
        	 centroAbajo.setBackground(Color.white);
        	 centro.setBackground(Color.white);
        	 JPanel abajo = new JPanel(new FlowLayout());
        	 abajo.setBackground(Color.WHITE);
             JLabel texto1 = new JLabel("Producto : ");
             List<TransferIngrediente> combo = controlador.listaIngredientes();
             TransferIngrediente [] combolist  = new TransferIngrediente[combo.size()];         	
         	 for(int i=0;i<combo.size();i++){
         		combolist[i]=combo.get(i);
         	}
         	 JComboBox<TransferIngrediente> comboIngredientes = new JComboBox<>(combolist);
         	 comboIngredientes.setEditable(true);
         	comboIngredientes.setMinimumSize(jtextYcomco);
         	comboIngredientes.setPreferredSize(jtextYcomco);
         	comboIngredientes.setMaximumSize(jtextYcomco);
             JLabel texto3 = new JLabel("Cantidad : ");
     		 JSpinner spinner = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));    	
     		 //spinner.setMinimumSize(jtextYcomco);
     		 //spinner.setMaximumSize(jtextYcomco);
     		// spinner.setPreferredSize(jtextYcomco);
     		JButton ok = new JButton("Aceptar");
     		ok.setBackground(new Color(0, 128, 0));
    		ok.setFont(new Font("Arial",Font.BOLD,15));
    		ok.setForeground(Color.WHITE);
    		ok.addActionListener(ee ->{    			
    			TransferIngrediente in=(TransferIngrediente) comboIngredientes.getSelectedItem();    			
    			in.setCantidad(in.getCantidad()+ (int) spinner.getValue());
    			if(controlador.modificarIngrediente(in)) {
    				JOptionPane.showMessageDialog(null, "Cantidad modificada correctamente.");
    			}
    			crearFrame.dispose();
        		SwingUtilities.getWindowAncestor(InventarioPanel.this).dispose();
        		GUIGestor.resetInstancia();
        		GUIGestor.getInstancia(controlador,null);
    		});
    		JButton cancelar = new JButton("Cancelar");
    		cancelar.setBackground(Color.GRAY);
    		cancelar.setFont(new Font("Arial",Font.BOLD,15));
    		cancelar.setForeground(Color.WHITE);
    		cancelar.addActionListener(ee ->{
    			crearFrame.dispose();
    		});
    		centroArriba.setAlignmentX(CENTER_ALIGNMENT);
    		centroAbajo.setAlignmentX(CENTER_ALIGNMENT);
    		centroArriba.setOpaque(false);
    		centroAbajo.setOpaque(false);
    		centroArriba.add(texto1);
    		centroArriba.add(comboIngredientes);
    		centroAbajo.add(texto3);
    		centroAbajo.add(Box.createHorizontalStrut(114));
    		centroAbajo.add(spinner);
    		centroAbajo.add(Box.createVerticalStrut(20));
    		centro.add(centroArriba);
    		centro.add(centroAbajo);
    		abajo.add(cancelar);
    		abajo.add(ok);
    		aux.add(centro,BorderLayout.CENTER);
    		aux.add(abajo,BorderLayout.PAGE_END);
        });
        
/////////////////////////BOTON DE ELIMINAR UN INGREDIENTE///////////////////////
        JButton btnEliminar= new JButton("Eliminar");
        btnEliminar.setPreferredSize(botonDimension);
        btnEliminar.setMinimumSize(botonDimension);
        btnEliminar.setMaximumSize(botonDimension);
        btnEliminar.setBackground(new Color(255, 69, 58));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial",Font.BOLD,15));
        
        btnEliminar.addActionListener(e->{
        	JFrame crearFrame = new JFrame("Eliminar Ingrediente");
        	crearFrame.setResizable(false);
            crearFrame.setSize(400, 150);
            crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            crearFrame.setLocationRelativeTo(null);
            crearFrame.setVisible(true);
            JPanel aux = new JPanel();
            aux.setLayout(new BorderLayout());
            crearFrame.add(aux);         
            aux.setBackground(Color.white);
            JLabel titulo = new JLabel("¿Quieres eliminar este producto?", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));
            aux.add(titulo, BorderLayout.NORTH);
        	 JPanel centro = new JPanel(new FlowLayout());
             JPanel abajo = new JPanel(new FlowLayout());
             centro.setBackground(Color.WHITE);
             abajo.setBackground(Color.WHITE);
             JLabel texto1 = new JLabel("Producto");
             List<TransferIngrediente> combo = controlador.listaIngredientes();
             TransferIngrediente [] combolist  = new TransferIngrediente[combo.size()];         	
         	 for(int i=0;i<combo.size();i++){
         		combolist[i]=combo.get(i);
         	}
         	 JComboBox<TransferIngrediente> comboIngredientes = new JComboBox<>(combolist);
         	 comboIngredientes.setEditable(true);   		 
     		JButton ok = new JButton("Aceptar");
     		ok.setBackground(new Color(255, 69, 58));
    		ok.setFont(new Font("Arial",Font.BOLD,15));
    		ok.setForeground(Color.WHITE);
    		ok.addActionListener(ee ->{    			
    			TransferIngrediente in=(TransferIngrediente) comboIngredientes.getSelectedItem();
    			if(controlador.eliminarIngrediente(in)) {
    				JOptionPane.showMessageDialog(null, "Ingrediente eliminado correctamente.");
    			}
    			crearFrame.dispose();
        		SwingUtilities.getWindowAncestor(InventarioPanel.this).dispose();
        		GUIGestor.resetInstancia();
        		GUIGestor.getInstancia(controlador,null);
    		});
    		JButton cancelar = new JButton("Cancelar");
    		cancelar.setBackground(Color.GRAY);
    		cancelar.setFont(new Font("Arial",Font.BOLD,15));
    		cancelar.setForeground(Color.WHITE);
    		cancelar.addActionListener(ee ->{
    			crearFrame.dispose();
    		});
    		centro.add(texto1);
    		centro.add(comboIngredientes);
    		abajo.add(cancelar);
    		abajo.add(ok);
    		aux.add(centro,BorderLayout.CENTER);
    		aux.add(abajo,BorderLayout.PAGE_END);
        });
        
/////////////////////////BOTON DE REGRESAR EL MENU DEL GESTOR///////////////////////
        ImageIcon volverIcono = new ImageIcon("resources/botonAtras.png");
        Image volverIconoImagen = volverIcono.getImage().getScaledInstance(35,35, Image.SCALE_SMOOTH);
        JButton btnVolver = new JButton("Volver",new ImageIcon(volverIconoImagen));
        btnVolver.setFont(new Font("Arial",Font.BOLD,10));
        btnVolver.setForeground(Color.white);
        btnVolver.setPreferredSize(new Dimension(110,20));
        btnVolver.setContentAreaFilled(false); // elimina el fondo estirado
        btnVolver.setBorderPainted(false);    // elimina el borde
        btnVolver.setFocusPainted(false);     // quita ese borde de foco azul
        btnVolver.setHorizontalAlignment(SwingConstants.CENTER);
        btnVolver.setVerticalAlignment(SwingConstants.CENTER);
        btnVolver.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVolver.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnVolver.addActionListener(ev -> {
        	cardLayout.show(panelContenedor, "menu");
        });
        
        ImageIcon logo = new ImageIcon("resources/logo.png"); 
        Image locoImagenEscalado = logo.getImage().getScaledInstance(63,63, Image.SCALE_SMOOTH);
        ImageIcon logoIconoEscalado=new ImageIcon(locoImagenEscalado);
        JLabel etiquetaImagen = new JLabel(logoIconoEscalado);
        
        panelSuperior.add(btnVolver, BorderLayout.LINE_START);
        panelSuperior.setBackground(new Color(100, 180, 255));
        panelSuperior.add(etiquetaImagen,BorderLayout.LINE_END);
        panelBotones.add(Box.createVerticalStrut(70));  // Espacio superior
        panelBotones.add(btonAnadir); // Primer botón
        panelBotones.add(Box.createVerticalStrut(10));  // Espacio entre botones
        panelBotones.add(btnAumentar); // Segundo botón
        panelBotones.add(Box.createVerticalStrut(10));  // Espacio entre botones
        panelBotones.add(btnEliminar); // Tercer botón
        
        this.add(panelSuperior,BorderLayout.PAGE_START);
        this.add(panelBotones, BorderLayout.LINE_END);
	}
}
