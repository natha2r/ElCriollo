package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoriaPlatosDao {

    public CategoriaPlatosDao(Connection conn1) {
    }

    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public CategoriaPlatosDao(com.sun.jdi.connect.spi.Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para obtener las categorías según el menú seleccionado
    public ObservableList<String> getCategoriasByMenu(String nombreMenu) {
        ObservableList<String> categorias = FXCollections.observableArrayList();

        try {
            conn = cn.getConnection();
            String query = "SELECT nombrePlato FROM platos cp "
                    + "JOIN TipoMenu tm ON cp.idTipoMenu = tm.idTipoMenu "
                    + "WHERE tm.nombreMenu = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, nombreMenu);
            rs = pst.executeQuery();

            while (rs.next()) {
                categorias.add(rs.getString("categoriaPlatosId"));
                categorias.add(rs.getString("nombrePlato"));
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

        return categorias;
    }
    

    public List<CategoriaPlato> getCategoriasPlatos() {
        List<CategoriaPlato> categoriasPlatos = new ArrayList<>();
        // Aquí iría la lógica para conectarte a la base de datos y obtener las categorías.
        return categoriasPlatos;
    }

    public List<CategoriaPlato> cargarCategorias() {
        List<CategoriaPlato> categorias = new ArrayList<>();
        // Lógica para consultar la base de datos y llenar la lista
        return categorias;
    }

    public static class CategoriaPlato {

        public CategoriaPlato() {
        }

        public String getNombreCategoriaPlatos() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public String getIdCategoriaPlatos() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public String getNombre() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public String getId() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
