package Negocio;

import java.util.List;

public interface SAIngrediente {

	boolean crearIngrediente(TransferIngrediente ingrediente);

	boolean eliminarIngrediente(TransferIngrediente ingrediente);

	TransferIngrediente buscarIngrediente(String idIngrediente);
	
	public List<TransferIngrediente> listaIngredientes();
	
	public boolean modificarIngrediente(TransferIngrediente modificado);
	
	boolean compruebaIngredientes(TransferPlato plato);

	void restaIngredientes(TransferPlato plato);

	void sumaIngredientes(TransferPlato plato);

}
