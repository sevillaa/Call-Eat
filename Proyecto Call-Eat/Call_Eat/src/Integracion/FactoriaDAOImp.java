package Integracion;

import Integracion.DAOEmpleado;
import Integracion.DAOEmpleadoImp;

import Integracion.FactoriaDAO;

public class FactoriaDAOImp extends FactoriaDAO {
	
	
	public DAOEmpleado nuevoDAOClientes() {
		return new DAOEmpleadoImp();
	}

}
