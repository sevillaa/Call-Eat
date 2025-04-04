package Presentacion;

public abstract class GUIGestor {
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

