package Integracion;

import java.util.List;

import Negocio.TransferPlato;

public class FachadaDAOPlatoImp implements FachadaDAOPlato  {

    private SADAOPlato saDaoPlato = new SADAOPlatoImp();

    @Override
	public boolean crearPlato(TransferPlato plato) {
		return this.saDaoPlato.crearPlato(plato);
	}
	
    @Override
	public TransferPlato buscarPlato(String idPlato) {
		return this.saDaoPlato.buscarPlato(idPlato);
	}

    @Override
	public boolean eliminarPlato(TransferPlato plato) {
		return this.saDaoPlato.eliminarPlato(plato);
	}

    @Override
	public List<TransferPlato> obtenerPlatos() {
		return saDaoPlato.obtenerPlatos();
	}

	@Override
	public void actualizarPlato(TransferPlato plato) {
		saDaoPlato.actualizarPlato(plato);
	}

	@Override
	public boolean comprobarPlato(TransferPlato plato) {
		return this.saDaoPlato.comprobarPlato(plato);
	}
}
