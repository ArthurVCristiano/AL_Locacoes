package Arthur.Unitario.Unitario;

import static org.junit.jupiter.api.Assertions.*;

import entity.Produto;
import exceptions.ProdutoInvalidoException;
import mock.ProdutoMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class REF03 {


//ARTHUR VELHO CRISTIANO - REF 02 - RT02 CASO 05


    private Produto produtoTeste;
    private ProdutoMock mock;

    @BeforeEach

    public void iniciarProduto() {
        mock = new ProdutoMock();
        produtoTeste = Produto.createProduto(
                "Pão de Forma",
                6.50,
                20,
                "Pão");

        mock.salvar(produtoTeste);
    }

    @Test

    // ROTEIRO 02 - CASO 05 - Deve alterar informação do tipo de um produto

    public void testCS05() {
        String nome = this.produtoTeste.getNome();
        double preco = this.produtoTeste.getPreco();
        int quantidade = this.produtoTeste.getQuantidade();
        String tipoNovo = "Salgado";


        Produto produtoTesteNovo = null;

        try {
            produtoTesteNovo = Produto.createProduto(nome, preco, quantidade, tipoNovo);
        } catch (ProdutoInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Produto produtoRetornado = this.mock.update(nome, produtoTesteNovo);

        assertNotNull(produtoRetornado);
        assertEquals(tipoNovo, produtoRetornado.getTipo(), "Tipo não foi atleradol");

        Produto produtoNoMock = mock.consultarPeloNome(nome);
        assertNotNull(produtoNoMock, "O produto atualizado não foi encontrado.");
    }

    @Test

    // ROTEIRO 02 - CASO 06 - Invalidar alteração de nome não válido

    public void testCS06() {
        String nomeNovo = "";
        double preco = this.produtoTeste.getPreco();
        int quantidade = this.produtoTeste.getQuantidade();
        String tipo = this.produtoTeste.getTipo();


        Produto produtoTesteNovo = null;

        try {
            produtoTesteNovo = Produto.createProduto(nomeNovo, preco, quantidade, tipo);
        } catch (ProdutoInvalidoException e) {
            fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
        }

        Produto produtoRetornado = this.mock.update(nomeNovo, produtoTesteNovo);

        assertNotNull(produtoRetornado);
        assertEquals(nomeNovo, produtoRetornado.getNome(), "Nome não foi atleradol");

        Produto produtoNoMock = mock.consultarPeloNome(nomeNovo);
        assertNotNull(produtoNoMock, "O produto atualizado não foi encontrado.");
    }

        @Test

        // ROTEIRO 02 - CASO 07 - Invalidar alteração de valor não válido

        public void testCS07(){
            String nome = this.produtoTeste.getNome();
            double precoInvalido = -5.99;
            int quantidade = this.produtoTeste.getQuantidade();
            String tipo = this.produtoTeste.getTipo();


            Produto produtoTesteNovo = null;

            try {
                produtoTesteNovo = Produto.createProduto(nome, precoInvalido, quantidade, tipo);
            } catch (ProdutoInvalidoException e) {
                fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
            }

            Produto produtoRetornado = this.mock.update(nome, produtoTesteNovo);

            assertNotNull(produtoRetornado);
            assertEquals(precoInvalido, produtoRetornado.getPreco(), "Preço não foi atleradol");

            Produto produtoNoMock = mock.consultarPeloNome(nome);
            assertNotNull(produtoNoMock, "O produto atualizado não foi encontrado.");
        }

        @Test

        // ROTEIRO 02 - CASO 08 - Invalidar alteração de quantidade não válida

        public void testCS08(){
            String nome = this.produtoTeste.getNome();
            double preco = this.produtoTeste.getPreco();
            int quantidadeInvalido = -5;
            String tipo = this.produtoTeste.getTipo();


            Produto produtoTesteNovo = null;

            try {
                produtoTesteNovo = Produto.createProduto(nome, preco, quantidadeInvalido, tipo);
            } catch (ProdutoInvalidoException e) {
                fail("Não deveria lançar exceção ao criar cliente atualizado: " + e.getMessage());
            }

            Produto produtoRetornado = this.mock.update(nome, produtoTesteNovo);

            assertNotNull(produtoRetornado);
            assertEquals(quantidadeInvalido, produtoRetornado.getQuantidade(), "Quantidade não foi atleradol");

            Produto produtoNoMock = mock.consultarPeloNome(nome);
            assertNotNull(produtoNoMock, "O produto atualizado não foi encontrado.");
        }
    }
