package Integracion;

import Negocio.TransferIngrediente;

public class FachadaDAOIngredienteImp implements FachadaDAOIngrediente {

    private SADAOIngrediente saDaoIngrediente = new SADAOIngredienteImp();

	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		return this.saDaoIngrediente.crearIngrediente(ingrediente);
	}

	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		return this.saDaoIngrediente.eliminarIngrediente(ingrediente);
	}

	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		return this.saDaoIngrediente.buscarIngrediente(idIngrediente);
	}

}
