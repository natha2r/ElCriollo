
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ProveedoresDao {
    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    
    
    
    
     public boolean agregarProveedor(String id, String nombre, String contacto, String telefono, String email, String direccion, String terminoPago) {
        String query = "INSERT INTO proveedores (idProveedores, nombreEmpresa, contacto, telefono, email, direccion, terminoPago) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = cn.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, contacto);
            pstmt.setString(4, telefono);
            pstmt.setString(5, email);
            pstmt.setString(6, direccion);
            pstmt.setString(7, terminoPago);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    


    
    // Método para verificar si el ID ya existe
    public  boolean existeId(String id) {
        String sql = "SELECT COUNT(*) FROM proveedores WHERE idProveedores = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para verificar si el Nombre ya existe
    public boolean existeNombre(String nombre) {
        String sql = "SELECT COUNT(*) FROM proveedores WHERE nombreEmpresa = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
