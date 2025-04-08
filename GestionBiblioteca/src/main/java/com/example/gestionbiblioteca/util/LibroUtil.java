package com.example.gestionbiblioteca.util;

import com.example.gestionbiblioteca.Libro;
import com.example.gestionbiblioteca.modelo.LibroVO;

import java.util.ArrayList;

public class LibroUtil {
    static ArrayList<Libro> libros = new ArrayList<>();

    // Convierte la lista que recogemos de la base de datos a Libro
    public static ArrayList<Libro> convertirVo(ArrayList<LibroVO> librosVO) {
        for (LibroVO libroVO : librosVO) {
            libros.add(new Libro(
                    libroVO.getIdLibro(),
                    libroVO.getTitulo(),
                    libroVO.getAutor(),
                    libroVO.getAnioPublicacion(),
                    libroVO.getEditorial(),
                    libroVO.isDisponible(),
                    libroVO.getPortada()
            ));
        }
        return libros;
    }

    // Convierte una instancia de Libro a LibroVO
    public static LibroVO convertirVo(Libro libro) {
        return new LibroVO(
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getAnioPublicacion(),
                libro.getEditorial(),
                libro.isDisponible(),
                libro.getPortada()
        );
    }
}
