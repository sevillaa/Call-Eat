package Integracion;

import Negocio.TransferPlato;

public interface SADAOPlato {

	boolean crearPlato(TransferPlato plato);

	boolean eliminarPlato(TransferPlato plato);

	TransferPlato buscarPlato(String idPlato);

}
