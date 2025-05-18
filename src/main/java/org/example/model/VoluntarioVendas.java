package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um voluntário responsável por registar e gerir vendas de produtos.
 * Mantém uma lista das vendas realizadas, permite registar, remover vendas e calcular totais.
 * Implementa categorização baseada no total acumulado de vendas.
 */
public class VoluntarioVendas extends Voluntario implements IVendasVoluntarios, Serializable {
    private final List<VendaProdutos> vendasProdutos;

    /**
     * Construtor completo.
     *
     * @param nome Nome do voluntário.
     * @param numeroAluno Número do aluno.
     * @param instituicao Instituição associada.
     * @param curso Curso do voluntário.
     * @param senha Senha de acesso.
     */
    public VoluntarioVendas(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        super(nome, numeroAluno, instituicao, curso, senha);
        this.vendasProdutos = new ArrayList<>();
    }

    /** Construtor por omissão. */
    public VoluntarioVendas() {
        super();
        this.vendasProdutos = new ArrayList<>();
    }

    /**
     * Construtor de cópia.
     *
     * @param outro Outro voluntário vendas para copiar os dados.
     */
    public VoluntarioVendas(VoluntarioVendas outro) {
        super(outro);
        this.vendasProdutos = new ArrayList<>(outro.vendasProdutos);
    }

    /**
     * Regista uma venda de um produto, reduzindo o stock da barraca associada
     * e adicionando a venda à lista interna.
     *
     * @param nomeProduto Nome do produto vendido.
     * @param quantidade Quantidade vendida.
     * @throws IllegalArgumentException se o produto não for encontrado ou a quantidade for inválida.
     */
    /**
     * Regista uma venda de um produto, reduzindo o stock da barraca associada
     * e adicionando a venda à lista interna.
     *
     * @param nomeProduto Nome do produto vendido.
     * @param quantidade Quantidade vendida.
     * @throws IllegalArgumentException se o produto não for encontrado no estoque da barraca ou a quantidade for inválida.
     */
    public void registarVenda(String nomeProduto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        Barraca barraca = this.getBarracaAssociada();
        if (barraca == null) {
            throw new IllegalStateException("Voluntário não associado a uma barraca.");
        }
        double precoUnitario = 0.0;
        StockProdutos produtoEncontrado = null;
        for (StockProdutos sp : barraca.getStock()) {
            if (sp.getNome().trim().equalsIgnoreCase(nomeProduto.trim())) { // Comparação robusta
                precoUnitario = sp.getPrecoUnitario();
                produtoEncontrado = sp;
                break;
            }
        }
        if (produtoEncontrado == null) {
            throw new IllegalArgumentException("Produto '" + nomeProduto + "' não encontrado no estoque da barraca '" + barraca.getNome() + "'.");
        }
        if (precoUnitario <= 0.0) {
            throw new IllegalArgumentException("Preço unitário do produto '" + nomeProduto + "' é inválido (zero ou negativo).");
        }
        if (produtoEncontrado.getQuantidade() < quantidade) {
            throw new IllegalArgumentException("Quantidade insuficiente no estoque para o produto '" + nomeProduto + "'. Disponível: " + produtoEncontrado.getQuantidade());
        }
        barraca.reduzirStock(nomeProduto, quantidade);
        double valorTotal = quantidade * precoUnitario;
        this.vendasProdutos.add(new VendaProdutos(nomeProduto, quantidade, valorTotal));
    }

    /**
     * Remove a última venda registada, adicionando novamente a quantidade ao stock.
     */
    public void removerUltimaVenda() {
        if (!this.vendasProdutos.isEmpty()) {
            VendaProdutos ultimaVenda = this.vendasProdutos.remove(this.vendasProdutos.size() - 1);
            Barraca barraca = this.getBarracaAssociada();
            barraca.adicionarStock(new StockProdutos(
                    ultimaVenda.getNomeProduto(),
                    ultimaVenda.getValorTotal() / ultimaVenda.getQuantidade(),
                    ultimaVenda.getQuantidade()));
        }
    }

    /**
     * Obtém o total acumulado das vendas registadas.
     *
     * @return Total em valor monetário.
     */
    public double getTotalVendas() {
        double total = 0.0;
        for (VendaProdutos venda : this.vendasProdutos) {
            total += venda.getValorTotal();
        }
        return total;
    }

    /**
     * Retorna uma cópia da lista de todas as vendas realizadas.
     *
     * @return Lista das vendas.
     */
    public List<VendaProdutos> getTodasVendas() {
        return new ArrayList<>(this.vendasProdutos);
    }

    /**
     * Limpa todas as vendas registadas.
     */
    public void limparVendas() {
        this.vendasProdutos.clear();
    }

    /**
     * Obtém a categoria do voluntário com base no total das vendas.
     *
     * @return Categoria "Bronze", "Prata" ou "Ouro".
     */
    public String getCategoria() {
        double total = this.getTotalVendas();
        if (total < 200) {
            return "Bronze";
        } else if (total <= 500) {
            return "Prata";
        } else {
            return "Ouro";
        }
    }

    /**
     * Mostra no ecrã a categoria atual do voluntário.
     * A mensagem inclui nome, total de vendas e categoria atribuída.
     */
    @Override
    public void verificarEExibirCategoria() {
        System.out.printf("\t\tVoluntário: %s, Total de Vendas: %.2f€, Classificação: %s%n",
                this.getNome(), this.getTotalVendas(), this.getCategoria());
    }

    /**
     * Retorna uma string formatada com informações sobre a categoria e vendas do voluntário.
     *
     * @return Detalhes da categoria e total de vendas.
     */
    public String verificarCategoria() {
        return "Voluntário: " + this.getNome() +
                ", Total de Vendas: " + String.format("%.2f€", this.getTotalVendas()) +
                ", Classificação: " + this.getCategoria();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoluntarioVendas)) return false;
        VoluntarioVendas that = (VoluntarioVendas) o;
        return super.equals(o) && this.vendasProdutos.equals(that.vendasProdutos);
    }

    @Override
    public String toString() {
        return "VoluntarioVendas{" + super.toString() +
                ", totalVendas=" + String.format("%.2f€", this.getTotalVendas()) +
                ", vendas=" + (this.vendasProdutos.isEmpty() ? "nenhuma" : this.vendasProdutos.size() + " vendas") + "}";
    }
}
