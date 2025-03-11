package Negocio;

import java.util.List;

public interface SASmoothies {

	public boolean buscarSmoothie(String nombre, int id);
	List<TransferSmoothies> listaSmoothies();
}
