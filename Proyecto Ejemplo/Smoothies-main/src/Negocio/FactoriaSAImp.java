package Negocio;


public  class FactoriaSAImp extends FactoriaSA{
	public SAClientes nuevoSAClientes() {
		return new SAClientesImp();
	}

	@Override
	public SAProductos nuevoSAProducto() {
		return new SAProductosImp();
	}

	@Override
	public SASmoothies nuevoSASmoothies() {
		return new SASmoothiesImp();
	}

	@Override
	public SAPedidos nuevoSAPedidos() {
		return new SAPedidosImp();
	}
	
	
}
