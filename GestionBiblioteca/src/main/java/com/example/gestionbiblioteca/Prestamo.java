package com.example.gestionbiblioteca;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

// Clase para gestionar los préstamos en la biblioteca
public class Prestamo {
    private IntegerProperty idPrestamo;
    private StringProperty dni;
    private IntegerProperty idLibro; // Cambio de tipo a Integer para el idLibro
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    // Constructor con todos los parámetros
    public Prestamo(int idPrestamo, String dni, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        this.idPrestamo = new SimpleIntegerProperty(idPrestamo);
        this.dni = new SimpleStringProperty(dni);
        this.idLibro = new SimpleIntegerProperty(idLibro); // Ahora usamos idLibro como Integer
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    // Constructor sin ID para nuevos préstamos
    public Prestamo(String dni, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        this.dni = new SimpleStringProperty(dni);
        this.idLibro = new SimpleIntegerProperty(idLibro); // Ahora usamos idLibro como Integer
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

    public String getdni() {
        return dni.get();
    }

    public void setdni(String dni) {
        this.dni.set(dni);
    }

    public StringProperty dniroperty() {
        return dni;
    }

    public int getIdLibro() {
        return idLibro.get(); // Método actualizado para obtener idLibro
    }

    public void setIdLibro(int idLibro) {
        this.idLibro.set(idLibro); // Método actualizado para establecer idLibro
    }

    public IntegerProperty idLibroProperty() {
        return idLibro; // Método actualizado para idLibro
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
                ", dni='" + dni.get() + '\'' +
                ", idLibro=" + idLibro.get() + // Actualizado para mostrar idLibro
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}
