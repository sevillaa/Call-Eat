package Integracion;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferCliente;

public class DAOClienteImp implements DAOCliente {
    private static final String FILE_PATH = "clientes.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TransferCliente buscarCliente(String correo) {
        List<TransferCliente> clientes = cargarClientes();
        
        for (TransferCliente cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean registrarCliente(TransferCliente cliente) throws NoSuchAlgorithmException {
        List<TransferCliente> clientes = cargarClientes();

        if (buscarCliente(cliente.getCorreo()) == null) {
            clientes.add(cliente);
            guardarClientes(clientes);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(TransferCliente cliente) {
        List<TransferCliente> clientes = cargarClientes();
        Iterator<TransferCliente> iterator = clientes.iterator();

        while (iterator.hasNext()) {
            TransferCliente c = iterator.next();
            if (c.getCorreo().equals(cliente.getCorreo()) && c.getContraseña().equals(cliente.getContraseña())) {
                iterator.remove();
                guardarClientes(clientes);
                return true;
            }
        }
        return false;
    }

    private List<TransferCliente> cargarClientes() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<TransferCliente>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void guardarClientes(List<TransferCliente> clientes) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

