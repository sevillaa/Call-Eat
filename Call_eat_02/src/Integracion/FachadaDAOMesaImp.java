package Integracion;

import Negocio.TransferMesa;

public class FachadaDAOMesaImp implements FachadaDAOMesa{

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

}
