package com.example.gestionbiblioteca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class EditarPrestamoController {

    @FXML
    private DatePicker fechaPrestamo;
    @FXML
    private DatePicker fechaDevolucion;
    @FXML
    private TextField libroPrestado;

    private Stage ventana;
    private boolean botonConfirmado = false;

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void botonAceptar() {
        if (fechaPrestamo.getValue() == null || fechaDevolucion.getValue() == null || libroPrestado.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }
        if (fechaPrestamo.getValue().isAfter(fechaDevolucion.getValue())) {
            mostrarAlerta("Error", "La fecha de devolución no puede ser anterior a la fecha de préstamo.");
            return;
        }
        botonConfirmado = true;
        ventana.close();
    }

    @FXML
    private void botonCancelar() {
        ventana.close();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public boolean esBotonConfirmado() {
        return botonConfirmado;
    }
}

