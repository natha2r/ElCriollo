package models;

public class TipoReportes {
    private String idTipoReporte;
    private String nombreTipoReporte;
    private String descripcion;

    public TipoReportes() {
    }

    public TipoReportes(String idTipoReporte, String nombreTipoReporte, String descripcion) {
        this.idTipoReporte = idTipoReporte;
        this.nombreTipoReporte = nombreTipoReporte;
        this.descripcion = descripcion;
    }

    public String getIdTipoReporte() {
        return idTipoReporte;
    }

    public void setIdTipoReporte(String idTipoReporte) {
        this.idTipoReporte = idTipoReporte;
    }

    public String getNombreTipoReporte() {
        return nombreTipoReporte;
    }

    public void setNombreTipoReporte(String nombreTipoReporte) {
        this.nombreTipoReporte = nombreTipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
