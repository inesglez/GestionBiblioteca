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
    private SimpleStringProperty dniUsuario;
    private SimpleObjectProperty<LocalDate> fechaPrestamo;
    private SimpleObjectProperty<LocalDate> fechaDevolucion;
    private SimpleStringProperty libro;

    public PrestamoModelo() {
        this.idPrestamo = new SimpleIntegerProperty();
        this.dniUsuario = new SimpleStringProperty();
        this.fechaPrestamo = new SimpleObjectProperty<>();
        this.fechaDevolucion = new SimpleObjectProperty<>();
        this.libro = new SimpleStringProperty();
    }

    public PrestamoModelo(String dniUsuario, LocalDate fechaPrestamo, LocalDate fechaDevolucion, String libro) {
        this();
        this.dniUsuario.set(dniUsuario);
        this.fechaPrestamo.set(fechaPrestamo);
        this.fechaDevolucion.set(fechaDevolucion);
        this.libro.set(libro);
    }

    public int getIdPrestamo() {
        return idPrestamo.get();
    }

    public SimpleIntegerProperty idPrestamoProperty() {
        return idPrestamo;
    }

    public String getDniUsuario() {
        return dniUsuario.get();
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario.set(dniUsuario);
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

    public String getLibro() {
        return libro.get();
    }

    public void setLibro(String libro) {
        this.libro.set(libro);
    }

    // Métodos Property
    public SimpleObjectProperty<LocalDate> fechaPrestamoProperty() {
        return fechaPrestamo;
    }

    public SimpleObjectProperty<LocalDate> fechaDevolucionProperty() {
        return fechaDevolucion;
    }

    public SimpleStringProperty libroProperty() {
        return libro;
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
                        rs.getString("dniUsuario"),
                        rs.getDate("fechaPrestamo").toLocalDate(),
                        rs.getDate("fechaDevolucion").toLocalDate(),
                        rs.getString("libro")
                );
                prestamo.idPrestamo.set(rs.getInt("idPrestamo"));
                lista.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return lista;
    }

    public void anadirPrestamo(PrestamoModelo prestamo) throws SQLException {
        String sql = "INSERT INTO prestamos (dniUsuario, fechaPrestamo, fechaDevolucion, libro) VALUES (?, ?, ?, ?)";
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, prestamo.getDniUsuario());
            stmt.setDate(2, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(3, Date.valueOf(prestamo.getFechaDevolucion()));
            stmt.setString(4, prestamo.getLibro());
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
        String sql = "UPDATE prestamos SET fechaPrestamo = ?, fechaDevolucion = ?, libro = ? WHERE idPrestamo = ?";
        Conexion conexion = new Conexion();
        try (Connection conn = conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(2, Date.valueOf(prestamo.getFechaDevolucion()));
            stmt.setString(3, prestamo.getLibro());
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
