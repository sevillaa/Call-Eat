package Negocio;

import java.util.Date;
import java.util.List;

import Integracion.FachadaDAOPedidoImp;

public class SAPedidoImp implements SAPedido{

	
	private FachadaDAOPedidoImp fachadaDaoPedido = new FachadaDAOPedidoImp();
	
	@Override
	public boolean crearPedido(TransferPedido pedido) {
		// TODO Auto-generated method stub
		

		
		
		return this.fachadaDaoPedido.crearPedido(pedido);
	}

	@Override
	public boolean eliminarPedido(TransferPedido pedido) {
		// TODO Auto-generated method stub
		

		
		
		return this.fachadaDaoPedido.eliminarPedido(pedido);
	}

	@Override
	public TransferPedido buscarPedido(String idpedido) {
		// TODO Auto-generated method stub
		

		
		
		
		return this.fachadaDaoPedido.buscarPedido(idpedido);
	}

	@Override
	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2) {
		List<TransferPedido> pedidos = fachadaDaoPedido.listaPedidos(fecha1,fecha2);
		if(pedidos != null)
			return pedidos;
		return null;//En caso de que no haya ningun pedido aún
	}

	@Override
	public boolean modificarPedido(TransferPedido pedido) {
	    if (pedido == null || pedido.getId() == null) {
	        return false;
	    }
	    return this.fachadaDaoPedido.modificarPedido(pedido);
	}



}
