package com.example.gestionbiblioteca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainController {

    @FXML
    private VBox mainContent;  // Contenedor principal donde se cargan dinámicamente las vistas

    @FXML
    private TextField searchField; // Campo de búsqueda

    // Método llamado cuando se hace clic en "Agregar Usuario"
    @FXML
    private void agregarUsuario() {
        // Lógica para agregar un usuario
        System.out.println("Agregar Usuario");
        // Aquí podrías abrir una nueva vista para agregar usuario, si es necesario
    }

    // Método llamado cuando se hace clic en "Listar Usuarios"
    @FXML
    private void listarUsuarios() {
        // Lógica para listar los usuarios
        System.out.println("Listar Usuarios");
    }

    // Método llamado cuando se hace clic en "Nuevo Préstamo"
    @FXML
    private void nuevoPrestamoModelo() {
        // Lógica para realizar un nuevo préstamo
        System.out.println("Nuevo Préstamo");
    }

    // Método llamado cuando se hace clic en "Historial de Préstamos"
    @FXML
    private void historialPrestamos() {
        // Lógica para mostrar el historial de préstamos
        System.out.println("Historial de Préstamos");
    }

    // Método de búsqueda
    @FXML
    private void buscar() {
        String query = searchField.getText();
        // Lógica para buscar un usuario o libro
        System.out.println("Buscando: " + query);
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
