package Presentacion;

import Negocio.TransferPedido;

public abstract class GUIProductos {

	private static GUIProductos instancia = null;
	
	public static GUIProductos getInstancia(Controlador controlador,TransferPedido pedido) {
		if(instancia == null) 
			instancia = new GUIProductosImp(controlador,pedido);
		return instancia;
	}
	
	public abstract void actualizar(int evento, Object datos);
}
