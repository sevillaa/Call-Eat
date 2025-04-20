package Presentacion;

import java.util.List;

import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;
import Negocio.TransferMesa;
import Negocio.TransferPedido;
import Negocio.TransferPlato;

public interface Controlador {
    //METODOS DE EMPLEADO:
    public boolean crearUsuario(TransferEmpleado empleado);

    public boolean accesoCliente(String correo, String contraseña);

    public TransferEmpleado accesoClienteSeguro(String correo, String contraseña);

    public String buscarIdUsuario(String correo, String contraseña);

    public boolean borrarCliente(TransferEmpleado empleado);
    
    public List<TransferEmpleado> listaEmpleados();
    
    public boolean modificarEmpleado(TransferEmpleado modificado);
    
    public boolean eliminarEmpleado(TransferEmpleado empleadoAEliminar);

    // METODOS DE INGREDIENTE:
    public boolean crearIngrediente(TransferIngrediente ingrediente);

    public boolean eliminarIngrediente(TransferIngrediente ingrediente);

    public TransferIngrediente buscarIngrediente(String idIngrediente);
    
    public boolean compruebaIngredientes(TransferPlato plato);

    public void restaIngredientes(TransferPlato plato);

    public void sumaIngredientes(TransferPlato plato);

    // METODOS DE MESA:
    public boolean crearMesa(TransferMesa mesa);

    public boolean eliminarMesa(TransferMesa mesa);

    public TransferMesa buscarMesa(String idMesa);
    
    public List<TransferMesa> obtenerMesas();

    public void anadirMesa(TransferMesa nuevaMesa);

    public void editarMesa(TransferMesa mesaActualizada);

    public void mostrarMenuPrincipal();

    // METODOS DE PEDIDO:
    public boolean crearPedido(TransferPedido pedido);

    public boolean eliminarPedido(TransferPedido pedido);

    public TransferPedido buscarPedido(String idPedido);

    // METODOS DE PLATO:
    public boolean crearPlato(TransferPlato plato);

    public boolean eliminarPlato(TransferPlato plato);

    public TransferPlato buscarPlato(String idPlato);

    // METODOS DE CONTROLADOR
    void accion(int evento, Object datos);

    List<TransferPlato> obtenerPlatos();
}