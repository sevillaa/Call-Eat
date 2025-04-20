package Integracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferMesa;

public class DAOMesaImp {
    private static final String FILE_PATH = "mesa.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<TransferMesa> mesas;

    public DAOMesaImp() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                this.mesas = objectMapper.readValue(file, new TypeReference<List<TransferMesa>>() {});
            } else {
                this.mesas = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            this.mesas = new ArrayList<>();
        }
    }

    public boolean crearMesa(TransferMesa mesa) {
        try {
           // mesas.removeAll(mesas);
        	if (!mesas.contains(mesa)) {
                mesas.add(mesa);
                objectMapper.writeValue(new File(FILE_PATH), mesas);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public TransferMesa buscarMesa(String id) {
        for (TransferMesa mesa : this.mesas) {
            if (mesa.getId().equals(id)) {
                return mesa;
            }
        }
        return null;
    }

    public void actualizarMesa(TransferMesa mesaActualizada) {
        try {
            for (int i = 0; i < mesas.size(); i++) {
                if (mesas.get(i).getId().equals(mesaActualizada.getId())) {
                    mesas.set(i, mesaActualizada);
                    break;
                }
            }
            objectMapper.writeValue(new File(FILE_PATH), mesas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarMesa(TransferMesa mesaEliminar) {
        try {
            if (mesas.contains(mesaEliminar)) {
                mesas.remove(mesaEliminar);
                objectMapper.writeValue(new File(FILE_PATH), mesas);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<TransferMesa> obtenerMesas() {
        return Collections.unmodifiableList(this.mesas);
    }
}