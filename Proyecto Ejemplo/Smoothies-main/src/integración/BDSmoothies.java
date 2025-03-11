package integración;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BDSmoothies extends BD{
	
public static String buscarSmoothies(String nombre, int id) {
		
	String tS = null  ; 
	   
		
	    try {
	    	// Registrar el driver de MySQL
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // Conectar a la base de datos
	        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            String query = "SELECT * FROM batidos WHERE nombre = ?";
	            PreparedStatement stmt = conexion.prepareStatement(query);
	            stmt.setString(1, nombre);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	            	 tS = rs.getInt("id") + "," +rs.getString("nombre")+ "," +rs.getString("descripcion")+ "," + rs.getInt("calorias");
	                return tS;
	            } else {
	                return null;
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Error: no se pudo cargar el driver de MySQL");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Error al conectar a la base de datos MySQL");
	        e.printStackTrace();
	    }
	    return tS;
	}

	
	public static void añadirSmoothie(String nombre, String listaing, int id, int calorias) {
		// TODO Auto-generated method stub
		
	}


	public static List<String> sacarListaSmoothies() {

	List<String> listaSmoothies = new ArrayList<>();
		
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
            	
                String query = "SELECT * FROM batidos";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    String tS = rs.getInt("id")+","+rs.getString("nombre")+","+rs.getString("descripcion")+","+rs.getInt("calorias");
                    listaSmoothies.add(tS);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se pudo cargar el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos MySQL");
            e.printStackTrace();
        }
        return listaSmoothies;
	}

}
