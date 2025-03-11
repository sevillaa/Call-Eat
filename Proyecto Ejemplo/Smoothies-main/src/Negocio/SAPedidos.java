package Negocio;

import java.util.List;

public interface SAPedidos {

	public boolean crearPedido(TransferPedido pedido);
	
	List<TransferPedido> listaPedidos(String producto);
	List<TransferPedido> listaTodosPedidos();

	public boolean eliminarPedido(String pedido);
}
