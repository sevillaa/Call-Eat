package Integracion;

import java.util.Date;
import java.util.List;

import Negocio.TransferPedido;

public class SADAOPedidoImp implements SADAOPedido {
	
	DAOPedidoImp dao = new DAOPedidoImp();

	@Override
	public boolean crearPedido(TransferPedido pedido) {
		return dao.crearPedido(pedido);
	}

	@Override
	public boolean eliminarPedido(TransferPedido pedido) {
		return dao.eliminarPedido(pedido);

	}

	@Override
	public TransferPedido buscarPedido(String idpedido) {
		return dao.buscarPedido(idpedido);

	}

	@Override
	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2) {
    	return dao.listarPedidosPorFechas(fecha1,fecha2);

	}

}