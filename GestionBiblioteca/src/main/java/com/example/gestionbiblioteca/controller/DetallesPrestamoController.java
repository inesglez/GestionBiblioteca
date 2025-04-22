package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class DetallesPrestamoController {

    @FXML
    private Label labelCodigo;
    @FXML
    private Label labelFechaPrestamo;
    @FXML
    private Label labelFechaDevolucion;
    @FXML
    private Label labelLibro; // Esta etiqueta ahora mostrará el idLibro, no el nombre
    @FXML
    private Label labelUsuario;

    private PrestamoModelo prestamoModelo = new PrestamoModelo();
    private PrestamoModelo prestamoSeleccionado;

    // Método para cargar el préstamo seleccionado
    public void cargarDetalles(PrestamoModelo prestamo) {
        this.prestamoSeleccionado = prestamo;

        // Cargar los detalles del préstamo
        labelCodigo.setText(String.valueOf(prestamo.getIdPrestamo())); // Convertir int a String
        labelFechaPrestamo.setText(prestamo.getFechaPrestamo().toString());
        labelFechaDevolucion.setText(prestamo.getFechaDevolucion().toString());
        labelLibro.setText(String.valueOf(prestamo.getIdLibro())); // Mostrar el idLibro en lugar del nombre del libro
        labelUsuario.setText(prestamo.getDni());
    }

    // Método para cerrar la ventana de detalles
    @FXML
    private void botonCerrarVentana() {
        // Cerrar la ventana actual (solo si se ha abierto como nueva ventana, no en un contexto de escena principal)
        ((Stage) labelCodigo.getScene().getWindow()).close();
    }
}