package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;

import models.Employees;
import models.EmployeesDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Categorias;
import models.CategoriasDao;
import models.Pedidos;
import models.PedidosDao;
import models.Productos;

public class Inicio_ventas_administradorController {

    
    
    /*BOTON DE PRINCIPAL DE INICIO*/
    @FXML
    private Button btn_inicio;

    /*son subbotones del boton de inicio*/
    @FXML
    private Button btn_ventas;

    @FXML
    private Button btn_meseras;

    /*son recursos necesiarios para metodos de la vista inicio**/
    @FXML
    public Label jlabel_pedido;

    @FXML
    public Label jlabel_sum_pedido;

    
    
    
    /*BOTON DE PRINCIPAL DE CONFIGURACION*/
    @FXML
    private Button btn_configuracion;

    /*son subbotones del boton configuracion*/
    @FXML
    private Button btn_personal_roles;

    @FXML
    private Button btn_registro_actividades;

    @FXML
    private Button btn_volver_config;

    @FXML
    private Button btn_registrar_informacion;

    @FXML
    private Button btn_modificar_informacion;

    @FXML
    private Button btn_cancelar_registro_informacion;

    @FXML
    private Button btn_interno_registrar_informacion;

    /*SON RECURSOS PAR METODOS DE LA VISTA DE REGISTRAR EN CONFIGURACION*/
    @FXML
    private TextField txt_Id;
    @FXML
    private TextField txt_Nombre;
    @FXML
    private TextField txt_Edad;
    @FXML
    private TextField txt_Direccion;
    @FXML
    private TextField txt_Telefono;
    @FXML
    private TextField txt_Email;
    @FXML
    private ComboBox<String> cmb_Cargo;
    @FXML
    private PasswordField txt_Contraseña;
    @FXML
    private TextField txt_Usuario;

    /*SUBRECURSOS PARA METODOS DE LA VISTA DE MODIFICAR EN CONFIGURACION*/
    @FXML
    private TextField txt_modificar_Id;
    @FXML
    private TextField txt_modificar_Nombre;
    @FXML
    private TextField txt_modificar_Edad;
    @FXML
    private TextField txt_modificar_Direccion;
    @FXML
    private TextField txt_modificar_Telefono;
    @FXML
    private TextField txt_modificar_Email;
    @FXML
    private ComboBox<String> combo_Cargo;
    @FXML
    private TextField txt_modificar_Usuario;
    @FXML
    private PasswordField txt_modificar_Contraseña;

    // ObservableList para gestionar la lista de empleados
    private ObservableList<Employees> employeeList = FXCollections.observableArrayList();

    /*OTROS RECURSOS PARA LA TABLAVIEWS*/
    @FXML
    private TableView<Employees> tablaEmpleados;
    @FXML
    private TableColumn<Employees, String> colId;
    @FXML
    private TableColumn<Employees, String> colNombre;
    @FXML
    private TableColumn<Employees, String> colEdad;
    @FXML
    private TableColumn<Employees, String> colDireccion;
    @FXML
    private TableColumn<Employees, String> colTelefono;
    @FXML
    private TableColumn<Employees, String> colEmail;
    @FXML
    private TableColumn<Employees, String> colCargo;
    @FXML
    private TableColumn<Employees, String> colUsuario;
    @FXML
    private TableColumn<Employees, String> colContraseña;

    
    
    /*BOTON DE PRINCIPAL DE INVENTARIO*/
    @FXML
    private Button btn_inventario;
    
    /*son subbotones del boton inventario*/
    
    @FXML
    private Button btn_nuevo_prod;
    
    @FXML
    private Button btn_reponer_prod;
    
    @FXML
    private Button btn_buscar_prod;
    
    
    
    /*SON SUBRECURSOS PARA LA TABLA INVENTARIO*/
    @FXML
    private ComboBox<Categorias> comboBoxCategoria;
    @FXML
    private TableView<Productos> tableViewProductos;

    
    
    
    
    @FXML
    private Button btn_reportes;

    @FXML
    private Button btn_mesas;

    @FXML
    private Button btn_caja;

    @FXML
    private Button btn_salir;

    /*son recursos necesiarios para todas las vistas**/
    @FXML
    private Pane pane_personal_roles;

    @FXML
    private ScrollPane scroll_inicio_meseras;

    @FXML
    private AnchorPane anchor_inicio_ventas;

    @FXML
    private AnchorPane anchor_configuracion;

    @FXML
    private AnchorPane anchorPane_inicio;
    
    @FXML
    private AnchorPane anchor_inventario;

    @FXML
    private Pane pane_inventario;
    
    @FXML
    private Pane pane_configuracion;

    @FXML
    private Pane pane_registrar_informacion;

    @FXML
    private Pane pane_modificar_informacion;
    
    @FXML
    private Pane pane_nuevo_producto;
    
    @FXML
    private Pane pane_reponer_producto;
    
    
    @FXML
    private boolean enFormulario = false;
    
    

    // Cambia el tipo de conn a java.sql.Connection
    private Connection conn;

    public Inicio_ventas_administradorController() {
    }

    public Inicio_ventas_administradorController(Button btn_inicio, Button btn_configuracion, Button btn_inventario, Button btn_reportes,
            Button btn_mesas, Button btn_caja, Button btn_salir, Button btn_ventas, Button btn_meseras, ScrollPane scroll_inicio_meseras,
            AnchorPane anchor_inicio_ventas, Label jlabel_pedido, Label jlabel_sum_pedido, Connection conn) {
        this.btn_inicio = btn_inicio;
        this.btn_configuracion = btn_configuracion;
        this.btn_inventario = btn_inventario;
        this.btn_reportes = btn_reportes;
        this.btn_mesas = btn_mesas;
        this.btn_caja = btn_caja;
        this.btn_salir = btn_salir;
        this.btn_ventas = btn_ventas;
        this.btn_meseras = btn_meseras;
        this.scroll_inicio_meseras = scroll_inicio_meseras;
        this.anchor_inicio_ventas = anchor_inicio_ventas;
        this.jlabel_pedido = jlabel_pedido;
        this.jlabel_sum_pedido = jlabel_sum_pedido;
        this.conn = conn;

    }

    public void initialize() {
        // No hacer nada aquí
        /*onShown();
        UpdatePedidosLabels();*/
        updatePedidoLabels();
        EmployeesDao employeesDao = new EmployeesDao();
        List<String> roles = employeesDao.obtenerRoles();
        cmb_Cargo.getItems().addAll(roles);
        limpiarCampos();
        limpiarCamposmodificados();
        configurarColumnas(); // Método separado para la configuración de columnas
        // Configurar el ComboBox con los cargos
        combo_Cargo.getItems().addAll(roles);

        
        
        

        // Obtener los empleados de la base de datos y agregarlos al ObservableList
        employeeList.addAll(employeesDao.obtenerTodosLosEmpleados());
        System.out.println(employeesDao.obtenerTodosLosEmpleados());
        // Enlazar la lista de empleados a la tabla
        tablaEmpleados.setItems(employeeList);
        // Llama al método que maneja la selección de filas
        tablaEmpleados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarDatos(newValue);
            }
        })
                ;

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

        //subbotones de la vista de inicio
        btn_ventas.setOnAction(e -> handleBtnVentasAction());
        btn_meseras.setOnAction(e -> handleBtnMeserasAction());

        //subbotones de la vista de configuracion
        btn_personal_roles.setOnAction(e -> handleBtnPersonalRolesAction());
        btn_registro_actividades.setOnAction(e -> handleBtnRegistroActividadesAction());
        btn_volver_config.setOnAction(e -> handleBtnVolverConfigAction());
        btn_registrar_informacion.setOnAction(e -> handleBtnRegistrarInformacion());
        btn_modificar_informacion.setOnAction(e -> handleBtnModificarInformacion());
        btn_cancelar_registro_informacion.setOnAction(e -> handleBtnCancelarRegistroInformacion());
        btn_interno_registrar_informacion.setOnAction(e -> handleBtnInternoRegistroInformacion());
        
        
        //subbotones de la vista de inventario
        btn_nuevo_prod.setOnAction(e -> handleBtnNuevoProdAction());
        btn_reponer_prod.setOnAction(e -> handleBtnReponerProdAction());
        btn_buscar_prod.setOnAction(e -> handleBtnBuscarProdAction());
    }

    
    
@FXML
public void handleBtnInicioAction() {

    // Verificar si estás en los paneles de "Registrar Información" o "Modificar Información"
    if ((pane_registrar_informacion.isVisible() || pane_modificar_informacion.isVisible()) && !anchorPane_inicio.isVisible()) {
        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Acción para el botón "Inicio" si confirma
            limpiarEstadoFormulario();
            mostrarPanelInicio();
            enFormulario = false; // Actualizamos correctamente el estado
        }
    } else {
        // Si no estás en los formularios (ya estás en el panel de inicio), cambiar a la vista sin advertencia
        limpiarEstadoFormulario(); // Aseguramos de limpiar correctamente
        mostrarPanelInicio();
        enFormulario = false; // Estado correcto para evitar conflictos
    }
}

private void limpiarEstadoFormulario() {
    // Limpiar campos de los formularios y asegurarse de que no hay formularios activos
    limpiarCampos();
    limpiarCamposmodificados();
    
    // Ocultar todos los formularios que podrían estar visibles
    pane_registrar_informacion.setVisible(false);
    pane_modificar_informacion.setVisible(false);
}


private void mostrarPanelInicio() {
    // Mostrar el panel de inicio y asegurarse de que los paneles relevantes estén ocultos
    anchorPane_inicio.setVisible(true);
    anchor_inicio_ventas.setVisible(true);
    scroll_inicio_meseras.setVisible(false);
    anchor_inventario.setVisible(false);
    // Ocultar otros paneles (como el de configuración)
    anchor_configuracion.setVisible(false);
    pane_configuracion.setVisible(false);  // Aseguramos ocultar la vista de configuración
    pane_inventario.setVisible(false);
    pane_nuevo_producto.setVisible(false);
    pane_reponer_producto.setVisible(false);

}



    
 @FXML
public void handleBtnConfiguracionAction() {

    // Verificar si el usuario está en "Registrar Información" o "Modificar Información"
    if ((pane_registrar_informacion.isVisible() || pane_modificar_informacion.isVisible()) && anchor_configuracion.isVisible()) {
        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            limpiarEstadoFormulario(); // Limpiar antes de cambiar el panel
            mostrarPanelConfiguracion();
            enFormulario = false; // Estado actualizado correctamente
        }
    } else {
        // Si no está en las vistas de "Registrar" o "Modificar", realiza la acción sin advertencia
        limpiarEstadoFormulario(); // Limpiar de todos modos para asegurar consistencia
        mostrarPanelConfiguracion();
        enFormulario = false; // Asegurarse de que no estamos en un formulario
    }
}

private void mostrarPanelConfiguracion() {
    // Mostrar el panel de configuración y asegurarse de que los paneles relevantes estén ocultos
    anchor_configuracion.setVisible(true);
    pane_configuracion.setVisible(true);

    // Ocultar otros paneles que no son relevantes
    anchorPane_inicio.setVisible(false);
    anchor_inicio_ventas.setVisible(false);
    scroll_inicio_meseras.setVisible(false);
    pane_registrar_informacion.setVisible(false); 
    pane_modificar_informacion.setVisible(false);
    pane_personal_roles.setVisible(false);
    anchor_inventario.setVisible(false);
    pane_inventario.setVisible(false);
    pane_nuevo_producto.setVisible(false);
    pane_reponer_producto.setVisible(false);

    // Llamar al método para agregar el efecto 3D si es necesario
    agregarEfecto3D(btn_personal_roles);
    agregarEfecto3D(btn_registro_actividades);
}










    @FXML
public void handleBtnInventarioAction() {
    // Verificar si el usuario está en "Registrar Información" o "Modificar Información"
    if ((pane_registrar_informacion.isVisible() || pane_modificar_informacion.isVisible()) && !anchor_inventario.isVisible()) {
        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Acción para el botón de inventario si confirma
            limpiarEstadoFormulario();
            mostrarPanelInventario();
            enFormulario = false; // Estado actualizado correctamente
        }
    } else {
        // Si no está en los formularios (ya estás en el panel de inventario), cambiar a la vista sin advertencia
        limpiarEstadoFormulario(); // Asegurar consistencia al limpiar
        mostrarPanelInventario();
        enFormulario = false; // Estado correcto para evitar conflictos
    }
}

private void mostrarPanelInventario() {
    // Mostrar el panel de inventario y ocultar los demás paneles no relevantes
    anchor_inventario.setVisible(true);
    pane_inventario.setVisible(true);

    pane_nuevo_producto.setVisible(false);
    pane_reponer_producto.setVisible(false);
    
    // Ocultar otros paneles que no son relevantes
    anchorPane_inicio.setVisible(false);
    anchor_inicio_ventas.setVisible(false);
    scroll_inicio_meseras.setVisible(false);
    anchor_configuracion.setVisible(false);
    pane_configuracion.setVisible(false);
    pane_registrar_informacion.setVisible(false);
    pane_modificar_informacion.setVisible(false);
}

    
    
    
    

    public void handleBtnReportesAction() {
        // Acción para el botón "REPORTES"
    }

    public void handleBtnCajaAction() {
        // Acción para el botón "CAJA"
    }

    private void handleBtnMesasAction() {

    }

    @FXML
    public void handleBtnSalirAction() {
        // Acción para el botón "SALIR"
        limpiarCampos();

        Stage stage = (Stage) btn_salir.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
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

    /*BOTONES DE LA VISTA DE INICIO*/
    @FXML
    public void handleBtnVentasAction() {
        // Acción para el botón "VENTAS"
        // Show the anchor_inicio_ventas container and hide the scroll_inicio_meseras container
        anchor_inicio_ventas.setVisible(true);
        scroll_inicio_meseras.setVisible(false);

        /*try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/Inicio_ventas_administrador.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        limpiarCampos();
    }

    /*BOTONES DE LA VISTA DE INICIO*/
    @FXML
    public void handleBtnMeserasAction() {
        // Acción para el botón "MESAS"
        // Show the scroll_inicio_meseras container and hide the anchor_inicio_ventas container
        anchor_inicio_ventas.setVisible(false);
        scroll_inicio_meseras.setVisible(true);

        limpiarCampos();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/TarjetaMeseraAdm.fxml"));
            loader.setLocation(getClass().getResource("/components/TarjetaMeseraAdm.fxml"));
            Pane tarjetaMeseraPane = loader.load();

            GridPane gridpane_inicio_meseras = (GridPane) scroll_inicio_meseras.getContent();
            /*gridpane_inicio_meseras.setVisible(true); // Ensure the gridpane is visible
            gridpane_inicio_meseras.setManaged(true); // Ensure the gridpane is managed
            gridpane_inicio_meseras.setPrefWidth(300); // Set a non-zero pref width
            gridpane_inicio_meseras.setPrefHeight(300); // Set a non-zero pref height*/

            gridpane_inicio_meseras.getChildren().clear(); // clear the gridpane before adding new content
            gridpane_inicio_meseras.add(tarjetaMeseraPane, 0, 0); // add to the first row and column
            gridpane_inicio_meseras.setConstraints(tarjetaMeseraPane, 0, 0); // Set the constraints explicitly
            gridpane_inicio_meseras.getColumnConstraints().clear();
            gridpane_inicio_meseras.getRowConstraints().clear();

            /*scroll_inicio_meseras.setFitToWidth(true); // Ensure the scrollpane fits to width
            scroll_inicio_meseras.setFitToHeight(true); // Ensure the scrollpane fits to height
            scroll_inicio_meseras.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Show the vertical scrollbar
            scroll_inicio_meseras.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Show the horizontal scrollbar*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
    
    
    /*--------------------------------------------------------------------------*/
    
    
    /* *************************MODULO DE INICIO******************************* */
    
    
    /*--------------------------------------------------------------------------*/
    
    
    
    
    
    /*DIFERENTES ACCIONES QUE CONTIENE LA VISTA DE INICIO*/
    @FXML
    public void cargarPedidosDelDia(Date fecha) {
        try {

            // Crear una instancia de PedidosDAO
            // Aquí 'conn' es de tipo 'java.sql.Connection'
            PedidosDao pedidosDAO = new PedidosDao((com.sun.jdi.connect.spi.Connection) conn);

            // Definir la fecha para la consulta
            // Obtener los pedidos realizados en la fecha especificada
            List<Pedidos> pedidosDelDia = pedidosDAO.obtenerPedidosPorDia(fecha);

            // Mostrar la información de los pedidos
            for (Pedidos pedido : pedidosDelDia) {
                System.out.println("ID Pedido: " + pedido.getIdPedidos());
                System.out.println("ID Empleado: " + pedido.getEmpleadosId());
                System.out.println("ID Mesa: " + pedido.getMesasId());
                System.out.println("Fecha Pedido: " + pedido.getFechaPedido());
                System.out.println("Estado Pedido: " + pedido.getEstadoPedido());
                System.out.println("Precio Total: " + pedido.getPrecioTotal());
                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updatePedidoLabels() {
        Timeline updateTimeline = new Timeline(new KeyFrame(Duration.seconds(86400), event -> {
            try {
                if (conn != null && !conn.isClosed()) {
                    int sumPedido = getSumPedidoForTheDay(conn);
                    Platform.runLater(() -> {
                        if (jlabel_sum_pedido != null) {
                            jlabel_sum_pedido.setText(String.valueOf(sumPedido));
                        } else {
                            System.err.println("jlabel_sum_pedido es null");
                        }
                    });
                } else {
                    System.err.println("La conexión a la base de datos es null o está cerrada.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
        updateTimeline.setCycleCount(Timeline.INDEFINITE);
        updateTimeline.play();
    }

    @FXML
    private int getSumPedidoForTheDay(Connection connection) throws SQLException {
        String query = "SELECT SUM(precioTotal) FROM pedidos WHERE fechaPedido = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().with(LocalTime.MIDNIGHT)));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int sum = resultSet.getInt(1);
            System.out.println("Suma de pedidos del día: " + sum);  // Imprimir el resultado para verificar
            return sum;
        } else {
            System.out.println("No se encontraron pedidos para la fecha.");
            return 0;
        }
    }

    
    
    /*--------------------------------------------------------------------------*/
    
    
    /* *********************MODULO DE CONFIGURACION*************************** */
    
    
    /*--------------------------------------------------------------------------*/
    
    
    
    /*-----DIFERENTES ACCIONES QUE CONTIENE LA VISTA DE CONFIGURACIONES---------*/
    private void agregarEfecto3D(Button button) {
        button.setOnMouseEntered(event -> {
            Pane root = pane_configuracion; // Usamos el pane_configuracion como contenedor

            // Simula otros botones detrás al mover los que están apilados
            for (int i = 0; i < 5; i++) {
                Button virtualBtn = crearBoton(button.getText(), button.getLayoutX(), button.getLayoutY() + (i + 1) * 10);
                virtualBtn.setOpacity(0.3 - (i * 0.05)); // Más transparencia a medida que "retrocede"
                virtualBtn.setDisable(true); // No interactivo
                root.getChildren().add(virtualBtn);
                virtualBtn.toBack(); // Asegurarse de que vaya detrás del botón real

                // Animar el movimiento hacia adelante de forma secuencial
                TranslateTransition tt = new TranslateTransition(Duration.millis(300 + (i * 50)), virtualBtn);
                tt.setByY(-10); // Desplazamos hacia arriba
                tt.play();

                // Eliminar el "botón virtual" al final de la animación
                tt.setOnFinished(e -> root.getChildren().remove(virtualBtn));
            }
        });

        // Restaurar el estado original al salir el mouse (si es necesario)
        button.setOnMouseExited(event -> {
            // Aquí podrías agregar animaciones o restaurar el estado original
        });
    }

    // Método para crear un botón virtual con estilo
    private Button crearBoton(String texto, double x, double y) {
        Button button = new Button(texto);
        button.setStyle("-fx-background-color: linear-gradient(#ffcc00, #ff9900); -fx-text-fill: white; -fx-background-radius: 30px; -fx-border-radius: 30px;");
        button.setPrefSize(500, 60);
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    /*-----------------------BOTON DE PERSONAL Y ROLES------------------------*/
    @FXML
    public void handleBtnPersonalRolesAction() {
        // Acción para el botón "PERSONAL Y ROLES"
        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(true);
        pane_registrar_informacion.setVisible(false);
        /*anchor_configuracion.setVisible(false);*/

    }

    /*BOTON DE VOLVER A CONFIGURACION*/
    @FXML
public void handleBtnVolverConfigAction() {
    // Acción para el botón "PERSONAL Y ROLES"

    

    // Verificar si el panel de registro o el panel de modificar están visibles
    if (pane_registrar_informacion.isVisible() || pane_modificar_informacion.isVisible()) {

        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Aquí realiza la acción que corresponda al botón "VOLVER_CONFIG"
            pane_configuracion.setVisible(true);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(true);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_informacion.setVisible(false);

            limpiarCampos();  // Limpiar todos los campos
            limpiarCamposmodificados();
            enFormulario = false; 
        }
    } else {
        // Si no está en la vista de "Registrar Formulario" o "Modificar Información", realiza la acción normalmente
        pane_configuracion.setVisible(true);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(true);
        pane_registrar_informacion.setVisible(false);
        pane_modificar_informacion.setVisible(false);
         enFormulario = false; // Asegurarse de que no estamos en un formulario
        
        
    }
}

    public void handleBtnRegistroActividadesAction() {
        // Acción para el botón "REGISTRO DE ACTIVIDADES"

    }

    /*---BOTON PARA REGISTRAR INFORMACION EN LA VISTA REGISTRO INFORMACION-----*/
    @FXML
    private void handleBtnRegistrarInformacion() {
        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(true);
        pane_registrar_informacion.setVisible(true);

    }

    @FXML
    private void handleBtnInternoRegistroInformacion() {
        // Intenta registrar el empleado
        boolean registroExitoso = guardarEmpleado(); // Cambia guardarEmpleado() para que devuelva un booleano

        if (registroExitoso) {
            // Solo si el registro fue exitoso, cambiar la visibilidad de los paneles
            pane_configuracion.setVisible(true);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(true);
            anchor_configuracion.setVisible(true);
            pane_registrar_informacion.setVisible(false);

            limpiarCampos(); // Limpia los campos solo después de un registro exitoso
        }
    }

    /*-------------------Subboton para cancelar el registro de la informacion 
    de la vista registro informacion-----------------------------------------*/
    @FXML
    private void handleBtnCancelarRegistroInformacion() {
        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Solo si el usuario confirma, cambia la visibilidad de los paneles
            pane_configuracion.setVisible(true);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(true);
            anchor_configuracion.setVisible(true);
            pane_registrar_informacion.setVisible(false);

            limpiarCampos(); // Limpia los campos solo si se confirma la cancelación
        }
    }

    @FXML
    private void handleBtnModificarInformacion() {

        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(true);
        pane_registrar_informacion.setVisible(false);
        pane_modificar_informacion.setVisible(true);
        tablaEmpleados.getSelectionModel().clearSelection();

        limpiarCampos();
    }

    @FXML
    private boolean guardarEmpleado() {
        EmployeesDao employeesDao = new EmployeesDao();

        // Verificar que todos los campos requeridos estén llenos
        if (txt_Id.getText().isEmpty() || txt_Nombre.getText().isEmpty() || cmb_Cargo.getValue() == null
                || txt_Telefono.getText().isEmpty() || txt_Direccion.getText().isEmpty() || txt_Email.getText().isEmpty()
                || txt_Edad.getText().isEmpty() || txt_Usuario.getText().isEmpty() || txt_Contraseña.getText().isEmpty()) {

            mostrarMensaje("Todos los campos deben estar llenos para registrar la información.");
            return false; // Indica que no se completó el registro
        }

        // Validar que la edad sea de 18 años o más
        try {
            int edad = Integer.parseInt(txt_Edad.getText());
            if (edad < 18) {
                mostrarMensajeLado("La edad debe ser de 18 años o más.", txt_Edad);
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarMensajeLado("La edad debe ser un número válido.", txt_Edad);
            return false;
        }

        // Verificar si el ID o el usuario ya están registrados
        if (employeesDao.existeIdOUsuario(txt_Id.getText(), txt_Usuario.getText())) {
            mostrarMensaje("El ID o el usuario ya están registrados. Deben ser cambiados para continuar.");
            return false;
        }

        // Confirmación antes de guardar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea registrar la información?");
        confirmacion.setContentText("Si acepta, se procederá a registrar la información.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isEmpty() || resultado.get() == ButtonType.CANCEL) {
            return false; // Si cancela, no se realiza el registro
        }

        // Crea la instancia del empleado y procede a guardar
        Employees nuevoEmpleado = new Employees();
        nuevoEmpleado.setIdEmpleados(txt_Id.getText());
        nuevoEmpleado.setNombreEmpleado(txt_Nombre.getText());
        nuevoEmpleado.setRol(cmb_Cargo.getValue());
        nuevoEmpleado.setTelefono(txt_Telefono.getText());
        nuevoEmpleado.setDireccion(txt_Direccion.getText());
        nuevoEmpleado.setEmail(txt_Email.getText());
        nuevoEmpleado.setEdad(Integer.parseInt(txt_Edad.getText()));

        String usuario = txt_Usuario.getText();
        String contrasena = txt_Contraseña.getText();

        if (employeesDao.guardarEmpleadoYSesion(nuevoEmpleado, usuario, contrasena)) {
            mostrarMensaje("Empleado y usuario registrados exitosamente.");
            return true; // Registro exitoso
        } else {
            mostrarMensaje("Error al registrar el empleado o el usuario.");
            return false;
        }
    }

// Método para confirmar la cancelación del formulario
    @FXML
    private void cancelarFormulario() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Cancelar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar?");
        confirmacion.setContentText("Perderá la información que no haya guardado.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Cierra la vista o limpia los campos
            limpiarCampos();
        }
    }

// Método para mostrar mensajes
    @FXML
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para mostrar mensajes al lado del campo de edad
    @FXML
    private void mostrarMensajeLado(String mensaje, TextField campo) {
        Tooltip tooltip = new Tooltip(mensaje);
        tooltip.setAutoHide(true);
        tooltip.show(campo, campo.localToScreen(campo.getBoundsInLocal()).getMinX(),
                campo.localToScreen(campo.getBoundsInLocal()).getMaxY());
    }
    
    @FXML
    private void limpiarCampos() {
        txt_Id.setText("");            // Limpiar el campo de ID
        txt_Nombre.setText("");        // Limpiar el campo de nombre
        cmb_Cargo.setValue(null);      // Limpiar el comboBox de cargo
        // date_FechaContratacion.setValue(null); // Limpiar la fecha de contratación si lo estás usando
        // txt_Salario.setText("");      // Limpiar el campo de salario si lo estás usando
        txt_Telefono.setText("");      // Limpiar el campo de teléfono
        txt_Direccion.setText("");     // Limpiar el campo de dirección
        txt_Email.setText("");         // Limpiar el campo de email
        txt_Edad.setText("");          // Limpiar el campo de edad
        txt_Usuario.setText("");       // Limpiar el campo de usuario
        txt_Contraseña.setText("");    // Limpiar el campo de contraseña
    }

    // Método para capturar la selección del empleado y llenar los campos
    private void cargarDatos(Employees empleado) {
        limpiarCamposmodificados();
        // Llenar los campos de texto con los datos del empleado seleccionado
        txt_modificar_Id.setText(empleado.getIdEmpleados());
        txt_modificar_Nombre.setText(empleado.getNombreEmpleado());
        txt_modificar_Edad.setText(String.valueOf(empleado.getEdad()));
        txt_modificar_Direccion.setText(empleado.getDireccion());
        txt_modificar_Telefono.setText(empleado.getTelefono());
        txt_modificar_Email.setText(empleado.getEmail());
        combo_Cargo.setValue(empleado.getRol());

        // Llenar los campos de sesión
        txt_modificar_Usuario.setText(empleado.getSesion().getUsuario());
        txt_modificar_Contraseña.setText(empleado.getSesion().getContraseña());
    }

    /*CONFIGURACION DE LA VISTA DE MODIFICAR INFORMACION*/
    private void configurarColumnas() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idEmpleados"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCargo.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        colContraseña.setCellValueFactory(new PropertyValueFactory<>("contraseña"));
        // Para los campos que vienen de la clase Sesiones
        colUsuario.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getSesion().getUsuario()));
        colContraseña.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getSesion().getContraseña()));
    }

    @FXML
    private void limpiarCamposmodificados() {
        txt_modificar_Id.clear(); // Limpiar el campo de ID
        txt_modificar_Nombre.clear(); // Limpiar el campo de nombre
        combo_Cargo.setValue(null);      // Limpiar el comboBox de cargo
        // date_FechaContratacion.setValue(null); // Limpiar la fecha de contratación si lo estás usando
        // txt_Salario.setText("");      // Limpiar el campo de salario si lo estás usando
        txt_modificar_Telefono.clear(); //Limpiar el campo de teléfono
        txt_modificar_Direccion.clear(); // Limpiar el campo de dirección
        txt_modificar_Email.clear();         // Limpiar el campo de email
        txt_modificar_Edad.clear();          // Limpiar el campo de edad
        txt_modificar_Usuario.clear();       // Limpiar el campo de usuario
        txt_modificar_Contraseña.clear(); // Limpiar el campo de contraseña
        
    }
    
    @FXML
    private void limpiarCamposmodificados2() {
        txt_modificar_Id.clear(); // Limpiar el campo de ID
        txt_modificar_Nombre.clear(); // Limpiar el campo de nombre
        combo_Cargo.setValue(null);      // Limpiar el comboBox de cargo
        // date_FechaContratacion.setValue(null); // Limpiar la fecha de contratación si lo estás usando
        // txt_Salario.setText("");      // Limpiar el campo de salario si lo estás usando
        txt_modificar_Telefono.clear(); //Limpiar el campo de teléfono
        txt_modificar_Direccion.clear(); // Limpiar el campo de dirección
        txt_modificar_Email.clear();         // Limpiar el campo de email
        txt_modificar_Edad.clear();          // Limpiar el campo de edad
        txt_modificar_Usuario.clear();       // Limpiar el campo de usuario
        txt_modificar_Contraseña.clear(); // Limpiar el campo de contraseña
        tablaEmpleados.getSelectionModel().clearSelection();
        
    }

    @FXML
    private void handleModificarEmpleado() {
        Employees empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();

        if (empleadoSeleccionado != null) {
            // Guardar el ID original antes de la modificación
            String idOriginal = empleadoSeleccionado.getIdEmpleados();
            String nuevoId = txt_modificar_Id.getText();

            // Verificar si el ID ha sido modificado
            if (!idOriginal.equals(nuevoId)) {
                mostrarAlerta(AlertType.ERROR, "Error", "Modificación de ID restringida", "No es posible modificar el ID del empleado.");
                return; // Detener el proceso de modificación
            }

            // Validar que la edad sea mayor o igual a 18
            int edad = Integer.parseInt(txt_modificar_Edad.getText());
            if (edad < 18) {
                mostrarAlerta(AlertType.ERROR, "Error", "Edad no permitida", "La edad permitida debe ser 18 o mayor.");
                return; // Detener el proceso de modificación
            }

            // Continuar con la actualización de los demás campos si el ID no fue modificado y la edad es válida
            empleadoSeleccionado.setNombreEmpleado(txt_modificar_Nombre.getText());
            empleadoSeleccionado.setEdad(edad); // Edad ya validada
            empleadoSeleccionado.setDireccion(txt_modificar_Direccion.getText());
            empleadoSeleccionado.setTelefono(txt_modificar_Telefono.getText());
            empleadoSeleccionado.setEmail(txt_modificar_Email.getText());
            empleadoSeleccionado.setRol(combo_Cargo.getValue());

            // Obtener los valores de usuario y contraseña para la tabla sesion
            String usuarioNuevo = txt_modificar_Usuario.getText();
            String contrasena = txt_modificar_Contraseña.getText();

            // Llamar al DAO para actualizar la base de datos
            EmployeesDao employeesDao = new EmployeesDao();
            boolean actualizado = employeesDao.modificarEmpleadoYSesion(empleadoSeleccionado, usuarioNuevo, contrasena); // Elimina usuarioAntiguo

            if (actualizado) {
                mostrarAlerta(AlertType.INFORMATION, "Éxito", "Empleado actualizado", "La información del empleado ha sido actualizada correctamente.");
                // Refrescar el TableView
                tablaEmpleados.refresh();
            } else {
                mostrarAlerta(AlertType.ERROR, "Error", "Error al actualizar", "No se pudo actualizar la información del empleado.");
            }
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", "Error al modificar", "Por favor selecciona un empleado para modificar.");
        }
    }

    @FXML
    private void handleBorrarEmpleado() {
        Employees empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();

        if (empleadoSeleccionado != null) {
            // Confirmación antes de eliminar
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar eliminación");
            confirmacion.setHeaderText("¿Está seguro de que desea eliminar este empleado?");
            confirmacion.setContentText("Esta acción no se puede deshacer.");

            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Eliminar de la base de datos
                EmployeesDao employeesDao = new EmployeesDao();
                boolean eliminado = employeesDao.eliminarEmpleadoYSesion(empleadoSeleccionado.getIdEmpleados(), empleadoSeleccionado.getSesion().getUsuario());

                if (eliminado) {
                    // Eliminar de la lista y del TableView
                    tablaEmpleados.getItems().remove(empleadoSeleccionado);
                    mostrarAlerta(AlertType.INFORMATION, "Éxito", "Empleado eliminado", "El empleado ha sido eliminado correctamente.");
                } else {
                    mostrarAlerta(AlertType.ERROR, "Error", "Error al eliminar", "No se pudo eliminar al empleado.");
                }
            }
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", "Error al eliminar", "Por favor seleccione un empleado para eliminar.");
        }
    }

    @FXML
    public void mostrarAlerta(AlertType tipo, String titulo, String encabezado, String contenido) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    
    
    
    
    /*--------------------------------------------------------------------------*/
    
    
    /* ***********************MODULO DE INVENTARIO*************************** */
    
    
    /*--------------------------------------------------------------------------*/
    
    
    @FXML
    private void handleBtnNuevoProdAction() {
        
    }
    
    
    @FXML
    private void handleBtnReponerProdAction() {
        
    }

    
    @FXML
    private void handleBtnBuscarProdAction() {
        
    }
    
    
    private void cargarCategorias() {
    CategoriasDao categoriaDao = new CategoriasDao();
    ObservableList<Categorias> listaCategorias = FXCollections.observableArrayList(categoriaDao.obtenerTodasLasCategorias());
    comboBoxCategoria.setItems(listaCategorias);
}
    
    
    
    
}
