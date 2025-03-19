package com.example.gestionbiblioteca.controller;


import com.example.gestionbiblioteca.Main;
import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import com.example.gestionbiblioteca.modelo.PrestamoVO;
import com.example.gestionbiblioteca.modelo.repository.ExceptionPrestamo;
import com.example.gestionbiblioteca.modelo.repository.ExceptionUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

// Esta clase es el controlador de los detalles del préstamo, contiene los botones de editar y eliminar.
public class DetallesPrestamoController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label idPrestamoLabel;
    @FXML
    private Label fechaPrestamoLabel;
    @FXML
    private Label fechaDevolucionLabel;
    @FXML
    private Label libroPrestadoLabel;
    @FXML
    private Label dniClienteLabel;
    @FXML
    private Label nombreClienteLabel; // Nuevo campo para el nombre del cliente
    @FXML
    private Button cancelButton;
    @FXML
    private Button editButton;

    private PrestamoVO prestamo;
    private PrestamoModelo prestamoModelo;
    private PrestamoController prestamoController;
    private Main main;

    @FXML
    private void initialize() {
    }

    // Inyectamos el Main
    public void setMain(Main main) {
        this.main = main;
    }

    // Inyectamos el controller de préstamos para después pasarle el elemento que se ha creado
    public void setPrestamoController(PrestamoController prestamoController) {
        this.prestamoController = prestamoController;
    }

    // Inyectamos el modelo de Prestamo
    public void setPrestamoModelo(PrestamoModelo prestamoModelo) {
        this.prestamoModelo = prestamoModelo;
    }

    // Eliminamos el préstamo de la base de datos y de la vista
    public void eliminarPrestamo() throws ExceptionUsuario {
        // Eliminar préstamo en el modelo
        prestamoModelo.eliminarPrestamo(prestamo.getIdPrestamo(), prestamo.getDniCliente());

        prestamoController.eliminarTarjetasPrestamo(prestamo.getIdPrestamo());

        // Cerrar el stage después de eliminar
        cerrar();
    }

    // Llamamos a la función del main que abre la pantalla de editar con los datos del préstamo actual
    public void editarPrestamo() throws ExceptionPrestamo, IOException {
        // Verificamos si la fecha de devolución está en el futuro
        LocalDate fechaActual = LocalDate.now();
        if (prestamo.getFechaDevolucion().isAfter(fechaActual)) {
            main.AñadirPrestamoEditar(prestamo);
            cerrar();
        } else {
            mostrarAlerta("No se puede editar un préstamo pasado", "El préstamo que intentas editar ya ha sido devuelto y no puede modificarse.");
        }
    }

    // Mostrar alerta
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    // Colocamos los datos del préstamo
    public void setData(PrestamoVO prestamo) {
        this.prestamo = prestamo;
        idPrestamoLabel.setText(String.valueOf(prestamo.getIdPrestamo()));
        fechaPrestamoLabel.setText(String.valueOf(prestamo.getFechaPrestamo()));
        fechaDevolucionLabel.setText(String.valueOf(prestamo.getFechaDevolucion()));
        libroPrestadoLabel.setText(prestamo.getLibroPrestado());
        dniClienteLabel.setText(prestamo.getDniCliente());
        nombreClienteLabel.setText(getNombreCliente(prestamo.getDniCliente())); // Asumimos que tienes un método para obtener el nombre
    }

    // Método para obtener el nombre del cliente a partir de su DNI
    private String getNombreCliente(String dniCliente) {
        // Aquí deberías implementar la lógica para obtener el nombre del cliente usando el DNI
        return "Nombre del Cliente"; // Reemplazar con la lógica real
    }

    // Cerramos la ventana
    @FXML
    public void cerrar() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
