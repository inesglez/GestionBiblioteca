package com.example.gestionbiblioteca.util;


import com.example.gestionbiblioteca.Usuario;
import com.example.gestionbiblioteca.modelo.UsuarioVO;

import java.util.ArrayList;
// Esta clase se encargará de hacer las transformaciones pertinentes entre Persona y PersonaVO
public class UsuarioUtil {
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    // Convierte la lista que recogemos de la base de datos a Persona
    public static ArrayList<Usuario> convertirVo(ArrayList<UsuarioVO> usuarios2) {
        for (UsuarioVO sujeto : usuarios2) {
           usuarios.add(new Usuario(sujeto.getDNI(),sujeto.getNombre(),sujeto.getApellidos(),sujeto.getDireccion(),sujeto.getLocalidad(),sujeto.getProvincia()));
        }
        return usuarios;
    }
    // Convierte una instancia de Persona a PersonaVO
    public static UsuarioVO convertirVo(Usuario persona) {
        return new UsuarioVO(persona.getDNI(),persona.getNombre(),persona.getApellidos(),persona.getDireccion(),persona.getLocalidad(),persona.getProvincia());
    }
}
