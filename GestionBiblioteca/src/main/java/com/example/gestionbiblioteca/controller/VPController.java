package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.Main;
import com.example.gestionbiblioteca.modelo.ExcepcionBiblioteca;
import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import com.example.gestionbiblioteca.modelo.UsuarioModelo;
import com.example.gestionbiblioteca.modelo.repository.ExceptionPrestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class VPController {

    @FXML
    private TableView<UsuarioModelo> tablaUsuarios;
    @FXML
    private TableColumn<UsuarioModelo, String> columnaNombre;
    @FXML
    private TableColumn<UsuarioModelo, String> columnaApellidos;

    @FXML
    private TextField ponerId;
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
    @FXML
    private TextField buscarDni;

    private Main main;
    private PrestamoModelo prestamoModelo;
    private ObservableList<UsuarioModelo> usuarioModeloData = FXCollections.observableArrayList();

    public void setBibliotecaModelo(PrestamoModelo prestamoModelo) throws ExceptionPrestamo {
        this.prestamoModelo = prestamoModelo;
    }

    public void setMain(Main main) {
        tablaUsuarios.setItems(main.getUsuarioData());
        this.main = main;
    }

    private void mostrarDatosUsuario(UsuarioModelo usuarioModelo) {
        if (usuarioModelo != null) {
            ponerId.setText(usuarioModelo.getDni());
            ponerNombre.setText(usuarioModelo.getNombre());
            ponerApellidos.setText(usuarioModelo.getApellidos());
            ponerDireccion.setText(usuarioModelo.getDireccion());
            ponerLocalidad.setText(usuarioModelo.getLocalidad());
            ponerProvincia.setText(usuarioModelo.getProvincia());
        } else {
            ponerId.setText("");
            ponerNombre.setText("");
            ponerApellidos.setText("");
            ponerDireccion.setText("");
            ponerLocalidad.setText("");
            ponerProvincia.setText("");
        }
    }

    @FXML
    private void initialize() {
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDatosUsuario(newValue));
    }

    private void cargarDatosUsuarios() throws ExceptionPrestamo, SQLException {
        ArrayList<UsuarioModelo> listaUsuarioModelos = prestamoModelo.obtenerListaUsuarios();
        usuarioModeloData.setAll(listaUsuarioModelos);
        usuarioModeloData.sort(Comparator.comparing(UsuarioModelo::getApellidos));
        tablaUsuarios.setItems(usuarioModeloData);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void botonNuevoUsuario() {
        UsuarioModelo usuarioModelo = new UsuarioModelo();
        usuarioModelo.setDni(ponerId.getText());
        usuarioModelo.setNombre(ponerNombre.getText());
        usuarioModelo.setApellidos(ponerApellidos.getText());
        usuarioModelo.setDireccion(ponerDireccion.getText());
        usuarioModelo.setLocalidad(ponerLocalidad.getText());
        usuarioModelo.setProvincia(ponerProvincia.getText());

        boolean okClicked = main.pantallaCrearUsuario(usuarioModelo);
        if (okClicked) {
            try {
                prestamoModelo.anadirUsuario(usuarioModelo);
                main.getUsuarioData().add(usuarioModelo);
                cargarDatosUsuarios();
            } catch (ExceptionPrestamo| SQLException e) {
                mostrarAlerta("Error", "No se puede conectar con la base de datos para añadir el usuario");
            }
        }
    }

    @FXML
    private void botonEditarUsuario() {
        UsuarioModelo usuarioModeloSelecc = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioModeloSelecc != null) {
            usuarioModeloSelecc.setDni(ponerId.getText());
            usuarioModeloSelecc.setNombre(ponerNombre.getText());
            usuarioModeloSelecc.setApellidos(ponerApellidos.getText());
            usuarioModeloSelecc.setDireccion(ponerDireccion.getText());
            usuarioModeloSelecc.setLocalidad(ponerLocalidad.getText());
            usuarioModeloSelecc.setProvincia(ponerProvincia.getText());

            boolean okClicked = main.pantallaEditarUsuario(usuarioModeloSelecc);
            if (okClicked) {
                try {
                    prestamoModelo.editarUsuario(usuarioModeloSelecc);
                    mostrarDatosUsuario(usuarioModeloSelecc);
                    cargarDatosUsuarios();
                } catch (ExceptionPrestamo | SQLException e) {
                    mostrarAlerta("Error", "No se pudo editar al usuario");
                }
            }
        } else {
            mostrarAlerta("Nada seleccionado", "Seleccione un usuario para editar");
        }
    }

    @FXML
    private void botonEliminarUsuario() {
        UsuarioModelo usuarioModeloSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioModeloSeleccionado != null) {
            try {
                prestamoModelo.eliminarUsuario(usuarioModeloSeleccionado);
                cargarDatosUsuarios();
            } catch (ExceptionPrestamo | SQLException e) {
                mostrarAlerta("Error", "No se pudo eliminar al usuario");
            }
        } else {
            mostrarAlerta("Nada seleccionado", "Seleccione un usuario para eliminar");
        }
    }

    @FXML
    private void busquedaDni() {
        try {
            String dniBuscado = buscarDni.getText().trim();
            if (dniBuscado.isEmpty()) {
                mostrarAlerta("Búsqueda vacía", "Ingrese un DNI.");
                return;
            }
            UsuarioModelo usuarioModelo = prestamoModelo.buscarDNI(dniBuscado);
            if (usuarioModelo == null) {
                mostrarAlerta("Usuario no encontrado", "No se ha encontrado un usuario con el DNI proporcionado.");
                return;
            }
            mostrarDatosUsuario(usuarioModelo);
            usuarioModeloData.setAll(usuarioModelo);
            tablaUsuarios.setItems(usuarioModeloData);
        } catch (ExceptionPrestamo| SQLException e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }
}
