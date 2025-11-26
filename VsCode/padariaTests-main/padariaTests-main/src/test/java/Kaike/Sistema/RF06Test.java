/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kaike.Sistema;

import daos.ClienteDao;
import daos.ProdutoDAO;
import daos.VendaDao;
import entity.Cliente;
import entity.Produto;
import entity.Venda;
import exceptions.VendaInvalidaException;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author user
 */
public class RF06Test {
    
    @Test
    public void deveValidarResgateViaPontos(){
        
        //arrange
        Cliente cliente = Cliente.createUser("Kaike", "11831869918", "47992042889", 10);
        Produto produto = Produto.createProduto("Pão francês", 4.99, 10, "Salgado");
    
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        produtos.add(produto);
        
        ClienteDao cliDAO = new ClienteDao();
        ProdutoDAO prodDAO = new ProdutoDAO();
        VendaDao venDAO = new VendaDao();
        
        cliDAO.salvar(cliente);
        prodDAO.salvar(produto);
        
        //act
        Venda venda = Venda.createVendaResgatada(cliente, "11831869918", 9.98, produtos);
        venDAO.salvar(venda);
        
        //assert

        Assert.assertEquals("Pontos", venDAO.consultar().get(0).getMtdPag());
    }
    
    @Test
    public void deveValidarResgateInvalido(){
        //arrange
        Cliente cliente2 = Cliente.createUser("Pedro", "11831862468", "47992042889", 0);
        Produto produto = Produto.createProduto("Pão francês", 4.99, 10, "Salgado");
    
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos.add(produto);
        produtos.add(produto);
        
        ClienteDao cliDAO = new ClienteDao();
        ProdutoDAO prodDAO = new ProdutoDAO();
        
        cliDAO.salvar(cliente2);
        prodDAO.salvar(produto);
        
        //act e assert
        Assert.assertNull(Venda.createVendaResgatada(cliente2, "11831869918", 9.98, produtos));
    }
}
