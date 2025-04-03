package Negocio;

import Integracion.DAOUsuariosImp;

public class TransferUsuarios{

	protected String id;
	protected String correo;
	protected String contraseña;
	protected String nombre;
	
	public TransferUsuarios(){
		
	}
	public TransferUsuarios(String id, String nombre, String correo , String contraseña){
		this.id = id ; 
		this.nombre = nombre ; 
		this.correo = correo  ; 
		this.contraseña = contraseña; 
	}
	

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getContraseña() {
		return contraseña;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getCorreo() {
		return correo;
	}
	

}

