package Presentacion;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import Negocio.FachadaEmpleadoImp;
import Negocio.FachadaIngredienteImp;
import Negocio.FachadaMesaImp;
import Negocio.FachadaPedidoImp;
import Negocio.FachadaPlatoImp;
import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;
import Negocio.TransferMesa;
import Negocio.TransferPedido;
import Negocio.TransferPlato;

public class ControladorImp implements Controlador {

	private FachadaEmpleadoImp fachadaEmpleado = new FachadaEmpleadoImp();
	private FachadaIngredienteImp fachadaIngrediente = new FachadaIngredienteImp();
	private FachadaMesaImp fachadaMesa = new FachadaMesaImp();
	private FachadaPedidoImp fachadaPedido = new FachadaPedidoImp();
	private FachadaPlatoImp fachadaPlato = new FachadaPlatoImp();
	
	
	// METODOS DE EMPLEADO:
	
	public boolean crearUsuario(TransferEmpleado empleado) {
		return this.fachadaEmpleado.crearUsuario(empleado);
	}
	
	public boolean accesoCliente(String correo, String contraseña) {
		return this.fachadaEmpleado.accesoCliente(correo, contraseña);
	}
	
	public TransferEmpleado accesoClienteSeguro(String correo, String contraseña) {
		return this.fachadaEmpleado.accesoClienteSeguro(correo, contraseña);
	}
	
	public String buscarIdUsuario(String correo, String contraseña) {
		return this.fachadaEmpleado.buscarIdUsuario(correo, contraseña);
	}
	
	public boolean borrarCliente(TransferEmpleado empleado) {
		return this.fachadaEmpleado.borrarCliente(empleado);
	}
	
	public List<TransferEmpleado> listaEmpleados(){
		return this.fachadaEmpleado.listaEmpleados();
	}
	
	public boolean modificarEmpleado(TransferEmpleado modificado) {
		return this.fachadaEmpleado.modificaEmpleado(modificado);
	}
	
	public boolean eliminarEmpleado(TransferEmpleado empleado) {
		return this.fachadaEmpleado.eliminaEmpleado(empleado);
	}

	
	// METODOS DE INGREDIENTE:
	
	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		return this.fachadaIngrediente.crearIngrediente(ingrediente);
	}
	
	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		return this.fachadaIngrediente.eliminarIngrediente(ingrediente);
	}
	
	public TransferIngrediente buscarIngrediente(String idIngrediente) {
		return this.fachadaIngrediente.buscarIngrediente(idIngrediente);
	}
	public boolean modificarIngrediente(TransferIngrediente modificado) {
		return this.fachadaIngrediente.modificarIngrediente(modificado);
	}
	public List<TransferIngrediente> listaIngredientes(){
		return this.fachadaIngrediente.listaIngredientes();
	}
	public boolean compruebaIngredientes(TransferPlato plato) {
		return this.fachadaIngrediente.compruebaIngredientes(plato);
	}

	public void restaIngredientes(TransferPlato plato) {
		this.fachadaIngrediente.restaIngredientes(plato);
	}

	public void sumaIngredientes(TransferPlato plato) {
		this.fachadaIngrediente.sumaIngredientes(plato);
	}
	
	//METODOS DE MESA:
	
	public boolean crearMesa(TransferMesa mesa) {
		return this.fachadaMesa.crearMesa(mesa);
	}
	
	public boolean eliminarMesa(TransferMesa mesa) {
		return this.fachadaMesa.eliminarMesa(mesa);
	}
	
	public TransferMesa buscarMesa(String idMesa) {
		return this.fachadaMesa.buscarMesa(idMesa);
	}
	
	// METODOS DE PEDIDO:
	
	public boolean crearPedido(TransferPedido pedido) {
		return this.fachadaPedido.crearPedido(pedido);
	}
	
	public boolean eliminarPedido(TransferPedido pedido) {
		return this.fachadaPedido.eliminarPedido(pedido);
	}
	
	public TransferPedido buscarPedido(String idPedido) {
		return this.fachadaPedido.buscarPedido(idPedido);
	}
	
	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2){
		return this.fachadaPedido.listaPedidos(fecha1,fecha2);
	}
	
	public boolean modificarPedido(TransferPedido pedido) {
		return this.fachadaPedido.modificarPedido(pedido);
	}
	
	// METODOS DE PLATO:
	
	public boolean crearPlato(TransferPlato plato) {
		return this.fachadaPlato.crearPlato(plato);
	}
	
	public boolean eliminarPlato(TransferPlato plato) {
		return this.fachadaPlato.eliminarPlato(plato);
	}
	
	public TransferPlato buscarPlato(String idPlato) {
		return this.fachadaPlato.buscarPlato(idPlato);
	}
	
	@Override
	public List<TransferPlato> obtenerPlatos(){
		return fachadaPlato.obtenerPlatos();
	}

	// METODOS DE CONTROLLER:
	
    @Override
    public void accion(int evento, Object datos) {
    	
        switch (evento) {

            case (Eventos.ADD_CLIENTE): {
                @SuppressWarnings("unchecked")
                HashMap<String, String> ids = (HashMap<String, String>) datos;

                String nombre = ids.get("nombre");
                String correo = ids.get("correo");
                String contraseña = ids.get("contraseña");
                String rol = ids.get("rol"); 

                String idUsuario = generarCodigoRandom();

                TransferEmpleado cliente = new TransferEmpleado(idUsuario, nombre, correo, contraseña, rol); 
                
                if (this.crearUsuario(cliente)) {
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente \n      id : " + idUsuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear el usuario");
                }
                break; 

            }

            case (Eventos.INICIAR_SESION): {
                @SuppressWarnings("unchecked")
                HashMap<String, String> ids = (HashMap<String, String>) datos;

                String correo = ids.get("correo");
                String contraseña = ids.get("contraseña");

                TransferEmpleado clienteSeguro = this.accesoClienteSeguro(correo, contraseña);

                if (clienteSeguro != null) {
                    GUIEmpleado.getInstancia(this, clienteSeguro).actualizar(Eventos.CLIENTE_REGISTRADO, clienteSeguro);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar el usuario");
                }
                break;
            }

            default: {
                break;
            }
        }
    }
    
    @Override
    public String generarCodigoRandom() {
        int length = 8;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

	
	
    
    
}