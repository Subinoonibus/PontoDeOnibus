/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package factory;

/**
 *
 * @author Usuario
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM alunos";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    System.out.println("CPF: " + cpf + ", Nome: " + nome);
                }
            }
        } catch (SQLException e) {
        }
    }
}


