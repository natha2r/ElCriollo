
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


}
