package Integracion;

import java.util.Date;
import java.util.List;

import Negocio.TransferPedido;

public interface SADAOPedido {

	boolean crearPedido(TransferPedido pedido);

	boolean eliminarPedido(TransferPedido pedido);

	TransferPedido buscarPedido(String idpedido);

	List<TransferPedido> listaPedidos(Date fecha1, Date fecha2);


}
