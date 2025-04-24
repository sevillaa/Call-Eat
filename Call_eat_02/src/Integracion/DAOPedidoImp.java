package Integracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferPedido;

public class DAOPedidoImp {
	private List<TransferPedido> pedidos;
    private static final String FILE_PATH = "pedidos.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    
    public DAOPedidoImp() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                this.pedidos = objectMapper.readValue(file, new TypeReference<List<TransferPedido>>() {});
            } else {
                this.pedidos = new ArrayList<>();
                objectMapper.writeValue(file, pedidos); // Crear el archivo vacío si no existe
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.pedidos = new ArrayList<>();
        }
    }

	public boolean crearPedido(TransferPedido pedido) {
		try {
            if (!pedidos.contains(pedido)) {
                pedidos.add(pedido);
                objectMapper.writeValue(new File(FILE_PATH), pedidos);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
	}


	public boolean eliminarPedido(TransferPedido pedido) {
		try {
            if (pedidos.remove(pedido)) {
                objectMapper.writeValue(new File(FILE_PATH), pedidos);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TransferPedido buscarPedido(String idpedido) {
        for (TransferPedido pedido : pedidos) {
            if (pedido.getID().equals(idpedido)) {
                return pedido;
            }
        }
        return null;
	}

    public List<TransferPedido> listarPedidosPorFechas(Date fecha1, Date fecha2) {
        List<TransferPedido> filtrados = new ArrayList<>();
        for (TransferPedido pedido : pedidos) {
            Date fechaPedido = pedido.getFecha();
            if (fechaPedido != null && !fechaPedido.before(fecha1) && !fechaPedido.after(fecha2)) {
                filtrados.add(pedido);
            }
        }
        return filtrados;
    }
    

    public List<TransferPedido> obtenerTodosLosPedidos() {
        return Collections.unmodifiableList(this.pedidos);
    }
}