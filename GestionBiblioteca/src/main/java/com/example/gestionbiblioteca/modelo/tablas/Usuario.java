package com.example.gestionbiblioteca.modelo.tablas;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private final StringProperty dni;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty direccion;
    private final StringProperty localidad;
    private final StringProperty provincia;

    // Lista de préstamos del usuario
    private final List<PrestamoVO> prestamos;

    // Constructor por defecto
    public Usuario() {
        this(null, null, null, null, null, null);
    }

    // Constructor con datos iniciales
    public Usuario(String dni, String nombre, String apellido, String direccion, String localidad, String provincia) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincia);
        this.prestamos = new ArrayList<>();
    }

    /*
     * MÉTODOS PARA GESTIONAR LOS PRESTAMOS
     */

    // Añadir un préstamo
    public void agregarPrestamo(PrestamoVO prestamo) {
        prestamos.add(prestamo);
    }

    // Eliminar un préstamo
    public void eliminarPrestamo(PrestamoVO prestamo) {
        prestamos.remove(prestamo);
    }

    // Obtener la lista de préstamos
    public List<PrestamoVO> getPrestamos() {
        return prestamos;
    }

    // Obtener el número de préstamos
    public int getNumeroPrestamos() {
        return prestamos.size();
    }

    // Mostrar todos los préstamos del usuario
    public void mostrarPrestamos() {
        for (PrestamoVO prestamo : prestamos) {
            System.out.println("ID Préstamo: " + prestamo.getIdPrestamo());
            System.out.println("DNI Usuario: " + prestamo.getDniCliente());
            System.out.println("Fecha Préstamo: " + prestamo.getFechaPrestamo());
            System.out.println("Fecha Devolución: " + prestamo.getFechaDevolucion());
            System.out.println("Libro Prestado: " + prestamo.getLibroPrestado());
            System.out.println();
        }
    }

    /*
     * RESTO DE MÉTODOS
     */

    // Getters y Setters para nombre
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    // Getters y Setters para apellido
    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    // Getters y Setters para direccion
    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    // Getters y Setters para localidad
    public String getLocalidad() {
        return localidad.get();
    }

    public void setLocalidad(String localidad) {
        this.localidad.set(localidad);
    }

    public StringProperty localidadProperty() {
        return localidad;
    }

    // Getters y Setters para provincia
    public String getProvincia() {
        return provincia.get();
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }

    // Getters y Setters para dni
    public String getDni() {
        return dni.get();
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public StringProperty dniProperty() {
        return dni;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni=" + dni +
                ", nombre=" + nombre +
                ", apellido=" + apellido +
                ", direccion=" + direccion +
                ", localidad=" + localidad +
                ", provincia=" + provincia +
                ", prestamos=" + prestamos +
                '}';
    }
}
