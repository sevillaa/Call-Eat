package Integracion;

import Integracion.DAOCliente;
import Integracion.DAOClienteImp;

import Integracion.FactoriaDAO;

public class FactoriaDAOImp extends FactoriaDAO {
	
	
	public DAOCliente nuevoDAOClientes() {
		return new DAOClienteImp();
	}

}
