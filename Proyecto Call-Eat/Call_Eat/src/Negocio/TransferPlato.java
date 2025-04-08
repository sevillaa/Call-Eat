package Negocio;

public class TransferPlato {
	
	protected String id;
	protected String nombre;
	protected int precio;
	protected String categoria;
	
	public TransferPlato(){
		
	}
	
	public TransferPlato(String id, String nombre, int precio  , String categoria){
		this.id = id ; 
		this.nombre = nombre ; 
		this.precio = precio;
		this.categoria = categoria;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public int getPrecio() {
		return this.precio;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return this.categoria;
	}

}
