package integración;

import java.util.List;

import Negocio.TransferPedido;


public interface DAOPedidos {

	public TransferPedido buscarPedido(int idPedido);
	public boolean crearPedido(TransferPedido pedido);
	public List<TransferPedido> sacarListaPedidos(String idUsuario);
	public List<TransferPedido> sacarTodosPedidos();
	public boolean eliminarPedido(String pedido);
}
