package org.example.model;

import java.util.Objects;

public class StockProdutos {
    private String nome;
    private int quantidade;
    private double precoUnitario;

    public StockProdutos(String nome, double precoUnitario, int quantidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public boolean reduzirQuantidade(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockProdutos)) return false;
        StockProdutos that = (StockProdutos) o;
        return quantidade == that.quantidade &&
                Double.compare(that.precoUnitario, precoUnitario) == 0 &&
                nome.equalsIgnoreCase(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase(), quantidade, precoUnitario);
    }

    @Override
    public String toString() {
        return "StockProdutos{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }
}