package com.example.gestionbiblioteca.modelo;

import javafx.beans.property.*;

public class LibroModelo {

    private final IntegerProperty idLibro;
    private final StringProperty titulo;
    private final StringProperty autor;
    private final IntegerProperty anioPublicacion;
    private final StringProperty editorial;
    private final BooleanProperty disponible;
    private final StringProperty portada;

    // Constructor
    public LibroModelo(int idLibro, String titulo, String autor, int anioPublicacion,
                       String editorial, boolean disponible, String portada) {
        this.idLibro = new SimpleIntegerProperty(idLibro);
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.anioPublicacion = new SimpleIntegerProperty(anioPublicacion);
        this.editorial = new SimpleStringProperty(editorial);
        this.disponible = new SimpleBooleanProperty(disponible);
        this.portada = new SimpleStringProperty(portada);
    }

    // Getters y setters para las propiedades
    public int getIdLibro() {
        return idLibro.get();
    }

    public void setIdLibro(int idLibro) {
        this.idLibro.set(idLibro);
    }

    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getAutor() {
        return autor.get();
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public int getAnioPublicacion() {
        return anioPublicacion.get();
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion.set(anioPublicacion);
    }

    public String getEditorial() {
        return editorial.get();
    }

    public void setEditorial(String editorial) {
        this.editorial.set(editorial);
    }

    public boolean isDisponible() {
        return disponible.get();
    }

    public void setDisponible(boolean disponible) {
        this.disponible.set(disponible);
    }

    public String getPortada() {
        return portada.get();
    }

    public void setPortada(String portada) {
        this.portada.set(portada);
    }

    // Métodos Property para vinculación en la tabla
    public IntegerProperty idLibroProperty() {
        return idLibro;
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public StringProperty autorProperty() {
        return autor;
    }

    public IntegerProperty anioPublicacionProperty() {
        return anioPublicacion;
    }

    public StringProperty editorialProperty() {
        return editorial;
    }

    public BooleanProperty disponibleProperty() {
        return disponible;
    }

    public StringProperty portadaProperty() {
        return portada;
    }
}
