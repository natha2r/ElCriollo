package models;
import com.mysql.cj.xdevapi.Statement;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

public class EmployeesDao {
    
    // Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    // Variables para enviar datos entre interfaces
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
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return employee;
    }
    
    // Método para obtener meseras
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

    
    
    
    
    
    
    
    
    public boolean guardarEmpleadoYSesion(Employees employee, String usuario, String contraseña) {
        Connection connection = null;
        PreparedStatement stmtEmpleado = null;
        PreparedStatement stmtSesion = null;
        boolean resultado = false;

        try {
            // Abre la conexión
            connection = cn.getConnection();
            connection.setAutoCommit(false); // Manejo de transacciones manual

            // 1. Inserta en sesiones
            String sqlSesion = "INSERT INTO sesiones (usuario, fechaInicio, fechaFin, contraseña) VALUES (?, ?, ?, ?)";
            stmtSesion = connection.prepareStatement(sqlSesion);
            stmtSesion.setString(1, usuario);
            stmtSesion.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis())); // fechaInicio
            stmtSesion.setTimestamp(3, null); // fechaFin, puede ser nulo
            stmtSesion.setString(4, contraseña);

            // Ejecuta la inserción en sesiones
            int filasAfectadasSesion = stmtSesion.executeUpdate();

            // Verifica si la inserción en sesiones fue exitosa
            if (filasAfectadasSesion > 0) {
                // 2. Inserta el nuevo empleado usando el mismo usuario
                String sqlEmpleado = "INSERT INTO empleados (idEmpleados, nombreEmpleado, rol, telefono, direccion, email, edad, usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                stmtEmpleado = connection.prepareStatement(sqlEmpleado);
                stmtEmpleado.setString(1, employee.getIdEmpleados());
                stmtEmpleado.setString(2, employee.getNombreEmpleado());
                stmtEmpleado.setString(3, employee.getRol());
                stmtEmpleado.setString(4, employee.getTelefono());
                stmtEmpleado.setString(5, employee.getDireccion());
                stmtEmpleado.setString(6, employee.getEmail());
                stmtEmpleado.setInt(7, employee.getEdad());
                stmtEmpleado.setString(8, usuario); // Usa el mismo usuario que se ha creado

                // Ejecuta la inserción del empleado
                int filasAfectadasEmpleado = stmtEmpleado.executeUpdate();

                if (filasAfectadasEmpleado > 0) {
                    connection.commit(); // Confirma la transacción si ambas inserciones son exitosas
                    resultado = true; // Operación exitosa
                } else {
                    connection.rollback(); // Revierte si falla la inserción del empleado
                }
            } else {
                connection.rollback(); // Revierte si falla la inserción en sesiones
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Revierte en caso de error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Cierra recursos
            try {
                if (stmtEmpleado != null) {
                    stmtEmpleado.close();
                }
                if (stmtSesion != null) {
                    stmtSesion.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
        return resultado; // Devuelve el resultado
    }

    /*METODO PARA VERIFICAR SI EL ID O USUARIO ESTSAN EN LA BASE DE DATOS*/
    public boolean existeIdOUsuario(String idEmpleados, String usuario) {
        Connection connection = null;
        PreparedStatement stmtCheckId = null;
        PreparedStatement stmtCheckUsuario = null;
        ResultSet rsId = null;
        ResultSet rsUsuario = null;
        boolean existe = false;

        try {
            // Abre la conexión
            connection = cn.getConnection();

            // Verificar si el ID del empleado ya existe
            String sqlCheckId = "SELECT idEmpleados FROM empleados WHERE idEmpleados = ?";
            stmtCheckId = connection.prepareStatement(sqlCheckId);
            stmtCheckId.setString(1, idEmpleados);
            rsId = stmtCheckId.executeQuery();

            if (rsId.next()) {
                existe = true; // El ID ya existe
            }

            // Verificar si el usuario ya existe
            if (!existe) { // Solo verifica el usuario si el ID no existe
                String sqlCheckUsuario = "SELECT usuario FROM sesiones WHERE usuario = ?";
                stmtCheckUsuario = connection.prepareStatement(sqlCheckUsuario);
                stmtCheckUsuario.setString(1, usuario);
                rsUsuario = stmtCheckUsuario.executeQuery();

                if (rsUsuario.next()) {
                    existe = true; // El usuario ya existe
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cierra recursos
            try {
                if (rsId != null) {
                    rsId.close();
                }
                if (rsUsuario != null) {
                    rsUsuario.close();
                }
                if (stmtCheckId != null) {
                    stmtCheckId.close();
                }
                if (stmtCheckUsuario != null) {
                    stmtCheckUsuario.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
        return existe;
    }

    public ObservableList<String> obtenerRoles() {
        ObservableList<String> roles = FXCollections.observableArrayList();
        String query = "SELECT DISTINCT nombre FROM roles"; // Ajusta según tu estructura

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                roles.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    
    public boolean modificarEmpleadoYSesion(Employees empleado, String usuarioNuevo, String contrasena) {
    Connection connection = null;
    PreparedStatement pstmtEmpleado = null;
    PreparedStatement pstmtSesion = null;

    try {
        connection = cn.getConnection();
        connection.setAutoCommit(false); // Inicia la transacción

        // Primero, actualiza la tabla 'sesiones'
        pstmtSesion = connection.prepareStatement("UPDATE sesiones SET contraseña = ? WHERE usuario = ?");
        pstmtSesion.setString(1, contrasena);
        pstmtSesion.setString(2, usuarioNuevo); // Cambia esto si estás usando el usuario antiguo como referencia

        int filasActualizadas = pstmtSesion.executeUpdate();

        // Si el usuario no existe, deberías insertarlo
        if (filasActualizadas == 0) {
            pstmtSesion = connection.prepareStatement("INSERT INTO sesiones (usuario, contraseña) VALUES (?, ?)");
            pstmtSesion.setString(1, usuarioNuevo);
            pstmtSesion.setString(2, contrasena);
            pstmtSesion.executeUpdate();
        }

        // Luego, actualiza la tabla 'empleados'
        pstmtEmpleado = connection.prepareStatement("UPDATE empleados SET nombreEmpleado = ?, edad = ?, direccion = ?, telefono = ?, email = ?, rol = ?, usuario = ? WHERE idEmpleados = ?");
        pstmtEmpleado.setString(1, empleado.getNombreEmpleado());
        pstmtEmpleado.setInt(2, empleado.getEdad());
        pstmtEmpleado.setString(3, empleado.getDireccion());
        pstmtEmpleado.setString(4, empleado.getTelefono());
        pstmtEmpleado.setString(5, empleado.getEmail());
        pstmtEmpleado.setString(6, empleado.getRol());
        pstmtEmpleado.setString(7, usuarioNuevo); // Asegúrate de que el usuario nuevo esté aquí
        pstmtEmpleado.setString(8, empleado.getIdEmpleados());

        pstmtEmpleado.executeUpdate();

        connection.commit(); // Si todo está bien, commit
        return true;
    } catch (SQLException e) {
        if (connection != null) {
            try {
                connection.rollback(); // Rollback en caso de error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
        return false;
    } finally {
        // Cierra recursos
        try {
            if (pstmtEmpleado != null) pstmtEmpleado.close();
            if (pstmtSesion != null) pstmtSesion.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



    


    public boolean inactivarEmpleadoYSesion(String idEmpleado, String usuario) {
    boolean inactivado = false;
    Connection connection = null;
    PreparedStatement pstmtEmpleado = null;
    PreparedStatement pstmtSesion = null;
    PreparedStatement pstmtPedidos = null;
    PreparedStatement pstmtReportes = null;
    PreparedStatement pstmtHistorialVentas = null;

    try {
        connection = cn.getConnection();
        connection.setAutoCommit(false); // Inicia la transacción
        
        // Inactivar el empleado en la tabla empleados
        String sqlInactivarEmpleado = "UPDATE empleados SET activo = 0 WHERE idEmpleados = ?";
        pstmtEmpleado = connection.prepareStatement(sqlInactivarEmpleado);
        pstmtEmpleado.setString(1, idEmpleado);
        pstmtEmpleado.executeUpdate();

        // Inactivar la sesión en la tabla sesiones
        String sqlInactivarSesion = "UPDATE sesiones SET activo = 0 WHERE usuario = ?";
        pstmtSesion = connection.prepareStatement(sqlInactivarSesion);
        pstmtSesion.setString(1, usuario);
        pstmtSesion.executeUpdate();

        // Inactivar los registros relacionados en pedidos
        String sqlInactivarPedidos = "UPDATE pedidos SET empleadoInactivo = 1 WHERE empleadosId = ?";
        pstmtPedidos = connection.prepareStatement(sqlInactivarPedidos);
        pstmtPedidos.setString(1, idEmpleado);
        pstmtPedidos.executeUpdate();

        // Inactivar los registros en reportes
        String sqlInactivarReportes = "UPDATE reportes SET empleadoInactivo = 1 WHERE empleadosId = ?";
        pstmtReportes = connection.prepareStatement(sqlInactivarReportes);
        pstmtReportes.setString(1, idEmpleado);
        pstmtReportes.executeUpdate();

        // Inactivar los registros en historialVentas
        String sqlInactivarHistorialVentas = "UPDATE historialVentas SET empleadoInactivo = 1 WHERE empleadosId = ?";
        pstmtHistorialVentas = connection.prepareStatement(sqlInactivarHistorialVentas);
        pstmtHistorialVentas.setString(1, idEmpleado);
        pstmtHistorialVentas.executeUpdate();

        connection.commit(); // Confirmar las transacciones
        inactivado = true;
    } catch (SQLException e) {
        if (connection != null) {
            try {
                connection.rollback(); // Hacer rollback en caso de error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        try {
            if (pstmtEmpleado != null) pstmtEmpleado.close();
            if (pstmtSesion != null) pstmtSesion.close();
            if (pstmtPedidos != null) pstmtPedidos.close();
            if (pstmtReportes != null) pstmtReportes.close();
            if (pstmtHistorialVentas != null) pstmtHistorialVentas.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return inactivado;
}






    public List<Employees> obtenerTodosLosEmpleados() {
    List<Employees> empleadosList = new ArrayList<>();
    try (Connection connection = cn.getConnection(); 
         PreparedStatement pstmt = connection.prepareStatement(
            "SELECT e.idEmpleados, e.nombreEmpleado, e.edad, e.direccion, e.telefono, e.email, e.rol, "
            + "e.usuario, s.contraseña "
            + "FROM empleados e "
            + "LEFT JOIN sesiones s ON e.usuario = s.usuario "
            + "WHERE e.activo = 1")) {  // Filtra solo los empleados activos

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Employees empleado = new Employees();
            empleado.setIdEmpleados(rs.getString("idEmpleados"));
            empleado.setNombreEmpleado(rs.getString("nombreEmpleado"));
            empleado.setEdad(rs.getInt("edad"));
            empleado.setDireccion(rs.getString("direccion"));
            empleado.setTelefono(rs.getString("telefono"));
            empleado.setEmail(rs.getString("email"));
            empleado.setRol(rs.getString("rol"));

            Sesiones sesion = new Sesiones();
            sesion.setUsuario(rs.getString("usuario"));
            sesion.setContraseña(rs.getString("contraseña"));

            empleado.setSesion(sesion);
            empleadosList.add(empleado);
        }

    } catch (SQLException e) {
        // Aquí podrías lanzar una excepción personalizada
        e.printStackTrace();
    }
    return empleadosList;
}


}
