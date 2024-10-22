/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author User
 */
public class Carta {
    private String platosId;
    private String nombrePlato;
    private String descripcion;
    private Double precio;

    public Carta() {
    }

    public Carta(String platosId, String nombrePlato, String descripcion, Double precio) {
        this.platosId = platosId;
        this.nombrePlato = nombrePlato;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getPlatosId() {
        return platosId;
    }

    public void setPlatosId(String platosId) {
        this.platosId = platosId;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    
}
