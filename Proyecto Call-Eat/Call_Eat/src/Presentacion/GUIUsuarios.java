package Presentacion;

public abstract class GUIUsuarios{
    private static GUIUsuarios instancia = null;
    
    
    public static GUIUsuarios getInstancia(Controlador controlador, Object datos) {
        if (instancia == null)
            instancia = new GUIUsuariosImp(controlador, datos);
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
}
