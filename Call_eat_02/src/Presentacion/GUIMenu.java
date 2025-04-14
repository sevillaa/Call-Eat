package Presentacion;

public abstract class GUIMenu {
	private static GUIMenu instancia = null;
	
	public static GUIMenu getInstancia(Controlador controlador, Object datos) {
		 if (instancia == null)
	            instancia = new GUIMenuImp(controlador, datos);
	     return instancia;
	}
	public abstract void actualizar(int evento, Object datos);
	public static void resetInstancia() {
	    instancia = null;
	}

}
