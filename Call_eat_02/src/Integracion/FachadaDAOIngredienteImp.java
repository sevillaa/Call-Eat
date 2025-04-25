package Integracion;

import java.util.List;

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
	@Override
	public boolean modificarIngrediente(TransferIngrediente modificado) {
		return this.saDaoIngrediente.modificarIngrediente(modificado);
	}
	@Override
	public List<TransferIngrediente> listaIngredientes() {
		return saDaoIngrediente.listaIngredientes();
	}
	
	@Override
	public TransferIngrediente buscarIngredientePorNombre(String nombreIngrediente) {
		return this.saDaoIngrediente.buscarIngredientePorNombre(nombreIngrediente);
	}

}
