package entity;

import exceptions.ProdutoInvalidoException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto {

    private String nome;
    private double preco;
    private int quantidade;
    private String tipo;
    private int quantidadeEstoque;
    private int qtdListadoProdutos;
    private ArrayList<Produto> produtosCatalogo;

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    private Produto(String nome, double preco,int quantidade,String tipo) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public Produto() {}

    public static Produto createProduto(String nome, double preco,int quantidade, String tipo){
        Produto produto = null;
        ArrayList<String> carac= new ArrayList<>();
        carac.add("Salgado");
        carac.add("Doce");
        carac.add("Bebida");
        carac.add("Pão");
        int cont = 0;

        try {
            if(nome.isBlank() ){
                throw new ProdutoInvalidoException("Há campos obrigatórios não preenchidos!");
            }
            if(preco <= 0){
                throw new ProdutoInvalidoException("Valor digitado é inválido, favor validar!");
            }
            if(quantidade < 0){
                throw new ProdutoInvalidoException("Quantidade digitada é inválida, favor validar!");
            }
            if (!carac.stream().anyMatch(t -> t.equalsIgnoreCase(tipo))) {
                throw new ProdutoInvalidoException("Tipo digitado é inválido, favor validar!");
            }
            produto = new Produto(nome,preco,quantidade,tipo);
        } catch (ProdutoInvalidoException e) {
            System.out.printf("Erro: " + e.getMessage());
        }

        return produto;
    }

    public void produtosCatalogo(Produto prod){
        if(prod.qtdListadoProdutos > 0) {
            this.produtosCatalogo.add(prod);
        }
    }

    public String listProdutosCatalogo(){
        String produtos = " Produtos do Catalogo: \n";
        for (Produto prod : this.produtosCatalogo){
            produtos += prod.toString() + "\n";
        }

        return produtos;
    }

    public double getPrecoPontos() {
        return (int) (this.getPreco() / 10.0);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", tipo:" + tipo +
                '}';
    }
}
