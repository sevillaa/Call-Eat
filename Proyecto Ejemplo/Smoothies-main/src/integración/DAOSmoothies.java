package integración;

import java.util.List;

import Negocio.TransferProducto;
import Negocio.TransferSmoothies;

public interface DAOSmoothies {

	public TransferSmoothies buscarSmoothies(String nombre,int id);
	
	public void añadirSmoothie(String nombre, List<TransferProducto> ingredientes, int id, int calorias);
	
	public List<TransferSmoothies> sacarListaSmoothies();
}
