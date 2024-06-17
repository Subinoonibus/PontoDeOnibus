/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Usuario
 */
import modelo.HistoricoPeso;
import factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoricoPesoDAOImpl implements HistoricoPesoDAO {
    private static final Logger LOGGER = Logger.getLogger(HistoricoPesoDAOImpl.class.getName());

    @Override
    public void inserir(HistoricoPeso historicoPeso) {
        String sql = "INSERT INTO historico_peso (cpf_aluno, data, peso) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, historicoPeso.getCpfAluno());
            stmt.setString(2, historicoPeso.getData());
            stmt.setDouble(3, historicoPeso.getPeso());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inserir histórico de peso", e);
        }
    }

    @Override
    public void atualizar(HistoricoPeso historicoPeso) {
        String sql = "UPDATE historico_peso SET peso = ? WHERE cpf_aluno = ? AND data = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, historicoPeso.getPeso());
            stmt.setString(2, historicoPeso.getCpfAluno());
            stmt.setString(3, historicoPeso.getData());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar histórico de peso", e);
        }
    }

    @Override
    public void excluir(String cpfAluno, String data) {
        String sql = "DELETE FROM historico_peso WHERE cpf_aluno = ? AND data = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpfAluno);
            stmt.setString(2, data);

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir histórico de peso", e);
        }
    }

    @Override
    public List<HistoricoPeso> listarPorAluno(String cpfAluno) {
        List<HistoricoPeso> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico_peso WHERE cpf_aluno = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpfAluno);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HistoricoPeso historicoPeso = new HistoricoPeso();
                    historicoPeso.setCpfAluno(rs.getString("cpf_aluno"));
                    historicoPeso.setData(rs.getString("data"));
                    historicoPeso.setPeso(rs.getDouble("peso"));
                    historicos.add(historicoPeso);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar históricos de peso por aluno", e);
        }

        return historicos;
    }

    @Override
    public List<HistoricoPeso> listarTodos() {
        List<HistoricoPeso> historicos = new ArrayList<>();
        String sql = "SELECT * FROM historico_peso";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HistoricoPeso historicoPeso = new HistoricoPeso();
                historicoPeso.setCpfAluno(rs.getString("cpf_aluno"));
                historicoPeso.setData(rs.getString("data"));
                historicoPeso.setPeso(rs.getDouble("peso"));
                historicos.add(historicoPeso);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar todos os históricos de peso", e);
        }

        return historicos;
    }

    @Override
    public HistoricoPeso consultar(String cpfAluno, String data) {
        String sql = "SELECT * FROM historico_peso WHERE cpf_aluno = ? AND data = ?";
        HistoricoPeso historicoPeso = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpfAluno);
            stmt.setString(2, data);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    historicoPeso = new HistoricoPeso();
                    historicoPeso.setCpfAluno(rs.getString("cpf_aluno"));
                    historicoPeso.setData(rs.getString("data"));
                    historicoPeso.setPeso(rs.getDouble("peso"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao consultar histórico de peso", e);
        }

        return historicoPeso;
    }
}


   




       

