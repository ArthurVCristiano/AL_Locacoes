package daos;

import connection.Conexao;
import interfaces.ClienteDaoInterface;
import entity.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static entity.Cliente.createUser;

public class ClienteDao implements ClienteDaoInterface {

    @Override
    public void salvar(Cliente cliente){
        Connection con = null;
        try{
            con = Conexao.getConnection();

            String sql = "INSERT INTO clientes(nome,cpf,telefone,pontos)values(?,?,?,?)";

            PreparedStatement smt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = smt.getGeneratedKeys();

            smt.setString(1,cliente.getNome());
            smt.setString(2,cliente.getCpf());
            smt.setString(3,cliente.getTelefone());
            smt.setInt(4,cliente.getPontos());

            smt.execute();

            rs.next();
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            Conexao.fecharConexao();
        }
    }
    @Override
    public List<Cliente> consultar() {
        List<Cliente> clientesLista = new ArrayList<>();
        Connection con = null;

        try{
            con = Conexao.getConnection();
            PreparedStatement smt = con.prepareStatement("SELECT * FROM clientes");

            ResultSet rs = smt.executeQuery();

            while(rs.next()){
                Cliente cliente;
                cliente = createUser(rs.getString("nome"),rs.getString("cpf"),rs.getString("telefone"),rs.getInt("pontos"));
                clientesLista.add(cliente);
            }
            for(Cliente cli : clientesLista){
                System.out.println(cli.toString());
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
        finally{
            Conexao.fecharConexao();
        }
        return clientesLista;
    }

    @Override
    public boolean delete(String cpf) {

        Connection con = null;
        PreparedStatement smt = null;

        String sql = "DELETE FROM clientes WHERE cpf = ?";

        try{
            con = Conexao.getConnection();

            smt = con.prepareStatement(sql);
            smt.setString(1, cpf);

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
    public Cliente update(Cliente cli, String cpf) {
        Connection con = null;
        PreparedStatement smt = null;

        String sql = "UPDATE clientes SET nome = ?, cpf = ?, telefone = ?, pontos =  ? WHERE cpf = ?" ;

        try {

            con = Conexao.getConnection();
            smt = con.prepareStatement(sql);

            smt.setString(1, cli.getNome());
            smt.setString(2, cli.getCpf());
            smt.setString(3, cli.getTelefone());
            smt.setInt(4, cli.getPontos());
            smt.setString(5, cpf);

            smt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar atualizar o cliente: " + ex.getMessage());
        }
        finally {
            Conexao.fecharConexao();
        }
        return cli;
    }

    @Override
    public Cliente consultarPeloCpf(String cpf){
        Cliente cliente = new Cliente();
        String sql = "SELECT nome, cpf, telefone, pontos FROM clientes WHERE cpf = ?";

        try{
            Connection con = Conexao.getConnection();

            PreparedStatement smt = con.prepareStatement(sql);
            smt.setString(1,cpf);
            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                Cliente cli;
                cli = createUser(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getInt("pontos"));
                cliente = cli;

            }
        }catch(SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
        finally{
            Conexao.fecharConexao();
        }
        return cliente;
    }
}

