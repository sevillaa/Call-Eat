package Integracion;

import java.util.Date;
import java.util.List;

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

	@Override
	public List<TransferPedido> listaPedidos(Date fecha1, Date fecha2) {
    	return dao.listarPedidosPorFechas(fecha1,fecha2);

	}

}
