package org.example.model;

public class StockProdutos extends Produto {
    private int quantidade;

    private static final int QUANTIDADE_POR_OMISSAO = 0;

    public StockProdutos(String nome, double preco, int quantidade) {
        super(nome, preco);
        this.quantidade = quantidade;
    }

    public StockProdutos() {
        super();
        this.quantidade = QUANTIDADE_POR_OMISSAO;
    }

    public StockProdutos(StockProdutos stock) {
        super(stock);
        this.quantidade = stock.quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\nQuantidade: ").append(quantidade);
        return sb.toString();
    }
}