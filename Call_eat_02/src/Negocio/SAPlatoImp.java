package Negocio;

import Integracion.FachadaDAOPlatoImp;

public class SAPlatoImp implements SAPlato{

	private FachadaDAOPlatoImp fachadaDaoPlato = new FachadaDAOPlatoImp();
	
	@Override
	public boolean crearPlato(TransferPlato plato) {
		// TODO Auto-generated method stub
		

		
		
		return this.fachadaDaoPlato.crearPlato(plato);
	}

	@Override
	public boolean eliminarPlato(TransferPlato plato) {
		// TODO Auto-generated method stub
		

		
		return this.fachadaDaoPlato.eliminarPlato(plato);
	}

	@Override
	public TransferPlato buscarPlato(String idPlato) {
		// TODO Auto-generated method stub
		

		
		return this.fachadaDaoPlato.buscarPlato(idPlato);
	}

}
