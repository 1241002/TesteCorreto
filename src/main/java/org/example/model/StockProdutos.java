package org.example.model;

import java.io.Serializable;

/**
 * Representa um produto em stock, com nome, preço unitário e quantidade.
 */
public class StockProdutos implements Comparable<StockProdutos>, Serializable {
    private String nome;
    private int quantidade;
    private double precoUnitario;

    /**
     * Construtor completo.
     *
     * @param nome Nome do produto.
     * @param precoUnitario Preço unitário do produto.
     * @param quantidade Quantidade disponível em stock.
     */
    public StockProdutos(String nome, double precoUnitario, int quantidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    /**
     * Construtor por omissão.
     */
    public StockProdutos() {
        this.nome = "";
        this.precoUnitario = 0.0;
        this.quantidade = 0;
    }

    /**
     * Construtor de cópia.
     *
     * @param outro Produto a copiar.
     */
    public StockProdutos(StockProdutos outro) {
        this.nome = outro.nome;
        this.precoUnitario = outro.precoUnitario;
        this.quantidade = outro.quantidade;
    }

    /** Obtém o nome do produto. */
    public String getNome() {
        return this.nome;
    }

    /** Obtém a quantidade em stock. */
    public int getQuantidade() {
        return this.quantidade;
    }

    /** Obtém o preço unitário do produto. */
    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    /** Define o nome do produto. */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** Define a quantidade em stock. */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /** Define o preço unitário do produto. */
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * Reduz a quantidade do produto em stock.
     *
     * @param quantidade Quantidade a reduzir.
     */
    public void reduzirQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }

    /**
     * Compara produtos pelo nome (ignora maiúsculas/minúsculas).
     */
    @Override
    public int compareTo(StockProdutos outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    /**
     * Verifica se dois produtos são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockProdutos)) return false;
        StockProdutos that = (StockProdutos) o;
        return this.quantidade == that.quantidade &&
                Double.compare(this.precoUnitario, that.precoUnitario) == 0 &&
                this.nome.equals(that.nome);
    }

    /**
     * Representação em String do produto.
     */
    @Override
    public String toString() {
        return "StockProdutos{nome='" + nome + "', quantidade=" + quantidade + ", precoUnitario=" + String.format("%.2f", precoUnitario) + "}";
    }
}
