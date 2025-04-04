package Presentacion;

public abstract class GUIMesa {
    private static GUIMesa instancia;

    public static GUIMesa getInstancia(Controlador controlador,Object datos) {
        if (instancia == null) {
            instancia = new GUIMesaImp(controlador,datos);
        }
        return instancia;
    }

    public static void resetInstancia() {
        instancia = null;
    }

    public abstract void actualizar(int evento, Object datos);
}

