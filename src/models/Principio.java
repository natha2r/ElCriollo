
package models;

public class Principio {

    private int idPrincipio;
    private String nombre;
    private int idTipoPrincipio;

    public Principio() {
    }

    public Principio(int idPrincipio, String nombre, int idTipoPrincipio) {
        this.idPrincipio = idPrincipio;
        this.nombre = nombre;
        this.idTipoPrincipio = idTipoPrincipio;
    }    
    
    public int getIdPrincipio() {
        return idPrincipio;
    }

    public void setIdPrincipio(int idPrincipio) {
        this.idPrincipio = idPrincipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTipoPrincipio() {
        return idTipoPrincipio;
    }

    public void setIdTipoPrincipio(int idTipoPrincipio) {
        this.idTipoPrincipio = idTipoPrincipio;
    }

}
