/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigDecimal;

/**
 *
 * @author User
 */
public class DetallesPedidos {

    private String pedidosId;
    private String platosId;
    private int cantidad;
    private BigDecimal precioUnitario;
    private String principio;
    private String comentario;

    public DetallesPedidos() {
    }

    public DetallesPedidos(String pedidosId, String platosId, int cantidad, BigDecimal precioUnitario, String principio, String comentario) {
        this.pedidosId = pedidosId;
        this.platosId = platosId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.principio = principio;
        this.comentario = comentario;
    }

    public String getPedidosId() {
        return pedidosId;
    }

    public void setPedidosId(String pedidosId) {
        this.pedidosId = pedidosId;
    }

    public String getPlatosId() {
        return platosId;
    }

    public void setPlatosId(String platosId) {
        this.platosId = platosId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getPrincipio() {
        return principio;
    }

    public void setPrincipio(String principio) {
        this.principio = principio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setPrecioUnitario(double precio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}