/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class HistoricoPeso {
    private String cpfAluno;
    private String data;
    private double peso;

    // Construtor padrão
    public HistoricoPeso() {
    }

    // Construtor com argumentos
    public HistoricoPeso(String cpfAluno, String data, double peso) {
        this.cpfAluno = cpfAluno;
        this.data = data;
        this.peso = peso;
    }

    // Getters e Setters
    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}


