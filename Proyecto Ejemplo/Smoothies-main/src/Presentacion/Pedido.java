package Presentacion;

import Negocio.TransferPedido;

public abstract class Pedido {
	private static Pedido instancia = null;
	
	private int idPedido = 0;
	private String batidos="";
	private int precio = 0;
	private int unidades= 0;
	private String idUsuario;
	private static TransferPedido instanciatpPedido = new TransferPedido();
	
	
	static public Pedido getInstancia() {
		if(instancia == null) {
			instancia = new PedidoImp();
			//instanciatpPedido = new TransferPedido();
		}
		return instancia;
	}
	
	public abstract void agregarProducto(String idBatido);
	public abstract void eliminarProducto(String idBatido);
	public abstract void vaciarCarrito();

	public int getUnidades() {
		return unidades;
	}

	public String getBatidos() {
		return batidos;
	}

	public void setBatidos(String batidos) {
		this.batidos = batidos;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public void setIdUsuario(String idUsuario) {
		// TODO Auto-generated method stub
		this.idUsuario = idUsuario;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setId(int idPedido) {
		this.idPedido = idPedido;		
	}
	
	public int getId() {
		return idPedido;
	}

	public abstract void sumarBatido(int precio) ;
}
