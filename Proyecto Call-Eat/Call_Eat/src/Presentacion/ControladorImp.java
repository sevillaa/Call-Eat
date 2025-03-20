package Presentacion;

import java.util.HashMap;

import javax.swing.JOptionPane;

import Negocio.FactoriaSA;
import Negocio.SACliente;
import Negocio.TransferUsuarios;

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
			TransferUsuarios cliente = new TransferUsuarios(idUsuario, nombre, correo , contraseña) ; 

			
			SACliente saCliente = FactoriaSA.getInstancia().nuevoSAClientes();
			if(saCliente.crearUsuario(cliente)) {
				JOptionPane.showMessageDialog(null,"Usuario creado correctamente \n      id : "+ idUsuario);
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al crear el usuario");
			}
			//GUISmoothies.getInstancia(Controlador.getInstancia()).actualizar(Eventos.AÑADIR_CLIENTE, null);
		}
		case (Eventos.INICIAR_SESION):{
			@SuppressWarnings("unchecked")
			HashMap<String, String> ids = (HashMap<String, String>) datos;
			String correo = new String(ids.get("correo"));
			String contraseña = new String(ids.get("contraseña"));
			SACliente saClientes = FactoriaSA.getInstancia().nuevoSAClientes();
			if(saClientes.accesoCliente(correo, contraseña)) {
				
				GUIUsuarios.getInstancia(Controlador.getInstancia(), datos).actualizar(Eventos.CLIENTE_REGISTRADO, datos);
				break;
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al cargar el usuario");
				break ; 
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
