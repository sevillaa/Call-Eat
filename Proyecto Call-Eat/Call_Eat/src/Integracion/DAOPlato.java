package Integracion;

import java.security.NoSuchAlgorithmException;

import Negocio.TransferPlato;

public interface DAOPlato {
	public boolean registrarPlato(TransferPlato idPlato) throws NoSuchAlgorithmException;
	public TransferPlato buscarPlato(String idPlato);
	public boolean actualizarPlato(String idPlato);
	public boolean eliminarPlato(TransferPlato idPlato);
}
