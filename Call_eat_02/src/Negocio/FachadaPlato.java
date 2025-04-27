package Negocio;

import java.util.List;

public interface FachadaPlato {
	
	public boolean crearPlato(TransferPlato plato);

	public boolean eliminarPlato(TransferPlato plato);

	public TransferPlato buscarPlato(String idPlato);
	
	List<TransferPlato> obtenerPlatos();
	
	void actualizarPlato(TransferPlato plato);
}
