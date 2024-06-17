/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

/**
 *
 * @author Usuario
 */
import modelo.HistoricoPeso;
import java.util.List;

public interface HistoricoPesoDAO {
    void inserir(HistoricoPeso historicoPeso);
    void atualizar(HistoricoPeso historicoPeso);
    void excluir(String cpfAluno, String data);
    List<HistoricoPeso> listarPorAluno(String cpfAluno);
    List<HistoricoPeso> listarTodos();
    HistoricoPeso consultar(String cpfAluno, String data);  // Adicionado este método
}



