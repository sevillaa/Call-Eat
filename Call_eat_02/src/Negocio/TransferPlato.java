package Negocio;


import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public class TransferPlato {

	protected String id;
	protected String nombre;
	protected Map<String, Integer> ingredientes;
	protected double precio;
	protected String categoria;
	protected String iconPath;

	public TransferPlato() {

	}

	public TransferPlato(String id, String nombre, Map<String, Integer> ingredientes, double precio,
			String categoria, String iconPath) {
		this.id = id;
		this.nombre = nombre;
		this.ingredientes = ingredientes;
		this.precio = precio;
		this.categoria = categoria;
		this.iconPath = iconPath;
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

	public void setIngredientes(Map<String, Integer> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Map<String, Integer> getIngredientes() {
		return ingredientes;
	}

	public void setPrecio(double d) {
		this.precio = d;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return this.categoria;
	}
	
	public String getIconPath() {
		return this.iconPath;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;

		}

		TransferPlato other = (TransferPlato) obj;

		return id != null && id.equals(other.id);
	}

}
