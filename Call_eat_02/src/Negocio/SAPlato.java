package Negocio;

import java.util.List;

public interface SAPlato {

	boolean crearPlato(TransferPlato plato);

	boolean eliminarPlato(TransferPlato plato);

	TransferPlato buscarPlato(String idPlato);

	List<TransferPlato> obtenerPlatos();
	
	void actualizarPlato(TransferPlato plato);

}
