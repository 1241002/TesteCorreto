package org.example.model;

import java.io.Serializable;

/**
 * Representa um produto com nome e preço unitário.
 * Esta classe implementa a interface {@link Comparable} para permitir comparação
 * baseada no nome do produto, ignorando maiúsculas/minúsculas.
 */
public class Produto implements Comparable<Produto>, Serializable {
    private String nome;
    private double precoUnitario;

    /**
     * Constrói um novo produto com o nome e preço unitário fornecidos.
     *
     * @param nome o nome do produto
     * @param precoUnitario o preço unitário do produto
     */
    public Produto(String nome, double precoUnitario) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    /**
     * Constrói um produto vazio, com nome vazio e preço unitário zero.
     */
    public Produto() {
        this.nome = "";
        this.precoUnitario = 0.0;
    }

    /**
     * Constrói um produto copiando os atributos de outro produto.
     *
     * @param produto o produto a ser copiado
     */
    public Produto(Produto produto) {
        this.nome = produto.nome;
        this.precoUnitario = produto.precoUnitario;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return o nome do produto
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna o preço unitário do produto.
     *
     * @return o preço unitário do produto
     */
    public double getPrecoUnitario() {
        return this.precoUnitario;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome o novo nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o preço unitário do produto.
     *
     * @param precoUnitario o novo preço unitário do produto
     */
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    /**
     * Compara este produto com outro com base no nome, ignorando diferenças entre
     * maiúsculas e minúsculas.
     *
     * @param outro o produto a ser comparado
     * @return valor negativo se este produto for lexicograficamente menor que o outro,
     *         zero se forem iguais, ou valor positivo se for maior
     */
    @Override
    public int compareTo(Produto outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    /**
     * Verifica se este produto é igual a outro objeto.
     * Dois produtos são considerados iguais se tiverem o mesmo nome e preço unitário.
     *
     * @param o o objeto a ser comparado
     * @return {@code true} se os produtos forem iguais, {@code false} caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Double.compare(this.precoUnitario, produto.precoUnitario) == 0 &&
                this.nome.equals(produto.nome);
    }

    /**
     * Retorna uma representação em string do produto.
     *
     * @return string no formato "Produto{nome='nome', precoUnitario=preco}"
     */
    @Override
    public String toString() {
        return "Produto{nome='" + nome + "', precoUnitario=" + String.format("%.2f", precoUnitario) + "}";
    }
}
