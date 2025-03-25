package com.example.gestionbiblioteca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.gestionbiblioteca.modelo.repository.impl.UsuarioRepositoryImpl;

public class EliminarUsuarioController {

    @FXML
    private TextField ponerId;

    private Stage ventana;
    private boolean botonConfirmado = false;

    private final UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void botonEliminar() {
        String dniUsuario = ponerId.getText();

        if (dniUsuario.isEmpty()) {
            mostrarAlerta("Error", "El campo de DNI es obligatorio.");
            return;
        }

        // Confirmación antes de eliminar
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro de que desea eliminar al usuario con DNI " + dniUsuario + "?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            try {
                usuarioRepository.deleteUsuario(dniUsuario);
                botonConfirmado = true;
                ventana.close();
            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar el usuario.");
            }
        }
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
