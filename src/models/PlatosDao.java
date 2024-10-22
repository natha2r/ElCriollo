/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlatosDao {

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    // Método para obtener los platos según la categoría seleccionada
    public ObservableList<String> getPlatosByCategoria(String nombreCategoria) {
        ObservableList<String> platos = FXCollections.observableArrayList();

        try {
            conn = cn.getConnection();
            String query = "SELECT nombrePlato FROM platos p "
                    + "JOIN categoriaPlatos cp ON p.categoriaPlatosId = cp.idCategoriaPlatos "
                    + "WHERE cp.nombreCategoriaPlatos = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, nombreCategoria);
            rs = pst.executeQuery();

            while (rs.next()) {
                platos.add(rs.getString("nombrePlato"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return platos;
    }
}
