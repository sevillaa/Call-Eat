package Negocio;

import java.util.List;
import Integracion.FachadaDAOPlato;
import Integracion.FachadaDAOPlatoImp;

public class SAPlatoImp implements SAPlato{

	private FachadaDAOPlato fachadaDaoPlato = new FachadaDAOPlatoImp();
	
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

	@Override
	public List<TransferPlato> obtenerPlatos() {
		return this.fachadaDaoPlato.obtenerPlatos();
	}

}
