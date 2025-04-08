package com.example.gestionbiblioteca.modelo.repository.impl;

import com.example.gestionbiblioteca.modelo.LibroVO;
import com.example.gestionbiblioteca.modelo.repository.ExceptionLibro;
import com.example.gestionbiblioteca.modelo.repository.LibroRepository;

import java.sql.*;
import java.util.ArrayList;

public class LibroRepositoryImpl implements LibroRepository {
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<LibroVO> libros;
    private LibroVO libro;

    public LibroRepositoryImpl() {
    }

    // Método para obtener la lista de libros
    @Override
    public ArrayList<LibroVO> obtenerListaLibros() throws ExceptionLibro {
        try {
            Connection conn = this.conexion.conectarBD();
            this.libros = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM libros";  // Cambié 'usuarios' por 'libros'
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                int idLibro = rs.getInt("idLibro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int anioPublicacion = rs.getInt("anioPublicacion");
                String editorial = rs.getString("editorial");
                boolean disponible = rs.getBoolean("disponible");
                String portada = rs.getString("portada");

                this.libro = new LibroVO(idLibro, titulo, autor, anioPublicacion, editorial, disponible, portada); // Usar LibroVO
                this.libros.add(this.libro);
            }

            this.conexion.desconectarBD(conn);
            return this.libros;
        } catch (SQLException e) {
            throw new ExceptionLibro("No se ha podido realizar la operación");
        }
    }

    // Método para agregar un nuevo libro
    public void addLibro(LibroVO libro) throws ExceptionLibro {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "INSERT INTO libros (titulo, autor, anioPublicacion, editorial, disponible, portada) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setString(4, libro.getEditorial());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.setString(6, libro.getPortada());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Imprime el detalle del error
            throw new ExceptionLibro("No se ha podido agregar el libro");
        }
    }

    // Método para eliminar un libro por ID
    public void deleteLibro(int idLibro) throws ExceptionLibro {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "DELETE FROM libros WHERE idLibro = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idLibro);  // Asignar el valor de idLibro de forma segura
            pstmt.executeUpdate();
            this.conexion.desconectarBD(conn);
        } catch (SQLException e) {
            throw new ExceptionLibro("No se ha podido eliminar el libro");
        }
    }

    // Método para editar un libro
    public void editLibro(LibroVO libro) throws ExceptionLibro {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "UPDATE libros SET titulo = ?, autor = ?, anioPublicacion = ?, editorial = ?, disponible = ?, portada = ? WHERE idLibro = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setInt(3, libro.getAnioPublicacion());
            stmt.setString(4, libro.getEditorial());
            stmt.setBoolean(5, libro.isDisponible());
            stmt.setString(6, libro.getPortada());
            stmt.setInt(7, libro.getIdLibro());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new ExceptionLibro("No se ha podido editar el libro");
        }
    }

    // Método para obtener el último ID (utilizado para generar IDs de libros)
    public int lastId() throws ExceptionLibro {
        int lastLibroId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();
            ResultSet rs = comando.executeQuery("SELECT idLibro FROM libros ORDER BY idLibro DESC LIMIT 1");
            if (rs.next()) {
                lastLibroId = rs.getInt("idLibro");
            }

            return lastLibroId;
        } catch (SQLException e) {
            throw new ExceptionLibro("No se ha podido obtener el último ID del libro");
        }
    }
}


