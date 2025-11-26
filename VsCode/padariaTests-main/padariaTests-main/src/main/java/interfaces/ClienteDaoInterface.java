package interfaces;

import entity.Cliente;

import java.util.List;

public interface ClienteDaoInterface {
    void salvar(Cliente cliente);
    List<Cliente> consultar ();
    boolean delete(String cpf);
    Cliente update(Cliente cli, String cpf );
    Cliente consultarPeloCpf(String cpf);
}
