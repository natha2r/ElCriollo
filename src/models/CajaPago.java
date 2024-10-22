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
public class CajaPago {
    private String idCajaPago;
    private String tipoPago;
    private String pedidoId;
    private Date fechaPago;
    private Double montoPago;
    private String metodoPago;
    private String observaciones;

    public CajaPago() {
    }

    public CajaPago(String idCajaPago, String tipoPago, String pedidoId, Date fechaPago, Double montoPago, String metodoPago, String observaciones) {
        this.idCajaPago = idCajaPago;
        this.tipoPago = tipoPago;
        this.pedidoId = pedidoId;
        this.fechaPago = fechaPago;
        this.montoPago = montoPago;
        this.metodoPago = metodoPago;
        this.observaciones = observaciones;
    }

    public String getIdCajaPago() {
        return idCajaPago;
    }

    public void setIdCajaPago(String idCajaPago) {
        this.idCajaPago = idCajaPago;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Double montoPago) {
        this.montoPago = montoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
