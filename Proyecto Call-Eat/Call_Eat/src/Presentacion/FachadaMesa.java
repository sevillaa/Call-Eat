package Presentacion;

import java.util.List;

import Negocio.TransferMesa;
import Negocio.TransferPlato;

public interface FachadaMesa {
	boolean registrarMesa(TransferMesa mesa);
    boolean modificarMesa(TransferMesa mesa);
    boolean eliminarMesa(TransferMesa mesa);
    TransferMesa buscarMesaPorId(String id);
    List<TransferMesa> listarMesa();
}
