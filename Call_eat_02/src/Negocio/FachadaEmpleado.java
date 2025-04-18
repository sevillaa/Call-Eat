package Negocio;

import java.util.List;

public interface FachadaEmpleado {
	
	public boolean crearUsuario(TransferEmpleado cliente);
	public boolean accesoCliente(String correo, String contraseña);
	public TransferEmpleado accesoClienteSeguro(String correo, String contraseña);
	public boolean borrarCliente(TransferEmpleado cliente);
	public String buscarIdUsuario(String correo,String contraseña);
	public List<TransferEmpleado> listaEmpleados();
	public boolean modificaEmpleado(TransferEmpleado modificado);
	public boolean eliminaEmpleado(TransferEmpleado empleado);
}
