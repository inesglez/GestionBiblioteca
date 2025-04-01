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
    private Stage ventana; // Agregado para recibir la ventana desde el MainController

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void initialize() {
        // Inicializa valores si es necesario
    }

    @FXML
    private void botonGuardarPrestamo() {
        try {
            LocalDate fechaPrestamoValor = fechaPrestamo.getValue();
            LocalDate fechaDevolucionValor = fechaDevolucion.getValue();
            String libro = libroPrestado.getText();

            // Validación de campos
            if (fechaPrestamoValor == null || fechaDevolucionValor == null || libro.isEmpty()) {
                mostrarAlerta("Advertencia", "Todos los campos son obligatorios.");
                return;
            }

            if (fechaDevolucionValor.isBefore(fechaPrestamoValor)) {
                mostrarAlerta("Advertencia", "La fecha de devolución no puede ser anterior a la fecha de préstamo.");
                return;
            }

            // Crear el préstamo
            PrestamoModelo nuevoPrestamo = new PrestamoModelo("", fechaPrestamoValor, fechaDevolucionValor, libro);
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

    @FXML
    public void cerrarVentana() {
        // Usamos el componente fechaPrestamo para obtener la ventana
        Stage stage = (Stage) fechaPrestamo.getScene().getWindow();
        stage.close();
    }

}
