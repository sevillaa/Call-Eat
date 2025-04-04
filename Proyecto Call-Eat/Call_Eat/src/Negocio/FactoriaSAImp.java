package Negocio;

public  class FactoriaSAImp extends FactoriaSA{
	
	public SAEmpleado nuevoSAClientes() {
		return new SAEmpleadoImp();
	}

	
	
}
