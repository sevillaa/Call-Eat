package Negocio;

import java.util.List;

import integración.DAOSmoothiesImp;

public class TransferSmoothies extends DAOSmoothiesImp{

	protected String nombre;
	protected List<TransferProducto> ingredientes;
	protected int id;
	protected int calorias;
	protected String descripcion;
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public int getId() {
		return id;
	}
	
	public int getCalorias() {
		
		return calorias;
	}
	
	public void setIngredientes(List<TransferProducto> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	public List<TransferProducto> getIngredientes(){
		return ingredientes;
	}
	
}
