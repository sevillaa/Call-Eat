package Presentacion;

public abstract class GUIEmpleado{
    private static GUIEmpleado instancia = null;
    
    
    public static GUIEmpleado getInstancia(Controlador controlador, Object datos) {
        if (instancia == null)
            instancia = new GUIEmpleadoImp(controlador, datos);
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
    public static void resetInstancia() {
        instancia = null;
    }
}