package com.example.gestionbiblioteca.modelo.repository.impl;

import com.example.gestionbiblioteca.modelo.PrestamoVO;
import com.example.gestionbiblioteca.modelo.PrestamoModelo;
import com.example.gestionbiblioteca.modelo.repository.ExceptionPrestamo;
import com.example.gestionbiblioteca.modelo.repository.PrestamoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                        rs.getInt("idPrestamo"),
                        rs.getDate("fechaPrestamo").toLocalDate(),
                        rs.getDate("fechaDevolucion") != null ? rs.getDate("fechaDevolucion").toLocalDate() : null,
                        rs.getString("titulo"),
                        rs.getString("dni")
                );
                prestamosVO.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo obtener la lista de préstamos");
        }

        return convertirModelo(prestamosVO);
    }

    @Override
    public void addPrestamo(PrestamoVO prestamo) throws ExceptionPrestamo {
        String sql = "INSERT INTO prestamos (dni, titulo, fechaPrestamo, fechaDevolucion) VALUES (?, ?, ?, ?)";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prestamo.getDni());
            stmt.setString(2, prestamo.gettitulo());
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
        String sql = "DELETE FROM prestamos WHERE idPrestamo = ?";
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
        String sql = "UPDATE prestamos SET dni = ?, titulo = ?, fechaPrestamo = ?, fechaDevolucion = ? WHERE idPrestamo = ?";
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, prestamo.getDni());
            stmt.setString(2, prestamo.gettitulo());
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
            String sql = "SELECT idPrestamo FROM prestamos ORDER BY idPrestamo DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lastPrestamoId = rs.getInt("idPrestamo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se pudo obtener el último ID de préstamo");
        }
        return lastPrestamoId;
    }

    @Override
    public int[] countActuales() throws ExceptionPrestamo {
        int[] count = new int[2];
        try (Connection conn = this.conexion.conectarBD();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT COUNT(*) FROM prestamos WHERE fechaDevolucion IS NULL";
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
        int[] count = new int[12];
        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT MONTH(fechaPrestamo) AS mes, COUNT(*) AS total FROM prestamos GROUP BY MONTH(fechaPrestamo)")) {
            ResultSet rs = stmt.executeQuery();

            for (int i = 0; i < 12; i++) {
                count[i] = 0;
            }

            while (rs.next()) {
                int mes = rs.getInt("mes");
                count[mes - 1] = rs.getInt("total");
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
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM prestamos WHERE dni = ?")) {
            stmt.setString(1, prestamo.getDni());
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
    public List<Map.Entry<String, Integer>> obtenerLibrosMasPrestados() throws ExceptionPrestamo {
        Map<String, Integer> libroCount = new HashMap<>();
        String sql = "SELECT titulo, COUNT(*) as count FROM prestamos GROUP BY titulo ORDER BY count DESC LIMIT 10"; // top 10 libros más prestados
        try (Connection conn = conexion.conectarBD();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                libroCount.put(rs.getString("titulo"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            throw new ExceptionPrestamo("Error al obtener los libros más prestados");
        }
        return libroCount.entrySet().stream().collect(Collectors.toList());
    }


    public static List<PrestamoModelo> convertirModelo(List<PrestamoVO> prestamosVO) {
        List<PrestamoModelo> prestamosModelo = new ArrayList<>();
        for (PrestamoVO prestamoVO : prestamosVO) {
            prestamosModelo.add(new PrestamoModelo(
                    prestamoVO.getDni(),
                    prestamoVO.getFechaPrestamo(),
                    prestamoVO.getFechaDevolucion(),
                    prestamoVO.gettitulo()
            ));
        }
        return prestamosModelo;
    }
}
