package com.example.gestionbiblioteca.modelo.repository;

// Manejo de las excepciones para USuario
public class ExceptionUsuario extends Exception {
    private String mensaje;

    public ExceptionUsuario() {}
    public ExceptionUsuario(String message) {this.mensaje = message;}
    public String imprimirMensaje() {
        return this.mensaje;
    }
}
