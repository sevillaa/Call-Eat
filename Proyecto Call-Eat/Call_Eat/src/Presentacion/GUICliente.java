package Presentacion;

public abstract class GUICliente{
    private static GUICliente instancia = null;
    
    
    public static GUICliente getInstancia(Controlador controlador, Object datos) {
        if (instancia == null)
            instancia = new GUIClienteImp(controlador, datos);
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
}