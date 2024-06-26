/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Usuario
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HistPeso {
    private LocalDate data;
    private int peso;
    private String cpf;
    private String nome;
    private int altura;
    
    

    public HistPeso(LocalDate data, int peso, String cpf, String nome, int altura) {
        this.data = data;
        this.peso = peso;
        this.cpf = cpf;
        this.nome = nome;
        this.altura = altura;
    }

    // Getters e Setters
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public int getPeso() { return peso; }
    public void setPeso(int peso) { this.peso = peso; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getAltura() { return altura; }
    public void setAltura(int altura) { this.altura = altura; }

    // Método para calcular o IMC
    public static double calcularIMC(int peso, int altura) {
        double alturaMetros = altura / 100.0;
        return peso / (alturaMetros * alturaMetros);
    }

    // Método para interpretar o IMC
    public static String interpretarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 24.9) {
            return "Peso normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }

    // Método para gravar os dados do IMC em um arquivo texto
    public static void gravarIMCEmArquivo(HistPeso histPeso) {
        double imc = calcularIMC(histPeso.getPeso(), histPeso.getAltura());
        String interpretacao = interpretarIMC(imc);
        String dataCalculo = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String linha = String.format("Data: %s, CPF: %s, Nome: %s, IMC: %.2f, Interpretação: %s%n",
                dataCalculo, histPeso.getCpf(), histPeso.getNome(), imc, interpretacao);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("imc_resultados.txt", true))) {
            writer.write(linha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


