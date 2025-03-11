package Negocio;

public abstract class FactoriaSA {
	
	private static FactoriaSA instancia = null;

	static public FactoriaSA getInstancia() {
		if (instancia == null)
			instancia = new FactoriaSAImp();
		return instancia;
	}

	abstract public SAClientes nuevoSAClientes();
	abstract public SAProductos nuevoSAProducto();
	abstract public SASmoothies nuevoSASmoothies();
	abstract public SAPedidos nuevoSAPedidos();
}
