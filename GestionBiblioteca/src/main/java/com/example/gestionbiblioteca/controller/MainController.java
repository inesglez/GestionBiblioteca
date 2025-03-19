package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.Main;
import com.example.gestionbiblioteca.modelo.UsuarioModelo;
import com.example.gestionbiblioteca.modelo.UsuarioVO;
import com.example.gestionbiblioteca.modelo.repository.ExceptionUsuario;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// Controlador principal para gestionar la vista de usuarios y libros
public class MainController {

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button prestamosButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private VBox userCardContainer;
    @FXML
    private ScrollPane scrollPane;

    private VBox lastSelectedVBox;
    private UsuarioModelo usuarioModelo;
    private List<UsuarioModelo> usuarioModelos;
    private Main main;

    // Inicializa el controlador y asigna eventos
    public void initialize() {
        configurarScrollPane();
        configurarBotones();
    }

    private void configurarScrollPane() {
        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            double newVvalue = scrollPane.getVvalue() - event.getDeltaY() * 0.0005;
            scrollPane.setVvalue(Math.min(Math.max(newVvalue, 0), 1));
            event.consume();
        });
    }

    private void configurarBotones() {
        deleteButton.setOnAction(event -> {
            try {
                eliminar(deleteButton.getId());
            } catch (ExceptionUsuario e) {
                throw new RuntimeException(e);
            }
        });
        editButton.setOnAction(event -> {
            try {
                editar(getUsuario(editButton.getId()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        prestamosButton.setOnAction(event -> {
            try {
                verPrestamos(prestamosButton.getId());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setUsuarioModelo(UsuarioModelo usuarioModelo) {
        this.usuarioModelo = usuarioModelo;
    }

    public void setUsuarios() throws ExceptionUsuario {
        List<UsuarioVO> usuarioVOs = usuarioModelo.obtenerListaUsuarios();
        this.usuarioModelos = usuarioVOs.stream().map(this::convertirUsuarioVOAUsuario).collect(Collectors.toList());
        setData();
    }

    public List<UsuarioModelo> getUsuarios() {
        return usuarioModelos;
    }

    public UsuarioModelo getUsuario(String id) {
        return usuarioModelos.stream()
                .filter(usuarioModelo -> usuarioModelo.getDni().equals(id))
                .findFirst()
                .orElse(null);
    }

    public VBox nuevaTarjeta(UsuarioModelo usuarioModelo) {
        if (!usuarioModelos.contains(usuarioModelo)) usuarioModelos.add(usuarioModelo);

        VBox userCard = crearTarjetaBase(usuarioModelo);
        userCard.setOnMouseClicked(event -> Seleccionar(event));
        return userCard;
    }

    private VBox crearTarjetaBase(UsuarioModelo usuarioModelo) {
        VBox userCard = new VBox();
        userCard.setId(usuarioModelo.getDni());
        userCard.setUserData(usuarioModelo.getDni());

        Label dniLabel = new Label("DNI: " + usuarioModelo.getDni());
        Label nameLabel = new Label("Nombre: " + usuarioModelo.getNombre() + " " + usuarioModelo.getApellido());
        Label addressLabel = new Label("Dirección: " + usuarioModelo.getDireccion() + ", " + usuarioModelo.getLocalidad() + ", " + usuarioModelo.getProvincia());

        userCard.getChildren().addAll(dniLabel, nameLabel, addressLabel);
        return userCard;
    }

    @FXML
    private void buscar() {
        String dniBusqueda = searchField.getText().trim();
        boolean encontrado = false;

        for (Node node : userCardContainer.getChildren()) {
            VBox userCard = (VBox) node;
            boolean match = dniBusqueda.equals(userCard.getUserData());
            if (match) {
                encontrado = true;
            }
        }

        alerta(encontrado ? "Usuario encontrado" : "Este usuario no existe", encontrado ? Alert.AlertType.CONFIRMATION : Alert.AlertType.ERROR);
    }

    public void eliminar(String id) throws ExceptionUsuario {
        usuarioModelo.eliminarUsuario(id);
        usuarioModelos.removeIf(u -> u.getDni().equals(id));
        eliminarCard(id);
    }

    public void eliminarCard(String id) {
        userCardContainer.getChildren().removeIf(node -> id.equals(((VBox) node).getUserData()));
        alerta("Eliminado con éxito", Alert.AlertType.CONFIRMATION);
    }

    public void editar(UsuarioModelo usuarioModelo) throws IOException {
        if (lastSelectedVBox != null) {
            main.EditarONuevo(usuarioModelo);
        } else {
            alerta("Debe seleccionar un usuario", Alert.AlertType.ERROR);
        }
    }

    public void verPrestamos(String id) throws IOException {
        if (lastSelectedVBox != null) {
            main.verPrestamos(id);
        } else {
            alerta("Debe seleccionar un usuario", Alert.AlertType.ERROR);
        }
    }

    public void alerta(String message, Alert.AlertType alertType) {
        new Alert(alertType, message).show();
    }

    private UsuarioModelo convertirUsuarioVOAUsuario(UsuarioVO usuarioVO) {
        return new UsuarioModelo(usuarioVO.getDNI(), usuarioVO.getNombre(), usuarioVO.getApellidos(),
                usuarioVO.getDireccion(), usuarioVO.getLocalidad(), usuarioVO.getProvincia());
    }
}
