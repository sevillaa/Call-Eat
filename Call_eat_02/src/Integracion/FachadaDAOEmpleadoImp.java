package Integracion;

import java.util.List;

import Negocio.TransferEmpleado;

public class FachadaDAOEmpleadoImp implements FachadaDAOEmpleado {

    private SADAOEmpleado saDaoEmpleado = new SADAOEmpleadoImp();

    @Override
    public TransferEmpleado buscarEmpleado(String correo) {
        return saDaoEmpleado.buscarEmpleado(correo);
    }

    @Override
    public boolean registrarEmpleado(TransferEmpleado empleado) {
        return saDaoEmpleado.registrarEmpleado(empleado);
    }

    @Override
    public boolean eliminarEmpleado(TransferEmpleado empleado) {
        return saDaoEmpleado.eliminarEmpleado(empleado);
    }

	@Override
	public List<TransferEmpleado> listaEmpleados() {
		return saDaoEmpleado.listaEmpleados();
	}

	public boolean modificarEmpleado(TransferEmpleado modificado) {
		return saDaoEmpleado.modificarEmpleado(modificado);
	}
    

	
}
