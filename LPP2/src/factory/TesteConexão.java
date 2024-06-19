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

public class TesteConexão {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            System.out.println("Conexão aberta!");
        }
    }
}
