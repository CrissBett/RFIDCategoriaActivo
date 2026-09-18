package co.edu.sena.rfid.dao;

import co.edu.sena.rfid.conexion.ConexionBD;
import co.edu.sena.rfid.modelo.CategoriaActivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaActivoDAO {

    // CREATE
    public boolean insertarCategoria(CategoriaActivo categoria) {

        String sql = """
                INSERT INTO categoriaactivo
                    (nombre, descripcion, activo)
                VALUES (?, ?, ?)
                """;

        try (
                Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setBoolean(3, categoria.isActivo());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al insertar la categoria: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // READ
    public List<CategoriaActivo> listarCategorias() {

        List<CategoriaActivo> categorias = new ArrayList<>();

        String sql = """
                SELECT id_categoria, nombre, descripcion, activo
                FROM categoriaactivo
                ORDER BY id_categoria
                """;

        try (
                Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                CategoriaActivo categoria = new CategoriaActivo();

                categoria.setIdCategoria(
                        rs.getInt("id_categoria")
                );

                categoria.setNombre(
                        rs.getString("nombre")
                );

                categoria.setDescripcion(
                        rs.getString("descripcion")
                );

                categoria.setActivo(
                        rs.getBoolean("activo")
                );

                categorias.add(categoria);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al consultar las categorias: "
                    + e.getMessage()
            );
        }

        return categorias;
    }

    // UPDATE
    public boolean actualizarCategoria(CategoriaActivo categoria) {

        String sql = """
                UPDATE categoriaactivo
                SET nombre = ?,
                    descripcion = ?,
                    activo = ?
                WHERE id_categoria = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setBoolean(3, categoria.isActivo());
            ps.setInt(4, categoria.getIdCategoria());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al actualizar la categoria: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // DELETE
    public boolean eliminarCategoria(int idCategoria) {

        String sql = """
                DELETE FROM categoriaactivo
                WHERE id_categoria = ?
                """;

        try (
                Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Error al eliminar la categoria: "
                    + e.getMessage()
            );

            return false;
        }
    }
}
