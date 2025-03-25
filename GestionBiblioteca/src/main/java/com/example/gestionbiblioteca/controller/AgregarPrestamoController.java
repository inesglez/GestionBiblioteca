package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class AgregarPrestamoController {

    @FXML
    private DatePicker fechaPrestamo;
    @FXML
    private DatePicker fechaDevolucion;
    @FXML
    private TextField libroPrestado;
    @FXML
    private Button botonGuardar;

    private PrestamoModelo prestamoModelo = new PrestamoModelo();

    @FXML
    private void initialize() {
        // Inicializa los valores predeterminados si es necesario
    }

    @FXML
    private void botonGuardarPrestamo() {
        // Captura los datos del formulario
        LocalDate fechaPrestamoValor = fechaPrestamo.getValue();
        LocalDate fechaDevolucionValor = fechaDevolucion.getValue();
        String libro = libroPrestado.getText();

        // Crear un nuevo préstamo
        PrestamoModelo nuevoPrestamo = new PrestamoModelo("", fechaPrestamoValor, fechaDevolucionValor, libro);

        try {
            // Llamar al modelo para añadir el nuevo préstamo
            prestamoModelo.anadirPrestamo(nuevoPrestamo);
            mostrarAlerta("Éxito", "Préstamo añadido correctamente.");
            cerrarVentana();
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo añadir el préstamo: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cerrarVentana() {
        // Cierra la ventana actual (solo si se ha abierto como nueva ventana, no en un contexto de escena principal)
        ((Stage) botonGuardar.getScene().getWindow()).close();
    }
}
