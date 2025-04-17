package Negocio;

public interface SAPlato {

	boolean crearPlato(TransferPlato plato);

	boolean eliminarPlato(TransferPlato plato);

	TransferPlato buscarPlato(String idPlato);

}
