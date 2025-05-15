package org.example.model;

public class Vendas implements Comparable<Vendas> {
    // Variáveis de instância
    private StockProdutos produtos;
    private int quantidade;

    // Variáveis de classe
    private static final StockProdutos PRODUTOS_POR_OMISSAO = new StockProdutos("", 0.0, 0);
    private static final int QUANTIDADE_POR_OMISSAO = 0;

    // Construtor completo
    public Vendas(StockProdutos produtos, int quantidade) {
        if (produtos == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.produtos = new StockProdutos(produtos); // Cópia defensiva
        this.quantidade = quantidade;
    }

    // Construtor de cópia
    public Vendas(Vendas outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Venda não pode ser nula");
        }
        this.produtos = new StockProdutos(outro.produtos); // Cópia defensiva
        this.quantidade = outro.quantidade;
    }

    // Construtor vazio
    public Vendas() {
        this.produtos = PRODUTOS_POR_OMISSAO;
        this.quantidade = QUANTIDADE_POR_OMISSAO;
    }

    // Seletores
    public StockProdutos getProdutos() {
        return new StockProdutos(this.produtos); // Cópia defensiva
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    // Modificadores
    public void setProdutos(StockProdutos produtos) {
        if (produtos == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        this.produtos = new StockProdutos(produtos); // Cópia defensiva
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.quantidade = quantidade;
    }

    // Método de instância
    public double calcularTotalVendas() {
        return this.produtos.getPrecoUnitario() * this.quantidade;
    }

    // Implementação de Comparable (ordena por nome do produto)
    @Override
    public int compareTo(Vendas outro) {
        if (this.produtos == null || this.produtos.getNome() == null) {
            return (outro.produtos == null || outro.produtos.getNome() == null) ? 0 : -1;
        }
        if (outro.produtos == null || outro.produtos.getNome() == null) {
            return 1;
        }
        return this.produtos.getNome().compareToIgnoreCase(outro.produtos.getNome());
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendas)) return false;
        Vendas vendas = (Vendas) o;
        return this.quantidade == vendas.quantidade &&
                (this.produtos == null ? vendas.produtos == null : this.produtos.equals(vendas.produtos));
    }

    // Método toString
    @Override
    public String toString() {
        return String.format("Vendas{produto=%s, quantidade=%d, valorTotal=%.2f€}",
                (produtos == null || produtos.getNome() == null ? "N/A" : produtos.getNome()),
                quantidade,
                calcularTotalVendas());
    }
}