package Negocio;

import java.util.List;
import Integracion.FachadaDAOPlato;
import Integracion.FachadaDAOPlatoImp;

public class SAPlatoImp implements SAPlato{

	private FachadaDAOPlato fachadaDaoPlato = new FachadaDAOPlatoImp();
	
	@Override
	public boolean crearPlato(TransferPlato plato) {
		if(plato==null || plato.getId()==null || comprobarPlato(plato)) {
			return false;
		}
		return this.fachadaDaoPlato.crearPlato(plato);
	}

	@Override
	public boolean eliminarPlato(TransferPlato plato) {
		if(plato==null || plato.getId()==null || !comprobarPlato(plato)) {
			return false;
		}
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

	@Override
	public void actualizarPlato(TransferPlato plato) {
		fachadaDaoPlato.actualizarPlato(plato);
	}

	@Override
	public boolean comprobarPlato(TransferPlato plato) {
		return this.fachadaDaoPlato.comprobarPlato(plato);
	}

}
