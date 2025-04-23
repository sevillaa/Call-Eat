package Presentacion;

import javax.swing.*;

import Negocio.TransferEmpleado;

import java.awt.*;
import java.util.List;


public class GUIGestorImp extends GUIGestor {

    private static final long serialVersionUID = 1L;
	private JFrame frame;
    private Controlador controlador;
    private Object datos;
    private CardLayout cardLayout;
    private JPanel panelContenedor;

    public GUIGestorImp(Controlador controlador,Object datos) {
        this.controlador = controlador;
        this.datos = datos;
        initGUI();
    }

    private void initGUI() {
    	
    	frame = new JFrame("Gestor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400); // Tamaño fijo para mantener uniformidad
        frame.setLocationRelativeTo(null);

        // Panel contenedor con CardLayout para gestionar las diferentes vistas
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Se asume que InicioPanel, LoginPanel y RegistroPanel están implementados
        
        MenuGestorPanel menuGestorPanel = new MenuGestorPanel(cardLayout,panelContenedor,controlador,datos);
        PlatosPanel platosPanel = new PlatosPanel(panelContenedor, cardLayout, controlador);
        EmpleadosGestorPanel empleadosGestorPanel = new EmpleadosGestorPanel(panelContenedor, cardLayout, controlador,datos);
        InventarioPanel inventarioPanel = new InventarioPanel(panelContenedor, cardLayout, controlador);
        IngresosPanel ingresosPanel = new IngresosPanel(panelContenedor,cardLayout,controlador);
        
        // Agregar paneles al contenedor con un identificador único para cada uno
        panelContenedor.add(menuGestorPanel, "menu");
        panelContenedor.add(platosPanel, "platos");
        panelContenedor.add(inventarioPanel, "inventario");
        panelContenedor.add(empleadosGestorPanel,"empleados");
        panelContenedor.add(ingresosPanel,"ingresos");
        
        // Añadir el panel contenedor a la ventana
        frame.add(panelContenedor);
        frame.setVisible(true);
    	
    	
    }

    @Override
    public void actualizar(int evento, Object datos) {
                frame.dispose();
                // Se reinicia la instancia del menú mediante un método público en GUIMenu
                GUIGestor.resetInstancia();
                // Se crea o se recupera la instancia del menú, pasando el controlador y los datos del usuario
                GUIGestor.getInstancia(controlador, datos);
        }
    
}

