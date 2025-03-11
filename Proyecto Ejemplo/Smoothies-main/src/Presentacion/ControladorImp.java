package Presentacion;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import Negocio.FactoriaSA;
import Negocio.SAClientes;
import Negocio.SAPedidos;
import Negocio.SAPedidosImp;
import Negocio.SAProductos;
import Negocio.SAProductosImp;
import Negocio.SASmoothies;
import Negocio.SASmoothiesImp;
import Negocio.TransferCliente;
import Negocio.TransferPedido;
import Negocio.TransferProducto;
import Negocio.TransferSmoothies;


public class ControladorImp extends Controlador{

	
	public void accion(int evento, Object datos) {
		
		switch (evento) {
	
		case (Eventos.AÑADIR_CLIENTE):{
			HashMap<String, String> ids = (HashMap<String, String>) datos;
			String nombre = new String(ids.get("nombre"));
			String correo = new String(ids.get("correo"));
			String contraseña = new String(ids.get("contraseña"));
			String idUsuario = new String(ids.get("idUsuario"));
			TransferCliente cliente = new TransferCliente(idUsuario, nombre, correo , contraseña) ; 

			
			SAClientes saClientes = FactoriaSA.getInstancia().nuevoSAClientes();
			if(saClientes.crearUsuario(cliente)) {
				JOptionPane.showMessageDialog(null,"Usuario creado correctamente \n      id : "+ idUsuario);
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al crear el usuario");
			}
			GUISmoothies.getInstancia(Controlador.getInstancia()).actualizar(Eventos.AÑADIR_CLIENTE, null);
		}
		
		
		case (Eventos.INICIAR_SESION):{
			HashMap<String, String> ids = (HashMap<String, String>) datos;
			String correo = new String(ids.get("correo"));
			String contraseña = new String(ids.get("contraseña"));
			SAClientes saClientes = FactoriaSA.getInstancia().nuevoSAClientes();
			if(saClientes.accesoCliente(correo, contraseña)) {
				
				GUIClientes.getInstancia(Controlador.getInstancia(), datos).actualizar(Eventos.CLIENTE_REGISTRADO, datos);
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al cargar el usuario");
				break ; 
			}
		}
		case (Eventos.CAMPOS_VACIOS): {
			JOptionPane.showMessageDialog(null, "Alg�n campo est� vacio");
			GUISmoothies.getInstancia(Controlador.getInstancia()).actualizar(Eventos.CLIENTES_LIMPIAR, null);
			break ; 
		}
		
		case (Eventos.INICIAR_ADMINISTRADOR):{
			GUIAdministrador.getInstancia(Controlador.getInstancia()).actualizar(Eventos.INICIAR_ADMINISTRADOR, datos);
			break ; 
		}
		
		case (Eventos.LISTA_INGREDIENTES):{
			
            SAProductos saProductos = new SAProductosImp();
            
            List<TransferProducto> listaIngredientes = saProductos.listaIngredientes(true);

            datos = listaIngredientes;
			
			break;
		}
	
		case (Eventos.ELIMINAR_CLIENTE):{
			
			HashMap<String, String> ids = (HashMap<String, String>) datos;
			String correo = new String(ids.get("correo"));
			String contraseña = new String(ids.get("contraseña"));
			
			SAClientes saClientes = FactoriaSA.getInstancia().nuevoSAClientes();
			TransferCliente cliente = new TransferCliente(null, null, correo , contraseña) ; 
			if(saClientes.borrarCliente(cliente)) {
				JOptionPane.showMessageDialog(null, "Eliminado con exito");
			
			}else {
				JOptionPane.showMessageDialog(null, "Error al eliminar el usuario");

			}
			break;
		}
		
		case (Eventos.CREAR_PEDIDO):{
			TransferPedido ids = (TransferPedido) datos;
			//SACAR DE IDS LOS DATOS PARA CREAR EL PEDIDO
			/*
			 * protected int idPedido;
    			protected String batidos;
    			protected int precio;
    			protected int unidades;
    			protected String idUsuario;
			 */		
			SAPedidos saPedidos = FactoriaSA.getInstancia().nuevoSAPedidos();
			if(saPedidos.crearPedido(ids) ){
				JOptionPane.showMessageDialog(null, "Pedido realizado con éxito, gracias por comprar con nosotros");
			}else {
				JOptionPane.showMessageDialog(null, "Error al realizar pedido");
			}
			
			break;
		}
		case(Eventos.CAMBIAR_DISPONIBILIDAD):{
			SAProductos saProductos = FactoriaSA.getInstancia().nuevoSAProducto() ; 
			String dato = (String) datos ; 
			boolean disponibilidad ; 
			String[] parts = dato.split(",");
			String nombre = parts[0];
			String disp = parts[1]; 
			if(disp.equals("Activo")) { 
				disponibilidad = true  ; 
			}
			else {
				disponibilidad = false ; 
			}
				
			if(saProductos.cambiarestado(nombre,disponibilidad)) {
				JOptionPane.showMessageDialog(null, "Cambio de estado realizado con exito");
			}else {
				JOptionPane.showMessageDialog(null, "Error al realizar el cambio");
			}

			break ; 
		}
		case(Eventos.AÑADIR_INGREDIENTES):{
			HashMap<String, String> ids = (HashMap<String, String>) datos;
			String nombre = new String(ids.get("nombre"));
			String calorías = new String(ids.get("calorías"));
			int i = devolverLista("ingredientes",false).size() ; 
			
			TransferProducto ing = new TransferProducto() ; 
			int cal =Integer.parseInt(calorías ) ;
			ing.setCalorias(cal);
			ing.setNombre(nombre);
			ing.setId(i);

			
			SAProductos saProductos = FactoriaSA.getInstancia().nuevoSAProducto();
	
			if(saProductos.crearProducto(ing)) {
				i++ ; 
				JOptionPane.showMessageDialog(null, "Ingrediente creado correctamente");
			}
			else {
				JOptionPane.showMessageDialog(null, "Este producto ya existe");
			}
			break ; 
			
		}

		default: {
			break;
		}
		
		}
		
	}
	public <T> Iterator<T> obtenerIteradorLista(String producto, boolean especifico) {
        List<T> lista = devolverLista(producto, especifico);
        return new IteradorDeLista<>(lista);
    }
	@SuppressWarnings("unchecked")
protected <T>List<T> devolverLista(String producto,boolean especifico){
		
		if (producto == "ingredientes"){
			SAProductos saProductos = new SAProductosImp();
	        List<TransferProducto> listaIngredientes = saProductos.listaIngredientes(especifico);
	       
	        
	        return (List<T>) listaIngredientes;
		}
		else if(producto == "smoothies") {
			SASmoothies saSmoothies = new SASmoothiesImp();
			List<TransferSmoothies> listaSmoothies = saSmoothies.listaSmoothies();
			
			return (List<T>) listaSmoothies;
		}
		else if(producto == "pedidos"){
 			SAPedidos saPedidos = new SAPedidosImp();
			List<TransferPedido> listaPedidos = saPedidos.listaTodosPedidos();
			return (List<T>) listaPedidos;
		}
		else{ 
			SAPedidos saPedidos = new SAPedidosImp();
			List<TransferPedido> listaPedidos = saPedidos.listaPedidos(producto);
			
			return (List<T>) listaPedidos;
		}
	}
	
	public String buscarIdCliente(Object datos) {
		HashMap<String, String> ids = (HashMap<String, String>) datos;
		String correo = new String(ids.get("correo"));
		String contraseña = new String(ids.get("contraseña"));
		
		SAClientes saClientes = FactoriaSA.getInstancia().nuevoSAClientes();
		return saClientes.buscarIdUsuario(correo, contraseña);
	}
	@Override
	public boolean  eliminarPedido(String pedido) {
		String[] parts = pedido.split("-");
		String ipedido = parts[0];
		String[] parts1 = ipedido.split(":");
		String idpedido = parts1[1];
		SAPedidos  saPedidos = FactoriaSA.getInstancia().nuevoSAPedidos() ; 
		return saPedidos.eliminarPedido(idpedido) ; 
		
		
	}


}
