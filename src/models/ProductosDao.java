package models;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class ProductosDao {

    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public ObservableList<Productos> obtenerTodosLosProductos() {
        ObservableList<Productos> productos = FXCollections.observableArrayList();
        String query = "SELECT p.idProductos, p.nombreProducto, p.descripcionProducto, p.categoria, p.precio, i.stock, pr.nombreEmpresa "
                + "FROM productos p "
                + "JOIN inventario i "
                + "ON p.idProductos = i.productosId "
                + "JOIN proveedores pr ON i.proveedorId = pr.idProveedores";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Productos producto = new Productos();
                producto.setIdProductos(rs.getString("idProductos"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setDescripcionProducto(rs.getString("descripcionProducto"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setStock(rs.getString("stock"));
                producto.setProveedor(rs.getString("nombreEmpresa"));
                
       
                
                productos.add(producto); // Agregar a la lista
            }
        } catch (SQLException e) {
            // Aquí podrías lanzar una excepción personalizada
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }
    
    public ObservableList<String> obtenerCategorias() {
        ObservableList<String> categorias = FXCollections.observableArrayList();
        categorias.add("Todas las categorías"); // Opción para mostrar todos
        String query = "SELECT DISTINCT categoria FROM productos";
        try (Connection conn = cn.getConnection(); 
             java.sql.Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                categorias.add(rs.getString("categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener categorías: " + e.getMessage());
        }
        return categorias;
    }
    
    public ObservableList<Productos> obtenerProductosPorCategoria(String categoria) {
        ObservableList<Productos> productosFiltrados = FXCollections.observableArrayList();
        String query;
        if ("Todas las categorías".equals(categoria)) {
            query = "SELECT p.idProductos, p.nombreProducto, p.descripcionProducto, p.categoria, p.precio, i.stock, pr.nombreEmpresa "
                  + "FROM productos p "
                  + "JOIN inventario i "
                  + "ON p.idProductos = i.productosId "
                  + "JOIN proveedores pr ON i.proveedorId = pr.idProveedores";
        } else {
            query = "SELECT p.idProductos, p.nombreProducto, p.descripcionProducto, p.categoria, p.precio, i.stock, pr.nombreEmpresa "
                  + "FROM productos p "
                  + "JOIN inventario i "
                  + "ON p.idProductos = i.productosId "
                  + "JOIN proveedores pr ON i.proveedorId = pr.idProveedores "
                  + "WHERE p.categoria = ?";
        }

        try (Connection conn = cn.getConnection(); 
             PreparedStatement pst = conn.prepareStatement(query)) {

            if (!"Todas las categorías".equals(categoria)) {
                pst.setString(1, categoria);
            }
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Productos producto = new Productos();
                producto.setIdProductos(rs.getString("idProductos"));
                producto.setNombreProducto(rs.getString("nombreProducto"));
                producto.setDescripcionProducto(rs.getString("descripcionProducto"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCategoria(rs.getString("categoria"));
                producto.setStock(rs.getString("stock"));
                producto.setProveedor(rs.getString("nombreEmpresa"));
                
                productosFiltrados.add(producto); // Agregar a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al filtrar productos: " + e.getMessage());
        }
        return productosFiltrados;
    }
    
    
}
