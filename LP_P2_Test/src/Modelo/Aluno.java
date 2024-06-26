/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Aluno {
    private String cpf;
    private String nome;
    private String dataNasc;
    private int peso;
    private int altura;

    public Aluno(String cpf, String nome, String dataNasc, int peso, int altura) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.peso = peso;
        this.altura = altura;
    }

    public Aluno() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters e Setters
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDataNasc() { return dataNasc; }
    public void setDataNasc(String dataNasc) { this.dataNasc = dataNasc; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public int getAltura() { return altura; }
    public void setAltura(int altura) { this.altura = altura; }
}


