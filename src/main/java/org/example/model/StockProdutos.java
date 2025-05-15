package org.example.model;

public class StockProdutos implements Comparable<StockProdutos> {
    // Variáveis de instância
    private String nome;
    private int quantidade;
    private double precoUnitario;

    // Construtor completo
    public StockProdutos(String nome, double precoUnitario, int quantidade) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    // Construtor vazio
    public StockProdutos() {
        this.nome = "";
        this.precoUnitario = 0.0;
        this.quantidade = 0;
    }

    // Construtor de cópia
    public StockProdutos(StockProdutos outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Objeto StockProdutos não pode ser nulo");
        }
        this.nome = outro.nome;
        this.precoUnitario = outro.precoUnitario;
        this.quantidade = outro.quantidade;
    }

    // Seletores
    public String getNome() {
        return this.nome;
    }

    public int getQuantidade() {
        return this.quantidade;
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

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(double precoUnitario) {
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Preço unitário não pode ser negativo");
        }
        this.precoUnitario = precoUnitario;
    }

    // Método para reduzir quantidade
    public boolean reduzirQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade a reduzir não pode ser negativa");
        }
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }

    // Implementação de Comparable (ordena por nome)
    @Override
    public int compareTo(StockProdutos outro) {
        if (this.nome == null && outro.nome == null) return 0;
        if (this.nome == null) return -1;
        if (outro.nome == null) return 1;
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockProdutos)) return false;
        StockProdutos that = (StockProdutos) o;
        return this.quantidade == that.quantidade &&
                Double.compare(this.precoUnitario, that.precoUnitario) == 0 &&
                (this.nome == null ? that.nome == null : this.nome.equalsIgnoreCase(that.nome));
    }

    // Método toString
    @Override
    public String toString() {
        return "StockProdutos{" +
                "nome='" + (nome == null ? "" : nome) + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + String.format("%.2f", precoUnitario) +
                '}';
    }
}