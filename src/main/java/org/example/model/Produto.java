package org.example.model;

import java.util.Objects;

public class Produto {
    private String nome;
    private double precoUnitario;

    private static final String NOME_POR_OMISSAO = "por definir";
    private static final double PRECOUNITARIO_POR_OMISSAO = 0.0;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.precoUnitario = preco;
    }

    public Produto() {
        this.nome = NOME_POR_OMISSAO;
        this.precoUnitario = PRECOUNITARIO_POR_OMISSAO;
    }

    public Produto(Produto produto) {
        this.nome = produto.nome;
        this.precoUnitario = produto.precoUnitario;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.precoUnitario, precoUnitario) == 0 && Objects.equals(nome, produto.nome);
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nNome: ").append(nome);
        sb.append("\nPreco:").append(precoUnitario).append("€");
        sb.append("");
        return sb.toString();
    }
}
