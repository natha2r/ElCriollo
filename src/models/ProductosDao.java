package models;

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

}
