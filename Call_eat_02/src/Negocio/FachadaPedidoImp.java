package Negocio;

import java.util.Date;
import java.util.List;

public class FachadaPedidoImp implements FachadaPedido {
	
	private SAPedido saPedido = new SAPedidoImp();

	public boolean crearPedido(TransferPedido pedido) {
		return this.saPedido.crearPedido(pedido);
	}

	public boolean eliminarPedido(TransferPedido pedido) {
		return this.saPedido.eliminarPedido(pedido);
	}

	public TransferPedido buscarPedido(String idpedido) {
		return this.saPedido.buscarPedido(idpedido);
	}

	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2) {
		return saPedido.listaPedidos(fecha1,fecha2);
	}

    
}


