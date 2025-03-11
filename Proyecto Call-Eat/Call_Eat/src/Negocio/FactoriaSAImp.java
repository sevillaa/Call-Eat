package Negocio;

public  class FactoriaSAImp extends FactoriaSA{
	
	public SACliente nuevoSAClientes() {
		return new SAClienteImp();
	}

	
	
}
