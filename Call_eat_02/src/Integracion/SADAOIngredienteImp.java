package Integracion;

import Negocio.TransferIngrediente;

public class SADAOIngredienteImp implements SADAOIngrediente {

	DAOIngredienteImp dao = new DAOIngredienteImp();
	
	@Override
	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		return dao.crearIngrediente(ingrediente);
	}

	@Override
	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		return dao.eliminiarIngrediente(ingrediente);
	}

	@Override
	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		return dao.buscarIngrediente(idIngrediente);
	}

}
