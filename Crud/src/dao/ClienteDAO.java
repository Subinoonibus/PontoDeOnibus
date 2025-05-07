/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Cliente;
import factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */

public class ClienteDAO {

    public void inserir(Cliente cliente) throws Exception {
        Connection conn = ConnectionFactory.get();
        String sql = "INSERT INTO clientes (nome, cpf, email, telefone, endereco, data_nascimento) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, cliente.getTelefone());
        stmt.setString(5, cliente.getEndereco());
        stmt.setDate(6, cliente.getDataNascimento());
        stmt.execute();
        stmt.close();
        conn.close();
    }

    public List<Cliente> listar() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.conectar();
        String sql = "SELECT * FROM clientes";
        Statement stmt = conn.createStatement();
        ResultSet  = stmt.executeQuery(sql);
        while (rs.next()) {
            Cliente c = new Cliente();
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setEmail(rs.getString("email"));
            c.setTelefone(rs.getString("telefone"));
            c.setEndereco(rs.getString("endereco"));
            c.setDataNascimento(rs.getDate("data_nascimento"));
            lista.add(c);
        }
        rs.close();
        stmt.close();
        conn.close();
        return lista;
    }

    public void atualizar(Cliente cliente) throws Exception {
        Connection conn = ConnectionFactory.conectar();
        String sql = "UPDATE clientes SET nome=?, email=?, telefone=?, endereco=?, data_nascimento=? WHERE cpf=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getEmail());
        stmt.setString(3, cliente.getTelefone());
        stmt.setString(4, cliente.getEndereco());
        stmt.setDate(5, cliente.getDataNascimento());
        stmt.setString(6, cliente.getCpf()); // cpf é usado como identificador
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void excluirPorCpf(String cpf) throws Exception {
        Connection conn = ConnectionFactory.conectar();
        String sql = "DELETE FROM clientes WHERE cpf=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.execute();
        stmt.close();
        conn.close();
    }
}
