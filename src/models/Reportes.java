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
public class Reportes {
    private String idReportes;
    private String tipoReporteId;
    private Date fechaReporte;
    private String descripcion;
    private String datosReporte;
    private String empleadosId;
    private Date fechaActualizacion;

    public Reportes() {
    }

    public Reportes(String idReportes, String tipoReporteId, Date fechaReporte, String descripcion, String datosReporte, String empleadosId, Date fechaActualizacion) {
        this.idReportes = idReportes;
        this.tipoReporteId = tipoReporteId;
        this.fechaReporte = fechaReporte;
        this.descripcion = descripcion;
        this.datosReporte = datosReporte;
        this.empleadosId = empleadosId;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getIdReportes() {
        return idReportes;
    }

    public void setIdReportes(String idReportes) {
        this.idReportes = idReportes;
    }

    public String getTipoReporteId() {
        return tipoReporteId;
    }

    public void setTipoReporteId(String tipoReporteId) {
        this.tipoReporteId = tipoReporteId;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatosReporte() {
        return datosReporte;
    }

    public void setDatosReporte(String datosReporte) {
        this.datosReporte = datosReporte;
    }

    public String getEmpleadosId() {
        return empleadosId;
    }

    public void setEmpleadosId(String empleadosId) {
        this.empleadosId = empleadosId;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    
    
    
}
