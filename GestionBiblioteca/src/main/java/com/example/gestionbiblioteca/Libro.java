package com.example.gestionbiblioteca;

import javafx.beans.property.*;

public class Libro {
    private IntegerProperty idLibro;
    private StringProperty titulo;
    private StringProperty autor;
    private IntegerProperty anioPublicacion;
    private StringProperty editorial;
    private BooleanProperty disponible;
    private StringProperty portada; // Ruta de la imagen

    // Constructor con ID
    public Libro(int idLibro, String titulo, String autor, int anioPublicacion, String editorial, boolean disponible, String portada) {
        this.idLibro = new SimpleIntegerProperty(idLibro);
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.anioPublicacion = new SimpleIntegerProperty(anioPublicacion);
        this.editorial = new SimpleStringProperty(editorial);
        this.disponible = new SimpleBooleanProperty(disponible);
        this.portada = new SimpleStringProperty(portada);
    }

    // Constructor sin ID
    public Libro(String titulo, String autor, int anioPublicacion, String editorial, boolean disponible, String portada) {
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.anioPublicacion = new SimpleIntegerProperty(anioPublicacion);
        this.editorial = new SimpleStringProperty(editorial);
        this.disponible = new SimpleBooleanProperty(disponible);
        this.portada = new SimpleStringProperty(portada);
    }

    // Getters y setters
    public int getIdLibro() {
        return idLibro.get();
    }

    public void setIdLibro(int idLibro) {
        this.idLibro.set(idLibro);
    }

    public IntegerProperty idLibroProperty() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public String getAutor() {
        return autor.get();
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public StringProperty autorProperty() {
        return autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion.get();
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion.set(anioPublicacion);
    }

    public IntegerProperty anioPublicacionProperty() {
        return anioPublicacion;
    }

    public String getEditorial() {
        return editorial.get();
    }

    public void setEditorial(String editorial) {
        this.editorial.set(editorial);
    }

    public StringProperty editorialProperty() {
        return editorial;
    }

    public boolean isDisponible() {
        return disponible.get();
    }

    public void setDisponible(boolean disponible) {
        this.disponible.set(disponible);
    }

    public BooleanProperty disponibleProperty() {
        return disponible;
    }

    public String getPortada() {
        return portada.get();
    }

    public void setPortada(String portada) {
        this.portada.set(portada);
    }

    public StringProperty portadaProperty() {
        return portada;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro.get() +
                ", titulo='" + titulo.get() + '\'' +
                ", autor='" + autor.get() + '\'' +
                ", anioPublicacion=" + anioPublicacion.get() +
                ", editorial='" + editorial.get() + '\'' +
                ", disponible=" + disponible.get() +
                ", portada='" + portada.get() + '\'' +
                '}';
    }
}
