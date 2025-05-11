package org.example.model;

import java.util.Objects;

public class Vendas {
    private StockProdutos produtos;
    private int quantidade;
    private static StockProdutos PRODUTOS_POR_OMISSAO = new StockProdutos();
    private static int QUANTIDADE_POR_OMISSAO = 0;

    public Vendas(StockProdutos produtos, int quantidade) {
        this.produtos = produtos;
        this.quantidade = quantidade;
    }
    public Vendas (Vendas v){
        this.produtos = v.produtos;
        this.quantidade = v.quantidade;
    }
    public Vendas(){
        this.produtos = PRODUTOS_POR_OMISSAO;
        this.quantidade = QUANTIDADE_POR_OMISSAO;
    }
    public double calcularTotalVendas(){
        return produtos.getPrecoUnitario()*quantidade;
    }

    public StockProdutos getProdutos() {
        return produtos;
    }
    public void setProdutos(StockProdutos produtos) {
        this.produtos = produtos;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vendas vendas = (Vendas) o;
        return getQuantidade() == vendas.getQuantidade()
                && Objects.equals(getProdutos(), vendas.getProdutos());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vendas{");
        sb.append("\nVendas");
        sb.append("\nProdutos: ").append(produtos);
        sb.append("\nQuantidade=").append(quantidade);
        sb.append("");
        return sb.toString();
    }
}
