/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DetallesPedidos;
import models.Domicilios;
import models.Pedidos;

public class TarjetaLlevarController implements Initializable {

    @FXML
    private Label lbl_orden;
    @FXML
    private Label lbl_nombreMesera;
    @FXML
    private Label lbl_numeroMesa;
    @FXML
    private VBox ordenItems;
    private Domicilios domicilio;

    private List<DetallesPedidos> detalles;
    private String domicilioId; // Atributo para guardar el ID del pedido
    private HBox selectedHBox; 
    private String idSeleccionado;


    public TarjetaLlevarController(Button btn_facturar, Label lbl_order, Label lbl_nombre_mesera, Label lbl_numero_mesa, VBox orderItems, List<DetallesPedidos> detalles, String pedidoId) {
        this.lbl_orden = lbl_orden;
        this.lbl_nombreMesera = lbl_nombreMesera;
        this.lbl_numeroMesa = lbl_numeroMesa;
        this.ordenItems = ordenItems;
        this.detalles = detalles;
        this.domicilioId = domicilioId;
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setStyle("-fx-background-color: #9ECF78;");
    }

    public void setDomicilioDetails(String numeroOrden, String mesa, String mesera, List<DetallesPedidos> detalles) {
    this.domicilioId = numeroOrden; // Almacenar el ID del pedido
    lbl_orden.setText(numeroOrden.replace("pedido", "Pedido: "));
    lbl_numeroMesa.setText("Mesa: " + mesa);
    lbl_nombreMesera.setText("Mesera: " + mesera);

    // Añadir los detalles del pedido
    for (DetallesPedidos detalle : detalles) {
        HBox hbox = new HBox();
        hbox.setPrefWidth(200);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-background-color: #EFEFEF; -fx-border-color: #CCCCCC; -fx-border-radius: 5;");
        Label lbl_plato = new Label(detalle.getPlatosId());
        Label lbl_cantidad = new Label(" x" + detalle.getCantidad());
        Label lbl_precio = new Label("$" + detalle.getPrecioUnitario());

        // Agregar los elementos al HBox solo una vez
        hbox.getChildren().addAll(lbl_plato, lbl_cantidad, lbl_precio);
        hbox.setId(detalle.getPedidosId());

        hbox.setOnMouseClicked(event -> {
            if (selectedHBox != null) {
                selectedHBox.setStyle(""); 
            }

            //idSeleccionado = detalle.getdomicilioId();
            selectedHBox = hbox;
            selectedHBox.setStyle("-fx-border-color: blue; -fx-border-width: .2;"); 
        }); 

        ordenItems.getChildren().add(hbox);
    }
}

    public HBox getSelectedHBox() {
        return selectedHBox;
    }

    public String getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setPedido(Pedidos pedido, List<DetallesPedidos> detalles) {
        this.domicilio = domicilio;
        this.detalles = detalles;
    }

    public void abrirVistaFacturacion(String pedidoCard, String nMesa) {
        try {
            Domicilios domicilio = new Domicilios();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/Facturacion.fxml"));
            Parent root = loader.load();
            FacturacionController facturacionController = loader.getController();
            facturacionController.getlbl_nMesa().setText("MESA " + nMesa);

            // Pasar el ID de la reserva seleccionada al controlador de facturación
            facturacionController.mostrarFacturaParaPedido(pedidoCard);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Facturación");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    void setDomicilioDetails(String idDomicilios, String nombreCliente, String direccion, int cantidad, String platosId, String platos, Double precioUnitario, Double precioTotal, Date fecha, String observacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
