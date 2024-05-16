/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prat5_heranca;

/**
 *
 * @author Usuario
 */
public class Funcionario extends Pessoa{
    private Double salario;

public Funcionario(String RG, String nome, Double salario)
{
    super (RG, nome);
    this.salario=salario;
}

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
    
    public double aumentar_Salario(){
        double aux;
        if (salario>1000.00)
            aux=0.10*salario;
        else aux = 1*salario;
        return aux;
    }

}


