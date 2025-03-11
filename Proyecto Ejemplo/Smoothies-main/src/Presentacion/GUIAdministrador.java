package Presentacion;

public abstract class GUIAdministrador {
	private static GUIAdministrador instancia = null;

    public static GUIAdministrador getInstancia(Controlador controlador) {
        if (instancia == null)
            instancia = new GUIAdministradorImp(controlador);
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);

}
