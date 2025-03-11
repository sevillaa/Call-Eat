package Presentacion;

public class Main {

    public static void main(String[] args) {
        // Crear una instancia de Controlador
        Controlador controlador = Controlador.getInstancia(); 

        // Pasar el controlador al constructor de GUIClientesImp
        GUISmoothiesImp smoothiesImp = new GUISmoothiesImp(controlador);
    }
}
