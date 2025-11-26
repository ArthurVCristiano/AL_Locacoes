package mock;

import entity.Venda;
import interfaces.VendaDaoInterface;

import java.util.ArrayList;
import java.util.List;

public class VendaMock implements VendaDaoInterface {
    private List<Venda> vendas;

    public VendaMock(){
        this.vendas = new ArrayList<>();
    }

    @Override
    public void salvar(Venda venda) {
        vendas.add(venda);
    }

    @Override
    public List<Venda> consultar() {
        return vendas;
    }

    @Override
    public boolean delete(int id) {
        for(Venda umaVen : vendas){
            if(vendas.indexOf(umaVen) == id){
                vendas.remove(umaVen);
                return true;
            }
        }
        return false;
    }

    @Override
    public Venda update(Venda venda, int id) {
        for(Venda umaVen : vendas){
            if(vendas.indexOf(umaVen) == id){
                vendas.remove(umaVen);
                salvar(venda);
                return venda;
            }
        }
        return null;
    }

    @Override
    public Venda consultarPeloId(int id) {
        for(Venda umaVen : vendas){
            if(vendas.indexOf(umaVen) == id){
                return umaVen;
            }
        }
        return null;
    }
}
