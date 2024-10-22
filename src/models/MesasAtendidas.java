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
public class MesasAtendidas {
    private String idMesasAtendidas;
    private String empleadosId;
    private Date fecha;
    private int numeroMesasAtendidas;

    public MesasAtendidas() {
    }

    public MesasAtendidas(String idMesasAtendidas, String empleadosId, Date fecha, int numeroMesasAtendidas) {
        this.idMesasAtendidas = idMesasAtendidas;
        this.empleadosId = empleadosId;
        this.fecha = fecha;
        this.numeroMesasAtendidas = numeroMesasAtendidas;
    }

    public String getIdMesasAtendidas() {
        return idMesasAtendidas;
    }

    public void setIdMesasAtendidas(String idMesasAtendidas) {
        this.idMesasAtendidas = idMesasAtendidas;
    }

    public String getEmpleadosId() {
        return empleadosId;
    }

    public void setEmpleadosId(String empleadosId) {
        this.empleadosId = empleadosId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroMesasAtendidas() {
        return numeroMesasAtendidas;
    }

    public void setNumeroMesasAtendidas(int numeroMesasAtendidas) {
        this.numeroMesasAtendidas = numeroMesasAtendidas;
    }
    
    
}
