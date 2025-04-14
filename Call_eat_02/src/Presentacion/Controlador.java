package Presentacion;

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

	// METODOS DE INGREDIENTE:

	public boolean crearIngrediente(TransferIngrediente ingrediente);

	public boolean eliminarIngrediente(TransferIngrediente ingrediente);

	public TransferIngrediente buscarIngrediente(String idIngrediente);

	// METODOS DE MESA:

	public boolean crearMesa(TransferMesa mesa);

	public boolean eliminarMesa(TransferMesa mesa);

	public TransferMesa buscarMesa(String idMesa);

	// METODOS DE PEDIDO:

	public boolean crearPedido(TransferPedido pedido);

	public boolean eliminarPedido(TransferPedido pedido);

	public TransferPedido buscarPedido(String idPedido);

	// METODOS DE PLATO:

	public boolean crearPlato(TransferPlato plato);

	public boolean eliminarPlato(TransferPlato plato);

	public TransferPlato buscarPlato(String idPlato);
	
	
	
	
	// METODOS DE CONTROLADOR

	void accion(int evento, Object datos);

}
