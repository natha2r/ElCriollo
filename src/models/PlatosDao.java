/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

public class PlatosDao {

    // Instanciar la conexión 
    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    // Obtener todos los productos
    public List<Platos> getAllPlatos() {
        List<Platos> platos = new ArrayList<>();
        String query = "SELECT * FROM platos";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Platos plato = new Platos();
                plato.setIdPlatos(rs.getString("idPlatos"));
                plato.setNombrePlato(rs.getString("nombrePlato"));
                platos.add(plato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return platos;
    }

    // Obtener productos por categoría
    public List<Platos> getPlatosByCategoria(String idCategoriaPlatos) {
        List<Platos> platos = new ArrayList<>();
        String query = "SELECT * FROM platos WHERE categoriaPlatosId = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, idCategoriaPlatos);
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Platos plato = new Platos();
                plato.setIdPlatos(rs.getString("idPlatos"));
                plato.setNombrePlato(rs.getString("nombrePlato")); // Asegúrate de que sea el nombre correcto
                plato.setCategoriaPlatosId(rs.getString("categoriaPlatosId"));
                platos.add(plato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        return platos;
    }

    // Marcar un producto como no disponible
    public void marcarComoNoDisponible(String idPlatos) {
        String query = "UPDATE platos SET disponible = ? WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setBoolean(1, false); // Cambiar a no disponible
            pst.setString(2, idPlatos);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    // Método para obtener los platos según la categoría seleccionada
    public ObservableList<String> getPlatosByCategoriaM(String nombreCategoria) {
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

    // ************************************************** 07/11/24 *******************************************************
    
    // Método para obtener el precio de un plato por su nombre
    public String obtenerPrecioPorNombre(String nombrePlato) {
        String precio = "";
        String query = "SELECT precio FROM platos WHERE nombrePlato = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nombrePlato);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                precio = rs.getString("precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return precio;
    }
    
    // Traer nombre del plato, precio y tipo 
    public List<Platos> getPlatosByCategoriaC(String nombreCategoria) {
    List<Platos> platos = new ArrayList<>();
    String query = "SELECT p.idPlatos, p.nombrePlato, p.precio, p.esMini, p.categoriaPlatosId "
                 + "FROM platos p "
                 + "JOIN categoriaPlatos cp ON p.categoriaPlatosId = cp.idCategoriaPlatos "
                 + "WHERE cp.nombreCategoriaPlatos = ?";

    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, nombreCategoria);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            CheckBox miniCheckBox = new CheckBox();
            miniCheckBox.setSelected(rs.getBoolean("esMini"));

            Platos plato = new Platos(
                rs.getString("idPlatos"),
                rs.getString("nombrePlato"),
                rs.getDouble("precio"),
                rs.getString("categoriaPlatosId"),
                miniCheckBox
            );
            platos.add(plato);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return platos;
}



}
