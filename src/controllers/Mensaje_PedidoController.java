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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CategoriaPlatosDao;
import models.PlatosDao;
import models.Principio;
import models.PrincipioDao;

public class Mensaje_PedidoController implements Initializable {

    @FXML
    private Label txt_mesa; // Label donde se mostrará el número de mesa
    @FXML
    private Button btn_cancelarTM;
    @FXML
    private Pane glassPane1;
    @FXML
    private Button btn_Back;
    @FXML
    private Button btn_txtCategorias;
    @FXML
    private ListView<String> listViewCategorias;
    @FXML
    private ListView<String> listViewPlatos;
    @FXML
    private TextArea textAreaComentario;
    @FXML
    private Button btn_enviarCocina;
    @FXML
    private ComboBox<Principio> ComboBoxPrincipio; // Agrega el ComboBox aquí
    @FXML
    private Button btn_aceptarItems;
    @FXML
    private ScrollPane scrollPanePedidos; // Asegúrate de enlazarlo desde el FXML
    @FXML
    private GridPane gridPanePedidos;
    private int rowIndex = 1; // Para llevar el control de las filas

    private CategoriaPlatosDao categoriaPlatosDao = new CategoriaPlatosDao();
    private PlatosDao platosDao = new PlatosDao();
    private PrincipioDao principiosDao = new PrincipioDao();
    private String nombreMenuSeleccionado;
    private String PrincipioSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialmente, carga las categorías
        cargarCategorias();

        // Configura el botón de regreso
        btn_Back.setOnAction(event -> showCategories());

        // Oculta inicialmente el GlassPane y el botón de regreso
        btn_Back.setVisible(false);
        glassPane1.setVisible(false);
        btn_aceptarItems.setOnAction(e -> handleAceptarItemsClick());
        btn_enviarCocina.setOnAction(e -> handleEnviarCocinaClick());
    }

    // Método para mostrar el número de mesa
    public void mostrarNumeroMesa(String numeroMesa) {
        txt_mesa.setText(numeroMesa);
    }

    @FXML
    private void handleCancelarItem() {
        listViewCategorias.getSelectionModel().clearSelection();
        listViewPlatos.getSelectionModel().clearSelection(); // Limpia la selección anterior
        glassPane1.setVisible(false);
        btn_Back.setVisible(true);
    }

    @FXML
    private void handleCancelarPedidoClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Inicio_mesera.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btn_cancelarTM.getScene().getWindow();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMenuSeleccionado(String nombreMenuSeleccionado) {
        this.nombreMenuSeleccionado = nombreMenuSeleccionado;
        cargarCategorias();
    }

    public void setPrincipioSeleccionado(String PrincipioSeleccionado) {
        this.PrincipioSeleccionado = PrincipioSeleccionado;
    }

    private void cargarCategorias() {
        listViewPlatos.setVisible(false);
        listViewCategorias.setVisible(true);
        ObservableList<String> categorias = categoriaPlatosDao.getCategoriasByMenuM(nombreMenuSeleccionado);

        listViewCategorias.getSelectionModel().clearSelection(); // Limpia la selección anterior
        listViewCategorias.setItems(categorias);

        // Verificación del tamaño de la lista de categorías
        System.out.println("Tamaño de categorías: " + categorias.size());

        // Listener para la selección de categorías
        listViewCategorias.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, categoriaSeleccionada) -> {
            if (categoriaSeleccionada != null) {
                cargarPlatos(categoriaSeleccionada);
            }
        });

        btn_txtCategorias.setText("CATEGORÍAS");
    }

    private void cargarPlatos(String categoriaSeleccionada) {
        listViewPlatos.setVisible(true);
        listViewCategorias.setVisible(false);
        ObservableList<String> platos = platosDao.getPlatosByCategoriaM(categoriaSeleccionada);

        btn_txtCategorias.setText("PLATOS");
        listViewPlatos.setItems(platos);
        btn_Back.setVisible(true);

        listViewPlatos.getSelectionModel().clearSelection(); // Limpia la selección anterior

        // Verifica si hay platos antes de actualizar el ListView
        if (platos == null || platos.isEmpty()) {
            System.out.println("No hay platos disponibles en esta categoría.");
            return; // Evita continuar si la lista está vacía
        }

        // Listener para la selección de platos
        listViewPlatos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                glassPane1.setVisible(true);
                System.out.println("Plato seleccionado: " + newValue);
                cargarPrincipios();
            }
        });

    }

    private void cargarPrincipios() {
        ObservableList<Principio> principiosList = principiosDao.getAllPrincipios();

        // Establecer la lista en el ComboBox
        ComboBoxPrincipio.setItems(principiosList);

        // Personaliza la visualización en el ComboBox
        ComboBoxPrincipio.setCellFactory(comboBox -> new javafx.scene.control.ListCell<Principio>() {
            @Override
            protected void updateItem(Principio item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre()); // Asegúrate de que tienes este método en la clase Principio
                }
            }
        });

        ComboBoxPrincipio.setButtonCell(new javafx.scene.control.ListCell<Principio>() {
            @Override
            protected void updateItem(Principio item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre());
                }
            }
        });
    }

    private void showCategories() {
        glassPane1.setVisible(false);
        cargarCategorias();
        btn_txtCategorias.setText("CATEGORÍAS");
        btn_Back.setVisible(false);
    }

    @FXML
    private void handleBtnBackClick(ActionEvent event) {
        cargarCategorias();
    }

    @FXML
    private void handleAceptarItemsClick() {
        String platoSeleccionado = listViewPlatos.getSelectionModel().getSelectedItem();
        Principio principioSeleccionado = ComboBoxPrincipio.getSelectionModel().getSelectedItem();
        String comentarioSeleccionado = textAreaComentario.getText().trim(); // Obtener texto del TextArea

        // Validación de selección
        if (platoSeleccionado == null || principioSeleccionado == null) {
            System.out.println("Por favor, seleccione un plato y un principio.");
            return;
        }

        // Obtener precio del plato (supón que tienes un método para obtener el precio)
        String precio = obtenerPrecioPlato(platoSeleccionado);

        // Crear el VBox del pedido
        VBox pedidoVBox = crearPedidoVBox(platoSeleccionado, principioSeleccionado.getNombre(), comentarioSeleccionado);

        // Agregar el VBox y el precio al GridPane
        gridPanePedidos.add(pedidoVBox, 0, rowIndex); // Columna 0 para los detalles del pedido
        gridPanePedidos.add(new Label(precio), 1, rowIndex); // Columna 1 para el precio

        rowIndex++; // Incrementa la fila para el siguiente pedido

        // Limpiar selecciones y comentario
        listViewPlatos.getSelectionModel().clearSelection();
        ComboBoxPrincipio.getSelectionModel().clearSelection();
        textAreaComentario.clear(); // Limpiar el TextArea después de enviar
        glassPane1.setVisible(false);

    }

// Método auxiliar para crear el VBox del pedido
    private VBox crearPedidoVBox(String plato, String principio, String comentario) {
        VBox vbox = new VBox();
        vbox.setSpacing(3.0);
        vbox.setStyle("-fx-padding: 5px"); // Aplica el estilo del VBox

        Label txtPlato = new Label(plato);
        Label txtPrincipio = new Label(principio);
        Label txtComentario = new Label((comentario.isEmpty() ? "Sin comentario" : comentario));

        txtPlato.getStyleClass().add("label");
        txtPrincipio.getStyleClass().add("label");
        txtComentario.getStyleClass().add("label");

        vbox.getChildren().addAll(txtPlato, txtPrincipio, txtComentario);
        return vbox;
    }

// Método auxiliar para obtener el precio del plato
    private String obtenerPrecioPlato(String plato) {
        // Llama al método del DAO para obtener el precio desde la base de datos
        String precio = platosDao.obtenerPrecioPorNombre(plato);

        // Si el precio es vacío, retorna un mensaje predeterminado
        return precio.isEmpty() ? "$0" : "$" + precio;
    }

    @FXML
    private void handleEnviarCocinaClick() {
        // Lógica para enviar el pedido a la cocina
        //enviarPedidoACocina();

        // Obtener el Stage actual y cerrarlo
        Stage stage = (Stage) btn_enviarCocina.getScene().getWindow();
        stage.close();

        // Abrir la vista de inicio_mesera
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Inicio_mesera.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener el Stage principal y cambiar la escena
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
