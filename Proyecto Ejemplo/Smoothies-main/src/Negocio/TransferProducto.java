package Negocio;

import integración.DAOProductosImp;

public class TransferProducto extends DAOProductosImp{

	protected String nombre;
	protected int id;
	protected int calorias;
	protected boolean disponibilidad ; 
	
	public TransferProducto() {
		
	}
	public TransferProducto(String nombre , int id , int calorias , boolean disponibilidad) {
		this.nombre = nombre ; 
		this.id = id  ; 
		this.calorias = calorias ; 
		this.disponibilidad = disponibilidad  ; 
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDisp(boolean disp) {
		this.disponibilidad = disp ; 
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}
	
	public int getCalorias() {
		return calorias;
	}
	public boolean getDisp() {
		return disponibilidad;
	}
	
	public void cambiarDisp() {
		disponibilidad = !disponibilidad ; 
	}
	
	
}
