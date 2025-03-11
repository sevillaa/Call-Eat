package Negocio;

import java.security.NoSuchAlgorithmException;

import Integracion.DAOCliente;
import Integracion.FactoriaDAO;



public class SAClienteImp implements SACliente {

	public boolean crearUsuario(TransferCliente cliente) {
		DAOCliente daoCliente = (DAOCliente) FactoriaDAO.getInstancia().nuevoDAOClientes();
		boolean ok = false;
		try {
			ok = daoCliente.registrarCliente(cliente);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return ok;
		
	}
	
	public boolean accesoCliente(String correo, String contraseña) {
		DAOCliente daoCliente = (DAOCliente) FactoriaDAO.getInstancia().nuevoDAOClientes();
		TransferCliente ok;
		try {
			ok = daoCliente.buscarCliente(correo);

			if(ok.getContraseña().equals(contraseña)) { 		
	            return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean borrarCliente(TransferCliente cliente) {
		DAOCliente daoCliente = (DAOCliente) FactoriaDAO.getInstancia().nuevoDAOClientes();
		boolean ok = false;
		try {
			ok = daoCliente.eliminarCliente(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;	
		
	}

	@Override
	public String buscarIdUsuario(String correo, String contraseña) {
		DAOCliente daoCliente = (DAOCliente) FactoriaDAO.getInstancia().nuevoDAOClientes();
		TransferCliente ok;
		try {
			ok = daoCliente.buscarCliente(correo);
			if(ok.getContraseña().equals(contraseña)) { 		
	            return ok.id;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
