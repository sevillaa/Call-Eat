package Presentacion;

import java.util.List;

import Negocio.TransferPlato;

public interface FachadaPlato {
	boolean registrarPlato(TransferPlato plato);
    boolean modificarPlato(TransferPlato plato);
    boolean eliminarPlato(TransferPlato plato);
    TransferPlato buscarPlatoPorNombre(String nombre);
    List<TransferPlato> listarPlatos();
}
