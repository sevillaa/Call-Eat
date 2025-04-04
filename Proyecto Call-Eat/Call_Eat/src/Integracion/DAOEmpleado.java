package Integracion;

import java.security.NoSuchAlgorithmException;

import Negocio.TransferEmpleado;


public interface DAOEmpleado {
	public TransferEmpleado buscarCliente(String correo);
	public boolean registrarCliente(TransferEmpleado cliente) throws NoSuchAlgorithmException;
	public boolean eliminarCliente(TransferEmpleado cliente);
}