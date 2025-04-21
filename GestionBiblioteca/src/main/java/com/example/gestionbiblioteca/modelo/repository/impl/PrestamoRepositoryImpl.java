package com.example.gestionbiblioteca.modelo.repository.impl;

import com.example.gestionbiblioteca.modelo.PrestamoVO;
import com.example.gestionbiblioteca.modelo.PrestamoModelo; // Asegúrate de importar el modelo correspondiente
import com.example.gestionbiblioteca.modelo.repository.ExceptionPrestamo;
import com.example.gestionbiblioteca.modelo.repository.PrestamoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoRepositoryImpl implements PrestamoRepository {
    private final Conexion conexion = new Conexion();

    @Override
    public List<PrestamoModelo> ObtenerListaPrestamos() throws ExceptionPrestamo {
        List<PrestamoVO> prestamosVO = new ArrayList<>();
        try (Connection conn = this.conexion.conectarBD();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM prestamos";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                PrestamoVO prestamo = new PrestamoVO(
                        rs.getInt("id"),
                        rs.getDate("fecha_prestamo").toLocalDate(),
                        rs.getDate("fecha_devolucion") != null ? rs.getDate("fecha_devolucion").toLocalDate() : null,
                        rs.getInt("id_libro"),
                        rs.getString("DNI_usuario")
                );
                prestamosVO.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo obtener la lista de préstamos");
        }

        // Convertir de PrestamoVO a PrestamoModelo
        return convertirModelo(prestamosVO);
    }

    @Override
    public void addPrestamo(PrestamoVO prestamo) throws ExceptionPrestamo {
        String sql = "INSERT INTO prestamos (DNI_usuario, id_libro, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prestamo.getDniUsuario());
            stmt.setInt(2, prestamo.getIdLibro()); // Usar el id_libro
            stmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(4, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo añadir el préstamo");
        }
    }

    @Override
    public void deletePrestamo(int idPrestamo) throws ExceptionPrestamo {
        String sql = "DELETE FROM prestamos WHERE id = ?";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPrestamo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo eliminar el préstamo");
        }
    }

    @Override
    public void editPrestamo(PrestamoVO prestamo) throws ExceptionPrestamo {
        String sql = "UPDATE prestamos SET DNI_usuario = ?, id_libro = ?, fecha_prestamo = ?, fecha_devolucion = ? WHERE id = ?";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prestamo.getDniUsuario());
            stmt.setInt(2, prestamo.getIdLibro()); // Usar el id_libro
            stmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            stmt.setDate(4, prestamo.getFechaDevolucion() != null ? Date.valueOf(prestamo.getFechaDevolucion()) : null);
            stmt.setInt(5, prestamo.getIdPrestamo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo editar el préstamo");
        }
    }

    @Override
    public int lastId() throws ExceptionPrestamo {
        int lastPrestamoId = 0;
        try (Connection conn = this.conexion.conectarBD();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT id FROM prestamos ORDER BY id DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lastPrestamoId = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo obtener el último ID de préstamo");
        }
        return lastPrestamoId;
    }

    @Override
    public int[] countActuales() throws ExceptionPrestamo {
        int[] count = new int[2]; // Suponiendo que el primer valor es el total de préstamos activos y el segundo es el total de préstamos
        try (Connection conn = this.conexion.conectarBD();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT COUNT(*) FROM prestamos WHERE fecha_devolucion IS NULL";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count[0] = rs.getInt(1);
            }

            sql = "SELECT COUNT(*) FROM prestamos";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count[1] = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo obtener el conteo de préstamos");
        }
        return count;
    }

    @Override
    public int[] countMonthsByType(String tipo) throws ExceptionPrestamo {
        int[] count = new int[12]; // Para contar los préstamos por cada mes
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT MONTH(fecha_prestamo) AS mes, COUNT(*) AS total FROM prestamos GROUP BY MONTH(fecha_prestamo)")) {
            ResultSet rs = stmt.executeQuery();

            // Inicializamos el array con ceros (aunque Java lo hace por defecto, es bueno ser explícito)
            for (int i = 0; i < 12; i++) {
                count[i] = 0;
            }

            while (rs.next()) {
                int mes = rs.getInt("mes");
                count[mes - 1] = rs.getInt("total"); // Los meses en MySQL son de 1 a 12, por eso se ajusta a 0-based index
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo obtener el conteo de préstamos por mes");
        }
        return count;
    }

    @Override
    public int countConcretasByType(PrestamoVO prestamo) throws ExceptionPrestamo {
        int count = 0;
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM prestamos WHERE DNI_usuario = ?")) {
            stmt.setString(1, prestamo.getDniUsuario());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo obtener el conteo de préstamos concretos");
        }
        return count;
    }

    // Método para convertir de PrestamoVO a PrestamoModelo
    public static List<PrestamoModelo> convertirModelo(List<PrestamoVO> prestamosVO) {
        List<PrestamoModelo> prestamosModelo = new ArrayList<>();
        for (PrestamoVO prestamoVO : prestamosVO) {
            prestamosModelo.add(new PrestamoModelo(
                    prestamoVO.getDniUsuario(),
                    prestamoVO.getFechaPrestamo(),
                    prestamoVO.getFechaDevolucion(),
                    prestamoVO.getIdLibro()  // Usamos el idLibro en lugar de libroPrestado
            ));
        }
        return prestamosModelo;
    }
}
