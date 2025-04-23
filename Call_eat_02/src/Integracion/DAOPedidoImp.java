package Integracion;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferPedido;

public class DAOPedidoImp {
	private static final String FILE_PATH = "pedidos.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    
    
    
	public List<TransferPedido> listarPedidosPorFechas(Date fecha1, Date fecha2) {
		// TODO Auto-generated method stub
		return null;
	}


}
