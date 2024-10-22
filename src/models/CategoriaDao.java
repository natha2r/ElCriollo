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

public class CategoriaDao {

    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    // Obtener todos las categorias
    public List<CategoriaPlatos> getCategoriasPlatos() {
    List<CategoriaPlatos> categoriasPlatos = new ArrayList<>();
    String query = "SELECT * FROM categoriaplatos";
    
    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            CategoriaPlatos categoriaPlatos = new CategoriaPlatos();
            categoriaPlatos.setIdCategoriaPlatos(rs.getString("idCategoriaPlatos"));
            categoriaPlatos.setNombreCategoriaPlatos(rs.getString("nombreCategoriaPlatos"));
            categoriasPlatos.add(categoriaPlatos);
        }
    } catch (SQLException e) {
        // Handle the SQL exception
        e.printStackTrace();
    }
    
    return categoriasPlatos;
}

    public List<Categorias> getCategoriasProductos(String idCategoriaPlato) {
    List<Categorias> categoriasProductos = new ArrayList<>();
    String query = "SELECT * FROM categoria WHERE idCategoriaPlato = ?";

    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, idCategoriaPlato);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Categorias categoria = new Categorias();
            categoria.setIdCategoria(rs.getString("idCategoria"));
            categoria.setNombreCategoria(rs.getString("nombreCategoria"));
            categoriasProductos.add(categoria);
        }
    } catch (SQLException e) {
        // Handle the SQL exception
        e.printStackTrace();
    }

    return categoriasProductos;
}
    
    public List<Categorias> getCategoriaPlatos() {
    List<Categorias> categoriasPlatos = new ArrayList<>();
    String query = "SELECT * FROM categoriaPlatos";

    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Categorias categoria = new Categorias();
            categoria.setIdCategoria(rs.getString("idCategoriaPlatos"));
            categoria.setNombreCategoria(rs.getString("nombreCategoriaPlatos"));
            categoriasPlatos.add(categoria);
        }
    } catch (SQLException e) {
        // Handle the SQL exception
        e.printStackTrace();
    }

    return categoriasPlatos;
}
    
}
