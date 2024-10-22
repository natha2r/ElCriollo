package models;

public class Productos {
    private String idProductos;
    private String nombreProducto;
    private String descripcionProducto;
    private Double precio;
    private String categoria;
    private String stock; // Nuevo campo
    private String nombreEmpresa; // Nuevo campo

    // Constructor sin parámetros
    public Productos() {
    }
    
    

    // Constructor con todos los parámetros
    public Productos(String idProductos, String nombreProducto, String descripcionProducto, Double precio, String categoria, String stock, String proveedor) {
        this.idProductos = idProductos;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.nombreEmpresa = proveedor;
    }

    // Getters y setters para los campos existentes
    public String getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(String idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getters y setters para los nuevos campos
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getProveedor() {
        return nombreEmpresa;
    }

    public void setProveedor(String proveedor) {
        this.nombreEmpresa = proveedor;
    }
}
