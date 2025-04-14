package Integracion;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferEmpleado;

public class DAOEmpleadoImp  {
    private static final String FILE_PATH = "clientes.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public TransferEmpleado buscarEmpleado(String correo) {
        List<TransferEmpleado> clientes = cargarEmpleado();
        
        for (TransferEmpleado cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                return cliente;
            }
        }
        return null;
    }
    //crear un empleado nuevo es lo mismo que registrarlo
    public boolean registrarEmpleado(TransferEmpleado cliente) throws NoSuchAlgorithmException {
        List<TransferEmpleado> empleados = cargarEmpleado();

        if (buscarEmpleado(cliente.getCorreo()) == null) {
        	empleados.add(cliente);
        	guardarEmpleados(empleados);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarEmpleado(TransferEmpleado cliente) {
        List<TransferEmpleado> clientes = cargarEmpleado();
        Iterator<TransferEmpleado> iterator = clientes.iterator();

        while (iterator.hasNext()) {
            TransferEmpleado c = iterator.next();
            if (c.getCorreo().equals(cliente.getCorreo()) && c.getContraseña().equals(cliente.getContraseña())) {
                iterator.remove();
                guardarEmpleados(clientes);
                return true;
            }
        }
        return false;
    }

    private List<TransferEmpleado> cargarEmpleado() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<TransferEmpleado>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void guardarEmpleados(List<TransferEmpleado> empleado) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), empleado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

