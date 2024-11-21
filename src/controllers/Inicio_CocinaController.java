 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.CategoriaDao;
import models.ConnectionMySQL;
import models.DetallesPedidos;
import models.Pedidos;
import models.PedidosDao;
import javafx.scene.layout.HBox;
import models.CategoriaPlatos;
import models.CategoriaPlatosDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.TipoMenu;
import models.TipoMenuDao;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import models.Platos;
import models.PlatosDao;
import javafx.scene.text.Text;

public class Inicio_CocinaController implements Initializable {

    @FXML
    private VBox contenedor_categorias;
    
    @FXML
    private GridPane contenedor_tllevar;

    @FXML
    private VBox contenedor_productos;

    @FXML
    private VBox contenedor_platos;

    @FXML
    private GridPane contenedor_tarjetas;// GridPane para los pedidos

    @FXML
    private GridPane paneMenuP;

    @FXML
    private GridPane paneMenuPl;
    
    @FXML
    private BorderPane paneMenu;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnCurso;

    @FXML
    private Button btnTerminado;

    @FXML
    private Button btnConfiguracion;

    @FXML
    private Button btnAplicar;

    @FXML
    private Label lblMesa1;

    @FXML
    private Label lblTiempo1;

    @FXML
    private Label lblTiempoR1;

    @FXML
    private Label lblTiempoM1;

    @FXML
    private Label lblDmesera1;

    @FXML
    private Label lblMesa;

    @FXML
    private Label lblColorP1;

    @FXML
    private Label lblDpedido1;

    @FXML
    private Label lblPedidoo;

    private PedidosDao pedidos_dao;

    private Connection conn;

    @FXML
    private Label lblComentariooP;

    @FXML

    private Label lblPedidooo1;

    @FXML
    private Label lblComentarioooP;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Pane glassPane;

    @FXML
    private Pane V_configuracion;

    @FXML
    private CheckBox btnChulo;

    @FXML
    private CheckBox btnChulo1;
    
    @FXML
    private BorderPane paneMenuC;

    @FXML
    private BorderPane paneProductos;

    @FXML
    private BorderPane pane_categoriaplatos;

    @FXML
    private BorderPane paneConfiguracion;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnAtrass;

    @FXML
    private Button btnAtrasss;

    @FXML
    private ComboBox<String> comboBoxCategorias;

    Pedidos pedidos = new Pedidos();
    
    private CategoriaDao categoriaDao;

    private Label selectedLabel; // Para almacenar la etiqueta seleccionada

    public Inicio_CocinaController() {
    }

    public Inicio_CocinaController(VBox contenedor_categorias, GridPane contenedor_tllevar, VBox contenedor_productos, VBox contenedor_platos, GridPane contenedor_tarjetas, GridPane paneMenuP, GridPane paneMenuPl, BorderPane paneMenu, Button btnSalir, Button btnMenu, Button btnCurso, Button btnTerminado, Button btnConfiguracion, Button btnAplicar, Label lblMesa1, Label lblTiempo1, Label lblTiempoR1, Label lblTiempoM1, Label lblDmesera1, Label lblMesa, Label lblColorP1, Label lblDpedido1, Label lblPedidoo, PedidosDao pedidos_dao, Connection conn, Label lblComentariooP, Label lblPedidooo1, Label lblComentarioooP, AnchorPane rootPane, Pane glassPane, Pane V_configuracion, CheckBox btnChulo, CheckBox btnChulo1, BorderPane paneMenuC, BorderPane paneProductos, BorderPane pane_categoriaplatos, BorderPane paneConfiguracion, Button btnAtras, Button btnAtrass, Button btnAtrasss, ComboBox<String> comboBoxCategorias, MediaPlayer mediaPlayer, CategoriaDao categoriaDao, Label selectedLabel) {
        this.contenedor_categorias = contenedor_categorias;
        this.contenedor_tllevar = contenedor_tllevar;
        this.contenedor_productos = contenedor_productos;
        this.contenedor_platos = contenedor_platos;
        this.contenedor_tarjetas = contenedor_tarjetas;
        this.paneMenuP = paneMenuP;
        this.paneMenuPl = paneMenuPl;
        this.paneMenu = paneMenu;
        this.btnSalir = btnSalir;
        this.btnMenu = btnMenu;
        this.btnCurso = btnCurso;
        this.btnTerminado = btnTerminado;
        this.btnConfiguracion = btnConfiguracion;
        this.btnAplicar = btnAplicar;
        this.lblMesa1 = lblMesa1;
        this.lblTiempo1 = lblTiempo1;
        this.lblTiempoR1 = lblTiempoR1;
        this.lblTiempoM1 = lblTiempoM1;
        this.lblDmesera1 = lblDmesera1;
        this.lblMesa = lblMesa;
        this.lblColorP1 = lblColorP1;
        this.lblDpedido1 = lblDpedido1;
        this.lblPedidoo = lblPedidoo;
        this.pedidos_dao = pedidos_dao;
        this.conn = conn;
        this.lblComentariooP = lblComentariooP;
        this.lblPedidooo1 = lblPedidooo1;
        this.lblComentarioooP = lblComentarioooP;
        this.rootPane = rootPane;
        this.glassPane = glassPane;
        this.V_configuracion = V_configuracion;
        this.btnChulo = btnChulo;
        this.btnChulo1 = btnChulo1;
        this.paneMenuC = paneMenuC;
        this.paneProductos = paneProductos;
        this.pane_categoriaplatos = pane_categoriaplatos;
        this.paneConfiguracion = paneConfiguracion;
        this.btnAtras = btnAtras;
        this.btnAtrass = btnAtrass;
        this.btnAtrasss = btnAtrasss;
        this.comboBoxCategorias = comboBoxCategorias;
        this.categoriaDao = categoriaDao;
        this.selectedLabel = selectedLabel;
        ConnectionMySQL connectionMySQL = new ConnectionMySQL();
        conn = (Connection) connectionMySQL.getConnection();
        categoriaDao = new CategoriaDao();
    }

    @FXML
    private void initialize() {
        pedidos_dao = new PedidosDao();
        categoriaDao = new CategoriaDao();
        try {
            loadPedidos();
            //cargarCategoriasPlatos();
            cargarTiposMenu(); 
        } catch (Exception e) {
            System.out.println("Error al cargar pedidos: " + e.getMessage());
        }
        lblColorP1.setText("Nuevo texto desde el controlador");
    }

    private void handleButton1Click() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/LoginView.fxml"));
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

    private void cargarPedidos() {
        try {
            List<Pedidos> pedidos = pedidos_dao.getAllPedidos(); // Asegúrate de que este método esté implementado

            // Limpia el contenedor
            contenedor_tarjetas.getChildren().clear();

            for (Pedidos pedido : pedidos) {
                List<DetallesPedidos> detalles = pedidos_dao.getDetallesPedido(pedido.getIdPedidos());
                VBox pedidoCard = loadPedidoCard(pedido, detalles); // Cambiado a VBox
                contenedor_tarjetas.getChildren().add(pedidoCard);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar pedidos: " + e.getMessage());
        }
    }

    private void cargarCategoriasPlatos() throws SQLException {
        // Obtener las categorías de platos desde la base de datos
        CategoriaPlatosDao categoriaPlatosDao = new CategoriaPlatosDao();
        List<CategoriaPlatos> categoriasPlatos = categoriaPlatosDao.getCategoriasPlatos();

        // Limpiar el contenedor
        contenedor_categorias.getChildren().clear();

        // Agregar un label por cada categoría de platos
        for (int i = 0; i < categoriasPlatos.size(); i++) {
            CategoriaPlatos categoria = categoriasPlatos.get(i);
            Text text = new Text(categoria.getNombreCategoriaPlatos());
            text.setId("categoriaLabel" + i); // Asignar un ID único
            text.setStyle("-fx-padding: 20; -fx-border-color: #fff; -fx-background-color: #FAFBFD;");
            text.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); // Cambiar tipo y tamaño de letra
            text.setOnMouseClicked(event -> {
                paneMenuC.setVisible(false);
                paneProductos.setVisible(true);
            });
            contenedor_categorias.getChildren().add(text);
        }
    }

   
    private void cargarPlatos() {
        PlatosDao platosDao = new PlatosDao(); // Cambiar ProductoDao a PlatosDao
        List<Platos> platos = platosDao.getAllPlatos(); // Asegúrate de que este método esté implementado

        contenedor_productos.getChildren().clear();

        for (int i = 0; i < platos.size(); i++) {
            Platos plato = platos.get(i); // Cambia Productos a Platos
            HBox hbox = new HBox();
            hbox.setSpacing(15);

            Label label = new Label(plato.getNombrePlato()); // Cambia getNombreProducto a getNombrePlato
            label.setId("platoLabel" + i); // Asignar un ID único
            label.setStyle("-fx-padding: 20; -fx-border-color: #fff; -fx-background-color: #FAFBFD;");
            label.setFont(Font.font("Arial", FontWeight.NORMAL, 14)); // Cambiar tipo y tamaño de letra
        
            Label mensajeLabel = new Label(); // Crear un Label para mostrar mensajes a la mesera
            CheckBox checkBox = new CheckBox();
            checkBox.setPrefSize(50, 30); // Cambiar el tamaño del CheckBox
            checkBox.getStyleClass().add("customCheckBox"); // Aplicar estilo CSS
            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) {
                    mensajeLabel.setText(plato.getNombrePlato() + " ya no está disponible."); // Cambiar a getNombrePlato
                    checkBox.setSelected(true);
                    platosDao.marcarComoNoDisponible(plato.getIdPlatos()); // Cambiar a getIdPlatos
                } else {
                    mensajeLabel.setText(""); // Limpia el mensaje si se desmarca
                }
            });

            hbox.getChildren().addAll(label, checkBox, mensajeLabel); // Agregar mensajeLabel al HBox
            contenedor_productos.getChildren().add(hbox);
        }
    }
    
    private void cargarTiposMenu() {
        // Obtener los tipos de menú desde la base de datos
        TipoMenuDao tipoMenuDao = new TipoMenuDao();
        List<TipoMenu> tiposMenu = tipoMenuDao.getTipoMenu();

        // Limpiar el contenedor
        contenedor_platos.getChildren().clear();

        // Agregar un label por cada tipo de menú
        for (int i = 0; i < tiposMenu.size(); i++) {
            TipoMenu tipo = tiposMenu.get(i);
            Label label = new Label(tipo.getNombreMenu());
            label.setId("tipoMenuLabel" + i); // Asignar un ID único
            label.setStyle("-fx-padding: 20; -fx-border-color: #fff; -fx-background-color: #FAFBFD;");
            label.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); // Cambiar tipo y tamaño de letra

            // Agregar un evento para manejar clics en la etiqueta
            label.setOnMouseClicked(event -> {
                try {
                    pane_categoriaplatos.setVisible(false);
                    paneMenuC.setVisible(true);
                    // Cargar categorías según el tipo de menú
                    cargarCategoriasPorTipo(tipo.getIdTipoMenu());
                } catch (SQLException ex) {
                    Logger.getLogger(Inicio_CocinaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            contenedor_platos.getChildren().add(label);
        }
    }

    private void cargarCategoriasPorTipo(int idTipoMenu) throws SQLException {
        // Obtener las categorías de platos según el tipo de menú
        CategoriaPlatosDao categoriaPlatosDao = new CategoriaPlatosDao();
        List<CategoriaPlatos> categoriasPlatos = categoriaPlatosDao.getCategoriasPorTipo(idTipoMenu);

        // Limpiar el contenedor de categorías
            contenedor_categorias.getChildren().clear();

        for (int i = 0; i < categoriasPlatos.size(); i++) {
            CategoriaPlatos categoria = categoriasPlatos.get(i);
            Label label = new Label(categoria.getNombreCategoriaPlatos());
            label.setId("categoriaLabel" + i); // Asignar un ID único
            label.setStyle("-fx-padding: 20; -fx-border-color: #fff; -fx-background-color: #FAFBFD;");
            label.setFont(Font.font("Arial", FontWeight.NORMAL, 16)); // Cambiar tipo y tamaño de letra

            // Agregar un evento para manejar clics en la categoría
            label.setOnMouseClicked(event -> {
                paneMenuC.setVisible(false);
                paneProductos.setVisible(true);
                // Cargar platos según la categoría seleccionada
                cargarPlatosPorCategoria(categoria.getIdCategoriaPlatos());
            });

            contenedor_categorias.getChildren().add(label);
        }
    }

    private void cargarPlatosPorCategoria(String idCategoriaPlatos) {
        PlatosDao platosDao = new PlatosDao();
        List<Platos> platos = platosDao.getPlatosByCategoria(idCategoriaPlatos);

        contenedor_productos.getChildren().clear();

        for (int i = 0; i < platos.size(); i++) {
            Platos plato = platos.get(i);
            HBox hbox = new HBox();
                hbox.setSpacing(15);

            Label label = new Label(plato.getNombrePlato());
            label.setId("platoLabel" + i);
            label.setStyle("-fx-padding: 20; -fx-border-color: #fff; -fx-background-color: #FAFBFD;");
            label.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

            CheckBox checkBox = new CheckBox();
            checkBox.setPrefSize(50, 30);
            checkBox.getStyleClass().add("customCheckBox");
            checkBox.setOnAction(event -> {
                // Lógica para manejar la selección del plato
            });

            hbox.getChildren().addAll(label, checkBox);
            contenedor_productos.getChildren().add(hbox);
        }
    }
    
    private void cargarTarjetasLlevar() {
        try {
            List<Pedidos> pedidosLlevar = pedidos_dao.getAllPedidosLlevar();
            System.out.println("Número de pedidos para llevar: " + pedidosLlevar.size());

            contenedor_tllevar.getChildren().clear();

            if (pedidosLlevar.isEmpty()) {
                System.out.println("No hay pedidos para llevar.");
                return;
            }

            for (Pedidos pedido : pedidosLlevar) {
                List<DetallesPedidos> detalles = pedidos_dao.getDetallesPedido(pedido.getIdPedidos());
                VBox tarjetaLlevar = loadTarjetaLlevar(pedido, detalles);

                if (tarjetaLlevar != null) {
                    contenedor_tllevar.getChildren().add(tarjetaLlevar);
                } else {
                    System.err.println("Error: La tarjeta de llevar no se pudo cargar para el pedido ID: " + pedido.getIdPedidos());
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar pedidos para llevar: " + e.getMessage());
        }
    }

    private Pedidos getPedidoSeleccionado() throws SQLException {
        // Obtener el pedido seleccionado desde la base de datos
        Pedidos pedido = pedidos_dao.getPedido(lblMesa1.getText());

        return pedido;
    }

    @FXML
    public void onShown() {

    }

    @FXML
    private void menuAction() throws SQLException {
        pane_categoriaplatos.setVisible(true);
        cargarTiposMenu();
        cargarCategoriasPlatos();
        cargarPlatos();
        
    }

    @FXML
    private void cursoAction() {
        HBox selectedHBox = getSelectedHBox();
        if (selectedHBox != null) {
            selectedHBox.setStyle("-fx-background-color: #FAC26D;"); // Cambia el color para "En curso"
            // Aquí puedes actualizar el estado en la base de datos si es necesario
        } else {
            System.out.println("Por favor selecciona un pedido primero.");
        }
    }

    @FXML
    private void terminadoAction() {
        HBox selectedHBox = getSelectedHBox();
        if (selectedHBox != null) {
            selectedHBox.setStyle("-fx-background-color: #9ECF78;"); // Cambia el color para "Terminado"
            // Aquí puedes actualizar el estado en la base de datos si es necesario
        } else {
            System.out.println("Por favor selecciona un pedido primero.");
        }
    }

    // Método para obtener el HBox seleccionado
    private HBox getSelectedHBox() {
        for (var child : contenedor_tarjetas.getChildren()) {
            if (child instanceof VBox) {
                TarjetaPedidoController controller = (TarjetaPedidoController) ((VBox) child).getProperties().get("controller");
                if (controller != null) {
                    HBox selectedHBox = controller.getSelectedHBox();
                    if (selectedHBox != null) {
                        return selectedHBox; // Retorna el HBox seleccionado
                    }
                }
            }
        }
        return null; // No hay HBox seleccionado
    }

    @FXML
    public void salirAction() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public void configuracionAction() {
        // Mostrar u ocultar la ventana emergente dependiendo de su estado actual
        paneConfiguracion.setVisible(true);

        // Opcional: bloquear las interacciones con otros elementos cuando la ventana emergente esté visible
        glassPane.setVisible(true);
    }

    public void cancelarAction() {
        // Ocultar la ventana emergente sin aplicar cambios
        paneConfiguracion.setVisible(false);
        glassPane.setVisible(false);
    }

    @FXML
    public void aplicarAction() {
        // Ocultar la ventana emergente después de aplicar cambios
        paneConfiguracion.setVisible(false);
        glassPane.setVisible(false);
    }

    public void chuloAction() {
        btnChulo.setOnAction(event -> {
        });
    }

    public void chulo1Action() {

    }

    @FXML
    private void atrasAction() {
        paneMenuPl.setVisible(true);
        paneMenuC.setVisible(false);
    }

    @FXML
    private void atrassAction() {
        paneMenuC.setVisible(true);
        paneProductos.setVisible(false);
    }

    @FXML
    private void atraAction() {
        rootPane.setVisible(true);
        pane_categoriaplatos.setVisible(false);
    }
    
   private void loadPedidos() {
    try {
        List<Pedidos> pedidos = pedidos_dao.getAllPedidos();
        System.out.println("Número de pedidos: " + pedidos.size());

        // Limpia todas las columnas y filas existentes
        contenedor_tarjetas.getColumnConstraints().clear();
        contenedor_tarjetas.getRowConstraints().clear();
        contenedor_tarjetas.getChildren().clear();

        int column = 0;
        int row = 0;
        int maxColumns = 5; // Número máximo de columnas en el GridPane

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

            // Asigna el ID del pedido a lblColorP1 si no es nulo
            if (lblColorP1 != null) {
                lblColorP1.setUserData(pedido.getIdPedidos()); // Solo usamos el último valor, podrías ajustar según tu lógica
            }
        }
        
        contenedor_tarjetas.setVisible(true); // Asegúrate de que el contenedor está visible

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
            if (controller != null) {
                controller.setPedidoDetails(
                        pedido.getIdPedidos(),
                        pedido.getMesasId(),
                        pedido.getEmpleadosId(),
                        detalles
                );
                // Almacena el controlador en las propiedades del VBox
                pedidoCard.getProperties().put("controller", controller);
            } else {
                System.out.println("Error: Controlador es null.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar TarjetaPedido.fxml: " + e.getMessage());
        }
        return pedidoCard;
    }
    
    private VBox loadTarjetaLlevar(Pedidos pedido, List<DetallesPedidos> detalles) {
        VBox tarjetaLlevar = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/TarjetaLlevar.fxml"));
            tarjetaLlevar = loader.load();

            TarjetaLlevarController controller = loader.getController();
            //TarjetaLlevarController controller = new TarjetaLlevarController();
            if (controller != null) {
                controller.cargarDetalles(
                    pedido.getIdPedidos(),
                    pedido.getMesasId(),
                    pedido.getEmpleadosId(),
                    detalles
                );
            } else {
                System.err.println("Error: Controlador es null.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar TarjetaLlevar.fxml: " + e.getMessage());
        }

        return tarjetaLlevar;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSalir.setOnAction(e -> salirAction());
        btnMenu.setOnAction(e -> {
            try {
                menuAction();
            } catch (SQLException ex) {
                Logger.getLogger(Inicio_CocinaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnAplicar.setOnAction(e -> aplicarAction());
        pedidos_dao = new PedidosDao();
        btnAtras.setOnAction(e -> atrasAction());
        btnAtrass.setOnAction(e -> atrassAction());
        btnAtrasss.setOnAction(e -> atraAction());

        btnCurso.setOnAction(e -> cursoAction());
        btnTerminado.setOnAction(e -> terminadoAction());
        cargarTarjetasLlevar();

        try {
            loadPedidos();
            cargarCategoriasPlatos();
        } catch (Exception e) {
            System.out.println("Error al cargar pedidos: " + e.getMessage());
            // Inicializar la ventana emergente como oculta
            glassPane.setVisible(false);
            paneMenuC.setVisible(false);
            paneMenuP.setVisible(false);
            contenedor_categorias.setVisible(false);
            contenedor_productos.setVisible(false);
            contenedor_tarjetas.setVisible(true);
            paneConfiguracion.setVisible(false);
            contenedor_tllevar.setVisible(true);
        }
    }

    public void setPedidoEnCurso(Label lblColorP1) {
        lblColorP1.setStyle("-fx-background-color: #FAC26D;");
    }

    public void setStage(Stage stage) {
    }

    private static class lblColor {

        public lblColor() {
            
        }
    }

    public static class domicilios {

        public domicilios() {
        }
    }

    private static class producto {

        private static String getNombreProducto() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public producto() {
        }
    }
}

