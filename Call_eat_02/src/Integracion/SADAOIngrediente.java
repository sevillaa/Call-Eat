package Integracion;

import Negocio.TransferIngrediente;

public interface SADAOIngrediente {

	boolean crearIngrediente(TransferIngrediente ingrediente);

	boolean eliminarIngrediente(TransferIngrediente ingrediente);

	TransferIngrediente buscarIngrediente(String idIngrediente);

}
