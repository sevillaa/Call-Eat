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
        // Este método se usa para la comprobación, pero no expone el TransferCliente seguro
        DAOCliente daoCliente = (DAOCliente) FactoriaDAO.getInstancia().nuevoDAOClientes();
        TransferCliente cliente;
        try {
            cliente = daoCliente.buscarCliente(correo);
            if (cliente != null && cliente.getContraseña().equals(contraseña)) {         
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Nuevo método: devuelve un TransferCliente sin la contraseña
    public TransferCliente accesoClienteSeguro(String correo, String contraseña) {
        DAOCliente daoCliente = (DAOCliente) FactoriaDAO.getInstancia().nuevoDAOClientes();
        TransferCliente cliente;
        try {
            cliente = daoCliente.buscarCliente(correo);
            if (cliente != null && cliente.getContraseña().equals(contraseña)) {
                // Retornamos una copia del cliente sin la contraseña
                return new TransferCliente(cliente.getId(), cliente.getNombre(), cliente.getCorreo(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        TransferCliente cliente;
        try {
            cliente = daoCliente.buscarCliente(correo);
            if (cliente != null && cliente.getContraseña().equals(contraseña)) {         
                return cliente.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


