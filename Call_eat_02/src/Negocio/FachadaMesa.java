package Negocio;

public interface FachadaMesa {
	public boolean crearMesa(TransferMesa mesa);

	public boolean eliminarMesa(TransferMesa mesa);

	public TransferMesa buscarMesa(String idMesa);
}
