package Integracion;

import Negocio.TransferPlato;

public class FachadaDAOPlatoImp implements FachadaDAOPlato  {

    private SADAOPlato saDaoPlato = new SADAOPlatoImp();

	public boolean crearPlato(TransferPlato plato) {
		return this.saDaoPlato.crearPlato(plato);
	}

	public boolean eliminarPlato(TransferPlato plato) {
		return this.saDaoPlato.eliminarPlato(plato);
	}

	public TransferPlato buscarPlato(String idPlato) {
		return this.saDaoPlato.buscarPlato(idPlato);
	}

}
