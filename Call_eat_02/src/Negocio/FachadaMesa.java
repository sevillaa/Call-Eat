package Negocio;

import java.util.List;

public interface FachadaMesa {
    public boolean crearMesa(TransferMesa mesa);

    public boolean eliminarMesa(TransferMesa mesa);

    public TransferMesa buscarMesa(String idMesa);

    public boolean modificarMesa(TransferMesa mesaActualizada);

    public List<TransferMesa> obtenerMesas();
}