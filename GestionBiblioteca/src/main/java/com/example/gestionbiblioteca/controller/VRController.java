package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.Main;
import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import com.example.gestionbiblioteca.modelo.repository.impl.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
    private PrestamoModelo prestamoModelo = new PrestamoModelo();
    private ObservableList<PrestamoModelo> prestamoModeloData = FXCollections.observableArrayList();
    private String dniUsuarioSeleccionado;

    public void setMain(Main main) {
        this.main = main;
        cargarDatosPrestamos();
    }

    public String getDniUsuarioSeleccionado() {
        return dniUsuarioSeleccionado;
    }

    @FXML
    private void initialize() {
        columnaCodigo.setCellValueFactory(cellData -> cellData.getValue().idPrestamoProperty().asString());
        columnaFechaPrestamo.setCellValueFactory(cellData -> cellData.getValue().fechaPrestamoProperty().asString());
        tablaPrestamos.setItems(prestamoModeloData);
    }

    private void cargarDatosPrestamos() {
        try {
            List<PrestamoModelo> listaPrestamos = prestamoModelo.obtenerListaPrestamos();
            prestamoModeloData.setAll(listaPrestamos);
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudieron cargar los préstamos: " + e.getMessage());
        }
    }

    @FXML
    private void botonNuevoPrestamo() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/view/agregarPrestamo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Nuevo Préstamo");
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir la ventana de nuevo préstamo: " + e.getMessage());
        }
    }

    @FXML
    private void botonEditarPrestamo() {
        PrestamoModelo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/view/editarPrestamo.fxml"));
                Parent root = loader.load();
                EditarPrestamoController controller = loader.getController();
                controller.cargarPrestamo(seleccionado);  // Enviar el préstamo seleccionado
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
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
        PrestamoModelo seleccionado = tablaPrestamos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionbiblioteca/view/eliminarPrestamo.fxml"));
                Parent root = loader.load();
                EliminarPrestamoController controller = loader.getController();
                controller.cargarPrestamo(seleccionado);  // Enviar el préstamo seleccionado
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Eliminar Préstamo");
                stage.show();
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo abrir la ventana de eliminar préstamo: " + e.getMessage());
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

    public void eliminarTarjetasPrestamo(int idPrestamo) {
        // Usamos la clase Conexion para obtener la conexión
        try (Connection conn = new Conexion().conectarBD()) {
            String sql = "DELETE FROM tarjetas WHERE idPrestamo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idPrestamo);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudieron eliminar las tarjetas asociadas al préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
