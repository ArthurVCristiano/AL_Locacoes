package interfaces;

import entity.Produto;

import java.util.List;

public interface ProdutoDaoInterface {
    void salvar(Produto produto );
    List<Produto> consultar ();
    boolean delete(String texto);
    Produto update(String nome, Produto prod);
    Produto consultarPeloNome(String nome);
}
