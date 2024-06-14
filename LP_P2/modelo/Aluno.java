package modelo;

/**
 *
 * @author 
 */
public class Aluno {
    private int cpf;
    private String nome;
    private String data_nasc;
    private int peso;
    private int altura;


    public int getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public int getPeso() {
        return peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData_nasc(String data_nasc) {
        this.ndata_nasc = data_nasc;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
