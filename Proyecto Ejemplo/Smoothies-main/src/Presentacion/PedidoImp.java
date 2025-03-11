package Presentacion;

import javax.swing.JOptionPane;

import Negocio.FactoriaSA;
import Negocio.SAPedidos;
import Negocio.TransferPedido;

public class PedidoImp extends Pedido {

	@Override
	public void agregarProducto(String idBatido) {
				
		if(getUnidades() != 0) {
			setBatidos(getBatidos() + ",");
		}
		setBatidos(getBatidos() + idBatido);
		
		setUnidades(getUnidades() + 1);
		
	}

	@Override
	public void eliminarProducto(String idBatido) {
	    if (getUnidades() == 1) {
	        setBatidos(""); // Limpiar la lista de batidos
	        setUnidades(0); // Reiniciar el número de unidade
	        setPrecio(0);
	    } else {
	        // Buscar el ID del batido en la listaBatidos
	        String[] batidos = getBatidos().split(",");
	        boolean encontrado = false;

	        for (int i = 0; i < batidos.length; i++) {
	            if (batidos[i].equals(idBatido)) {
	                // Extraer el tamaño del batido
	                String[] partesBatido = batidos[i].split("-"); // Dividir el nombre del producto por el guion (-)
	                String tamañoBatido = partesBatido[1]; // El tamaño del batido está en la segunda posición

	                // Aquí puedes utilizar el tamaño del batido como necesites

	                if(tamañoBatido.equals("Pequeño")) {
	                    sumarBatido(-4);
	                } else if(tamañoBatido.equals("Mediano")) {
	                    sumarBatido(-5);
	                } else {
	                    sumarBatido(-6);
	                }

	                // Eliminar el ID del batido de la lista
	                StringBuilder sb = new StringBuilder(getBatidos());
	                if (i == 0) {
	                    // Si es el primer elemento, también eliminamos la coma después
	                    sb.delete(i * (idBatido.length() + 1), (i * (idBatido.length() + 1)) + idBatido.length() + 1);
	                } else {
	                    // Si es un elemento en medio o al final, eliminamos la coma anterior
	                    sb.delete((i * (idBatido.length() + 1)) - 1, (i * (idBatido.length() + 1)) + idBatido.length());
	                }
	                setBatidos(sb.toString());

	                encontrado = true;
	                break;
	            }
	        }


	        if (encontrado) {
	            setUnidades(getUnidades() - 1); // Decrementar el número de unidades si se eliminó un producto
	        }
	    }
	}

	@Override
	public void sumarBatido(int precio) {
		setPrecio(getPrecio() + precio);
	}
	
	@Override
    public void vaciarCarrito() {
        setBatidos(""); // Limpiar la lista de batidos
        setUnidades(0); // Reiniciar el número de unidades
    }


}
