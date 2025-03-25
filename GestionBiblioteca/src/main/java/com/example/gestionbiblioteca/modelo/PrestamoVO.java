package com.example.gestionbiblioteca.modelo;

import java.time.LocalDate;

public class PrestamoVO implements Comparable<PrestamoVO> {

    private Integer idPrestamo;
    private String dniUsuario;
    private LocalDate fechaPrestamo, fechaDevolucion;
    private String libroPrestado;

    // Constructor vacío
    public PrestamoVO() {}

    // Constructor con datos iniciales
    public PrestamoVO(Integer idPrestamo, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String libroPrestado, String dniUsuario) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.libroPrestado = libroPrestado;
        this.dniUsuario = dniUsuario;  // Corregido el nombre del parámetro
    }

    // Getters y Setters para idPrestamo
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    // Getters y Setters para dniUsuario
    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
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

    // Método toString() para representar el objeto
    @Override
    public String toString() {
        return "PrestamoVO{" +
                "idPrestamo=" + idPrestamo +
                ", dniUsuario='" + dniUsuario + '\'' +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", libroPrestado='" + libroPrestado + '\'' +
                '}';
    }

    // Método equals() para comparar objetos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrestamoVO that = (PrestamoVO) o;

        if (!idPrestamo.equals(that.idPrestamo)) return false;
        if (!dniUsuario.equals(that.dniUsuario)) return false;
        if (!fechaPrestamo.equals(that.fechaPrestamo)) return false;
        if (!fechaDevolucion.equals(that.fechaDevolucion)) return false;
        return libroPrestado.equals(that.libroPrestado);
    }

    // Método hashCode() para objetos con igualdad
    @Override
    public int hashCode() {
        int result = idPrestamo.hashCode();
        result = 31 * result + dniUsuario.hashCode();
        result = 31 * result + fechaPrestamo.hashCode();
        result = 31 * result + fechaDevolucion.hashCode();
        result = 31 * result + libroPrestado.hashCode();
        return result;
    }

    // Método compareTo() para ordenar préstamos por fecha de préstamo
    @Override
    public int compareTo(PrestamoVO o) {
        return this.fechaPrestamo.compareTo(o.fechaPrestamo);
    }
}
