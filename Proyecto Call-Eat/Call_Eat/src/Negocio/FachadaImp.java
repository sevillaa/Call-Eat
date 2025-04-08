package Negocio;

public class FachadaImp implements Fachada {
	private SAEmpleado saEmpleado;
	private SAIngrediente saIngrediente;
	private SAPlato saPlato;

	public FachadaImp() {
		this.saEmpleado = FactoriaSA.getInstancia().nuevoSAClientes();
		this.saIngrediente = FactoriaSA.getInstancia().nuevoSAIngredientes();
		this.saPlato = FactoriaSA.getInstancia().nuevosSAPlatos();
	}

	// -------------------
	// MÉTODOS EMPLEADO ✅
	// -------------------
	@Override
	public boolean registrarEmpleado(TransferEmpleado empleado) {
		return saEmpleado.crearUsuario(empleado);
	}
	@Override
	public boolean loginEmpleado(String correo, String contraseña) {
		return saEmpleado.accesoCliente(correo, contraseña);
	}
	@Override
	public TransferEmpleado obtenerEmpleadoSeguro(String correo, String contraseña) {
		return saEmpleado.accesoClienteSeguro(correo, contraseña);
	}
	@Override
	public boolean eliminarEmpleado(TransferEmpleado empleado) {
		return saEmpleado.borrarCliente(empleado);
	}
	@Override
	public String buscarIdEmpleado(String correo, String contraseña) {
		return saEmpleado.buscarIdUsuario(correo, contraseña);
	}

	// -----------------------
	// MÉTODOS INGREDIENTE ✅
	// -----------------------
	@Override
	public boolean registrarIngrediente(TransferIngrediente ingrediente) {
		return saIngrediente.crearIngrediente(ingrediente);
	}
	@Override
	public boolean modificarIngrediente(TransferIngrediente ingrediente) {
		return saIngrediente.modificarIngrediente(ingrediente);
	}
	@Override
	public boolean eliminarIngrediente(TransferIngrediente ingrediente) {
		return saIngrediente.borrarIngrediente(ingrediente);
	}
	@Override
	public TransferIngrediente buscarIngredientePorNombre(String nombre) {
		return saIngrediente.buscarIngrediente(nombre);
	}
	@Override
	public List<TransferIngrediente> listarIngredientes() {
		return saIngrediente.listarIngredientes();
	}

	// -----------------------
	// MÉTODOS PLATO ✅
	// -----------------------
	@Override
	public boolean registrarPlato(TransferPlato idPlato) {
		return saPlato.crearPlato(idPlato);
	}
	@Override
	public boolean modificarPlato(TransferPlato idPlato) {
		return saPlato.modificarPlato(idPlato);
	}
	@Override
	public boolean eliminarPlato(TransferPlato idPlato) {
		return saPlato.borrarPlato(idPlato);
	}
	@Override
	public TransferIngrediente buscarPlatoPorNombre(String plato) {
		return saPlato.buscarIngrediente(nombre);
	}
	@Override
	public List<TransferPlatp> listarPlatos() {
			return saPlato.listarPlato();
	}
}
