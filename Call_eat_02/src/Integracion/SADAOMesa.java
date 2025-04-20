package Integracion;

import java.util.List;
import Negocio.TransferMesa;

public interface SADAOMesa {
    boolean crearMesa(TransferMesa mesa);

    boolean eliminarMesa(TransferMesa mesa);

    TransferMesa buscarMesa(String idMesa);

    void actualizarMesa(TransferMesa mesaActualizada);

    List<TransferMesa> obtenerMesas();
}