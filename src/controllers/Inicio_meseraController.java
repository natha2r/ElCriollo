package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import models.CategoriaPlatosDao;
import models.Employees;
import models.EmployeesDao;
import models.MesasDao;
import models.Platos;
import models.PlatosDao;
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
    @FXML
    private Button btnAgregarDia, btnEliminarDia, btnAgregarCarta, btnEliminarCarta;

    //lISTA DE PANE
    @FXML
    private Pane pane_editMenu;
    @FXML
    private Pane pane_menuCarta;
    @FXML
    private Pane pane_menuDia;
    @FXML
    private Pane pane_inicio;
    @FXML
    private Pane glassPane;
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
    private ComboBox comboBoxselecMesera;
    @FXML
    private ComboBox<String> menuComboBox;
    @FXML
    private ComboBox<String> comboBoxGranos;
    @FXML
    private ComboBox<String> comboBoxVerduras;
    @FXML
    private ComboBox<String> sopasComboBox;

    //OTROS
    @FXML
    private TableView<Platos> tablaPlatosDia;
    @FXML
    private TableView<Platos> tablaPlatosCarta;
    @FXML
    private TableColumn<Platos, String> columnaNombrePlatoDia;
    @FXML
    private TableColumn<Platos, Double> columnaPrecioDia;
    @FXML
    private TableColumn<Platos, CheckBox> columnaEsMiniDia;
    @FXML
    private TableColumn<Platos, String> columnaNombrePlatoCarta;
    @FXML
    private TableColumn<Platos, Double> columnaPrecioCarta;
    @FXML
    private TableColumn<Platos, CheckBox> columnaEsMiniCarta;
    @FXML
    private ListView<String> listViewCategorias;
    @FXML
    private ListView<String> listViewCategoriasC;
    @FXML
    private Label popupLabel;
    @FXML
    private TextField txtNombrePlatoDia; // Campo de texto para el nombre del plato.
    @FXML
    private TextField txtPrecioPlatoDia;
    @FXML
    private TextField txtNombrePlatoCarta; // Campo de texto para el nombre del plato.
    @FXML
    private TextField txtPrecioPlatoCarta;
    @FXML
    private GridPane gridPaneMesas;

    //PANEL DE MENÚ
    @FXML
    private VBox vBox_editPrincipio;
    @FXML
    private Label txt_grano;
    @FXML
    private Label txt_verdura;
    @FXML
    private Label txt_sopa;
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox vBox_editSopa;

    private SopaDao sopaDao = new SopaDao();
    private PrincipioDao principioDao;
    private TipoMenuDao tipoMenuDao = new TipoMenuDao();
    private CategoriaPlatosDao categoriaPlatosDao = new CategoriaPlatosDao();
    private Mensaje_PedidoController mensajePedidoController;
    private String sopaSeleccionadaActual;
    //private PlatosDao platosDao;
    private ObservableList<Platos> listaPlatosDia = FXCollections.observableArrayList();
    private ObservableList<Platos> listaPlatosCarta = FXCollections.observableArrayList();
    private PlatosDao platosDao = new PlatosDao();
    private TableView<Platos> tablaActual;
    private ListView<String> listViewCategoriasActual;
    private ObservableList<Platos> listaPlatosActual;

    private int mesaCounter = 1; // Inicia en 17 ya que tienes hasta la 16

    // --------------------------
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
        this.menuComboBox = menuComboBox;
        this.comboBoxGranos = comboBoxGranos;
        this.comboBoxVerduras = comboBoxVerduras;
        this.sopasComboBox = sopasComboBox;
        this.principioDao = principioDao;
        this.btn_tomarPedido = btn_tomarPedido;
    }

    private void configurarContexto(TableView<Platos> tabla, ListView<String> listView, ObservableList<Platos> lista) {
        this.tablaActual = tabla;
        this.listViewCategoriasActual = listView;
        this.listaPlatosActual = lista;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //btnAgregarPlato.setOnAction(e -> agregarPlato());
        //btnEliminarPlato.setOnAction(e -> eliminarPlato());
        glassPane.setVisible(false);
        glassPane3.setVisible(false);

        pane_editMenu.setVisible(false);
        pane_menu.setVisible(false);
        pane_menuCarta.setVisible(false);
        pane_menuDia.setVisible(false);
        pane_inicio.setVisible(true);

        gridPaneMesas.getColumnConstraints().clear();
        gridPaneMesas.getRowConstraints().clear();
        gridPaneMesas.getChildren().clear();

        principioDao = new PrincipioDao();
        platosDao = new PlatosDao();
        menuComboBox.setItems(tipoMenuDao.getAllMenus());

        configurarTabla(tablaPlatosDia, columnaNombrePlatoDia, columnaPrecioDia, columnaEsMiniDia);
        configurarTabla(tablaPlatosCarta, columnaNombrePlatoCarta, columnaPrecioCarta, columnaEsMiniCarta);

        buttons();
        initComboBox();
        CargarVerduras();
        CargarGranos();
        cargarSopas();

        cargarMesasDesdeBaseDeDatos();

    }

    private void cargarMesasDesdeBaseDeDatos() {
        try {
            MesasDao mesasDao = new MesasDao();
            List<String> mesas = mesasDao.obtenerTodasLasMesas();

            for (String mesaId : mesas) {
                agregarMesaAGridPane(mesaId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void agregarMesaAGridPane(String mesaId) {
        StackPane mesaContainer = new StackPane();
        mesaContainer.setAlignment(Pos.CENTER);

        // Crear la imagen de la mesa
        ImageView mesaImage = new ImageView(new Image("resources/mesaoficial.png"));
        mesaImage.setFitWidth(106);
        mesaImage.setFitHeight(98);

        // Aquí usamos el ID de la mesa real
        int numeroMesa = mesaCounter; // Usa el contador para la visualización si es necesario

        // Crear el número de la mesa y colocarlo encima de la imagen
        Label lblMesaNumero = new Label(String.valueOf(numeroMesa));
        lblMesaNumero.setStyle("-fx-font-size: 16px; -fx-text-fill: #000000; -fx-font-weight: bold;");
        StackPane.setAlignment(lblMesaNumero, Pos.TOP_CENTER);

        // Agregar la imagen y el número al contenedor
        mesaContainer.getChildren().addAll(mesaImage, lblMesaNumero);

        // Calcular la posición de la nueva mesa en el GridPane
        int maxColumns = 4;
        int row = (mesaCounter - 1) / maxColumns;
        int col = (mesaCounter - 1) % maxColumns;

        // Asignar evento de clic al StackPane con el ID de la mesa real
        mesaContainer.setOnMouseClicked(event -> handleMesaClickAndShowPopup(mesaId)); // Usamos el ID real de la mesa
        mesaContainer.setOnMouseEntered(event -> mesaContainer.setCursor(Cursor.HAND));
        mesaContainer.setOnMouseExited(event -> mesaContainer.setCursor(Cursor.DEFAULT));

        gridPaneMesas.add(mesaContainer, col, row);

        // Incrementar el contador de mesas
        mesaCounter++;
    }

    private void buttons() {
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

        btnAgregarDia.setOnAction(event -> {
            configurarContexto(tablaPlatosDia, listViewCategorias, listaPlatosDia);
            agregarPlato(tablaPlatosDia, listViewCategorias, listaPlatosDia, txtNombrePlatoDia, txtPrecioPlatoDia);
        });

        btnEliminarDia.setOnAction(event -> {
            configurarContexto(tablaPlatosDia, listViewCategorias, listaPlatosDia);
            eliminarPlato();
        });

        btnAgregarCarta.setOnAction(event -> {
            configurarContexto(tablaPlatosCarta, listViewCategoriasC, listaPlatosCarta);
            agregarPlato(tablaPlatosCarta, listViewCategoriasC, listaPlatosCarta, txtNombrePlatoCarta, txtPrecioPlatoCarta);
        });

        btnEliminarCarta.setOnAction(event -> {
            configurarContexto(tablaPlatosCarta, listViewCategoriasC, listaPlatosCarta);
            eliminarPlato();
        });

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
        gridPaneMesas.setVisible(false);
        switchButtonStyles(btn_pedidos, btn_mesas);
    }

    private void handleButton2Click() {
        btn_mesas.toFront();
        gridPaneMesas.setVisible(true);
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
    private void handleMesaClickAndShowPopup(String mesaId) {
        System.out.println("Mesa clickeada: " + mesaId);

        String tableNumber = "Mesa " + mesaId;

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

    public Employees getSelectedMesera() {
        return (Employees) comboBoxselecMesera.getValue();
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
                Employees meseraSeleccionada = getSelectedMesera();

                // Obtener el ID de la mesa
                String idMesa = popupLabel.getText(); // o el método que uses para obtener el ID de la mesa

                // Pasar el ID de la mesa al controlador
                mensajePedidoController.setIdMesa(idMesa);
                if (meseraSeleccionada != null) {
                    // Pasar el ID de la mesera al controlador de Mensaje_Pedido
                    mensajePedidoController.setIdMesera(meseraSeleccionada.getIdEmpleados());
                } else {
                    System.out.println("Debe seleccionar una mesera.");
                }

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

    // -------------------------------------------------------------------------------------------------------------
    private void editarMenuCarta() {
        pane_menuCarta.setVisible(true);
        String categoria = "Platos a la Carta";
        ObservableList<String> categorias = categoriaPlatosDao.getCategoriasByMenuM(categoria);

        listViewCategoriasC.getSelectionModel().clearSelection();
        listViewCategoriasC.setItems(categorias);
        tablaPlatosCarta.setPlaceholder(new Label("Seleccione una categoría para ver los platos."));
        System.out.println("Tamaño de categorías: " + categorias.size());

        // Agregar listener para cargar platos al seleccionar una categoría
        listViewCategoriasC.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarPlatosPorCategoria(newValue, tablaPlatosCarta);
            } else {
                tablaPlatosCarta.setItems(FXCollections.observableArrayList());
            }
        });
    }

    private void editarMenuDia() {
        pane_menuDia.setVisible(true);
        String categoria = "Menú del Día";
        ObservableList<String> categorias = categoriaPlatosDao.getCategoriasByMenuM(categoria);

        listViewCategorias.getSelectionModel().clearSelection();
        listViewCategorias.setItems(categorias);
        tablaPlatosDia.setPlaceholder(new Label("Seleccione una categoría para ver los platos."));
        System.out.println("Tamaño de categorías: " + categorias.size());

        listViewCategorias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarPlatosPorCategoria(newValue, tablaPlatosDia);
            } else {
                tablaPlatosDia.setItems(FXCollections.observableArrayList()); // Vacía la tabla si no hay categoría seleccionada
            }
        });
    }

    private void cargarPlatosPorCategoria(String categoriaSeleccionada, TableView<Platos> tabla) {

        List<Platos> listaPlatos = platosDao.getPlatosByCategoriaC(categoriaSeleccionada);
        ObservableList<Platos> platosObservable = FXCollections.observableArrayList(listaPlatos);
        tabla.setItems(platosObservable);
    }

    private void configurarTabla(TableView<Platos> tabla, TableColumn<Platos, String> columnaNombrePlato,
            TableColumn<Platos, Double> columnaPrecio,
            TableColumn<Platos, CheckBox> columnaEsMini) {
        // Configurar columna de nombre
        columnaNombrePlato.setCellValueFactory(new PropertyValueFactory<>("nombrePlato"));
        columnaNombrePlato.setCellFactory(TextFieldTableCell.forTableColumn());
        columnaNombrePlato.setOnEditCommit(event -> {
            Platos plato = event.getRowValue();
            String nuevoNombre = event.getNewValue();
            plato.setNombrePlato(nuevoNombre); // Actualizar en el objeto
            platosDao.actualizarPlato(plato);  // Actualizar en la base de datos
        });

        // Configurar columna de precio
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaPrecio.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        columnaPrecio.setOnEditCommit(event -> {
            Platos plato = event.getRowValue();
            Double nuevoPrecio = event.getNewValue();
            plato.setPrecio(nuevoPrecio);      // Actualizar en el objeto
            platosDao.actualizarPlato(plato);  // Actualizar en la base de datos
        });

        // Configurar columna de "es mini"
        columnaEsMini.setCellValueFactory(cellData
                -> new SimpleObjectProperty<>(cellData.getValue().getMiniCheckBox()));

        // Asociar las columnas a la tabla
        tabla.getColumns().clear();
        tabla.getColumns().addAll(columnaNombrePlato, columnaPrecio, columnaEsMini);

        // Permitir edición en la tabla
        tabla.setEditable(true);
    }

    private void agregarPlato(TableView<Platos> tabla, ListView<String> listViewCategorias, ObservableList<Platos> listaPlatos, TextField txtNombrePlato, TextField txtPrecioPlato) {
        // Verificar que se haya seleccionado una categoría válida en el ListView
        String categoriaSeleccionada = listViewCategorias.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada == null || categoriaSeleccionada.isEmpty()) {
            mostrarAlerta("Debe seleccionar una categoría válida antes de agregar un plato.");
            return;
        }

        // Obtener el ID de la categoría seleccionada utilizando el método
        String idCategoria = platosDao.obtenerIdCategoriaPorNombre(categoriaSeleccionada);
        if (idCategoria == null) {
            mostrarAlerta("La categoría seleccionada no es válida o no existe en la base de datos.");
            return;
        }

        // Capturar los valores de los TextFields para el nombre y el precio del plato
        String nombrePlato = txtNombrePlato.getText().trim();
        String precioTexto = txtPrecioPlato.getText().trim();

        // Validar que los campos no estén vacíos
        if (nombrePlato.isEmpty()) {
            mostrarAlerta("Debe ingresar un nombre para el plato.");
            return;
        }

        double precioPlato;
        try {
            precioPlato = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Debe ingresar un precio válido para el plato.");
            return;
        }

        // Generar un nuevo ID para el plato
        String nuevoId = platosDao.generarNuevoIdPlato();
        if (nuevoId == null) {
            mostrarAlerta("No se pudo generar un nuevo ID para el plato.");
            return;
        }

        // Crear el objeto Platos
        Platos nuevoPlato = new Platos(
                nuevoId, // ID generado
                nombrePlato, // Nombre del plato ingresado
                precioPlato, // Precio del plato ingresado
                idCategoria, // ID de categoría obtenido de la base de datos
                new CheckBox() // CheckBox para "esMini"
        );

        // Insertar el plato en la base de datos
        if (platosDao.insertarPlato(nuevoPlato)) {
            // Agregar el plato a la lista observable asociada a la tabla
            listaPlatos.add(nuevoPlato);
            tabla.refresh(); // Refrescar la tabla para mostrar el nuevo plato
            mostrarAlerta("Plato agregado correctamente.");
            limpiarCampos(); // Limpiar los TextFields después de agregar el plato
        } else {
            mostrarAlerta("Error al agregar el plato a la base de datos.");
        }
    }

    private void eliminarPlato() {
        Platos platoSeleccionado = tablaActual.getSelectionModel().getSelectedItem();
        if (platoSeleccionado != null) {
            if (platosDao.eliminarPlato(platoSeleccionado.getIdPlatos())) {
                listaPlatosActual.remove(platoSeleccionado); // Eliminar el plato de la lista actual
                tablaActual.refresh(); // Refrescar la tabla actual
                mostrarAlerta("Plato eliminado correctamente.");
            } else {
                mostrarAlerta("Error al eliminar el plato de la base de datos.");
            }
        } else {
            mostrarAlerta("Seleccione un plato para eliminar.");
        }
    }

// Método para limpiar los TextFields después de agregar un plato
    private void limpiarCampos() {
        txtNombrePlatoDia.clear();
        txtPrecioPlatoDia.clear();
        txtNombrePlatoCarta.clear();
        txtPrecioPlatoCarta.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // ---------------------------------

   
}
