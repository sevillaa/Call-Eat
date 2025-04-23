package Negocio;

import java.util.Date;
import java.util.List;

public interface SAPedido {

	boolean crearPedido(TransferPedido pedido);

	boolean eliminarPedido(TransferPedido pedido);

	TransferPedido buscarPedido(String idpedido);
	
	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2);

}
