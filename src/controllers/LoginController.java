//RAMA DEVELOPMENT
package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Employees;
import models.EmployeesDao;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    boolean resizable;

    @FXML
    private TextField txt_usuario;

    @FXML
    private PasswordField txt_contraseña;

    @FXML
    private Button btn_ingresar;

    @FXML
    private Label lb_mensaje;

    private EmployeesDao employees_dao;
    private Stage stage;

    @FXML
    private void initialize() {
        employees_dao = new EmployeesDao();
        btn_ingresar.setOnAction(event -> handleLogin());
    }

    public LoginController() {
    }

    public LoginController(TextField txt_usuario, PasswordField txt_contraseña, Button btn_ingresar, Label lb_mensaje, EmployeesDao employees_dao) {
        this.txt_usuario = txt_usuario;
        this.txt_contraseña = txt_contraseña;
        this.btn_ingresar = btn_ingresar;
        this.lb_mensaje = lb_mensaje;
        this.employees_dao = employees_dao;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void handleLogin() {
        String usuario = txt_usuario.getText();
        String contraseña = txt_contraseña.getText();

        Employees employee = employees_dao.loginQuery(usuario, contraseña);

        if (employee != null) {
            // Verificar el rol del empleado
            String rol = employee.getRol(); // suponiendo que tienes un método getRole() en la clase Employees

            switch (rol) {
                case "Camarero" -> {
                    // Login exitoso, acceso permitido para administradores
                    lb_mensaje.setText("Login exitoso " + rol);
                    loadViewBasedOnRole(rol);
                    // Aquí puedes abrir una nueva ventana o hacer lo que necesites con el empleado
                }
                case "Chef" -> {
                    // Login exitoso, acceso permitido para usuarios normales
                    lb_mensaje.setText("Login exitoso " + rol);
                    loadViewBasedOnRole(rol);
                    // Aquí puedes abrir una nueva ventana o hacer lo que necesites con el empleado
                }
                case "Cajero" -> {
                    // Acceso Cajero
                    lb_mensaje.setText("Login exitoso " + rol);
                    loadViewBasedOnRole(rol);
                    // Aquí puedes abrir una nueva ventana o hacer lo que necesites con el empleado
                }
                case "Administrador" -> {
                    //Acceso administrador
                    lb_mensaje.setText("Login exitoso Administrador " + rol);
                    loadViewBasedOnRole(rol);
                    // Aquí puedes abrir una nueva ventana o hacer lo que necesites con el empleado
                }
                default -> // Acceso denegado para otros roles
                    lb_mensaje.setText("Acceso denegado");
            }
        } else {
            // Login fallido
            lb_mensaje.setText("Usuario o contraseña incorrectos");
        }
    }

    private void loadViewBasedOnRole(String rol) {
        String fxmlFile;
        switch (rol) {
            case "Camarero":
                fxmlFile = "/Views/Inicio_mesera.fxml";
                resizable = false;
                break;
            case "Chef":
                fxmlFile = "/Views/Inicio_Cocina.fxml";
                resizable = true;
                break;
            case "Cajero":
                fxmlFile = "/Views/CajeraView.fxml";
                resizable = true;
                break;
            case "Administrador":
                fxmlFile = "/Views/Inicio_ventas_administrador.fxml";
                resizable = true;
                break;
            default:
                fxmlFile = null; // No debería llegar aquí si el rol está validado
                break;
        }

        if (fxmlFile != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                if (stage != null) {
                    stage.setTitle(rol);
                    stage.setScene(scene);
                    stage.setResizable(resizable);
                    stage.show();
                } else {
                    System.out.println("Error: stage is null");
                }
            } catch (IOException e) {
                e.printStackTrace();
                lb_mensaje.setText("Error al cargar la vista.");
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employees_dao = new EmployeesDao();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
