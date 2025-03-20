package Integracion;

import java.security.NoSuchAlgorithmException;

import Negocio.TransferUsuarios;


public interface DAOUsuarios {
	public TransferUsuarios buscarCliente(String correo);
	public boolean registrarCliente(TransferUsuarios cliente) throws NoSuchAlgorithmException;
	public boolean eliminarCliente(TransferUsuarios cliente);
}