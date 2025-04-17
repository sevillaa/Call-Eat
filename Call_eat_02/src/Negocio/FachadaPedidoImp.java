package Negocio;


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

    
}


