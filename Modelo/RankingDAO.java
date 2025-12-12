package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RankingDAO {

    public static void guardarResultado(String ganador, String perdedor, int soldadosVivos) {
        System.out.println("GUARDANDO EN BD: " + ganador + " | " + perdedor + " | " + soldadosVivos);
        
        String sql = "INSERT INTO ranking (ganador, perdedor, soldados_vivos) VALUES (?, ?, ?)";

        try {
            Connection conn = Conexion.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, ganador);
            stmt.setString(2, perdedor);
            stmt.setInt(3, soldadosVivos);

            stmt.executeUpdate();
            stmt.close();

            System.out.println("GUARDADO CON ÉXITO");

        } catch (SQLException e) {
            System.out.println("ERROR AL GUARDAR: " + e.getMessage());
        }
    }

}
