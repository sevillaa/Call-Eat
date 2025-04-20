package Integracion;

import java.util.List;
import Negocio.TransferMesa;

public class FachadaDAOMesaImp implements FachadaDAOMesa {
    private SADAOMesa saDaoMesa = new SADAOMesaImp();

    public boolean crearMesa(TransferMesa mesa) {
        return this.saDaoMesa.crearMesa(mesa);
    }

    public boolean eliminarMesa(TransferMesa mesa) {
        return this.saDaoMesa.eliminarMesa(mesa);
    }

    public TransferMesa buscarMesa(String idMesa) {
        return this.saDaoMesa.buscarMesa(idMesa);
    }

    public void actualizarMesa(TransferMesa mesaActualizada) {
        this.saDaoMesa.actualizarMesa(mesaActualizada);
    }

    public List<TransferMesa> obtenerMesas() {
        return this.saDaoMesa.obtenerMesas();
    }
}