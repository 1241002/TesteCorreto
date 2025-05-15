package org.example.model;

public class Produto implements Comparable<Produto> {
    // Variáveis de instância
    private String nome;
    private double precoUnitario;

    // Construtor completo
    public Produto(String nome, double precoUnitario) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        }
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    // Construtor vazio
    public Produto() {
        this.nome = "";
        this.precoUnitario = 0.0;
    }

    // Construtor de cópia
    public Produto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        this.nome = produto.nome;
        this.precoUnitario = produto.precoUnitario;
    }

    // Seletores
    public String getNome() {
        return this.nome;
    }

    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    // Modificadores
    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        }
        this.precoUnitario = precoUnitario;
    }

    // Implementação de Comparable (ordena por nome)
    @Override
    public int compareTo(Produto outro) {
        if (this.nome == null && outro.nome == null) return 0;
        if (this.nome == null) return -1;
        if (outro.nome == null) return 1;
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Double.compare(this.precoUnitario, produto.precoUnitario) == 0 &&
                (this.nome == null ? produto.nome == null : this.nome.equalsIgnoreCase(produto.nome));
    }

    // Método toString
    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + (nome == null ? "" : nome) + '\'' +
                ", precoUnitario=" + String.format("%.2f", precoUnitario) +
                '}';
    }
}