package integración;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Negocio.TransferProducto;
import Negocio.TransferSmoothies;

public class DAOSmoothiesImp implements DAOSmoothies{

	@Override
	public TransferSmoothies buscarSmoothies(String nombre, int id) {
		
		TransferSmoothies tS = new TransferSmoothies();
	    String url = "jdbc:mysql://localhost:3306/smoothies";
	    String usuario = "root";
	    String contraseña2 = "contraseñaSQL";
		
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
	            	 tS.setId(rs.getInt("id"));
	                 tS.setNombre(rs.getString("nombre"));	            	
		             tS.setDescripcion(rs.getString("descripcion"));
		             tS.setCalorias(rs.getInt("calorias"));

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

	@Override
	public void añadirSmoothie(String nombre, List<TransferProducto> ingredientes, int id, int calorias) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TransferSmoothies> sacarListaSmoothies() {

		List<TransferSmoothies> listaSmoothies = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/smoothies";
        String usuario = "root";
        String contraseña2 = "contraseñaSQL";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña2)) {
            	
                String query = "SELECT * FROM batidos";
                PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    TransferSmoothies tS = new TransferSmoothies();
                    tS.setId(rs.getInt("id"));
                    tS.setNombre(rs.getString("nombre"));	            	
	            	tS.setDescripcion(rs.getString("descripcion"));
	            	tS.setCalorias(rs.getInt("calorias"));
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
