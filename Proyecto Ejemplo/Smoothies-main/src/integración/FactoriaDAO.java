package integración;


public abstract class FactoriaDAO {

	private static FactoriaDAO instancia = null;

	static public FactoriaDAO getInstancia() {
		if (instancia == null) //patron singleton
			instancia = new FactoriaDAOImp();
		
		return instancia;
	}

	public abstract DAOCliente nuevoDAOClientes();
	public abstract DAOProductos nuevoDAOProductos();
	public abstract DAOSmoothies nuevoDAOSmoothies();
	public abstract DAOPedidos nuevoDAOPedidos();
}
