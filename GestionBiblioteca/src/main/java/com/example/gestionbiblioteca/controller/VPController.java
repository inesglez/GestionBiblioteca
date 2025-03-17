package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.Main;
import com.example.gestionbiblioteca.modelo.BibliotecaModelo;
import com.example.gestionbiblioteca.modelo.ExcepcionBiblioteca;
import com.example.gestionbiblioteca.modelo.tablas.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class VPController {

    @FXML
    private TableView<Usuario> tablaUsuarios;
    @FXML
    private TableColumn<Usuario, String> columnaNombre;
    @FXML
    private TableColumn<Usuario, String> columnaApellido;

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
    @FXML
    private TextField ponerProvincia;
    @FXML
    private TextField buscarDni;

    private Main main;
    private BibliotecaModelo bibliotecaModelo;
    private ObservableList<Usuario> usuarioData = FXCollections.observableArrayList();

    public void setBibliotecaModelo(BibliotecaModelo bibliotecaModelo) throws ExcepcionBiblioteca {
        this.bibliotecaModelo = bibliotecaModelo;
    }

    public void setMain(Main main) {
        tablaUsuarios.setItems(main.getUsuarioData());
        this.main = main;
    }

    private void mostrarDatosUsuario(Usuario usuario) {
        if (usuario != null) {
            ponerId.setText(usuario.getDni());
            ponerNombre.setText(usuario.getNombre());
            ponerApellido.setText(usuario.getApellido());
            ponerDireccion.setText(usuario.getDireccion());
            ponerLocalidad.setText(usuario.getLocalidad());
            ponerProvincia.setText(usuario.getProvincia());
        } else {
            ponerId.setText("");
            ponerNombre.setText("");
            ponerApellido.setText("");
            ponerDireccion.setText("");
            ponerLocalidad.setText("");
            ponerProvincia.setText("");
        }
    }

    @FXML
    private void initialize() {
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaApellido.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarDatosUsuario(newValue));
    }

    private void cargarDatosUsuarios() throws ExcepcionBiblioteca, SQLException {
        ArrayList<Usuario> listaUsuarios = bibliotecaModelo.obtenerListaUsuarios();
        usuarioData.setAll(listaUsuarios);
        usuarioData.sort(Comparator.comparing(Usuario::getApellido));
        tablaUsuarios.setItems(usuarioData);
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
        Usuario usuario = new Usuario();
        usuario.setDni(ponerId.getText());
        usuario.setNombre(ponerNombre.getText());
        usuario.setApellido(ponerApellido.getText());
        usuario.setDireccion(ponerDireccion.getText());
        usuario.setLocalidad(ponerLocalidad.getText());
        usuario.setProvincia(ponerProvincia.getText());

        boolean okClicked = main.pantallaCrearUsuario(usuario);
        if (okClicked) {
            try {
                bibliotecaModelo.anadirUsuario(usuario);
                main.getUsuarioData().add(usuario);
                cargarDatosUsuarios();
            } catch (ExcepcionBiblioteca | SQLException e) {
                mostrarAlerta("Error", "No se puede conectar con la base de datos para añadir el usuario");
            }
        }
    }

    @FXML
    private void botonEditarUsuario() {
        Usuario usuarioSelecc = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSelecc != null) {
            usuarioSelecc.setDni(ponerId.getText());
            usuarioSelecc.setNombre(ponerNombre.getText());
            usuarioSelecc.setApellido(ponerApellido.getText());
            usuarioSelecc.setDireccion(ponerDireccion.getText());
            usuarioSelecc.setLocalidad(ponerLocalidad.getText());
            usuarioSelecc.setProvincia(ponerProvincia.getText());

            boolean okClicked = main.pantallaEditarUsuario(usuarioSelecc);
            if (okClicked) {
                try {
                    bibliotecaModelo.editarUsuario(usuarioSelecc);
                    mostrarDatosUsuario(usuarioSelecc);
                    cargarDatosUsuarios();
                } catch (ExcepcionBiblioteca | SQLException e) {
                    mostrarAlerta("Error", "No se pudo editar al usuario");
                }
            }
        } else {
            mostrarAlerta("Nada seleccionado", "Seleccione un usuario para editar");
        }
    }

    @FXML
    private void botonEliminarUsuario() {
        Usuario usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            try {
                bibliotecaModelo.eliminarUsuario(usuarioSeleccionado);
                cargarDatosUsuarios();
            } catch (ExcepcionBiblioteca | SQLException e) {
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
            Usuario usuario = bibliotecaModelo.buscarDNI(dniBuscado);
            if (usuario == null) {
                mostrarAlerta("Usuario no encontrado", "No se ha encontrado un usuario con el DNI proporcionado.");
                return;
            }
            mostrarDatosUsuario(usuario);
            usuarioData.setAll(usuario);
            tablaUsuarios.setItems(usuarioData);
        } catch (ExcepcionBiblioteca | SQLException e) {
            mostrarAlerta("Error", e.getMessage());
        }
    }
}
