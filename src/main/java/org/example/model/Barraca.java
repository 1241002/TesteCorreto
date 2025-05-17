package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma barraca associada a uma instituição,
 * contendo voluntários e um conjunto de produtos em stock.
 * Pode ser classificada com base nas vendas (Bronze, Prata ou Ouro).
 */
public class Barraca implements Comparable<Barraca>, Serializable {

    private String nome;
    private Instituicao instituicao;
    private List<Voluntario> voluntarios;
    private List<StockProdutos> stock;

    /**
     * Construtor completo.
     *
     * @param nome Nome da barraca.
     * @param instituicao Instituição à qual a barraca pertence.
     */
    public Barraca(String nome, Instituicao instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
    }

    /**
     * Construtor por omissão.
     */
    public Barraca() {
        this.nome = "";
        this.instituicao = null;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
    }

    /**
     * Construtor de cópia.
     *
     * @param b Barraca a copiar.
     */
    public Barraca(Barraca b) {
        this.nome = b.nome;
        this.instituicao = b.instituicao;
        this.voluntarios = new ArrayList<>(b.voluntarios);
        this.stock = new ArrayList<>(b.stock);
    }

    /**
     * Obtém o nome da barraca.
     *
     * @return Nome da barraca.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da barraca.
     *
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a instituição associada à barraca.
     *
     * @return Instituição da barraca.
     */
    public Instituicao getInstituicao() {
        return instituicao;
    }

    /**
     * Define a instituição da barraca.
     *
     * @param instituicao Instituição a associar.
     */
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    /**
     * Retorna uma cópia da lista de voluntários associados à barraca.
     *
     * @return Lista de voluntários.
     */
    public List<Voluntario> getVoluntarios() {
        return new ArrayList<>(voluntarios);
    }

    /**
     * Retorna uma cópia da lista de produtos em stock.
     *
     * @return Lista de produtos em stock.
     */
    public List<StockProdutos> getStock() {
        return new ArrayList<>(stock);
    }

    /**
     * Adiciona um voluntário à barraca.
     *
     * @param voluntario Voluntário a adicionar.
     * @return true se foi adicionado com sucesso, false caso contrário.
     */
    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (voluntario == null || voluntarios.contains(voluntario) || voluntario.getBarracaAssociada() != null) {
            return false;
        }
        voluntarios.add(voluntario);
        voluntario.setBarracaAssociada(this);
        return true;
    }

    /**
     * Classifica a barraca com base nas suas vendas.
     *
     * @return "Ouro", "Prata" ou "Bronze".
     */
    public String classificar() {
        double vendasTotal = this.exportarVendas();
        if (vendasTotal >= 1000) {
            return "Ouro";
        } else if (vendasTotal >= 500) {
            return "Prata";
        } else {
            return "Bronze";
        }
    }

    /**
     * Calcula o total de unidades em stock.
     *
     * @return Quantidade total de produtos em stock.
     */
    public int exportarStockTotal() {
        int total = 0;
        for (StockProdutos sp : this.stock) {
            total += sp.getQuantidade();
        }
        return total;
    }

    /**
     * Calcula o total de vendas da barraca com base nos voluntários de vendas.
     *
     * @return Valor total das vendas.
     */
    public double exportarVendas() {
        double total = 0;
        for (Voluntario v : this.voluntarios) {
            if (v instanceof VoluntarioVendas) {
                for (VendaProdutos vp : ((VoluntarioVendas) v).getTodasVendas()) {
                    total += vp.getValorTotal();
                }
            }
        }
        return total;
    }

    /**
     * Adiciona um produto ao stock da barraca.
     * Se o produto já existir, apenas aumenta a sua quantidade.
     *
     * @param stockProdutos Produto a adicionar.
     */
    public void adicionarStock(StockProdutos stockProdutos) {
        for (StockProdutos sp : this.stock) {
            if (sp.getNome().equals(stockProdutos.getNome())) {
                sp.setQuantidade(sp.getQuantidade() + stockProdutos.getQuantidade());
                return;
            }
        }
        this.stock.add(stockProdutos);
    }

    /**
     * Reduz a quantidade de um produto do stock.
     *
     * @param nomeProduto Nome do produto.
     * @param quantidade Quantidade a reduzir.
     */
    public void reduzirStock(String nomeProduto, int quantidade) {
        for (StockProdutos sp : this.stock) {
            if (sp.getNome().equals(nomeProduto)) {
                sp.setQuantidade(sp.getQuantidade() - quantidade);
                return;
            }
        }
    }

    /**
     * Compara barracas com base no nome (ordem alfabética).
     *
     * @param outra Outra barraca a comparar.
     * @return Resultado da comparação.
     */
    @Override
    public int compareTo(Barraca outra) {
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    /**
     * Verifica se duas barracas são iguais.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barraca)) return false;
        Barraca barraca = (Barraca) o;
        return this.nome.equals(barraca.nome) &&
                this.instituicao.equals(barraca.instituicao);
    }

    /**
     * Representação textual da barraca.
     *
     * @return String com informações da barraca.
     */
    @Override
    public String toString() {
        return "Barraca{" +
                "nome='" + nome + '\'' +
                ", instituicao=" + (instituicao == null ? "N/A" : instituicao.getNome()) +
                ", voluntarios=" + voluntarios.size() +
                ", stockTotal=" + exportarStockTotal() +
                ", vendasTotal=" + String.format("%.2f", exportarVendas()) +
                '}';
    }
}
