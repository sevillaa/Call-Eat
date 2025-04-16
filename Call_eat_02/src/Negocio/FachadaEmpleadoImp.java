package Negocio;

import java.util.List;

public class FachadaEmpleadoImp implements FachadaEmpleado {

	private SAEmpleado saEmpleado = new SAEmpleadoImp();

	@Override
	public boolean crearUsuario(TransferEmpleado cliente) {
		return saEmpleado.crearUsuario(cliente);
	}

	@Override
	public boolean accesoCliente(String correo, String contraseña) {
		return saEmpleado.accesoCliente(correo, contraseña);
	}

	@Override
	public TransferEmpleado accesoClienteSeguro(String correo, String contraseña) {
		return saEmpleado.accesoClienteSeguro(correo, contraseña);
	}

	@Override
	public boolean borrarCliente(TransferEmpleado cliente) {
		return saEmpleado.borrarCliente(cliente);
	}

	@Override
	public String buscarIdUsuario(String correo, String contraseña) {
		return saEmpleado.buscarIdUsuario(correo, contraseña);
	}

	@Override
	public List<TransferEmpleado> listaEmpleados() {
		return saEmpleado.listaEmpleados();
	}


    
}


