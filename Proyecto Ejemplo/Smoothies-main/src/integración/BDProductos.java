package integración;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BDProductos extends BD{
	

	
	public static String buscarProducto(String nombre) {
		String tP = null ; 

	    try {
	    	// Registrar el driver de MySQL
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // Conectar a la base de datos
	        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            String query = "SELECT * FROM ingredientes WHERE nombre = ? ";
	            PreparedStatement stmt = conexion.prepareStatement(query);
	            stmt.setString(1, nombre);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	            	tP = rs.getString("nombre") + ","+ rs.getInt("calorias")+ ","+ rs.getInt("id")+ ","+ rs.getBoolean("disponibilidad");
	                return tP;
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
	    return tP;
	}

	
	public static  boolean añadirProducto(String nombre, int id , int calorias, boolean dispo ) {

		String tP = buscarProducto(nombre) ;
	   
	        try {
	            // Registrar el driver de MySQL
	            Class.forName("com.mysql.cj.jdbc.Driver");
	             
	            // Conectar a la base de datos
	            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            	String query  ; 
	            	 PreparedStatement stmt = null ;
	            	if(tP == null) {
	                query = "INSERT INTO ingredientes (nombre ,id, calorias,disponibilidad) VALUES (?, ?, ?, ?)";
	                stmt = conexion.prepareStatement(query);
	                stmt.setString(1, nombre);
	                stmt.setInt(2, id);
	                stmt.setInt(3, calorias);
	                stmt.setBoolean(4, true);}
	            	 else if(dispo) {
	            		 query = "UPDATE ingredientes SET disponibilidad = 0 WHERE nombre = ? "  ; 
	            		 stmt = conexion.prepareStatement(query);
	 	                 stmt.setString(1, nombre);
	 	                
	                }
	                
	                
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

	
    public static List<String> sacarListaIngredientes(boolean especifico) {
        List<String> listaIngredientes = new ArrayList<>();
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
            	String query = "SELECT * FROM ingredientes";
            	if(especifico) query = "SELECT * FROM ingredientes WHERE disponibilidad = 1" ; 
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    String tP = rs.getString("nombre")+","+rs.getInt("calorias")+","+ rs.getInt("id")+","+rs.getBoolean("disponibilidad");
                    listaIngredientes.add(tP);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: no se pudo cargar el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos MySQL");
            e.printStackTrace();
        }
        return listaIngredientes;
    }
	
    
	public static boolean cambiarEstado(String nombre,  boolean disponibilidad) {
	    
	    try {
	    	// Registrar el driver de MySQL
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // Conectar a la base de datos
	        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            String query = "UPDATE ingredientes SET disponibilidad  = ? WHERE nombre = ?"  ; 
	            PreparedStatement stmt = conexion.prepareStatement(query);
	            stmt.setBoolean(1, !disponibilidad);
	            stmt.setString(2, nombre);
	            
	            
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
	return false ; 

	}

	
}



