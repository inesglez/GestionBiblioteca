package com.example.gestionbiblioteca.modelo.repository;



import com.example.gestionbiblioteca.modelo.UsuarioVO;

import java.util.ArrayList;

// Interfaz que maneja las funciones de acceso a la base de datos, en concreto a la tabla Usuario
public interface UsuarioRepository {
    ArrayList<UsuarioVO> ObtenerListaUsuarios() throws ExceptionUsuario;

    void addUsuario(UsuarioVO var1) throws ExceptionUsuario;

    void deleteUsuario(String var1) throws ExceptionUsuario
;

    void editUsuario
(UsuarioVO var1) throws ExceptionUsuario
;

    int lastId() throws ExceptionUsuario
;
}
