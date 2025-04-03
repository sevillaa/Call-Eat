package Integracion;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferUsuarios;

public class DAOUsuariosImp implements DAOUsuarios {
    private static final String FILE_PATH = "clientes.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TransferUsuarios buscarCliente(String correo) {
        List<TransferUsuarios> clientes = cargarClientes();
        
        for (TransferUsuarios cliente : clientes) {
            if (cliente.getCorreo().equals(correo)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean registrarCliente(TransferUsuarios cliente) throws NoSuchAlgorithmException {
        List<TransferUsuarios> clientes = cargarClientes();

        if (buscarCliente(cliente.getCorreo()) == null) {
            clientes.add(cliente);
            guardarClientes(clientes);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(TransferUsuarios cliente) {
        List<TransferUsuarios> clientes = cargarClientes();
        Iterator<TransferUsuarios> iterator = clientes.iterator();

        while (iterator.hasNext()) {
            TransferUsuarios c = iterator.next();
            if (c.getCorreo().equals(cliente.getCorreo()) && c.getContraseña().equals(cliente.getContraseña())) {
                iterator.remove();
                guardarClientes(clientes);
                return true;
            }
        }
        return false;
    }

    private List<TransferUsuarios> cargarClientes() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<TransferUsuarios>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void guardarClientes(List<TransferUsuarios> clientes) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

