package Negocio;

import java.util.List;

public interface SAProductos {

	public boolean crearProducto(TransferProducto ing);
	boolean buscarProducto(String correo, int id, int calorias);
	List<TransferProducto> listaIngredientes(boolean especifico);
	public boolean cambiarestado(String nombre, boolean disponibilidad) ;
}
