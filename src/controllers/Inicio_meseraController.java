package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Employees;
import models.EmployeesDao;
import models.PrincipioDao;
import models.SopaDao;
import models.TipoMenuDao;

public class Inicio_meseraController implements Initializable {

    @FXML
    private Button btn_pedidos;
    
    @FXML
    private ComboBox comboBoxselecMesera; //Lista desplegable de meseras

    @FXML
    private Button btn_menu;

    @FXML
    private Button btn_salir;

    @FXML
    private Label txt_grano;

    @FXML
    private Label txt_verdura;

    @FXML
    private Button btn_mesas;

    @FXML
    private Button btn_inicio;

    @FXML
    private Label popupLabel; //Texto de "MESA X"
    private Mensaje_PedidoController mensajePedidoController;

    @FXML
    private Pane pane_inicio; //Panel de Inicio de mesera

    @FXML
    private Pane glassPane; // El Pane que actúa como GlassPane
    
    @FXML
    private Button btn_arrowMenu;

    @FXML
    private GridPane gridPane; // Contenedor de las mesas

    @FXML
    private ImageView imageView1;

    @FXML
    private VBox vBox_editSopa;
    
     @FXML
    private Pane pane_editMenu;
    
    @FXML
    private Pane pane_menuCarta;
    @FXML
    private Pane pane_menuDia;
    

    @FXML
    private VBox vBox_editPrincipio;

    @FXML
    private Pane glassPane3;

    @FXML
    private Button btn_editSopa;

    @FXML
    private Pane pane_menu;

    @FXML
    private Button btn_editPrincipio;

    @FXML
    private Button btn_cancelarEditSopa;

    @FXML
    private Button btn_aceptarEditSopa;

    @FXML
    private Label txt_sopa;
    private String sopaSeleccionadaActual;

    @FXML
    private Button btn_cancelarEditPrincipio;
    
    @FXML
    private Button btn_editMenu;
    
    @FXML
    private Button btn_editDia;
    
    @FXML
    private Button btn_editCarta;
    
    @FXML
    private Pane pane_editCarta;
    
    @FXML
    private Pane pane_editDia;

    @FXML
    private Button btn_aceptarEditPrincipio;

    @FXML
    private Label txt_cantidad;  // Label para mostrar la cantidad
    private int cantidad = 0;  // Cantidad inicial

    @FXML
    private ComboBox<String> menuComboBox;  // El ComboBox que contiene los menús

    @FXML
    private ComboBox<String> comboBoxGranos;

    @FXML
    private ComboBox<String> comboBoxVerduras;

    @FXML
    private ComboBox<String> sopasComboBox;
    private SopaDao sopaDao = new SopaDao();
    private PrincipioDao principioDao;

    @FXML
    private Button btn_tomarPedido;

    private TipoMenuDao tipoMenuDao = new TipoMenuDao();

    public Inicio_meseraController() {
    }

    public Inicio_meseraController(Button btn_pedidos, ListView plato_dia, ListView plato_carta, ComboBox comboBoxselecMesera, Button btn_menu, Button btn_salir, Label txt_grano, Label txt_verdura, Button btn_mesas, Button btn_inicio, Label popupLabel, Mensaje_PedidoController mensajePedidoController, Pane pane_inicio, Pane glassPane, Button btn_arrowMenu, GridPane gridPane, ImageView imageView1, VBox vBox_editSopa, Pane pane_editMenu, Pane pane_menuCarta, Pane pane_menuDia, VBox vBox_editPrincipio, Pane glassPane3, Button btn_editSopa, Pane pane_menu, Button btn_editPrincipio, Button btn_cancelarEditSopa, Button btn_aceptarEditSopa, Label txt_sopa, String sopaSeleccionadaActual, Button btn_cancelarEditPrincipio, Button btn_editMenu, Button btn_editDia, Button btn_editCarta, Pane pane_editCarta, Pane pane_editDia, Button btn_aceptarEditPrincipio, Label txt_cantidad, ComboBox<String> menuComboBox, ComboBox<String> comboBoxGranos, ComboBox<String> comboBoxVerduras, ComboBox<String> sopasComboBox, PrincipioDao principioDao, Button btn_tomarPedido) {
        this.btn_pedidos = btn_pedidos;
        this.comboBoxselecMesera = comboBoxselecMesera;
        this.btn_menu = btn_menu;
        this.btn_salir = btn_salir;
        this.txt_grano = txt_grano;
        this.txt_verdura = txt_verdura;
        this.btn_mesas = btn_mesas;
        this.btn_inicio = btn_inicio;
        this.popupLabel = popupLabel;
        this.mensajePedidoController = mensajePedidoController;
        this.pane_inicio = pane_inicio;
        this.glassPane = glassPane;
        this.btn_arrowMenu = btn_arrowMenu;
        this.gridPane = gridPane;
        this.imageView1 = imageView1;
        this.vBox_editSopa = vBox_editSopa;
        this.pane_editMenu = pane_editMenu;
        this.pane_menuCarta = pane_menuCarta;
        this.pane_menuDia = pane_menuDia;
        this.vBox_editPrincipio = vBox_editPrincipio;
        this.glassPane3 = glassPane3;
        this.btn_editSopa = btn_editSopa;
        this.pane_menu = pane_menu;
        this.btn_editPrincipio = btn_editPrincipio;
        this.btn_cancelarEditSopa = btn_cancelarEditSopa;
        this.btn_aceptarEditSopa = btn_aceptarEditSopa;
        this.txt_sopa = txt_sopa;
        this.sopaSeleccionadaActual = sopaSeleccionadaActual;
        this.btn_cancelarEditPrincipio = btn_cancelarEditPrincipio;
        this.btn_editMenu = btn_editMenu;
        this.btn_editDia = btn_editDia;
        this.btn_editCarta = btn_editCarta;
        this.pane_editCarta = pane_editCarta;
        this.pane_editDia = pane_editDia;
        this.btn_aceptarEditPrincipio = btn_aceptarEditPrincipio;
        this.txt_cantidad = txt_cantidad;
        this.menuComboBox = menuComboBox;
        this.comboBoxGranos = comboBoxGranos;
        this.comboBoxVerduras = comboBoxVerduras;
        this.sopasComboBox = sopasComboBox;
        this.principioDao = principioDao;
        this.btn_tomarPedido = btn_tomarPedido;
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configura eventos para los botones
        btn_tomarPedido.setOnAction(e -> handlePedidoClick());
        btn_pedidos.setOnAction(e -> handleButton1Click());
        btn_mesas.setOnAction(e -> handleButton2Click());
        btn_menu.setOnAction(e -> handleButtonMenu());
        btn_inicio.setOnAction(e -> handleButtonInicio());
        btn_salir.setOnAction(e -> handleButton5Click());
        btn_editPrincipio.setOnAction(e -> handleButtonEditPrincipio());
        btn_cancelarEditPrincipio.setOnAction(e -> handleButtoncancelarEditPrincipio());
        btn_editSopa.setOnAction(e -> handleButtonEditSopa());
        btn_cancelarEditSopa.setOnAction(e -> handleButtoncancelarEditSopa());
        cargarSopas();
        
        btn_editMenu.setOnAction(e -> handleButtonEditMenu());
        cargarSopas();


        btn_aceptarEditSopa.setOnAction(event -> mostrarSopaSeleccionada());
        btn_aceptarEditPrincipio.setOnAction(event -> mostrarPrincipioSeleccionado());
        btn_editDia.setOnAction(event -> editarMenuDia());
        btn_editCarta.setOnAction(event -> editarMenuCarta());
        
        glassPane3.setVisible(false);
        pane_editMenu.setVisible(false);
        
        pane_menu.setVisible(false);
        pane_menuCarta.setVisible(false);
        pane_menuDia.setVisible(false);

        // Ocultar el GlassPane al iniciar la aplicación
        glassPane.setVisible(false);

        // Otros componentes de la interfaz pueden ser inicializados aquí
        pane_inicio.setVisible(true);
        
        principioDao = new PrincipioDao();

        initComboBox();
        CargarVerduras();
        CargarGranos();

        btn_tomarPedido.setOnAction(e -> handlePedidoClick());

        menuComboBox.setItems(tipoMenuDao.getAllMenus());

        imageView1.setImage(new Image("/resources/pepitoria.png"));
        makeImageViewCircular(imageView1, 50);

    }

    private void handleButton5Click() {
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

    @FXML
    private void incrementQuantity() {
        cantidad++;
        displayQuantity();
    }

    @FXML
    private void decrementQuantity() {
        if (cantidad > 0) {
            cantidad--;
        }
        displayQuantity();
    }

    private void displayQuantity() {
        txt_cantidad.setText(String.valueOf(cantidad));
    }

    private void handleButton1Click() {
        // Traer el botón al frente
        btn_pedidos.toFront();
        gridPane.setVisible(false);

        // Intercambiar colores entre btn_pedidos y btn_mesas
        switchButtonStyles(btn_pedidos, btn_mesas);

    }

    private void handleButton2Click() {
        // Traer el botón al frente
        btn_mesas.toFront();
        gridPane.setVisible(true);

        // Intercambiar colores entre btn_mesas y btn_pedidos
        switchButtonStyles(btn_mesas, btn_pedidos);

    }

// Método para intercambiar estilos entre dos botones
    private void switchButtonStyles(Button btn1, Button btn2) {
        // Guarda el estilo actual de btn1
        String btn1Style = btn1.getStyleClass().contains("btn_pedidos") ? "btn_pedidos" : "btn_mesas";
        // Guarda el estilo actual de btn2
        String btn2Style = btn2.getStyleClass().contains("btn_pedidos") ? "btn_pedidos" : "btn_mesas";

        // Intercambia estilos entre btn1 y btn2
        btn1.getStyleClass().remove(btn1Style);
        btn1.getStyleClass().add(btn2Style);

        btn2.getStyleClass().remove(btn2Style);
        btn2.getStyleClass().add(btn1Style);
    }

    private void handleButtonMenu() { //Acción para que al oprimir btn_menu se oculte el pane_inicio
        pane_inicio.setVisible(false);
        pane_menu.setVisible(true);
        pane_editMenu.setVisible(false);
       
    }
    
    private void handleButtonArrow() {
        pane_editMenu.setVisible(true);
    }

    private void handleButtonInicio() { // Acción para que al oprimir btn_inicio el pane_inicio sea visible
        pane_inicio.setVisible(true);
        pane_menu.setVisible(false);
        pane_editMenu.setVisible(false);
        pane_menuDia.setVisible(false);
        pane_menuCarta.setVisible(false);
    }

    private void handleButtonEditPrincipio() {
        glassPane3.setVisible(true);
        vBox_editSopa.setVisible(false);
        vBox_editPrincipio.setVisible(true);
    }

    private void handleButtoncancelarEditPrincipio() {
        glassPane3.setVisible(false);
    }
    
    private void handleButtonEditMenu(){
        pane_editMenu.setVisible(true);
    }

    private void handleButtonEditSopa() {
        glassPane3.setVisible(true);
        vBox_editPrincipio.setVisible(false);
        vBox_editSopa.setVisible(true);
    }

    private void handleButtoncancelarEditSopa() {
        glassPane3.setVisible(false);
    }
    
    private void editarMenuDia(){
        pane_menuDia.setVisible(true);
    }
    
     private void editarMenuCarta(){
         pane_menuCarta.setVisible(true);
    }
   

    @FXML
    private void showPopup() {
        // Restablecer la cantidad a 0
        cantidad = 0;
        displayQuantity();

        // Restablecer el ComboBox al estado inicial
        comboBoxselecMesera.getSelectionModel().
                clearSelection(); // Deseleccionar cualquier selección
        comboBoxselecMesera.setValue(null); // Asegúrate de que no hay ningún valor seleccionado

        // Forzar la actualización del promptText
        Platform.runLater(() -> {
            comboBoxselecMesera.setPromptText("Seleccione una mesera");
        });

        // Mostrar el GlassPane y el contenido del popup
        glassPane.setVisible(true);
    }

    @FXML
    private void closePopup() {
        // Ocultar el GlassPane y el contenido del popup
        glassPane.setVisible(false);

    }

    @FXML
    private void handleMesaClickAndShowPopup(MouseEvent event) {
        ImageView clickedMesa = (ImageView) event.getSource();
        String mesaId = clickedMesa.getId();
        String tableNumber = mesaId.replace("mesa", "MESA ");

        // Mostrar el número de la mesa en el popup
        popupLabel.setText(tableNumber);

        // Pasar el número de mesa a Mensaje_PedidoController
        if (mensajePedidoController != null) {
            mensajePedidoController.mostrarNumeroMesa(tableNumber);
        }

        // Mostrar el GlassPane con el popup
        showPopup();
    }

    private void cargarMensajePedidoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/Mensaje_Pedido.fxml"));
            Parent root = loader.load();
            mensajePedidoController = loader.getController();

            // Mostrar la nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComboBox() {
        EmployeesDao employeesDao = new EmployeesDao();
        ObservableList<Employees> meseras = employeesDao.getMeseras();
        comboBoxselecMesera.setItems(meseras);

        comboBoxselecMesera.setPromptText("Seleccione una mesera");

        // Configurar el ComboBox para mostrar el nombre del empleado
        comboBoxselecMesera.setCellFactory(new Callback<ListView<Employees>, ListCell<Employees>>() {
            @Override
            public ListCell<Employees> call(ListView<Employees> param) {
                return new ListCell<Employees>() {
                    @Override
                    protected void updateItem(Employees employee, boolean empty) {
                        super.updateItem(employee, empty);
                        if (employee != null && !empty) {
                            setText(employee.getNombreEmpleado()); // Asume que `getNombre()` devuelve el nombre del empleado
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

// Sobrescribe la celda que se muestra en el botón del ComboBox
        comboBoxselecMesera.setButtonCell(new ListCell<Employees>() {
            @Override
            protected void updateItem(Employees employee, boolean empty) {
                super.updateItem(employee, empty);
                if (employee != null && !empty) {
                    setText(employee.getNombreEmpleado()); // Aquí también muestra el nombre en el botón
                } else {
                    setText(null);
                }

            }
        });

    }

    @FXML
    private void handlePedidoClick() {
        try {
            // Obtener el menú seleccionado en el ComboBox
            String menuSeleccionado = menuComboBox.getSelectionModel().getSelectedItem();

            if (menuSeleccionado != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/Mensaje_Pedido.fxml"));
                Parent root = loader.load();

                // Obtener el controlador de la segunda vista
                Mensaje_PedidoController mensajePedidoController = loader.getController();

                // Pasar el menú seleccionado al controlador de la segunda vista
                mensajePedidoController.setMenuSeleccionado(menuSeleccionado);
                mensajePedidoController.mostrarNumeroMesa(popupLabel.getText());

                // Cambiar la escena
                Scene scene = new Scene(root);
                Stage stage = (Stage) btn_tomarPedido.getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } else {
                System.out.println("Debe seleccionar un menú.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeImageViewCircular(ImageView imageView, double radius) {
        // Crear un círculo que será usado como clip
        Circle clip = new Circle(radius, radius, radius);
        imageView.setClip(clip);

        // Preservar la relación de aspecto
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);  // Suaviza la imagen al redimensionar

        imageView.setFitWidth(100);  // Ancho de la imagen
        imageView.setFitHeight(100); // Altura de la imagen

        // Asignar una clase CSS común 
        imageView.getStyleClass().add("circular-image");
    }

    private void cargarSopas() {
        ObservableList<String> sopasList = sopaDao.getAllSopas();
        sopasComboBox.setItems(sopasList);
    }

    private void mostrarSopaSeleccionada() {
        String sopaSeleccionada = sopasComboBox.getValue();

        if (sopaSeleccionada != null) {
            sopaSeleccionadaActual = sopaSeleccionada;
            glassPane3.setVisible(false);
            txt_sopa.setText(sopaSeleccionada.toUpperCase());

        } else if (sopaSeleccionadaActual != null) {
            txt_sopa.setText(sopaSeleccionadaActual.toUpperCase());

        } else {
            txt_sopa.setText("Por favor, selecciona una sopa.");
        }
    }

    private void CargarVerduras() {
        ObservableList<String> verdurasList = principioDao.getAllVerduras();
        comboBoxVerduras.setItems(verdurasList);
    }

    private void CargarGranos() {
        ObservableList<String> granosList = principioDao.getAllGranos();
        comboBoxGranos.setItems(granosList);
    }

    private void mostrarPrincipioSeleccionado() {
        String verduraSeleccionada = comboBoxVerduras.getValue();
        String granoSeleccionado = comboBoxGranos.getValue();

        if (verduraSeleccionada != null) {
            glassPane3.setVisible(false);
            txt_verdura.setText(verduraSeleccionada.toUpperCase());

        } else {
            txt_verdura.setText("Seleccione una verdura");

        }
        if (granoSeleccionado != null) {
            txt_grano.setText(granoSeleccionado.toUpperCase());
        } else {
            txt_verdura.setText("Seleccione un grano");
        }
    }
    
    private void prueba(){
       txt_verdura.setText("Seleccione una verdura");
    }
}
