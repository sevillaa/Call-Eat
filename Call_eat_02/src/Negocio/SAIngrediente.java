package Negocio;

public interface SAIngrediente {

	boolean crearIngrediente(TransferIngrediente ingrediente);

	boolean eliminarIngrediente(TransferIngrediente ingrediente);

	TransferIngrediente buscarIngrediente(String idIngrediente);

}
