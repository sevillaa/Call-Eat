package Integracion;

import java.util.List;

import Negocio.TransferEmpleado;

public interface SADAOEmpleado {
	
	TransferEmpleado buscarEmpleado(String correo);
    boolean registrarEmpleado(TransferEmpleado empleado);
    boolean eliminarEmpleado(TransferEmpleado empleado);
    List<TransferEmpleado> listaEmpleados();
}
