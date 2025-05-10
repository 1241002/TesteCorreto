package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VendaProdutos {
    private List<StockProdutos> produtosDisponiveis;
    private List<Vendas> vendasRealizadas;

    public VendaProdutos(List<StockProdutos> produtosDisponiveis) {
        this.produtosDisponiveis = produtosDisponiveis;
        this.vendasRealizadas = new ArrayList<>();
    }

    public VendaProdutos(VendaProdutos vp) {
        this.produtosDisponiveis = new ArrayList<>(vp.produtosDisponiveis);
        this.vendasRealizadas = new ArrayList<>(vp.vendasRealizadas);
    }

    public VendaProdutos() {
        this.produtosDisponiveis = new ArrayList<>();
        this.vendasRealizadas = new ArrayList<>();
    }

    public boolean registoVenda(String nomeProduto, int quantidade) {
        for (StockProdutos produto : produtosDisponiveis) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                if (produto.getQuantidade() >= quantidade) {
                    produto.setQuantidade(produto.getQuantidade() - quantidade);
                    Vendas venda = new Vendas(produto, quantidade);
                    vendasRealizadas.add(venda);
                    System.out.println("Produto registado: " + produto.getNome() + " com quantidade de: " + produto.getQuantidade());
                    return true;
                } else {
                    System.out.println("Produto" + nomeProduto + "nao validado devido a quantidade insuficiente em stock");

                }
            }
        }
        System.out.println("Produto: " + nomeProduto + " nao encontrado devido ao escoamento de stock");
        return false;
    }
    public double calcularTotalVendasRealizadas(){
        double totalVendas=0.0;
        for (Vendas venda : vendasRealizadas) {
            totalVendas += venda.getQuantidade();
        }
        return totalVendas;
    }

    public List<StockProdutos> getProdutosDisponiveis() {
        return produtosDisponiveis;
    }

    public void setProdutosDisponiveis(List<StockProdutos> produtosDisponiveis) {
        this.produtosDisponiveis = produtosDisponiveis;
    }

    public List<Vendas> getVendasRealizadas() {
        return vendasRealizadas;
    }

    public void setVendasRealizadas(List<Vendas> vendasRealizadas) {
        this.vendasRealizadas = vendasRealizadas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Vendas de produtos:");
        sb.append("\nProdutosDisponiveis").append(produtosDisponiveis);
        sb.append("\nVendas Realizadas:").append(vendasRealizadas);
        sb.append("\n");
        return sb.toString();
    }
}


