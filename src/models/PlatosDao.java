/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

public class PlatosDao {

    // Instanciar la conexión 
    //  Instanciar la conexión 
    ConnectionMySQL cn = new ConnectionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    // Obtener todos los productos
    public List<Platos> getAllPlatos() {
        List<Platos> platos = new ArrayList<>();
        String query = "SELECT * FROM platos";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Platos plato = new Platos();
                plato.setIdPlatos(rs.getString("idPlatos"));
                plato.setNombrePlato(rs.getString("nombrePlato"));
                platos.add(plato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return platos;
    }

    // Obtener productos por categoría
    public List<Platos> getPlatosByCategoria(String idCategoriaPlatos) {
        List<Platos> platos = new ArrayList<>();
        String query = "SELECT * FROM platos WHERE categoriaPlatosId = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, idCategoriaPlatos);
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Platos plato = new Platos();
                plato.setIdPlatos(rs.getString("idPlatos"));
                plato.setNombrePlato(rs.getString("nombrePlato")); // Asegúrate de que sea el nombre correcto
                plato.setCategoriaPlatosId(rs.getString("categoriaPlatosId"));
                platos.add(plato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        return platos;
    }

    // Marcar un producto como no disponible
    public void marcarComoNoDisponible(String idPlatos) {
        String query = "UPDATE platos SET disponible = ? WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setBoolean(1, false); // Cambiar a no disponible
            pst.setString(2, idPlatos);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    // Método para obtener los platos según la categoría seleccionada
    public ObservableList<String> getCategoriasByMenuM(String nombreMenu) {
        ObservableList<String> categorias = FXCollections.observableArrayList();
        String query = "SELECT cp.nombreCategoriaPlatos "
                + "FROM CategoriaPlatos cp "
                + "JOIN TipoMenu tm ON cp.idTipoMenu = tm.idTipoMenu "
                + "WHERE tm.nombreMenu = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nombreMenu);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                categorias.add(rs.getString("nombreCategoriaPlatos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;

    }

    public String obtenerIdPlatoPorNombre(String nombrePlato) {
    String query = "SELECT idPlatos FROM Platos WHERE nombrePlato = ?";
    try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, nombrePlato);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getString("idPlatos"); // Cambia a getString para obtener el ID como texto
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Devuelve null si no se encuentra el plato o hay un error
}


    // Método para obtener los platos según la categoría seleccionada
    public ObservableList<String> getPlatosByCategoriaM(String nombreCategoria) {
        ObservableList<String> platos = FXCollections.observableArrayList();

        try {
            conn = cn.getConnection();
            String query = "SELECT nombrePlato FROM platos p "
                    + "JOIN categoriaPlatos cp ON p.categoriaPlatosId = cp.idCategoriaPlatos "
                    + "WHERE cp.nombreCategoriaPlatos = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, nombreCategoria);
            rs = pst.executeQuery();

            while (rs.next()) {
                platos.add(rs.getString("nombrePlato"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return platos;
    }

    // ************************************************** 07/11/24 *******************************************************
    // Método para obtener el precio de un plato por su nombre
    public String obtenerPrecioPorNombre(String nombrePlato) {
        String precio = "";
        String query = "SELECT precio FROM platos WHERE nombrePlato = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nombrePlato);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                precio = rs.getString("precio");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }

        return precio;
    }

    // Traer nombre del plato, precio y tipo para menú del día
    public List<Platos> getPlatosByCategoriaC(String nombreCategoria) {
        List<Platos> platos = new ArrayList<>();
        String query = "SELECT p.idPlatos, p.nombrePlato, p.precio, p.esMini, p.categoriaPlatosId "
                + "FROM platos p "
                + "JOIN categoriaPlatos cp ON p.categoriaPlatosId = cp.idCategoriaPlatos "
                + "WHERE cp.nombreCategoriaPlatos = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nombreCategoria);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CheckBox miniCheckBox = new CheckBox();
                miniCheckBox.setSelected(rs.getBoolean("esMini"));

                Platos plato = new Platos(
                        rs.getString("idPlatos"),
                        rs.getString("nombrePlato"),
                        rs.getDouble("precio"),
                        rs.getString("categoriaPlatosId"),
                        miniCheckBox
                );
                platos.add(plato);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return platos;
    }

    public boolean actualizarEsMini(String idPlato, boolean esMini) {
        String query = "UPDATE platos SET esMini = ? WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setBoolean(1, esMini);
            pst.setString(2, idPlato);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0; // Devuelve true si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si ocurrió un error
        }
    }

    public boolean actualizarNombrePlato(String idPlato, String nuevoNombre) {
        String query = "UPDATE platos SET nombrePlato = ? WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nuevoNombre);
            pst.setString(2, idPlato);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarPrecioPlato(String idPlato, double nuevoPrecio) {
        String query = "UPDATE platos SET precio = ? WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setDouble(1, nuevoPrecio);
            pst.setString(2, idPlato);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*public boolean insertarPlato(Platos plato) {
        String query = "INSERT INTO platos (idPlatos, nombrePlato, precio, categoriaPlatosId, esMini) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, plato.getIdPlatos());
            pst.setString(2, plato.getNombrePlato());
            pst.setDouble(3, plato.getPrecio());
            pst.setString(4, plato.getCategoriaPlatosId());
            pst.setBoolean(5, plato.getMiniCheckBox().isSelected());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/
 /*public boolean eliminarPlato(String idPlato) {
        String query = "DELETE FROM platos WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, idPlato);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }*/
    public List<Platos> obtenerTodosLosPlatos() {
        List<Platos> platos = new ArrayList<>();
        String query = "SELECT idPlatos, nombrePlato, precio, esMini, categoriaPlatosId FROM platos";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                CheckBox miniCheckBox = new CheckBox();
                miniCheckBox.setSelected(rs.getBoolean("esMini"));

                Platos plato = new Platos(
                        rs.getString("idPlatos"),
                        rs.getString("nombrePlato"),
                        rs.getDouble("precio"),
                        rs.getString("categoriaPlatosId"),
                        miniCheckBox
                );
                platos.add(plato);

                // Listener para guardar cambios en el CheckBox
                miniCheckBox.setOnAction(e -> {
                    plato.setMiniCheckBox(miniCheckBox);
                    actualizarPlato(plato);
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return platos;
    }

    public boolean insertarPlato(Platos plato) {
        String query = "INSERT INTO platos (idPlatos, nombrePlato, precio, esMini, categoriaPlatosId) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, plato.getIdPlatos());
            pst.setString(2, plato.getNombrePlato());
            pst.setDouble(3, plato.getPrecio());
            pst.setBoolean(4, plato.getMiniCheckBox().isSelected());
            pst.setString(5, plato.getCategoriaPlatosId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void actualizarPlato(Platos plato) {
        String query = "UPDATE platos SET nombrePlato = ?, precio = ?, esMini = ? WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, plato.getNombrePlato());
            pst.setDouble(2, plato.getPrecio());
            pst.setBoolean(3, plato.getMiniCheckBox().isSelected());
            pst.setString(4, plato.getIdPlatos());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarPlato(String idPlato) {
        String query = "DELETE FROM platos WHERE idPlatos = ?";
        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, idPlato);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se eliminó al menos un registro
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Devuelve false si hubo algún problema
    }

    public String generarNuevoIdPlato() {
        String query = "SELECT idPlatos FROM platos ORDER BY idPlatos DESC LIMIT 1";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String ultimoId = rs.getString("idPlatos"); // Por ejemplo, "plato027"
                // Extraer el número y generar el siguiente ID
                int numero = Integer.parseInt(ultimoId.substring(5)); // Obtiene "027" como número
                String nuevoId = String.format("plato%03d", numero + 1); // Incrementa y formatea con ceros
                return nuevoId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "plato001"; // Si no hay registros, comienza desde "plato001"
    }

    public String obtenerIdCategoriaPorNombre(String nombreCategoria) {
        String idCategoria = null;
        String query = "SELECT idCategoriaPlatos FROM categoriaPlatos WHERE nombreCategoriaPlatos = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, nombreCategoria);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idCategoria = rs.getString("idCategoriaPlatos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idCategoria;
    }

    public boolean esMiniPorNombre(String nombrePlato) {
        boolean esMini = false; // Valor predeterminado (si no se encuentra el plato, asumimos que no es mini)
        String query = "SELECT esMini FROM platos WHERE nombrePlato = ?";

        try (Connection conn = cn.getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {

            // Establecer el parámetro para la consulta
            pst.setString(1, nombrePlato);

            // Ejecutar la consulta
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    // Obtener el valor de la columna esMini
                    esMini = rs.getBoolean("esMini");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo adecuado de excepciones (puedes lanzar una excepción o loguear el error)
        }

        return esMini; // Retorna si el plato es Mini o no
    }

}
