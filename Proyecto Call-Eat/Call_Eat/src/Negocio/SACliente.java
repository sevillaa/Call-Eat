package Negocio;

public interface SACliente {
	public boolean crearUsuario(TransferUsuarios cliente);
	public boolean accesoCliente(String correo, String contraseña);
<<<<<<< HEAD
	public TransferCliente accesoClienteSeguro(String correo, String contraseña);
	public boolean borrarCliente(TransferCliente cliente);
=======
	public boolean borrarCliente(TransferUsuarios cliente);
>>>>>>> 0bd3ff1f93bcda9471a762bc8163217f13b25340
	public String buscarIdUsuario(String correo,String contraseña);
}
