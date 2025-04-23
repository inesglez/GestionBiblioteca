package com.example.gestionbiblioteca.modelo;

import com.example.gestionbiblioteca.modelo.repository.impl.Conexion;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoModelo {
    private SimpleIntegerProperty idPrestamo;
    private SimpleStringProperty dni;
    private SimpleObjectProperty<LocalDate> fechaPrestamo;
    private SimpleObjectProperty<LocalDate> fechaDevolucion;
    private SimpleStringProperty titulo;

    public PrestamoModelo() {
        this.idPrestamo = new SimpleIntegerProperty();
        this.dni = new SimpleStringProperty();
        this.fechaPrestamo = new SimpleObjectProperty<>();
        this.fechaDevolucion = new SimpleObjectProperty<>();
        this.titulo = new SimpleStringProperty();
    }

    public PrestamoModelo(String dni, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String titulo) {
        this();
        this.dni.set(dni);
        this.fechaPrestamo.set(fechaPrestamo);
        this.fechaDevolucion.set(fechaDevolucion);
        this.titulo.set(titulo);
    }
    public PrestamoModelo(Integer idPrestamo, String dni, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String titulo) {
        this();
        this.idPrestamo.set(idPrestamo);
        this.dni.set(dni);
        this.fechaPrestamo.set(fechaPrestamo);
        this.fechaDevolucion.set(fechaDevolucion);
        this.titulo.set(titulo);
    }


    public int getIdPrestamo() {
        return idPrestamo.get();
    }

    public String gettitulo() {
        return titulo.get();
    }

    public void settitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getDni() {  // Cambiado de getdni() a getDni()
        return dni.get();
    }

    public void setDni(String dni) {  // Cambiado de setdni() a setDni()
        this.dni.set(dni);
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo.get();
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo.set(fechaPrestamo);
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion.get();
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion.set(fechaDevolucion);
    }

    // Property methods
    public SimpleIntegerProperty idPrestamoProperty() {
        return idPrestamo;
    }

    public SimpleStringProperty dniProperty() {
        return dni;
    }

    public SimpleObjectProperty<LocalDate> fechaPrestamoProperty() {
        return fechaPrestamo;
    }

    public SimpleObjectProperty<LocalDate> fechaDevolucionProperty() {
        return fechaDevolucion;
    }

    public SimpleStringProperty tituloProperty() {
        return titulo;
    }

    public List<PrestamoModelo> obtenerListaPrestamos() throws SQLException {
        List<PrestamoModelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PrestamoModelo prestamo = new PrestamoModelo(
                        rs.getInt("idPrestamo"),
                        rs.getString("dni"),
                        rs.getDate("fechaPrestamo").toLocalDate(),
                        rs.getDate("fechaDevolucion").toLocalDate(),
                        rs.getString("titulo")
                );
              //  prestamo.idPrestamo.set(rs.getInt("idPrestamo"));
                lista.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    public void anadirPrestamo(PrestamoModelo prestamo) throws SQLException {
        String sql = "INSERT INTO prestamos (dni, fechaPrestamo, fechaDevolucion, titulo) VALUES (?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, prestamo.getDni());  // Cambiado de prestamo.getdni() a prestamo.getDni()
            stmt.setDate(2, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(3, Date.valueOf(prestamo.getFechaDevolucion()));
            stmt.setString(4, prestamo.gettitulo());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    prestamo.idPrestamo.set(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void editarPrestamo(PrestamoModelo prestamo) throws SQLException {
        String sql = "UPDATE prestamos SET fechaPrestamo = ?, fechaDevolucion = ?, titulo = ? WHERE idPrestamo = ?";
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(2, Date.valueOf(prestamo.getFechaDevolucion()));
            stmt.setString(3, prestamo.gettitulo());
            stmt.setInt(4, prestamo.getIdPrestamo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void eliminarPrestamo(int idPrestamo) throws SQLException {
        String sql = "DELETE FROM prestamos WHERE idPrestamo = ?";
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPrestamo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
