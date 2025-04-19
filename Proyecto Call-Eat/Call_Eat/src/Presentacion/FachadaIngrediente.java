package Presentacion;

import java.util.List;

import Negocio.TransferIngrediente;

public interface FachadaIngrediente {
	boolean registrarIngrediente(TransferIngrediente ingrediente);
    boolean modificarIngrediente(TransferIngrediente ingrediente);
    boolean eliminarIngrediente(TransferIngrediente ingrediente);
    TransferIngrediente buscarIngredientePorNombre(String nombre);
    List<TransferIngrediente> listarIngredientes();
}
