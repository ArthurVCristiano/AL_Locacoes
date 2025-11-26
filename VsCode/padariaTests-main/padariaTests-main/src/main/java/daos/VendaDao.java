package daos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import connection.Conexao;
import entity.Cliente;
import entity.Produto;
import entity.Venda;
import com.fasterxml.jackson.databind.ObjectMapper;
import interfaces.VendaDaoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static entity.Venda.createVenda;

public class VendaDao implements VendaDaoInterface {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void salvar(Venda venda) {

        ClienteDao dao = new ClienteDao();
        Cliente cliente = dao.consultarPeloCpf(venda.getCpfCliente());

        String sql = "INSERT INTO vendas (cpf_cliente,valor_venda,form_pag,data_venda, produtos) values(?,?,?,?,?)";

        try{
            Timestamp dataHoraAtual = new Timestamp(System.currentTimeMillis());
            Connection conexao = Conexao.getConnection();

            String produtos = mapper.writeValueAsString(venda.getProdutos());

            PreparedStatement smt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            smt.setString(1,venda.getCpfCliente());
            smt.setDouble(2,venda.getValorVenda());
            smt.setString(3, venda.getMtdPag());
            smt.setTimestamp(4,dataHoraAtual);
            smt.setString(5,produtos);

            smt.execute();
        }
        catch (SQLException | JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Erro ao tentar criar a venda:");
        }
    }

    @Override
    public Venda update(Venda venda, int id){
        Connection con = null;
        PreparedStatement smt = null;

        String sql = "UPDATE vendas SET cpf_cliente = ?, valor_venda = ?, form_pag = ? WHERE id_venda = ?" ;

        try {
            con = Conexao.getConnection();
            smt = con.prepareStatement(sql);

            smt.setString(1, venda.getCpfCliente());
            smt.setDouble(2,venda.getValorVenda());
            smt.setString(3,venda.getMtdPag());
            smt.setInt(4, id);

            smt.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("Erro ao tentar criar a venda: ");
        }
        finally {
            Conexao.fecharConexao();
        }
        return null;
    }

    @Override
    public List<Venda> consultar() {
        List<Venda> vendas = new ArrayList<>();
        Connection con = null;

        try{
            con = Conexao.getConnection();
            PreparedStatement smt = con.prepareStatement("SELECT * FROM vendas");

            ResultSet rs = smt.executeQuery();

            if(rs.next()){
                String jsonProdutos = rs.getString("produtos");
                ArrayList<Produto> produtos = mapper.readValue(
                        jsonProdutos, new TypeReference<ArrayList<Produto>>() {});
            while(rs.next()){
                Venda venda;
                venda = createVenda( rs.getString("cpf_cliente"), rs.getString("form_pag"), rs.getDouble("valor_venda"), produtos);
                System.out.println(venda.toString());
                }
            }


        }catch(SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }  catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } finally{
            Conexao.fecharConexao();
        }
        return vendas;
    }

    @Override
    public boolean delete(int id) {
        Connection con = null;
        PreparedStatement smt = null;

        String sql = "DELETE FROM vendas WHERE id_venda = ?";

        try{
            con = Conexao.getConnection();

            smt = con.prepareStatement(sql);
            smt.setInt(1, id);

            int linhasAfetadas = smt.executeUpdate();

            return linhasAfetadas > 0;

        }catch(SQLException ex){
            throw new RuntimeException();
        }
        finally{
            Conexao.fecharConexao();
        }
    }

    public Venda consultarPeloId(int id) {
        Venda venda  = null;
        ObjectMapper mapper = new ObjectMapper();
        String sql = "SELECT * FROM vendas WHERE id_venda = ?";
        try{
            Connection con = Conexao.getConnection();
            PreparedStatement smt = con.prepareStatement(sql);

            smt.setInt(1,id);

            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    String jsonProdutos = rs.getString("produtos");
                    ArrayList<Produto> produtos = mapper.readValue(
                            jsonProdutos, new TypeReference<ArrayList<Produto>>() {});
                    Venda ven = createVenda(rs.getString("cpf_cliente"), rs.getString("form_pag"), rs.getDouble("valor_venda"), produtos);
                    System.out.println(ven.toString());
                    venda = ven;
                }
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
        catch (Exception ex) {
            throw new RuntimeException("Erro ao processar dados JSON: " + ex.getMessage(), ex);
        }
        return venda;
    }
}
