package Presentacion;

import Negocio.TransferPedido;

public abstract class GUIMenu {

	private static GUIMenu instancia = null;
	
	public static GUIMenu getInstancia(Controlador controlador,Object datos, TransferPedido pedido) {
		if(instancia == null) 
			instancia = new GUIMenuImp(controlador,datos,pedido);
		return instancia;	
		
	}
	
	public abstract void actualizar(int evento, Object datos);
}
