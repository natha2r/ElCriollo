/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import models.DetallesPedidos;

public class TarjetaLlevarController implements Initializable {

    @FXML
    private Label lbl_order;
    @FXML
    private Label lbl_nombreMesera;
    @FXML
    private Label lbl_numeroMesa;
    @FXML
    private VBox ordenItems;

    private List<DetallesPedidos> detalles;
    private String domicilioId; 
    private HBox selectedHBox; 
    private String idSeleccionado;

    public TarjetaLlevarController() {
    }

    public TarjetaLlevarController(Label lbl_order, Label lbl_nombreMesera, Label lbl_numeroMesa, VBox ordenItems, List<DetallesPedidos> detalles, String domicilioId, HBox selectedHBox, String idSeleccionado) {
        this.lbl_order = lbl_order;
        this.lbl_nombreMesera = lbl_nombreMesera;
        this.lbl_numeroMesa = lbl_numeroMesa;
        this.ordenItems = ordenItems;
        this.detalles = detalles;
        this.domicilioId = domicilioId;
        this.selectedHBox = selectedHBox;
        this.idSeleccionado = idSeleccionado;
    }    
    
    // Eliminar el constructor que acepta parámetros

    @FXML
    private void handleLabelClick(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setStyle("-fx-background-color: #9ECF78;");
    }

    /*public void setDomicilioDetails(String numeroOrden, String mesa, String mesera, List<DetallesPedidos> detalles) {
        this.domicilioId = numeroOrden; 
        lbl_order.setText(numeroOrden.replace("pedido", "Pedido: "));
        lbl_numeroMesa.setText("Mesa: " + mesa);
        lbl_nombreMesera.setText("Mesera: " + mesera);
        this.detalles = detalles; // Asignar los detalles aquí

    }*/

    public void cargarDetalles(String numeroOrden, String mesa, String mesera, List<DetallesPedidos> detalles) {
        this.domicilioId = numeroOrden; 
        lbl_order.setText(numeroOrden.replace("pedido", "Pedido: "));
        lbl_numeroMesa.setText("Mesa: " + mesa);
        lbl_nombreMesera.setText("Mesera: " + mesera);
        ordenItems.getChildren().clear(); // Limpiar antes de agregar nuevos elementos
        
        this.detalles = detalles; // Asignar los detalles aquí

        // Verifica que la lista de detalles no esté vacía
        if (detalles == null || detalles.isEmpty()) {
            Label noDetallesLabel = new Label("No hay detalles para mostrar.");
            ordenItems.getChildren().add(noDetallesLabel);
            return;
        }

        for (DetallesPedidos detalle : detalles) {
            HBox hbox = new HBox();
            hbox.setPrefWidth(200);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setStyle("-fx-background-color: #EFEFEF; -fx-border-color: #CCCCCC; -fx-border-radius: 5;");

            // Asegúrate de que estos métodos devuelvan los valores correctos
            Label lbl_plato = new Label(detalle.getPlatosId()); // Asegúrate de que getPlatosId() devuelva el nombre del plato
            Label lbl_cantidad = new Label(" x" + detalle.getCantidad());
            Label lbl_precio = new Label("$" + detalle.getPrecioUnitario());

            // Agregar los elementos al HBox
            hbox.getChildren().addAll(lbl_plato, lbl_cantidad, lbl_precio);
            hbox.setId(String.valueOf(detalle.getPedidosId())); // Asignar ID del pedido

            hbox.setOnMouseClicked(event -> {
                if (selectedHBox != null) {
                    selectedHBox.setStyle(""); // Restablecer estilo
                }
                selectedHBox = hbox;
                selectedHBox.setStyle("-fx-border-color: blue; -fx-border-width: 0.2;");
                idSeleccionado = String.valueOf(detalle.getPedidosId()); // Asignar ID seleccionado
            });
            
            ordenItems.getChildren().add(hbox); // Asegúrate de que esto esté correcto
        }
    }


    public HBox getSelectedHBox() {
        return selectedHBox;
    }

    public String getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setPedido(List<DetallesPedidos> detalles) {
        this.detalles = detalles; 
        //cargarDetalles(); // Cargar los detalles si es necesario
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }
}


