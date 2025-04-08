package Negocio;

import java.util.List;

public interface Fachada {
	// -------------------
    // MÉTODOS EMPLEADO
    // -------------------
    boolean registrarEmpleado(TransferEmpleado empleado);
    boolean loginEmpleado(String correo, String contraseña);
    TransferEmpleado obtenerEmpleadoSeguro(String correo, String contraseña);
    boolean eliminarEmpleado(TransferEmpleado empleado);
    String buscarIdEmpleado(String correo, String contraseña);

    // -----------------------
    // MÉTODOS INGREDIENTE
    // -----------------------
    boolean registrarIngrediente(TransferIngrediente ingrediente);
    boolean modificarIngrediente(TransferIngrediente ingrediente);
    boolean eliminarIngrediente(TransferIngrediente ingrediente);
    TransferIngrediente buscarIngredientePorNombre(String nombre);
    List<TransferIngrediente> listarIngredientes();

    // -----------------------
    // MÉTODOS PLATO
    // -----------------------
    boolean registrarPlato(TransferPlato plato);
    boolean modificarPlato(TransferPlato plato);
    boolean eliminarPlato(TransferPlato plato);
    TransferPlato buscarPlatoPorNombre(String nombre);
    List<TransferPlato> listarPlatos();
}
