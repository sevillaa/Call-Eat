package Integracion;

import Negocio.TransferPedido;

public class FachadaDAOPedidoImp implements FachadaDAOPedido {

    private SADAOPedido saDaoPedido = new SADAOPedidoImp();

	public boolean crearPedido(TransferPedido pedido) {
		return this.saDaoPedido.crearPedido(pedido);
	}

	public boolean eliminarPedido(TransferPedido pedido) {
		return this.saDaoPedido.eliminarPedido(pedido);
	}

	public TransferPedido buscarPedido(String idpedido) {
		return this.saDaoPedido.buscarPedido(idpedido);
	}

}
