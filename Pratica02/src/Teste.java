/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Data dataNasc = new Data(5, 8, 2000);
        Aluno primeiro = new Aluno(1, "Thiago", 'M', "123456789", "12345678910", dataNasc); 
        
        primeiro.imprimir();
    }
    
}
