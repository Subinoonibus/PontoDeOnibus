/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Usuario
 */
import Factory.ConnectionFactory;
import Modelo.HistPeso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class HistPesoDAO {
    private Connection connection;

    public HistPesoDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void inserir(HistPeso histPeso) throws SQLException {
        String sql = "INSERT INTO HistPeso (data, peso, cpf, nome, altura) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(histPeso.getData()));
            stmt.setInt(2, histPeso.getPeso());
            stmt.setString(3, histPeso.getCpf());
            stmt.setString(4, histPeso.getNome());
            stmt.setInt(5, histPeso.getAltura());
            stmt.executeUpdate();
        }
    }

    public void excluirPorCpf(String cpf) throws SQLException {
        String sql = "DELETE FROM HistPeso WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    public void atualizar(HistPeso histPeso) throws SQLException {
        String sql = "UPDATE HistPeso SET data = ?, peso = ?, nome = ?, altura = ? WHERE cpf = ? AND data = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(histPeso.getData()));
            stmt.setInt(2, histPeso.getPeso());
            stmt.setString(3, histPeso.getNome());
            stmt.setInt(4, histPeso.getAltura());
            stmt.setString(5, histPeso.getCpf());
            stmt.setDate(6, java.sql.Date.valueOf(histPeso.getData()));
            stmt.executeUpdate();
        }
    }

    // Método para consultar histórico por CPF
    public HistPeso consultar(String cpf) throws SQLException {
        // Implementar consulta por CPF
        return null; // Exemplo simplificado
    }
    
    public void salvarPesoHistorico(String cpf, int peso,int altura, LocalDate data) {
        String sql = "INSERT INTO weighthist (cpf, peso, altura, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/historico", "root", "@Ararazul13");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setFloat(2, peso);
            stmt.setFloat(3, altura);
            stmt.setDate(4, java.sql.Date.valueOf(data));
            

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao salvar histórico de peso: " + e.getMessage());
        }
    }

}


