package Integracion;

import java.util.List;

import Negocio.TransferPlato;

public interface SADAOPlato {

	boolean crearPlato(TransferPlato plato);

	boolean eliminarPlato(TransferPlato plato);

	TransferPlato buscarPlato(String idPlato);

	List<TransferPlato> obtenerPlatos();
	
	void actualizarPlato(TransferPlato plato);
}
