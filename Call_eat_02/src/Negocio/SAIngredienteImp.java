package Negocio;

import Integracion.FachadaDAOIngredienteImp;

public class SAIngredienteImp implements SAIngrediente{

	private FachadaDAOIngredienteImp fachadaDaoIngrediente = new FachadaDAOIngredienteImp();
	
	@Override
	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		// TODO Auto-generated method stub
		

		
		return this.fachadaDaoIngrediente.crearIngrediente(ingrediente);
	}

	@Override
	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		// TODO Auto-generated method stub
		

		
		return this.fachadaDaoIngrediente.eliminarIngrediente(ingrediente);
	}

	@Override
	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		// TODO Auto-generated method stub
		

		
		return this.fachadaDaoIngrediente.buscarIngrediente(idIngrediente);
	}



}
