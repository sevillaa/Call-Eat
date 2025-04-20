package Integracion;

import java.util.List;
import Negocio.TransferMesa;

public class SADAOMesaImp implements SADAOMesa {
    private DAOMesaImp dao = new DAOMesaImp();

    @Override
    public boolean crearMesa(TransferMesa mesa) {
        return dao.crearMesa(mesa);
    }

    @Override
    public boolean eliminarMesa(TransferMesa mesa) {
        return dao.eliminarMesa(mesa);
    }

    @Override
    public TransferMesa buscarMesa(String idMesa) {
        return dao.buscarMesa(idMesa);
    }

    @Override
    public void actualizarMesa(TransferMesa mesaActualizada) {
        dao.actualizarMesa(mesaActualizada);
    }

    @Override
    public List<TransferMesa> obtenerMesas() {
        return dao.obtenerMesas();
    }
}