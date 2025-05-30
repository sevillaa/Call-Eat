package Integracion;

import java.util.List;

import Negocio.TransferPlato;

public interface FachadaDAOPlato {
	public boolean crearPlato(TransferPlato plato);
	
	public void actualizarPlato(TransferPlato plato);

	public TransferPlato buscarPlato(String idPlato);

	public boolean eliminarPlato(TransferPlato plato);
	
	public List<TransferPlato> obtenerPlatos();
	
	public boolean comprobarPlato(TransferPlato plato);
}
