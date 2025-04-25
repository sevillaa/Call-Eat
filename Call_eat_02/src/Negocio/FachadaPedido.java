package Negocio;

public interface FachadaPedido {
	public boolean crearPedido(TransferPedido pedido);

	public boolean eliminarPedido(TransferPedido pedido);

	public TransferPedido buscarPedido(String idpedido);
	
	public boolean modificarPedido(TransferPedido pedido);
}
