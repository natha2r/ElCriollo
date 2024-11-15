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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.CategoriaPlatosDao;
import models.Employees;
import models.EmployeesDao;
import models.Platos;
import models.PrincipioDao;
import models.SopaDao;
import models.TipoMenuDao;

public class Inicio_meseraController implements Initializable {

    //lISTA DE BOTONES
    @FXML
    private Button btn_pedidos;
    @FXML
    private Button btn_menu;
    @FXML
    private Button btn_salir;
    @FXML
    private Button btn_inicio;
    @FXML
    private Button btn_arrowMenu;
    @FXML
    private Button btn_arrowMenu2;
    @FXML
    private Button btn_editSopa;
    @FXML
    private Button btn_arrowMenu1;
    @FXML
    private Button btn_editPrincipio;
    @FXML
    private Button btn_cancelarEditSopa;
    @FXML
    private Button btn_aceptarEditSopa;
    @FXML
    private Button btn_cancelarEditPrincipio;
    @FXML
    private Button btn_editMenu;
    @FXML
    private Button btn_editDia;
    @FXML
    private Button btn_editCarta;
    @FXML
    private Button btn_aceptarEditPrincipio;
    @FXML
    private Button btn_tomarPedido;
    @FXML
    private Button btn_mesas;

    //lISTA DE PANE
    @FXML
    private Pane pane_editMenu;
    @FXML
    private Pane pane_menuCarta;
    @FXML
    private Pane pane_menuDia;
    @FXML
    private Pane pane_inicio; //Panel de Inicio de mesera
    @FXML
    private Pane glassPane; // El Pane que actúa como GlassPane
    @FXML
    private Pane pane_editCarta;
    @FXML
    private Pane pane_editDia;
    @FXML
    private Pane glassPane3;
    @FXML
    private Pane pane_menu;

    // LISTA DE COMBOBOX
    @FXML
    private ComboBox comboBoxselecMesera; //Lista desplegable de meseras
    @FXML
    private ComboBox<String> menuComboBox;  // El ComboBox que contiene los menús
    @FXML
    private ComboBox<String> comboBoxGranos;
    @FXML
    private ComboBox<String> comboBoxVerduras;
    @FXML
    private ComboBox<String> sopasComboBox;

    //OTROS
    @FXML
    private TableView<Platos> tablaPlatos;
    @FXML
    private TableColumn<Platos, String> columnaPlatos;
    @FXML
    private TableColumn<Platos, String> columnaPrecio;
    @FXML
    private TableColumn<Platos, CheckBox> columnaMini;
    @FXML
    private ListView<String> listViewCategorias;
    @FXML
    private ListView<String> listViewCategoriasC;
    @FXML
    private Label popupLabel; //Texto de "MESA X"
    @FXML
    private ImageView imageView1;

    //PANEL DE MENÚ
    @FXML
    private VBox vBox_editPrincipio;
    @FXML
    private Label txt_grano;
    @FXML
    private Label txt_verdura;
    @FXML
    private Label txt_sopa;

    private Mensaje_PedidoController mensajePedidoController;
    @FXML
    private GridPane gridPane; // Contenedor de las mesas
    @FXML
    private VBox vBox_editSopa;

    @FXML
    private Label txt_cantidad;  // Label para mostrar la cantidad
    private int cantidad = 0;  // Cantidad inicial
    private SopaDao sopaDao = new SopaDao();
    private PrincipioDao principioDao;
    private TipoMenuDao tipoMenuDao = new TipoMenuDao();
    private CategoriaPlatosDao categoriaPlatosDao = new CategoriaPlatosDao();
    private String sopaSeleccionadaActual;

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
        btn_editMenu.setOnAction(e -> handleButtonEditMenu());
        btn_aceptarEditSopa.setOnAction(event -> mostrarSopaSeleccionada());
        btn_aceptarEditPrincipio.setOnAction(event -> mostrarPrincipioSeleccionado());
        btn_editDia.setOnAction(event -> editarMenuDia());
        btn_editCarta.setOnAction(event -> editarMenuCarta());
        btn_arrowMenu.setOnAction(event -> handlearrowMenu());
        btn_arrowMenu1.setOnAction(event -> handlearrowMenu());
        btn_arrowMenu2.setOnAction(event -> handlearrowMenu2());
        btn_tomarPedido.setOnAction(e -> handlePedidoClick());

        initComboBox();
        CargarVerduras();
        CargarGranos();
        cargarSopas();

        glassPane.setVisible(false);
        glassPane3.setVisible(false);
        pane_editMenu.setVisible(false);
        pane_menu.setVisible(false);
        pane_menuCarta.setVisible(false);
        pane_menuDia.setVisible(false);
        pane_inicio.setVisible(true);

        principioDao = new PrincipioDao();
        menuComboBox.setItems(tipoMenuDao.getAllMenus());

    }

    private void handleButton5Click() {
        Stage stage = (Stage) btn_salir.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/LoginView.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setStage(stage);

            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleButton1Click() {
        btn_pedidos.toFront();
        gridPane.setVisible(false);
        switchButtonStyles(btn_pedidos, btn_mesas);
    }

    private void handleButton2Click() {
        btn_mesas.toFront();
        gridPane.setVisible(true);
        switchButtonStyles(btn_mesas, btn_pedidos);

    }

    private void switchButtonStyles(Button btn1, Button btn2) {

        String btn1Style = btn1.getStyleClass().contains("btn_pedidos") ? "btn_pedidos" : "btn_mesas";
        String btn2Style = btn2.getStyleClass().contains("btn_pedidos") ? "btn_pedidos" : "btn_mesas";

        btn1.getStyleClass().remove(btn1Style);
        btn1.getStyleClass().add(btn2Style);

        btn2.getStyleClass().remove(btn2Style);
        btn2.getStyleClass().add(btn1Style);
    }

    private void handleButtonMenu() {
        pane_inicio.setVisible(false);
        pane_menu.setVisible(true);
        pane_editMenu.setVisible(false);
        pane_menuCarta.setVisible(false);
        pane_menuDia.setVisible(false);

    }

    private void handlearrowMenu() {
        pane_editMenu.setVisible(true);
        pane_menuDia.setVisible(false);
        pane_menuCarta.setVisible(false);
    }

    private void handlearrowMenu2() {
        pane_editMenu.setVisible(false);
        pane_menuDia.setVisible(false);
        pane_menuCarta.setVisible(false);
    }

    private void handleButtonInicio() {
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

    private void handleButtonEditMenu() {
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

    @FXML
    private void showPopup() {

        comboBoxselecMesera.getSelectionModel().
                clearSelection();
        comboBoxselecMesera.setValue(null);

        Platform.runLater(() -> {
            comboBoxselecMesera.setPromptText("Seleccione una mesera");
        });

        glassPane.setVisible(true);
    }

    @FXML
    private void closePopup() {
        glassPane.setVisible(false);

    }

    @FXML
    private void handleMesaClickAndShowPopup(MouseEvent event) {
        ImageView clickedMesa = (ImageView) event.getSource();
        String mesaId = clickedMesa.getId();
        String tableNumber = mesaId.replace("mesa", "MESA ");

        popupLabel.setText(tableNumber);


        if (mensajePedidoController != null) {
            mensajePedidoController.mostrarNumeroMesa(tableNumber);
        }

        showPopup();
    }

    private void cargarMensajePedidoView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/Mensaje_Pedido.fxml"));
            Parent root = loader.load();
            mensajePedidoController = loader.getController();

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

        comboBoxselecMesera.setCellFactory(new Callback<ListView<Employees>, ListCell<Employees>>() {
            @Override
            public ListCell<Employees> call(ListView<Employees> param) {
                return new ListCell<Employees>() {
                    @Override
                    protected void updateItem(Employees employee, boolean empty) {
                        super.updateItem(employee, empty);
                        if (employee != null && !empty) {
                            setText(employee.getNombreEmpleado()); 
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        comboBoxselecMesera.setButtonCell(new ListCell<Employees>() {
            @Override
            protected void updateItem(Employees employee, boolean empty) {
                super.updateItem(employee, empty);
                if (employee != null && !empty) {
                    setText(employee.getNombreEmpleado());
                } else {
                    setText(null);
                }

            }
        });

    }

    @FXML
    private void handlePedidoClick() {
        try {
   
            String menuSeleccionado = menuComboBox.getSelectionModel().getSelectedItem();

            if (menuSeleccionado != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/Mensaje_Pedido.fxml"));
                Parent root = loader.load();

          
                Mensaje_PedidoController mensajePedidoController = loader.getController();

                mensajePedidoController.setMenuSeleccionado(menuSeleccionado);
                mensajePedidoController.mostrarNumeroMesa(popupLabel.getText());

  
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

    private void editarMenuDia() {
        pane_menuDia.setVisible(true);
        String categoria = "Menú del Día";
        ObservableList<String> categorias = categoriaPlatosDao.getCategoriasByMenuM(categoria);

        listViewCategorias.getSelectionModel().clearSelection();
        listViewCategorias.setItems(categorias);

        System.out.println("Tamaño de categorías: " + categorias.size());
    }

    private void editarMenuCarta() {
        pane_menuCarta.setVisible(true);
        String categoria = "Platos a la Carta";
        ObservableList<String> categorias = categoriaPlatosDao.getCategoriasByMenuM(categoria);

        listViewCategoriasC.getSelectionModel().clearSelection();
        listViewCategoriasC.setItems(categorias);
        System.out.println("Tamaño de categorías: " + categorias.size());
    }

}
