package Presentacion;

import java.util.HashMap;

import javax.swing.JOptionPane;

import Negocio.FactoriaSA;
import Negocio.SACliente;
import Negocio.TransferCliente;

public class ControladorImp extends Controlador{

	@Override
	public void accion(int evento, Object datos) {
		// TODO Auto-generated method stub
		switch(evento) {
		
		case (Eventos.ADD_CLIENTE):{
			@SuppressWarnings("unchecked")
			HashMap<String, String> ids = (HashMap<String, String>) datos;
			String nombre = new String(ids.get("nombre"));
			String correo = new String(ids.get("correo"));
			String contraseña = new String(ids.get("contraseña"));
			String idUsuario = generarCodigoRandom();
			TransferCliente cliente = new TransferCliente(idUsuario, nombre, correo , contraseña) ; 

			
			SACliente saCliente = FactoriaSA.getInstancia().nuevoSAClientes();
			if(saCliente.crearUsuario(cliente)) {
				JOptionPane.showMessageDialog(null,"Usuario creado correctamente \n      id : "+ idUsuario);
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al crear el usuario");
			}
		}
		case (Eventos.INICIAR_SESION): {
		    @SuppressWarnings("unchecked")
		    HashMap<String, String> ids = (HashMap<String, String>) datos;
		    String correo = ids.get("correo");
		    String contraseña = ids.get("contraseña");
		    SACliente saClientes = FactoriaSA.getInstancia().nuevoSAClientes();
		    TransferCliente clienteSeguro = saClientes.accesoClienteSeguro(correo, contraseña);
		    
		    if(clienteSeguro != null) {
		        // Se pasa el TransferCliente seguro (sin contraseña) a la GUI
		        GUICliente.getInstancia(Controlador.getInstancia(), clienteSeguro)
		                  .actualizar(Eventos.CLIENTE_REGISTRADO, clienteSeguro);
		        break;
		    } else {
		        JOptionPane.showMessageDialog(null, "Error al cargar el usuario");
		        break; 
		    }
		}

		default: {
			break;
		}
		}
	}
	
	private String generarCodigoRandom() {
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
