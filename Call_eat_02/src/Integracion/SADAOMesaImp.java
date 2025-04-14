package Integracion;

import Negocio.TransferMesa;

public class SADAOMesaImp implements SADAOMesa {

	DAOMesaImp dao = new DAOMesaImp();

	@Override
	public boolean crearMesa(TransferMesa mesa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarMesa(TransferMesa mesa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferMesa buscarMesa(String idMesa) {
		// TODO Auto-generated method stub
		return null;
	}

}
