package Negocio;


public class FachadaMesaImp implements FachadaMesa {
	
	private SAMesa saMesa = new SAMesaImp();

	public boolean crearMesa(TransferMesa mesa) {
		return this.saMesa.crearMesa(mesa);
	}

	public boolean eliminarMesa(TransferMesa mesa) {
		return this.saMesa.eliminarMesa(mesa);
	}

	public TransferMesa buscarMesa(String idMesa) {
		return this.saMesa.buscarMesa(idMesa);
	}

    
}


