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
    private TextField libroPrestado;  // Este campo ya no es necesario para el libro (si solo se usa idLibro)
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
            String libroIdText = libroPrestado.getText(); // Asumimos que el TextField contiene el idLibro

            // Validación de campos
            if (fechaPrestamoValor == null || fechaDevolucionValor == null || libroIdText.isEmpty()) {
                mostrarAlerta("Advertencia", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
                return;
            }

            if (fechaDevolucionValor.isBefore(fechaPrestamoValor)) {
                mostrarAlerta("Advertencia", "La fecha de devolución no puede ser anterior a la fecha de préstamo.", Alert.AlertType.WARNING);
                return;
            }

            // Validación para que las fechas no sean anteriores a la fecha actual
            if (fechaPrestamoValor.isBefore(LocalDate.now()) || fechaDevolucionValor.isBefore(LocalDate.now())) {
                mostrarAlerta("Advertencia", "Las fechas no pueden ser anteriores a la fecha actual.", Alert.AlertType.WARNING);
                return;
            }

            // Convertir el idLibro desde el texto
            int idLibro;
            try {
                idLibro = Integer.parseInt(libroIdText); // Convertimos el texto a int
            } catch (NumberFormatException e) {
                mostrarAlerta("Advertencia", "El ID del libro no es válido.", Alert.AlertType.WARNING);
                return;
            }

            // Crear el préstamo
            PrestamoModelo nuevoPrestamo = new PrestamoModelo("", fechaPrestamoValor, fechaDevolucionValor, idLibro); // Cambiar libro por idLibro
            prestamoModelo.anadirPrestamo(nuevoPrestamo);

            mostrarAlerta("Éxito", "Préstamo añadido correctamente.", Alert.AlertType.INFORMATION);
            cerrarVentana();

        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo añadir el préstamo: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
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
