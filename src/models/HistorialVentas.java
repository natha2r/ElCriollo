package models;

import java.util.Date;

public class HistorialVentas {
    private String idHistorialVentas;
    private Date fechaVenta;
    private float totalVenta;
    private String empleadosId;
    private String mesasId;
    private String productosId;
    private int cantidadProductos;
    private float precioUnitarioProducto;
    private float precioTotal;

    public HistorialVentas() {
    }

    public HistorialVentas(String idHistorialVentas, Date fechaVenta, float totalVenta, String empleadosId, String mesasId, String productosId, int cantidadProductos, float precioUnitarioProducto, float precioTotal) {
        this.idHistorialVentas = idHistorialVentas;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.empleadosId = empleadosId;
        this.mesasId = mesasId;
        this.productosId = productosId;
        this.cantidadProductos = cantidadProductos;
        this.precioUnitarioProducto = precioUnitarioProducto;
        this.precioTotal = precioTotal;
    }

    public String getIdHistorialVentas() {
        return idHistorialVentas;
    }

    public void setIdHistorialVentas(String idHistorialVentas) {
        this.idHistorialVentas = idHistorialVentas;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public float getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(float totalVenta) {
        this.totalVenta = totalVenta;
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

    public String getProductosId() {
        return productosId;
    }

    public void setProductosId(String productosId) {
        this.productosId = productosId;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public float getPrecioUnitarioProducto() {
        return precioUnitarioProducto;
    }

    public void setPrecioUnitarioProducto(float precioUnitarioProducto) {
        this.precioUnitarioProducto = precioUnitarioProducto;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}

