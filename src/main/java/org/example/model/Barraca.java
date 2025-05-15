package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Barraca implements Comparable<Barraca> {
    // Variáveis de instância
    private String nome;
    private Instituicao instituicao;
    private List<Voluntario> voluntarios;
    private List<StockProdutos> stock;
    private List<VendaProdutos> vendas;

    // Construtor completo
    public Barraca(String nome, Instituicao instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    // Construtor vazio
    public Barraca() {
        this.nome = "";
        this.instituicao = null;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    // Construtor de cópia
    public Barraca(Barraca b) {
        this.nome = b.nome;
        this.instituicao = b.instituicao; // Assume-se referência partilhada
        this.voluntarios = new ArrayList<>(b.voluntarios);
        this.stock = new ArrayList<>(b.stock);
        this.vendas = new ArrayList<>(b.vendas);
    }

    // Seletores
    public String getNome() {
        return nome;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public List<Voluntario> getVoluntarios() {
        return new ArrayList<>(voluntarios);
    }

    public List<StockProdutos> getStock() {
        return new ArrayList<>(stock);
    }

    public List<VendaProdutos> getVendas() {
        return new ArrayList<>(vendas);
    }

    // Modificadores
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    // Métodos de instância
    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (voluntario == null) return false;
        if (voluntario.getInstituicao() != null &&
                voluntario.getInstituicao().equals(this.instituicao) &&
                voluntario.getBarracaAssociada() == null) {
            this.voluntarios.add(voluntario);
            voluntario.setBarracaAssociada(this);
            return true;
        }
        return false;
    }

    public String classificar() {
        int stockTotal = this.exportarStockTotal();
        if (stockTotal <= 10) {
            return "Ouro";
        } else if (stockTotal <= 100) {
            return "Prata";
        } else {
            return "Bronze";
        }
    }

    public int exportarStockTotal() {
        int total = 0;
        for (StockProdutos sp : this.stock) {
            total += sp.getQuantidade();
        }
        return total;
    }

    public float exportarVendas() {
        float total = 0;
        for (Voluntario v : this.voluntarios) {
            if (v instanceof VoluntarioVendas) {
                for (VendaProdutos vp : ((VoluntarioVendas) v).getTodasVendas()) {
                    total += vp.getValorTotal();
                }
            }
        }
        return total;
    }

    public boolean adicionarStock(StockProdutos stockProdutos) {
        if (stockProdutos == null) return false;
        for (StockProdutos sp : this.stock) {
            if (sp.getNome() != null &&
                    sp.getNome().equalsIgnoreCase(stockProdutos.getNome())) {
                sp.setQuantidade(sp.getQuantidade() + stockProdutos.getQuantidade());
                return true;
            }
        }
        this.stock.add(stockProdutos);
        return true;
    }

    public boolean reduzirStock(String nomeProduto, int quantidade) {
        if (nomeProduto == null || quantidade < 0) return false;
        for (StockProdutos sp : this.stock) {
            if (sp.getNome() != null &&
                    sp.getNome().equalsIgnoreCase(nomeProduto) &&
                    sp.getQuantidade() >= quantidade) {
                sp.setQuantidade(sp.getQuantidade() - quantidade);
                return true;
            }
        }
        return false;
    }

    // Implementação de Comparable (ordena por nome)
    @Override
    public int compareTo(Barraca outra) {
        if (this.nome == null && outra.nome == null) return 0;
        if (this.nome == null) return -1;
        if (outra.nome == null) return 1;
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barraca)) return false;
        Barraca barraca = (Barraca) o;
        return (this.nome == null ? barraca.nome == null : this.nome.equalsIgnoreCase(barraca.nome)) &&
                (this.instituicao == null ? barraca.instituicao == null : this.instituicao.equals(barraca.instituicao));
    }

    // Método toString
    @Override
    public String toString() {
        return "Barraca{" +
                "nome='" + (nome == null ? "" : nome) + '\'' +
                ", instituicao=" + (instituicao == null ? "N/A" : instituicao.getNome()) +
                ", voluntarios=" + voluntarios.size() +
                ", stockTotal=" + exportarStockTotal() +
                ", vendasTotal=" + String.format("%.2f", exportarVendas()) +
                '}';
    }
}