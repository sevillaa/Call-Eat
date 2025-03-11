package Negocio;

import java.util.List;

import integración.DAOPedidos;
import integración.FactoriaDAO;

public class SAPedidosImp implements SAPedidos{

	@Override
	public boolean crearPedido(TransferPedido pedido) {
		DAOPedidos daoPedidos = (DAOPedidos) FactoriaDAO.getInstancia().nuevoDAOPedidos();
		boolean ok = false;
		
		try {
			ok = daoPedidos.crearPedido(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ok;
	}



	@Override
	public List<TransferPedido> listaPedidos(String idUsuario) {
		DAOPedidos daoPedidos = (DAOPedidos) FactoriaDAO.getInstancia().nuevoDAOPedidos();
		List<TransferPedido> listaPedidos = null;
		
		try {
			listaPedidos = daoPedidos.sacarListaPedidos(idUsuario);
			
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return listaPedidos;
	}

	@Override
	public List<TransferPedido> listaTodosPedidos() {
		DAOPedidos daoPedidos = (DAOPedidos) FactoriaDAO.getInstancia().nuevoDAOPedidos();
		List<TransferPedido> listaPedidos = null;
		try {
			listaPedidos = daoPedidos.sacarTodosPedidos();
			
		}catch(Exception e) {
			 e.printStackTrace();
		}
		return listaPedidos;
	}



	@Override
	public boolean eliminarPedido(String pedido) {
		
		DAOPedidos daoPedidos = (DAOPedidos) FactoriaDAO.getInstancia().nuevoDAOPedidos();
		return daoPedidos.eliminarPedido(pedido) ; 
	}

}
