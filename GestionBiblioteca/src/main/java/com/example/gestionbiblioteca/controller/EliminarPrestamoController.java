package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EliminarPrestamoController {

    @FXML
    private Label mensajeConfirmacion;
    @FXML
    private Button botonEliminar;
    @FXML
    private Button botonCancelar;

    private PrestamoModelo prestamoModelo = new PrestamoModelo();
    private PrestamoModelo prestamoSeleccionado;

    public void cargarPrestamo(PrestamoModelo prestamo) {
        this.prestamoSeleccionado = prestamo;
        // Mostrar un mensaje de confirmación
        mensajeConfirmacion.setText("¿Está seguro de que desea eliminar el préstamo?");
    }

    @FXML
    private void botonEliminarPrestamo() {
        try {
            // Llamar al modelo para eliminar el préstamo
            prestamoModelo.eliminarPrestamo(prestamoSeleccionado.getIdPrestamo());
            mostrarAlerta("Éxito", "Préstamo eliminado correctamente.");
            cerrarVentana();
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo eliminar el préstamo: " + e.getMessage());
        }
    }

    @FXML
    private void botonCancelar() {
        // Cierra la ventana de eliminación sin hacer nada
        cerrarVentana();
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
        ((Stage) botonEliminar.getScene().getWindow()).close();
    }
}
