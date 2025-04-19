package Integracion;

import java.security.NoSuchAlgorithmException;

import Negocio.TransferPedido;
import Negocio.TransferPlato;

public class DAOPedidoImp implements DAOPedido{

	@Override
	public boolean registrarPedido(TransferPedido pedido) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TransferPlato buscarPedido(String idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarPedido(String idPedido) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarPedido(TransferPlato idPedido) {
		// TODO Auto-generated method stub
		return false;
	}

}
