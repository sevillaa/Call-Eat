package integración;

import java.security.NoSuchAlgorithmException;

import Negocio.TransferCliente;


public interface DAOCliente {
	public TransferCliente buscarCliente(String correo);
	public boolean registrarCliente(TransferCliente cliente) throws NoSuchAlgorithmException;
	public boolean eliminarCliente(TransferCliente cliente);
}
