package Integracion;

import Negocio.TransferPlato;

public interface FachadaDAOPlato {
	public boolean crearPlato(TransferPlato plato);

	public TransferPlato buscarPlato(String idPlato);

	public boolean eliminarPlato(TransferPlato plato);
}
