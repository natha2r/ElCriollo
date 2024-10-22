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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Employees;
import models.EmployeesDao;

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
    private Button btn_mesas;

    @FXML
    private Button btn_inicio;

    @FXML
    private Label popupLabel; //Texto de "MESA X"

    @FXML
    private Pane pane_inicio; //Panel de Inicio de mesera

    @FXML
    private Pane glassPane; // El Pane que actúa como GlassPane

    @FXML
    private GridPane gridPane; // Contenedor de las mesas

    @FXML
    private Label txt_cantidad;  // Label para mostrar la cantidad
    private int cantidad = 0;  // Cantidad inicial

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configura eventos para los botones
        btn_pedidos.setOnAction(e -> handleButton1Click());
        btn_mesas.setOnAction(e -> handleButton2Click());
        btn_menu.setOnAction(e -> handleButton3Click());
        btn_inicio.setOnAction(e -> handleButton4Click());
        btn_salir.setOnAction(e -> handleButton5Click());

        // Ocultar el GlassPane al iniciar la aplicación
        glassPane.setVisible(false);

        // Otros componentes de la interfaz pueden ser inicializados aquí
        pane_inicio.setVisible(true);
        initComboBox();

    }

    private void toma() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/Mensaje_Pedido.fxml"));
            Parent root = loader.load();

            // Configurar y mostrar la escena
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        System.out.println("btn_pedidos fue clickeado y ahora está encima de btn_mesas");
    }

    private void handleButton2Click() {
        // Traer el botón al frente
        btn_mesas.toFront();
        gridPane.setVisible(true);

        // Intercambiar colores entre btn_mesas y btn_pedidos
        switchButtonStyles(btn_mesas, btn_pedidos);

        System.out.println("btn_mesas fue clickeado y ahora está encima de btn_pedidos");
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

    private void handleButton3Click() { //Acción para que al oprimir btn_menu se oculte el pane_inicio
        btn_menu.toFront();
        pane_inicio.setVisible(false);
        gridPane.setVisible(false); //mejo
        System.out.println("btn_menu fue clickeado");
    }

    private void handleButton4Click() { // Acción para que al oprimir btn_inicio el pane_inicio sea visible
        btn_inicio.toFront();
        pane_inicio.setVisible(true);
        gridPane.setVisible(true);
        System.out.println("btn_inicio fue clickeado");
    }

    @FXML
    private void showPopup() {
        // Restablecer la cantidad a 0
        cantidad = 0;
        displayQuantity();

        // Restablecer el ComboBox al estado inicial
        comboBoxselecMesera.getSelectionModel().clearSelection(); // Deseleccionar cualquier selección
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
        // Obtener el objeto que lanzó el evento (en este caso, el ImageView de la mesa)
        ImageView clickedMesa = (ImageView) event.getSource();

        // Obtener el ID del ImageView para determinar el número de la mesa
        String mesaId = clickedMesa.getId();  // Ejemplo: "mesa1", "mesa2"
        String tableNumber = mesaId.replace("mesa", "MESA ");  // Convertir "mesa1" a "Mesa 1"

        // Mostrar el número de la mesa en el popup
        popupLabel.setText(tableNumber);

        // Mostrar el GlassPane con el popup
        showPopup();
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
}
