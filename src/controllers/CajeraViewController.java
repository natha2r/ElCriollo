package controllers;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.sql.SQLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.ConnectionMySQL;
import models.DetallesPedidos;
import models.Pedidos;
import models.PedidosDao;
import models.Reservas;
import models.ReservasDao;
import models.Domicilios;

public class CajeraViewController implements Initializable {

    @FXML
    private Button btn_reservar;

    @FXML
    private GridPane contenedor_tarjetasR;

    @FXML
    private TextField txt_hora;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_personas;

    @FXML
    private DatePicker dp_fecha;

    @FXML
    private TextArea txt_motivo;   //Es un TextArea

    @FXML
    private TextField txt_telefono;

    private ReservasDao reservasDao;
    private int currentColumn = 0;
    private int currentRow = 0;
    private int maxColumns = 3;

    @FXML
    private Button btn_cancelar_reserva;

    @FXML
    private Button btn_salir;

    @FXML
    private Button btn_add_reservas;

    @FXML
    private Button btn_inicio;

    @FXML
    private Button mostrarReservas;

    @FXML
    private Button mostrarDelivery;

    @FXML
    private Button mostrarFacturas;

    @FXML
    private Label popupLabel; //Texto de "MESA X"

    @FXML
    private Pane paneInicio; //Panel de Inicio

    @FXML
    private Pane contenerdorReservas; // El Pane que actúa como GlassPane

    @FXML
    private AnchorPane contenedorFacturas;

    @FXML
    private BorderPane glassPane;

    @FXML
    private AnchorPane contenedorReservas;

    @FXML
    private AnchorPane contenedorDeilvery;

    @FXML
    private GridPane contenedor_tarjetas; // GridPane para los pedidos

    private Connection conn;

    private PedidosDao pedidos_dao;

    private Pedidos pedido;
    private String idReservaActual;  // Variable para almacenar el ID de la reserva actual

    public CajeraViewController() {
    }

    public CajeraViewController(Button btn_reservar, GridPane contenedor_tarjetasR, TextField txt_hora, TextField txt_nombre, TextField txt_personas, DatePicker dp_fecha, TextArea txt_motivo, TextField txt_telefono, ReservasDao reservasDao, Button btn_cancelar_reserva, Button btn_salir, Button btn_add_reservas, Button btn_inicio, Button mostrarReservas, Button mostrarDelivery, Button mostrarFacturas, Label popupLabel, Pane paneInicio, Pane contenerdorReservas, AnchorPane contenedorFacturas, BorderPane glassPane, AnchorPane contenedorReservas, AnchorPane contenedorDeilvery, GridPane contenedor_tarjetas, Connection conn, PedidosDao pedidos_dao, Pedidos pedido, String idReservaActual) {
        this.btn_reservar = btn_reservar;
        this.contenedor_tarjetasR = contenedor_tarjetasR;
        this.txt_hora = txt_hora;
        this.txt_nombre = txt_nombre;
        this.txt_personas = txt_personas;
        this.dp_fecha = dp_fecha;
        this.txt_motivo = txt_motivo;
        this.txt_telefono = txt_telefono;
        this.reservasDao = reservasDao;
        this.btn_cancelar_reserva = btn_cancelar_reserva;
        this.btn_salir = btn_salir;
        this.btn_add_reservas = btn_add_reservas;
        this.btn_inicio = btn_inicio;
        this.mostrarReservas = mostrarReservas;
        this.mostrarDelivery = mostrarDelivery;
        this.mostrarFacturas = mostrarFacturas;
        this.popupLabel = popupLabel;
        this.paneInicio = paneInicio;
        this.contenerdorReservas = contenerdorReservas;
        this.contenedorFacturas = contenedorFacturas;
        this.glassPane = glassPane;
        this.contenedorReservas = contenedorReservas;
        this.contenedorDeilvery = contenedorDeilvery;
        this.contenedor_tarjetas = contenedor_tarjetas;
        this.conn = conn;
        this.pedidos_dao = pedidos_dao;
        this.pedido = pedido;
        this.idReservaActual = idReservaActual;
    }

    public CajeraViewController(GridPane contenedor_tarjetas, Connection conn, PedidosDao pedidos_dao) {
        this.contenedor_tarjetas = contenedor_tarjetas;
        this.conn = conn;
        this.pedidos_dao = pedidos_dao;
        ConnectionMySQL connectionMySQL = new ConnectionMySQL();
        conn = (Connection) connectionMySQL.getConnection();
        pedidos_dao = new PedidosDao(conn);
    }

    public String getIdReservaActual() {
        return idReservaActual;
    }

    public void setIdReservaActual(String idReservaActual) {
        this.idReservaActual = idReservaActual;
    }

    public DatePicker getDp_fecha() {
        return dp_fecha;
    }

    public TextField getTxt_hora() {
        return txt_hora;
    }

    public TextField getTxt_nombre() {
        return txt_nombre;
    }

    public TextField getTxt_personas() {
        return txt_personas;
    }

    public TextArea getTxt_motivo() {
        return txt_motivo;
    }

    public TextField getTxt_telefono() {
        return txt_telefono;
    }

    public BorderPane getGlassPane() {
        return glassPane;
    }

    public GridPane getContenedor_tarjetasR() {
        return contenedor_tarjetasR;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_inicio.setStyle("-fx-background-color: #C68711;");
        mostrarFacturas.setStyle("-fx-background-color: #FFB94F;");
        pedido = new Pedidos();
        reservasDao = new ReservasDao();
        //btn_reservar.setOnAction(event -> registrarReserva());
        // Configura eventos para los botones
        btn_add_reservas.setOnAction(e -> addReservas());
        mostrarFacturas.setOnAction(e -> mostrarFacturas());
        mostrarReservas.setOnAction(e -> mostrarReservas());
        mostrarDelivery.setOnAction(e -> mostrarDelivery());
        btn_reservar.setOnAction(event -> guardarCambios());

        contenedorReservas.setVisible(false);
        glassPane.setVisible(false);
        cargarReservas();
        // Otros componentes de la interfaz pueden ser inicializados aquí
        paneInicio.setVisible(true);
        btn_salir.setOnAction(e -> handleButton3Click());
        pedidos_dao = new PedidosDao(conn);
        try {
            loadPedidos();
        } catch (Exception e) {
            System.out.println("Error al cargar pedidos: " + e.getMessage());
        }
    }

    @FXML
    private void addReservas() {
        glassPane.setVisible(true);
        txt_hora.clear();
        limpiarFormulario();
    }

    @FXML
    private void mostrarFacturas() {
        glassPane.setVisible(false);
        contenedorFacturas.setVisible(true);
        contenedorReservas.setVisible(false);
        contenedorDeilvery.setVisible(false);
        mostrarFacturas.setStyle("-fx-background-color: #FFB94F;");
        mostrarReservas.setStyle("-fx-background-color: #FFA150;");
        mostrarDelivery.setStyle("-fx-background-color: #FFA150;");
    }

    @FXML
    private void mostrarReservas() {
        glassPane.setVisible(false);
        contenedorFacturas.setVisible(false);
        contenedorReservas.setVisible(true);
        mostrarReservas.setStyle("-fx-background-color: #FFB94F;");
        mostrarFacturas.setStyle("-fx-background-color: #FFA150;");
        mostrarDelivery.setStyle("-fx-background-color: #FFA150;");
        contenedorDeilvery.setVisible(false);
    }

    @FXML
    private void mostrarDelivery() {
        contenedorFacturas.setVisible(false);
        contenedorReservas.setVisible(false);
        contenedorDeilvery.setVisible(true);
        mostrarDelivery.setStyle("-fx-background-color: #FFB94F;");
        mostrarFacturas.setStyle("-fx-background-color: #FFA150;");
        mostrarReservas.setStyle("-fx-background-color: #FFA150;");
    }

    @FXML
    private void closePopup() {
        // Ocultar el GlassPane y el contenido del popup
        glassPane.setVisible(false);

    }

    private void handleButton3Click() {
        Stage stage = (Stage) btn_salir.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/LoginView.fxml"));
            Parent root = loader.load();

            // Obtener el controlador y establecer el Stage
            LoginController loginController = loader.getController();
            loginController.setStage(stage);

            // Configurar y mostrar la escena
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
    private void handleButton1Click() {
        contenedorFacturas.setVisible(true);
        contenedorReservas.setVisible(false);
    }
    
    private void handleButton2Click() {
        contenedorFacturas.setVisible(false);
        contenedorReservas.setVisible(true);
    }
     */
    private void registrarReserva() {
        glassPane.setVisible(true);
        // Crear objeto de reserva con los datos ingresados
        Reservas nuevaReserva = new Reservas();
        nuevaReserva.setNombreCliente(txt_nombre.getText());
        nuevaReserva.setNumeroPersonas(Integer.parseInt(txt_personas.getText()));
        nuevaReserva.setFecha(java.sql.Date.valueOf(dp_fecha.getValue()));
        nuevaReserva.setTelefono(txt_telefono.getText());
        nuevaReserva.setNota(txt_motivo.getText());

        // Guardar reserva en la base de datos
        if (reservasDao.guardarReserva(nuevaReserva)) {
            // Agregar la tarjeta de reserva al GridPane
            agregarTarjetaReserva(nuevaReserva);

            // Limpiar los campos del formulario
            limpiarFormulario();
        } else {
            System.out.println("Error al guardar la reserva en la base de datos.");
        }
    }

    private void agregarTarjetaReserva(Reservas reserva) {
        glassPane.setVisible(false);
        try {
            // Cargar la tarjeta de reserva desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/TarjetaReserva.fxml"));
            VBox tarjetaReserva = loader.load();

            String hora = txt_hora.getText();

            // Obtener el controlador de la tarjeta de reserva
            TarjetaReservaController controller = loader.getController();
            controller.setDatosReserva(reserva, hora);

            tarjetaReserva.setId(reserva.getIdReservas());

            controller.getBtn_editarReserva().setOnAction(event -> {
                setIdReservaActual(reserva.getIdReservas());
                // Pasar el ID del pedido para facturar
                controller.handleEditar();
            });

            controller.getBtn_deletereserva().setOnAction(event -> {
                // Pasar el ID del pedido para facturar
                controller.handleEliminar(reserva.getIdReservas());
            });

            // Pasar el controlador principal (CajeraViewController) al controlador de la tarjeta
            controller.setCajeraController(this); // this es la instancia de CajeraViewController

            contenedor_tarjetasR.getColumnConstraints().clear();
            contenedor_tarjetasR.getRowConstraints().clear();

            // Añadir la tarjeta de reserva al GridPane
            contenedor_tarjetasR.add(tarjetaReserva, currentColumn, currentRow);

            // Mover la posición del botón "+"
            moverBotonAddReserva();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registrardelivery() {
        Domicilios nuevoDomicilio = new Domicilios();
        nuevoDomicilio.setNombreCliente(idReservaActual);
    }

    public void guardarCambios() {
        // Crear un nuevo objeto de reserva
        Reservas reserva = new Reservas();
        reserva.setNombreCliente(getTxt_nombre().getText());
        reserva.setTelefono(getTxt_telefono().getText());
        reserva.setFecha(java.sql.Date.valueOf(getDp_fecha().getValue()));
        reserva.setNota(getTxt_motivo().getText());
        reserva.setNumeroPersonas(Integer.parseInt(getTxt_personas().getText()));

        // Si estamos en modo de edición (es decir, si hay un ID de reserva presente)
        if (idReservaActual != null && !idReservaActual.isEmpty()) {
            // Actualizar la reserva existente
            reserva.setIdReservas(idReservaActual);

            if (reservasDao.actualizarReserva(reserva)) {
                System.out.println("Reserva actualizada exitosamente.");
            } else {
                System.out.println("Error al actualizar la reserva.");
            }
        } else {
            // Registrar una nueva reserva
            if (reservasDao.guardarReserva(reserva)) {
                System.out.println("Nueva reserva registrada exitosamente.");
            } else {
                System.out.println("Error al registrar la nueva reserva.");
            }
        }

        // Después de registrar o actualizar, refrescar las tarjetas
        getContenedor_tarjetasR().getChildren().clear();
        cargarReservas(); // Actualiza las reservas visibles
        limpiarFormulario(); // Limpiar los campos del formulario
    }

    private void moverBotonAddReserva() {
        // Incrementa la columna actual y mueve el botón a la posición siguiente
        currentColumn++;
        if (currentColumn >= maxColumns) {
            currentColumn = 0;
            currentRow++;
        }
        // Reubicar el botón "+" en la siguiente celda del GridPane
        contenedor_tarjetasR.getChildren().remove(btn_add_reservas);
        contenedor_tarjetasR.add(btn_add_reservas, currentColumn, currentRow);
    }

    public void moverBotonAddReservaEliminar() {
        // Decrementa la columna actual y mueve el botón a la posición anterior
        currentColumn++;
        if (currentColumn < 0) {
            currentColumn = maxColumns;
            currentRow--;
        }
        // Asegúrate de que la posición no sea negativa
        if (currentRow < 0) {
            currentRow = 0;
        }
        // Reubicar el botón "+" en la celda correcta del GridPane
        contenedor_tarjetasR.getChildren().remove(btn_add_reservas);
        contenedor_tarjetasR.add(btn_add_reservas, currentColumn, currentRow);
    }

    public void limpiarFormulario() {
        txt_nombre.clear();
        txt_personas.clear();
        dp_fecha.setValue(null);
        txt_telefono.clear();
        txt_motivo.clear();

        // Limpiar el ID de la reserva actual para que vuelva al modo "registro"
        idReservaActual = null;
    }

    public void cargarReservas() {
        // Cargar reservas existentes desde la base de datos
        List<Reservas> reservas = reservasDao.obtenerTodasLasReservas();
        // Limpia el GridPane antes de volver a agregar las tarjetas
        //contenedor_tarjetasR.getChildren().clear();

        // Restablece las posiciones actuales
        currentColumn = 0;
        currentRow = 0;

        // Agrega las tarjetas de reserva al GridPane
        for (Reservas reserva : reservas) {
            agregarTarjetaReserva(reserva);
        }
    }

    private void loadPedidos() {
        try {
            List<Pedidos> pedidos = pedidos_dao.getAllPedidos();

            // Limpia todas las columnas y filas existentes
            contenedor_tarjetas.getColumnConstraints().clear();
            contenedor_tarjetas.getRowConstraints().clear();
            contenedor_tarjetas.getChildren().clear();

            int column = 0;
            int row = 0;
            int maxColumns = 3; // Número máximo de columnas en el GridPane

            for (Pedidos pedido : pedidos) {
                List<DetallesPedidos> detalles = pedidos_dao.getDetallesPedido(pedido.getIdPedidos());
                VBox pedidoCard = loadPedidoCard(pedido, detalles); // Cambiado a VBox

                // Añadir tarjeta al GridPane
                contenedor_tarjetas.add(pedidoCard, column, row);
                column++;

                // Mover a la siguiente fila si se alcanzó el número máximo de columnas
                if (column >= maxColumns) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private VBox loadPedidoCard(Pedidos pedido, List<DetallesPedidos> detalles) {
        VBox pedidoCard = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/TarjetaPedido.fxml"));
            pedidoCard = loader.load();
            TarjetaPedidoController controller = loader.getController();
            controller.setPedidoDetails(
                    pedido.getIdPedidos(),
                    pedido.getMesasId(),
                    pedido.getEmpleadosId(),
                    detalles
            );
            controller.getbtn_facturar().setOnAction(event -> {
                // Pasar el ID del pedido para facturar
                controller.abrirVistaFacturacion(pedido.getIdPedidos(), pedido.getMesasId());
            });

        } catch (IOException e) {
            e.printStackTrace(); // Log the exception
        }
        return pedidoCard;
    }

    private String String(int numeroPersonas) {
        return null;
    }

}
