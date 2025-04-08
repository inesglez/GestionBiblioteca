package com.example.gestionbiblioteca.modelo;

public class LibroVO {

    private int idLibro;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String editorial;
    private boolean disponible;
    private String portada;

    // Constructor por defecto vacío
    public LibroVO() {
    }

    // Constructor con todos los parámetros
    public LibroVO(int idLibro, String titulo, String autor, int anioPublicacion, String editorial, boolean disponible, String portada) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.disponible = disponible;
        this.portada = portada;
    }

    // Getters y Setters
    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    @Override
    public String toString() {
        return "LibroVO{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", editorial='" + editorial + '\'' +
                ", disponible=" + disponible +
                ", portada='" + portada + '\'' +
                '}';
    }
}
