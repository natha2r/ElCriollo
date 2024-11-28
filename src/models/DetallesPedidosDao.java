package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetallesPedidosDao {

    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn = cn.getConnection();
    PreparedStatement pst;
    ResultSet rs;

    public boolean guardarDetallePedido(DetallesPedidos detalle) {
    String query = "INSERT INTO detallespedido (pedidosId, platosId, cantidad, precioUnitario, principio, comentario) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {

        // Asignar valores a los parámetros
        stmt.setString(1, detalle.getPedidosId());
        stmt.setString(2, detalle.getPlatosId());
        stmt.setInt(3, detalle.getCantidad());
        stmt.setBigDecimal(4, detalle.getPrecioUnitario());
        stmt.setString(5, detalle.getPrincipio());
        stmt.setString(6, detalle.getComentario());

        // Ejecutar la sentencia de actualización (INSERT)
        int filasAfectadas = stmt.executeUpdate(); // Usar executeUpdate en lugar de executeQuery

        // Retorna true si al menos una fila fue afectada
        return filasAfectadas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}
