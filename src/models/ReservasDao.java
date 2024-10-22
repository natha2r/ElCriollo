package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservasDao {

    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //metodo guardar reservas
    public boolean guardarReserva(Reservas reservas) {
        String query = "INSERT INTO reservas (nombreCliente, numeroPersonas, fecha, nota, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, reservas.getNombreCliente());
            pst.setInt(2, reservas.getNumeroPersonas());
            pst.setDate(3, (Date) reservas.getFecha());
            pst.setString(4, reservas.getNota());
            pst.setString(5, reservas.getTelefono());

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar una reserva en la base de datos
    public boolean actualizarReserva(Reservas reservas) {
        String query = "UPDATE reservas SET nombreCliente = ?, fecha = ?, numeroPersonas = ?, telefono = ?, nota = ? WHERE idReservas = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, reservas.getNombreCliente());
            pst.setDate(2, new java.sql.Date(reservas.getFecha().getTime()));
            pst.setInt(3, reservas.getNumeroPersonas());
            pst.setString(4, reservas.getTelefono());
            pst.setString(5, reservas.getNota());
            pst.setString(6, reservas.getIdReservas());

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //METODO UPDATE RESERVAS
    public List<Reservas> obtenerTodasLasReservas() {
        List<Reservas> reservas = new ArrayList<>();
        String query = "SELECT * FROM reservas";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Reservas reserva = new Reservas();
                reserva.setIdReservas(rs.getString("idReservas"));
                reserva.setNombreCliente(rs.getString("nombreCliente"));
                reserva.setNumeroPersonas(rs.getInt("numeroPersonas"));
                reserva.setFecha(rs.getDate("fecha"));
                reserva.setTelefono(rs.getString("telefono"));
                reserva.setNota(rs.getString("nota"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
    
    // Método para obtener una reserva por su ID
    public Reservas obtenerReservaPorId(String idReservas) {
        Reservas reserva = null; // La reserva que vamos a devolver

        String query = "SELECT * FROM reservas WHERE idReservas = ?"; // La consulta mágica

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, idReservas); // Configurar el ID en la consulta
            ResultSet rs = pst.executeQuery();

            // Si encuentra la reserva, entonces hay un resultado
            if (rs.next()) {
                // Crear la reserva con los datos recuperados
                reserva = new Reservas();
                reserva.setIdReservas(rs.getString("idReservas"));
                reserva.setNombreCliente(rs.getString("nombreCliente"));
                reserva.setFecha(rs.getDate("fecha"));
                reserva.setNumeroPersonas(rs.getInt("numeroPersonas"));
                reserva.setTelefono(rs.getString("telefono"));
                reserva.setNota(rs.getString("nota"));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Manejar los errores
        }

        // Devolver la reserva encontrada (o null si no hay ninguna)
        return reserva;
    }

    //Metodo de eliminar reserva
    public boolean eliminarReserva(String idReservas) {
        String query = "DELETE FROM reservas WHERE idReservas = ?";

        // Obtener la conexión a la base de datos
        try (Connection connection = cn.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            // Establecer el valor del parámetro (ID de la reserva) en la consulta
            statement.setString(1, idReservas);

            // Ejecutar la consulta de eliminación
            int rowsAffected = statement.executeUpdate();

            // Retornar true si al menos una fila fue afectada (significa que se eliminó la reserva)
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // En caso de error, retornar false
        }
    }

}
