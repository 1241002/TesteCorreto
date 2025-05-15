package org.example.model;

public class StockProdutos implements Comparable<StockProdutos> {
    private String nome;
    private int quantidade;
    private double precoUnitario;

    public StockProdutos(String nome, double precoUnitario, int quantidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public StockProdutos() {
        this.nome = "";
        this.precoUnitario = 0.0;
        this.quantidade = 0;
    }

    public StockProdutos(StockProdutos outro) {
        this.nome = outro.nome;
        this.precoUnitario = outro.precoUnitario;
        this.quantidade = outro.quantidade;
    }

    public String getNome() {
        return this.nome;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void reduzirQuantidade(int quantidade) {
        this.quantidade -= quantidade;
    }

    @Override
    public int compareTo(StockProdutos outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockProdutos)) return false;
        StockProdutos that = (StockProdutos) o;
        return this.quantidade == that.quantidade &&
                Double.compare(this.precoUnitario, that.precoUnitario) == 0 &&
                this.nome.equals(that.nome);
    }

    @Override
    public String toString() {
        return "StockProdutos{nome='" + nome + "', quantidade=" + quantidade + ", precoUnitario=" + String.format("%.2f", precoUnitario) + "}";
    }
}