package com.example.gestionbiblioteca.modelo.repository;


import com.example.gestionbiblioteca.modelo.PrestamoVO;

import java.util.ArrayList;

// Interfaz que maneja las funciones de acceso a la base de datos, en concreto a la tabla prestamo
public interface PrestamoRepository {
    ArrayList<PrestamoVO> ObtenerListaPrestamos() throws ExceptionPrestamo;

    void addPrestamo(PrestamoVO var1) throws ExceptionPrestamo;

    void deletePrestamo(int var1) throws ExceptionPrestamo;

    void editPrestamo(PrestamoVO var1) throws ExceptionPrestamo;

    int lastId() throws ExceptionPrestamo;

    int[] countActuales() throws ExceptionPrestamo;

    int[] countMonthsByType(String tipo) throws ExceptionPrestamo;

    int countConcretasByType(PrestamoVO prestamo) throws ExceptionPrestamo;

}
