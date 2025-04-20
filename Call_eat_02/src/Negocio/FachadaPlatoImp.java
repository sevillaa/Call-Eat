package Negocio;

import java.util.List;

public class FachadaPlatoImp implements FachadaPlato {
	
	private SAPlato saPlato = new SAPlatoImp();
	
	@Override
	public boolean crearPlato(TransferPlato plato) {
		return this.saPlato.crearPlato(plato);
	}

	@Override
	public boolean eliminarPlato(TransferPlato plato) {
		return this.saPlato.eliminarPlato(plato);
	}

	@Override
	public TransferPlato buscarPlato(String idPlato) {
		return this.saPlato.buscarPlato(idPlato);
	}

	public List<TransferPlato> obtenerPlatos() {
		return this.saPlato.obtenerPlatos();
	}

}


