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
public class Domicilios {
    private String idDomicilios;
    private String nombreCliente;
    private String direccion;
    private int cantidad;
    private String platosId;
    private String platos;
    private Double precioUnitario;
    private Double precioTotal;
    private Date fecha;
    private String observacion;

    public Domicilios() {
    }

    public Domicilios(String idDomicilios, String nombreCliente, String direccion, int cantidad, String platosId, String platos, Double precioUnitario, Double precioTotal, Date fecha, String observacion) {
        this.idDomicilios = idDomicilios;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.cantidad = cantidad;
        this.platosId = platosId;
        this.platos = platos;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
        this.observacion = observacion;
    }

    public String getIdDomicilios() {
        return idDomicilios;
    }

    public void setIdDomicilios(String idDomicilios) {
        this.idDomicilios = idDomicilios;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPlatosId() {
        return platosId;
    }

    public void setPlatosId(String platosId) {
        this.platosId = platosId;
    }

    public String getPlatos() {
        return platos;
    }

    public void setPlatos(String platos) {
        this.platos = platos;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
}
