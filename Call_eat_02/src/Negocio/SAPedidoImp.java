package Negocio;

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

}
