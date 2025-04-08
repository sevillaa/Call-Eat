package Integracion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferIngrediente;

public class DAOIngredienteImp implements DAOIngrediente {

	private static final String FILE_PATH = "ingredientes.json";
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void crear(TransferIngrediente ingrediente) {
		try {
			List<TransferIngrediente> ingredientes = obtenerTodos();
			ingredientes.add(ingrediente);
			objectMapper.writeValue(new File(FILE_PATH), ingredientes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TransferIngrediente buscarIngrediente(String id) {
		List<TransferIngrediente> ingredientes = obtenerTodos();

		for (TransferIngrediente ingrediente : ingredientes) {
			if (ingrediente.getId().equals(id)) {
				return ingrediente;
			}
		}

		return null;
	}
	
	@Override
	public void actualizar(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminiar() {
		// TODO Auto-generated method stub
		
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
