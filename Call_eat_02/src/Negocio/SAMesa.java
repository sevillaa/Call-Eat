package Negocio;

public interface SAMesa {

	boolean crearMesa(TransferMesa mesa);

	boolean eliminarMesa(TransferMesa mesa);

	TransferMesa buscarMesa(String idMesa);

}
