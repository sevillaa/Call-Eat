package Negocio;

import java.util.List;

import Integracion.FachadaDAOEmpleadoImp;

public class SAEmpleadoImp implements SAEmpleado {
	
	private FachadaDAOEmpleadoImp fachadaDaoEmpleado = new FachadaDAOEmpleadoImp();


	

	@Override
    public boolean crearUsuario(TransferEmpleado empleado) {
        // Delegamos la acción de registrar empleado a la fachada
        return fachadaDaoEmpleado.registrarEmpleado(empleado);
    }
    
	 @Override
	    public boolean accesoCliente(String correo, String contraseña) {
	        TransferEmpleado empleado = fachadaDaoEmpleado.buscarEmpleado(correo);
	        if (empleado != null && empleado.getContraseña().equals(contraseña)) {         
	            return true;
	        }
	        return false;
	    }
    
	 @Override
	    public TransferEmpleado accesoClienteSeguro(String correo, String contraseña) {
	        TransferEmpleado empleado = fachadaDaoEmpleado.buscarEmpleado(correo);
	        if (empleado != null && empleado.getContraseña().equals(contraseña)) {
	            // Retornamos una copia del empleado sin la contraseña, por seguridad.
	            return new TransferEmpleado(
	                empleado.getId(),
	                empleado.getNombre(),
	                empleado.getCorreo(),
	                null, // No se expone la contraseña.
	                empleado.getRol()
	            );
	        }
	        return null;
	    }

    
	 @Override
	    public boolean borrarCliente(TransferEmpleado empleado) {
	        return fachadaDaoEmpleado.eliminarEmpleado(empleado);
	    }

    
    @Override
    public String buscarIdUsuario(String correo, String contraseña) {
        TransferEmpleado empleado = fachadaDaoEmpleado.buscarEmpleado(correo);
        if (empleado != null && empleado.getContraseña().equals(contraseña)) {         
            return empleado.getId();
        }
        return null;
    }

	@Override
	public List<TransferEmpleado> listaEmpleados() {
		List<TransferEmpleado> empleados = fachadaDaoEmpleado.listaEmpleados();
		if(empleados != null)
			return empleados;
		return null;//En caso de que no haya ningun empleado aún
	}
    
}


