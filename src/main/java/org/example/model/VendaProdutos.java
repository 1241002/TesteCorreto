package org.example.model;

import java.util.Objects;

public class VendaProdutos {
    private String nomeProduto;
    private int quantidade;
    private double valorTotal;

    public VendaProdutos(String nomeProduto, int quantidade, double valorTotal) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendaProdutos)) return false;
        VendaProdutos that = (VendaProdutos) o;
        return quantidade == that.quantidade &&
                Double.compare(that.valorTotal, valorTotal) == 0 &&
                nomeProduto.equalsIgnoreCase(that.nomeProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeProduto.toLowerCase(), quantidade, valorTotal);
    }

    @Override
    public String toString() {
        return "VendaProdutos{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                '}';
    }
}