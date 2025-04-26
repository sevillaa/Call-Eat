package Presentacion;

public abstract class GUIMenuPrincipal {
	private static GUIMenuPrincipal instancia = null;
	
	public static GUIMenuPrincipal getInstancia(Controlador controlador, Object datos) {
		 if (instancia == null)
	            instancia = new GUIMenuPrincipalImp(controlador, datos);
	     return instancia;
	}
	public abstract void actualizar(int evento, Object datos);
	public static void resetInstancia() {
	    instancia = null;
	}

}
