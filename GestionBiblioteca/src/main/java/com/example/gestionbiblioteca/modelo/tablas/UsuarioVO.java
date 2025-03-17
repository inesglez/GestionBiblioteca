package com.example.gestionbiblioteca.modelo.tablas;

public class UsuarioVO {

    String dni, nombre, apellido, direccion, localidad, provincia;

    // Constructor por defecto vacío
    public UsuarioVO() {

    }

    // Constructor con todos los parámetros
    public UsuarioVO(String dni, String nombre, String apellido, String direccion, String localidad, String provincia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    // Getters y Setters para DNI
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    // Getters y Setters para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters para apellido
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getters y Setters para direccion
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Getters y Setters para localidad
    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    // Getters y Setters para provincia
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
