package Negocio;

public interface SAEmpleado {
	public boolean crearUsuario(TransferEmpleado cliente);
	public boolean accesoCliente(String correo, String contraseña);
	public TransferEmpleado accesoClienteSeguro(String correo, String contraseña);
	public boolean borrarCliente(TransferEmpleado cliente);
	public String buscarIdUsuario(String correo,String contraseña);
}
