package com.example.gestionbiblioteca.modelo.repository;

// Manejo de las excepciones para Libro
public class ExceptionLibro extends Exception {
    private String mensaje;

    public ExceptionLibro() {}

    public ExceptionLibro(String message) {
        this.mensaje = message;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
