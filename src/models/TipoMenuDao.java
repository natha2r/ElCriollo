package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoMenuDao {

    // Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();

    // Constructor (eliminado el parámetro no necesario)
    public TipoMenuDao() {
        // No se necesita constructor específico para Connection
    }

    // Método para obtener todos los menús de la tabla TipoMenu
    public ObservableList<String> getAllMenus() {
        ObservableList<String> menuList = FXCollections.observableArrayList();
        String query = "SELECT nombreMenu FROM TipoMenu";

        try (Connection conn = cn.getConnection(); 
             PreparedStatement pst = conn.prepareStatement(query); 
             ResultSet rs = pst.executeQuery()) {

            // Agregar cada menú a la lista
            while (rs.next()) {
                menuList.add(rs.getString("nombreMenu"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuList;
    }

    // Obtener todos los tipos de menú
    public List<TipoMenu> getTipoMenu() {
        List<TipoMenu> tipoMenus = new ArrayList<>();
        String query = "SELECT * FROM tipoMenu";
        
        try (Connection conn = cn.getConnection(); 
             PreparedStatement pst = conn.prepareStatement(query); 
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                TipoMenu tipoMenu = new TipoMenu();
                tipoMenu.setIdTipoMenu(rs.getInt("idTipoMenu"));
                tipoMenu.setNombreMenu(rs.getString("nombreMenu")); // Asegúrate de que este nombre sea correcto
                tipoMenus.add(tipoMenu); // Agregar a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tipoMenus;
    }

    // Método para obtener tipos de menú (a implementar si es necesario)
    public List<TipoMenu> getTiposMenu() {
        // Lógica para obtener tipos de menú, si se necesita
        return new ArrayList<>(); // Retornar una lista vacía temporalmente
    }
}
