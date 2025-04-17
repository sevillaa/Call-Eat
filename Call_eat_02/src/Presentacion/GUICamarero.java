package Presentacion;

public abstract class GUICamarero {
    private static GUICamarero instancia;

    public static GUICamarero getInstancia(Controlador controlador,Object datos) {
        if (instancia == null) {
            instancia = new GUICamareroImp(controlador,datos);
        }
        return instancia;
    }

    public static void resetInstancia() {
        instancia = null;
    }

    public abstract void actualizar(int evento, Object datos);
}
