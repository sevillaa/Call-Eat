package Negocio;

import java.util.Map;

public class TransferIngrediente {
	
	protected String id;
	protected String nombre;
	protected int cantidad;
	
	public TransferIngrediente(){
		
	}
	
	public TransferIngrediente(String id, String nombre, int cantidad){
		this.id = id ; 
		this.nombre = nombre ; 
		this.cantidad = cantidad;
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
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}
	@Override
	public String toString(){
		return this.nombre;
	}


}
