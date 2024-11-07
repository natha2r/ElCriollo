/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controllers.Inicio_CocinaController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDao {

    public static List<Inicio_CocinaController.domicilios> getAllPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Connection conn;

    public DomicilioDao(Connection conn) {
        this.conn = conn;
    }

    public List<Domicilios> getAllDomicilios() {
        List<Domicilios> domicilios = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM domicilios")) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Domicilios domicilio = new Domicilios();
                domicilio.setIdDomicilios(rs.getString("idDomicilios"));
                domicilio.setNombreCliente(rs.getString("nombreCliente"));
                domicilio.setDireccion(rs.getString("direccion"));
                domicilio.setCantidad(rs.getInt("cantidad"));
                domicilio.setPlatosId(rs.getString("platosId"));
                domicilio.setPlatos(rs.getString("platos"));
                domicilio.setPrecioUnitario(rs.getDouble("precioUnitario"));
                domicilio.setPrecioTotal(rs.getDouble("precioTotal"));
                domicilio.setFecha(rs.getDate("fecha"));
                domicilio.setObservacion(rs.getString("observacion"));
                domicilios.add(domicilio);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los domicilios: " + e.getMessage());
        }
        return domicilios;
    }

    public Domicilios getDomicilioById(String idDomicilios) {
        Domicilios domicilio = null;
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM domicilios WHERE idDomicilios = ?")) {
            pstmt.setString(1, idDomicilios);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                domicilio = new Domicilios();
                domicilio.setIdDomicilios(rs.getString("idDomicilios"));
                domicilio.setNombreCliente(rs.getString("nombreCliente"));
                domicilio.setDireccion(rs.getString("direccion"));
                domicilio.setCantidad(rs.getInt("cantidad"));
                domicilio.setPlatosId(rs.getString("platosId"));
                domicilio.setPlatos(rs.getString("platos"));
                domicilio.setPrecioUnitario(rs.getDouble("precioUnitario"));
                domicilio.setPrecioTotal(rs.getDouble("precioTotal"));
                domicilio.setFecha(rs.getDate("fecha"));
                domicilio.setObservacion(rs.getString("observacion"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el domicilio por ID: " + e.getMessage());
        }
        return domicilio;
    }

    public void createDomicilio(Domicilios domicilio) {
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO domicilios (idDomicilios, nombreCliente, direccion, cantidad, platosId, platos, precioUnitario, precioTotal, fecha, observacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, domicilio.getIdDomicilios());
            pstmt.setString(2, domicilio.getNombreCliente());
            pstmt.setString(3, domicilio.getDireccion());
            pstmt.setInt(4, domicilio.getCantidad());
            pstmt.setString(5, domicilio.getPlatosId());
            pstmt.setString(6, domicilio.getPlatos());
            pstmt.setDouble(7, domicilio.getPrecioUnitario());
            pstmt.setDouble(8, domicilio.getPrecioTotal());
            pstmt.setDate(9, (Date) domicilio.getFecha());
            pstmt.setString(10, domicilio.getObservacion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear el domicilio: " + e.getMessage());
        }
    }
    
    public void updateDomicilio(Domicilios domicilio) {
    try (PreparedStatement pstmt = conn.prepareStatement("UPDATE domicilios SET nombreCliente = ?, direccion = ?, cantidad = ?, platosId = ?, platos = ?, precioUnitario = ?, precioTotal = ?, fecha = ?, observacion = ? WHERE idDomicilios = ?")) {
        pstmt.setString(1, domicilio.getNombreCliente());
        pstmt.setString(2, domicilio.getDireccion());
        pstmt.setInt(3, domicilio.getCantidad());
        pstmt.setString(4, domicilio.getPlatosId());
        pstmt.setString(5, domicilio.getPlatos());
        pstmt.setDouble(6, domicilio.getPrecioUnitario());
        pstmt.setDouble(7, domicilio.getPrecioTotal());
        pstmt.setDate(8, (Date) domicilio.getFecha());
        pstmt.setString(9, domicilio.getObservacion());
        pstmt.setString(10, domicilio.getIdDomicilios());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Error al actualizar el domicilio: " + e.getMessage());
    }
}

    public void deleteDomicilio(String idDomicilios) {
        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM domicilios WHERE idDomicilios = ?")) {
            pstmt.setString(1, idDomicilios);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el domicilio: " + e.getMessage());
        }
    }
}
