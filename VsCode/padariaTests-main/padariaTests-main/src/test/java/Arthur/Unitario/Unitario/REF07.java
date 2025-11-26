package Arthur.Unitario.Unitario;

import static org.junit.jupiter.api.Assertions.*;

import entity.Cliente;

import entity.Produto;
import entity.Venda;
import mock.VendaMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class REF07 {

    private Venda vendaTeste;
    private VendaMock mock;
    private ArrayList<Produto> produtos;

    @BeforeEach

    public void iniciarVenda() {
        mock = new VendaMock();
        produtos = new ArrayList<>();
        vendaTeste = Venda.createVenda(
                "11732755922",
                "Pix",
                200,
                produtos
        );
    }

    @Test

    // REF07 - ROT01- CASO01 - Confirmar a geração dos pontos

    public void testCS01() {
        Cliente clienteTeste = Cliente.createUser("Andrei", "11122233344", "48999999999", 0);

        //tring mtdPag, double valorVenda, ArrayList<Produto>produtos)
        int ponto = vendaTeste.calculaPontos(vendaTeste.getValorVenda());
        clienteTeste.adicionaPontos(ponto);


        //Pela regra de negócio, a cada 10 reais de compra = 1 ponto, valor esperado é de 20 pontos, já que o valor é 200.

        assertEquals(20, clienteTeste.getPontos(), "Pontos gerados e atribuídos com sucesso!");
    }
    @Test

        public void testCS02() {
            ArrayList<Produto>produtos = new ArrayList<>();
            String cpfInvalido = "1112223334444";
            Cliente clienteTeste = Cliente.createUser("Andrei", cpfInvalido, "48999999999", 0);

            Venda vendaTeste2 = Venda.createVenda(cpfInvalido,"Pix", 200, produtos);

            assertNotNull(vendaTeste2, " Erro no teste");
        }


    @Test

    // REF07 - ROT01- CASO03 - Validar valor de venda inválido


    public void testCS03(){
        double valorInvalido = -500;
        vendaTeste = Venda.createVenda("11732755922","Pix",valorInvalido,produtos);

        assertNotNull(vendaTeste, " Erro no teste");
    }

}
