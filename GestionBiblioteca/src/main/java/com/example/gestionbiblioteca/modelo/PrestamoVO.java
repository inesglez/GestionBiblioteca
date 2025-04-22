package com.example.gestionbiblioteca.modelo;

import java.time.LocalDate;

public class PrestamoVO implements Comparable<PrestamoVO> {

    private Integer idPrestamo;
    private String dni;  // Campo dni en lugar de dniUsuario
    private LocalDate fechaPrestamo, fechaDevolucion;
    private Integer idLibro;

    // Constructor vacío
    public PrestamoVO() {}

    // Constructor con datos iniciales
    public PrestamoVO(Integer idPrestamo, LocalDate fechaPrestamo, LocalDate fechaDevolucion, Integer idLibro, String dni) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idLibro = idLibro;
        this.dni = dni;  // Usamos dni
    }

    // Getters y Setters para idPrestamo
    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    // Getters y Setters para dni
    public String getDni() {
        return dni;  // Cambiado a dni
    }

    public void setDni(String dni) {  // Cambiado a dni
        this.dni = dni;
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

    // Getters y Setters para idLibro
    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    // Método toString() para representar el objeto
    @Override
    public String toString() {
        return "PrestamoVO{" +
                "idPrestamo=" + idPrestamo +
                ", dni='" + dni + '\'' +  // Cambiado a dni
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", idLibro=" + idLibro +
                '}';
    }

    // Método equals() para comparar objetos
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrestamoVO that = (PrestamoVO) o;

        if (!idPrestamo.equals(that.idPrestamo)) return false;
        if (!dni.equals(that.dni)) return false;  // Cambiado a dni
        if (!fechaPrestamo.equals(that.fechaPrestamo)) return false;
        if (!fechaDevolucion.equals(that.fechaDevolucion)) return false;
        return idLibro.equals(that.idLibro);
    }

    // Método hashCode() para objetos con igualdad
    @Override
    public int hashCode() {
        int result = idPrestamo.hashCode();
        result = 31 * result + dni.hashCode();  // Cambiado a dni
        result = 31 * result + fechaPrestamo.hashCode();
        result = 31 * result + fechaDevolucion.hashCode();
        result = 31 * result + idLibro.hashCode();
        return result;
    }

    // Método compareTo() para ordenar préstamos por fecha de préstamo
    @Override
    public int compareTo(PrestamoVO o) {
        return this.fechaPrestamo.compareTo(o.fechaPrestamo);
    }
}
