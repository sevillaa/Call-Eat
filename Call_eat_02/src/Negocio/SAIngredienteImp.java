package Negocio;

import java.util.List;
import java.util.Map;

import Integracion.FachadaDAOIngredienteImp;

public class SAIngredienteImp implements SAIngrediente {

	private FachadaDAOIngredienteImp fachadaDaoIngrediente = new FachadaDAOIngredienteImp();

	@Override
	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		if (ingrediente == null || ingrediente.getId() == null || comprobarIngrediente(ingrediente)) {
			return false;
		}
		return this.fachadaDaoIngrediente.crearIngrediente(ingrediente);
	}

	@Override
	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		if (ingrediente == null || ingrediente.getId() == null || !comprobarIngrediente(ingrediente)) {
			return false;
		}
		return this.fachadaDaoIngrediente.eliminarIngrediente(ingrediente);
	}

	@Override
	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		return this.fachadaDaoIngrediente.buscarIngrediente(idIngrediente);
	}

	@Override
	public TransferIngrediente buscarIngredientePorNombre(String nombreIngrediente) {
		return this.fachadaDaoIngrediente.buscarIngredientePorNombre(nombreIngrediente);
	}

	@Override
	public List<TransferIngrediente> listaIngredientes() {
		List<TransferIngrediente> ingredientes = fachadaDaoIngrediente.listaIngredientes();
		if (ingredientes != null)
			return ingredientes;
		return null;// En caso de que no haya ningun ingrediente aún
	}

	@Override
	public boolean compruebaIngredientes(TransferPlato plato) {
		for (Map.Entry<String, Integer> entry : plato.getIngredientes().entrySet()) {
			String nombre = entry.getKey();
			int cantidadNecesaria = entry.getValue();

			TransferIngrediente ing = buscarIngredientePorNombre(nombre);

			if (ing == null || ing.getCantidad() < cantidadNecesaria) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean modificarIngrediente(TransferIngrediente modificado) {
		if (modificado == null || modificado.getId() == null || !comprobarIngrediente(modificado)) {
			return false;
		}
		return this.fachadaDaoIngrediente.modificarIngrediente(modificado);
	}

	@Override
	public void sumaIngredientes(TransferPlato plato) {
		for (Map.Entry<String, Integer> entry : plato.getIngredientes().entrySet()) {
			String nombre = entry.getKey();
			int cantidad = entry.getValue();

			TransferIngrediente ing = buscarIngredientePorNombre(nombre);
			ing.setCantidad(ing.getCantidad() + cantidad);
			modificarIngrediente(ing);
		}
	}

	@Override
	public void restaIngredientes(TransferPlato plato) {
		for (Map.Entry<String, Integer> entry : plato.getIngredientes().entrySet()) {
			String nombre = entry.getKey();
			int cantidad = entry.getValue();

			TransferIngrediente ing = buscarIngredientePorNombre(nombre);
			ing.setCantidad(ing.getCantidad() - cantidad);
			modificarIngrediente(ing);
		}
	}

	@Override
	public boolean comprobarIngrediente(TransferIngrediente ingrediente) {
		return this.fachadaDaoIngrediente.comprobarIngrediente(ingrediente);
	}

}
