package Presentacion;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;

public class InventarioPanel extends JPanel {

	private JPanel panelContenedor;
	private JTable table;
	private CardLayout cardLayout;
	private Controlador controlador;
	private List<TransferIngrediente> ingredientes;
	private JButton botonAnadir;
	private JButton botonModificar;
	private JButton botonEliminar;
	private JButton botonCancelar;

	public InventarioPanel(JPanel panelContenedor, CardLayout cardLayout, Controlador controlador) {
		this.panelContenedor = panelContenedor;
		this.cardLayout = cardLayout;
		this.controlador = controlador;
		this.ingredientes = new ArrayList<>();
		initComponents();
	}

	private void cargarIngredientes() {

		ingredientes = controlador.listaIngredientes();

		String[] columnas = { "Nombre de producto", "Cantidad" };
		Object[][] datosIngredientes = new Object[ingredientes.size()][2];

		for (int i = 0; i < ingredientes.size(); i++) {
			TransferIngrediente ingrediente = ingredientes.get(i);
			datosIngredientes[i][0] = ingrediente.getNombre();
			datosIngredientes[i][1] = ingrediente.getCantidad();
		}

		table.setModel(new DefaultTableModel(datosIngredientes, columnas));
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn col1 = columnModel.getColumn(0); // Columna 1 (Nombre de producto)
		TableColumn col2 = columnModel.getColumn(1); // Columna 2 (Cantidad)

		// Ajusta el tamaño de la primera columna
		// (Nombre de producto)
		col2.setPreferredWidth(60); // Ajusta el tamaño de la segunda columna (Cantidad)
		col2.setMinWidth(40);
		col2.setMaxWidth(100);
		// Centrar el contenido de la columna "Cantidad"
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra el contenido
		col2.setCellRenderer(renderer); // Aplica el renderizador a la columna "Cantidad"
	}

	private void initComponents() {
		// this.setSize(1000, 500);
		this.setLayout(new BorderLayout());
		JPanel panelSuperior = new JPanel(new BorderLayout());
		JPanel panelCentral = new JPanel(new BorderLayout());
		panelCentral.setBackground(Color.white);
		JPanel panelBotones = new JPanel(new FlowLayout());
		panelBotones.setBackground(new Color(173, 216, 230));

		JLabel tituloPlantilla = new JLabel("Inventario", SwingConstants.CENTER);
		tituloPlantilla.setFont(new Font("Arial", Font.BOLD, 20));
		panelCentral.add(tituloPlantilla, BorderLayout.NORTH);

		table = new JTable();
		cargarIngredientes();

		JScrollPane scrollTabla = new JScrollPane(table);
		panelCentral.add(scrollTabla, BorderLayout.CENTER);
		Dimension botonDimension = new Dimension(150, 40); // Tamaño común para los botones
		Dimension jtextYcomco = new Dimension(200, 25);

		///////////////////////// BOTON DE CREAR UN NUEVO
		///////////////////////// INGREDIENTE///////////////////////
		botonAnadir = new JButton("Crear");
		botonAnadir.setBackground(new Color(50, 205, 50));
		botonAnadir.setForeground(Color.WHITE);
		botonAnadir.setFont(new Font("Arial", Font.BOLD, 13));
		botonAnadir.addActionListener(e -> {
			
			JFrame crearFrame = new JFrame("Crear Ingrediente");
			crearFrame.setResizable(false);
			crearFrame.setSize(400, 200);
			crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			crearFrame.setLocationRelativeTo(null);
			crearFrame.setVisible(true);

			JPanel panelCrear = new JPanel();
			panelCrear.setBackground(Color.WHITE);
			panelCrear.setLayout(new BorderLayout());
			crearFrame.add(panelCrear);

			JLabel titulo = new JLabel("Creacion de ingrediente", SwingConstants.CENTER);
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			panelCrear.add(titulo, BorderLayout.NORTH);

			JPanel centro = new JPanel();
			centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
			JPanel centroArriba = new JPanel();
			JPanel centroAbajo = new JPanel();
			centroArriba.setBackground(Color.white);
			centroAbajo.setBackground(Color.white);
			centro.setBackground(Color.white);
			centro.setBackground(Color.WHITE);

			JPanel abajo = new JPanel(new FlowLayout());
			abajo.setBackground(Color.WHITE);
			JLabel labelNombre = new JLabel("Nombre : ");
			JTextField textNombre = new JTextField();
			textNombre.setMinimumSize(jtextYcomco);
			textNombre.setPreferredSize(jtextYcomco);
			textNombre.setMaximumSize(jtextYcomco);
			JLabel labelCantidad = new JLabel("Cantidad : ");
			JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
			JButton ok = new JButton("Aceptar");
			ok.setBackground(new Color(50, 205, 50));
			ok.setFont(new Font("Arial", Font.BOLD, 15));
			ok.setForeground(Color.WHITE);
			ok.addActionListener(ee -> {
				TransferIngrediente in = new TransferIngrediente(controlador.generarCodigoRandom(), textNombre.getText(),
						(int) spinnerCantidad.getValue());
				if (controlador.crearIngrediente(in)) {
					JOptionPane.showMessageDialog(null, "Ingrediente creado correctamente.");
				} else {
					JOptionPane.showMessageDialog(null, "Error, el ingrediente '" + textNombre.getText() + "' ya existe ",
							"Ingrediente ya existente", JOptionPane.ERROR_MESSAGE);
				}

				crearFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						cargarIngredientes();
					}

				});
				crearFrame.dispose();
			});
			JButton cancelar = new JButton("Cancelar");
			cancelar.setBackground(Color.GRAY);
			cancelar.setFont(new Font("Arial", Font.BOLD, 15));
			cancelar.setForeground(Color.WHITE);
			cancelar.addActionListener(ee -> {
				crearFrame.dispose();
			});
			centroArriba.add(labelNombre);
			centroArriba.add(textNombre);
			centroAbajo.add(labelCantidad);
			centroAbajo.add(Box.createHorizontalStrut(115));
			centroAbajo.add(spinnerCantidad);
			centro.add(centroArriba);
			centro.add(centroAbajo);
			abajo.add(cancelar);
			abajo.add(ok);
			panelCrear.add(centro, BorderLayout.CENTER);
			panelCrear.add(abajo, BorderLayout.PAGE_END);

		});
/////////////////////////BOTON DE AUMENTAR UN INGREDIENTE///////////////////////

		botonModificar = new JButton("Aumentar");
		botonModificar.setBackground(new Color(0, 128, 0));
		botonModificar.setForeground(Color.WHITE);
		botonModificar.setFont(new Font("Arial", Font.BOLD, 13));
		botonModificar.addActionListener(e -> {
			JFrame crearFrame = new JFrame("Aumentar Ingrediente");
			crearFrame.setResizable(false);
			crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			crearFrame.setSize(400, 200);
			crearFrame.setLocationRelativeTo(null);
			crearFrame.setVisible(true);
			crearFrame.setBackground(Color.white);

			JPanel panelModificar = new JPanel();
			panelModificar.setLayout(new BorderLayout());
			panelModificar.setBackground(Color.WHITE);
			crearFrame.add(panelModificar);
			JLabel titulo = new JLabel("Aumentar suministro", SwingConstants.CENTER);
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			panelModificar.add(titulo, BorderLayout.NORTH);
			JPanel centro = new JPanel();
			centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
			JPanel centroArriba = new JPanel();
			JPanel centroAbajo = new JPanel();
			centroArriba.setBackground(Color.white);
			centroAbajo.setBackground(Color.white);
			centro.setBackground(Color.white);
			JPanel abajo = new JPanel(new FlowLayout());
			abajo.setBackground(Color.WHITE);
			JLabel labelNombre = new JLabel("Producto : ");
			String[] combolist = new String[ingredientes.size()];
			for (int i = 0; i < ingredientes.size(); i++) {
				combolist[i] = ingredientes.get(i).getNombre();
			}
			JComboBox<String> comboIngredientes = new JComboBox<>(combolist);
			comboIngredientes.setEditable(true);
			comboIngredientes.setMinimumSize(jtextYcomco);
			comboIngredientes.setPreferredSize(jtextYcomco);
			comboIngredientes.setMaximumSize(jtextYcomco);
			if (table.getSelectedRow() != -1) {
				int fila = table.getSelectedRow();
				comboIngredientes.setSelectedItem(combolist[fila]);
			}
			JLabel labelCantidad = new JLabel("Cantidad : ");
			JSpinner spinnerCantidad = new JSpinner(new SpinnerNumberModel(10, 1, 10000, 1));
			JButton ok = new JButton("Aceptar");
			ok.setBackground(new Color(0, 128, 0));
			ok.setFont(new Font("Arial", Font.BOLD, 15));
			ok.setForeground(Color.WHITE);
			ok.addActionListener(ee -> {
				if (!comprobarIngredienteComboBoxAumentar((String) comboIngredientes.getSelectedItem())) {

					JOptionPane.showMessageDialog(null,
							"El ingrediente '" + (String) comboIngredientes.getSelectedItem() + "' no existe", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				} else {

					TransferIngrediente in = transformasStringATransferIngrediente(
							(String) comboIngredientes.getSelectedItem());
					in.setCantidad(in.getCantidad() + (int) spinnerCantidad.getValue());
					if (controlador.modificarIngrediente(in)) {
						JOptionPane.showMessageDialog(null, "Cantidad modificada correctamente.");
					} else {
						JOptionPane.showMessageDialog(null,
								"El ingrediente '" + comboIngredientes.getSelectedIndex() + "' no existe",
								"No existe este ingrediente", JOptionPane.ERROR_MESSAGE);
					}
					crearFrame.dispose();
				}

				crearFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						cargarIngredientes();
					}

				});
				
			});
			JButton cancelar = new JButton("Cancelar");
			cancelar.setBackground(Color.GRAY);
			cancelar.setFont(new Font("Arial", Font.BOLD, 15));
			cancelar.setForeground(Color.WHITE);
			cancelar.addActionListener(ee -> {
				crearFrame.dispose();
			});
			centroArriba.setAlignmentX(CENTER_ALIGNMENT);
			centroAbajo.setAlignmentX(CENTER_ALIGNMENT);
			centroArriba.setOpaque(false);
			centroAbajo.setOpaque(false);
			centroArriba.add(labelNombre);
			centroArriba.add(comboIngredientes);
			centroAbajo.add(labelCantidad);
			centroAbajo.add(Box.createHorizontalStrut(114));
			centroAbajo.add(spinnerCantidad);
			centroAbajo.add(Box.createVerticalStrut(20));
			centro.add(centroArriba);
			centro.add(centroAbajo);
			abajo.add(cancelar);
			abajo.add(ok);
			panelModificar.add(centro, BorderLayout.CENTER);
			panelModificar.add(abajo, BorderLayout.PAGE_END);
		});

/////////////////////////BOTON DE ELIMINAR UN INGREDIENTE///////////////////////
		botonEliminar = new JButton("Eliminar");
		botonEliminar.setBackground(new Color(255, 69, 58));
		botonEliminar.setForeground(Color.WHITE);
		botonEliminar.setFont(new Font("Arial", Font.BOLD, 13));
		botonEliminar.addActionListener(e -> {
			JFrame crearFrame = new JFrame("Eliminar Ingrediente");
			crearFrame.setResizable(false);
			crearFrame.setSize(400, 150);
			crearFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			crearFrame.setLocationRelativeTo(null);
			crearFrame.setVisible(true);
			JPanel panelEliminar = new JPanel();
			panelEliminar.setLayout(new BorderLayout());
			crearFrame.add(panelEliminar);
			panelEliminar.setBackground(Color.white);
			JLabel titulo = new JLabel("¿Quieres eliminar este producto?", SwingConstants.CENTER);
			titulo.setFont(new Font("Arial", Font.BOLD, 20));
			panelEliminar.add(titulo, BorderLayout.NORTH);
			JPanel centro = new JPanel(new FlowLayout());
			JPanel abajo = new JPanel(new FlowLayout());
			centro.setBackground(Color.WHITE);
			abajo.setBackground(Color.WHITE);
			JLabel labelNombre = new JLabel("Producto");
			String[] combolist = new String[ingredientes.size()];
			for (int i = 0; i < ingredientes.size(); i++) {
				combolist[i] = ingredientes.get(i).getNombre();
			}
			JComboBox<String> comboIngredientes = new JComboBox<>(combolist);
			comboIngredientes.setEditable(true);
			if (table.getSelectedRow() != -1) {
				int fila = table.getSelectedRow();
				comboIngredientes.setSelectedItem(combolist[fila]);
			}
			JButton ok = new JButton("Eliminar");
			ok.setBackground(new Color(255, 69, 58));
			ok.setFont(new Font("Arial", Font.BOLD, 15));
			ok.setForeground(Color.WHITE);
			ok.addActionListener(ee -> {
				if (!comprobarIngredienteComboBoxAumentar((String) comboIngredientes.getSelectedItem())) {

					JOptionPane.showMessageDialog(null,
							"El ingrediente '" + (String) comboIngredientes.getSelectedItem() + "' no existe", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
				else {
					TransferIngrediente in = transformasStringATransferIngrediente(
							(String) comboIngredientes.getSelectedItem());
					if (controlador.eliminarIngrediente(in)) {
						JOptionPane.showMessageDialog(null, "Ingrediente eliminado correctamente.");
					}
					else {
						JOptionPane.showMessageDialog(null,
								"El ingrediente '" + comboIngredientes.getSelectedIndex() + "' no existe",
								"No existe este ingrediente", JOptionPane.ERROR_MESSAGE);
					}
					crearFrame.dispose();
				}
				
				crearFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						cargarIngredientes();
					}

				});
				
			});
			JButton cancelar = new JButton("Cancelar");
			cancelar.setBackground(Color.GRAY);
			cancelar.setFont(new Font("Arial", Font.BOLD, 15));
			cancelar.setForeground(Color.WHITE);
			cancelar.addActionListener(ee -> {
				crearFrame.dispose();
			});
			centro.add(labelNombre);
			centro.add(comboIngredientes);
			abajo.add(cancelar);
			abajo.add(ok);
			panelEliminar.add(centro, BorderLayout.CENTER);
			panelEliminar.add(abajo, BorderLayout.PAGE_END);
		});

/////////////////////////BOTON DE REGRESAR EL MENU DEL GESTOR///////////////////////
		ImageIcon volverIcono = new ImageIcon("resources/botonAtras.png");
		Image volverIconoImagen = volverIcono.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		botonCancelar = new JButton("Volver", new ImageIcon(volverIconoImagen));
		botonCancelar.setFont(new Font("Arial", Font.BOLD, 10));
		botonCancelar.setForeground(Color.white);
		botonCancelar.setPreferredSize(new Dimension(110, 20));
		botonCancelar.setContentAreaFilled(false); // elimina el fondo estirado
		botonCancelar.setBorderPainted(false); // elimina el borde
		botonCancelar.setFocusPainted(false); // quita ese borde de foco azul
		botonCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		botonCancelar.setVerticalAlignment(SwingConstants.CENTER);
		botonCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		botonCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		botonCancelar.addActionListener(ev -> {
			cardLayout.show(panelContenedor, "menu");
		});

		ImageIcon logo = new ImageIcon("resources/logo.png");
		Image locoImagenEscalado = logo.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH);
		ImageIcon logoIconoEscalado = new ImageIcon(locoImagenEscalado);
		JLabel etiquetaImagen = new JLabel(logoIconoEscalado);

		panelSuperior.add(botonCancelar, BorderLayout.LINE_START);
		panelSuperior.setBackground(new Color(100, 180, 255));
		panelSuperior.add(etiquetaImagen, BorderLayout.LINE_END);
		panelBotones.add(botonAnadir); // Primer botón
		panelBotones.add(botonModificar); // Segundo botón
		panelBotones.add(botonEliminar); // Tercer botón
		panelCentral.add(panelBotones, BorderLayout.SOUTH);
		this.add(panelSuperior, BorderLayout.PAGE_START);
		this.add(panelCentral, BorderLayout.CENTER);
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
		for (TransferIngrediente i : ingredientes) {
			if (i.getNombre().equals(nombreIngrediente)) {
				return i;
			}
		}
		return null;
	}
}
