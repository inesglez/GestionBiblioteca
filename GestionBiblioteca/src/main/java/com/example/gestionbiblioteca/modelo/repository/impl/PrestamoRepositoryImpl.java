package com.example.gestionbiblioteca.modelo.repository.impl;

import com.example.gestionbiblioteca.modelo.PrestamoVO;
import com.example.gestionbiblioteca.modelo.repository.ExceptionPrestamo;
import com.example.gestionbiblioteca.modelo.repository.PrestamoRepository;

import java.sql.*;
import java.util.ArrayList;

public class PrestamoRepositoryImpl implements PrestamoRepository {
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private PrestamoVO prestamo;

    public PrestamoRepositoryImpl() {
    }

    public ArrayList<PrestamoVO> ObtenerListaPrestamos() throws ExceptionPrestamo {
        try {
            Connection conn = this.conexion.conectarBD();
            ArrayList<PrestamoVO> prestamos = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM prestamo";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                int idPrestamo = rs.getInt("id_prestamo");
                Date fechaPrestamo = rs.getDate("fecha_prestamo");
                Date fechaDevolucion = rs.getDate("fecha_devolucion");
                String libroPrestado = rs.getString("libro_prestado");
                String dniCliente = rs.getString("dni_cliente");

                this.prestamo = new PrestamoVO(idPrestamo, fechaPrestamo.toLocalDate(), fechaDevolucion.toLocalDate(), libroPrestado, dniCliente);
                prestamos.add(this.prestamo);
            }

            this.conexion.desconectarBD(conn);
            return prestamos;
        } catch (SQLException var6) {
            throw new ExceptionPrestamo("No se ha podido realizar la operación");
        }
    }

    public void addPrestamo(PrestamoVO prestamoVO) throws ExceptionPrestamo {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, libro_prestado, dni_cliente) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Asignamos valores al PreparedStatement
            stmt.setDate(1, Date.valueOf(prestamoVO.getFechaPrestamo()));  // Conversión de LocalDate a java.sql.Date
            stmt.setDate(2, Date.valueOf(prestamoVO.getFechaDevolucion()));  // Conversión de LocalDate a java.sql.Date
            stmt.setString(3, prestamoVO.getLibroPrestado());
            stmt.setString(4, prestamoVO.getDniCliente());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("No se ha podido realizar el préstamo");
        }
    }

    public void deletePrestamo(int idPrestamo) throws ExceptionPrestamo {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "DELETE FROM prestamo WHERE id_prestamo = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPrestamo);
            pstmt.executeUpdate();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionPrestamo("No se ha podido realizar la eliminación");
        }
    }

    public void editPrestamo(PrestamoVO prestamoVO) throws ExceptionPrestamo {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "UPDATE prestamo SET fecha_prestamo = ?, fecha_devolucion = ?, libro_prestado = ?, dni_cliente = ? WHERE id_prestamo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDate(1, Date.valueOf(prestamoVO.getFechaPrestamo()));  // Conversión de LocalDate a java.sql.Date
            stmt.setDate(2, Date.valueOf(prestamoVO.getFechaDevolucion()));  // Conversión de LocalDate a java.sql.Date
            stmt.setString(3, prestamoVO.getLibroPrestado());
            stmt.setString(4, prestamoVO.getDniCliente());
            stmt.setInt(5, prestamoVO.getIdPrestamo());

            stmt.executeUpdate();
        } catch (Exception var4) {
            throw new ExceptionPrestamo("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExceptionPrestamo {
        int lastPrestamoId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for (ResultSet registro = comando.executeQuery("SELECT id_prestamo FROM prestamo ORDER BY id_prestamo DESC LIMIT 1"); registro.next(); lastPrestamoId = registro.getInt("id_prestamo")) {
            }

            return lastPrestamoId;
        } catch (SQLException var5) {
            throw new ExceptionPrestamo("No se ha podido realizar la búsqueda del ID");
        }
    }

    @Override
    public int[] countActuales() throws ExceptionPrestamo {
        return countByType();
    }

    private int[] countByType() throws ExceptionPrestamo {
        int[] counts = new int[4]; // Array para almacenar las cantidades de cada tipo
        String[] tiposLibro = {"Ficción", "No ficción", "Ciencia", "Historia"}; // Tipos de libros
        String query = "SELECT COUNT(*) AS total " +
                "FROM prestamo " +
                "WHERE libro_prestado = ? AND fecha_devolucion >= CURRENT_DATE AND fecha_prestamo <= CURRENT_DATE";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < tiposLibro.length; i++) {
                stmt.setString(1, tiposLibro[i]);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        counts[i] = rs.getInt("total"); // Almacenar la cantidad en el array
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counts;
    }

    @Override
    public int[] countMonthsByType(String tipoLibro) throws ExceptionPrestamo {
        int[] countByMonth = new int[12]; // Un array para cada mes (de enero a diciembre)

        String sql = "SELECT m.mes, COUNT(p.id_prestamo) AS total " +
                "FROM ( " +
                "    SELECT 1 AS mes UNION ALL " +
                "    SELECT 2 UNION ALL " +
                "    SELECT 3 UNION ALL " +
                "    SELECT 4 UNION ALL " +
                "    SELECT 5 UNION ALL " +
                "    SELECT 6 UNION ALL " +
                "    SELECT 7 UNION ALL " +
                "    SELECT 8 UNION ALL " +
                "    SELECT 9 UNION ALL " +
                "    SELECT 10 UNION ALL " +
                "    SELECT 11 UNION ALL " +
                "    SELECT 12 " +
                ") AS m " +
                "LEFT JOIN prestamo p ON MONTH(p.fecha_prestamo) = m.mes AND p.libro_prestado = ? " +
                "GROUP BY m.mes " +
                "ORDER BY m.mes";


        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoLibro);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int mes = rs.getInt("mes") - 1; // Los meses en la base de datos son de 1 a 12, pero el array empieza en 0
                    countByMonth[mes] = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("Error al contar los préstamos por mes.");
        }

        return countByMonth;
    }

    @Override
    public int countConcretosByType(PrestamoVO prestamoVO) throws ExceptionPrestamo {
        int count = 0;

        // Consulta SQL para contar los préstamos con las condiciones especificadas
        String sql = "SELECT COUNT(*) AS total " +
                "FROM prestamo " +
                "WHERE libro_prestado = ? " +
                "AND fecha_prestamo <= ? " +
                "AND fecha_devolucion >= ?";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los valores en la consulta preparada
            stmt.setString(1, prestamoVO.getLibroPrestado());
            stmt.setDate(2, Date.valueOf(prestamoVO.getFechaPrestamo()));
            stmt.setDate(3, Date.valueOf(prestamoVO.getFechaDevolucion()));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionPrestamo("Error al contar los préstamos concretos.");
        }

        return count;
    }
}
