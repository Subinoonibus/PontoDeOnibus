/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Usuario
 */
import modelo.Aluno;
import factory.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImpl implements AlunoDAO {

    @Override
    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (cpf, nome, dataNascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setDouble(4, aluno.getPeso());
            stmt.setInt(5, aluno.getAltura());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(String cpf) {
        String sql = "DELETE FROM aluno WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, dataNascimento = ?, peso = ?, altura = ? WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getDataNascimento());
            stmt.setDouble(3, aluno.getPeso());
            stmt.setInt(4, aluno.getAltura());
            stmt.setString(5, aluno.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Aluno consultar(String cpf) {
        String sql = "SELECT * FROM aluno WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Aluno(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("dataNascimento"),
                        rs.getDouble("peso"),
                        rs.getInt("altura")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Aluno> listarTodos() {
        String sql = "SELECT * FROM aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("dataNascimento"),
                        rs.getDouble("peso"),
                        rs.getInt("altura")
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}

