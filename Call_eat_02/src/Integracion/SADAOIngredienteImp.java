package Integracion;

import Negocio.TransferIngrediente;

public class SADAOIngredienteImp implements SADAOIngrediente {

	DAOIngredienteImp dao = new DAOIngredienteImp();
	
	@Override
	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		// TODO Auto-generated method stub
		return null;
	}

}
