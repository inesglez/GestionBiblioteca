package com.example.gestionbiblioteca.modelo;

public class UsuarioVO {

    String dni, nombre, apellidos, direccion, localidad, provincia;

    // Constructor por defecto vacío
    public UsuarioVO() {

    }

    // Constructor con todos los parámetros
    public UsuarioVO(String dni, String nombre, String apellidos, String direccion, String localidad, String provincia) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    // Getters y Setters para DNI
    public String getDNI() {
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
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
