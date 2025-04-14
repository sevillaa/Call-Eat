package Integracion;

import Negocio.TransferPlato;

public interface FachadaDAOPlato {
	public boolean crearPlato(TransferPlato plato);

	public boolean eliminarPlato(TransferPlato plato);

	public TransferPlato buscarPlato(String idPlato);
}
