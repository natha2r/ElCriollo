
package models;

public class Platos {
    private String idPlatos;
    private String nombrePlato;
    private int precio;
    private String categoriaPlatosId;

    public Platos() {
    }

    public Platos(String idPlatos, String nombrePlato, int precio, String categoriaPlatosId) {
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCategoriaPlatosId() {
        return categoriaPlatosId;
    }

    public void setCategoriaPlatosId(String categoriaPlatosId) {
        this.categoriaPlatosId = categoriaPlatosId;
    }
    
    
}
