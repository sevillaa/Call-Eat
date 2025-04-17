package Integracion;

import Negocio.TransferPlato;

public class SADAOPlatoImp implements SADAOPlato {
	
	DAOPlatoImp daoPlato = new DAOPlatoImp();

	@Override
	public boolean crearPlato(TransferPlato plato) {
		return daoPlato.crearPlato(plato);
	}

	@Override
	public boolean eliminarPlato(TransferPlato plato) {
		return daoPlato.eliminarPlato(plato);
	}

	@Override
	public TransferPlato buscarPlato(String idPlato) {
		return daoPlato.buscarPlato(idPlato);
	}

}
