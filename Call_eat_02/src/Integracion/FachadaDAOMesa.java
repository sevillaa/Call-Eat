package Integracion;

import java.util.List;
import Negocio.TransferMesa;

public interface FachadaDAOMesa {
    public boolean crearMesa(TransferMesa mesa);

    public boolean eliminarMesa(TransferMesa mesa);
    
    public TransferMesa buscarMesa(String idMesa);

    public void actualizarMesa(TransferMesa mesaActualizada);

    public List<TransferMesa> obtenerMesas();
}