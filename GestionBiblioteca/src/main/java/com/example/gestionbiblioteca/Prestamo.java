package com.example.gestionbiblioteca;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

// Clase para gestionar los préstamos en la biblioteca
public class Prestamo {
    private IntegerProperty idPrestamo;
    private StringProperty dniUsuario;
    private StringProperty libro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    // Constructor con todos los parámetros
    public Prestamo(int idPrestamo, String dniUsuario, String libro, Date fechaPrestamo, Date fechaDevolucion) {
        this.idPrestamo = new SimpleIntegerProperty(idPrestamo);
        this.dniUsuario = new SimpleStringProperty(dniUsuario);
        this.libro = new SimpleStringProperty(libro);
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Constructor sin ID para nuevos préstamos
    public Prestamo(String dniUsuario, String libro, Date fechaPrestamo, Date fechaDevolucion) {
        this.dniUsuario = new SimpleStringProperty(dniUsuario);
        this.libro = new SimpleStringProperty(libro);
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Getters y setters
    public int getIdPrestamo() {
        return idPrestamo.get();
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo.set(idPrestamo);
    }

    public IntegerProperty idPrestamoProperty() {
        return idPrestamo;
    }

    public String getDniUsuario() {
        return dniUsuario.get();
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario.set(dniUsuario);
    }

    public StringProperty dniUsuarioProperty() {
        return dniUsuario;
    }

    public String getLibro() {
        return libro.get();
    }

    public void setLibro(String libro) {
        this.libro.set(libro);
    }

    public StringProperty libroProperty() {
        return libro;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo.get() +
                ", dniUsuario='" + dniUsuario.get() + '\'' +
                ", libro='" + libro.get() + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}
