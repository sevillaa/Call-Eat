package Integracion;

import java.util.List;
import Negocio.TransferIngrediente;

public interface DAOIngrediente {
	
	 public void crear(TransferIngrediente ingrediente);
	 public TransferIngrediente buscarIngrediente(String id);
	 public void actualizar(String id);
	 public void eliminiar();
	 
}
