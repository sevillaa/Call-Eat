package Integracion;

import java.util.Date;
import java.util.List;

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

	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2) {
		return saDaoPedido.listaPedidos(fecha1,fecha2);
	}

}
