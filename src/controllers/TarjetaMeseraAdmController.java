package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class TarjetaMeseraAdmController implements Initializable {

    @FXML
    private Label lbl_nombre;
    
    @FXML
    private Label lbl_edad;
    
    @FXML
    private Label lbl_mesas;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setDatosMeseras() {
        lbl_nombre.setText("Nombre: " );
        lbl_edad.setText("Edad: " );
        lbl_mesas.setText("Mesas: ");
        
    }
    
}
