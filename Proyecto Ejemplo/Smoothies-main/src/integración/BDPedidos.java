package integración;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class BDPedidos extends BD {
	
	public static String buscarPedido(int idPedido) {
		
	    try {
	    	// Registrar el driver de MySQL
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // Conectar a la base de datos
	        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            String query = "SELECT * FROM pedidos WHERE nombre = ?";
	            PreparedStatement stmt = conexion.prepareStatement(query);
	            stmt.setInt(1, idPedido);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	            	return rs.getInt("idPedido") + "," +rs.getString("batidos")+ "," +rs.getInt("precio")+ "," +rs.getInt("unidades")+ "," +rs.getString("idUsuario") ;
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
	    return null ; 
	  
	}


	public static boolean crearPedido(int idPedido, String batidos, int precio, int unidades, String idUsuario) {
	   
	    try {
	        // Registrar el driver de MySQL
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        // Conectar a la base de datos
	        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            // Preparar la consulta SQL para insertar el pedido
	            String query = "INSERT INTO pedidos (idPedido, batidos, precio, unidades, idUsuario) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement stmt = conexion.prepareStatement(query);
	            stmt.setInt(1, idPedido);
	            stmt.setString(2, batidos);
	            stmt.setInt(3, precio);
	            stmt.setInt(4, unidades);
	            stmt.setString(5, idUsuario);

	            // Ejecutar la consulta
	            int filasAfectadas = stmt.executeUpdate();
	            // Verificar si se insertó correctamente el pedido
	            if (filasAfectadas > 0) {
	                return true; // Se creó el pedido exitosamente
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Error: no se pudo cargar el driver de MySQL");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Error al conectar a la base de datos MySQL");
	        e.printStackTrace();
	    }
	    return false; // No se pudo crear el pedido
	}


	public static List<String> sacarListaPedidos(String idUsuario) {
	    List<String> listaPedidos = new ArrayList<>();
	    

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            String query = "SELECT * FROM pedidos WHERE idUsuario = ?";
	            PreparedStatement stmt = conexion.prepareStatement(query);
	            stmt.setString(1, idUsuario);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	                String tP = rs.getInt("idPedido")+ ","+ rs.getString("batidos")+ ","+ rs.getInt("precio")+ ","+ rs.getInt("unidades")+ ","+rs.getString("idUsuario");
	                listaPedidos.add(tP); 
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Error: no se pudo cargar el driver de MySQL");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Error al conectar a la base de datos MySQL");
	        e.printStackTrace();
	    }
	    return listaPedidos;
	}

	
	public static List<String> sacarTodosPedidos() {
	    List<String> listaPedidos = new ArrayList<>();
	

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
	            String query = "SELECT * FROM pedidos"; 
	            PreparedStatement stmt = conexion.prepareStatement(query);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	                String tP = rs.getInt("idPedido")+ ","+ rs.getString("batidos")+ ","+ rs.getInt("precio")+ ","+ rs.getInt("unidades")+ ","+rs.getString("idUsuario");
	                listaPedidos.add(tP); 
	            }
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Error: no se pudo cargar el driver de MySQL");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Error al conectar a la base de datos MySQL");
	        e.printStackTrace();
	    }
	    return listaPedidos;
	}


	public static boolean eliminarPedido(String pedido) {
		try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Conectar a la base de datos
            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
            	            	
                String query = "DELETE FROM pedidos WHERE idPedido = ? ";
                PreparedStatement stmt = conexion.prepareStatement(query);
                stmt.setString(1, pedido);
              
             
                int i = stmt.executeUpdate();
                if(i != 0) return true  ; 
               
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
