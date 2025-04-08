package Negocio;

public  class FactoriaSAImp extends FactoriaSA{
	
	public SAEmpleado nuevoSAClientes() {
		return new SAEmpleadoImp();
	}

	@Override
	public SAIngrediente nuevoSAIngredientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SAPlato nuevosSAPlatos() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
