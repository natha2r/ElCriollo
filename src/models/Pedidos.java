package models;

import java.util.Date;

public class Pedidos {
    private String idPedidos;
    private String empleadosId;
    private String mesasId;
    private Date fechaPedido;
    private String estadoPedido;
    private Double precioTotal;

    public Pedidos() {
    }

    public Pedidos(String idPedidos, String empleadosId, String mesasId, Date fechaPedido, String estadoPedido, Double precioTotal) {
        this.idPedidos = idPedidos;
        this.empleadosId = empleadosId;
        this.mesasId = mesasId;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.precioTotal = precioTotal;
    }

    public String getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(String idPedidos) {
        this.idPedidos = idPedidos;
    }

    public String getEmpleadosId() {
        return empleadosId;
    }

    public void setEmpleadosId(String empleadosId) {
        this.empleadosId = empleadosId;
    }

    public String getMesasId() {
        return mesasId;
    }

    public void setMesasId(String mesasId) {
        this.mesasId = mesasId;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
