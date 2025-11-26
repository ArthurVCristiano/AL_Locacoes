package Mateus.Unitario;

import entity.Cliente;
import entity.Produto;
import entity.Venda;
import mock.ClienteMock;
import mock.ProdutoMock;
import mock.VendaMock;
import org.junit.Test;

import java.util.ArrayList;

public class testedotestebagual {
    @Test
    public void teste01(){
        // Arrange
        Cliente cli01 = Cliente.createUser("Mateus", "12312312312", "54984310238", 0);
        Cliente cli02 = Cliente.createUser("Kaike", "32132132132", "47999999999", 0);
        Cliente cli03 = Cliente.createUser("Andrei", "45645645645", "47999999999", 3);
        Cliente cli04 = Cliente.createUser("Arthur", "65465465465", "47999999999", 100);
        Cliente cliUpd = Cliente.createUser("Andrei", "45645645645", "47999999999", 3000);

        //Act
        ClienteMock cliDao = new ClienteMock();
        cliDao.salvar(cli01);
        cliDao.salvar(cli02);
        cliDao.salvar(cli03);
        cliDao.salvar(cli04);

        cliDao.update(cliUpd, "45645645645");

        //Assert

        for (Cliente umCli : cliDao.consultar()){
            System.out.printf(umCli.getNome() +  ", " + umCli.getPontos() + "\n");
        }

        System.out.printf(cliDao.consultarPeloCpf("65465465465").toString());
    }

    @Test
    public void teste02(){
        //Arrange
        Produto prod01 = Produto.createProduto("Pão de queijo", 8.00, 10, "Salgado");
        Produto prod02 = Produto.createProduto("Bolo de cenoura", 20.5, 7, "Doce");
        Produto prod03 = Produto.createProduto("Pastel de Frango", 7, 15, "Salgado");
        Produto prodUpd = Produto.createProduto("Pão de queijo", 10, 4, "Salgado");

        //Act
        ProdutoMock prodDao = new ProdutoMock();
        prodDao.salvar(prod01);
        prodDao.salvar(prod02);
        prodDao.salvar(prod03);

        prodDao.delete(prod03.getNome());
        prodDao.update(prodUpd.getNome(), prodUpd);

        //Arrange

        for(Produto umProd : prodDao.consultar()){
            System.out.printf(umProd.toString() + "\n");
        }

        System.out.printf(prodDao.consultarPeloNome("Bolo de cenoura").toString());
    }

    @Test
    public void teste03(){
        //Arrange
        Cliente cliente01 = Cliente.createUser("Mateus", "12312312312", "54999999999", 0);
        Produto produto01 = Produto.createProduto("Pão de queijo", 5, 100, "Salgado");
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(produto01);
        Venda venda01 = Venda.createVenda(cliente01.getCpf(), "Dinheiro", 10, produtos);
        Venda venda02 = Venda.createVenda(cliente01.getCpf(), "Pix", 10, produtos);
        Venda vendaUpd = Venda.createVenda(cliente01.getCpf(), "Débito", 10, produtos);

        VendaMock venDao = new VendaMock();

        //Act
        venDao.salvar(venda01);
        venDao.salvar(venda02);

        venDao.update(vendaUpd, 1);
        venDao.delete(0);

        //Arrange
        for(Venda umaVen : venDao.consultar()){
            System.out.printf(umaVen.toString() + "\n");
        }
    }
}
