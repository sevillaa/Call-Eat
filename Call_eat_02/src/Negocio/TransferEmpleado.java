package Negocio;

public class TransferEmpleado{

	protected String id;
	protected String correo;
	protected String contraseña;
	protected String nombre;
	protected String rol;
	
	public TransferEmpleado(){
		
	}
	public TransferEmpleado(String id, String nombre, String correo , String contraseña,String rol){
		this.id = id ; 
		this.nombre = nombre ; 
		this.correo = correo  ; 
		this.contraseña = contraseña; 
		this.rol = rol;
	}
	
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getRol() {
		return rol;
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