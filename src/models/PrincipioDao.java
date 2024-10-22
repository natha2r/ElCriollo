package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PrincipioDao {

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

        // Método para obtener todas las verduras de la tabla Principios
    public ObservableList<String> getAllVerduras() {
        ObservableList<String> verdurasList = FXCollections.observableArrayList();
        String query = "select p.nombre from principios p join tipoPrincipio tp on p.idTipoPrincipio = tp.idTipoPrincipio where tp.tipo = 'Verduras'";
                

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            // Agregar cada verdura a la lista
            while (rs.next()) {
                String verdura = rs.getString("nombre");
                verdurasList.add(verdura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return verdurasList;
    }

    // Método para obtener todos los granos de la tabla Principios
    public ObservableList<String> getAllGranos() {
        ObservableList<String> granosList = FXCollections.observableArrayList();
        String query = "select p.nombre from principios p join tipoPrincipio tp on p.idTipoPrincipio = tp.idTipoPrincipio where tp.tipo = 'Granos'";

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            // Agregar cada grano a la lista
            while (rs.next()) {
                String grano = rs.getString("nombre");
                granosList.add(grano);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return granosList;
    }
}
