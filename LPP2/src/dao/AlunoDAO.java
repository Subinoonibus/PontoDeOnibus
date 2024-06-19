/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Usuario
 */
import factory.ConnectionFactory;
import modelo.Aluno;
import java.sql.*;
import java.sql.PreparedStatement;

/**
 *
 * @author conta
 */
public class AlunoDAO {
    private final Connection connection;
    private Aluno aluno;

    public AlunoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Aluno aluno) {
        String sql = "INSERT INTO aluno (cpf, nome, data_de_nascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, aluno.getCpf());
                stmt.setString(2, aluno.getNome());
                stmt.setString(3, aluno.getDataNascimento());
                stmt.setDouble(4, aluno.getPeso());
                stmt.setInt(5, aluno.getAltura());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar aluno: " + e.getMessage());
        }
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, data_de_nascimento = ?, peso = ?, altura = ? WHERE cpf = ?";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, aluno.getNome());
                stmt.setString(2, aluno.getDataNascimento());
                stmt.setDouble(3, aluno.getPeso());
                stmt.setInt(4, aluno.getAltura());
                stmt.setString(5, aluno.getCpf());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public Aluno consultar(String cpf) {
        String sql = "SELECT * FROM aluno WHERE cpf = ?";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, cpf);
                try (ResultSet rs = stmt.executeQuery()) {
                    Aluno aluno = null;
                    if (rs.next()) {
                        aluno = new Aluno();
                        aluno.setCpf(rs.getString("cpf"));
                        aluno.setNome(rs.getString("nome"));
                        aluno.setDataNascimento(rs.getString("data_de_nascimento"));
                        aluno.setPeso(rs.getDouble("peso"));
                        aluno.setAltura(rs.getInt("altura"));
                    }
                }
            }
            return aluno;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao consultar cliente: " + e.getMessage());
        }
    }
    
    public void deletar(String cpf) {
    String sqlDeleteHistoricoPeso = "DELETE FROM HistoricoPeso WHERE cpf = ?";
    String sqlDeleteAluno = "DELETE FROM aluno WHERE cpf = ?";
    PreparedStatement stmtHistoricoPeso = null;
    PreparedStatement stmtCliente = null;

    try {
        // Start a transaction
        connection.setAutoCommit(false);

        // Delete from weighthist
        stmtHistoricoPeso = connection.prepareStatement(sqlDeleteHistoricoPeso);
        stmtHistoricoPeso.setString(1, cpf);
        stmtHistoricoPeso.executeUpdate();

        // Delete from cliente
        stmtCliente = connection.prepareStatement(sqlDeleteAluno);
        stmtCliente.setString(1, cpf);
        stmtCliente.executeUpdate();

        // Commit the transaction
        connection.commit();
    } catch (SQLException e) {
        if (connection != null) {
            try {
                // Rollback the transaction in case of error
                connection.rollback();
            } catch (SQLException ex) {
            }
        }
        throw new RuntimeException("Erro ao deletar aluno: " + e.getMessage());
    } finally {
        if (stmtHistoricoPeso != null) {
            try {
                stmtHistoricoPeso.close();
            } catch (SQLException e) {
            }
        }
        if (stmtCliente != null) {
            try {
                stmtCliente.close();
            } catch (SQLException e) {
            }
        }
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
        }
    }
    }
}
