package main;

import controllers.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML y el controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/LoginView.fxml"));
        Parent root = loader.load();

        // Obtener el controlador y establecer el Stage
        LoginController loginController = loader.getController();
        loginController.setStage(stage);
        
        Image icon = new Image("resources/icon.png");
        stage.getIcons().add(icon);

        // Configurar y mostrar la escena
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
