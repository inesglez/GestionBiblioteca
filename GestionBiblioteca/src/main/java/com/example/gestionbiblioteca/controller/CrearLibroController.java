package com.example.gestionbiblioteca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import com.example.gestionbiblioteca.modelo.repository.impl.LibroRepositoryImpl;
import com.example.gestionbiblioteca.modelo.LibroVO;
import com.example.gestionbiblioteca.modelo.repository.ExceptionLibro;

public class CrearLibroController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField ponerTitulo;
    @FXML
    private TextField ponerAutor;
    @FXML
    private TextField ponerAnioPublicacion;
    @FXML
    private TextField ponerEditorial;
    @FXML
    private TextField ponerPortada;

    private Stage ventana;
    private boolean botonConfirmado = false;
    private LibroRepositoryImpl libroRepository = new LibroRepositoryImpl(); // Acceso a la base de datos

    public void setVentana(Stage ventana) {
        this.ventana = ventana;
    }

    @FXML
    private void botonOk() {
        try {
            // Verifica que los campos no estén vacíos
            if (ponerTitulo.getText().isEmpty() || ponerAutor.getText().isEmpty() || ponerAnioPublicacion.getText().isEmpty() || ponerEditorial.getText().isEmpty() || ponerPortada.getText().isEmpty()) {
                mostrarAlerta("Error", "Todos los campos son obligatorios.");
                return;
            }

            // Obtiene el siguiente ID disponible para el libro
            int idLibro = libroRepository.lastId() + 1;  // Asigna el siguiente ID

            // Crea el libro con los datos proporcionados
            LibroVO nuevoLibro = new LibroVO(idLibro, ponerTitulo.getText(), ponerAutor.getText(), Integer.parseInt(ponerAnioPublicacion.getText()), ponerEditorial.getText(), true, ponerPortada.getText());

            // Guarda el libro en la base de datos
            libroRepository.addLibro(nuevoLibro);

            // Marca el formulario como confirmado y cierra la ventana
            botonConfirmado = true;
            ventana.close();

        } catch (ExceptionLibro e) {
            // En caso de que se lance ExceptionLibro, mostramos una alerta
            mostrarAlerta("Error", "No se ha podido guardar el libro: " + e.getMessage());
        } catch (NumberFormatException e) {
            // En caso de que el año de publicación no sea un número válido
            mostrarAlerta("Error", "El año de publicación debe ser un número.");
        }
    }

    @FXML
    private void botonCancelar() {
        if (ventana != null) {
            ventana.close();
        }
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
