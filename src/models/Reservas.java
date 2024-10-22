/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author User
 */
public class Reservas {
    private String idReservas;
    private String nombreCliente;
    private Date fecha;
    private int numeroPersonas;
    private String telefono;
    private String nota;

    public Reservas() {
    }

    public Reservas(String idReservas, String nombreCliente, Date fecha, int numeroPersonas, String telefono, String nota) {
        this.idReservas = idReservas;
        this.nombreCliente = nombreCliente;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
        this.telefono = telefono;
        this.nota = nota;
    }

    public String getIdReservas() {
        return idReservas;
    }

    public void setIdReservas(String idReservas) {
        this.idReservas = idReservas;
    }


    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    
}
