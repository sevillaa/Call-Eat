package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Negocio.TransferIngrediente;
import Negocio.TransferPlato;

public class PlatillosPanel extends JPanel {

	private JPanel panelContenedor;
	private CardLayout cardLayout;
	Controlador controlador;

	private JPanel panelPlatillos;
	private List<JButton> botonesPlatillos = new ArrayList<>();
	private JButton botonActivo = null;
	private JTextField campoBusqueda;

	public PlatillosPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
		this.panelContenedor = panelContenedor;
		this.cardLayout = cardLayout;
		this.controlador = controlador;
		initGUI();
	}

	public void initGUI() {
		this.setLayout(new BorderLayout());

		// Panel superior (Panel azul con nombre de la aplicación)
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.setBackground(new Color(100, 180, 255));
		panelSuperior.setPreferredSize(new Dimension(0, 63));

		JLabel titulo = new JLabel("Call&Eat", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 25));
		titulo.setForeground(Color.WHITE);
		panelSuperior.add(titulo, BorderLayout.CENTER);

		ImageIcon icon = new ImageIcon("resources/botonAtras.png");
		icon = new ImageIcon(icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
		JButton botonVolver = new JButton("Volver", icon);
		botonVolver.setFont(new Font("Arial", Font.BOLD, 10));
		botonVolver.setForeground(Color.white);
		botonVolver.setBorderPainted(false);
		botonVolver.setFocusPainted(false);
		botonVolver.setContentAreaFilled(false);
		botonVolver.addActionListener(e -> {
			cardLayout.show(panelContenedor, "menu");
		});
		panelSuperior.add(botonVolver, BorderLayout.WEST);

		this.add(panelSuperior, BorderLayout.PAGE_START);

		// Panel central (Panel con los platillos)
		panelPlatillos = new JPanel(new GridLayout(0, 4, 30, 30));

		JScrollPane scrollPanelPlatillos = new JScrollPane(panelPlatillos);
		customizeScrollBar(scrollPanelPlatillos);
		this.add(scrollPanelPlatillos, BorderLayout.CENTER);

		// Creación botones de platillo
		for (TransferPlato plato : controlador.obtenerPlatos()) {
			JButton botonPlato = new JButton();
			ImageIcon platoIcon = new ImageIcon(plato.getIconPath());
			botonPlato.setText(plato.getNombre());
			platoIcon = new ImageIcon(platoIcon.getImage().getScaledInstance(1000, 1000, Image.SCALE_SMOOTH));
			botonPlato.setIcon(platoIcon);
			botonPlato.putClientProperty("categoria", plato.getCategoria());
			botonPlato.addActionListener(e -> {
				mostrarDetallesPlato(plato);
			});
			botonesPlatillos.add(botonPlato);
			panelPlatillos.add(botonPlato);
		}

		// Ajuste dinámico de tamaño de botones al redimensionar
		scrollPanelPlatillos.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int alturaBoton = scrollPanelPlatillos.getViewport().getHeight() / 3;
				int anchuraBoton = (scrollPanelPlatillos.getViewport().getWidth() - 100) / 4;

				for (JButton boton : botonesPlatillos) {
					// Ajustar el tamaño del icono según el tamaño del botón
					ImageIcon icon = (ImageIcon) boton.getIcon();
					if (icon != null) {
						icon = new ImageIcon(
								icon.getImage().getScaledInstance(anchuraBoton + 100, alturaBoton, Image.SCALE_SMOOTH));
						boton.setIcon(icon);
					}

					boton.setPreferredSize(new Dimension(anchuraBoton, alturaBoton));
					boton.setMaximumSize(new Dimension(anchuraBoton, alturaBoton));
				}
				panelPlatillos.revalidate();
			}
		});

		// Panel Inferior (Panel para buscar, filtrar por categorías y añadir nuevo platillo)
		JPanel panelCategorias = new JPanel(new GridLayout(1, 6));

		// Crear el campo de búsqueda
		JPanel panelBusqueda = new JPanel(new BorderLayout());
		panelBusqueda.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, new Color(100, 180, 255)));
		panelBusqueda.setBackground(Color.WHITE);
		ImageIcon lupaIcon = new ImageIcon("resources/Botones_menu_gestor/lupa.png");
		lupaIcon = new ImageIcon(lupaIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		JLabel labelLupa = new JLabel(lupaIcon);
		labelLupa.setOpaque(true);
		labelLupa.setBackground(new Color(100, 180, 255));
		labelLupa.setBorder(BorderFactory.createMatteBorder(0, 6, 0, 12, new Color(100, 180, 255)));
		campoBusqueda = new JTextField();
		campoBusqueda.setBorder(BorderFactory.createEmptyBorder());
		campoBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filtrar(); // Filtrar cuando se escribe
			}
		});
		panelBusqueda.add(labelLupa, BorderLayout.WEST);
		panelBusqueda.add(campoBusqueda, BorderLayout.CENTER);
		panelCategorias.add(panelBusqueda);

		// Crear los botones de categoría
		for (String categoria : new String[] { "Todos", "Platos", "Bebidas", "Postres" }) {
			JButton botonCategoria = new JButton(categoria);
			if (categoria.equals("Todos")) {
				botonActivo = botonCategoria;
			}
			botonCategoria.setForeground(Color.WHITE);
			botonCategoria.setBorder(BorderFactory.createEmptyBorder());
			botonCategoria.setFocusPainted(false);
			botonCategoria.setBackground(new Color(100, 180, 255));
			botonCategoria.addActionListener(e -> {
				botonActivo.setBorder(BorderFactory.createEmptyBorder());
				botonCategoria.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(255, 255, 255)));
				botonActivo = botonCategoria;
				filtrar();
			});
			botonCategoria.setPreferredSize(new Dimension(0, 30));
			panelCategorias.add(botonCategoria);
		}
		
		// Crear el boton de agregar plato
		JButton botonAgregarPlato = new JButton("Agregar nuevo plato");
		botonAgregarPlato.setForeground(Color.WHITE);
		botonAgregarPlato.setBorder(BorderFactory.createEmptyBorder());
		botonAgregarPlato.setFocusPainted(false);
		botonAgregarPlato.setBackground(new Color(100, 180, 255));
		panelCategorias.add(botonAgregarPlato);
		this.add(panelCategorias, BorderLayout.PAGE_END);
		botonAgregarPlato.addActionListener(e->{
			JFrame frame = new JFrame("Crear Plato");
			frame.setResizable(false);
			frame.setSize(420, 400);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
            frame.setBackground(Color.BLACK);

            JPanel panelPrincipal = new JPanel(new BorderLayout());
            frame.add(panelPrincipal);
            panelPrincipal.setBackground(Color.white);
            
            JLabel nombre = new JLabel("Creacion de Plato", SwingConstants.CENTER);
            nombre.setFont(new Font("Arial", Font.BOLD, 20));
            panelPrincipal.add(nombre, BorderLayout.NORTH);
            
            JPanel panelCentral = new JPanel();
            panelCentral.setLayout(new BoxLayout(panelCentral,BoxLayout.Y_AXIS));
       	 	JPanel panelNombre = new JPanel();
       	 	JPanel panelPrecio= new JPanel();
       	 	JPanel panelTipo = new JPanel();
    	 	JPanel panelBuscarIngrediente= new JPanel();
    	 	JPanel panelTabla = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	 	JPanel panelBotonesAbajo= new JPanel(new FlowLayout(FlowLayout.CENTER));
    	 	
    	 	panelNombre.setBackground(Color.white);
    	 	panelPrecio.setBackground(Color.white);
    	 	panelTipo.setBackground(Color.white);
    	 	panelBuscarIngrediente.setBackground(Color.white);
    	 	panelTabla.setBackground(Color.white);
    	 	panelBotonesAbajo.setBackground(Color.white);
    	 	panelCentral.setBackground(Color.WHITE);
            
            
            
            //Implementacion de paneles del panelCentro(su jlabel y su jtext o jcombobox)
            Dimension dimensionJtextNombre = new Dimension(200,25);
            Dimension dimensionJtextPrecio = new Dimension(88,25);
            Dimension dimensionJComboIngredientes = new Dimension(190,25);
            Dimension dimensionSpinnerCantidad=new Dimension(60,25);
            //panel nombre
            JLabel nombreLabel = new JLabel("Nombre : ");
            JTextField jtextNombre = new JTextField();
            jtextNombre.setMinimumSize(dimensionJtextNombre);
            jtextNombre.setPreferredSize(dimensionJtextNombre);
            jtextNombre.setMaximumSize(dimensionJtextNombre);
            panelNombre.add(nombreLabel);
            panelNombre.add(jtextNombre);
           // panel precio
            JLabel labelPrecio = new JLabel("Precio :                        ");
            JTextField jtextPrecio = new JTextField();
            jtextPrecio.setMinimumSize(dimensionJtextPrecio);
            jtextPrecio.setPreferredSize(dimensionJtextPrecio);
            jtextPrecio.setMaximumSize(dimensionJtextPrecio);
    		panelPrecio.add(labelPrecio);
    		panelPrecio.add(Box.createHorizontalStrut(30));
    		panelPrecio.add(jtextPrecio);
    		
    		//panel categoria
    		JLabel labelTipo=new JLabel("Tipo:   ");
    		String[] tipo=new String[1];
    		JRadioButton tipo1 = new JRadioButton("Platos");
    		tipo1.addItemListener(ee->{
    			tipo[0]="Platos";
    		});
    		JRadioButton tipo2 = new JRadioButton("Bebidas");
    		tipo2.addItemListener(ee->{
    			tipo[0]="Bebidas";
    		});
    		JRadioButton tipo3 = new JRadioButton("Postres");
    		tipo3.addItemListener(ee->{
    			tipo[0]="Postres";
    		});
    		ButtonGroup tipoGrupo = new ButtonGroup();
    		tipoGrupo.add(tipo1);
    		tipoGrupo.add(tipo2);
    		tipoGrupo.add(tipo3);
    		
    		panelTipo.add(labelTipo);
    		panelTipo.add(tipo1);
    		panelTipo.add(tipo2);
    		panelTipo.add(tipo3);
    		
    		
    		
    		
    		//panel ingredientes Table
    		String[] nombresTable= {"Ingrediente","Cantidad"};
    		List<TransferIngrediente> lista=new ArrayList<>();
    		Object[][] datosIngredientes = new Object[lista.size()][2];
    		/*
    		for (int i = 0; i < lista.size(); i++) {
    			TransferIngrediente ingrediente = lista.get(i);
    			datosIngredientes[i][0] = ingrediente.getNombre();
    			datosIngredientes[i][1] = ingrediente.getCantidad();
    		}*/
    		JTable tabla=new JTable();
    		tabla.setModel(new DefaultTableModel(datosIngredientes,nombresTable));
    		TableColumnModel columnModel = tabla.getColumnModel();
    		TableColumn col1 = columnModel.getColumn(0); // Columna 1 (Nombre de producto)
    		TableColumn col2 = columnModel.getColumn(1); // Columna 2 (Cantidad)

    		// col1.setPreferredWidth(200); // Ajusta el tamaño de la primera columna
    		// (Nombre de producto)
    		col1.setPreferredWidth(249); // Ajusta el tamaño de la segunda columna (Cantidad)
    		col1.setMinWidth(249);
    		col1.setMaxWidth(249);
    		col2.setPreferredWidth(60); // Ajusta el tamaño de la segunda columna (Cantidad)
    		col2.setMinWidth(60);
    		col2.setMaxWidth(60);
    		// Centrar el contenido de la columna "Cantidad"
    		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    		renderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra el contenido
    		col2.setCellRenderer(renderer); // Aplica el renderizador a la columna "Cantidad"
    		//col1.setCellRenderer(renderer);
    		JScrollPane scrollTabla = new JScrollPane(tabla);
    		scrollTabla.setPreferredSize(new Dimension(327, 100)); // Ajusta según necesites
    		//tabla.setPreferredScrollableViewportSize(tabla.getPreferredSize());
    		
    		JButton botonEliminarIngredienteTabla=new JButton("-");
    		botonEliminarIngredienteTabla.setBackground(new Color(255, 69, 58));
    		botonEliminarIngredienteTabla.setFont(new Font("Arial", Font.BOLD, 15));
    		botonEliminarIngredienteTabla.setForeground(Color.WHITE);
    		botonEliminarIngredienteTabla.addActionListener(ee->{
    			eliminarFilaListaIngredientes(tabla);
    		});
    		panelTabla.add(scrollTabla);
    		panelTabla.add(botonEliminarIngredienteTabla);
    		
    		//panel ComboBox de ingredientes
    		JLabel labelIngrediente = new JLabel("Ingrediente : ");
    		List<TransferIngrediente> ingredientes =controlador.listaIngredientes();
    		String[] ingredientesNombre=new String[ingredientes.size()];
    		for(int i=0;i<ingredientes.size();i++) {
    			ingredientesNombre[i]=ingredientes.get(i).getNombre();
    		}
    		JComboBox<String> ingredientesCombo=new JComboBox(ingredientesNombre);
    		ingredientesCombo.setEditable(true);
    		ingredientesCombo.setMinimumSize(dimensionJComboIngredientes);
    		ingredientesCombo.setPreferredSize(dimensionJComboIngredientes);
    		ingredientesCombo.setMaximumSize(dimensionJComboIngredientes);
    		panelBuscarIngrediente.add(labelIngrediente);
    		panelBuscarIngrediente.add(ingredientesCombo);
    		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
    		spinner.setMinimumSize(dimensionSpinnerCantidad);
    		spinner.setPreferredSize(dimensionSpinnerCantidad);
    		spinner.setMaximumSize(dimensionSpinnerCantidad);
    		
    		JButton botonAgregar=new JButton("+");
    		botonAgregar.setBackground(new Color(0, 128, 0));
    		botonAgregar.setFont(new Font("Arial", Font.BOLD, 15));
    		botonAgregar.setForeground(Color.WHITE);
    		botonAgregar.addActionListener(ee->{
    			if(comprobarIngredienteComboBoxAumentar((String)ingredientesCombo.getSelectedItem()) &&
    					!ingredienteRepetido(tabla, (String)ingredientesCombo.getSelectedItem())) {
    				String [] ingredienteFila = {(String) ingredientesCombo.getSelectedItem() ,String.valueOf(spinner.getValue())};
    				agregarFilaListaIngrediente(tabla,ingredienteFila);
    			}
    		});
    		
    		panelBuscarIngrediente.add(labelIngrediente);
    		panelBuscarIngrediente.add(ingredientesCombo);
    		panelBuscarIngrediente.add(spinner);
    		panelBuscarIngrediente.add(botonAgregar);
    		//pane de botones de abajo: aceptar y cancelar
    		JButton botonAceptarCrearPlato = new JButton("Aceptar");
    		botonAceptarCrearPlato.setBackground(new Color(50, 205, 50));
    		botonAceptarCrearPlato.setFont(new Font("Arial", Font.BOLD, 15));
    		botonAceptarCrearPlato.setForeground(Color.WHITE);
    		botonAceptarCrearPlato.addActionListener(ee -> {
    			if(nombrePlatoNoNulo(jtextNombre) && comprobarPrecio(jtextPrecio) && categoriaNoNula(tipoGrupo) && noTablaNula(tabla))  {
    				
    				Map<String,Integer> mapa=new HashMap<>();
    				for(int i=0;i<tabla.getRowCount();i++) {
    					mapa.put((String)tabla.getValueAt(i,0),Integer.valueOf((String)tabla.getValueAt(i,1)));
    				}
    				try{
    					String nombreDireccion = (String)jtextNombre.getText();
    					String nombreDirecciontransformado = "resources/carta/"+nombreDireccion.trim().replaceAll(" +", "_")+".jpeg";
    					
    					TransferPlato plato = new TransferPlato(controlador.generarCodigoRandom(),(String)jtextNombre.getText(),
        						mapa,Double.valueOf((String)jtextPrecio.getText()),tipo[0],nombreDirecciontransformado);

        				if (controlador.crearPlato(plato)) {
        					JOptionPane.showMessageDialog(null, "Plato creado correctamente.");
        					
        				} else {
        					JOptionPane.showMessageDialog(null, "Error, el plato no se agrego correctamente","Error", JOptionPane.ERROR_MESSAGE);
        				}
    				}catch(NumberFormatException ex) {
    					JOptionPane.showMessageDialog(null, "El precio debe ser en valor numerico.");
    				}
    				frame.dispose();
    				initGUI();
    			}
			});
			JButton botonCancelarAgregarPlato = new JButton("Cancelar");
			botonCancelarAgregarPlato.setBackground(Color.GRAY);
			botonCancelarAgregarPlato.setFont(new Font("Arial", Font.BOLD, 15));
			botonCancelarAgregarPlato.setForeground(Color.WHITE);
			botonCancelarAgregarPlato.addActionListener(ee -> {
				frame.dispose();
			});
    		
    		panelCentral.add(panelNombre);
    		panelCentral.add(panelPrecio);
    		panelCentral.add(panelTipo);
    		panelCentral.add(panelBuscarIngrediente);
    		panelCentral.add(panelTabla);
    		panelBotonesAbajo.add(botonCancelarAgregarPlato);
    		panelBotonesAbajo.add(botonAceptarCrearPlato);
    		panelPrincipal.add(panelCentral,BorderLayout.CENTER);
    		panelPrincipal.add(panelBotonesAbajo,BorderLayout.SOUTH);
    		
		});
	}

	private void filtrar() {
		panelPlatillos.removeAll();

		String textoBusqueda = campoBusqueda.getText().trim().toLowerCase();
		String categoriaSeleccionada = botonActivo != null ? botonActivo.getText() : "Todos";

		for (JButton b : botonesPlatillos) {
			String nombrePlato = b.getText().toLowerCase();
			String categoriaBoton = (String) b.getClientProperty("categoria");

			boolean coincideCategoria = categoriaSeleccionada.equals("Todos")
					|| categoriaSeleccionada.equals(categoriaBoton);
			boolean coincideBusqueda = textoBusqueda.isEmpty() || nombrePlato.contains(textoBusqueda);

			if (coincideCategoria && coincideBusqueda) {
				panelPlatillos.add(b);
			}
		}

		// Rellenar con espacios vacíos para mantener la estructura del menú
		int totalComp = panelPlatillos.getComponentCount();
		for (int i = totalComp; i < 9; i++) {
			JPanel panelVacio = new JPanel();
			panelVacio.setOpaque(false);
			panelPlatillos.add(panelVacio);
		}

		panelPlatillos.revalidate();
		panelPlatillos.repaint();
	}
	
	private void mostrarDetallesPlato(TransferPlato plato) {
	    // Crear el JDialog
	    JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Detalles del Plato");
	    dialog.setSize(700, 500);
	    dialog.setLocationRelativeTo(this);
	    dialog.setLayout(new BorderLayout(20, 20));

	    // Imagen a la izquierda
	    ImageIcon imagenPlato = new ImageIcon(plato.getIconPath());
	    imagenPlato = new ImageIcon(imagenPlato.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
	    JLabel labelImagen = new JLabel(imagenPlato);
	    labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
	    dialog.add(labelImagen, BorderLayout.WEST);

	    // Datos a la derecha
	    JPanel panelInfo = new JPanel();
	    panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
	    panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	    JLabel labelNombre = new JLabel("Nombre: " + plato.getNombre());
	    labelNombre.setFont(new Font("Arial", Font.BOLD, 18));
	    panelInfo.add(labelNombre);

	    JLabel labelCategoria = new JLabel("Categoría: " + plato.getCategoria());
	    labelCategoria.setFont(new Font("Arial", Font.PLAIN, 16));
	    panelInfo.add(Box.createRigidArea(new Dimension(0, 10))); // espacio
	    panelInfo.add(labelCategoria);

	    JLabel labelPrecio = new JLabel("Precio: " + plato.getPrecio() + " €");
	    labelPrecio.setFont(new Font("Arial", Font.PLAIN, 16));
	    panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
	    panelInfo.add(labelPrecio);

	    dialog.add(panelInfo, BorderLayout.CENTER);

	    // Ingredientes abajo
	    JPanel panelIngredientes = new JPanel();
	    panelIngredientes.setLayout(new BoxLayout(panelIngredientes, BoxLayout.Y_AXIS));
	    panelIngredientes.setBorder(BorderFactory.createTitledBorder("Ingredientes"));
	    
	    // Lista de ingredientes (asegúrate de que plato.getIngredientes() exista)
	    for (Map.Entry<String, Integer> entry : plato.getIngredientes().entrySet()) {
	        String ingrediente = entry.getKey();
	        Integer cantidad = entry.getValue();
	        JLabel labelIngrediente = new JLabel("- " + ingrediente + " (" + cantidad + ")");
	        panelIngredientes.add(labelIngrediente);
	    }

	    JScrollPane scrollIngredientes = new JScrollPane(panelIngredientes);
	    scrollIngredientes.setPreferredSize(new Dimension(700, 150));
	    dialog.add(scrollIngredientes, BorderLayout.SOUTH);

	    // Panel de botones (Eliminar y Editar)
	    JPanel panelBotones = new JPanel();
	    panelBotones.setLayout(new GridLayout(1, 2, 10, 0));
	    
	    // Botón Eliminar
	    JButton botonEliminar = new JButton("Eliminar");
	    botonEliminar.addActionListener(e -> {
	        int confirmacion = JOptionPane.showConfirmDialog(dialog, "¿Estás seguro de eliminar este plato?", 
	                "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
	        if (confirmacion == JOptionPane.YES_OPTION) {
	            controlador.eliminarPlato(plato); // Asumiendo que tienes un método en controlador para eliminar el plato
	            dialog.dispose(); // Cierra el diálogo
	        }
	    });
	    panelBotones.add(botonEliminar);
	    
	    // Botón Editar
	    JButton botonEditar = new JButton("Editar");
	    botonEditar.addActionListener(e -> {
	        // Aquí puedes abrir un formulario de edición
	        // Vamos a crear un ejemplo básico de cómo editar un plato
	        JTextField nombreEditado = new JTextField(plato.getNombre(), 20);
	        JTextField categoriaEditada = new JTextField(plato.getCategoria(), 20);
	        JTextField precioEditado = new JTextField(String.valueOf(plato.getPrecio()), 20);

	        JPanel panelEdicion = new JPanel();
	        panelEdicion.setLayout(new BoxLayout(panelEdicion, BoxLayout.Y_AXIS));
	        panelEdicion.add(new JLabel("Nombre:"));
	        panelEdicion.add(nombreEditado);
	        panelEdicion.add(new JLabel("Categoría:"));
	        panelEdicion.add(categoriaEditada);
	        panelEdicion.add(new JLabel("Precio:"));
	        panelEdicion.add(precioEditado);
	        
	        int opcion = JOptionPane.showConfirmDialog(dialog, panelEdicion, 
	                "Editar Platillo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        if (opcion == JOptionPane.OK_OPTION) {
	            plato.setNombre(nombreEditado.getText());
	            plato.setCategoria(categoriaEditada.getText());
	            try {
	                plato.setPrecio(Double.parseDouble(precioEditado.getText()));
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(dialog, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            controlador.actualizarPlato(plato);
	            dialog.dispose(); // Cierra el diálogo después de editar
	        }
	    });
	    panelBotones.add(botonEditar);

	    dialog.add(panelBotones, BorderLayout.NORTH);

	    // Mostrar
	    dialog.setVisible(true);
	}
	
	private void customizeScrollBar(JScrollPane scrollPanelPlatillos) {
		scrollPanelPlatillos.getVerticalScrollBar().setUnitIncrement(40);
		scrollPanelPlatillos.setBorder(BorderFactory.createEmptyBorder(0, 200, 0, 0));
		scrollPanelPlatillos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanelPlatillos.setViewportBorder(BorderFactory.createEmptyBorder(0, 0, 0, 200));
		scrollPanelPlatillos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// Personalizando la barra de desplazamiento vertical
		JScrollBar verticalScrollBar = scrollPanelPlatillos.getVerticalScrollBar();
		verticalScrollBar.setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				// Establece el color de fondo de la barra de desplazamiento
				this.thumbColor = new Color(180, 180, 180); // Color del "dedo" de la barra de desplazamiento
				this.trackColor = new Color(240, 240, 240); // Color de la pista (fondo de la barra de desplazamiento)
			}

			// Sobreescribimos los métodos para eliminar los triángulos
			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setPreferredSize(new Dimension(0, 0)); // Sin botones de aumentar (triángulo arriba)
				button.setVisible(false); // Aseguramos que no sea visible
				return button;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setPreferredSize(new Dimension(0, 0)); // Sin botones de disminuir (triángulo abajo)
				button.setVisible(false); // Aseguramos que no sea visible
				return button;
			}
		});
	}
	private boolean comprobarIngredienteComboBoxAumentar(String ingredienteNombre) {
		for (TransferIngrediente i : this.controlador.listaIngredientes()) {
			if (i.getNombre().equals(ingredienteNombre)) {
				return true;
			}
		}
		return false;
	}
	private TransferIngrediente transformasStringATransferIngrediente(String nombreIngrediente) {
		for (TransferIngrediente i : controlador.listaIngredientes()) {
			if (i.getNombre().equals(nombreIngrediente)) {
				return i;
			}
		}
		return null;
	}
	private void agregarFilaListaIngrediente(JTable tabla, Object[] ingrediente) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
	    modelo.addRow(ingrediente);
	}
	private void eliminarFilaListaIngredientes(JTable tabla) {
		int filaSeleccionada = tabla.getSelectedRow();
		if (filaSeleccionada != -1) {
		    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
		    model.removeRow(filaSeleccionada);
		} else {
		    JOptionPane.showMessageDialog(null, "Selecciona una fila para eliminar.");
		}
	}
	private boolean ingredienteRepetido(JTable tabla, String ingrediente) {
		for(int i=0;i<tabla.getRowCount();i++) {
			if (((String) tabla.getValueAt(i, 0)).equals(ingrediente)) {
				JOptionPane.showMessageDialog(null, "Este ingrediente ya esta en la lista.");
			    return true;
			}
		}
		return false;
	}
	private boolean comprobarPrecio(JTextField text) {
		if(text.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Introduzca el precio del plato.");
			return false;
		}
		
		
		return true;
	}
	private boolean categoriaNoNula(ButtonGroup grupo) {
		if(grupo.getSelection()==null) {
			JOptionPane.showMessageDialog(null, "Elija una categoria.");
			return false;
		}

		return true;
		
	}
	private boolean nombrePlatoNoNulo(JTextField text) {
		if(text.getText().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Introduzca un nombre para el plato.");
			return false;
		}
		return true;
	}
	private boolean noTablaNula(JTable tabla) {
		if(tabla.getRowCount()<1){
			JOptionPane.showMessageDialog(null, "Agregue minimo un ingrediente.");
			return false;
		}
		return true;
	}
}
