package Negocio;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TransferPlato {

	protected String id;
	protected String nombre;
	protected Map<String, Integer> ingredientes;
	protected int precio;
	protected String categoria;

	public TransferPlato() {

	}

	public TransferPlato(String id, String nombre, Map<String, Integer> ingredientes, int precio,
			String categoria) {
		this.id = id;
		this.nombre = nombre;
		this.ingredientes = ingredientes;
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

	public void setIngredientes(Map<String, Integer> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Map<String, Integer> getIngredientes() {
		return ingredientes;
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
