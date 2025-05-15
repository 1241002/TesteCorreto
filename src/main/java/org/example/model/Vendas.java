package org.example.model;

public class Vendas implements Comparable<Vendas> {
    private StockProdutos produtos;
    private int quantidade;

    public Vendas(StockProdutos produtos, int quantidade) {
        this.produtos = new StockProdutos(produtos);
        this.quantidade = quantidade;
    }

    public Vendas(Vendas outro) {
        this.produtos = new StockProdutos(outro.produtos);
        this.quantidade = outro.quantidade;
    }

    public Vendas() {
        this.produtos = new StockProdutos();
        this.quantidade = 0;
    }

    public StockProdutos getProdutos() {
        return new StockProdutos(this.produtos);
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setProdutos(StockProdutos produtos) {
        this.produtos = new StockProdutos(produtos);
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double calcularTotalVendas() {
        return this.produtos.getPrecoUnitario() * this.quantidade;
    }

    @Override
    public int compareTo(Vendas outro) {
        return this.produtos.getNome().compareToIgnoreCase(outro.produtos.getNome());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendas)) return false;
        Vendas vendas = (Vendas) o;
        return this.quantidade == vendas.quantidade &&
                this.produtos.equals(vendas.produtos);
    }

    @Override
    public String toString() {
        return "Vendas{produto=" + produtos.getNome() + ", quantidade=" + quantidade + ", valorTotal=" + String.format("%.2f", calcularTotalVendas()) + "€}";
    }
}