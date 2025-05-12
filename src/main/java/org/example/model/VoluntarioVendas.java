package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VoluntarioVendas extends Voluntario implements IVendasVoluntarios, IClassificacao {
    private List<VendaProdutos> vendasProdutos;

    public VoluntarioVendas(String nome, int numeroAluno, Instituicao instituicao) {
        super(nome, numeroAluno, instituicao);
        this.vendasProdutos = new ArrayList<>();
    }

    public void registarVenda(String nomeProduto, int quantidade, double precoUnitario) {
        if (quantidade > 0 && precoUnitario > 0.0) {
            vendasProdutos.add(new VendaProdutos(nomeProduto, quantidade, precoUnitario));
        }
    }

    public boolean removerUltimaVenda() {
        if (!vendasProdutos.isEmpty()) {
            vendasProdutos.remove(vendasProdutos.size() - 1);
            return true;
        }
        return false;
    }

    public double getTotalVendas() {
        double total = 0.0;
        for (VendaProdutos venda : vendasProdutos) {
            total += venda.getValorTotal();
        }
        return total;
    }


    public List<VendaProdutos> getTodasVendas() {
        return new ArrayList<>(vendasProdutos);
    }

    public void limparVendas() {
        vendasProdutos.clear();
    }

    @Override
    public String classificar() {
        double totalVendas = getTotalVendas();
        if (totalVendas < 500.0) {
            return "Bronze";
        } else if (totalVendas >= 500.0 && totalVendas <= 1000.0) {
            return "Prata";
        } else {
            return "Ouro";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("VoluntarioVendas: ").append(super.toString());
        sb.append("Total de Vendas: ").append(String.format("%.2f€", getTotalVendas())).append("\n");
        for (VendaProdutos v : vendasProdutos) {
            sb.append(" - ").append(v.toString()).append("\n");
        }
        return sb.toString();
    }


}


