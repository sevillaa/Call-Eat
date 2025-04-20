package Presentacion;

import java.util.List;

import Negocio.TransferMesa;

public abstract class GUIMesa {
    private static GUIMesa instancia;

    public static GUIMesa getInstancia(Controlador controlador, List<TransferMesa> datos) {
        if (instancia == null) {
            instancia = new GUIMesaImp(controlador, datos);
        }
        return instancia;
    }

    public static void resetInstancia() {
        instancia = null;
    }

    public abstract void actualizar(int evento, Object datos);

    public abstract void dispose();
}