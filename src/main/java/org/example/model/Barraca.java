package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Barraca {
    private String nome;
    private Instituicao instituicao;
    private List<Voluntario> voluntarios;
    private List<StockProdutos> stock;
    private List<VendaProdutos> vendas;

    public Barraca(String nome, Instituicao instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

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

    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (voluntario.getInstituicao().equals(instituicao) && voluntario.getBarracaAssociada() == null) {
            voluntarios.add(voluntario);
            voluntario.setBarracaAssociada(this);
            return true;
        }
        return false;
    }

    public String classificar() {
        int stockTotal = exportarStockTotal();
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
        for (StockProdutos sp : stock) {
            total += sp.getQuantidade();
        }
        return total;
    }

    public float exportarVendas() {
        float total = 0;
        for (Voluntario v : voluntarios) {
            if (v instanceof VoluntarioVendas) {
                for (VendaProdutos vp : ((VoluntarioVendas) v).getTodasVendas()) {
                    total += vp.getValorTotal();
                }
            }
        }
        return total;
    }

    public boolean adicionarStock(StockProdutos stockProdutos) {
        for (StockProdutos sp : stock) {
            if (sp.getNome().equalsIgnoreCase(stockProdutos.getNome())) {
                sp.setQuantidade(sp.getQuantidade() + stockProdutos.getQuantidade());
                return true;
            }
        }
        stock.add(stockProdutos);
        return true;
    }

    public boolean reduzirStock(String nomeProduto, int quantidade) {
        for (StockProdutos sp : stock) {
            if (sp.getNome().equalsIgnoreCase(nomeProduto) && sp.getQuantidade() >= quantidade) {
                sp.setQuantidade(sp.getQuantidade() - quantidade);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barraca)) return false;
        Barraca barraca = (Barraca) o;
        return nome.equalsIgnoreCase(barraca.nome) &&
                instituicao.equals(barraca.instituicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase(), instituicao);
    }

    @Override
    public String toString() {
        return "Barraca{" +
                "nome='" + nome + '\'' +
                ", instituicao=" + instituicao.getNome() +
                '}';
    }
}