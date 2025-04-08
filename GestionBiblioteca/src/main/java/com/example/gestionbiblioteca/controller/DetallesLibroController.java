package com.example.gestionbiblioteca.controller;

import com.example.gestionbiblioteca.modelo.LibroModelo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DetallesLibroController {

    @FXML private Label idLibroLabel;
    @FXML private Label tituloLibroLabel;
    @FXML private Label autorLibroLabel;
    @FXML private Label anioPublicacionLabel;
    @FXML private Label editorialLibroLabel;
    @FXML private Label disponibilidadLibroLabel;
    @FXML private ImageView portadaLibroImageView;

    public void mostrarDetalles(LibroModelo libro) {
        // Establece los textos de los labels
        tituloLibroLabel.setText(libro.getTitulo());
        autorLibroLabel.setText(libro.getAutor());
        anioPublicacionLabel.setText(String.valueOf(libro.getAnioPublicacion()));
        editorialLibroLabel.setText(libro.getEditorial());
        disponibilidadLibroLabel.setText(libro.isDisponible() ? "Disponible" : "No disponible");

        // Mostrar la portada como una imagen
        Image image = new Image(libro.getPortada()); // Asegúrate de que getPortada() devuelve una URL válida
        portadaLibroImageView.setImage(image);
    }

    public void botonCerrarVentana(ActionEvent actionEvent) {
        // Obtén la ventana (Stage) actual
        Stage stage = (Stage) tituloLibroLabel.getScene().getWindow();
        // Cierra la ventana
        stage.close();
    }
}
