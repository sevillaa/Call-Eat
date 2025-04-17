package Integracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferIngrediente;
import Negocio.TransferPlato;

public class DAOIngredienteImp {

	private List<TransferIngrediente> ingredientes;
	private static final String FILE_PATH = "ingredientes.json";
	private static ObjectMapper objectMapper = new ObjectMapper();

	public DAOIngredienteImp() {
		try {
			this.ingredientes = objectMapper.readValue(new File(FILE_PATH),
					new TypeReference<List<TransferIngrediente>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean crearIngrediente(TransferIngrediente ingrediente) {
		try {
			if (!ingredientes.contains(ingrediente)) {
				ingredientes.add(ingrediente);
				objectMapper.writeValue(new File(FILE_PATH), ingredientes);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public TransferIngrediente buscarIngrediente(String id) {
		List<TransferIngrediente> ingredientes = obtenerTodos();

		for (TransferIngrediente ingrediente : ingredientes) {
			if (ingrediente.getId().equals(id)) {
				return ingrediente;
			}
		}

		return null;
	}

	public void actualizarIngrediente(TransferIngrediente ingredienteActualizado ) {
		try {
			int i = 0;
			while (i < ingredientes.size() && !ingredientes.get(i).getId().equals(ingredienteActualizado.getId())) {
				i++;
			}
			ingredientes.set(i, ingredienteActualizado);
			objectMapper.writeValue(new File(FILE_PATH), ingredientes);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean eliminiarIngrediente(TransferIngrediente ingredienteEliminar) {
		try {
			if (ingredientes.contains(ingredienteEliminar)) {
				ingredientes.remove(ingredienteEliminar);
				objectMapper.writeValue(new File(FILE_PATH), ingredientes);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private List<TransferIngrediente> obtenerTodos() {
		try {
			File file = new File(FILE_PATH);

			if (!file.exists()) {
				return new ArrayList<>();
			}

			return objectMapper.readValue(file, new TypeReference<List<TransferIngrediente>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

}
