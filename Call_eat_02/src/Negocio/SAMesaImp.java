package Negocio;

import java.util.List;

import Integracion.FachadaDAOMesaImp;

public class SAMesaImp implements SAMesa {
    private FachadaDAOMesaImp fachadaDaoMesa = new FachadaDAOMesaImp();

    @Override
    public boolean crearMesa(TransferMesa mesa) {
        if (mesa.getCapacidad() <= 0) {
            return false;
        }
        return this.fachadaDaoMesa.crearMesa(mesa);
    }

    @Override
    public boolean eliminarMesa(TransferMesa mesa) {
        if (mesa.isReservada()) {
            return false;
        }
        return this.fachadaDaoMesa.eliminarMesa(mesa);
    }

    @Override
    public TransferMesa buscarMesa(String idMesa) {
        return this.fachadaDaoMesa.buscarMesa(idMesa);
    }

    @Override
    public boolean modificarMesa(TransferMesa mesaActualizada) {
        if (mesaActualizada.getCapacidad() <= 0) {
            return false;
        }
        TransferMesa mesaExistente = buscarMesa(mesaActualizada.getId());
        if (mesaExistente == null) {
            return false;
        }
        this.fachadaDaoMesa.actualizarMesa(mesaActualizada);
        return true;
    }

    @Override
    public List<TransferMesa> obtenerMesas() {
        return this.fachadaDaoMesa.obtenerMesas();
    }
}