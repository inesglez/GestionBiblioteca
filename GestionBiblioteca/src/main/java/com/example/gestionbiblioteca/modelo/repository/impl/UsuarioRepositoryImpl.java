package com.example.gestionbiblioteca.modelo.repository.impl;

import com.example.gestionbiblioteca.modelo.UsuarioVO;
import com.example.gestionbiblioteca.modelo.repository.ExceptionUsuario;
import com.example.gestionbiblioteca.modelo.repository.UsuarioRepository;


import java.sql.*;
import java.util.ArrayList;


public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final Conexion conexion = new Conexion();
    private Statement stmt;
    private String sentencia;
    private ArrayList<UsuarioVO> usuarios;
    private UsuarioVO usuario;

    public UsuarioRepositoryImpl() {
    }

    public ArrayList<UsuarioVO> ObtenerListaUsuarios() throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            this.usuarios = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM usuarios";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");
                String DNI = rs.getString("DNI");
                this.usuario= new UsuarioVO(DNI,nombre,apellidos,direccion,localidad,provincia);
                this.usuarios.add(this.usuario);
            }

            this.conexion.desconectarBD(conn);
            return this.usuarios;
        } catch (SQLException var6) {
            throw new ExceptionUsuario("No se ha podido realizar la operación");
        }
    }

    public void addUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario{
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "INSERT INTO usuario (nombre, apellidos, direccion, localidad, provincia, DNI) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVO.getNombre());
            stmt.setString(2, usuarioVO.getApellidos());
            stmt.setString(3, usuarioVO.getDireccion());
            stmt.setString(4, usuarioVO.getLocalidad());
            stmt.setString(5, usuarioVO.getProvincia());
            stmt.setString(6, usuarioVO.getDNI());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Imprime el detalle del error
            throw new ExceptionUsuario("No se ha podido realizar la edición");
        }
    }

    public void deleteUsuario(String dniUsuario) throws ExceptionUsuario{
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "DELETE FROM usuario WHERE DNI = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dniUsuario);  // Asignar el valor de dniPersona de forma segur
            pstmt.executeUpdate();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionUsuario("No se ha podido realizar la eliminación");
        }
    }


    public void editUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "UPDATE usuario SET nombre = ?, apellidos = ?, direccion = ?, localidad = ?, provincia = ? WHERE DNI = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVO.getNombre());
            stmt.setString(2, usuarioVO.getApellidos());
            stmt.setString(3, usuarioVO.getDireccion());
            stmt.setString(4, usuarioVO.getLocalidad());
            stmt.setString(5, usuarioVO.getProvincia());
            stmt.setString(6, usuarioVO.getDNI());
            stmt.executeUpdate();
        } catch (Exception var4) {
            throw new ExceptionUsuario("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExceptionUsuario{
        int lastUsuarioId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT codigo FROM Usuario ORDER BY codigo DESC LIMIT 1"); registro.next(); lastUsuarioId = registro.getInt("codigo")) {
            }

            return lastUsuarioId;
        } catch (SQLException var5) {
            throw new ExceptionUsuario("No se ha podido realizar la busqueda del ID");
        }
    }
}
