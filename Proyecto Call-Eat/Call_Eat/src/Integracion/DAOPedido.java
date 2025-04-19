package Integracion;

import java.security.NoSuchAlgorithmException;

import Negocio.TransferPedido;
import Negocio.TransferPlato;

public interface DAOPedido {
	public boolean registrarPedido(TransferPedido pedido) throws NoSuchAlgorithmException;
	public TransferPlato buscarPedido(String idPedido);
	public boolean actualizarPedido(String idPedido);
	public boolean eliminarPedido(TransferPlato idPedido);
}
