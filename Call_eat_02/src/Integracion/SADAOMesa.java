package Integracion;

import Negocio.TransferMesa;

public interface SADAOMesa {

	boolean crearMesa(TransferMesa mesa);

	boolean eliminarMesa(TransferMesa mesa);

	TransferMesa buscarMesa(String idMesa);

}
