package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.CategoriaPlatosDao;
import models.PlatosDao;

public class Mensaje_PedidoController implements Initializable {

    public Mensaje_PedidoController() {
    }

    public Mensaje_PedidoController(Label txt_mesa, Button btn_cancelarTM, Inicio_meseraController inicioMeseraController, Pane glassPane1, ListView<String> listViewCategorias, String nombreMenuSeleccionado) {
        this.txt_mesa = txt_mesa;
        this.btn_cancelarTM = btn_cancelarTM;
        this.inicioMeseraController = inicioMeseraController;
        this.glassPane1 = glassPane1;
        this.listViewCategorias = listViewCategorias;
        this.nombreMenuSeleccionado = nombreMenuSeleccionado;
    }

    @FXML
    private Label txt_mesa; // Label donde se mostrará el número de mesa

    @FXML
    private Button btn_cancelarTM;

    private Inicio_meseraController inicioMeseraController;

    @FXML
    private Pane glassPane1;

    @FXML
    private Button btn_Back;

    @FXML
    private Button btn_txtCategorias;

    @FXML
    private ListView<String> listViewCategorias = new ListView<>();

    private CategoriaPlatosDao categoriaPlatosDao = new CategoriaPlatosDao();
    private PlatosDao platosDao = new PlatosDao();
    private String nombreMenuSeleccionado;
    private boolean mostrandoPlatos = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialmente, carga las categorías
        cargarCategorias();

        // Configura el botón de regreso
        btn_Back.setOnAction(event -> showCategories());

        // Oculta inicialmente el GlassPane y el botón de regreso
        btn_Back.setVisible(false);
        glassPane1.setVisible(false);
    }

    // Método para mostrar el número de mesa
    public void mostrarNumeroMesa(String numeroMesa) {
        txt_mesa.setText(numeroMesa);
    }

    @FXML
    private void handleCancelarItem() {
        // Oculta el GlassPane
        glassPane1.setVisible(false);
        
        // Muestra el botón de regresar
        btn_Back.setVisible(true);
    }

    
    @FXML
    private void handleCancelarPedidoClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Inicio_mesera.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la segunda vista
            inicioMeseraController = loader.getController();

            // Cambiar la escena
            Scene scene = new Scene(root);
            Stage stage = (Stage) btn_cancelarTM.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para inicializar el controlador con el menú seleccionado
    public void setMenuSeleccionado(String nombreMenuSeleccionado) {
        this.nombreMenuSeleccionado = nombreMenuSeleccionado;
        cargarCategorias();
    }

    // Método para cargar las categorías
    private void cargarCategorias() {
        ObservableList<String> categorias = categoriaPlatosDao.getCategoriasByMenu(nombreMenuSeleccionado);
        listViewCategorias.setItems(categorias);

        // Listener para la selección de categorías
        listViewCategorias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarPlatos(newValue);
            }
        });

        // Asegurarse de que la lista muestre las categorías correctamente
        btn_txtCategorias.setText("CATEGORÍAS");
    }

    // Método para cargar los platos basados en la categoría seleccionada
    private void cargarPlatos(String categoriaSeleccionada) {
        ObservableList<String> platos = platosDao.getPlatosByCategoria(categoriaSeleccionada);

        // Actualiza el título del botón
        btn_txtCategorias.setText("PLATOS");

        // Cambia el contenido del ListView para mostrar los platos
        listViewCategorias.setItems(platos);

        // Muestra el botón de regreso para volver a las categorías
        btn_Back.setVisible(true);

        // Listener para la selección de platos
        listViewCategorias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Muestra el GlassPane para agregar un comentario al pedido
                glassPane1.setVisible(true);

                // Aquí se puede manejar lo que sucede cuando se selecciona un plato
                System.out.println("Plato seleccionado: " + newValue);
            }
        });
    }

    private void showCategories() {
        // Oculta el GlassPane
        glassPane1.setVisible(false);

        // Vuelve a cargar las categorías
        cargarCategorias();

        // Actualiza el título del botón de texto
        btn_txtCategorias.setText("CATEGORÍAS");

        // Oculta el botón de regreso
        btn_Back.setVisible(false);
    }

    @FXML
    private void handleBtnBackClick(ActionEvent event) {
        // Vuelve a mostrar la lista de categorías
        cargarCategorias();
    }

}
