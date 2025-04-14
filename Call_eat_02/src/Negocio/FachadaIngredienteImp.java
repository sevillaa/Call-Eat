package Negocio;



public class FachadaIngredienteImp implements FachadaIngrediente {
	
	private SAIngrediente saIngrediente = new SAIngredienteImp();

	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		return this.saIngrediente.crearIngrediente(ingrediente);
	}

	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		return this.saIngrediente.eliminarIngrediente(ingrediente);
	}

	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		return this.saIngrediente.buscarIngrediente(idIngrediente);
	}

    
}


