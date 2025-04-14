package Integracion;

import Negocio.TransferPedido;

public interface SADAOPedido {

	boolean crearPedido(TransferPedido pedido);

	boolean eliminarPedido(TransferPedido pedido);

	TransferPedido buscarPedido(String idpedido);

}
