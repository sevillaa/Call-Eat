package Negocio;

public interface FachadaIngrediente {
	public boolean crearIngrediente(TransferIngrediente ingrediente);

	public boolean eliminarIngrediente(TransferIngrediente ingrediente);

	public TransferIngrediente buscarIngrediente(String idIngrediente);
	
	boolean compruebaIngredientes(TransferPlato plato);

	void restaIngredientes(TransferPlato plato);

	void sumaIngredientes(TransferPlato plato);
}
