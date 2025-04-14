package Integracion;

import Negocio.TransferPedido;

public interface FachadaDAOPedido {

	public boolean crearPedido(TransferPedido pedido);

	public boolean eliminarPedido(TransferPedido pedido);

	public TransferPedido buscarPedido(String idpedido);
}
