package Negocio;

import Integracion.FachadaDAOMesaImp;

public class SAMesaImp implements SAMesa{

	
	private FachadaDAOMesaImp fachadaDaoMesa = new FachadaDAOMesaImp();
	
	@Override
	public boolean crearMesa(TransferMesa mesa) {
		// TODO Auto-generated method stub
		

		
		
		return this.fachadaDaoMesa.crearMesa(mesa);
	}

	@Override
	public boolean eliminarMesa(TransferMesa mesa) {
		// TODO Auto-generated method stub
		

		
		
		return this.fachadaDaoMesa.eliminarMesa(mesa);
	}

	@Override
	public TransferMesa buscarMesa(String idMesa) {
		// TODO Auto-generated method stub
		

		
		
		return this.fachadaDaoMesa.buscarMesa(idMesa);
	}

}
