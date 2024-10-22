package models;

public class Mesas {
    private String idMesas;
    private int numeroMesa;
    private int capacidad;
    private String estado;

    public Mesas() {}

    public Mesas(String idMesas, int numeroMesa, int capacidad, String estado) {
        this.idMesas = idMesas;
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public String getIdMesas() {
        return idMesas;
    }

    public void setIdMesas(String idMesas) {
        this.idMesas = idMesas;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

