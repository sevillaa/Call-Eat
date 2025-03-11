package Negocio;

import integración.DAOPedidosImp;

public class TransferPedido extends DAOPedidosImp {
    protected int idPedido;
    protected String batidos;
    protected int precio;
    protected int unidades;
    protected String idUsuario;
    
    public TransferPedido() {
    	
    	
    } ;
    
    public TransferPedido(int idPedido, String batidos, int precio, int unidades, String idUsuario){
    	this.idPedido = idPedido;
        this.batidos = batidos;
        this.precio = precio;
        this.unidades = unidades;
        this.idUsuario = idUsuario;
    }

    // Getters
    public int getIdPedido() {
        return idPedido;
    }

    public String getBatidos() {
        return batidos;
    }

    public int getPrecio() {
        return precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    // Setters


    public void setBatidos(String batidos) {
        this.batidos = batidos;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public void setId(int idPedido) {
		this.idPedido = idPedido;		
	}


	
	public void agregarProducto(String idBatido) {
				
		if(getUnidades() != 0) {
			
			setBatidos(getBatidos() + "/"+ idBatido);
		}else {
			setBatidos( idBatido) ;
		}
		
			
	
	
		setUnidades(getUnidades() + 1);
		
	}

	
	public void eliminarProducto(String idBatido) {
	    if (getUnidades() == 1) {
	        setBatidos(""); // Limpiar la lista de batidos
	        setUnidades(0); // Reiniciar el número de unidade
	        setPrecio(0);
	    } else {
	        // Buscar el ID del batido en la listaBatidos
	        String[] batidos = getBatidos().split("/");
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

	public void sumarBatido(int precio) {
		setPrecio(getPrecio() + precio);
		
	}

	public void vaciarCarrito() {
		setBatidos(""); // Limpiar la lista de batidos
        setUnidades(0); // Reiniciar el número de unidades
		
	}

	
}

