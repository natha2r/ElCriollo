/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author User
 */
public class DetallesPedidos {
    private String pedidosId;
    private String platosId;
    private int cantidad;
    private Double precioUnitario;

    public DetallesPedidos() {
    }

    public DetallesPedidos(String pedidosId, String platosId, int cantidad, Double precioUnitario) {
        this.pedidosId = pedidosId;
        this.platosId = platosId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
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

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }   
    
}
