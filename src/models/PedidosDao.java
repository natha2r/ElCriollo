package models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PedidosDao {

    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn = cn.getConnection();
    PreparedStatement pst;
    ResultSet rs;

    public PedidosDao(com.sun.jdi.connect.spi.Connection conn) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Obtener información básica del pedido
    public Pedidos getPedido(String idPedido) throws SQLException {
        Pedidos pedido = null;
        String query = "SELECT p.idPedidos AS numeroOrden, p.fechaPedido, p.estadoPedido, p.precioTotal, "
                + "e.nombreEmpleado AS mesera, m.numeroMesa "
                + "FROM pedidos p "
                + "JOIN empleados e ON p.empleadosId = e.idEmpleados "
                + "JOIN mesas m ON p.mesasId = m.idMesas "
                + "WHERE p.idPedidos = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new Pedidos();
                    pedido.setIdPedidos(rs.getString("numeroOrden"));
                    pedido.setFechaPedido(rs.getDate("fechaPedido"));
                    pedido.setEstadoPedido(rs.getString("estadoPedido"));
                    pedido.setPrecioTotal(rs.getDouble("precioTotal"));
                    pedido.setEmpleadosId(rs.getString("empleadosId"));
                    pedido.setMesasId(rs.getString("mesasId"));
                    // Seteamos los valores adicionales que se usan en el controlador
                    // Aquí deberías considerar crear una clase o estructura para la información completa si se necesita
                }
            }
        }
        return pedido;
    }

    

    // Obtener detalles del pedido
    public List<DetallesPedidos> getDetallesPedido(String idPedido) throws SQLException {
        List<DetallesPedidos> detalles = new ArrayList<>();
        String query = "SELECT p.nombrePlato, d.cantidad, d.precioUnitario "
                + "FROM detallesPedido d "
                + "JOIN platos p ON d.platosId = p.idPlatos "
                + "WHERE d.pedidosId = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetallesPedidos detalle = new DetallesPedidos();
                    detalle.setPlatosId(rs.getString("nombrePlato"));
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precioUnitario"));
                    detalles.add(detalle);
                }
            }
        }
        return detalles;
    }

    // Obtener todos los pedidos
    public List<Pedidos> getAllPedidos() throws SQLException {
        List<Pedidos> pedidosList = new ArrayList<>();
        String query = "SELECT p.idPedidos, p.fechaPedido, p.estadoPedido, p.precioTotal, "
                + "e.nombreEmpleado AS nombreEmpleado, m.numeroMesa "
                + "FROM pedidos p "
                + "JOIN empleados e ON p.empleadosId = e.idEmpleados "
                + "JOIN mesas m ON p.mesasId = m.idMesas";

        try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedidos pedido = new Pedidos(
                        rs.getString("idPedidos"),
                        rs.getString("nombreEmpleado"), // Suponiendo que has modificado la clase Pedidos para incluir el nombre del empleado
                        rs.getString("numeroMesa"), // Suponiendo que has modificado la clase Pedidos para incluir el número de mesa
                        rs.getDate("fechaPedido"),
                        rs.getString("estadoPedido"),
                        rs.getDouble("precioTotal")
                );
                pedidosList.add(pedido);
            }
        }
        return pedidosList;
    }

    // Obtener todos los pedidos activos
    public List<Pedidos> getActivePedidos() throws SQLException {
        List<Pedidos> pedidosList = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE estadoPedido = 'En proceso'";

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pedidos pedido = new Pedidos(
                        rs.getString("idPedidos"),
                        rs.getString("empleadosId"),
                        rs.getString("mesasId"),
                        rs.getDate("fechaPedido"),
                        rs.getString("estadoPedido"),
                        rs.getDouble("precioTotal")
                );
                pedidosList.add(pedido);
            }
        }
        return pedidosList;
    }
    
    public void updatePedidoEstado(int pedidoId, String nuevoEstado) throws SQLException {
        String sql = "UPDATE pedidos SET estado = ? WHERE id_pedido = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevoEstado);
            pstmt.setInt(2, pedidoId);
            pstmt.executeUpdate();
        }
    }
    
    public List<Pedidos> obtenerPedidosPorDia(Date fecha) throws SQLException {
        List<Pedidos> pedidosList = new ArrayList<>();

        String query = "SELECT * FROM pedidos WHERE DATE(fecha_pedido) = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(fecha.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Pedidos pedido = new Pedidos();
                    pedido.setIdPedidos(resultSet.getString("idPedidos"));
                    pedido.setEmpleadosId(resultSet.getString("empleadosId"));
                    pedido.setMesasId(resultSet.getString("mesasId"));
                    pedido.setFechaPedido(resultSet.getTimestamp("fecha_pedido"));
                    pedido.setEstadoPedido(resultSet.getString("estadoPedido"));
                    pedido.setPrecioTotal(resultSet.getDouble("precioTotal"));

                    pedidosList.add(pedido);
                }
            }
        }

        return pedidosList;
    }
}
