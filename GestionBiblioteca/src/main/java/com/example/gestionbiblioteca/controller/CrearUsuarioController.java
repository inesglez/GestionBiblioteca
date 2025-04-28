package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.repository.impl.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearUsuarioController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField ponerDNI;
    @FXML
    private TextField ponerNombre;
    @FXML
    private TextField ponerApellidos;
    @FXML
    private TextField ponerDireccion;
    @FXML
    private TextField ponerLocalidad;
    @FXML
    private TextField ponerProvincia;

    private Stage ventana;
    private boolean botonConfirmado = false;

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void botonOk() {
        // Validar que todos los campos estén completos
        if (ponerDNI.getText().isEmpty() || ponerNombre.getText().isEmpty() || ponerApellidos.getText().isEmpty() ||
                ponerDireccion.getText().isEmpty() || ponerLocalidad.getText().isEmpty() || ponerProvincia.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        // Si todo está bien, proceder a guardar en la base de datos
        try (Connection conn = new Conexion().conectarBD()) {
            String sql = "INSERT INTO usuarios (dni, nombre, apellidos, direccion, localidad, provincia) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, ponerDNI.getText());
                stmt.setString(2, ponerNombre.getText());
                stmt.setString(3, ponerApellidos.getText());
                stmt.setString(4, ponerDireccion.getText());
                stmt.setString(5, ponerLocalidad.getText());
                stmt.setString(6, ponerProvincia.getText());

                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Usuario creado con éxito.");
                    botonConfirmado = true;
                    ventana.close();
                } else {
                    mostrarAlerta(Alert.AlertType.ERROR, "Error", "Hubo un problema al guardar el usuario.");
                }
            } catch (SQLException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al insertar datos en la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error de conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void botonCancelar() {
        if (ventana != null) {
            ventana.close();
        }
    }

    // Aquí el método mejorado
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public boolean esBotonConfirmado() {
        return botonConfirmado;
    }
}
