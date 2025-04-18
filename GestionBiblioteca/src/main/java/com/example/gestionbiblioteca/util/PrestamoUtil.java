package com.example.gestionbiblioteca.util;

import com.example.gestionbiblioteca.modelo.PrestamoVO;
import com.example.gestionbiblioteca.modelo.PrestamoModelo;

import java.util.ArrayList;
import java.util.List;

public class PrestamoUtil {

    // Convierte la lista de PrestamoVO a PrestamoModelo
    public static List<PrestamoModelo> convertirModelo(List<PrestamoVO> prestamosVO) {
        List<PrestamoModelo> prestamosModelo = new ArrayList<>();
        for (PrestamoVO prestamoVO : prestamosVO) {
            prestamosModelo.add(new PrestamoModelo(
                    prestamoVO.getDniUsuario(),
                    prestamoVO.getFechaPrestamo(),
                    prestamoVO.getFechaDevolucion(),
                    prestamoVO.getLibroPrestado()
            ));
        }
        return prestamosModelo;
    }

    // Convierte la lista de PrestamoModelo a PrestamoVO
    public static List<PrestamoVO> convertirVo(List<PrestamoModelo> prestamosModelo) {
        List<PrestamoVO> prestamosVO = new ArrayList<>();
        for (PrestamoModelo prestamoModelo : prestamosModelo) {
            prestamosVO.add(new PrestamoVO(
                    prestamoModelo.getIdPrestamo(),
                    prestamoModelo.getFechaPrestamo(),
                    prestamoModelo.getFechaDevolucion(),
                    prestamoModelo.getLibro(),
                    prestamoModelo.getDniUsuario()
            ));
        }
        return prestamosVO;
    }

    // Convierte un solo PrestamoModelo a PrestamoVO
    public static PrestamoVO convertirVo(PrestamoModelo prestamoModelo) {
        return new PrestamoVO(
                prestamoModelo.getIdPrestamo(),
                prestamoModelo.getFechaPrestamo(),
                prestamoModelo.getFechaDevolucion(),
                prestamoModelo.getLibro(),
                prestamoModelo.getDniUsuario()
        );
    }

    // Convierte un solo PrestamoVO a PrestamoModelo
    public static PrestamoModelo convertirModelo(PrestamoVO prestamoVO) {
        return new PrestamoModelo(
                prestamoVO.getDniUsuario(),
                prestamoVO.getFechaPrestamo(),
                prestamoVO.getFechaDevolucion(),
                prestamoVO.getLibroPrestado()
        );
    }

}
