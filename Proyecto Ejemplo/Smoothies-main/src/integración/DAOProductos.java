package integración;

import java.util.List;

import Negocio.TransferProducto;

public interface DAOProductos {
	public TransferProducto buscarProducto(String nombre);

	public boolean añadirProducto(TransferProducto ing);
	
	public List<TransferProducto> sacarListaIngredientes(boolean especifico);
	public boolean cambiarEstado(String nombre, boolean disponibilidad); 
}
