package com.example.gestionbiblioteca.modelo;

import java.time.LocalDate;

public class PrestamoVO {

    Integer idPrestamo;
    String dniCliente;
    LocalDate fechaPrestamo, fechaDevolucion;
    String libroPrestado;

    // Constructor vacío
    public PrestamoVO() {
    }

    // Constructor con datos iniciales
    public PrestamoVO(Integer idPrestamo, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String libroPrestado, String dniCliente) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libroPrestado = libroPrestado;
        this.dniCliente = dniCliente;
    }

    // Getters y Setters para idPrestamo
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    // Getters y Setters para dniCliente
    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    // Getters y Setters para fechaPrestamo
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    // Getters y Setters para fechaDevolucion
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y Setters para libroPrestado
    public String getLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(String libroPrestado) {
        this.libroPrestado = libroPrestado;
    }
}
