package Mateus.Integração;

import daos.ProdutoDAO;
import entity.Produto;
import org.junit.Assert;
import org.junit.Test;

public class RF03 {
    //RT03
    @Test
    public void deveEditarProduto(){
        //Arrange
        ProdutoDAO prodDao = new ProdutoDAO();
        Produto produto01;
        Produto produtoUpd;
        String nome = "Pão de Forma";

        //Act
        produto01 = Produto.createProduto(nome, 6.5, 20, "Doce");
        produtoUpd = Produto.createProduto("PÃO FRANCÊS", 5.99, 40, "Salgado");
        prodDao.salvar(produto01);
        prodDao.update(nome.toUpperCase(), produtoUpd);

        //Assert
        Assert.assertEquals("PÃO FRANCÊS", prodDao.consultarPeloNome("Pão francês").getNome());
    }


}
