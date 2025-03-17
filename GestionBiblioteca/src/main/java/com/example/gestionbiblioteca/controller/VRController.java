package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.Main;
import com.example.gestionbiblioteca.modelo.BibliotecaModelo;
import com.example.gestionbiblioteca.modelo.ExcepcionBiblioteca;
import com.example.gestionbiblioteca.modelo.tablas.Prestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VRController {

    @FXML
    private TableView<Prestamo> tablaPrestamos;
    @FXML
    private TableColumn<Prestamo, String> columnaCodigo;
    @FXML
    private TableColumn<Prestamo, String> columnaFechaPrestamo;

    @FXML
    private DatePicker fechaPrestamo;
    @FXML
    private DatePicker fechaDevolucion;
    @FXML
    private TextField libroPrestado;

    private Main main;
    private BibliotecaModelo bibliotecaModelo;
    private ObservableList<Prestamo> prestamoData = FXCollections.observableArrayList();
    private String dniUsuarioSeleccionado;

    public void setBibliotecaModelo(BibliotecaModelo bibliotecaModelo) throws ExcepcionBiblioteca {
        this.bibliotecaModelo = bibliotecaModelo;
        cargarDatosPrestamos();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getDniUsuarioSeleccionado() {
        return dniUsuarioSeleccionado;
    }

    @FXML
    private void initialize() {
        columnaCodigo.setCellValueFactory(cellData -> cellData.getValue().idPrestamoProperty().asString());
        columnaFechaPrestamo.setCellValueFactory(cellData -> cellData.getValue().fechaPrestamoProperty().asString());
        tablaPrestamos.setItems(prestamoData);

        tablaPrestamos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDatosPrestamo(newValue));
    }

    private void cargarDatosPrestamos() {
        try {
            ArrayList<Prestamo> listaPrestamos = bibliotecaModelo.obtenerListaPrestamos();
            prestamoData.setAll(listaPrestamos);
        } catch (ExcepcionBiblioteca | SQLException e) {
            mostrarAlerta("Error", "No se pudieron cargar los préstamos: " + e.getMessage());
        }
    }

    private void mostrarDatosPrestamo(Prestamo prestamo) {
        if (prestamo != null) {
            fechaPrestamo.setValue(prestamo.getFechaPrestamo());
            fechaDevolucion.setValue(prestamo.getFechaDevolucion());
            libroPrestado.setText(prestamo.getLibro());
        } else {
            fechaPrestamo.setValue(null);
            fechaDevolucion.setValue(null);
            libroPrestado.setText("");
        }
    }

    @FXML
    private void botonNuevoPrestamo() {
        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setDniUsuario(dniUsuarioSeleccionado);
        nuevoPrestamo.setFechaPrestamo(fechaPrestamo.getValue());
        nuevoPrestamo.setFechaDevolucion(fechaDevolucion.getValue());
        nuevoPrestamo.setLibro(libroPrestado.getText());

        boolean okClicked = main.pantallaEditarCrearPrestamo(nuevoPrestamo);
        if (okClicked) {
            try {
                bibliotecaModelo.anadirPrestamo(nuevoPrestamo);
                prestamoData.add(nuevoPrestamo);
            } catch (ExcepcionBiblioteca e) {
                mostrarAlerta("Error", "No se pudo añadir el préstamo: " + e.getMessage());
            }
        }
    }

    @FXML
    private void botonEditarPrestamo() {
        Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setFechaPrestamo(fechaPrestamo.getValue());
            seleccionado.setFechaDevolucion(fechaDevolucion.getValue());
            seleccionado.setLibro(libroPrestado.getText());

            boolean okClicked = main.pantallaEditarCrearPrestamo(seleccionado);
            if (okClicked) {
                try {
                    bibliotecaModelo.editarPrestamo(seleccionado);
                    mostrarDatosPrestamo(seleccionado);
                } catch (ExcepcionBiblioteca e) {
                    mostrarAlerta("Error", "No se pudo actualizar el préstamo: " + e.getMessage());
                }
            }
        } else {
            mostrarAlerta("Error", "Seleccione un préstamo para editar.");
        }
    }

    @FXML
    private void botonEliminarPrestamo() {
        Prestamo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                bibliotecaModelo.eliminarPrestamo(seleccionado.getIdPrestamo());
                prestamoData.remove(seleccionado);
            } catch (ExcepcionBiblioteca e) {
                mostrarAlerta("Error", "No se pudo eliminar el préstamo: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Error", "Seleccione un préstamo para eliminar.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
