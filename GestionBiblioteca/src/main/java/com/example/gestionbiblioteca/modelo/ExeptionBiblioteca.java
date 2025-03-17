package com.example.gestionbiblioteca.modelo;

public class ExeptionBiblioteca extends RuntimeException {

    public String mensaje;

    public ExeptionBiblioteca() {
        super();
    }

    public ExeptionBiblioteca(String mensaje) {
        super(mensaje);
    }

    public String getMensaje() {
        return mensaje;
    }

}
