package com.example.gestionbiblioteca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CrearUsuarioController {

    @FXML
    private TextField ponerId;
    @FXML
    private TextField ponerNombre;
    @FXML
    private TextField ponerApellido;
    @FXML
    private TextField ponerDireccion;
    @FXML
    private TextField ponerLocalidad;

    private Stage ventana;
    private boolean botonConfirmado = false;

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void botonOk() {
        if (ponerId.getText().isEmpty() || ponerNombre.getText().isEmpty() || ponerApellido.getText().isEmpty() || ponerDireccion.getText().isEmpty() || ponerLocalidad.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
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
