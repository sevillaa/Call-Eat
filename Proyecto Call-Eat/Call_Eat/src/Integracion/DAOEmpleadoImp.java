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

public class DAOEmpleadoImp implements DAOEmpleado {
    private static final String FILE_PATH = "clientes.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TransferEmpleado buscarCliente(String correo) {
        List<TransferEmpleado> clientes = cargarClientes();
        
        for (TransferEmpleado cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean registrarCliente(TransferEmpleado cliente) throws NoSuchAlgorithmException {
        List<TransferEmpleado> clientes = cargarClientes();

        if (buscarCliente(cliente.getCorreo()) == null) {
            clientes.add(cliente);
            guardarClientes(clientes);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(TransferEmpleado cliente) {
        List<TransferEmpleado> clientes = cargarClientes();
        Iterator<TransferEmpleado> iterator = clientes.iterator();

        while (iterator.hasNext()) {
            TransferEmpleado c = iterator.next();
            if (c.getCorreo().equals(cliente.getCorreo()) && c.getContraseña().equals(cliente.getContraseña())) {
                iterator.remove();
                guardarClientes(clientes);
                return true;
            }
        }
        return false;
    }

    private List<TransferEmpleado> cargarClientes() {
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

    private void guardarClientes(List<TransferEmpleado> clientes) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

