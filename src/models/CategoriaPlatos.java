package models;

public class CategoriaPlatos {
    private String idCategoriaPlatos;
    private String nombreCategoriaPlatos;
    private int idTipoMenu;

    public CategoriaPlatos() {
    }

    public String getIdCategoriaPlatos() {
        return idCategoriaPlatos;
    }

    public void setIdCategoriaPlatos(String idCategoriaPlatos) {
        this.idCategoriaPlatos = idCategoriaPlatos;
    }

    public String getNombreCategoriaPlatos() {
        return nombreCategoriaPlatos;
    }

    public void setNombreCategoriaPlatos(String nombreCategoriaPlatos) {
        this.nombreCategoriaPlatos = nombreCategoriaPlatos;
    }

    public int getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(int idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }
}


