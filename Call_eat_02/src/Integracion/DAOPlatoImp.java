package Integracion;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Negocio.TransferEmpleado;
import Negocio.TransferIngrediente;
import Negocio.TransferPlato;

public class DAOPlatoImp {

	private List<TransferPlato> platos;
	private static final String FILE_PATH = "carta.json";
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public DAOPlatoImp(){
		try {
			this.platos = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<TransferPlato>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public boolean crearPlato(TransferPlato plato) {
		try {
			if (!platos.contains(plato)) {
				platos.add(plato);
				objectMapper.writeValue(new File(FILE_PATH), platos);
				return true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public TransferPlato buscarPlato(String id) {
		for (TransferPlato plato : this.platos) {
			if (plato.getId().equals(id)) {
				return plato;
			}
		}

		return null;
	}

	public void actualizarPlato(TransferPlato platoActualizado) {
		try {
			int i = 0;
			while (i < platos.size() && !platos.get(i).getId().equals(platoActualizado.getId())) {
				i++;
			}
			 if (i != platos.size()) {
				 platos.set(i, platoActualizado);
					objectMapper.writeValue(new File(FILE_PATH), platos);
		        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean eliminarPlato(TransferPlato platoEliminar) {
		try {
			if (platos.contains(platoEliminar)) {
				platos.remove(platoEliminar);
				objectMapper.writeValue(new File(FILE_PATH), platos);
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public List<TransferPlato> obtenerPlatos() {
		return Collections.unmodifiableList(this.platos);
	}
	public boolean comprobarPlato(TransferPlato plato) {
		for(TransferPlato p : this.platos) {
			if(p.getId().equals(plato.getId()) || p.getNombre().equals(plato.getNombre())) {
				return true;
			}
		}
		
		return false;
	}
}
