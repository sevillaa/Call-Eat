package Negocio;

public interface FachadaPlato {
	
	public boolean crearPlato(TransferPlato plato);

	public boolean eliminarPlato(TransferPlato plato);

	public TransferPlato buscarPlato(String idPlato);
}
