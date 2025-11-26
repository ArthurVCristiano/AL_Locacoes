import daos.ProdutoDAO;
import daos.VendaDao;
import entity.Produto;
import entity.Venda;

import java.util.ArrayList;

import static entity.Produto.createProduto;
import static entity.Venda.createVenda;

public class Main {
    public static void main(String[] args) {
        Produto produto1 = createProduto("Pepsi",10.0,15,"Bebida");
        Produto produto2 = createProduto("Arthur",599.99,1,"Arthur");

        VendaDao vendaDao = new VendaDao();
        ProdutoDAO prodDao = new ProdutoDAO();
        ArrayList<Produto> produtos = new ArrayList<>();

        produtos.add(produto1);
        produtos.add(produto2);

        //prodDao.salvar(produto1);
        //prodDao.salvar(produto2);

        Venda venda = createVenda("13147889966", "Dinheiro",produto2.getPreco(),produtos);

        //vendaDao.salvar(venda);

        System.out.println(vendaDao.consultarPeloId(1).getValorVenda());
    }

}
