package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.DetallesPedidos;
import models.Pedidos;

public class TarjetaDeliveryController implements Initializable {

    @FXML
    private Button btn_facturar;
    @FXML
    private Label lbl_order;
    @FXML
    private Label lbl_direccion;
    @FXML
    private Label lbl_telefono;
    @FXML
    private VBox orderItems;
    private Pedidos pedido;

    private List<DetallesPedidos> detalles;
    private String pedidoId; // Atributo para guardar el ID del pedido
    private HBox selectedHBox;
    private String idSeleccionado;

    public TarjetaDeliveryController() {
    }

    public TarjetaDeliveryController(Button btn_facturar, Label lbl_order, Label lbl_direccion, Label lbl_telefono, VBox orderItems, Pedidos pedido, List<DetallesPedidos> detalles, String pedidoId, HBox selectedHBox, String idSeleccionado) {
        this.btn_facturar = btn_facturar;
        this.lbl_order = lbl_order;
        this.lbl_direccion = lbl_direccion;
        this.lbl_telefono = lbl_telefono;
        this.orderItems = orderItems;
        this.pedido = pedido;
        this.detalles = detalles;
        this.pedidoId = pedidoId;
        this.selectedHBox = selectedHBox;
        this.idSeleccionado = idSeleccionado;
    }

    public void setPedidoDetails(String numeroOrden, String direccion, String telefono, List<DetallesPedidos> detalles) {
        this.pedidoId = numeroOrden; // Almacenar el ID del pedido
        lbl_order.setText(numeroOrden.replace("pedido", "Pedido: "));
        lbl_direccion.setText("Direccion: " + direccion);
        lbl_telefono.setText("Telefono: " + telefono);

        // Añadir los detalles del pedido
        for (DetallesPedidos detalle : detalles) {
            HBox hbox = new HBox();
            hbox.setPrefWidth(200);
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setStyle("-fx-font-family: 'Karla'; -fx-font-style: normal; -fx-font-weight: bold; -fx-font-size: 15px; -fx-text-fill: #FEB01D;");
            Label lbl_plato = new Label(detalle.getPlatosId() + "  ");
            Label lbl_cantidad = new Label(" x" + detalle.getCantidad() + "  ");
            Label lbl_precio = new Label("$" + detalle.getPrecioUnitario());

            hbox.setId(detalle.getPedidosId());

            hbox.setOnMouseClicked(event -> {
                if (selectedHBox != null) {
                    selectedHBox.setStyle("");
                }

                idSeleccionado = detalle.getPedidosId();
                selectedHBox = hbox;
                selectedHBox.setStyle("-fx-border-color: blue; -fx-border-width: .2;");
            });

            hbox.getChildren().addAll(lbl_cantidad, lbl_plato, lbl_precio);
            orderItems.getChildren().add(hbox);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
