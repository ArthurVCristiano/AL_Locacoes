package daos;

import connection.Conexao;
import entity.Produto;
import interfaces.ProdutoDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static entity.Produto.createProduto;

public class ProdutoDAO implements ProdutoDaoInterface {

    @Override
    public void salvar(Produto obj) {
        Connection conexao = null;

        for (Produto umProd : consultar()){
            if(umProd.getNome().equals(obj.getNome())){
                throw new RuntimeException("Produto já cadastrado!");
            }
        }

        try{
            conexao = Conexao.getConnection();
            String sql = "INSERT INTO produtos (nome,preco,quantidade,tipo) VALUES(?,?,?,?)";

                PreparedStatement smt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                smt.setString(1, obj.getNome().toUpperCase());
                smt.setDouble(2, obj.getPreco());
                smt.setInt(3, obj.getQuantidade());
                smt.setString(4, obj.getTipo());

                smt.execute();


        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar salvar o cliente: " + ex.getMessage());
        }
    }

    @Override
    public Produto update(String nome, Produto prod) {
        Connection con = null;
        PreparedStatement smt = null;

        String sql = "UPDATE produtos SET nome = ?, preco = ?, tipo = ?, quantidade = ? WHERE nome = ?" ;
        try {
            con = Conexao.getConnection();
            smt = con.prepareStatement(sql);

            smt.setString(1, prod.getNome());
            smt.setDouble(2,prod.getPreco());
            smt.setString(3,prod.getTipo());
            smt.setInt(4, prod.getQuantidade());
            smt.setString(5, nome);

            smt.executeUpdate();
        }

        catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar atualizar o cliente: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao();
        }
        return prod;
    }

    @Override
    public List<Produto> consultar() {
        List<Produto> produtos = new ArrayList<>();
        Connection con = null;

        try{
            con = Conexao.getConnection();
            PreparedStatement smt = con.prepareStatement("SELECT * FROM produtos");

            ResultSet rs = smt.executeQuery();


            while(rs.next()){
                Produto prod = createProduto(rs.getString("nome").toUpperCase(),rs.getDouble("preco"),rs.getInt("quantidade"),rs.getString("tipo"));
                produtos.add(prod);
            }
            for(Produto pro : produtos){
                System.out.println(pro.toString());
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
        finally{
            Conexao.fecharConexao();
        }
        return produtos;
    }

    @Override
    public boolean delete(String nome) {
        Connection con = null;
        PreparedStatement smt = null;

        String sql = "DELETE FROM produtos WHERE nome = ? ";
        try{
            con = Conexao.getConnection();

            smt = con.prepareStatement(sql);
            smt.setString(1, nome.toUpperCase());

            int linhasAfetadas = smt.executeUpdate();

            return linhasAfetadas > 0;

        }catch(SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
        finally{
            Conexao.fecharConexao();
        }
    }

    @Override
    public Produto consultarPeloNome(String nome) {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        Produto prod = new Produto();
        try{

            Connection con = Conexao.getConnection();
            PreparedStatement smt = con.prepareStatement(sql);

            smt.setString(1,nome.toUpperCase());
            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                Produto produto = createProduto(
                        rs.getString("nome").toUpperCase(),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getString("tipo")
                );
                prod = produto;
            }



        }catch(SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
        finally{
            Conexao.fecharConexao();
        }
        return prod;

    }
}
