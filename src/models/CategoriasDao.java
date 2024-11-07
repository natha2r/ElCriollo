
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;


public class CategoriasDao {
    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    
    
    
    public List<Categorias> obtenerTodasLasCategorias() {
    List<Categorias> categoriaslist = new ArrayList<>();
    String query = "SELECT * FROM categorias";

    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            Categorias categoria = new Categorias();
            categoria.setIdCategoria(rs.getString("idCategoria"));
            categoria.setNombreCategoria(rs.getString("nombreCategoria"));
            categoriaslist.add(categoria);
        }
    } catch (SQLException e) {
        // Aquí podrías lanzar una excepción personalizada
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al obtener categorías: " + e.getMessage());
    }
    return categoriaslist;
}


   
    // Método para obtener todas las categorías
    public List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        String query = "SELECT nombreCategoria FROM categoria";
        try (Connection conn = cn.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("nombreCategoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

   

    // Método para agregar una nueva categoría
    public boolean agregarCategoria(String id, String nombre) {
        String query = "INSERT INTO categoria (idCategoria, nombreCategoria) VALUES (?, ?)";
        
        try (Connection conn = cn.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, nombre);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean existeIdCategoria(String id) {
        String query = "SELECT COUNT(*) FROM categoria WHERE idCategoria = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Retorna true si existe el ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si el ID no existe o si ocurre un error
    }

    public boolean existeNombreCategoria(String nombre) {
        String query = "SELECT COUNT(*) FROM categoria WHERE nombreCategoria = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, nombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Retorna true si existe el Nombre
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si el Nombre no existe o si ocurre un error
    }
    
    /**
     * ***********************************************************************/
    
    
   
    // Método para obtener todos los nombres de las categorías
    public List<String> obtenerCategoriasNombres() {
        List<String> categorias = new ArrayList<>();
        String query = "SELECT nombreCategoria FROM categoria"; // Suponiendo que la tabla se llama 'categoria'

        try (Connection conn = cn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categorias.add(rs.getString("nombreCategoria"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo de errores adecuado aquí
        }
        return categorias;
    }

    


    

    
    
    
  
    
    
    
    
}
