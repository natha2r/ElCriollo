package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.CategoriaPlatosDao;
import models.Employees;
import models.Pedidos;
import models.PedidosDao;
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
    @FXML
    private ComboBox<String> ComboBoxTamaño;
    @FXML
    private Label labelTotal; // Label para mostrar el total del pedido
    @FXML
    private CheckBox checkBoxConsumoMesa;
    @FXML
    private CheckBox checkBoxParaLlevar;
    @FXML
    private HBox hboxBotones;
    @FXML
    private Button btnEditar;

    private int rowIndex = 1; // Para llevar el control de las filas
    private double totalPedido = 0.0; // Para llevar el seguimiento del total

    private CategoriaPlatosDao categoriaPlatosDao = new CategoriaPlatosDao();
    private PlatosDao platosDao = new PlatosDao();
    private PrincipioDao principiosDao = new PrincipioDao();
    private PedidosDao pedidosDao = new PedidosDao();
    private String nombreMenuSeleccionado;
    private String PrincipioSeleccionado;
    private Employees meseraSeleccionada;
    private String mesaSeleccionada;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCategorias();
        //btn_enviarCocina.setOnAction(event -> enviarPedidoACocina());
        btn_Back.setOnAction(event -> showCategories());
        btn_Back.setVisible(false);
        glassPane1.setVisible(false);
        btn_aceptarItems.setOnAction(e -> handleAceptarItemsClick());
        //btn_enviarCocina.setOnAction(e -> handleEnviarCocinaClick());
        ObservableList<String> opciones = FXCollections.observableArrayList("Mini", "Normal");
        ComboBoxTamaño.setItems(opciones);
        checkBoxCarga();

        listViewPlatos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> handlePlatoSeleccionado()
        );

    }

    private String idMesa;

    private String idMesera;

    // Método para recibir el ID de la mesera
    public void setIdMesera(String idMesera) {
        this.idMesera = idMesera;

        // Imprimir el ID para verificar que se está recibiendo correctamente
        System.out.println("ID de la mesera recibido: " + this.idMesera);
    }

    // Setter para el ID de la mesa
    public void setIdMesa(String idMesa) {
        this.idMesa = idMesa;
        System.out.println("ID de la mesa recibido: " + this.idMesa);
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

    public void cargarCategorias() {
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

    private void checkBoxCarga() {
        checkBoxConsumoMesa.setOnAction(event -> {
            if (checkBoxConsumoMesa.isSelected()) {
                checkBoxParaLlevar.setSelected(false); // Desmarcar el otro
            }
        });

        checkBoxParaLlevar.setOnAction(event -> {
            if (checkBoxParaLlevar.isSelected()) {
                checkBoxConsumoMesa.setSelected(false); // Desmarcar el otro
            }
        });
    }

    @FXML
    private void handleBtnBackClick(ActionEvent event) {
        cargarCategorias();
    }

    @FXML
    private void handlePlatoSeleccionado() {
        String platoSeleccionado = listViewPlatos.getSelectionModel().getSelectedItem();

        if (platoSeleccionado != null) {
            // Verificar si el plato es Mini
            boolean esMini = platosDao.esMiniPorNombre(platoSeleccionado);
            System.out.println("Plato seleccionado: " + platoSeleccionado);
            System.out.println("¿Es Mini?: " + esMini);

            // Habilitar o deshabilitar el ComboBox de tamaño según el resultado
            ComboBoxTamaño.setDisable(!esMini); // Si no es Mini, deshabilitar
            System.out.println("ComboBox Tamaño deshabilitado: " + ComboBoxTamaño.isDisabled());
        }
    }

    @FXML
    private void handleAceptarItemsClick() {
        String platoSeleccionado = listViewPlatos.getSelectionModel().getSelectedItem();
        Principio principioSeleccionado = ComboBoxPrincipio.getSelectionModel().getSelectedItem();
        String comentarioSeleccionado = textAreaComentario.getText().trim();
        String tamañoSeleccionado = ComboBoxTamaño.getSelectionModel().getSelectedItem();

        // Validar que se seleccionaron plato y principio
        if (platoSeleccionado == null || principioSeleccionado == null) {
            System.out.println("Por favor, seleccione un plato y un principio.");
            return;
        }

        // Obtener precio del plato
        String precioString = obtenerPrecioPlato(platoSeleccionado);
        if (precioString == null || precioString.isEmpty()) {
            System.out.println("El precio del plato no está disponible.");
            return;
        }

        // Limpiar caracteres no numéricos del precio
        precioString = precioString.replaceAll("[^\\d.]", ""); // Eliminar caracteres como "$"
        double precio;
        try {
            precio = Double.parseDouble(precioString);
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el precio a número: " + precioString);
            return;
        }

        // Si el tamaño es "Mini", asignar precio fijo
        if ("Mini".equals(tamañoSeleccionado)) {
            precio = 11000; // Precio fijo para tamaño "Mini"
        }

        // CheckBox: Verificar si es "Para llevar" y sumar costo adicional
        if (checkBoxParaLlevar.isSelected()) {
            precio += 1000; // Costo adicional por "Para llevar"
        }

        // Actualizar el total del pedido
        totalPedido += precio;

        // Crear el VBox para el pedido
        VBox pedidoVBox = crearPedidoVBox(
                "Mini".equals(tamañoSeleccionado) ? platoSeleccionado + " - Mini" : platoSeleccionado,
                principioSeleccionado.getNombre(),
                comentarioSeleccionado + (checkBoxParaLlevar.isSelected() ? " (Para llevar)" : " (En mesa)"),
                precio
        );

        // Agregar el pedido al GridPane
        gridPanePedidos.add(pedidoVBox, 0, rowIndex);
        rowIndex++;

        // Limpiar selecciones y comentario
        listViewPlatos.getSelectionModel().clearSelection();
        ComboBoxPrincipio.getSelectionModel().clearSelection();
        ComboBoxTamaño.getSelectionModel().clearSelection();
        textAreaComentario.clear();
        checkBoxParaLlevar.setSelected(false);
        checkBoxConsumoMesa.setSelected(false);
        glassPane1.setVisible(false);

        // Actualizar el Label con el total del pedido
        labelTotal.setText("Total: $" + String.format("%.2f", totalPedido));
    }

// Método auxiliar para crear el VBox del pedido
    private VBox crearPedidoVBox(String plato, String principio, String comentario, double precio) {
        VBox vbox = new VBox();
        vbox.setSpacing(2.0); // Espaciado entre líneas del pedido
        vbox.setPadding(new Insets(10));
        vbox.setStyle("vbox-pedido");

        // Label para el plato principal con precio
        HBox hboxTitulo = new HBox();
        hboxTitulo.setAlignment(Pos.CENTER_LEFT);
        hboxTitulo.setSpacing(5);

        Label labelPlato = new Label("1 " + plato); // Cantidad inicial de 1
        labelPlato.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333,-fx-font-family: 'Karla';");

        Label labelPrecio = new Label(String.format("$%.0f", precio)); // Precio sin decimales
        labelPrecio.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333, -fx-font-family: 'Karla';");
        HBox.setHgrow(labelPrecio, Priority.ALWAYS);

        // Botones de eliminar, editar y sumar cantidad
        Button btnEliminar = new Button("❌");
        btnEliminar.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 16px; "
                + "-fx-cursor: hand;");
        btnEliminar.setOnAction(e -> eliminarPedido(vbox, precio));

        Button btnEditar = new Button("✏️");
        btnEditar.setStyle("-fx-background-color: transparent; -fx-text-fill: #333; -fx-font-size: 16px; "
                + "-fx-cursor: hand;");
        // Agregar lógica para editar si es necesario

        Button btnSumarCantidad = new Button("➕");
        btnSumarCantidad.setStyle("-fx-background-color: transparent; -fx-text-fill: #333; -fx-font-size: 16px; "
                + "-fx-cursor: hand;");
        btnSumarCantidad.setOnAction(e -> incrementarCantidad(labelPlato, labelPrecio, precio));

        hboxTitulo.getChildren().addAll(labelPlato, labelPrecio, btnEliminar, btnEditar, btnSumarCantidad);

        // Label para el principio
        Label labelPrincipio = new Label(principio);
        labelPrincipio.setStyle("-fx-font-size: 14px; -fx-text-fill: #555, -fx-font-family: 'Karla';");

        // Label para el comentario (opcional)
        Label labelComentario = new Label(comentario.isEmpty() ? "" : comentario);
        labelComentario.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        // Agregar elementos al VBox
        vbox.getChildren().addAll(hboxTitulo, labelPrincipio);
        if (!comentario.isEmpty()) {
            vbox.getChildren().add(labelComentario);
        }

        return vbox;
    }

    private void incrementarCantidad(Label labelPlato, Label labelPrecio, double precio) {
        // Obtener la cantidad actual
        String textoPlato = labelPlato.getText();
        int cantidadActual = Integer.parseInt(textoPlato.split(" ")[0]);

        // Incrementar la cantidad
        cantidadActual++;
        labelPlato.setText(cantidadActual + " " + textoPlato.substring(2));

        // Actualizar el precio total
        double nuevoPrecio = precio * cantidadActual;
        labelPrecio.setText(String.format("$%.0f", nuevoPrecio));

        // Actualizar el total del pedido
        totalPedido += precio;
        labelTotal.setText("Total: $" + String.format("%.2f", totalPedido));
    }

    private void eliminarPedido(VBox pedidoVBox, double precioUnitario) {
        // Obtener la cantidad actual del plato desde el label
        Label labelPlato = (Label) pedidoVBox.lookup(".label"); // Suponiendo que es el primer Label del VBox
        if (labelPlato != null) {
            String textoPlato = labelPlato.getText();
            int cantidadActual = Integer.parseInt(textoPlato.split(" ")[0]); // Obtener cantidad desde "5 Plato"

            // Calcular el precio total del pedido
            double precioTotal = cantidadActual * precioUnitario;

            // Actualizar el total del pedido
            totalPedido -= precioTotal;

            // Asegurar que no haya valores negativos
            if (totalPedido < 0) {
                totalPedido = 0;
            }

            // Actualizar el Label del total
            labelTotal.setText("Total: $" + String.format("%.2f", totalPedido));

            // Eliminar el VBox del GridPane
            gridPanePedidos.getChildren().remove(pedidoVBox);
        }
    }

// Método auxiliar para obtener el precio del plato
    private String obtenerPrecioPlato(String plato) {
        // Llama al método del DAO para obtener el precio desde la base de datos
        String precio = platosDao.obtenerPrecioPorNombre(plato);

        // Si el precio es vacío, retorna un mensaje predeterminado
        return precio.isEmpty() ? "$0" : "$" + precio;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void mostrarNumeroMesa(String numeroMesa) {
        System.out.println(idMesa);
    }

    public void mostrarMesera(String nombreMesera) {
        System.out.println(idMesera);
    }

    @FXML
    private void handleEnviarCocinaClick(ActionEvent event) {
        // Verifica si los campos necesarios están completos
        if (listViewPlatos.getItems().isEmpty()) {
            // Mostrar un mensaje de error si no hay platos seleccionados
            return;
        } 

        // Crear un objeto Pedido con los datos necesarios
        Pedidos pedido = new Pedidos();
        /*Pedidos.setIdMesa(idMesa);   // ID de la mesa
        Pedidos.setIdMesera(idMesera); // ID de la mesera
        Pedidos.setPlatosSeleccionados(listViewPlatos.getItems()); // Aquí pasas los platos seleccionados

        // Agregar cualquier otro dato necesario, como comentarios, principios, tamaños, etc.
        String comentario = textAreaComentario.getText();
        Pedidos.setComentario(comentario);
        Pedidos.setConsumoMesa(checkBoxConsumoMesa.isSelected());

        // Enviar el pedido a la base de datos o procesar el pedido
        // Si estás usando un DAO para la base de datos, asegúrate de incluir estos valores
        PedidosDao pedidoDao = new PedidosDao();
        boolean exito = pedidoDao.enviarPedidoACocina(Pedidos);

        if (exito) {
            // Mostrar mensaje de éxito o realizar alguna otra acción
            System.out.println("Pedido enviado a cocina.");
        } else {
            // Manejo de errores si el envío falla
            System.out.println("Error al enviar el pedido.");
        }
    }*/

}
}


