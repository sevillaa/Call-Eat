package Presentacion;

import java.util.HashMap;
import javax.swing.JOptionPane;

import Negocio.FactoriaSA;
import Negocio.SAEmpleado;
import Negocio.TransferEmpleado;

public class ControladorImp extends Controlador {

    @Override
    public void accion(int evento, Object datos) {
        switch (evento) {

            case (Eventos.ADD_CLIENTE): {
                @SuppressWarnings("unchecked")
                HashMap<String, String> ids = (HashMap<String, String>) datos;

                String nombre = ids.get("nombre");
                String correo = ids.get("correo");
                String contraseña = ids.get("contraseña");
                String rol = ids.get("rol"); 

                String idUsuario = generarCodigoRandom();

                TransferEmpleado cliente = new TransferEmpleado(idUsuario, nombre, correo, contraseña, rol); 
                
                SAEmpleado saCliente = FactoriaSA.getInstancia().nuevoSAClientes();
                if (saCliente.crearUsuario(cliente)) {
                    JOptionPane.showMessageDialog(null, "Usuario creado correctamente \n      id : " + idUsuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear el usuario");
                }
                break; 

            }

            case (Eventos.INICIAR_SESION): {
                @SuppressWarnings("unchecked")
                HashMap<String, String> ids = (HashMap<String, String>) datos;

                String correo = ids.get("correo");
                String contraseña = ids.get("contraseña");

                SAEmpleado saClientes = FactoriaSA.getInstancia().nuevoSAClientes();
                TransferEmpleado clienteSeguro = saClientes.accesoClienteSeguro(correo, contraseña);

                if (clienteSeguro != null) {
                    GUIEmpleado.getInstancia(Controlador.getInstancia(), clienteSeguro)
                               .actualizar(Eventos.CLIENTE_REGISTRADO, clienteSeguro);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar el usuario");
                }
                break;
            }

            default: {
                break;
            }
        }
    }

    private String generarCodigoRandom() {
        int length = 8;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}

