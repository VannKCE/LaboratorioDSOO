package Modelo;

import java.sql.*;

public class Conexion {
    private static Connection conn = null;

    public static Connection getConexion() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:sqlite:ranking.db";
                conn = DriverManager.getConnection(url);
                System.out.println("Conexión establecida.");
            }
        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conn;
    }
}
