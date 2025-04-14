package Integracion;

import Negocio.TransferEmpleado;

public interface FachadaDAOEmpleado {
	
    TransferEmpleado buscarEmpleado(String correo);
    boolean registrarEmpleado(TransferEmpleado empleado);
    boolean eliminarEmpleado(TransferEmpleado empleado);

}