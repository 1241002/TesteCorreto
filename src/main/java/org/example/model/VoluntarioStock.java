package org.example.model;

import java.io.Serializable;

/**
 * Representa um voluntário especializado em gestão de stock de produtos.
 * Permite adicionar produtos e repor quantidades na barraca associada.
 */
public class VoluntarioStock extends Voluntario implements Serializable {

    /**
     * Construtor completo.
     *
     * @param nome Nome do voluntário.
     * @param numeroAluno Número do aluno.
     * @param instituicao Instituição associada.
     * @param curso Curso do voluntário.
     * @param senha Senha de acesso.
     */
    public VoluntarioStock(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        super(nome, numeroAluno, instituicao, curso, senha);
    }

    /** Construtor por omissão. */
    public VoluntarioStock() {
        super();
    }

    /**
     * Construtor de cópia.
     *
     * @param outro Outro voluntário stock para copiar os dados.
     */
    public VoluntarioStock(VoluntarioStock outro) {
        super(outro);
    }

    /**
     * Adiciona um produto ao stock da barraca associada.
     *
     * @param nomeProduto Nome do produto.
     * @param precoUnitario Preço unitário do produto.
     * @param quantidade Quantidade a adicionar.
     */
    public void adicionarProdutoAoStock(String nomeProduto, double precoUnitario, int quantidade) {
        Barraca barraca = this.getBarracaAssociada();
        barraca.adicionarStock(new StockProdutos(nomeProduto, precoUnitario, quantidade));
    }

    /**
     * Repõe a quantidade de um produto no stock da barraca associada.
     * Procura o preço unitário do produto na lista de produtos da instituição.
     *
     * @param nomeProduto Nome do produto.
     * @param quantidade Quantidade a repor.
     */
    public void reporProduto(String nomeProduto, int quantidade) {
        Barraca barraca = this.getBarracaAssociada();
        double precoUnitario = -1.0; // indicador de produto não encontrado
        for (Produto produto : this.getInstituicao().getLstProdutos()) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                precoUnitario = produto.getPrecoUnitario();
                break;
            }
        }
        if (precoUnitario < 0) {
            throw new IllegalArgumentException("Produto não encontrado na instituição: " + nomeProduto);
        }

        barraca.adicionarStock(new StockProdutos(nomeProduto, precoUnitario, quantidade));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "VoluntarioStock{" + super.toString() + "}";
    }
}
