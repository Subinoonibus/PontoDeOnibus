/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

/**
 *
 * @author Usuario
 */
import Dao.AlunoDAO;
import Modelo.Aluno;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlunoGUI extends JFrame {
    private JTextField cpfField;
    private JTextField nomeField;
    private JTextField dataNascField;
    private JTextField pesoField;
    private JTextField alturaField;
    private JTextArea outputArea;
    private JButton inserirButton;
    private JButton excluirButton;
    private JButton atualizarButton;
    private JButton consultarButton;

    private AlunoDAO alunoDAO;

    public AlunoGUI() {
        setTitle("AlunoGUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(8, 2));

        mainPanel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        mainPanel.add(cpfField);

        mainPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        mainPanel.add(nomeField);

        mainPanel.add(new JLabel("Data Nascimento (DD/MM/YYYY):"));
        dataNascField = new JTextField();
        mainPanel.add(dataNascField);

        mainPanel.add(new JLabel("Peso:"));
        pesoField = new JTextField();
        mainPanel.add(pesoField);

        mainPanel.add(new JLabel("Altura:"));
        alturaField = new JTextField();
        mainPanel.add(alturaField);

        inserirButton = new JButton("Inserir");
        mainPanel.add(inserirButton);

        excluirButton = new JButton("Excluir");
        mainPanel.add(excluirButton);

        atualizarButton = new JButton("Atualizar");
        mainPanel.add(atualizarButton);

        consultarButton = new JButton("Consultar");
        mainPanel.add(consultarButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane);

        add(mainPanel);

        try {
            alunoDAO = new AlunoDAO();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage());
        }

        inserirButton.addActionListener((ActionEvent e) -> {
            if (cpfField.getText().isEmpty() || nomeField.getText().isEmpty() ||
                    dataNascField.getText().isEmpty() || pesoField.getText().isEmpty() || 
                    alturaField.getText().isEmpty()) {
                
                outputArea.setText("Por favor, preencha todos os campos.");
                return;
            }
            
            try {
                String dataNasc = convertDateToMySQLFormat(dataNascField.getText());
                Aluno aluno = new Aluno(
                        cpfField.getText(),
                        nomeField.getText(),
                        dataNasc,
                        Integer.parseInt(pesoField.getText()),
                        Integer.parseInt(alturaField.getText())
                );
                alunoDAO.inserir(aluno);
                outputArea.setText("Aluno inserido com sucesso!");
            } catch (SQLException ex) {
                outputArea.setText("Erro ao inserir aluno: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                outputArea.setText("Erro: Peso e altura devem ser números inteiros.");
            } catch (ParseException ex) {
                outputArea.setText("Erro: Formato de data inválido. Use DD/MM/YYYY.");
            }
        });

        excluirButton.addActionListener((ActionEvent e) -> {
            if (cpfField.getText().isEmpty()) {
                outputArea.setText("Por favor, preencha o campo CPF.");
                return;
            }
            
            try {
                alunoDAO.excluir(cpfField.getText());
                outputArea.setText("Aluno excluído com sucesso!");
            } catch (SQLException ex) {
                outputArea.setText("Erro ao excluir aluno: " + ex.getMessage());
            }
        });

        atualizarButton.addActionListener((ActionEvent e) -> {
            if (cpfField.getText().isEmpty() || nomeField.getText().isEmpty() ||
                    dataNascField.getText().isEmpty() || pesoField.getText().isEmpty() || 
                    alturaField.getText().isEmpty()) {
                
                outputArea.setText("Por favor, preencha todos os campos.");
                return;
            }
            
            try {
                String dataNasc = convertDateToMySQLFormat(dataNascField.getText());
                Aluno aluno = new Aluno(
                        cpfField.getText(),
                        nomeField.getText(),
                        dataNasc,
                        Integer.parseInt(pesoField.getText()),
                        Integer.parseInt(alturaField.getText())
                );
                alunoDAO.atualizar(aluno);
                outputArea.setText("Aluno atualizado com sucesso!");
            } catch (SQLException ex) {
                outputArea.setText("Erro ao atualizar aluno: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                outputArea.setText("Erro: Peso e altura devem ser números inteiros.");
            } catch (ParseException ex) {
                outputArea.setText("Erro: Formato de data inválido. Use DD/MM/YYYY.");
            }
        });

        consultarButton.addActionListener((ActionEvent e) -> {
            if (cpfField.getText().isEmpty()) {
                outputArea.setText("Por favor, preencha o campo CPF.");
                return;
            }
            
            try {
                Aluno aluno = alunoDAO.consultar(cpfField.getText());
                if (aluno != null) {
                    outputArea.setText("CPF: " + aluno.getCpf() + "\n" +
                            "Nome: " + aluno.getNome() + "\n" +
                                    "Data de Nascimento: " + convertDateToUserFormat(aluno.getDataNasc()) + "\n" +
                                            "Peso: " + aluno.getPeso() + "\n" +
                                                    "Altura: " + aluno.getAltura());
                } else {
                    outputArea.setText("Aluno não encontrado!");
                }
            } catch (SQLException ex) {
                outputArea.setText("Erro ao consultar aluno: " + ex.getMessage());
            } catch (ParseException ex) {
                outputArea.setText("Erro ao formatar a data de nascimento.");
            }
        });
    }

    private String convertDateToMySQLFormat(String date) throws ParseException {
        SimpleDateFormat userFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = userFormat.parse(date);
        return mysqlFormat.format(parsedDate);
    }

    private String convertDateToUserFormat(String date) throws ParseException {
        SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat userFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = mysqlFormat.parse(date);
        return userFormat.format(parsedDate);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AlunoGUI().setVisible(true);
        });
    }
}


