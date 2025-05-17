package org.example.model;

import java.io.Serializable;

/**
 * Representa uma venda de um produto, com nome, quantidade e valor total.
 */
public class VendaProdutos implements Comparable<VendaProdutos>, Serializable {
    private String nomeProduto;
    private int quantidade;
    private double valorTotal;

    /**
     * Construtor completo.
     *
     * @param nomeProduto Nome do produto vendido.
     * @param quantidade Quantidade vendida.
     * @param valorTotal Valor total da venda.
     */
    public VendaProdutos(String nomeProduto, int quantidade, double valorTotal) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    /**
     * Construtor por omissão.
     */
    public VendaProdutos() {
        this.nomeProduto = "";
        this.quantidade = 0;
        this.valorTotal = 0.0;
    }

    /**
     * Construtor de cópia.
     *
     * @param outro VendaProdutos a copiar.
     */
    public VendaProdutos(VendaProdutos outro) {
        this.nomeProduto = outro.nomeProduto;
        this.quantidade = outro.quantidade;
        this.valorTotal = outro.valorTotal;
    }

    /** Obtém o nome do produto vendido. */
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    /** Obtém a quantidade vendida. */
    public int getQuantidade() {
        return this.quantidade;
    }

    /** Obtém o valor total da venda. */
    public double getValorTotal() {
        return this.valorTotal;
    }

    /** Define o nome do produto vendido. */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /** Define a quantidade vendida. */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /** Define o valor total da venda. */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Compara vendas pelo nome do produto (ignora maiúsculas/minúsculas).
     */
    @Override
    public int compareTo(VendaProdutos outro) {
        return this.nomeProduto.compareToIgnoreCase(outro.nomeProduto);
    }

    /**
     * Verifica se duas vendas são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendaProdutos)) return false;
        VendaProdutos that = (VendaProdutos) o;
        return this.quantidade == that.quantidade &&
                Double.compare(this.valorTotal, that.valorTotal) == 0 &&
                this.nomeProduto.equals(that.nomeProduto);
    }

    /**
     * Representação em String da venda.
     */
    @Override
    public String toString() {
        return "VendaProdutos{nomeProduto='" + nomeProduto + "', quantidade=" + quantidade + ", valorTotal=" + String.format("%.2f", valorTotal) + "}";
    }
}
