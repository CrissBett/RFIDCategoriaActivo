package co.edu.sena.rfid.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexion JDBC con la base de datos del Sistema RFID.
 */
public final class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/rfid_gestion_activos"
            + "?useSSL=false"
            + "&serverTimezone=America/Bogota"
            + "&allowPublicKeyRetrieval=true";

    private static final String VARIABLE_USUARIO =
            "RFID_DB_USER";

    private static final String VARIABLE_PASSWORD =
            "RFID_DB_PASSWORD";

    private ConexionBD() {
        // Evita crear instancias de esta clase de utilidad.
    }

    /**
     * Establece una conexion JDBC con la base de datos.
     *
     * @return conexion JDBC abierta
     * @throws SQLException si faltan credenciales o falla la conexion
     */
    public static Connection conectar()
            throws SQLException {

        String usuario =
                System.getenv(VARIABLE_USUARIO);

        String password =
                System.getenv(VARIABLE_PASSWORD);

        if (usuario == null || usuario.isBlank()) {
            throw new SQLException(
                    "No se encontro la variable "
                    + VARIABLE_USUARIO
            );
        }

        if (password == null || password.isBlank()) {
            throw new SQLException(
                    "No se encontro la variable "
                    + VARIABLE_PASSWORD
            );
        }

        return DriverManager.getConnection(
                URL,
                usuario,
                password
        );
    }
}