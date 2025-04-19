package Presentacion;

import Negocio.TransferEmpleado;

public interface FachadaEmpleado {
	boolean registrarEmpleado(TransferEmpleado empleado);
    boolean loginEmpleado(String correo, String contraseña);
    TransferEmpleado obtenerEmpleadoSeguro(String correo, String contraseña);
    boolean eliminarEmpleado(TransferEmpleado empleado);
    String buscarIdEmpleado(String correo, String contraseña);
}
