package Integracion;

import Integracion.DAOUsuarios;
import Integracion.DAOUsuariosImp;

import Integracion.FactoriaDAO;

public class FactoriaDAOImp extends FactoriaDAO {
	
	
	public DAOUsuarios nuevoDAOClientes() {
		return new DAOUsuariosImp();
	}

}
