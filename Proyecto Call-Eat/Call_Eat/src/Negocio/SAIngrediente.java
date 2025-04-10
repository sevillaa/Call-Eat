package Negocio;

public interface SAIngrediente {
	public boolean crearIngrediente(TransferIngrediente cliente);
	public boolean accesoCliente(String correo, String contraseña);
	public TransferEmpleado accesoClienteSeguro(String correo, String contraseña);
	public boolean borrarCliente(TransferEmpleado cliente);
	public String buscarIdUsuario(String correo,String contraseña);
}
