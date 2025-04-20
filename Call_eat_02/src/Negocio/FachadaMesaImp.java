package Negocio;

import java.util.List;

public class FachadaMesaImp implements FachadaMesa {
    private SAMesa saMesa = new SAMesaImp();

    public boolean crearMesa(TransferMesa mesa) {
        return this.saMesa.crearMesa(mesa);
    }

    public boolean eliminarMesa(TransferMesa mesa) {
        return this.saMesa.eliminarMesa(mesa);
    }

    public TransferMesa buscarMesa(String idMesa) {
        return this.saMesa.buscarMesa(idMesa);
    }

    public boolean modificarMesa(TransferMesa mesaActualizada) {
        return this.saMesa.modificarMesa(mesaActualizada);
    }

    public List<TransferMesa> obtenerMesas() {
        return this.saMesa.obtenerMesas();
    }
}