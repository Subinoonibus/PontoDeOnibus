/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

/**
 *
 * @author Usuario
 */
import modelo.Aluno;
import java.util.List;

public interface AlunoDAO {
    void inserir(Aluno aluno);
    void excluir(String cpf);
    void atualizar(Aluno aluno);
    Aluno consultar(String cpf);
    List<Aluno> listarTodos();
}

