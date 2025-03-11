package Presentacion;

public abstract class GUIClientes {
    private static GUIClientes instancia = null;
    
    
    public static GUIClientes getInstancia(Controlador controlador, Object datos) {
        if (instancia == null)
            instancia = new GUIClientesImp(controlador, datos);
        return instancia;
    }

    public abstract void actualizar(int evento, Object datos);
}

