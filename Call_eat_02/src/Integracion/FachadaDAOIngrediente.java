package Integracion;

import Negocio.TransferIngrediente;

public interface FachadaDAOIngrediente {
	public boolean crearIngrediente(TransferIngrediente ingrediente);

	public boolean eliminarIngrediente(TransferIngrediente ingrediente);

	public TransferIngrediente buscarIngrediente(String idIngrediente);

}
