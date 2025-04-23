package com.example.gestionbiblioteca.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private VBox mainContent;

    @FXML
    private TextField searchField;

    @FXML
    private void agregarUsuario() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/CrearUsuario.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            CrearUsuarioController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Crear Usuario");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de usuario");
            alert.showAndWait();
        }
    }

    @FXML
    private void listarUsuarios() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/VP.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            VPController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Listar Usuarios");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de usuarios");
            alert.showAndWait();
        }
    }

    @FXML
    private void agregarLibro() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/CrearLibro.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            CrearLibroController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Crear Libro");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de libro");
            alert.showAndWait();
        }
    }


    @FXML
    private void listarLibros() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/Libros.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            LibroController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Listar Libros");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de visualización de Libros");
            alert.showAndWait();
        }
    }

    @FXML
    private void nuevoPrestamo() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/CrearPrestamo.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            AgregarPrestamoController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Nuevo Prestamo");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de creación de prestamos");
            alert.showAndWait();
        }
    }

    @FXML
    private void historialPrestamos() {
        try {
            // Cargar el FXML de CrearUsuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/VR.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            VRController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Listar Prestamos");

            // Pasar el Stage al controlador
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de visualización de Prestamos");
            alert.showAndWait();
        }
    }
    @FXML
    private void buscar() {
        String query = searchField.getText();
        System.out.println("Buscando: " + query);
    }
    @FXML
    private void mostrarGrafico() {
        try {
            // Cargar el FXML de la vista del gráfico
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/grafico.fxml"));
            AnchorPane root = loader.load();

            // Obtener el controlador de la vista cargada
            GraficoController controller = loader.getController();

            // Crear la escena y la ventana
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Gráfico de Libros Más Prestados");

            // Pasar el Stage al controlador del gráfico
            controller.setVentana(stage);

            // Mostrar la ventana
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Mostrar mensaje de error si no se puede cargar la vista
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana del gráfico");
            alert.showAndWait();
        }
    }

}
