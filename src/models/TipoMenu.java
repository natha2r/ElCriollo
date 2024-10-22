package models;


public class TipoMenu {
    private int idTipoMenu;
    private String nombreMenu;
 

    public TipoMenu() {
    }

    public TipoMenu(int idTipoMenu, String nombreMenu) {
        this.idTipoMenu = idTipoMenu;
        this.nombreMenu = nombreMenu;
    }

    public int getIdTipoMenu() {
        return idTipoMenu;
    }

    public void setIdTipoMenu(int idTipoMenu) {
        this.idTipoMenu = idTipoMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }
    
     void setNombreTipoMenu(String string) {
        
    }
}