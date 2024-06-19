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
import modelo.HistoricoPeso;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Date;

public class HistoricoPesoDAO {

    private final Connection connection;

    public HistoricoPesoDAO() {
        this.connection = new ConnectionFactory().getConnection(); // Inicializa a conexão corretamente
    }

    public void adicionar(HistoricoPeso historico) {
        String sql = "INSERT INTO weighthist (data,altura , peso, cpf) VALUES (?, ?, ?)";
        try {
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setDate(1, Date.valueOf(historico.getData()));
                stmt.setDouble(2, historico.getPeso());
                stmt.setString(3, historico.getCpfAluno());
                stmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar histórico de peso: " + e.getMessage());
        }
    }

    public List<HistoricoPeso> consultarPorCliente(String cpfAluno) {
    List<HistoricoPeso> historicos = new ArrayList<>();
    String sql = "SELECT * FROM weighthist WHERE cpf = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, cpfAluno);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                HistoricoPeso historico = new HistoricoPeso();
                // Obtendo a data como java.sql.Date
                Date dataSql = rs.getDate("data");
                // Convertendo java.sql.Date para LocalDate
                LocalDate dataLocalDate = dataSql.toLocalDate();
                historico.setData(dataLocalDate);
                historico.setPeso(rs.getDouble("peso"));
                historico.setCpfAluno(rs.getString("cpf"));
                historicos.add(historico);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao consultar históricos de peso por cliente: " + e.getMessage());
    }
    return historicos;
}

    public void salvarPesoHistorico(String cpf, float peso,float altura, LocalDate data) {
        String sql = "INSERT INTO weighthist (cpf, peso, altura, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vendas", "root", "guitarhero");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setFloat(2, peso);
            stmt.setFloat(3, altura);
            stmt.setDate(4, java.sql.Date.valueOf(data));
            

            stmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar histórico de peso: " + e.getMessage());
        }
    }
}
