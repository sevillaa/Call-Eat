package Presentacion;

import java.util.List;

import Negocio.TransferPedido;

public interface FachadaPedido {
	boolean registrarPedido(TransferPedido pedido);
    boolean modificarPedido(TransferPedido pedido);
    boolean eliminarPedido(TransferPedido pedido);
    TransferPedido buscarPedidoPorId(String id);
    List<TransferPedido> listarPedidos();
}
