package Negocio;

import Integracion.FachadaDAOPlatoImp;

public class SAPlatoImp implements SAPlato{

	private FachadaDAOPlatoImp fachadaDaoPlato = new FachadaDAOPlatoImp();
	
	@Override
	public boolean crearPlato(TransferPlato plato) {
		return this.fachadaDaoPlato.crearPlato(plato);
	}

	@Override
	public boolean eliminarPlato(TransferPlato plato) {
		return this.fachadaDaoPlato.eliminarPlato(plato);
	}

	@Override
	public TransferPlato buscarPlato(String idPlato) {
		return this.fachadaDaoPlato.buscarPlato(idPlato);
	}

}
