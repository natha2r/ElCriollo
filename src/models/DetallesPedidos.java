package models;

/**
 *
 * @author User
 */
public class DetallesPedidos {
    private String pedidosId;
    private String platosId; // Cambiado de productosId a platosId
    private int cantidad;
    private Double precioUnitario;
    private String domicilioId; 

    public DetallesPedidos() {
    }

    public DetallesPedidos(String pedidosId, String platosId, int cantidad, Double precioUnitario, String domicilioId) {
        this.pedidosId = pedidosId;
        this.platosId = platosId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.domicilioId = domicilioId; // Inicializando domicilioId
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

    public String getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(String domicilioId) {
        this.domicilioId = domicilioId;
    }
}
