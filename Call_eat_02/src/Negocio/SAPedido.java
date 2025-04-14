package Negocio;

public interface SAPedido {

	boolean crearPedido(TransferPedido pedido);

	boolean eliminarPedido(TransferPedido pedido);

	TransferPedido buscarPedido(String idpedido);

}
