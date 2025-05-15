package org.example.model;

import java.util.Objects;

public class Vendas {
    private StockProdutos produtos;
    private int quantidade;
    private static final StockProdutos PRODUTOS_POR_OMISSAO = new StockProdutos("", 0.0, 0);
    private static final int QUANTIDADE_POR_OMISSAO = 0;

    public Vendas(StockProdutos produtos, int quantidade) {
        this.produtos = produtos;
        this.quantidade = quantidade;
    }

    public Vendas(Vendas v) {
        this.produtos = new StockProdutos(v.produtos.getNome(), v.produtos.getPrecoUnitario(), v.produtos.getQuantidade());
        this.quantidade = v.quantidade;
    }

    public Vendas() {
        this.produtos = PRODUTOS_POR_OMISSAO;
        this.quantidade = QUANTIDADE_POR_OMISSAO;
    }

    public double calcularTotalVendas() {
        return produtos.getPrecoUnitario() * quantidade;
    }

    public StockProdutos getProdutos() {
        return new StockProdutos(produtos.getNome(), produtos.getPrecoUnitario(), produtos.getQuantidade());
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendas vendas = (Vendas) o;
        return quantidade == vendas.quantidade && Objects.equals(produtos, vendas.produtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtos, quantidade);
    }

    @Override
    public String toString() {
        return String.format("Venda: Produto=%s, Quantidade=%d, Valor Total=%.2f€",
                produtos.getNome(), quantidade, calcularTotalVendas());
    }
}