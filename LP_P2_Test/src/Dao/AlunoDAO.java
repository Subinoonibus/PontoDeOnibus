/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Usuario
 */
import Modelo.Aluno;
import Factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {
    private Connection connection;

    public AlunoDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public void inserir(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO Aluno (cpf, nome, dataNasc, peso, altura) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNasc());
            stmt.setInt(4, aluno.getPeso());
            stmt.setInt(5, aluno.getAltura());
            stmt.executeUpdate();
        }
    }

    public void excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM Aluno WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }

    public void atualizar(Aluno aluno) throws SQLException {
        String sql = "UPDATE Aluno SET nome = ?, dataNasc = ?, peso = ?, altura = ? WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getDataNasc());
            stmt.setInt(3, aluno.getPeso());
            stmt.setInt(4, aluno.getAltura());
            stmt.setString(5, aluno.getCpf());
            stmt.executeUpdate();
        }
    }

    public Aluno consultar(String cpf) throws SQLException {
        String sql = "SELECT * FROM Aluno WHERE cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("dataNasc"),
                        rs.getInt("peso"),
                        rs.getInt("altura")
                );
            }
        }
        return null;
    }
}


