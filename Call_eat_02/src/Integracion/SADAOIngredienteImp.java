package Integracion;

import java.util.List;

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
	@Override
	public boolean modificarIngrediente(TransferIngrediente modificado) {
		return this.dao.actualizarIngrediente(modificado);
	}
	@Override
	public List<TransferIngrediente> listaIngredientes() {
		return dao.obtenerIngredientes();
	}

	@Override
	public TransferIngrediente buscarIngredientePorNombre(String nombreIngrediente) {
		return dao.buscarIngredientePorNombre(nombreIngrediente);
	}

	@Override
	public boolean comprobarIngrediente(TransferIngrediente ingrediente) {
		return this.dao.comprobarIngrediente(ingrediente);
	}

}
