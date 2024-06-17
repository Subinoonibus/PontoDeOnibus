/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

/**
 *
 * @author Usuario
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.AlunoDAO;
import dao.AlunoDAOImpl;
import modelo.Aluno;

public class GUI extends JFrame {
    private final AlunoDAO alunoDAO;
    
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField dataNascimentoField;
    private JTextField pesoField;
    private JTextField alturaField;

    public GUI() {
        alunoDAO = new AlunoDAOImpl();
        
        setTitle("Gerenciamento de Alunos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        panel.add(cpfField);

        panel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        panel.add(nomeField);

        panel.add(new JLabel("Data de Nascimento:"));
        dataNascimentoField = new JTextField();
        panel.add(dataNascimentoField);

        panel.add(new JLabel("Peso:"));
        pesoField = new JTextField();
        panel.add(pesoField);

        panel.add(new JLabel("Altura:"));
        alturaField = new JTextField();
        panel.add(alturaField);

        JButton insertButton = new JButton("Inserir");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirAluno();
            }
        });
        panel.add(insertButton);

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAluno();
            }
        });
        panel.add(updateButton);

        JButton searchButton = new JButton("Consultar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarAluno();
            }
        });
        panel.add(searchButton);

        add(panel);
    }

    private void inserirAluno() {
        String cpf = cpfField.getText();
        String nome = nomeField.getText();
        String dataNascimento = dataNascimentoField.getText();
        double peso = Double.parseDouble(pesoField.getText());
        int altura = Integer.parseInt(alturaField.getText());

        Aluno aluno = new Aluno(cpf, nome, dataNascimento, peso, altura);
        alunoDAO.inserir(aluno);
        JOptionPane.showMessageDialog(this, "Aluno inserido com sucesso!");
    }

    private void atualizarAluno() {
        String cpf = cpfField.getText();
        String nome = nomeField.getText();
        String dataNascimento = dataNascimentoField.getText();
        double peso = Double.parseDouble(pesoField.getText());
        int altura = Integer.parseInt(alturaField.getText());

        Aluno aluno = new Aluno(cpf, nome, dataNascimento, peso, altura);
        alunoDAO.atualizar(aluno);
        JOptionPane.showMessageDialog(this, "Aluno atualizado com sucesso!");
    }

    private void consultarAluno() {
        String cpf = cpfField.getText();
        Aluno aluno = alunoDAO.consultar(cpf);
        
        if (aluno != null) {
            nomeField.setText(aluno.getNome());
            dataNascimentoField.setText(aluno.getDataNascimento());
            pesoField.setText(String.valueOf(aluno.getPeso()));
            alturaField.setText(String.valueOf(aluno.getAltura()));
        } else {
            JOptionPane.showMessageDialog(this, "Aluno não encontrado!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
}



