package Presentacion;

import java.util.Date;
import java.util.List;

import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;
import Negocio.TransferMesa;
import Negocio.TransferPedido;
import Negocio.TransferPlato;

public interface Controlador {

	//METODOS DE EMPLEADO:

	public boolean crearUsuario(TransferEmpleado empleado);

	public boolean accesoCliente(String correo, String contraseña);

	public TransferEmpleado accesoClienteSeguro(String correo, String contraseña);

	public String buscarIdUsuario(String correo, String contraseña);

	public boolean borrarCliente(TransferEmpleado empleado);
	
	public List<TransferEmpleado> listaEmpleados();
	
	public boolean modificarEmpleado(TransferEmpleado modificado);
	
	public boolean eliminarEmpleado(TransferEmpleado empleadoAEliminar);

	// METODOS DE INGREDIENTE:

	public boolean crearIngrediente(TransferIngrediente ingrediente);

	public boolean eliminarIngrediente(TransferIngrediente ingrediente);

	public TransferIngrediente buscarIngrediente(String idIngrediente);
	
	public boolean modificarIngrediente(TransferIngrediente modificado);
	
	public List<TransferIngrediente> listaIngredientes();
	
	public boolean compruebaIngredientes(TransferPlato plato);

	public void restaIngredientes(TransferPlato plato);

	public void sumaIngredientes(TransferPlato plato);

	// METODOS DE MESA:

	public boolean crearMesa(TransferMesa mesa);

	public boolean eliminarMesa(TransferMesa mesa);

	public TransferMesa buscarMesa(String idMesa);
	
	public List<TransferMesa> listaMesas();

	// METODOS DE PEDIDO:

	public boolean crearPedido(TransferPedido pedido);

	public boolean eliminarPedido(TransferPedido pedido);

	public TransferPedido buscarPedido(String idPedido);
	
	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2);
	
	public boolean modificarPedido(TransferPedido pedido);

	// METODOS DE PLATO:

	public boolean crearPlato(TransferPlato plato);

	public boolean eliminarPlato(TransferPlato plato);

	public TransferPlato buscarPlato(String idPlato);
	
	public List<TransferPlato> obtenerPlatos();
	
	
	
	
	// METODOS DE CONTROLADOR

	void accion(int evento, Object datos);
	
	public  String generarCodigoRandom();





}

