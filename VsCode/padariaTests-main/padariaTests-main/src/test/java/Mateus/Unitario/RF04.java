package Mateus.Unitario;

import entity.Cliente;
import entity.Produto;
import entity.Venda;
import mock.VendaMock;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RF04 {
    //RT03

    @Test
    public void deveDeletarVenda(){
        //Este teste tem como objetivo validar a
        //exclusão de uma venda dentro de um banco
        //de dados, devido ao teste ser de caráter
        //unitário, será utilizado um mock como
        //substituto do banco, se o teste tiver sucesso
        //ele deve excluir com sucesso a venda da tabela
        //e retornar uma tabela vazia

        // Arrange
        ArrayList<Produto> produtos = new ArrayList<>();
        Produto prod01 = Produto.createProduto("Pão de queijo", 5, 20, "Salgado");
        VendaMock venDao = new VendaMock();
        produtos.add(prod01);
        Venda vendaValida = Venda.createVenda("12312312312", "Dinheiro", 10, produtos);
        venDao.salvar(vendaValida);

        // Act
        System.out.printf(venDao.consultarPeloId(0).toString());
        venDao.delete(0);

        // Assert
        Assert.assertEquals(new ArrayList<>(), venDao.consultar());
        Assert.assertNull(venDao.consultarPeloId(0));
    }

    @Test
    public void validarExclusãoVendaInvalida(){
        //Este teste tem como objetivo validar a
        //exclusão de uma venda dentro de um banco
        //de dados, devido ao teste ser de caráter
        //unitário, será utilizado um mock como
        //substituto do banco, se o teste tiver sucesso
        //ele não deve excluir a venda da tabela
        //e retornar uma tabela com elementos atribuidos

        // Arrange
        ArrayList<Produto> produtos = new ArrayList<>();
        Produto prod01 = Produto.createProduto("Pão de queijo", 5, 20, "Salgado");
        Venda vendaValida = Venda.createVenda("12312312312", "Dinheiro", 10, produtos);
        VendaMock venDao = new VendaMock();
        List<Venda> vendas;
        produtos.add(prod01);
        venDao.salvar(vendaValida);

        // Act
        System.out.printf(venDao.consultarPeloId(0).toString());
        vendas = venDao.consultar();
        venDao.delete(1);

        // Assert
        Assert.assertNotNull(venDao.consultarPeloId(0));
        Assert.assertEquals("12312312312", venDao.consultarPeloId(0).getCpfCliente());
        Assert.assertEquals(vendas, venDao.consultar());
    }
}
