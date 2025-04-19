package Presentacion;

import Negocio.TransferEmpleado;

public class FachadaEmpleadoImp implements FachadaEmpleado{

	@Override
	public boolean registrarEmpleado(TransferEmpleado empleado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean loginEmpleado(String correo, String contraseña) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferEmpleado obtenerEmpleadoSeguro(String correo, String contraseña) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarEmpleado(TransferEmpleado empleado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String buscarIdEmpleado(String correo, String contraseña) {
		// TODO Auto-generated method stub
		return null;
	}

}
