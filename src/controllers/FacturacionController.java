package controllers;

import com.sun.jdi.connect.spi.Connection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
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
    private Button btn_imprimir;

    private Connection conn;

    private PedidosDao pedidosDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidosDao = new PedidosDao(conn); // Inicializa tu DAO aquí
    }

    // Método para establecer el DAO desde el MainController
    public void setPedidosDao(PedidosDao pedidosDao) {
        this.pedidosDao = pedidosDao;
    }
    
    public Label getlbl_nMesa(){
        return lbl_nMesa;
    }
    
    public void mostrarFacturaParaPedido(String idPedidos){
        cargarDetallesFactura(idPedidos);
    }
    
    public void cargarDetallesFactura(String idPedidos) {
        try {
            // Lógica para cargar los detalles basados en el ID del pedido
            List<DetallesPedidos> detalles = pedidosDao.getDetallesPedido(idPedidos); // Cambia el método según tu DAO

            // Limpiar el contenedor de detalles
            contenedorDetalles.getChildren().clear();

            // Añadir cada detalle como un Label, o puedes crear un HBox para estilizar mejor
            for (DetallesPedidos detalle : detalles) {
                Label label = new Label(detalle.getPlatosId() + " - Cantidad: " + detalle.getCantidad() + " - Precio: " + detalle.getPrecioUnitario());
                contenedorDetalles.getChildren().add(label);
            }
            
            btn_imprimir.setOnAction(e -> imprimir(idPedidos));
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cargar detalles de factura: " + e.getMessage());
        }
    }
    
    
    
    private void imprimir(String idPedidos){
        try {
            List<DetallesPedidos> detalles = pedidosDao.getDetallesPedido(idPedidos);
            System.out.println("click");
            Ticket ticket = new Ticket();
            ticket.imprimirFactura(detalles, "4");
        } catch (SQLException ex) {
            Logger.getLogger(FacturacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
