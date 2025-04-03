package Integracion;

import Integracion.DAOUsuarios;

import Integracion.FactoriaDAO;
import Integracion.FactoriaDAOImp;

public abstract class FactoriaDAO {

	private static FactoriaDAO instancia = null;

	static public FactoriaDAO getInstancia() {
		if (instancia == null) //patron singleton
			instancia = new FactoriaDAOImp();
		
		return instancia;
	}

	public abstract DAOUsuarios nuevoDAOClientes();

}
