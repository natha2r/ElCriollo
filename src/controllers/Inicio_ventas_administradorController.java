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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import models.CategoriasDao;
import models.Pedidos;
import models.PedidosDao;
import models.Productos;
import models.ProductosDao;
import models.ProveedoresDao;
import models.InventarioDao;

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

    @FXML
    private Button btn_inactivos;

    @FXML
    private Button btn_atras_informacion;

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

    /*SUBRECURSOS PARA METODOS DE LA VISTA DE MODIFICAR EN CONFIGURACION */
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

    /*OTROS RECURSOS PARA LA TABLAVIEWS DE EMPLEADOS ACTIVOS */
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

    /*OTROS RECURSOS PARA LA TABLAVIEWS DE EMPLEADOS INACTIVOS */
    @FXML
    private TableView<Employees> tablaEmpleadosInactivos;
    @FXML
    private TableColumn<Employees, String> colId_inactivos;
    @FXML
    private TableColumn<Employees, String> colNombre_inactivos;
    @FXML
    private TableColumn<Employees, String> colEdad_inactivos;
    @FXML
    private TableColumn<Employees, String> colDireccion_inactivos;
    @FXML
    private TableColumn<Employees, String> colTelefono_inactivos;
    @FXML
    private TableColumn<Employees, String> colEmail_inactivos;
    @FXML
    private TableColumn<Employees, String> colCargo_inactivos;
    @FXML
    private TableColumn<Employees, String> colUsuario_inactivos;
    @FXML
    private TableColumn<Employees, String> colContraseña_inactivos;

    /*SUBRECURSOS PARA METODOS DE LA VISTA DE MODIFICAR EN CONFIGURACION DE EMPLEADOS 
    INACTIVOS*/
    @FXML
    private TextField buscarProducto;
    @FXML
    private TextField txt_modificar_Id_inactivos;
    @FXML
    private TextField txt_modificar_Nombre_inactivos;
    @FXML
    private TextField txt_modificar_Edad_inactivos;
    @FXML
    private TextField txt_modificar_Direccion_inactivos;
    @FXML
    private TextField txt_modificar_Telefono_inactivos;
    @FXML
    private TextField txt_modificar_Email_inactivos;
    @FXML
    private ComboBox<String> combo_Cargo_inactivos;
    @FXML
    private TextField txt_modificar_Usuario_inactivos;
    @FXML
    private PasswordField txt_modificar_Contraseña_inactivos;

    /*BOTON DE PRINCIPAL DE INVENTARIO*/
    @FXML
    private Button btn_inventario;

    /*son subbotones de la vista inventario*/
    @FXML
    private Button btn_nuevo_prod;

    @FXML
    private Button btn_reponer_prod;

    @FXML
    private Button btn_buscar_prod;

    @FXML
    private Button btn_volver_inventario;

    @FXML
    private Button btn_actualizar_producto;
    @FXML
    private Button btn_actualizar_proveedor;
    @FXML
    private Button btn_actualizar_categoria;

    @FXML
    private Button btn_interno_agregar_producto;
    @FXML
    private Button btn_interno_limpiar_producto;
    @FXML
    private Button btn_interno_cancelar_producto;

    @FXML
    private Button btn_interno_agregar_proveedor;
    @FXML
    private Button btn_interno_limpiar_proveedor;
    @FXML
    private Button btn_interno_cancelar_proveedor;

    @FXML
    private Button btn_interno_agregar_categoria;
    @FXML
    private Button btn_interno_limpiar_categoria;
    @FXML
    private Button btn_interno_cancelar_categoria;

    /*SON SUBRECURSOS PARA LA TABLA INVENTARIO*/
    @FXML
    private ComboBox<String> cmb_cargar_categoria;

    // ObservableList para gestionar la lista de productos
    //private ObservableList<Productos> ProductList = FXCollections.observableArrayList();
    /*OTROS RECURSOS PARA LA TABLAVIEW DE PRODUCTOS*/
    @FXML
    private TableView<Productos> tablaInventario;
    @FXML
    private TableColumn<Productos, String> columnaID;
    @FXML
    private TableColumn<Productos, String> columnaProducto;
    @FXML
    private TableColumn<Productos, String> columnaCategoria;
    @FXML
    private TableColumn<Productos, Integer> columnaStock;
    @FXML
    private TableColumn<Productos, Double> columnaPrecio;
    @FXML
    private TableColumn<Productos, String> columnaProveedor;

    /*SON RECURSOS PAR METODOS DE LA VISTA DE AGREGAR PRODUCTO*/
    @FXML
    private TextField txt_Id_agregar_prod;
    @FXML
    private TextField txt_Nombre_agregar_prod;
    @FXML
    private TextField txt_stock_agregar_prod;
    @FXML
    private TextField txt_precio_agregar_prod;
    @FXML
    private DatePicker txt_fecha_agregar_prod;
    @FXML
    private ComboBox<String> cmb_categoria_agregar_prod;
    @FXML
    private ComboBox<String> cmb_proveedor_agregar_prod;

    private ProductosDao productoDao = new ProductosDao();
    private CategoriasDao categoriaDao = new CategoriasDao();
    private ProveedoresDao ProveedoresDao = new ProveedoresDao();
    private InventarioDao InventarioDao = new InventarioDao();

    /*SON RECURSOS PAR METODOS DE LA VISTA DE AGREGAR PROVEEDOR*/
    @FXML
    private TextField txt_Id_agregar_prov;
    @FXML
    private TextField txt_Nombre_agregar_prov;
    @FXML
    private TextField txt_contacto_agregar_prov;
    @FXML
    private TextField txt_telefono_agregar_prov;
    @FXML
    private TextField txt_email_agregar_prov;
    @FXML
    private TextField txt_direccion_agregar_prov;
    @FXML
    private TextField txt_tpago_agregar_prov;

    /*SON RECURSOS PAR METODOS DE LA VISTA DE AGREGAR PROVEEDOR*/
    @FXML
    private TextField txt_Id_agregar_cat;
    @FXML
    private TextField txt_Nombre_agregar_cat;

    /*OTROS RECURSOS PARA LA TABLAVIEW DE PRODUCTOS*/
    @FXML
    private TableView<Productos> tablaInventario_reponer;
    @FXML
    private TableColumn<Productos, String> columnaID_reponer;
    @FXML
    private TableColumn<Productos, String> columnaProducto_reponer;
    @FXML
    private TableColumn<Productos, String> columnaCategoria_reponer;
    @FXML
    private TableColumn<Productos, String> columnaStock_reponer;
    @FXML
    private TableColumn<Productos, Double> columnaPrecio_reponer;
    @FXML
    private TableColumn<Productos, String> columnaProveedor_reponer;

    /*recursos para la vista de reponer producto*/
    @FXML
    private ComboBox<String> cmb_cargar_reponer_categoria;

    @FXML
    private TextField buscarReponerProducto;
    @FXML
    private Button btn_buscar_reponer_prod;

    @FXML
    private TextField txt_reponer_id_prod;
    @FXML
    private TextField txt_reponer_producto;
    @FXML
    private TextField txt_reponer_categoria_prod;
    @FXML
    private TextField txt_reponer_stock_prod;
    @FXML
    private TextField txt_reponer_precio_prod;
    @FXML
    private TextField txt_reponer_proveedor_prod;

    @FXML
    private Button btn_interno_reponer_prod;
    @FXML
    private Button btn_limpiar_reponer_prod;
    @FXML
    private Button btn_salir_reponer_prod;

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
    private Pane pane_reponer_producto;

    @FXML
    private Pane pane_modificar_inactivos_informacion;

    @FXML
    private Pane pane_actualizacion_inventario;

    @FXML
    private Pane pane_nuevo_producto;

    @FXML
    private Pane pane_nuevo_proveedor;

    @FXML
    private Pane pane_nueva_categoria;

    @FXML
    private boolean enFormulario = false;

    // Cambia el tipo de conn a java.sql.Connection
    private Connection conn;

    public Inicio_ventas_administradorController() {
    }

    public Inicio_ventas_administradorController(Button btn_inicio, Button btn_ventas, Button btn_meseras, Label jlabel_pedido, Label jlabel_sum_pedido, Button btn_configuracion, Button btn_personal_roles, Button btn_registro_actividades, Button btn_volver_config, Button btn_registrar_informacion, Button btn_modificar_informacion, Button btn_cancelar_registro_informacion, Button btn_interno_registrar_informacion, Button btn_inactivos, Button btn_atras_informacion, TextField txt_Id, TextField txt_Nombre, TextField txt_Edad, TextField txt_Direccion, TextField txt_Telefono, TextField txt_Email, ComboBox<String> cmb_Cargo, PasswordField txt_Contraseña, TextField txt_Usuario, TextField txt_modificar_Id, TextField txt_modificar_Nombre, TextField txt_modificar_Edad, TextField txt_modificar_Direccion, TextField txt_modificar_Telefono, TextField txt_modificar_Email, ComboBox<String> combo_Cargo, TextField txt_modificar_Usuario, PasswordField txt_modificar_Contraseña, TableView<Employees> tablaEmpleados, TableColumn<Employees, String> colId, TableColumn<Employees, String> colNombre, TableColumn<Employees, String> colEdad, TableColumn<Employees, String> colDireccion, TableColumn<Employees, String> colTelefono, TableColumn<Employees, String> colEmail, TableColumn<Employees, String> colCargo, TableColumn<Employees, String> colUsuario, TableColumn<Employees, String> colContraseña, TableView<Employees> tablaEmpleadosInactivos, TableColumn<Employees, String> colId_inactivos, TableColumn<Employees, String> colNombre_inactivos, TableColumn<Employees, String> colEdad_inactivos, TableColumn<Employees, String> colDireccion_inactivos, TableColumn<Employees, String> colTelefono_inactivos, TableColumn<Employees, String> colEmail_inactivos, TableColumn<Employees, String> colCargo_inactivos, TableColumn<Employees, String> colUsuario_inactivos, TableColumn<Employees, String> colContraseña_inactivos, TextField txt_modificar_Id_inactivos, TextField txt_modificar_Nombre_inactivos, TextField txt_modificar_Edad_inactivos, TextField txt_modificar_Direccion_inactivos, TextField txt_modificar_Telefono_inactivos, TextField txt_modificar_Email_inactivos, ComboBox<String> combo_Cargo_inactivos, TextField txt_modificar_Usuario_inactivos, PasswordField txt_modificar_Contraseña_inactivos, Button btn_inventario, Button btn_nuevo_prod, Button btn_reponer_prod, Button btn_buscar_prod, Button btn_volver_inventario, Button btn_actualizar_producto, Button btn_actualizar_proveedor, Button btn_actualizar_categoria, Button btn_interno_agregar_producto, Button btn_interno_limpiar_producto, Button btn_interno_cancelar_producto, Button btn_interno_agregar_proveedor, Button btn_interno_limpiar_proveedor, Button btn_interno_cancelar_proveedor, Button btn_interno_agregar_categoria, Button btn_interno_limpiar_categoria, Button btn_interno_cancelar_categoria, ComboBox<String> cmb_cargar_categoria, TableView<Productos> tablaInventario, TableColumn<Productos, String> columnaID, TableColumn<Productos, String> columnaProducto, TableColumn<Productos, String> columnaCategoria, TableColumn<Productos, Integer> columnaStock, TableColumn<Productos, Double> columnaPrecio, TableColumn<Productos, String> columnaProveedor, TextField txt_Id_agregar_prod, TextField txt_Nombre_agregar_prod, TextField txt_stock_agregar_prod, TextField txt_precio_agregar_prod, DatePicker txt_fecha_agregar_prod, ComboBox<String> cmb_categoria_agregar_prod, ComboBox<String> cmb_proveedor_agregar_prod, TextField txt_Id_agregar_prov, TextField txt_Nombre_agregar_prov, TextField txt_contacto_agregar_prov, TextField txt_telefono_agregar_prov, TextField txt_email_agregar_prov, TextField txt_direccion_agregar_prov, TextField txt_tpago_agregar_prov, TextField txt_Id_agregar_cat, TextField txt_Nombre_agregar_cat, TableView<Productos> tablaInventario_reponer, TableColumn<Productos, String> columnaID_reponer, TableColumn<Productos, String> columnaProducto_reponer, TableColumn<Productos, String> columnaCategoria_reponer, TableColumn<Productos, String> columnaStock_reponer, TableColumn<Productos, Double> columnaPrecio_reponer, TableColumn<Productos, String> columnaProveedor_reponer, ComboBox<String> cmb_cargar_reponer_categoria, TextField buscarReponerProducto, Button btn_buscar_reponer_prod, TextField txt_reponer_id_prod, TextField txt_reponer_producto, TextField txt_reponer_categoria_prod, TextField txt_reponer_stock_prod, TextField txt_reponer_precio_prod, TextField txt_reponer_proveedor_prod, Button btn_interno_reponer_prod, Button btn_limpiar_reponer_prod, Button btn_salir_reponer_prod, Button btn_reportes, Button btn_mesas, Button btn_caja, Button btn_salir, Pane pane_personal_roles, ScrollPane scroll_inicio_meseras, AnchorPane anchor_inicio_ventas, AnchorPane anchor_configuracion, AnchorPane anchorPane_inicio, AnchorPane anchor_inventario, Pane pane_inventario, Pane pane_configuracion, Pane pane_registrar_informacion, Pane pane_modificar_informacion, Pane pane_reponer_producto, Pane pane_modificar_inactivos_informacion, Pane pane_actualizacion_inventario, Pane pane_nuevo_producto, Pane pane_nuevo_proveedor, Pane pane_nueva_categoria, Connection conn) {
        this.btn_inicio = btn_inicio;
        this.btn_ventas = btn_ventas;
        this.btn_meseras = btn_meseras;
        this.jlabel_pedido = jlabel_pedido;
        this.jlabel_sum_pedido = jlabel_sum_pedido;
        this.btn_configuracion = btn_configuracion;
        this.btn_personal_roles = btn_personal_roles;
        this.btn_registro_actividades = btn_registro_actividades;
        this.btn_volver_config = btn_volver_config;
        this.btn_registrar_informacion = btn_registrar_informacion;
        this.btn_modificar_informacion = btn_modificar_informacion;
        this.btn_cancelar_registro_informacion = btn_cancelar_registro_informacion;
        this.btn_interno_registrar_informacion = btn_interno_registrar_informacion;
        this.btn_inactivos = btn_inactivos;
        this.btn_atras_informacion = btn_atras_informacion;
        this.txt_Id = txt_Id;
        this.txt_Nombre = txt_Nombre;
        this.txt_Edad = txt_Edad;
        this.txt_Direccion = txt_Direccion;
        this.txt_Telefono = txt_Telefono;
        this.txt_Email = txt_Email;
        this.cmb_Cargo = cmb_Cargo;
        this.txt_Contraseña = txt_Contraseña;
        this.txt_Usuario = txt_Usuario;
        this.txt_modificar_Id = txt_modificar_Id;
        this.txt_modificar_Nombre = txt_modificar_Nombre;
        this.txt_modificar_Edad = txt_modificar_Edad;
        this.txt_modificar_Direccion = txt_modificar_Direccion;
        this.txt_modificar_Telefono = txt_modificar_Telefono;
        this.txt_modificar_Email = txt_modificar_Email;
        this.combo_Cargo = combo_Cargo;
        this.txt_modificar_Usuario = txt_modificar_Usuario;
        this.txt_modificar_Contraseña = txt_modificar_Contraseña;
        this.tablaEmpleados = tablaEmpleados;
        this.colId = colId;
        this.colNombre = colNombre;
        this.colEdad = colEdad;
        this.colDireccion = colDireccion;
        this.colTelefono = colTelefono;
        this.colEmail = colEmail;
        this.colCargo = colCargo;
        this.colUsuario = colUsuario;
        this.colContraseña = colContraseña;
        this.tablaEmpleadosInactivos = tablaEmpleadosInactivos;
        this.colId_inactivos = colId_inactivos;
        this.colNombre_inactivos = colNombre_inactivos;
        this.colEdad_inactivos = colEdad_inactivos;
        this.colDireccion_inactivos = colDireccion_inactivos;
        this.colTelefono_inactivos = colTelefono_inactivos;
        this.colEmail_inactivos = colEmail_inactivos;
        this.colCargo_inactivos = colCargo_inactivos;
        this.colUsuario_inactivos = colUsuario_inactivos;
        this.colContraseña_inactivos = colContraseña_inactivos;
        this.txt_modificar_Id_inactivos = txt_modificar_Id_inactivos;
        this.txt_modificar_Nombre_inactivos = txt_modificar_Nombre_inactivos;
        this.txt_modificar_Edad_inactivos = txt_modificar_Edad_inactivos;
        this.txt_modificar_Direccion_inactivos = txt_modificar_Direccion_inactivos;
        this.txt_modificar_Telefono_inactivos = txt_modificar_Telefono_inactivos;
        this.txt_modificar_Email_inactivos = txt_modificar_Email_inactivos;
        this.combo_Cargo_inactivos = combo_Cargo_inactivos;
        this.txt_modificar_Usuario_inactivos = txt_modificar_Usuario_inactivos;
        this.txt_modificar_Contraseña_inactivos = txt_modificar_Contraseña_inactivos;
        this.btn_inventario = btn_inventario;
        this.btn_nuevo_prod = btn_nuevo_prod;
        this.btn_reponer_prod = btn_reponer_prod;
        this.btn_buscar_prod = btn_buscar_prod;
        this.btn_volver_inventario = btn_volver_inventario;
        this.btn_actualizar_producto = btn_actualizar_producto;
        this.btn_actualizar_proveedor = btn_actualizar_proveedor;
        this.btn_actualizar_categoria = btn_actualizar_categoria;
        this.btn_interno_agregar_producto = btn_interno_agregar_producto;
        this.btn_interno_limpiar_producto = btn_interno_limpiar_producto;
        this.btn_interno_cancelar_producto = btn_interno_cancelar_producto;
        this.btn_interno_agregar_proveedor = btn_interno_agregar_proveedor;
        this.btn_interno_limpiar_proveedor = btn_interno_limpiar_proveedor;
        this.btn_interno_cancelar_proveedor = btn_interno_cancelar_proveedor;
        this.btn_interno_agregar_categoria = btn_interno_agregar_categoria;
        this.btn_interno_limpiar_categoria = btn_interno_limpiar_categoria;
        this.btn_interno_cancelar_categoria = btn_interno_cancelar_categoria;
        this.cmb_cargar_categoria = cmb_cargar_categoria;
        this.tablaInventario = tablaInventario;
        this.columnaID = columnaID;
        this.columnaProducto = columnaProducto;
        this.columnaCategoria = columnaCategoria;
        this.columnaStock = columnaStock;
        this.columnaPrecio = columnaPrecio;
        this.columnaProveedor = columnaProveedor;
        this.txt_Id_agregar_prod = txt_Id_agregar_prod;
        this.txt_Nombre_agregar_prod = txt_Nombre_agregar_prod;
        this.txt_stock_agregar_prod = txt_stock_agregar_prod;
        this.txt_precio_agregar_prod = txt_precio_agregar_prod;
        this.txt_fecha_agregar_prod = txt_fecha_agregar_prod;
        this.cmb_categoria_agregar_prod = cmb_categoria_agregar_prod;
        this.cmb_proveedor_agregar_prod = cmb_proveedor_agregar_prod;
        this.txt_Id_agregar_prov = txt_Id_agregar_prov;
        this.txt_Nombre_agregar_prov = txt_Nombre_agregar_prov;
        this.txt_contacto_agregar_prov = txt_contacto_agregar_prov;
        this.txt_telefono_agregar_prov = txt_telefono_agregar_prov;
        this.txt_email_agregar_prov = txt_email_agregar_prov;
        this.txt_direccion_agregar_prov = txt_direccion_agregar_prov;
        this.txt_tpago_agregar_prov = txt_tpago_agregar_prov;
        this.txt_Id_agregar_cat = txt_Id_agregar_cat;
        this.txt_Nombre_agregar_cat = txt_Nombre_agregar_cat;
        this.tablaInventario_reponer = tablaInventario_reponer;
        this.columnaID_reponer = columnaID_reponer;
        this.columnaProducto_reponer = columnaProducto_reponer;
        this.columnaCategoria_reponer = columnaCategoria_reponer;
        this.columnaStock_reponer = columnaStock_reponer;
        this.columnaPrecio_reponer = columnaPrecio_reponer;
        this.columnaProveedor_reponer = columnaProveedor_reponer;
        this.cmb_cargar_reponer_categoria = cmb_cargar_reponer_categoria;
        this.buscarReponerProducto = buscarReponerProducto;
        this.btn_buscar_reponer_prod = btn_buscar_reponer_prod;
        this.txt_reponer_id_prod = txt_reponer_id_prod;
        this.txt_reponer_producto = txt_reponer_producto;
        this.txt_reponer_categoria_prod = txt_reponer_categoria_prod;
        this.txt_reponer_stock_prod = txt_reponer_stock_prod;
        this.txt_reponer_precio_prod = txt_reponer_precio_prod;
        this.txt_reponer_proveedor_prod = txt_reponer_proveedor_prod;
        this.btn_interno_reponer_prod = btn_interno_reponer_prod;
        this.btn_limpiar_reponer_prod = btn_limpiar_reponer_prod;
        this.btn_salir_reponer_prod = btn_salir_reponer_prod;
        this.btn_reportes = btn_reportes;
        this.btn_mesas = btn_mesas;
        this.btn_caja = btn_caja;
        this.btn_salir = btn_salir;
        this.pane_personal_roles = pane_personal_roles;
        this.scroll_inicio_meseras = scroll_inicio_meseras;
        this.anchor_inicio_ventas = anchor_inicio_ventas;
        this.anchor_configuracion = anchor_configuracion;
        this.anchorPane_inicio = anchorPane_inicio;
        this.anchor_inventario = anchor_inventario;
        this.pane_inventario = pane_inventario;
        this.pane_configuracion = pane_configuracion;
        this.pane_registrar_informacion = pane_registrar_informacion;
        this.pane_modificar_informacion = pane_modificar_informacion;
        this.pane_reponer_producto = pane_reponer_producto;
        this.pane_modificar_inactivos_informacion = pane_modificar_inactivos_informacion;
        this.pane_actualizacion_inventario = pane_actualizacion_inventario;
        this.pane_nuevo_producto = pane_nuevo_producto;
        this.pane_nuevo_proveedor = pane_nuevo_proveedor;
        this.pane_nueva_categoria = pane_nueva_categoria;
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
        });

        //mostrar en la tabla los empleados inactivos
        configurarColumnasInactivos();
        cargarEmpleadosInactivos();

        tablaEmpleadosInactivos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarInactivos(newValue);
            }
        });

        cargarCategorias();
        filtrarProductosPorCategoria();

        cmb_cargar_categoria.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Obtener la categoría seleccionada
                    String categoriaSeleccionada = newValue;

                    // Obtener los productos filtrados por la categoría seleccionada
                    ObservableList<Productos> productosFiltrados = productoDao.obtenerProductosPorCategoria(categoriaSeleccionada);

                    // Actualizar la tabla con los productos filtrados
                    tablaInventario.setItems(productosFiltrados);
                }
                if (newValue == "Todas las categorías") {

                    ObservableList<Productos> listaProductos = productoDao.obtenerTodosLosProductos();

                    tablaInventario.setItems(listaProductos);

                }
            }
        });

        cmb_cargar_reponer_categoria.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    // Obtener la categoría seleccionada
                    String categoriaSeleccionada = newValue;

                    // Obtener los productos filtrados por la categoría seleccionada
                    ObservableList<Productos> productosFiltrados = productoDao.obtenerProductosPorCategoria(categoriaSeleccionada);

                    // Actualizar la tabla con los productos filtrados
                    tablaInventario_reponer.setItems(productosFiltrados);
                }
                if (newValue == "Todas las categorías") {

                    ObservableList<Productos> listaProductos = productoDao.obtenerTodosLosProductos();

                    tablaInventario.setItems(listaProductos);

                }
            }
        });

        buscarReponerProducto.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    buscarProductoReponer();
                }
            }
        });

        buscarProducto.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    buscarProducto();
                }
            }
        });

        cargarCategoriasproductos();
        cargarProveedores();

        cargarCategoriasreponer();
        filtrarPorCategoria();

        cargarProductosreponer();
        configurarColumnasProductosreponer();
        actualizarProducto();
        // Listener para llenar campos de texto al seleccionar un producto
        tablaInventario_reponer.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cargarDatosProductoSeleccionado(newSelection);
            }
        });

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
        btn_inactivos.setOnAction(e -> handleBtnInactivosAction());
        btn_atras_informacion.setOnAction(e -> handleBtnAtrasInformacionAction());

        //subbotones de la vista de inventario
        btn_nuevo_prod.setOnAction(e -> handleBtnNuevoProdAction());
        btn_reponer_prod.setOnAction(e -> handleBtnReponerProdAction());
        btn_buscar_prod.setOnAction(e -> handleBtnBuscarProdAction());
        btn_volver_inventario.setOnAction(e -> handleBtnVolverInventarioAction());
        btn_actualizar_producto.setOnAction(e -> handleBtnActualizarProductoAction());
        btn_actualizar_proveedor.setOnAction(e -> handleBtnActualizarProveedorAction());
        btn_actualizar_categoria.setOnAction(e -> handleBtnActualizarCategoriaAction());
        btn_interno_agregar_producto.setOnAction(e -> handleBtnAgregarProducto());
        btn_interno_limpiar_producto.setOnAction(e -> handleBtnLimpiarProducto());
        btn_interno_cancelar_producto.setOnAction(e -> handleBtnCancelarProducto());
        btn_interno_agregar_proveedor.setOnAction(e -> handleBtnAgregarProveedor());
        btn_interno_limpiar_proveedor.setOnAction(e -> handleBtnLimpiarProveedor());
        btn_interno_cancelar_proveedor.setOnAction(e -> handleBtnCancelarProveedor());
        btn_interno_agregar_categoria.setOnAction(e -> handleBtnAgregarCategoria());
        btn_interno_limpiar_categoria.setOnAction(e -> handleBtnLimpiarCategoria());
        btn_interno_cancelar_categoria.setOnAction(e -> handleBtnCancelarCategoria());
        btn_interno_reponer_prod.setOnAction(e -> handleBtnInternoReponerProd());
        btn_limpiar_reponer_prod.setOnAction(e -> handleBtnLimpiarReponerProd());
        btn_salir_reponer_prod.setOnAction(e -> handleBtnSalirReponerProd());

    }

    @FXML
    public void handleBtnInicioAction() {

        // Verificar si estás en los paneles de "Registrar Información" o "Modificar Información"
        if ((pane_registrar_informacion.isVisible() || pane_modificar_informacion.isVisible()
                || pane_modificar_inactivos_informacion.isVisible() || pane_nuevo_producto.isVisible()
                || pane_nuevo_proveedor.isVisible() || pane_nueva_categoria.isVisible()) && !anchorPane_inicio.isVisible()) {
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
        limpiarCamposmodificadosinactivos();
        limpiarCamposproductos();
        limpiarCamposproveedor();

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
        pane_nuevo_proveedor.setVisible(false);
        pane_nueva_categoria.setVisible(false);
        pane_actualizacion_inventario.setVisible(false);
        pane_reponer_producto.setVisible(false);
        pane_modificar_inactivos_informacion.setVisible(false);

    }

    @FXML
    public void handleBtnConfiguracionAction() {

        // Verificar si el usuario está en algún formulario específico
        boolean enFormularioEspecifico = pane_registrar_informacion.isVisible()
                || pane_modificar_informacion.isVisible()
                || pane_modificar_inactivos_informacion.isVisible()
                || pane_nuevo_producto.isVisible()
                || pane_nuevo_proveedor.isVisible()
                || pane_nueva_categoria.isVisible();

        if (enFormularioEspecifico) {
            // Mostrar advertencia de confirmación
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar");
            confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
            confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

            Optional<ButtonType> resultado = confirmacion.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Limpiar el formulario y mostrar el panel de configuración
                limpiarEstadoFormulario();
                mostrarPanelConfiguracion();
                enFormulario = false; // Estado actualizado correctamente
            }
        } else {
            // Si no está en los formularios, cambiar a la vista de configuración sin advertencia
            limpiarEstadoFormulario(); // Asegurar consistencia al limpiar
            mostrarPanelConfiguracion();
            enFormulario = false; // Estado correcto para evitar conflictos
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
        pane_nuevo_proveedor.setVisible(false);
        pane_nueva_categoria.setVisible(false);
        pane_actualizacion_inventario.setVisible(false);
        pane_reponer_producto.setVisible(false);
        pane_modificar_inactivos_informacion.setVisible(false);

        // Llamar al método para agregar el efecto 3D si es necesario
        agregarEfecto3D(btn_personal_roles);
        agregarEfecto3D(btn_registro_actividades);
    }

    @FXML
    public void handleBtnInventarioAction() {
        // Verificar si el usuario está en algún formulario específico
        boolean enFormularioEspecifico = pane_registrar_informacion.isVisible()
                || pane_modificar_informacion.isVisible()
                || pane_modificar_inactivos_informacion.isVisible()
                || pane_nuevo_producto.isVisible()
                || pane_nuevo_proveedor.isVisible()
                || pane_nueva_categoria.isVisible();

        if (enFormularioEspecifico) {
            // Mostrar advertencia de confirmación
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar");
            confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
            confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

            Optional<ButtonType> resultado = confirmacion.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Limpiar el formulario y mostrar el panel de inventario
                limpiarEstadoFormulario();
                mostrarPanelInventario();
                enFormulario = false; // Estado actualizado correctamente
            }
        } else {
            // Si no está en los formularios, cambiar a la vista de inventario sin advertencia
            limpiarEstadoFormulario(); // Asegurar consistencia al limpiar
            mostrarPanelInventario();
            enFormulario = false; // Estado correcto para evitar conflictos
        }
    }

    private void mostrarPanelInventario() {
        // Mostrar el panel de inventario y ocultar los demás paneles no relevantes
        anchor_inventario.setVisible(true);
        pane_inventario.setVisible(true);

        pane_actualizacion_inventario.setVisible(false);
        pane_nuevo_producto.setVisible(false);
        pane_reponer_producto.setVisible(false);
        pane_nuevo_proveedor.setVisible(false);
        pane_nueva_categoria.setVisible(false);

        // Ocultar otros paneles que no son relevantes
        anchorPane_inicio.setVisible(false);
        anchor_inicio_ventas.setVisible(false);
        scroll_inicio_meseras.setVisible(false);
        anchor_configuracion.setVisible(false);
        pane_configuracion.setVisible(false);
        pane_registrar_informacion.setVisible(false);
        pane_modificar_informacion.setVisible(false);
        pane_modificar_inactivos_informacion.setVisible(false);
        configurarColumnasProductos();
        cargarProductos();

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
        pane_modificar_inactivos_informacion.setVisible(false);
        /*anchor_configuracion.setVisible(false);*/

    }

    /*BOTON DE VOLVER A CONFIGURACION*/
    @FXML
    public void handleBtnVolverConfigAction() {
        // Acción para el botón "PERSONAL Y ROLES"

        // Verificar si el panel de registro o el panel de modificar están visibles
        if (pane_registrar_informacion.isVisible() || pane_modificar_informacion.isVisible() || pane_modificar_inactivos_informacion.isVisible()) {

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
                pane_modificar_inactivos_informacion.setVisible(false);

                limpiarCamposmodificadosinactivos();
                limpiarCampos();  // Limpiar todos los campos
                limpiarCamposmodificados();
                enFormulario = false;
            }
        } else {
            // Si no está en la vista de "Registrar Formulario" o "Modificar Información" o "Modificar informacion activo", realiza la acción normalmente
            pane_configuracion.setVisible(true);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(true);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);
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
        pane_modificar_inactivos_informacion.setVisible(false);

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
            pane_modificar_inactivos_informacion.setVisible(false);

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
            pane_modificar_inactivos_informacion.setVisible(false);

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
        pane_modificar_inactivos_informacion.setVisible(false);
        tablaEmpleados.getSelectionModel().clearSelection();

        limpiarCampos();
    }

    @FXML
    private void handleBtnInactivosAction() {
        pane_modificar_inactivos_informacion.setVisible(true);
        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(true);
        pane_registrar_informacion.setVisible(false);
        pane_modificar_informacion.setVisible(false);

    }

    @FXML
    private void handleBtnAtrasInformacionAction() {

        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(true);
        pane_registrar_informacion.setVisible(false);
        pane_modificar_informacion.setVisible(true);
        pane_modificar_inactivos_informacion.setVisible(false);
        limpiarCamposmodificadosinactivos();
        limpiarCamposmodificados2();
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
    private void limpiarCamposmodificados2() { //metodo para limpiar la informaciomn de los TXT en la vista de modificiacion con el boton limpiar

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

            // Validar que la edad sea numérica y tenga exactamente 2 dígitos
            String edadTexto = txt_modificar_Edad.getText();
            if (!esNumerico(edadTexto) || edadTexto.length() != 2) {
                mostrarAlerta(AlertType.ERROR, "Error", "Edad no válida", "Por favor, ingresa un valor numérico de dos dígitos para la edad.");
                return; // Detener el proceso de modificación
            }

            int edad = Integer.parseInt(edadTexto);
            if (edad < 18) { // Verificar que esté en el rango permitido
                mostrarAlerta(AlertType.ERROR, "Error", "Edad no permitida", "La edad debe ser de 18 en adelante.");
                return; // Detener el proceso de modificación
            }

            // Validar que el teléfono sea numérico y tenga entre 10 y 11 dígitos
            String telefono = txt_modificar_Telefono.getText();
            if (!esNumerico(telefono) || (telefono.length() < 10 || telefono.length() > 11)) {
                mostrarAlerta(AlertType.ERROR, "Error", "Teléfono no válido", "El teléfono debe ser un número de 10 a 11 dígitos.");
                return; // Detener el proceso de modificación
            }

            // Continuar con la actualización de los demás campos si el ID no fue modificado, la edad es válida y el teléfono es correcto
            empleadoSeleccionado.setNombreEmpleado(txt_modificar_Nombre.getText());
            empleadoSeleccionado.setEdad(edad); // Edad ya validada
            empleadoSeleccionado.setDireccion(txt_modificar_Direccion.getText());
            empleadoSeleccionado.setTelefono(txt_modificar_Telefono.getText()); // Teléfono ya validado
            empleadoSeleccionado.setEmail(txt_modificar_Email.getText());
            empleadoSeleccionado.setRol(combo_Cargo.getValue());

            // Obtener los valores de usuario y contraseña para la tabla sesion
            String usuarioNuevo = txt_modificar_Usuario.getText();
            String contrasena = txt_modificar_Contraseña.getText();

            // Llamar al DAO para actualizar la base de datos
            EmployeesDao employeesDao = new EmployeesDao();
            boolean actualizado = employeesDao.modificarEmpleadoYSesion(empleadoSeleccionado, usuarioNuevo, contrasena);

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

// Método auxiliar para verificar si un texto es numérico
    private boolean esNumerico(String texto) {
        if (texto == null) {
            return false;
        }
        try {
            Long.parseLong(texto); // Cambiar a Long para manejar más dígitos si es necesario
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void handleInactivarEmpleado() {
        Employees empleadoSeleccionado = tablaEmpleados.getSelectionModel().getSelectedItem();

        if (empleadoSeleccionado != null) {
            // Confirmación antes de inactivar
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar inactivación");
            confirmacion.setHeaderText("¿Está seguro de que desea inactivar este empleado?");
            confirmacion.setContentText("El empleado no se eliminará, pero se marcará como inactivo.");

            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Inactivar en la base de datos
                EmployeesDao employeesDao = new EmployeesDao();
                boolean inactivado = employeesDao.inactivarEmpleadoYSesion(empleadoSeleccionado.getIdEmpleados(), empleadoSeleccionado.getSesion().getUsuario());
                limpiarCamposmodificados2();
                if (inactivado) {
                    // Eliminar de la lista y del TableView
                    tablaEmpleados.getItems().remove(empleadoSeleccionado);
                    mostrarAlerta(AlertType.INFORMATION, "Éxito", "Empleado inactivado", "El empleado ha sido marcado como inactivo.");
                } else {
                    mostrarAlerta(AlertType.ERROR, "Error", "Error al inactivar", "No se pudo inactivar al empleado.");
                }
            }
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", "Error al inactivar", "Por favor seleccione un empleado para inactivar.");
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

    @FXML
    private void limpiarCamposmodificadosinactivos() { //metodo para limpiar la informaciomn de los TXT en la vista de modificiacion con el boton limpiar

        txt_modificar_Id_inactivos.clear(); // Limpiar el campo de ID
        txt_modificar_Nombre_inactivos.clear(); // Limpiar el campo de nombre
        combo_Cargo_inactivos.setValue(null);      // Limpiar el comboBox de cargo
        // date_FechaContratacion.setValue(null); // Limpiar la fecha de contratación si lo estás usando
        // txt_Salario.setText("");      // Limpiar el campo de salario si lo estás usando
        txt_modificar_Telefono_inactivos.clear(); //Limpiar el campo de teléfono
        txt_modificar_Direccion_inactivos.clear(); // Limpiar el campo de dirección
        txt_modificar_Email_inactivos.clear();         // Limpiar el campo de email
        txt_modificar_Edad_inactivos.clear();          // Limpiar el campo de edad
        txt_modificar_Usuario_inactivos.clear();       // Limpiar el campo de usuario
        txt_modificar_Contraseña_inactivos.clear(); // Limpiar el campo de contraseña
        tablaEmpleadosInactivos.getSelectionModel().clearSelection();
    }

    //configuracion de columnas inactivas 
    private void configurarColumnasInactivos() {
        colId_inactivos.setCellValueFactory(new PropertyValueFactory<>("idEmpleados"));
        colNombre_inactivos.setCellValueFactory(new PropertyValueFactory<>("nombreEmpleado"));
        colEdad_inactivos.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colDireccion_inactivos.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono_inactivos.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail_inactivos.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCargo_inactivos.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colUsuario_inactivos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSesion().getUsuario()));
        colContraseña_inactivos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSesion().getContraseña()));
    }

    // Método para capturar la selección del empleado y llenar los campos 
    //de empleados inactivos
    private void cargarInactivos(Employees empleado) {

        // Llenar los campos de texto con los datos del empleado seleccionado
        txt_modificar_Id_inactivos.setText(empleado.getIdEmpleados());
        txt_modificar_Nombre_inactivos.setText(empleado.getNombreEmpleado());
        txt_modificar_Edad_inactivos.setText(String.valueOf(empleado.getEdad()));
        txt_modificar_Direccion_inactivos.setText(empleado.getDireccion());
        txt_modificar_Telefono_inactivos.setText(empleado.getTelefono());
        txt_modificar_Email_inactivos.setText(empleado.getEmail());
        combo_Cargo_inactivos.setValue(empleado.getRol());

        // Llenar los campos de sesión
        txt_modificar_Usuario_inactivos.setText(empleado.getSesion().getUsuario());
        txt_modificar_Contraseña_inactivos.setText(empleado.getSesion().getContraseña());
    }

    private void cargarEmpleadosInactivos() {
        EmployeesDao empleadosDao = new EmployeesDao();
        ObservableList<Employees> empleadosInactivos = FXCollections.observableArrayList(empleadosDao.obtenerEmpleadosInactivos());
        tablaEmpleadosInactivos.setItems(empleadosInactivos);
    }

    //METODO PARA EL BOTON DE ACTIVAR LOS EMPLEADOS 
    public void activarEmpleado() {

        Employees empleadoSeleccionado = tablaEmpleadosInactivos.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionado != null) {
            empleadoSeleccionado.setActivo(true); // Asumiendo que tienes un método setActivo()
            EmployeesDao empleadosDao = new EmployeesDao();
            empleadosDao.actualizarEmpleado(empleadoSeleccionado);
            cargarEmpleadosInactivos(); // Refresca la tabla después de la activación
            limpiarCamposmodificadosinactivos();

            // Muestra un mensaje de éxito
            mostrarAlerta(AlertType.INFORMATION, "Éxito", "Activación de Empleado", "El empleado ha sido activado exitosamente.");
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", "Error al activar", "Por favor seleccione un empleado para activarLO.");
        }

    }

    /*--------------------------------------------------------------------------*/
 /* ********************FIN DEL MODULO CONFIGURACION************************ */
 /*--------------------------------------------------------------------------*/
    //////////////////////////////////////////////////////////////////////////////
    /*--------------------------------------------------------------------------*/
 /* ***********************MODULO DE INVENTARIO*************************** */
 /*--------------------------------------------------------------------------*/
    @FXML
    private void handleBtnNuevoProdAction() {
        pane_actualizacion_inventario.setVisible(true);

        pane_modificar_inactivos_informacion.setVisible(false);
        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(false);
        pane_registrar_informacion.setVisible(false);
        pane_modificar_informacion.setVisible(false);
        pane_inventario.setVisible(false);
    }

    @FXML
    private void handleBtnReponerProdAction() {
        pane_reponer_producto.setVisible(true);

        pane_actualizacion_inventario.setVisible(false);
        pane_modificar_inactivos_informacion.setVisible(false);
        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(false);
        pane_registrar_informacion.setVisible(false);
        pane_modificar_informacion.setVisible(false);
        pane_inventario.setVisible(false);
    }

    @FXML
    private void handleBtnBuscarProdAction() {

    }

    @FXML
    public void handleBtnVolverInventarioAction() {
        // Acción para el botón "volver inventario"

        // Verificar si el panel de actualizacon están visibles
        if (pane_nuevo_producto.isVisible() || pane_nuevo_proveedor.isVisible() || pane_nueva_categoria.isVisible()) {

            // Mostrar advertencia de confirmación
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar");
            confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del formulario?");
            confirmacion.setContentText("Si cancela, se perderá toda la información ingresada.");

            Optional<ButtonType> resultado = confirmacion.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                // Aquí realiza la acción que corresponda al botón ""
                pane_inventario.setVisible(true);

                pane_configuracion.setVisible(false);
                anchorPane_inicio.setVisible(false);
                pane_personal_roles.setVisible(false);
                anchor_configuracion.setVisible(false);
                pane_registrar_informacion.setVisible(false);
                pane_modificar_informacion.setVisible(false);
                pane_modificar_inactivos_informacion.setVisible(false);
                pane_actualizacion_inventario.setVisible(false);
                pane_nuevo_producto.setVisible(false);
                pane_nuevo_proveedor.setVisible(false);
                pane_nueva_categoria.setVisible(false);

                /*limpiarCamposmodificadosinactivos();
            limpiarCampos();  // Limpiar todos los campos
            limpiarCamposmodificados();*/
                limpiarCamposproductos();
                limpiarCamposproveedor();

                enFormulario = false;
            }
        } else {
            // Si no está en la vista de "nuevo producto" o "nuevo proveedor", realiza la acción normalmente
            pane_inventario.setVisible(true);
            pane_configuracion.setVisible(false);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(true);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);
            pane_nuevo_producto.setVisible(false);
            pane_nuevo_proveedor.setVisible(false);
            pane_actualizacion_inventario.setVisible(false);
            pane_nueva_categoria.setVisible(false);

            enFormulario = false; // Asegurarse de que no estamos en un formulario

        }
    }

    // En InventarioController.java
    private void cargarCategorias() {
        ProductosDao productosDao = new ProductosDao();
        ObservableList<String> categorias = productosDao.obtenerCategorias();
        cmb_cargar_categoria.setItems(categorias);
        cmb_cargar_categoria.getSelectionModel().select("Todas las categorías"); // Seleccionar por defecto
    }

    // En InventarioController.java
    private void filtrarProductosPorCategoria() {
        String categoriaSeleccionada = cmb_cargar_categoria.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada != null) {
            ObservableList<Productos> productosFiltrados = productoDao.obtenerProductosPorCategoria(categoriaSeleccionada);
            tablaInventario.setItems(productosFiltrados);
        }
    }

    private void configurarColumnasProductos() {
        columnaID.setCellValueFactory(new PropertyValueFactory<>("idProductos"));
        columnaProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columnaStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columnaPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));

    }

    private void cargarProductos() {
        ProductosDao productosDao = new ProductosDao();
        ObservableList<Productos> listaProductos = productoDao.obtenerTodosLosProductos();
        tablaInventario.setItems(listaProductos);
    }

    /*-------------------ESTO ES PARA LA VISTA DE PRODUCTOS------------------------*/
    @FXML
    private void handleBtnActualizarProductoAction() {
        pane_nuevo_producto.setVisible(true);

        pane_nuevo_proveedor.setVisible(false);
        pane_actualizacion_inventario.setVisible(false);
        pane_nueva_categoria.setVisible(false);

        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(false);
        pane_inventario.setVisible(false);
    }

    @FXML
    private void handleBtnAgregarProducto() {
        String idProducto = txt_Id_agregar_prod.getText().trim();
        String nombreProducto = txt_Nombre_agregar_prod.getText().trim();
        String stock = txt_stock_agregar_prod.getText().trim();
        String precioText = txt_precio_agregar_prod.getText().trim();
        String categoriaSeleccionada = cmb_categoria_agregar_prod.getValue();
        String proveedorSeleccionado = cmb_proveedor_agregar_prod.getValue();

        // Verificar campos vacíos
        if (idProducto.isEmpty() || nombreProducto.isEmpty() || stock.isEmpty() || precioText.isEmpty()
                || proveedorSeleccionado == null || categoriaSeleccionada == null) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        // Convertir `precio` a double
        double precio;
        try {
            precio = Double.parseDouble(precioText);
        } catch (NumberFormatException e) {
            mostrarAlerta("El precio debe ser un número válido.");
            return;
        }

        // Verificar si el ID ya existe
        if (productoDao.existeIdProducto(idProducto)) {
            mostrarAlerta("El ID de producto ya existe. No puede ser duplicado.");
            return;
        }

        // Verificar si el Nombre ya existe
        if (productoDao.existeNombreProducto(nombreProducto)) {
            mostrarAlerta("El nombre de producto ya existe. No puede ser duplicado.");
            return;
        }

        // Obtener el ID de la categoría y del proveedor en lugar de sus nombres
        String idCategoria = productoDao.obtenerIdCategoriaPorNombre(categoriaSeleccionada);
        String idProveedor = productoDao.obtenerIdProveedorPorNombre(proveedorSeleccionado);

        // Generar el nuevo ID de inventario
        String idInventario = InventarioDao.generarNuevoIdInventario();

        // Convertir la fecha actual para `fechaRecepcion`
        java.sql.Date fechaRecepcion = new java.sql.Date(System.currentTimeMillis());

        // Llamar al método `agregarProductoCompleto` en el DAO con los ID de categoría y proveedor
        boolean exito = productoDao.agregarProductoCompleto(idProducto, nombreProducto, precio, idCategoria, idInventario, stock, fechaRecepcion, idProveedor);

        // Mostrar mensaje de éxito o error
        if (exito) {
            mostrarAlerta("Producto agregado exitosamente.");
            // Redirigir al panel de actualización de inventario y limpiar campos
            pane_inventario.setVisible(true);
            pane_configuracion.setVisible(false);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(false);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);
            pane_actualizacion_inventario.setVisible(true);
            pane_nuevo_producto.setVisible(false);
            pane_nuevo_proveedor.setVisible(false);
            pane_nueva_categoria.setVisible(false);

            limpiarCamposproductos();
        } else {
            mostrarAlerta("Hubo un error al agregar el producto.");
        }
    }

    @FXML
    private void handleBtnLimpiarProducto() {
        limpiarCamposproductos();
    }

    @FXML
    private void handleBtnCancelarProducto() {
        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del producto?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada del producto.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Solo si el usuario confirma, cambia la visibilidad de los paneles
            pane_nuevo_producto.setVisible(false);
            pane_nuevo_proveedor.setVisible(false);
            pane_nueva_categoria.setVisible(false);

            pane_actualizacion_inventario.setVisible(true);
            pane_inventario.setVisible(true);

            pane_configuracion.setVisible(false);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(false);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);

            limpiarCamposproductos(); // Limpia los campos solo si se confirma la cancelación
        }
    }

    @FXML
    private void limpiarCamposproductos() {
        txt_Id_agregar_prod.setText("");

        txt_fecha_agregar_prod.setValue(null);
        txt_Nombre_agregar_prod.setText("");
        txt_stock_agregar_prod.setText("");
        txt_precio_agregar_prod.setText("");      // Limpiar el campo de teléfono
        cmb_categoria_agregar_prod.setValue(null);     // Limpiar el campo de dirección
        cmb_proveedor_agregar_prod.setValue(null);         // Limpiar el campo de email     
    }

    // Método para mostrar una alerta
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    // Método para cargar categorías en el ComboBox
    private void cargarCategoriasproductos() {
        List<String> categorias = categoriaDao.obtenerCategoriasNombres();
        cmb_categoria_agregar_prod.getItems().addAll(categorias);
        // Opción para agregar nueva categoría

    }

    @FXML
    // Cargar proveedores en el ComboBox
    private void cargarProveedores() {
        cmb_proveedor_agregar_prod.getItems().clear();
        for (String proveedor : productoDao.obtenerProveedores()) {
            cmb_proveedor_agregar_prod.getItems().add(proveedor);
        }
    }

    /*------------------ESTO ES PARA LA VISTA DE PROVEEDORES-------------------*/
    @FXML
    private void handleBtnActualizarProveedorAction() {
        pane_nuevo_proveedor.setVisible(true);

        pane_nuevo_producto.setVisible(false);
        pane_actualizacion_inventario.setVisible(false);
        pane_nueva_categoria.setVisible(false);

        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(false);
        pane_inventario.setVisible(false);
    }

    @FXML
    private void handleBtnAgregarProveedor() {
        String id = txt_Id_agregar_prov.getText();
        String nombre = txt_Nombre_agregar_prov.getText();
        String contacto = txt_contacto_agregar_prov.getText();
        String telefono = txt_telefono_agregar_prov.getText();
        String email = txt_email_agregar_prov.getText();
        String direccion = txt_direccion_agregar_prov.getText();
        String terminoPago = txt_tpago_agregar_prov.getText();

        // Validar que los campos obligatorios estén completos
        if (id.isEmpty() || nombre.isEmpty() || contacto.isEmpty() || direccion.isEmpty() || terminoPago.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios, excepto el email y el teléfono.");
            return;
        }

        // Verificar si el ID, Nombre o Contacto ya existen en la base de datos
        if (ProveedoresDao.existeId(id)) {
            mostrarAlerta("El ID ya existe en la base de datos y no puede repetirse.");
            return;
        }
        if (ProveedoresDao.existeNombre(nombre)) {
            mostrarAlerta("El Nombre ya existe en la base de datos y no puede repetirse.");
            return;
        }

        // Si todas las validaciones pasan, intentamos agregar el proveedor
        boolean exito = ProveedoresDao.agregarProveedor(id, nombre, contacto, telefono, email, direccion, terminoPago);

        if (exito) {
            mostrarAlerta("Proveedor agregado exitosamente.");

            // Redirigir al panel de actualización de inventario
            pane_inventario.setVisible(true);
            pane_configuracion.setVisible(false);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(false);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);
            pane_actualizacion_inventario.setVisible(true);
            pane_nuevo_producto.setVisible(false);
            pane_nuevo_proveedor.setVisible(false);
            pane_nueva_categoria.setVisible(false);

            limpiarCamposproveedor();
        } else {
            mostrarAlerta("Hubo un error al agregar el proveedor.");
        }
    }

    @FXML
    private void handleBtnLimpiarProveedor() {
        limpiarCamposproveedor();
    }

    @FXML
    private void handleBtnCancelarProveedor() {
        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado del proveedor?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada del proveedor.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Solo si el usuario confirma, cambia la visibilidad de los paneles
            pane_nuevo_producto.setVisible(false);
            pane_nuevo_proveedor.setVisible(false);
            pane_nueva_categoria.setVisible(false);

            pane_actualizacion_inventario.setVisible(true);
            pane_inventario.setVisible(true);

            pane_configuracion.setVisible(false);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(false);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);

            limpiarCamposproveedor(); // Limpia los campos solo si se confirma la cancelación
        }
    }

    @FXML
    private void limpiarCamposproveedor() {
        txt_Id_agregar_prov.setText("");            // Limpiar el campo de ID
        txt_Nombre_agregar_prov.setText("");
        txt_contacto_agregar_prov.setText("");
        txt_telefono_agregar_prov.setText("");      // Limpiar el campo de teléfono
        txt_email_agregar_prov.setText("");
        txt_direccion_agregar_prov.setText("");
        txt_tpago_agregar_prov.setText("");
    }

    /*-------------------------------------------------------------------------*/
 /*------------------ESTO ES PARA LA VISTA DE CATEGORIA---------------------*/
    @FXML
    private void handleBtnActualizarCategoriaAction() {
        pane_nueva_categoria.setVisible(true);

        pane_nuevo_proveedor.setVisible(false);
        pane_nuevo_producto.setVisible(false);
        pane_actualizacion_inventario.setVisible(false);

        pane_configuracion.setVisible(false);
        anchorPane_inicio.setVisible(false);
        pane_personal_roles.setVisible(false);
        anchor_configuracion.setVisible(false);
        pane_inventario.setVisible(false);
    }

    @FXML
    private void handleBtnAgregarCategoria() {
        String id = txt_Id_agregar_cat.getText();
        String nombre = txt_Nombre_agregar_cat.getText();

        // Validar que los campos obligatorios estén completos
        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        // Verificar si el ID o Nombre ya existen en la base de datos
        if (categoriaDao.existeIdCategoria(id)) {
            mostrarAlerta("El ID de la categoría ya existe en la base de datos y no puede repetirse.");
            return;
        }
        if (categoriaDao.existeNombreCategoria(nombre)) {
            mostrarAlerta("El Nombre de la categoría ya existe en la base de datos y no puede repetirse.");
            return;
        }

        // Si todas las validaciones pasan, intentamos agregar la categoría
        boolean exito = categoriaDao.agregarCategoria(id, nombre);

        if (exito) {
            mostrarAlerta("Categoría agregada exitosamente.");

            // Redirigir al panel de actualización de inventario
            pane_inventario.setVisible(true);
            pane_configuracion.setVisible(false);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(false);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);
            pane_actualizacion_inventario.setVisible(true);
            pane_nuevo_producto.setVisible(false);
            pane_nuevo_proveedor.setVisible(false);
            pane_nueva_categoria.setVisible(false);

            limpiarCamposcategoria();
        } else {
            mostrarAlerta("Hubo un error al agregar la categoría.");
        }
    }

    @FXML
    private void handleBtnLimpiarCategoria() {
        limpiarCamposcategoria();
    }

    @FXML
    private void handleBtnCancelarCategoria() {
        // Mostrar advertencia de confirmación
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText("¿Está seguro de que desea cancelar el llenado de laa categoria?");
        confirmacion.setContentText("Si cancela, se perderá toda la información ingresada de la categoria.");

        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Solo si el usuario confirma, cambia la visibilidad de los paneles
            pane_nuevo_producto.setVisible(false);
            pane_nuevo_proveedor.setVisible(false);
            pane_nueva_categoria.setVisible(false);

            pane_actualizacion_inventario.setVisible(true);
            pane_inventario.setVisible(true);

            pane_configuracion.setVisible(false);
            anchorPane_inicio.setVisible(false);
            pane_personal_roles.setVisible(false);
            anchor_configuracion.setVisible(false);
            pane_registrar_informacion.setVisible(false);
            pane_modificar_inactivos_informacion.setVisible(false);

            limpiarCamposcategoria(); // Limpia los campos solo si se confirma la cancelación
        }
    }

    @FXML
    private void limpiarCamposcategoria() {
        txt_Id_agregar_cat.setText("");            // Limpiar el campo de ID
        txt_Nombre_agregar_cat.setText("");

    }

    /*-------------------------------------------------------------------------*/
 /*-------------ESTO ES PARA LA VISTA DE REPONER PRODUCTO-------------------*/
    @FXML
    private void configurarColumnasProductosreponer() {
        columnaID_reponer.setCellValueFactory(new PropertyValueFactory<>("idProductos"));
        columnaProducto_reponer.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        columnaCategoria_reponer.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columnaStock_reponer.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columnaPrecio_reponer.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnaProveedor_reponer.setCellValueFactory(new PropertyValueFactory<>("proveedor"));

    }

    @FXML
    private void cargarProductosreponer() {
        ProductosDao productosDao = new ProductosDao();
        ObservableList<Productos> listaProductos = productoDao.obtenerTodosLosProductos();
        tablaInventario_reponer.setItems(listaProductos);
    }

    @FXML
    // En InventarioController.java
    private void cargarCategoriasreponer() {
        ProductosDao productosDao = new ProductosDao();
        ObservableList<String> categorias = productosDao.obtenerCategorias();
        cmb_cargar_reponer_categoria.setItems(categorias);
        cmb_cargar_reponer_categoria.getSelectionModel().select("Todas las categorías"); // Seleccionar por defecto
    }

    private void cargarDatosProductoSeleccionado(Productos producto) {
        txt_reponer_id_prod.setText(producto.getIdProductos());
        txt_reponer_producto.setText(producto.getNombreProducto());
        txt_reponer_categoria_prod.setText(producto.getCategoria());
        txt_reponer_stock_prod.setText(String.valueOf(producto.getStock()));
        txt_reponer_precio_prod.setText(String.valueOf(producto.getPrecio()));
        txt_reponer_proveedor_prod.setText(producto.getProveedor());

        // Deshabilitar campos que no deben editarse
        txt_reponer_id_prod.setDisable(true);
        txt_reponer_producto.setDisable(true);
        txt_reponer_categoria_prod.setDisable(true);
        txt_reponer_proveedor_prod.setDisable(true);

        // Habilitar solo los campos que se pueden modificar
        txt_reponer_stock_prod.setDisable(false);
        txt_reponer_precio_prod.setDisable(false);
    }

    @FXML
    private void filtrarPorCategoria() {
        String categoriaSeleccionada = cmb_cargar_reponer_categoria.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada != null) {
            ObservableList<Productos> productosFiltrados = FXCollections.observableArrayList(
                    productoDao.obtenerProductosPorCategoria(categoriaSeleccionada)
            );

            // Imprimir cantidad de productos obtenidos
            System.out.println("Productos filtrados: " + productosFiltrados.size());

            for (Productos producto : productosFiltrados) {
                System.out.println("Producto: " + producto.getNombreProducto() + ", Precio: " + producto.getPrecio());
            }

            // Configurar el TableView y refrescar
            tablaInventario_reponer.setItems(productosFiltrados);
            tablaInventario_reponer.refresh();
        } else {
            System.out.println("Seleccione una categoría válida.");
        }

    }

    @FXML
    private void buscarProductoReponer() {
        String nombreProducto = buscarReponerProducto.getText().trim();
        if (!nombreProducto.isEmpty()) {
            ObservableList<Productos> productosFiltrados = productoDao.buscarProductoPorNombre(nombreProducto);

            if (productosFiltrados.isEmpty()) {
                System.out.println("No se encontraron productos con ese nombre.");
            }

            tablaInventario_reponer.setItems(productosFiltrados);
        } else {
            ObservableList<Productos> listaProductos = productoDao.obtenerTodosLosProductos();
            tablaInventario_reponer.setItems(listaProductos);
            System.out.println("Ingrese un nombre de producto para buscar.");
        }
    }

    @FXML
    private void buscarProducto() {
        String nombreProducto = buscarProducto.getText().trim();
        if (!nombreProducto.isEmpty()) {
            ObservableList<Productos> productosFiltrados = productoDao.buscarProductoPorNombre(nombreProducto);

            if (productosFiltrados.isEmpty()) {
                System.out.println("No se encontraron productos con ese nombre.");
            }

            tablaInventario.setItems(productosFiltrados);
        } else {
            ObservableList<Productos> listaProductos = productoDao.obtenerTodosLosProductos();
            tablaInventario.setItems(listaProductos);
            System.out.println("Ingrese un nombre de producto para buscar.");
        }
    }

    @FXML
    private void actualizarProducto() {
        String idProducto = txt_reponer_id_prod.getText();
        String nuevoStock = txt_reponer_stock_prod.getText(); // Obtén el valor del campo de stock
        String precioTexto = txt_reponer_precio_prod.getText(); // Obtén el valor del campo de precio

        if (precioTexto != null && !precioTexto.isEmpty()) {  // Verifica que el precio no esté vacío
            try {
                double nuevoPrecio = Double.parseDouble(precioTexto); // Convierte el precio a double
                // Actualiza el producto solo si el precio es válido
                boolean actualizado = productoDao.actualizarProducto(idProducto, nuevoStock, nuevoPrecio);
                if (actualizado) {
                    cargarProductosreponer(); // Actualizar la tabla
                    limpiarCamposreponer();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
                }
            } catch (NumberFormatException e) {
                // Si el precio no es válido, no haces nada o solo registras el error sin mostrar el mensaje
                // Deja que el método termine sin hacer nada si la conversión falla
                System.out.println("Precio no válido, no se actualizó el producto."); // O simplemente loguea el error
            }
        } else {
            // Si el campo de precio está vacío, puedes manejarlo de otra forma sin mostrar el mensaje innecesario
            System.out.println("Precio vacío, no se actualizó el producto."); // O simplemente no hacer nada
        }
    }

    private void limpiarCamposreponer() {
        txt_reponer_id_prod.clear();
        txt_reponer_producto.clear();
        txt_reponer_categoria_prod.clear();
        txt_reponer_stock_prod.clear();
        txt_reponer_precio_prod.clear();
        txt_reponer_proveedor_prod.clear();
    }

    @FXML
    private void handleBtnInternoReponerProd() {
        actualizarProducto();
    }

    @FXML
    private void handleBtnLimpiarReponerProd() {
        limpiarCamposreponer();
    }

    @FXML
    private void handleBtnSalirReponerProd() {

    }

    /*--------------------------------------------------------------------------*/
 /* **********************FIN DEL MODULO INVENTARIO************************* */
 /*--------------------------------------------------------------------------*/
}
