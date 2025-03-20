package Presentacion;

public class Main {
    public static void main(String[] args) {
        // Obtener la instancia del controlador singleton.
        Controlador controlador = Controlador.getInstancia();
        
        // Iniciar la interfaz gráfica, por ejemplo, la ventana de cliente.
        // Se pasa el controlador y, si no hay datos iniciales, null.
        GUIUsuarios.getInstancia(controlador, null);
    }
}
