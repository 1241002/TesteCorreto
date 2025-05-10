package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VoluntarioStock extends Voluntario {
    private List<StockProdutos> produtosEstoque;

    public VoluntarioStock(String nome, int numeroAluno, Instituicao instituicao) {
        super(nome, numeroAluno, instituicao);
        this.produtosEstoque = new ArrayList<>(); // Inicializa a lista de produtos no estoque
    }

    // Metodo para repor um produto no estoque
    public void reporProduto(String nomeProduto, int quantidade) {
        StockProdutos produto = encontrarProduto(nomeProduto);
        if (produto != null) {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
            System.out.println("Produto " + nomeProduto + " reposto com sucesso. Quantidade atual: " + produto.getQuantidade());
        } else {
            System.out.println("Produto " + nomeProduto + " não encontrado no estoque.");
        }
    }

    // Metodo auxiliar para encontrar um produto pelo nome
    private StockProdutos encontrarProduto(String nomeProduto) {
        for (StockProdutos produto : produtosEstoque) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null; // Produto não encontrado
    }

    // Metodo para adicionar um produto ao estoque
    public void adicionarProdutoAoEstoque(StockProdutos produto) {
        produtosEstoque.add(produto);
        System.out.println("Produto " + produto.getNome() + " adicionado ao estoque.");
    }

    public List<StockProdutos> getProdutosEstoque() {
        return produtosEstoque;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nVoluntario de Stock:");
        sb.append(super.toString());
        sb.append("\nProdutos de Stoque: ").append(produtosEstoque);
        sb.append("");
        return sb.toString();
    }
}