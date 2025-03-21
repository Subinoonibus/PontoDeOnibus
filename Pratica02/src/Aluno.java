/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Aluno {
    
    private int ra;
    private String nome;
    private char sexo;
    private String rg;
    private String cpf;
    private Data datanasc;

    public Data getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Data datanasc) {
        this.datanasc = datanasc;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Aluno(int ra, String nome, char sexo, String rg, String cpf, Data datanasc) {
        this.ra = ra;
        this.nome = nome;
        this.sexo = sexo;
        this.rg = rg;
        this.cpf = cpf;
        this.datanasc = datanasc;
    }
    
    public void imprimir() {
        System.out.println("RA: " + ra);
        System.out.println("Nome: " + nome);
        System.out.println("Sexo: " + sexo);
        System.out.println("RG: " + rg);
        System.out.println("CPF: " + cpf);
        System.out.println("Data de Nascimento: " + datanasc.formatarData());
    }

    
    
    
    
}
