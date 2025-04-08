package com.example.gestionbiblioteca.modelo.repository;

import com.example.gestionbiblioteca.modelo.LibroVO;

import java.util.ArrayList;

// Interfaz que maneja las funciones de acceso a la base de datos, en concreto a la tabla Libro
public interface LibroRepository {
    ArrayList<LibroVO> obtenerListaLibros() throws ExceptionLibro;  // Cambiado a LibroVO
    void deleteLibro(int idLibro) throws ExceptionLibro;
    void editLibro(LibroVO libro) throws ExceptionLibro;
    int lastId() throws ExceptionLibro;
}
