package org.example.model;

public class VendaProdutos {
    private String nomeProduto;
    private int quantidade;
    private double precoUnitario;

    public VendaProdutos(String nomeProduto, int quantidade, double precoUnitario) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public double getValorTotal() {
        return quantidade * precoUnitario;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public String toString() {
        return nomeProduto + " - " + quantidade + " x " + precoUnitario + "€ = " + String.format("%.2f€", getValorTotal());
    }
}
