package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.Main;
import com.example.gestionbiblioteca.modelo.ExcepcionBiblioteca;
import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class VRController {

    @FXML
    private TableView<PrestamoModelo> tablaPrestamos;
    @FXML
    private TableColumn<PrestamoModelo, String> columnaCodigo;
    @FXML
    private TableColumn<PrestamoModelo, String> columnaFechaPrestamo;

    @FXML
    private DatePicker fechaPrestamo;
    @FXML
    private DatePicker fechaDevolucion;
    @FXML
    private TextField libroPrestado;

    private Main main;
    private PrestamoModelo prestamoModelo;
    private ObservableList<PrestamoModelo> prestamoModeloData = FXCollections.observableArrayList();
    private String dniUsuarioSeleccionado;

    public void setBibliotecaModelo(PrestamoModelo prestamoModelo) throws ExcepcionBiblioteca {
        this.prestamoModelo = prestamoModelo;
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
        tablaPrestamos.setItems(prestamoModeloData);

        tablaPrestamos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDatosPrestamo(newValue));
    }

    private void cargarDatosPrestamos() {
        try {
            ArrayList<PrestamoModelo> listaPrestamoModelos = prestamoModelo.obtenerListaPrestamos();
            prestamoModeloData.setAll(listaPrestamoModelos);
        } catch (ExcepcionBiblioteca | SQLException e) {
            mostrarAlerta("Error", "No se pudieron cargar los préstamos: " + e.getMessage());
        }
    }

    private void mostrarDatosPrestamo(PrestamoModelo prestamoModelo) {
        if (prestamoModelo != null) {
            fechaPrestamo.setValue(prestamoModelo.getFechaPrestamo());
            fechaDevolucion.setValue(prestamoModelo.getFechaDevolucion());
            libroPrestado.setText(prestamoModelo.getLibro());
        } else {
            fechaPrestamo.setValue(null);
            fechaDevolucion.setValue(null);
            libroPrestado.setText("");
        }
    }

    @FXML
    private void botonNuevoPrestamo() {
        PrestamoModelo nuevoPrestamoModelo = new PrestamoModelo();
        nuevoPrestamoModelo.setDniUsuario(dniUsuarioSeleccionado);
        nuevoPrestamoModelo.setFechaPrestamo(fechaPrestamo.getValue());
        nuevoPrestamoModelo.setFechaDevolucion(fechaDevolucion.getValue());
        nuevoPrestamoModelo.setLibro(libroPrestado.getText());

        boolean okClicked = main.pantallaEditarCrearPrestamo(nuevoPrestamoModelo);
        if (okClicked) {
            try {
                prestamoModelo.anadirPrestamo(nuevoPrestamoModelo);
                prestamoModeloData.add(nuevoPrestamoModelo);
            } catch (ExcepcionBiblioteca e) {
                mostrarAlerta("Error", "No se pudo añadir el préstamo: " + e.getMessage());
            }
        }
    }

    @FXML
    private void botonEditarPrestamo() {
        PrestamoModelo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setFechaPrestamo(fechaPrestamo.getValue());
            seleccionado.setFechaDevolucion(fechaDevolucion.getValue());
            seleccionado.setLibro(libroPrestado.getText());

            boolean okClicked = main.pantallaEditarCrearPrestamo(seleccionado);
            if (okClicked) {
                try {
                    prestamoModelo.editarPrestamo(seleccionado);
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
        PrestamoModelo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                prestamoModelo.eliminarPrestamo(seleccionado.getIdPrestamo());
                prestamoModeloData.remove(seleccionado);
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
