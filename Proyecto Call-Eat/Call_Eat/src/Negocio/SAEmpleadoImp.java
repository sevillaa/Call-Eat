package Negocio;

import java.security.NoSuchAlgorithmException;
import Integracion.DAOEmpleado;
import Integracion.FactoriaDAO;

public class SAEmpleadoImp implements SAEmpleado {

    public boolean crearUsuario(TransferEmpleado cliente) {
        DAOEmpleado daoCliente = (DAOEmpleado) FactoriaDAO.getInstancia().nuevoDAOClientes();
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
        DAOEmpleado daoCliente = (DAOEmpleado) FactoriaDAO.getInstancia().nuevoDAOClientes();
        TransferEmpleado cliente;
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
    
    public TransferEmpleado accesoClienteSeguro(String correo, String contraseña) {
        DAOEmpleado daoCliente = (DAOEmpleado) FactoriaDAO.getInstancia().nuevoDAOClientes();
        TransferEmpleado cliente;
        try {
            cliente = daoCliente.buscarCliente(correo);
            if (cliente != null && cliente.getContraseña().equals(contraseña)) {
                // Retornamos una copia del cliente sin la contraseña, pero con el rol
                return new TransferEmpleado(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getCorreo(),
                    null, // por seguridad
                    cliente.getRol() // ✅ AÑADIDO
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public boolean borrarCliente(TransferEmpleado cliente) {
        DAOEmpleado daoCliente = (DAOEmpleado) FactoriaDAO.getInstancia().nuevoDAOClientes();
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
        DAOEmpleado daoCliente = (DAOEmpleado) FactoriaDAO.getInstancia().nuevoDAOClientes();
        TransferEmpleado cliente;
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


