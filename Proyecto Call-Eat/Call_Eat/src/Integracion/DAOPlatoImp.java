package Integracion;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferEmpleado;
import Negocio.TransferPlato;

public class DAOPlatoImp implements DAOPlato {

	private static final String FILE_PATH = "platos.json";
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public boolean registrarPlato(TransferPlato plato) throws NoSuchAlgorithmException {
		List<TransferPlato> platos = cargarPlatos();

		if (buscarPlato(plato.getId()) == null) {
			platos.add(plato);
			guardarPlatos(platos);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public TransferPlato buscarPlato(String id) {
		List<TransferPlato> platos = cargarPlatos();

		for (TransferPlato plato : platos) {
			if (plato.getId().equals(id)) {
				return plato;
			}
		}
		return null;
	}

	@Override
	public boolean actualizarPlato(String idPlato) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarPlato(TransferPlato plato) {
		List<TransferPlato> platos = cargarPlatos();
		Iterator<TransferPlato> iterator = platos.iterator();

		while (iterator.hasNext()) {
			TransferPlato c = iterator.next();
			if (c.getId().equals(plato.getId())) {
				iterator.remove();
				guardarPlatos(platos);
				return true;
			}
		}
		return false;
	}

	private List<TransferPlato> cargarPlatos() {
		try {
			File file = new File(FILE_PATH);
			if (!file.exists()) {
				return new ArrayList<>();
			}
			return objectMapper.readValue(file, new TypeReference<List<TransferPlato>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private void guardarPlatos(List<TransferPlato> clientes) {
		try {
			objectMapper.writeValue(new File(FILE_PATH), clientes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
