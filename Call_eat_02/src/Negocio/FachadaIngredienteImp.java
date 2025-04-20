package Negocio;

import java.util.List;

public class FachadaIngredienteImp implements FachadaIngrediente {
	
	private SAIngrediente saIngrediente = new SAIngredienteImp();

	@Override
	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		return this.saIngrediente.crearIngrediente(ingrediente);
	}
	
	@Override
	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		return this.saIngrediente.eliminarIngrediente(ingrediente);
	}
	
	@Override
	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		return this.saIngrediente.buscarIngrediente(idIngrediente);
	}
	
	@Override
	public boolean modificarIngrediente(TransferIngrediente modificado) {
		return this.saIngrediente.modificarIngrediente(modificado);
	}
	@Override
	public boolean compruebaIngredientes(TransferPlato plato) {
		return saIngrediente.compruebaIngredientes(plato);
	}

	@Override
	public void restaIngredientes(TransferPlato plato) {
		saIngrediente.restaIngredientes(plato);
	}

	@Override
	public void sumaIngredientes(TransferPlato plato) {
		saIngrediente.sumaIngredientes(plato);
	}

	@Override
	public List<TransferIngrediente> listaIngredientes() {
		return saIngrediente.listaIngredientes();
	}
	
	

    
}


