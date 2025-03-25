package com.example.gestionbiblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Cargar el archivo FXML (vista)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/vista/main_view.fxml"));

        // Crear una nueva escena con el contenido de la vista
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        // Establecer el título de la ventana
        stage.setTitle("Gestión de Biblioteca");

        // Establecer la escena en la ventana (Stage)
        stage.setScene(scene);

        // Mostrar la ventana
        stage.show();
    }

    public static void main(String[] args) {
        launch(args); // Llama a la función launch() para iniciar la aplicación JavaFX
    }
}
