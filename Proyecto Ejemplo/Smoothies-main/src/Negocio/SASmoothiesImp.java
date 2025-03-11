package Negocio;

import java.util.List;

import integración.DAOSmoothies;
import integración.FactoriaDAO;

public class SASmoothiesImp implements SASmoothies {

	@Override
	public boolean buscarSmoothie(String nombre, int id) {
		
		DAOSmoothies daoSmoothies = (DAOSmoothies) FactoriaDAO.getInstancia().nuevoDAOSmoothies();
		TransferSmoothies ok;
		
		try {
			ok = daoSmoothies.buscarSmoothies(nombre, id);
			if(ok !=null) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<TransferSmoothies> listaSmoothies() {
		DAOSmoothies daoSmoothies = (DAOSmoothies) FactoriaDAO.getInstancia().nuevoDAOSmoothies();
		List<TransferSmoothies> listaSmoothies = null;
		
		try {
			listaSmoothies = daoSmoothies.sacarListaSmoothies();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaSmoothies;
	}

}
