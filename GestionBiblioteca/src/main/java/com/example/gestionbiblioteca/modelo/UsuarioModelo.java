package com.example.gestionbiblioteca.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class UsuarioModelo {

    private final StringProperty dni;
    private final StringProperty nombre;
    private final StringProperty apellidos;
    private final StringProperty direccion;
    private final StringProperty localidad;
    private final StringProperty provincia;

    // Lista de préstamos del usuario
    private final List<PrestamoVO> prestamos;

    // Constructor por defecto
    public UsuarioModelo() {
        this(null, null, null, null, null, null);
    }

    // Constructor con datos iniciales
    public UsuarioModelo(String dni, String nombre, String apellidos, String direccion, String localidad, String provincia) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellidos = new SimpleStringProperty(apellidos);
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
    public String getApellidos() {
        return apellidos.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public StringProperty apellidosProperty() {
        return apellidos;
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
                ", apellidos=" + apellidos +
                ", direccion=" + direccion +
                ", localidad=" + localidad +
                ", provincia=" + provincia +
                ", prestamos=" + prestamos +
                '}';
    }
}
