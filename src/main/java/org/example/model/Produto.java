package org.example.model;

import java.util.Objects;

public class Produto {
    private String nome;
    private double preco;

    private static final String NOME_POR_OMISSAO = "por definir";
    private static final double PRECO_POR_OMISSAO = 0.0;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public Produto() {
        this.nome = NOME_POR_OMISSAO;
        this.preco = PRECO_POR_OMISSAO;
    }

    public Produto(Produto produto) {
        this.nome = produto.nome;
        this.preco = produto.preco;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.preco, preco) == 0 && Objects.equals(nome, produto.nome);
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ").append(nome);
        sb.append("\nPreco:").append(preco).append("€");
        sb.append("");
        return sb.toString();
    }
}
