/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author User
 */
public class Inventario {
    private String idInventario;
    private String productosId;
    private String stock;
    private Date fechaRecepcion;
    private Double precioCompraUnitario;
    private String proveedorId;

    public Inventario() {
    }

    public Inventario(String idInventario, String productosId, String stock, Date fechaRecepcion, Double precioCompraUnitario, String proveedorId) {
        this.idInventario = idInventario;
        this.productosId = productosId;
        this.stock = stock;
        this.fechaRecepcion = fechaRecepcion;
        this.precioCompraUnitario = precioCompraUnitario;
        this.proveedorId = proveedorId;
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public String getProductosId() {
        return productosId;
    }

    public void setProductosId(String productosId) {
        this.productosId = productosId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Double getPrecioCompraUnitario() {
        return precioCompraUnitario;
    }

    public void setPrecioCompraUnitario(Double precioCompraUnitario) {
        this.precioCompraUnitario = precioCompraUnitario;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }
    
    
}
