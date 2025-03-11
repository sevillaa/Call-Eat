package Presentacion;

public abstract class GUISmoothies {
	private static GUISmoothies instancia = null;

    public static GUISmoothies getInstancia(Controlador controlador) {
        if (instancia == null)
            instancia = new GUISmoothiesImp(controlador);
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
}
