
 
package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SopaDao {

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    // Método para obtener todas las sopas de la tabla TipoMenu
    public ObservableList<String> getAllSopas() {
        ObservableList<String> sopasList = FXCollections.observableArrayList();
        String query = "SELECT nombreSopa FROM sopas";

        try {
            conn = cn.getConnection();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            // Agregar cada menú a la lista
            while (rs.next()) {
                String sopa = rs.getString("nombreSopa");
                sopasList.add(sopa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sopasList;
    }
}
