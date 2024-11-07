
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class InventarioDao {
    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    
  public String generarNuevoIdInventario() {
    String nuevoIdInventario = "inv001"; // ID inicial en caso de que no existan registros
    String consulta = "SELECT idInventario FROM inventario ORDER BY idInventario DESC";

    try (Connection conn = cn.getConnection();
         PreparedStatement ps = conn.prepareStatement(consulta);
         ResultSet rs = ps.executeQuery()) {

        // Iterar sobre los resultados hasta encontrar un ID que cumpla con el formato esperado
        while (rs.next()) {
            String idInventarioActual = rs.getString("idInventario");

            // Comprobar si el ID tiene el formato correcto "inv###"
            if (idInventarioActual.startsWith("inv")) {
                try {
                    int numero = Integer.parseInt(idInventarioActual.substring(3)); // Extraer el número después de "inv"
                    numero++; // Incrementar el número
                    nuevoIdInventario = String.format("inv%03d", numero); // Generar el nuevo ID con el formato inv###
                    break; // Salir del bucle cuando se encuentre un ID válido
                } catch (NumberFormatException e) {
                    // Si no es un número, continuar con el siguiente registro
                    System.err.println("Error al convertir el número en el ID de inventario: " + e.getMessage());
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return nuevoIdInventario;
}


    
    
}
