package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class EditarPrestamoController {

    @FXML
    private DatePicker fechaPrestamo;
    @FXML
    private DatePicker fechaDevolucion;
    @FXML
    private TextField libroPrestado;
    @FXML
    private Button botonGuardar;

    private PrestamoModelo prestamoModelo = new PrestamoModelo();
    private PrestamoModelo prestamoSeleccionado;

    public void cargarPrestamo(PrestamoModelo prestamo) {
        this.prestamoSeleccionado = prestamo;
        // Cargar los datos del préstamo seleccionado
        fechaPrestamo.setValue(prestamo.getFechaPrestamo());
        fechaDevolucion.setValue(prestamo.getFechaDevolucion());
        libroPrestado.setText(prestamo.getLibro());
    }

    @FXML
    private void botonGuardarPrestamo() {
        // Actualiza los datos del préstamo seleccionado
        prestamoSeleccionado.setFechaPrestamo(fechaPrestamo.getValue());
        prestamoSeleccionado.setFechaDevolucion(fechaDevolucion.getValue());
        prestamoSeleccionado.setLibro(libroPrestado.getText());

        try {
            // Llamar al modelo para actualizar el préstamo
            prestamoModelo.editarPrestamo(prestamoSeleccionado);
            mostrarAlerta("Éxito", "Préstamo actualizado correctamente.");
            cerrarVentana();
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo actualizar el préstamo: " + e.getMessage());
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
