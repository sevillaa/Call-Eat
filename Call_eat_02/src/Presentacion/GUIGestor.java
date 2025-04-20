package Presentacion;

import javax.swing.JPanel;

public abstract class GUIGestor extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static GUIGestor instancia;

    public static GUIGestor getInstancia(Controlador controlador,Object datos) {
        if (instancia == null) {
            instancia = new GUIGestorImp(controlador,datos);
        }
        return instancia;
    }

    public static void resetInstancia() {
        instancia = null;
    }

    public abstract void actualizar(int evento, Object datos);
}

