package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Barraca implements Comparable<Barraca> {
    // Variáveis de instância
    private String nome;
    private Instituicao instituicao;
    private List<Voluntario> voluntarios;
    private List<StockProdutos> stock;

    // Construtor completo
    public Barraca(String nome, Instituicao instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
    }

    // Construtor vazio
    public Barraca() {
        this.nome = "";
        this.instituicao = null;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
    }

    // Construtor de cópia
    public Barraca(Barraca b) {
        this.nome = b.nome;
        this.instituicao = b.instituicao;
        this.voluntarios = new ArrayList<>(b.voluntarios);
        this.stock = new ArrayList<>(b.stock);
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

    // Modificadores
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (voluntario == null || voluntarios.contains(voluntario) || voluntario.getBarracaAssociada() != null) {
            return false; // Volunteer not added (null, already in list, or associated with another barraca)
        }
        voluntarios.add(voluntario);
        voluntario.setBarracaAssociada(this);
        return true; // Volunteer added successfully
    }

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

    public int exportarStockTotal() {
        int total = 0;
        for (StockProdutos sp : this.stock) {
            total += sp.getQuantidade();
        }
        return total;
    }

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

    public void adicionarStock(StockProdutos stockProdutos) {
        for (StockProdutos sp : this.stock) {
            if (sp.getNome().equals(stockProdutos.getNome())) {
                sp.setQuantidade(sp.getQuantidade() + stockProdutos.getQuantidade());
                return;
            }
        }
        this.stock.add(stockProdutos);
    }

    public void reduzirStock(String nomeProduto, int quantidade) {
        for (StockProdutos sp : this.stock) {
            if (sp.getNome().equals(nomeProduto)) {
                sp.setQuantidade(sp.getQuantidade() - quantidade);
                return;
            }
        }
    }

    // Implementação de Comparable (ordena por nome)
    @Override
    public int compareTo(Barraca outra) {
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barraca)) return false;
        Barraca barraca = (Barraca) o;
        return this.nome.equals(barraca.nome) &&
                this.instituicao.equals(barraca.instituicao);
    }

    // Método toString
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