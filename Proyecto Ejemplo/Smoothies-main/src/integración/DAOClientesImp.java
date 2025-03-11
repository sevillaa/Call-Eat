package integración;

import java.security.NoSuchAlgorithmException;

import Negocio.TransferCliente;

public class DAOClientesImp implements DAOCliente{

	@Override
	public TransferCliente buscarCliente(String correo) {
		String tC= null;
	
			tC = BDCliente.buscarCliente(correo);
			if(tC != null) {
				String info[] = tC.split(",");
					return new TransferCliente(info[0],info[1],info[2],info[3]);
			}
			else {
				  return null;
			}
	}

	@Override
	public boolean registrarCliente(TransferCliente cliente) throws NoSuchAlgorithmException {
		if(buscarCliente(cliente.getCorreo()) == null) {
			return BDCliente.registrar(cliente.getId(),cliente.getNombre() ,cliente.getCorreo(),
					cliente.getContraseña());
		}else {
			return false ; 
		}
	}
	    

	@Override
	public boolean eliminarCliente(TransferCliente tE) {
		
	   
	    if (tE != null) { // significa que el cliente no existe
	    	return BDCliente.eliminarCliente(tE.getCorreo(), tE.getContraseña()) ; 
	    }
	    else {
	    	return false ; 
	    }

	}

}
