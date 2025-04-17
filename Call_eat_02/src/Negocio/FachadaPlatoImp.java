package Negocio;


public class FachadaPlatoImp implements FachadaPlato {
	
	private SAPlato saPlato = new SAPlatoImp();

	public boolean crearPlato(TransferPlato plato) {
		return this.saPlato.crearPlato(plato);
	}

	public boolean eliminarPlato(TransferPlato plato) {
		return this.saPlato.eliminarPlato(plato);
	}

	public TransferPlato buscarPlato(String idPlato) {
		return this.saPlato.buscarPlato(idPlato);
	}

    
}


