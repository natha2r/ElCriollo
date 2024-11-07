package models;

import java.beans.Statement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    String query = "SELECT p.idProductos, p.nombreProducto, p.precio, "
                 + "c.nombreCategoria, i.stock, pr.nombreEmpresa "
                 + "FROM productos p "
                 + "JOIN inventario i ON p.idProductos = i.productosId "
                 + "JOIN proveedores pr ON i.proveedorId = pr.idProveedores "
                 + "JOIN categoria c ON p.categoria = c.idCategoria";

    try (Connection conn = cn.getConnection(); 
         PreparedStatement pst = conn.prepareStatement(query); 
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            Productos producto = new Productos();
            producto.setIdProductos(rs.getString("idProductos"));
            producto.setNombreProducto(rs.getString("nombreProducto"));
            
            producto.setPrecio(rs.getDouble("precio"));
            producto.setCategoria(rs.getString("nombreCategoria")); // Obtiene el nombre de la categoría
            producto.setStock(rs.getString("stock"));
            producto.setProveedor(rs.getString("nombreEmpresa")); // Obtiene el nombre del proveedor

            productos.add(producto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al obtener productos: " + e.getMessage());// Manejar excepción
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
    
    
    
    /* ******************** METODO PARA AGREGAR PRODUCTOS ********************  */
    
    
    
    public boolean agregarProductoCompleto(String idProducto, String nombreProducto, double precio, String idCategoria, String idInventario, String stock, java.sql.Date fechaRecepcion, String idProveedor) {
    String productoQuery = "INSERT INTO productos (idProductos, nombreProducto, precio, categoria) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE nombreProducto = VALUES(nombreProducto), precio = VALUES(precio)";
    String inventarioQuery = "INSERT INTO inventario (idInventario, productosId, stock, fechaRecepcion, proveedorId) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE stock = VALUES(stock), fechaRecepcion = VALUES(fechaRecepcion)";

    Connection conn = null;

    try {
        conn = cn.getConnection();
        if (conn == null) {
            System.err.println("Error: no se pudo establecer la conexión con la base de datos.");
            return false;
        }
        conn.setAutoCommit(false); // Iniciar transacción
        System.out.println("Conexión establecida y transacción iniciada.");

        // Inserta o actualiza el producto
        System.out.println("Ejecutando inserción o actualización para producto.");
        try (PreparedStatement psProducto = conn.prepareStatement(productoQuery)) {
            psProducto.setString(1, idProducto);
            psProducto.setString(2, nombreProducto);
            psProducto.setDouble(3, precio);
            psProducto.setString(4, idCategoria); // Se usa el ID de la categoría
            psProducto.executeUpdate();
            System.out.println("Producto insertado/actualizado correctamente.");
        }

        // Inserta o actualiza el inventario
        System.out.println("Ejecutando inserción o actualización para inventario.");
        try (PreparedStatement psInventario = conn.prepareStatement(inventarioQuery)) {
            psInventario.setString(1, idInventario);
            psInventario.setString(2, idProducto);
            psInventario.setString(3, stock);
            psInventario.setDate(4, fechaRecepcion);
            psInventario.setString(5, idProveedor); // Se usa el ID del proveedor
            psInventario.executeUpdate();
            System.out.println("Inventario insertado/actualizado correctamente.");
        }

        conn.commit(); // Confirmar la transacción
        System.out.println("Transacción completada exitosamente.");
        return true;
    } catch (SQLException e) {
        System.err.println("Error en la transacción: " + e.getMessage());
        e.printStackTrace();
        if (conn != null) {
            try {
                conn.rollback(); // Revertir la transacción en caso de error
                System.out.println("Transacción revertida.");
            } catch (SQLException rollbackEx) {
                System.err.println("Error al hacer rollback: " + rollbackEx.getMessage());
                rollbackEx.printStackTrace();
            }
        }
        return false;
    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Restaurar el estado de auto-commit
                conn.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException closeEx) {
                System.err.println("Error al cerrar la conexión: " + closeEx.getMessage());
                closeEx.printStackTrace();
            }
        }
    }
}

    
    
    // Método para obtener el ID de la categoría a partir del nombre de la categoría
public String obtenerIdCategoriaPorNombre(String nombreCategoria) {
    String query = "SELECT idCategoria FROM categoria WHERE nombreCategoria = ?";
    try (Connection conn = cn.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, nombreCategoria);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("idCategoria");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el ID de la categoría: " + e.getMessage());
        e.printStackTrace();
    }
    return null; // Retorna null si no se encuentra el ID o si ocurre un error
}

// Método para obtener el ID del proveedor a partir del nombre del proveedor
public String obtenerIdProveedorPorNombre(String nombreProveedor) {
    String query = "SELECT idProveedores FROM proveedores WHERE nombreEmpresa = ?";
    try (Connection conn = cn.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, nombreProveedor);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("idProveedores");
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener el ID del proveedor: " + e.getMessage());
        e.printStackTrace();
    }
    return null; // Retorna null si no se encuentra el ID o si ocurre un error
}





    // Método para obtener la lista de proveedores
    public List<String> obtenerProveedores() {
        List<String> proveedores = new ArrayList<>();
        String query = "SELECT nombreEmpresa FROM proveedores";
        try (Connection conn = cn.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                proveedores.add(rs.getString("nombreEmpresa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    





//*****************************************************************************/
   
    // Métodos para obtener el último ID de categoría
public String obtenerUltimoIdCategoria() {
    String query = "SELECT idCategoria FROM categoria ORDER BY idCategoria DESC LIMIT 1";
    try (Connection conn = cn.getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getString("idCategoria");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}



public boolean existeIdProducto(String idProducto) {
        String query = "SELECT COUNT(*) FROM productos WHERE idProductos = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, idProducto);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existeNombreProducto(String nombreProducto) {
        String query = "SELECT COUNT(*) FROM productos WHERE nombreProducto = ?";
        try (Connection conn = cn.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, nombreProducto);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    
}
