package org.example.model;

public class VendaProdutos implements Comparable<VendaProdutos> {
    // Variáveis de instância
    private String nomeProduto;
    private int quantidade;
    private double valorTotal;

    // Construtor completo
    public VendaProdutos(String nomeProduto, int quantidade, double valorTotal) {
        if (nomeProduto == null) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        if (valorTotal < 0) {
            throw new IllegalArgumentException("Valor total não pode ser negativo");
        }
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    // Construtor vazio
    public VendaProdutos() {
        this.nomeProduto = "";
        this.quantidade = 0;
        this.valorTotal = 0.0;
    }

    // Construtor de cópia
    public VendaProdutos(VendaProdutos outro) {
        if (outro == null) {
            throw new IllegalArgumentException("VendaProdutos não pode ser nulo");
        }
        this.nomeProduto = outro.nomeProduto;
        this.quantidade = outro.quantidade;
        this.valorTotal = outro.valorTotal;
    }

    // Seletores
    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    // Modificadores
    public void setNomeProduto(String nomeProduto) {
        if (nomeProduto == null) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo");
        }
        this.nomeProduto = nomeProduto;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        this.quantidade = quantidade;
    }

    public void setValorTotal(double valorTotal) {
        if (valorTotal < 0) {
            throw new IllegalArgumentException("Valor total não pode ser negativo");
        }
        this.valorTotal = valorTotal;
    }

    // Implementação de Comparable (ordena por nomeProduto)
    @Override
    public int compareTo(VendaProdutos outro) {
        if (this.nomeProduto == null && outro.nomeProduto == null) return 0;
        if (this.nomeProduto == null) return -1;
        if (outro.nomeProduto == null) return 1;
        return this.nomeProduto.compareToIgnoreCase(outro.nomeProduto);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendaProdutos)) return false;
        VendaProdutos that = (VendaProdutos) o;
        return this.quantidade == that.quantidade &&
                Double.compare(this.valorTotal, that.valorTotal) == 0 &&
                (this.nomeProduto == null ? that.nomeProduto == null : this.nomeProduto.equalsIgnoreCase(that.nomeProduto));
    }

    // Método toString
    @Override
    public String toString() {
        return "VendaProdutos{" +
                "nomeProduto='" + (nomeProduto == null ? "" : nomeProduto) + '\'' +
                ", quantidade=" + quantidade +
                ", valorTotal=" + String.format("%.2f", valorTotal) +
                '}';
    }
}