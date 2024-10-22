package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class EmployeesDao {
    
    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    //int state = -1;

    //Variables para enviar datos entre interfaces
    public static String id_empleado = "";
    public static String nombre_empleado = "";
    public static String rol_empleado = "";
    public static String fechaContratacion_empleado = "";
    public static String salario_empleado = "";
    public static String telefono_empleado = "";
    public static String direccion_empleado = "";
    public static String email_empleado = "";
    public static String usuario_empleado = "";
    
    // Método del login
    public Employees loginQuery(String user, String password) {
        String query = "SELECT e.* FROM sesiones s JOIN empleados e ON s.usuario = e.usuario WHERE s.usuario = ? AND s.contraseña = ?";
        Employees employee = null;

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, user);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    employee = new Employees();
                    employee.setIdEmpleados(rs.getString("idEmpleados"));
                    employee.setNombreEmpleado(rs.getString("nombreEmpleado"));
                    employee.setUsuario(rs.getString("usuario"));
                    employee.setDireccion(rs.getString("direccion"));
                    employee.setTelefono(rs.getString("telefono"));
                    employee.setEmail(rs.getString("email"));
                    employee.setRol(rs.getString("rol"));
                }
            }
        } catch (SQLException e) {
            // Reemplaza con un mecanismo de log adecuado
            JOptionPane.showMessageDialog(null, "error." + e);
        }
        return employee;
    }
    
    public ObservableList<Employees> getMeseras() {
    ObservableList<Employees> meseras = FXCollections.observableArrayList();
    String query = "SELECT * FROM empleados WHERE rol = ?";
    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, "Camarero");
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Employees employee = new Employees();
                employee.setIdEmpleados(rs.getString("idEmpleados"));
                employee.setNombreEmpleado(rs.getString("nombreEmpleado"));
                meseras.add(employee);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return meseras;
}
}
