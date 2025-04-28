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

        // El campo ID no debería poder modificarse
        ponerId.setDisable(true);
    }

    @FXML
    private void botonOk() {
        if (ponerNombre.getText().isEmpty() || ponerApellido.getText().isEmpty()
                || ponerDireccion.getText().isEmpty() || ponerLocalidad.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        // Actualizar los datos
        usuario.setNombre(ponerNombre.getText());
        usuario.setApellidos(ponerApellido.getText());
        usuario.setDireccion(ponerDireccion.getText());
        usuario.setLocalidad(ponerLocalidad.getText());

        try {
            usuarioRepository.editUsuario(usuario);

            // MOSTRAR ALERTA Y NO CERRAR INSTANTÁNEAMENTE
            Alert alertaExito = new Alert(Alert.AlertType.INFORMATION);
            alertaExito.setTitle("Éxito");
            alertaExito.setHeaderText(null);
            alertaExito.setContentText("Usuario editado con éxito.");

            // Mostrar el Alert de manera NO bloqueante
            alertaExito.show();

            // **USAR EVENTO** cuando el usuario cierre la alerta
            alertaExito.setOnHidden(event -> {
                botonConfirmado = true;
                ventana.close(); // recién ahí cerramos
            });

        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el usuario.");
        }
    }


    @FXML
    private void botonCancelar() {
        ventana.close();
    }

    // Mejorado para aceptar el tipo de alerta
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
