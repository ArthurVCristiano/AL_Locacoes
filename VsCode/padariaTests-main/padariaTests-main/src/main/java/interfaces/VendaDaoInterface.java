package interfaces;

import entity.Venda;

import java.util.List;

public interface VendaDaoInterface {
    void salvar(Venda venda);
    List<Venda> consultar ();
    boolean delete(int id);
    Venda update(Venda venda, int id);
    Venda consultarPeloId(int id);
}
