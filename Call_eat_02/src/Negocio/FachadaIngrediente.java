package Negocio;

import java.util.List;

public interface FachadaIngrediente {
	
	public boolean crearIngrediente(TransferIngrediente ingrediente);

	public boolean eliminarIngrediente(TransferIngrediente ingrediente);

	public TransferIngrediente buscarIngrediente(String idIngrediente);
	
	public boolean modificarIngrediente(TransferIngrediente modificado);
	
	public List<TransferIngrediente> listaIngredientes();
	
	boolean compruebaIngredientes(TransferPlato plato);

	void restaIngredientes(TransferPlato plato);

	void sumaIngredientes(TransferPlato plato);
}
