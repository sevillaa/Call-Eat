package integración;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BDCliente extends BD{
	 
	
	public static String buscarCliente(String correo) {
		String tE = null ; 
		 try {
		    	// Registrar el driver de MySQL
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        // Conectar a la base de datos
		        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
		            String query = "SELECT * FROM usuario WHERE correo = ?";
		            PreparedStatement stmt = conexion.prepareStatement(query);
		            stmt.setString(1, correo);
		            ResultSet rs = stmt.executeQuery();
		            if (rs.next()) {
		            	tE = rs.getString("idUsuario")+","+rs.getString("nombre")+","+rs.getString("correo")
		            	+","+rs.getString("password") ;	
		                return tE;
		            } 
		        }
		    } catch (ClassNotFoundException e) {
		        System.out.println("Error: no se pudo cargar el driver de MySQL");
		        e.printStackTrace();
		    } catch (SQLException e) {
		        System.out.println("Error al conectar a la base de datos MySQL");
		        e.printStackTrace();
		    }
		    return tE;
		
		
	}
	public static boolean registrar(String id,String nombre ,String correo,String contraseña) {
	        try {
	            // Registrar el driver de MySQL
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            // Conectar a la base de datos
	            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            	            	
	                String query = "INSERT INTO usuario (idUsuario ,nombre, correo, password) "
	                		+ "VALUES (?, ?, ?, ?)";
	                PreparedStatement stmt = conexion.prepareStatement(query);
	                stmt.setString(1, id);
	                stmt.setString(2, nombre);
	                stmt.setString(3, correo);
	                stmt.setString(4, contraseña);
	             
	                int resultado = stmt.executeUpdate();
	                if (resultado > 0) {
	                    return true;
	                } else {
	                    return false;
	                }
	            }

	        } catch (ClassNotFoundException e) {
	            System.out.println("Error: no se pudo cargar el driver de MySQL");
	            e.printStackTrace();
	        } catch (SQLException e) {
	            System.out.println("Error al conectar a la base de datos MySQL");
	            e.printStackTrace();
	        }
	    
	    return false;
	
	}
	
	public static boolean eliminarCliente(String correo, String contraseña) {
    	try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conectar a la base de datos
            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
            	            	
                String query = "DELETE FROM usuario WHERE correo = ? AND password = ?";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.setString(1, correo);
                stmt.setString(2, contraseña);
             
                int resultado = stmt.executeUpdate();
                if (resultado > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se pudo cargar el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos MySQL");
            e.printStackTrace();
        }
	return false;
	}

}
