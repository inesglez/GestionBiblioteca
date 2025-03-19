package com.example.gestionbiblioteca.modelo.repository;

public class ExceptionPrestamo extends RuntimeException {

    public String mensaje;

    public ExceptionPrestamo() {
        super();
    }

    public ExceptionPrestamo(String mensaje) {
        super(mensaje);
    }

    public String getMensaje() {
        return mensaje;
    }

}
