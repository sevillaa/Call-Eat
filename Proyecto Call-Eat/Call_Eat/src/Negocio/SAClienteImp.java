package Negocio;

import java.security.NoSuchAlgorithmException;

import Integracion.DAOUsuarios;
import Integracion.FactoriaDAO;



public class SAClienteImp implements SACliente {

	public boolean crearUsuario(TransferUsuarios cliente) {
		DAOUsuarios daoCliente = (DAOUsuarios) FactoriaDAO.getInstancia().nuevoDAOClientes();
		boolean ok = false;
		try {
			ok = daoCliente.registrarCliente(cliente);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return ok;
		
	}
	
	public boolean accesoCliente(String correo, String contraseña) {
		DAOUsuarios daoCliente = (DAOUsuarios) FactoriaDAO.getInstancia().nuevoDAOClientes();
		TransferUsuarios ok;
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
	
	public boolean borrarCliente(TransferUsuarios cliente) {
		DAOUsuarios daoCliente = (DAOUsuarios) FactoriaDAO.getInstancia().nuevoDAOClientes();
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
		DAOUsuarios daoCliente = (DAOUsuarios) FactoriaDAO.getInstancia().nuevoDAOClientes();
		TransferUsuarios ok;
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
