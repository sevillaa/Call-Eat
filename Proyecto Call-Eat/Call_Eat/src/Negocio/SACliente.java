package Negocio;

public interface SACliente {
	public boolean crearUsuario(TransferCliente cliente);
	public boolean accesoCliente(String correo, String contraseña);
	public boolean borrarCliente(TransferCliente cliente);
	public String buscarIdUsuario(String correo,String contraseña);
}
