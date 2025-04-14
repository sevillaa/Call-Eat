package Integracion;

import Negocio.TransferMesa;

public interface FachadaDAOMesa {
	public boolean crearMesa(TransferMesa mesa);

	public boolean eliminarMesa(TransferMesa mesa);
	
	public TransferMesa buscarMesa(String idMesa);
}
