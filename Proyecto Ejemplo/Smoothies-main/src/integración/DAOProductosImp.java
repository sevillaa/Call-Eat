package integración;


import java.util.ArrayList;
import java.util.List;

import Negocio.TransferProducto;

public class DAOProductosImp implements DAOProductos {


	@Override
	public TransferProducto buscarProducto(String nombre) {
		String tP = BDProductos.buscarProducto(nombre) ; 
		if(tP!=null) {
			String info[] = tP.split(",");
			return new TransferProducto(info[0],Integer.parseInt(info[1]),Integer.parseInt(info[2]), Boolean.parseBoolean(info[3]));
		}
		
		return null  ; 
		
	  
	}

	@Override
	public boolean añadirProducto(TransferProducto ing ) {
		return BDProductos.añadirProducto(ing.getNombre(), ing.getId(), ing.getCalorias(), ing.getDisp()) ; 
	}

	@Override
    public List<TransferProducto> sacarListaIngredientes(boolean especifico) {
        List<TransferProducto> listaIngredientes = new ArrayList<>();
        List<String> lp = BDProductos.sacarListaIngredientes(especifico); 
	    for(String tP : lp ) {
	    	if(tP!= null ) {
	    	String info[] = tP.split(",");
	    	String nombre  = info[0] ; 
	    	int id = Integer.parseInt(info[1]) ; 
	    	int calorias = Integer.parseInt(info[2]) ; 
	    	boolean disp  = Boolean.parseBoolean(info[3]) ; 
	    	TransferProducto T = new TransferProducto(nombre, id,calorias,disp) ;
	    	listaIngredientes.add(T) ; 
	    	}
	    }
        return listaIngredientes;
    }
	@Override
	public boolean cambiarEstado(String nombre,  boolean disponibilidad) {
		boolean ok = false ; 
		TransferProducto tP = this.buscarProducto(nombre) ;
		if(tP != null) {
			ok = BDProductos.cambiarEstado(tP.getNombre(), tP.getDisp())  ; 
			if(ok){
				tP.cambiarDisp();
				return ok ; 
			} ; 
		}
	
	    
		return ok ; 

	}

	
}
