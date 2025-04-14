package Integracion;

import java.security.NoSuchAlgorithmException;
import Negocio.TransferEmpleado;

public class SADAOEmpleadoImp implements SADAOEmpleado{
	
	DAOEmpleadoImp dao = new DAOEmpleadoImp();
    
    public TransferEmpleado buscarEmpleado(String correo) {
        return dao.buscarEmpleado(correo);
    }

    public boolean registrarEmpleado(TransferEmpleado empleado) {
        try {
			return dao.registrarEmpleado(empleado);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
    }

    public boolean eliminarEmpleado(TransferEmpleado empleado) {
        return dao.eliminarEmpleado(empleado);
    }

}
