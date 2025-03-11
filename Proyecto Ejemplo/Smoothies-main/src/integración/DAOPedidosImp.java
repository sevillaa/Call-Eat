package integración;


import java.util.ArrayList;
import java.util.List;

import Negocio.TransferPedido;

public class DAOPedidosImp implements DAOPedidos{

	@Override
	public TransferPedido buscarPedido(int idPedido) {

		String tP = BDPedidos.buscarPedido(idPedido) ; 
		
		if(tP != null) {
			String info[] = tP.split(",");
		return new TransferPedido(Integer.getInteger(info[0]), info[1],Integer.getInteger(info[2]),Integer.getInteger(info[3]),info[4]) ;
		}
		return null ; 
	}

	@Override
	public boolean crearPedido(TransferPedido pedido) {
	    return BDPedidos.crearPedido(pedido.getIdPedido(),pedido.getBatidos(), pedido.getPrecio(), pedido.getUnidades(), pedido.getIdUsuario()) ; 
	}


	@Override
	public List<TransferPedido> sacarListaPedidos(String idUsuario) {
	    List<TransferPedido> listaPedidos = new ArrayList<>();
	    List<String> lp = BDPedidos.sacarListaPedidos(idUsuario) ; 
	    for(String tP : lp ) {
	    	if(tP!= null ) {
	    	String info[] = tP.split(",");
	    	TransferPedido T = new TransferPedido(Integer.parseInt(info[0]), info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]),info[4]) ;
	    	listaPedidos.add(T) ; 
	    	}
	    }
	              
	    return listaPedidos;
	}

	@Override
	public List<TransferPedido> sacarTodosPedidos() {
		List<TransferPedido> listaPedidos = new ArrayList<>();
	    List<String> lp = BDPedidos.sacarTodosPedidos() ; 
	    for(String tP : lp ) {
	    	if(tP!= null){
	    	String info[] = tP.split(",");
	    	TransferPedido T = new TransferPedido(Integer.parseInt(info[0]), info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]),info[4]) ;
	    	listaPedidos.add(T) ; 
	    	}
	    }
	              
	    return listaPedidos;
	}

	@Override
	public boolean eliminarPedido(String pedido) {
		return BDPedidos.eliminarPedido(pedido) ; 
		
	}

	
	

}
