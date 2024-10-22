package controllers;

import java.io.IOException;
import java.net.URL;
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
import models.Pedidos;

public class TarjetaPedidoController implements Initializable {

    @FXML
    private Button btn_facturar;
    @FXML
    private Label lbl_order;
    @FXML
    private Label lbl_nombre_mesera;
    @FXML
    private Label lbl_numero_mesa;
    @FXML
    private VBox orderItems;
    private Pedidos pedido;

    private List<DetallesPedidos> detalles;
    private String pedidoId; // Atributo para guardar el ID del pedido
    private HBox selectedHBox;
    private String idSeleccionado;

    public TarjetaPedidoController() {
    }

    public TarjetaPedidoController(Button btn_facturar, Label lbl_order, Label lbl_nombre_mesera, Label lbl_numero_mesa, VBox orderItems, List<DetallesPedidos> detalles, String pedidoId) {
        this.btn_facturar = btn_facturar;
        this.lbl_order = lbl_order;
        this.lbl_nombre_mesera = lbl_nombre_mesera;
        this.lbl_numero_mesa = lbl_numero_mesa;
        this.orderItems = orderItems;
        this.detalles = detalles;
        this.pedidoId = pedidoId;
    }

    @FXML
    private void handleLabelClick(MouseEvent event) {
        Label label = (Label) event.getSource();
        label.setStyle("-fx-background-color: #9ECF78;");
    }

    public void setPedidoDetails(String numeroOrden, String mesa, String mesera, List<DetallesPedidos> detalles) {
        this.pedidoId = numeroOrden; // Almacenar el ID del pedido
        lbl_order.setText(numeroOrden.replace("pedido", "Pedido: "));
        lbl_numero_mesa.setText("Mesa: " + mesa);
        lbl_nombre_mesera.setText("Mesera: " + mesera);

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

    public Button getbtn_facturar() {
        return btn_facturar;
    }

    public HBox getSelectedHBox() {
        return selectedHBox;
    }

    public String getIdSeleccionado() {
        return idSeleccionado;
    }

    public void setPedido(Pedidos pedido, List<DetallesPedidos> detalles) {
        this.pedido = pedido;
        this.detalles = detalles;
    }

    public void abrirVistaFacturacion(String pedidoCard, String nMesa) {
        try {
            Pedidos pedidos = new Pedidos();
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
}
