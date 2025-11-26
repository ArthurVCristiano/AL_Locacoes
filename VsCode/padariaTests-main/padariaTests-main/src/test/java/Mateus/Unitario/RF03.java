package Mateus.Unitario;

import entity.Produto;
import org.junit.Assert;
import org.junit.Test;

public class RF03 {
    //RT01
    @Test
    public void criarProdutoValidoCT04(){
        //Esse teste tem como objetivo criar, com sucesso
        //um produto válido, caso o teste der certo ele
        //deve criar um novo produto, com todos os seus
        //valores atribuidos corretamente

        // Arrange
        Produto produtoValido;
        String nome = "Pão de Forma";
        double preco = 6.5;
        int quantidade = 20;
        String tipo = "Salgado";
        // Act
        produtoValido = Produto.createProduto(nome, preco, quantidade, tipo);
        // Assert
        Assert.assertNotNull(produtoValido);
        Assert.assertEquals("Pão de Forma", produtoValido.getNome());
        Assert.assertEquals(0, produtoValido.getPreco(), 6.5);
        Assert.assertEquals(20, produtoValido.getQuantidade());
        Assert.assertEquals("Salgado", produtoValido.getTipo());
    }

    @Test
    public void validarProdutoInvalidoValorCT01(){
        //Esse teste tem como objetivo validar a
        //tentativa de criação de um produto inválido, cujo
        //esse possui um valor inválido, caso o teste der certo
        //ele deve retornar um erro de valor inválido
        // e retorna o novo produto como nulo

        //Arrange
        Produto prodPrecoInvalido;
        String nome = "Pão de Forma";
        double preco = -6.5;
        int quantidade = 20;
        String tipo = "Salgado";
        //Act
        prodPrecoInvalido = Produto.createProduto(nome, preco, quantidade, tipo);
        //Assert
        Assert.assertNull(prodPrecoInvalido);
    }

    @Test
    public void validarProdutoInvalidoQuantidadeCT02(){
        //Esse teste tem como objetivo validar a tentativa de
        //criação de um produto inválido, cujo esse possui uma
        //quantidade inválida, caso o teste der certo ele deve
        //retornar um erro de quantidade inválida e retorna o
        //novo produto como nulo

        //Arrange
        Produto prodQuantInvalido;
        String nome = "Pão de Forma";
        double preco = 6.5;
        int quantidade = -3;
        String tipo = "Salgado";
        //Act
        prodQuantInvalido = Produto.createProduto(nome, preco, quantidade, tipo);
        //Assert
        Assert.assertNull(prodQuantInvalido);
    }

    @Test
    public void validarProdutoInvalidoTipoCT03(){
        //Esse teste tem como objetivo validar a tentativa de
        //criação de um produto inválido, cujo esse possui um
        //tipo inválido, caso o teste der certo ele deve
        //retornar um erro de tipo inválido e retorna o
        //novo produto como nulo

        //Arrange
        Produto prodQuantInvalido;
        String nome = "Pão de Forma";
        double preco = 6.5;
        int quantidade = 20;
        String tipo = "Carro";
        //Act
        prodQuantInvalido = Produto.createProduto(nome, preco, quantidade, tipo);
        //Assert
        Assert.assertNull(prodQuantInvalido);
    }
}
