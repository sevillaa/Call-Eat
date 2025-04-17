package Integracion;

import Negocio.TransferPedido;

public class SADAOPedidoImp implements SADAOPedido {
	
	DAOPedidoImp dao = new DAOPedidoImp();

	@Override
	public boolean crearPedido(TransferPedido pedido) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarPedido(TransferPedido pedido) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferPedido buscarPedido(String idpedido) {
		// TODO Auto-generated method stub
		return null;
	}

}
