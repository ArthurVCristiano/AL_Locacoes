package entity;

import exceptions.ClienteInvalidoException;

public class Cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private int pontos;

    private Cliente(String nome, String cpf, String telefone, int pontos){
        this.nome = nome.toUpperCase();
        this.cpf = cpf;
        this.telefone = telefone;
        this.pontos = pontos;
    }

    public Cliente() {}

    public static Cliente createUser(String nome, String cpf, String telefone, int pontos )throws ClienteInvalidoException{
        Cliente cliente = null;
        try {
            if(nome.isBlank()){
                throw new ClienteInvalidoException("Há campos obrigatórios não preenchidos!");
            }

            if( cpf.length() != 11){
                throw new ClienteInvalidoException("CPF digitado é inválido, favor validar!");
            }

            if(telefone.length() != 11){
                throw new ClienteInvalidoException("Número digitado é inválido, favor validar!");
            }

            if(pontos < 0){
              throw new ClienteInvalidoException("Cliente não possui nenhum ponto salvo");

            }
            cliente = new Cliente(nome, cpf, telefone, pontos);
        } catch (ClienteInvalidoException e) {
            System.out.printf("Erro: " + e.getMessage());
        }

        return cliente;
    }

    public void adicionaPontos(int pontos){
        this.pontos += pontos;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
    public int getPontos() {
        return pontos;
    }
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", pontos=" + pontos +
                '}';
    }
}
