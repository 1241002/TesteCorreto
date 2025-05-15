package org.example.model;

public class Produto implements Comparable<Produto> {
    private String nome;
    private double precoUnitario;

    public Produto(String nome, double precoUnitario) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public Produto() {
        this.nome = "";
        this.precoUnitario = 0.0;
    }

    public Produto(Produto produto) {
        this.nome = produto.nome;
        this.precoUnitario = produto.precoUnitario;
    }

    public String getNome() {
        return this.nome;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public int compareTo(Produto outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Double.compare(this.precoUnitario, produto.precoUnitario) == 0 &&
                this.nome.equals(produto.nome);
    }

    @Override
    public String toString() {
        return "Produto{nome='" + nome + "', precoUnitario=" + String.format("%.2f", precoUnitario) + "}";
    }
}