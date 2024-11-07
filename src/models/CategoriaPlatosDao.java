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

    private ConnectionMySQL cn;  // Connection manager
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;


    public CategoriaPlatosDao() {
        cn = new ConnectionMySQL(); // Initialize the connection manager
    }

    // Method to get categories based on the selected menu
    public ObservableList<String> getCategoriasByMenu(String nombreMenu) {
        ObservableList<String> categoriasPlatos = FXCollections.observableArrayList();
        String query = "SELECT nombrePlato FROM platos cp "
                + "JOIN categoriaPlatos tm ON cp.idCategoriaPlatos = tm.idCategoriaPlatos "
                + "WHERE tm.nombreCategoriaPlatos = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, nombreMenu);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    categoriasPlatos.add(rs.getString("nombrePlato"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoriasPlatos;
    }

    public List<CategoriaPlatos> getCategoriasPlatos() {
        List<CategoriaPlatos> categorias = new ArrayList<>();
        String query = "SELECT * FROM categoriaPlatos";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                CategoriaPlatos categoria = new CategoriaPlatos();
                categoria.setIdCategoriaPlatos(rs.getString("idCategoriaPlatos"));
                categoria.setNombreCategoriaPlatos(rs.getString("nombreCategoriaPlatos"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    public List<CategoriaPlatos> getCategoriasPorTipo(int idTipoMenu) {
        List<CategoriaPlatos> categorias = new ArrayList<>();
        String query = "SELECT * FROM categoriaPlatos WHERE idTipoMenu = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, idTipoMenu);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CategoriaPlatos categoria = new CategoriaPlatos();
                    categoria.setIdCategoriaPlatos(rs.getString("idCategoriaPlatos"));
                    categoria.setNombreCategoriaPlatos(rs.getString("nombreCategoriaPlatos"));
                    categoria.setIdTipoMenu(rs.getInt("idTipoMenu"));
                    categorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    // Método para obtener las categorías según el menú seleccionado
    public ObservableList<String> getCategoriasByMenuM(String nombreMenu) {
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
    public ObservableList<String> getPlatosByMenuM(String nombreMenu) {
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
    public List<CategoriaPlatos> getCategoriasPlatosM() throws SQLException {
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
