package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.LibroModelo;
import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import com.example.gestionbiblioteca.modelo.PrestamoVO;
import com.example.gestionbiblioteca.modelo.UsuarioVO;
import com.example.gestionbiblioteca.modelo.repository.impl.PrestamoRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VRController {

    @FXML
    private TableView<PrestamoModelo> tablaPrestamos;
    @FXML
    private TableColumn<PrestamoModelo, Integer> columnaCodigo;
    @FXML
    private TableColumn<PrestamoModelo, String> columnaFechaPrestamo;
    @FXML
    private DatePicker fechaPrestamo;
    @FXML
    private DatePicker fechaDevolucion;
    @FXML
    private TextField libroPrestado;
    @FXML
    private TextField dni;

    private ObservableList<PrestamoModelo> listaPrestamo;
    private final PrestamoRepositoryImpl prestamoRepository = new PrestamoRepositoryImpl();
    private Stage ventana;

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void initialize() {
        columnaCodigo.setCellValueFactory(cellData -> cellData.getValue().idPrestamoProperty().asObject());
        columnaFechaPrestamo.setCellValueFactory(cellData -> cellData.getValue().fechaPrestamoProperty().asString());

        cargarDatosPrestamos();

        tablaPrestamos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                rellenarCamposFormulario(newValue);
            }
        });
    }

    private void cargarDatosPrestamos() {
        listaPrestamo = FXCollections.observableArrayList();
        try {
            List<PrestamoModelo> prestamos = prestamoRepository.ObtenerListaPrestamos();
            for (PrestamoModelo prestamo : prestamos) {
                listaPrestamo.add(new PrestamoModelo(prestamo.getDni(),prestamo.getFechaPrestamo(), prestamo.getFechaDevolucion(),
                        prestamo.gettitulo()));

            }

            tablaPrestamos.setItems(listaPrestamo);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista: " + e.getMessage());
        }
    }

    private void rellenarCamposFormulario(PrestamoModelo prestamo) {
        if (fechaPrestamo != null && fechaDevolucion != null && libroPrestado != null) {
            fechaPrestamo.setValue(prestamo.getFechaPrestamo());
            fechaDevolucion.setValue(prestamo.getFechaDevolucion());
            libroPrestado.setText(prestamo.gettitulo());
            dni.setText(prestamo.getDni());
        }
    }

    @FXML
    private void botonNuevoPrestamo() {
        abrirVentana("/com/example/gestionbiblioteca/view/agregarPrestamo.fxml", "Nuevo Préstamo");
    }

    @FXML
    private void botonEditarPrestamo() {
        PrestamoModelo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/view/editarPrestamo.fxml"));
                Parent root = loader.load();
                EditarPrestamoController controller = loader.getController();
                controller.cargarPrestamo(seleccionado);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Editar Préstamo");
                stage.show();
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo abrir la ventana de editar préstamo: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Error", "Seleccione un préstamo para editar.");
        }
    }

    @FXML
    private void botonEliminarPrestamo() {
        // Verificar si un libro ha sido seleccionado en la tabla
        PrestamoModelo prestamoSeleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (prestamoSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un Prestamo para eliminar.");
            return;
        }

        // Confirmación antes de eliminar
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro de que desea eliminar el Prestamo con ID " + prestamoSeleccionado.getIdPrestamo() + "?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            try {
                // Eliminar el libro del repositorio
                prestamoRepository.deletePrestamo(prestamoSeleccionado.getIdPrestamo());

                // Eliminar el libro de la lista y refrescar la tabla
                listaPrestamo.remove(prestamoSeleccionado);

            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar el prestamo.");
            }
        }
    }

    private void abrirVentana(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir la ventana: " + e.getMessage());
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
