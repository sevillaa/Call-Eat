package Presentacion;

import javax.swing.*;

public abstract class GUICocina extends JPanel {  // ¡Ahora hereda de JPanel!
    private static final long serialVersionUID = 1L;
	private static GUICocina instancia = null;
    
    public static GUICocina getInstancia(Controlador controlador, Object datos) {
        if (instancia == null)
            instancia = new GUICocinaImp(controlador, datos);
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
}