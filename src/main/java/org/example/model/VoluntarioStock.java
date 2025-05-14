package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VoluntarioStock extends Voluntario {
    private ArrayList<StockProdutos> produtosStock;

    public VoluntarioStock(String nome, int numeroAluno, Instituicao instituicao) {
        super(nome, numeroAluno, instituicao);
        this.produtosStock = new ArrayList<>();
    }

    public VoluntarioStock(VoluntarioStock vs) {
        super(vs);
        this.produtosStock = new ArrayList<>(vs.produtosStock);
    }

    public VoluntarioStock() {
        super();
        this.produtosStock = new ArrayList<>();
    }

    public void reporProduto(String nomeProduto, int quantidade) {
        StockProdutos produto = encontrarProduto(nomeProduto);
        if (produto != null) {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
            System.out.println("Produto " + nomeProduto + " reposto com sucesso. Quantidade atual: " + produto.getQuantidade());
        } else {
            System.out.println("Produto " + nomeProduto + " não encontrado no stock.");
        }
    }

    private StockProdutos encontrarProduto(String nomeProduto) {
        for (StockProdutos produto : produtosStock) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }


    public void adicionarProdutoAoStock(StockProdutos produto) {
        produtosStock.add(produto);
        System.out.println("Produto " + produto.getNome() + " adicionado ao stock.");
    }

    public ArrayList<StockProdutos> getProdutosStock() {
        return produtosStock;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nVoluntario de Stock:");
        sb.append(super.toString());
        sb.append("\nProdutos de Stock: ").append(produtosStock);
        sb.append("");
        return sb.toString();
    }
}