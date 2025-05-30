package Integracion;

import java.util.List;

import Negocio.TransferPlato;

public class SADAOPlatoImp implements SADAOPlato {

	DAOPlatoImp daoPlato = new DAOPlatoImp();

	@Override
	public boolean crearPlato(TransferPlato plato) {
		return daoPlato.crearPlato(plato);
	}

	@Override
	public boolean eliminarPlato(TransferPlato plato) {
		return daoPlato.eliminarPlato(plato);
	}

	@Override
	public TransferPlato buscarPlato(String idPlato) {
		return daoPlato.buscarPlato(idPlato);
	}

	@Override
	public List<TransferPlato> obtenerPlatos() {
		return daoPlato.obtenerPlatos();
	}

	@Override
	public void actualizarPlato(TransferPlato plato) {
		daoPlato.actualizarPlato(plato);
	}

	@Override
	public boolean comprobarPlato(TransferPlato plato) {
		return this.daoPlato.comprobarPlato(plato);
	}

}
