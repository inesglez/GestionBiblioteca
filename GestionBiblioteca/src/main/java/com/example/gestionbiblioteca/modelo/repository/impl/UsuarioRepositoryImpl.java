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

    // Método para obtener la lista de usuarios
    @Override
    public ArrayList<UsuarioVO> ObtenerListaUsuarios() throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            this.usuarios = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM usuarios";  // Cambié 'usuario' por 'usuarios'
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String localidad = rs.getString("localidad");
                String provincia = rs.getString("provincia");
                String DNI = rs.getString("DNI");  // Asegúrate de que 'DNI' esté en mayúsculas en la base de datos
                this.usuario = new UsuarioVO(DNI, nombre, apellidos, direccion, localidad, provincia);
                this.usuarios.add(this.usuario);
            }

            this.conexion.desconectarBD(conn);
            return this.usuarios;
        } catch (SQLException var6) {
            throw new ExceptionUsuario("No se ha podido realizar la operación");
        }
    }

    // Método para agregar un nuevo usuario
    public void addUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "INSERT INTO usuarios (nombre, apellidos, direccion, localidad, provincia, DNI) VALUES (?, ?, ?, ?, ?, ?)";  // Cambié 'usuario' por 'usuarios'
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
            throw new ExceptionUsuario("No se ha podido realizar la operación");
        }
    }

    // Método para eliminar un usuario por DNI
    public void deleteUsuario(String dniUsuario) throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "DELETE FROM usuarios WHERE DNI = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dniUsuario);  // Asignar el valor de dniUsuario de forma segura

            // Ejecutar la actualización y obtener las filas afectadas
            int rowsAffected = pstmt.executeUpdate();

            // Imprimir las filas afectadas
            System.out.println("Filas afectadas: " + rowsAffected);

            // Verificar si no se eliminó nada
            if (rowsAffected == 0) {
                System.out.println("No se eliminó ningún usuario con el DNI: " + dniUsuario);
            }

            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionUsuario("No se ha podido realizar la eliminación");
        }
    }


    // Método para editar un usuario
    public void editUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "UPDATE usuarios SET nombre = ?, apellidos = ?, direccion = ?, localidad = ?, provincia = ? WHERE DNI = ?";  // Cambié 'usuario' por 'usuarios'
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioVO.getNombre());
            stmt.setString(2, usuarioVO.getApellidos());
            stmt.setString(3, usuarioVO.getDireccion());
            stmt.setString(4, usuarioVO.getLocalidad());
            stmt.setString(5, usuarioVO.getProvincia());
            stmt.setString(6, usuarioVO.getDNI());
            stmt.executeUpdate();
        } catch (Exception var4) {
            throw new ExceptionUsuario("No se ha podido realizar la operación");
        }
    }

    // Método para obtener el último ID (utilizado para generar IDs de usuarios)
    public int lastId() throws ExceptionUsuario {
        int lastUsuarioId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();
            ResultSet rs = comando.executeQuery("SELECT codigo FROM usuarios ORDER BY codigo DESC LIMIT 1");  // Cambié 'Usuario' por 'usuarios'
            if (rs.next()) {
                lastUsuarioId = rs.getInt("codigo");
            }

            return lastUsuarioId;
        } catch (SQLException var5) {
            throw new ExceptionUsuario("No se ha podido obtener el último ID del usuario");
        }
    }
}
