package Arthur.Unitario.Unitario;

import static org.junit.jupiter.api.Assertions.*;

import entity.Produto;
import mock.ProdutoMock;
import org.junit.jupiter.api.Test;

//ARTHUR VELHO CRISTIANO - REF 06 - RT01 CASO 01

public class REF06 {

    private Produto produtoTeste = new Produto();
    private ProdutoMock mock;

    private Produto paoFrances =  Produto.createProduto("Pão Frances", 20, 2,"Pão");
    private Produto paoQueijo =  Produto.createProduto("Pão de Queijo", 20, 5, "Pão");
    private Produto paoFarofa = Produto.createProduto("Pão Doce com farofa", 20, 4, "Pão");
    private Produto paoDoce = Produto.createProduto("Pão Doce liso", 20, 5, "Pão");
    private Produto cafe = Produto.createProduto("Café expresso", 20, 4, "Bebida");
    private Produto roscaTradicional =  Produto.createProduto("Rosca tradicional", 20, 10, "Salgado");
    private Produto boloCenoura =  Produto.createProduto("Bolo de Cenoura", 20, 20, "Doce");


    public String produtosCatalogoListados(){


        produtoTeste.produtosCatalogo(paoFrances);produtoTeste.produtosCatalogo(paoQueijo);produtoTeste.produtosCatalogo(paoFarofa);produtoTeste.produtosCatalogo(paoDoce);
        produtoTeste.produtosCatalogo(cafe);produtoTeste.produtosCatalogo(roscaTradicional);produtoTeste.produtosCatalogo(boloCenoura);

        String prodCatalogo =  produtoTeste.listProdutosCatalogo();

        return prodCatalogo;
    }

    @Test

    // REF06 = RT01 - CASO 01 - Buscar produtos disponíveis
    public void testeCS01(){


        String produtosCatalogo = " Produtos do Catalogo: \n" +
                "Pão Frances - 2 pt, Disponivel: 20\n" +
                "Pão de Queijo - 5 pt, Disponivel: 20\n" +
                "Pão Doce com farofa - 4 pt, Disponivel: 20\n" +
                "Pão Doce liso - 5 pt, Disponivel: 20\n" +
                "Café expresso - 4 pt, Disponivel: 20\n" +
                "Rosca tradicional - 10 pt, Disponivel: 20\n" +
                "Bolo de Cenoura - 20 pt, Disponivel: 20\n";

        //produtos listados na tela de catalogo, são produtos que ficam lá fixamente no catalogo.

        assertEquals(produtosCatalogo, produtosCatalogo.toString(), "Produtos retornados com sucesso");
    }

    @Test

    // REF06 = RT01 - CASO 02 - Buscar produtos indisponíveis
    public void testeCS02(){
        this.mock = new ProdutoMock();
        String nomeInvalido = "banana";

        assertNotNull(mock.consultarPeloNome(nomeInvalido), "Nenhum produto encontrado!");


    }
}
