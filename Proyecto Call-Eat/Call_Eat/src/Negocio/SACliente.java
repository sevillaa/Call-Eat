package Negocio;

public interface SACliente {
	public boolean crearUsuario(TransferUsuarios cliente);
	public boolean accesoCliente(String correo, String contraseña);
	public boolean borrarCliente(TransferUsuarios cliente);
	public String buscarIdUsuario(String correo,String contraseña);
}
