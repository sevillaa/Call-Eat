package Presentacion;

import java.util.Iterator;
import java.util.List;



public abstract class Controlador {
	private static Controlador instancia = null;

	static public Controlador getInstancia() {
		if (instancia == null)
			instancia = new ControladorImp();
		return instancia;
	} // Patr�n Singleton!!!
	
	public abstract void accion(int evento, Object datos);
	protected abstract <T>List<T> devolverLista(String producto,boolean especifico); //programación genérica
	public abstract String buscarIdCliente(Object datos);
	public abstract <T> Iterator<T> obtenerIteradorLista(String producto, boolean especifico) ;

	public abstract boolean eliminarPedido(String pedido)  ; 
}
