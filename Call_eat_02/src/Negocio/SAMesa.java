package Negocio;

import java.util.List;

public interface SAMesa {
    boolean crearMesa(TransferMesa mesa);

    boolean eliminarMesa(TransferMesa mesa);

    TransferMesa buscarMesa(String idMesa);

    boolean modificarMesa(TransferMesa mesaActualizada);

    List<TransferMesa> obtenerMesas();
}