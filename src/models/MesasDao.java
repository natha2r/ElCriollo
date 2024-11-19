
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MesasDao {
    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
    
    
    
    // Obtener el último ID de mesa
    public String obtenerUltimaMesa() throws Exception {
        String query = "SELECT idMesas FROM mesas ORDER BY idMesas DESC LIMIT 1";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("idMesas");
            }
        }
        return null; // No hay mesas
    }

    // Insertar una nueva mesa
    public void insertarMesa(String idMesa) throws Exception {
        String query = "INSERT INTO mesas (idMesas, numeroMesa) VALUES (?, ?)";
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, idMesa);
            stmt.setInt(2, Integer.parseInt(idMesa.replace("mesa", "")));
            stmt.executeUpdate();
        }
    }

    // Obtener todas las mesas
    public List<String> obtenerTodasLasMesas() throws Exception {
        String query = "SELECT numeroMesa FROM mesas ORDER BY idMesas";
        List<String> mesas = new ArrayList<>();
        try (Connection conn = cn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                mesas.add(rs.getString("numeroMesa"));
            }
        }
        return mesas;
    }
    
    public void eliminarUltimaMesa(String idMesa) throws Exception {
    String query = "DELETE FROM mesas WHERE idMesas = ?";
    try (Connection conn = cn.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, idMesa);
        stmt.executeUpdate();
    }
}
    
}
