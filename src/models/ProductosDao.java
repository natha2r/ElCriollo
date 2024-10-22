/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    java.sql.ResultSet rs;
    
    // Obtener todos las categorias
    public List<Productos> getAllProductos() {
    List<Productos> productos = new ArrayList<>();
    String query = "SELECT * FROM productos";
    
    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        java.sql.ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            Productos producto = new Productos();
            producto.setIdProductos(rs.getString("idProductos"));
            producto.setNombreProducto(rs.getString("nombreProductos"));
            productos.add(producto);
        }
    } catch (SQLException e) {
        // Handle the SQL exception
        e.printStackTrace();
    }
    
    return productos;
}
   public List<Productos> getProductosByCategoria(String idCategoria) {
    List<Productos> productos = new ArrayList<>();
    String query = "SELECT * FROM productos WHERE idCategoria = ?";

    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, idCategoria);
        java.sql.ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Productos producto = new Productos();
            producto.setIdProductos(rs.getString("idProductos"));
            producto.setNombreProducto(rs.getString("nombreProductos"));
            productos.add(producto);
        }
    } catch (SQLException e) {
        // Handle the SQL exception
        e.printStackTrace();
    }
    return productos;
   }
}