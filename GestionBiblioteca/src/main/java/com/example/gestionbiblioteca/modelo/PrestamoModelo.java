package com.example.gestionbiblioteca.modelo;

import javafx.beans.property.*;
import java.time.LocalDate;

public class PrestamoModelo {

    private final IntegerProperty idPrestamo;
    private final StringProperty libroPrestado;
    private final ObjectProperty<LocalDate> fechaPrestamo;
    private final ObjectProperty<LocalDate> fechaDevolucion;
    private final StringProperty dniCliente;

    // Constructor por defecto
    public PrestamoModelo() {
        this(0, null, null, null, null);
    }

    // Constructor con algunos datos iniciales
    public PrestamoModelo(int idPrestamo, String libroPrestado, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String dniCliente) {
        this.idPrestamo = new SimpleIntegerProperty(idPrestamo);
        this.libroPrestado = new SimpleStringProperty(libroPrestado);
        this.fechaPrestamo = new SimpleObjectProperty<LocalDate>(fechaPrestamo);
        this.fechaDevolucion = new SimpleObjectProperty<LocalDate>(fechaDevolucion);
        this.dniCliente = new SimpleStringProperty(dniCliente);
    }

    // Getters y Setters para idPrestamo
    public Integer getIdPrestamo() {
        return idPrestamo.get();
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo.set(idPrestamo);
    }

    public IntegerProperty idPrestamoProperty() {
        return idPrestamo;
    }

    // Getters y Setters para libroPrestado
    public String getLibroPrestado() {
        return libroPrestado.get();
    }

    public void setLibroPrestado(String libroPrestado) {
        this.libroPrestado.set(libroPrestado);
    }

    public StringProperty libroPrestadoProperty() {
        return libroPrestado;
    }

    // Getters y Setters para fechaPrestamo
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo.get();
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo.set(fechaPrestamo);
    }

    public ObjectProperty<LocalDate> fechaPrestamoProperty() {
        return fechaPrestamo;
    }

    // Getters y Setters para fechaDevolucion
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion.get();
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion.set(fechaDevolucion);
    }

    public ObjectProperty<LocalDate> fechaDevolucionProperty() {
        return fechaDevolucion;
    }

    // Getters y Setters para dniCliente
    public String getDniCliente() {
        return dniCliente.get();
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente.set(dniCliente);
    }

    public StringProperty dniClienteProperty() {
        return dniCliente;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", libroPrestado='" + libroPrestado + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", dniCliente='" + dniCliente + '\'' +
                '}';
    }
}
