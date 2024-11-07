package controllers;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DetallesPedidos;
import models.PedidosDao;

public class FacturacionController implements Initializable {

    @FXML
    private Label lbl_nMesa;
    @FXML
    private ScrollPane scrollPaneDetalles;
    @FXML
    private VBox contenedorDetalles;
    @FXML
    private Button btn_cancelarT;
    @FXML
    private Button btn_efectivo;
    @FXML
    private Button btn_volver;
    @FXML
    private Button btn_facturar;
    @FXML
    private Label lbl_total;
    @FXML
    private Label lbl_totalP;
    @FXML
    private Label lbl_cambio;
    @FXML
    private TextField txt_pago;
    @FXML
    private BorderPane glassPane;

    private Connection conn;

    private PedidosDao pedidosDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidosDao = new PedidosDao(conn); // Inicializa tu DAO aquí
        btn_efectivo.setOnAction(e -> abrirInformacionEfectivo());
        btn_volver.setOnAction(e -> cancelarPago());
        btn_cancelarT.setOnAction(e -> cancelar());
    }

    // Método para establecer el DAO desde el MainController
    public void setPedidosDao(PedidosDao pedidosDao) {
        this.pedidosDao = pedidosDao;
    }

    public Label getlbl_nMesa() {
        return lbl_nMesa;
    }

    public void abrirInformacionEfectivo() {
        glassPane.setVisible(true);
        lbl_totalP.setText(lbl_total.getText()); // Copia el valor de lbl_total a lbl_totalP
        txt_pago.setText(""); // Asegura que el campo txt_pago esté vacío al abrir
        lbl_cambio.setText("0"); // Inicializa lbl_cambio en 0

        // Formato para Colombia con separador de miles
        NumberFormat formatoColombia = NumberFormat.getInstance(new Locale("es", "CO"));

        // Añade un listener para actualizar el cambio al escribir en txt_pago
        txt_pago.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    // Convierte el texto de lbl_totalP a int eliminando cualquier separador
                    int totalInt = formatoColombia.parse(lbl_totalP.getText()).intValue();

                    // Convierte el valor de txt_pago a int eliminando cualquier separador de miles
                    int pagoInt = formatoColombia.parse(newValue).intValue();

                    // Calcula el cambio
                    int cambio = pagoInt - totalInt;

                    // Muestra el cambio con separador de miles en lbl_cambio
                    lbl_cambio.setText(formatoColombia.format(cambio));
                } catch (ParseException e) {
                    lbl_cambio.setText("Error"); // Si hay un error de formato, muestra "Error"
                }
            } else {
                lbl_cambio.setText("0"); // Si el campo está vacío, muestra 0
            }
        });
    }
    
    public void cancelarPago () {
        glassPane.setVisible(false);
        lbl_totalP.setText("");
        lbl_cambio.setText("");
        txt_pago.setText("");
    }
    
    public void cancelar(){
        Stage stage = (Stage) btn_cancelarT.getScene().getWindow();
        stage.close();
    }

    public void mostrarFacturaParaPedido(String idPedidos, String mesa) {
        cargarDetallesFactura(idPedidos, mesa);
    }

    public void cargarDetallesFactura(String idPedidos, String Mesa) {
        try {
            BigDecimal total = new BigDecimal("0");
            String precioFormateado = "";
            DecimalFormat df = new DecimalFormat("#,###"); // Formato para mostrar con comas y dos decimales
            // Lógica para cargar los detalles basados en el ID del pedido
            List<DetallesPedidos> detalles = pedidosDao.getDetallesPedido(idPedidos); // Cambia el método según tu DAO

            // Limpiar el contenedor de detalles
            contenedorDetalles.getChildren().clear();

            // Añadir cada detalle como un Label, o puedes crear un HBox para estilizar mejor
            for (DetallesPedidos detalle : detalles) {
                String precioLbl = df.format(detalle.getPrecioUnitario());
                Label label = new Label(detalle.getPlatosId() + " - Cantidad: " + detalle.getCantidad() + " - Precio: " + precioLbl);
                contenedorDetalles.getChildren().add(label);
                BigDecimal cantidadBD = BigDecimal.valueOf(detalle.getCantidad());
                total = total.add(detalle.getPrecioUnitario().multiply(cantidadBD));
                precioFormateado = df.format(total);
            }

            lbl_total.setText(String.valueOf(precioFormateado));

            btn_facturar.setOnAction(e -> imprimir(idPedidos, Mesa));

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cargar detalles de factura: " + e.getMessage());
        }
    }

    private void imprimir(String idPedidos, String Mesa) {
        try {
            List<DetallesPedidos> detalles = pedidosDao.getDetallesPedido(idPedidos);
            Ticket ticket = new Ticket();
            ticket.imprimirFactura(detalles, Mesa);
        } catch (SQLException ex) {
            Logger.getLogger(FacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
