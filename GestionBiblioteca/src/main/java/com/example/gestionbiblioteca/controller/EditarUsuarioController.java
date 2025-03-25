package com.example.gestionbiblioteca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.gestionbiblioteca.modelo.UsuarioVO;
import com.example.gestionbiblioteca.modelo.repository.impl.UsuarioRepositoryImpl;

public class EditarUsuarioController {

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
    private UsuarioVO usuario;
    private boolean botonConfirmado = false;

    private final UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
        // Poner los datos existentes en los campos de texto
        ponerId.setText(usuario.getDNI());
        ponerNombre.setText(usuario.getNombre());
        ponerApellido.setText(usuario.getApellidos());
        ponerDireccion.setText(usuario.getDireccion());
        ponerLocalidad.setText(usuario.getLocalidad());
    }

    @FXML
    private void botonOk() {
        if (ponerId.getText().isEmpty() || ponerNombre.getText().isEmpty() || ponerApellido.getText().isEmpty() || ponerDireccion.getText().isEmpty() || ponerLocalidad.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        // Actualizar los datos del usuario
        usuario.setDni(ponerId.getText());
        usuario.setNombre(ponerNombre.getText());
        usuario.setApellidos(ponerApellido.getText());
        usuario.setDireccion(ponerDireccion.getText());
        usuario.setLocalidad(ponerLocalidad.getText());

        // Realizar la edición en la base de datos
        try {
            usuarioRepository.editUsuario(usuario);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar el usuario.");
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
