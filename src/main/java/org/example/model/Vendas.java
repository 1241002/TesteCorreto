package org.example.model;

import java.io.Serializable;

/**
 * Representa uma venda de um produto em stock, com a quantidade vendida.
 */
public class Vendas implements Comparable<Vendas>, Serializable {
    private StockProdutos produtos;
    private int quantidade;

    /**
     * Construtor completo.
     *
     * @param produtos Produto em stock vendido.
     * @param quantidade Quantidade vendida.
     */
    public Vendas(StockProdutos produtos, int quantidade) {
        this.produtos = new StockProdutos(produtos);
        this.quantidade = quantidade;
    }

    /**
     * Construtor de cópia.
     *
     * @param outro Outra venda a copiar.
     */
    public Vendas(Vendas outro) {
        this.produtos = new StockProdutos(outro.produtos);
        this.quantidade = outro.quantidade;
    }

    /**
     * Construtor por omissão.
     */
    public Vendas() {
        this.produtos = new StockProdutos();
        this.quantidade = 0;
    }

    /** Obtém o produto vendido (cópia). */
    public StockProdutos getProdutos() {
        return new StockProdutos(this.produtos);
    }

    /** Obtém a quantidade vendida. */
    public int getQuantidade() {
        return this.quantidade;
    }

    /** Define o produto vendido (faz cópia). */
    public void setProdutos(StockProdutos produtos) {
        this.produtos = new StockProdutos(produtos);
    }

    /** Define a quantidade vendida. */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /** Calcula o valor total da venda. */
    public double calcularTotalVendas() {
        return this.produtos.getPrecoUnitario() * this.quantidade;
    }

    /**
     * Compara vendas pelo nome do produto.
     */
    @Override
    public int compareTo(Vendas outro) {
        return this.produtos.getNome().compareToIgnoreCase(outro.produtos.getNome());
    }

    /**
     * Verifica se duas vendas são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendas)) return false;
        Vendas vendas = (Vendas) o;
        return this.quantidade == vendas.quantidade &&
                this.produtos.equals(vendas.produtos);
    }

    /**
     * Representação textual da venda.
     */
    @Override
    public String toString() {
        return "Vendas{produto=" + produtos.getNome() + ", quantidade=" + quantidade + ", valorTotal=" + String.format("%.2f", calcularTotalVendas()) + "€}";
    }
}
