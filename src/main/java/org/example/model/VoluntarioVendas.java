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
     */
    public void registarVenda(String nomeProduto, int quantidade) {
        Barraca barraca = this.getBarracaAssociada();
        double precoUnitario = 0.0;
        for (Produto produto : this.getInstituicao().getLstProdutos()) {
            if (produto.getNome().equals(nomeProduto)) {
                precoUnitario = produto.getPrecoUnitario();
                break;
            }
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
        if (total < 500) {
            return "Bronze";
        } else if (total <= 1000) {
            return "Prata";
        } else {
            return "Ouro";
        }
    }

    @Override
    public void verificarEExibirCategoria() {
        // Método da interface, pode ser implementado conforme necessidade.
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
