package org.example.model;

import java.util.Objects;

public class StockProdutos {
    private Produto produto;
    private int stock;

    private static final int STOCK_POR_OMISSAO = 0;

    public StockProdutos(Produto produto, int stock) {
        this.produto = produto;
        this.stock = stock;
    }

    public StockProdutos() {
        this.produto = new Produto();
        this.stock = STOCK_POR_OMISSAO;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Adiciona unidades ao stock
    public void adicionarStock(int quantidade) {
        if (quantidade > 0) {
            this.stock += quantidade;
        }
    }

    // Vende unidades do produto
    public boolean vender(int quantidade) {
        if (quantidade > 0 && quantidade <= stock) {
            stock -= quantidade;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockProdutos that = (StockProdutos) o;
        return stock == that.stock && Objects.equals(produto, that.produto);
    }

    @Override
    public String toString() {
        return produto.toString() + " | Stock: " + stock;
    }
}
