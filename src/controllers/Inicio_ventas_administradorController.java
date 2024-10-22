package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Inicio_ventas_administradorController {

    @FXML
    private Button btn_inicio;

    @FXML
    private Button btn_configuracion;

    @FXML
    private Button btn_inventario;

    @FXML
    private Button btn_reportes;

    @FXML
    private Button btn_mesas;

    @FXML
    private Button btn_caja;

    @FXML
    private Button btn_salir;

    @FXML
    private Button btn_ventas;

    @FXML
    private Button btn_meseras;

    @FXML
    private AnchorPane rootPane;
    
    
    
    public void initialize() {
        // No hacer nada aquí
    }

    @FXML
    public void onShown() {
        // Agregar controladores a los botones
        btn_inicio.setOnAction(e -> handleBtnInicioAction());
        btn_configuracion.setOnAction(e -> handleBtnConfiguracionAction());
        btn_inventario.setOnAction(e -> handleBtnInventarioAction());
        btn_reportes.setOnAction(e -> handleBtnReportesAction());
        btn_mesas.setOnAction(e -> handleBtnMesasAction());
        btn_caja.setOnAction(e -> handleBtnCajaAction());
        btn_salir.setOnAction(e -> handleBtnSalirAction());
        btn_ventas.setOnAction(e -> handleBtnVentasAction());
        btn_meseras.setOnAction(e -> handleBtnMeserasAction());

       
    }

    @FXML
    public void handleBtnInicioAction() {
        // Acción para el botón "INICIO"
    }
    
    @FXML
    public void handleBtnConfiguracionAction() {
        // Acción para el botón "CONFIGURACIÓN"
        
    }

    public void handleBtnInventarioAction() {
        // Acción para el botón "INVENTARIO"
    }

    public void handleBtnReportesAction() {
        // Acción para el botón "REPORTES"
    }

    public void handleBtnMesasAction() {
        // Acción para el botón "MESAS"
    }

    public void handleBtnCajaAction() {
        // Acción para el botón "CAJA"
    }
    @FXML
    public void handleBtnSalirAction() {
        // Acción para el botón "SALIR"
        
        
    }
    @FXML
    public void salir() {
        // Acción para el botón "SALIR"
        
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main/LoginView.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void handleBtnVentasAction() {
        // Acción para el botón "VENTAS"
    }

    public void handleBtnMeserasAction() {
        // Acción para el botón "MESERAS"
    }
}