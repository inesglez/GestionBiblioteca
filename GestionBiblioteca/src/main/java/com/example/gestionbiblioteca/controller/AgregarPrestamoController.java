package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.repository.impl.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AgregarPrestamoController {

    @FXML
    private TextField dni;
    @FXML
    private TextField nombrePersona; // NUEVO
    @FXML
    private DatePicker fechaPrestamo;
    @FXML
    private DatePicker fechaDevolucion;
    @FXML
    private TextField libroPrestado;
    @FXML
    private Button botonGuardar;

    private Stage ventana;

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void initialize() {
        // Evento al perder foco en el campo DNI
        dni.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // perdió el foco
                buscarNombrePorDNI();
            }
        });
    }

    private void buscarNombrePorDNI() {
        String dniValor = dni.getText();

        if (dniValor.isEmpty()) {
            nombrePersona.setText("");
            return;
        }

        String sql = "SELECT nombre FROM usuarios WHERE dni = ?";

        try (Connection conn = new Conexion().conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dniValor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                nombrePersona.setText(rs.getString("nombre"));
            } else {
                nombrePersona.setText("No encontrado");
            }

        } catch (SQLException e) {
            nombrePersona.setText("Error al buscar");
        }
    }

    @FXML
    private void botonGuardarPrestamo() {
        String dniValor = dni.getText();
        LocalDate fechaPrestamoValor = fechaPrestamo.getValue();
        LocalDate fechaDevolucionValor = fechaDevolucion.getValue();
        String libroIdText = libroPrestado.getText();


        if (dniValor.isEmpty() || fechaPrestamoValor == null || fechaDevolucionValor == null || libroIdText.isEmpty()) {
            mostrarAlerta("Advertencia", "Todos los campos son obligatorios.", Alert.AlertType.WARNING);
            return;
        }

        if (fechaDevolucionValor.isBefore(fechaPrestamoValor)) {
            mostrarAlerta("Advertencia", "La fecha de devolución no puede ser anterior a la fecha de préstamo.", Alert.AlertType.WARNING);
            return;
        }

        if (fechaPrestamoValor.isBefore(LocalDate.now()) || fechaDevolucionValor.isBefore(LocalDate.now())) {
            mostrarAlerta("Advertencia", "Las fechas no pueden ser anteriores a la fecha actual.", Alert.AlertType.WARNING);
            return;
        }

        String titulo;
        try {
            titulo = libroIdText;
        } catch (NumberFormatException e) {
            mostrarAlerta("Advertencia", "El ID del libro no es válido.", Alert.AlertType.WARNING);
            return;
        }

        String sql = "INSERT INTO prestamos (dni, fechaPrestamo, fechaDevolucion, titulo) VALUES (?, ?, ?, ?)";

        try (Connection conn = new Conexion().conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dniValor);
            stmt.setDate(2, java.sql.Date.valueOf(fechaPrestamoValor));
            stmt.setDate(3, java.sql.Date.valueOf(fechaDevolucionValor));
            stmt.setString(4, titulo);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                mostrarAlerta("Éxito", "Préstamo añadido correctamente.", Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                mostrarAlerta("Error", "No se pudo añadir el préstamo.", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            mostrarAlerta("Error", "Error al insertar el préstamo: " + e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
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
        Stage stage = (Stage) fechaPrestamo.getScene().getWindow();
        stage.close();
    }
}
