package org.example.model;

public class VendaProdutos implements Comparable<VendaProdutos> {
    private String nomeProduto;
    private int quantidade;
    private double valorTotal;

    public VendaProdutos(String nomeProduto, int quantidade, double valorTotal) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public VendaProdutos() {
        this.nomeProduto = "";
        this.quantidade = 0;
        this.valorTotal = 0.0;
    }

    public VendaProdutos(VendaProdutos outro) {
        this.nomeProduto = outro.nomeProduto;
        this.quantidade = outro.quantidade;
        this.valorTotal = outro.valorTotal;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int compareTo(VendaProdutos outro) {
        return this.nomeProduto.compareToIgnoreCase(outro.nomeProduto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendaProdutos)) return false;
        VendaProdutos that = (VendaProdutos) o;
        return this.quantidade == that.quantidade &&
                Double.compare(this.valorTotal, that.valorTotal) == 0 &&
                this.nomeProduto.equals(that.nomeProduto);
    }

    @Override
    public String toString() {
        return "VendaProdutos{nomeProduto='" + nomeProduto + "', quantidade=" + quantidade + ", valorTotal=" + String.format("%.2f", valorTotal) + "}";
    }
}