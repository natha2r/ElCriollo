package models;

public class Platos {
    private String idPlatos;
    private String nombrePlato;
    private Double precio;
    private String categoriaPlatosId;

    public Platos() {
    }

    public Platos(String idPlatos, String nombrePlato, Double precio, String categoriaPlatosId) {
        this.idPlatos = idPlatos;
        this.nombrePlato = nombrePlato;
        this.precio = precio;
        this.categoriaPlatosId = categoriaPlatosId;
    }

    public String getIdPlatos() {
        return idPlatos;
    }

    public void setIdPlatos(String idPlatos) {
        this.idPlatos = idPlatos;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoriaPlatosId() {
        return categoriaPlatosId;
    }

    public void setCategoriaPlatosId(String categoriaPlatosId) {
        this.categoriaPlatosId = categoriaPlatosId;
    }
    
    
}
