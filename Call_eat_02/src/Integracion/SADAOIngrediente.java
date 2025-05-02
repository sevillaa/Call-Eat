package Integracion;

import java.util.List;

import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;

public interface SADAOIngrediente {

	boolean crearIngrediente(TransferIngrediente ingrediente);

	boolean eliminarIngrediente(TransferIngrediente ingrediente);

	TransferIngrediente buscarIngrediente(String idIngrediente);
	
	public boolean modificarIngrediente(TransferIngrediente modificado);
	
	List<TransferIngrediente> listaIngredientes();

	TransferIngrediente buscarIngredientePorNombre(String nombreIngrediente);
	
	public boolean comprobarIngrediente(TransferIngrediente ingrediente) ;
}
