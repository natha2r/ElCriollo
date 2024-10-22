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

    private ConnectionMySQL cn = new ConnectionMySQL();
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;

    // Constructor por defecto
    public CategoriaPlatosDao() {
    }

    // Método para obtener las categorías según el menú seleccionado
    public ObservableList<String> getCategoriasByMenu(String nombreMenu) {
        ObservableList<String> categorias = FXCollections.observableArrayList();

        try {
            conn = cn.getConnection();
            String query = "SELECT nombreCategoriaPlatos FROM categoriaPlatos cp "
                    + "JOIN TipoMenu tm ON cp.idTipoMenu = tm.idTipoMenu "
                    + "WHERE tm.nombreMenu = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, nombreMenu);
            rs = pst.executeQuery();

            while (rs.next()) {
                categorias.add(rs.getString("nombreCategoriaPlatos"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return categorias;
    }

    // Método para obtener los platos según el menú seleccionado
    public ObservableList<String> getPlatosByMenu(String nombreMenu) {
        ObservableList<String> platos = FXCollections.observableArrayList();

        try {
            conn = cn.getConnection();
            String query = "SELECT nombrePlato FROM platos cp "
                    + "JOIN TipoMenu tm ON cp.idTipoMenu = tm.idTipoMenu "
                    + "WHERE tm.nombreMenu = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, nombreMenu);
            rs = pst.executeQuery();

            while (rs.next()) {
                platos.add(rs.getString("nombrePlato"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }

        return platos;
    }

    // Método para obtener todas las categorías de platos
    public List<CategoriaPlatos> getCategoriasPlatos() throws SQLException {
        List<CategoriaPlatos> categorias = new ArrayList<>();
        String query = "SELECT * FROM tipoMenu";
        try {
            conn = cn.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CategoriaPlatos categoria = new CategoriaPlatos();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNombre(resultSet.getString("nombre"));
                categorias.add(categoria);
            }
        } finally {
            closeResources();
        }
        return categorias;
    }

    // Método para cerrar recursos
    private void closeResources() {
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
}
