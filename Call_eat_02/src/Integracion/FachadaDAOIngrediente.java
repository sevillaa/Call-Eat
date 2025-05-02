package Integracion;

import java.util.List;

import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;

public interface FachadaDAOIngrediente {
	public boolean crearIngrediente(TransferIngrediente ingrediente);

	public boolean eliminarIngrediente(TransferIngrediente ingrediente);

	public TransferIngrediente buscarIngrediente(String idIngrediente);
	
	public boolean modificarIngrediente(TransferIngrediente modificado);
	
	List<TransferIngrediente> listaIngredientes();

	TransferIngrediente buscarIngredientePorNombre(String nombreIngrediente);
	
	boolean comprobarIngrediente(TransferIngrediente ingrediente);
}
