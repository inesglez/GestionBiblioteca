package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.UsuarioModelo;
import com.example.gestionbiblioteca.modelo.UsuarioVO;
import com.example.gestionbiblioteca.modelo.repository.impl.UsuarioRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class VPController {

    @FXML
    private TableView<UsuarioModelo> tablaUsuarios;
    @FXML
    private TableColumn<UsuarioModelo, String> columnaNombre;
    @FXML
    private TableColumn<UsuarioModelo, String> columnaApellido;

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
    private Button botonNuevoUsuario;
    @FXML
    private Button botonEditarUsuario;
    @FXML
    private Button botonEliminarUsuario;

    private ObservableList<UsuarioModelo> listaUsuarios;
    private final UsuarioRepositoryImpl usuarioRepository = new UsuarioRepositoryImpl();
    private Stage ventana;

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    public void initialize() {
        // Inicializar las columnas de la tabla
        columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        columnaApellido.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        // Cargar los usuarios
        cargarUsuarios();

        // Listener para seleccionar un usuario de la tabla
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                rellenarCamposFormulario(newValue);
            }
        });
    }

    private void cargarUsuarios() {
        listaUsuarios = FXCollections.observableArrayList();

        try {
            // Cargar todos los usuarios desde el repositorio
            List<UsuarioVO> usuarios = usuarioRepository.ObtenerListaUsuarios();
            for (UsuarioVO usuario : usuarios) {
                listaUsuarios.add(new UsuarioModelo(usuario.getDNI(), usuario.getNombre(), usuario.getApellidos(),
                        usuario.getDireccion(), usuario.getLocalidad(), usuario.getProvincia()));
            }

            // Establecer los datos en la tabla
            tablaUsuarios.setItems(listaUsuarios);
        } catch (Exception e) {
            e.printStackTrace();  // Imprime la excepción en la consola
            // Mostrar mensaje de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo cargar la vista");
            alert.setContentText(e.getMessage()); // Mostrar el mensaje de error completo
            alert.showAndWait();
        }
    }

    // Rellenar los campos del formulario con los datos del usuario seleccionado
    private void rellenarCamposFormulario(UsuarioModelo usuario) {
        ponerId.setText(usuario.getDni());
        ponerNombre.setText(usuario.getNombre());
        ponerApellido.setText(usuario.getApellidos());
        ponerDireccion.setText(usuario.getDireccion());
        ponerLocalidad.setText(usuario.getLocalidad());
        ponerProvincia.setText(usuario.getProvincia());
    }

    @FXML
    private void botonEditarUsuario() {
        // Verificar si un usuario ha sido seleccionado en la tabla
        UsuarioModelo usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un usuario para editar.");
            return;
        }

        // Obtener los datos del formulario
        String dni = ponerId.getText();
        String nombre = ponerNombre.getText();
        String apellidos = ponerApellido.getText();
        String direccion = ponerDireccion.getText();
        String localidad = ponerLocalidad.getText();
        String provincia = ponerProvincia.getText();

        // Validar que los campos no estén vacíos
        if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || localidad.isEmpty() || provincia.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        try {
            // Crear el objeto UsuarioVO con los datos editados
            UsuarioVO usuarioEditado = new UsuarioVO(dni, nombre, apellidos, direccion, localidad, provincia);

            // Actualizar el usuario en el repositorio
            usuarioRepository.editUsuario(usuarioEditado);

            // Actualizar la lista de usuarios y refrescar la tabla
            cargarUsuarios();

            // Limpiar los campos de entrada
            limpiarCampos();

            // Mostrar alerta de éxito (ahora con ALERT tipo INFORMATION)
            mostrarAlerta("Éxito", "Usuario editado con éxito.");

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo editar el usuario.");
        }
    }

    @FXML
    private void botonEliminarUsuario() {
        // Verificar si un usuario ha sido seleccionado en la tabla
        UsuarioModelo usuarioSeleccionado = tablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un usuario para eliminar.");
            return;
        }

        // Confirmación antes de eliminar
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Está seguro de que desea eliminar al usuario con DNI " + usuarioSeleccionado.getDni() + "?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            try {
                // Eliminar el usuario del repositorio
                usuarioRepository.deleteUsuario(usuarioSeleccionado.getDni());

                // Eliminar el usuario de la lista y refrescar la tabla
                listaUsuarios.remove(usuarioSeleccionado);

                // Mostrar alerta de éxito después de eliminar
                mostrarAlerta("Éxito", "Usuario eliminado con éxito.");

            } catch (Exception e) {
                mostrarAlerta("Error", "No se pudo eliminar el usuario.");
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        // Si el título es "Éxito", cambiar a ALERT tipo INFORMATION
        Alert.AlertType tipoAlerta = titulo.equals("Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR;
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    private void limpiarCampos() {
        // Limpiar los campos de entrada
        ponerId.clear();
        ponerNombre.clear();
        ponerApellido.clear();
        ponerDireccion.clear();
        ponerLocalidad.clear();
        ponerProvincia.clear();
    }
}
