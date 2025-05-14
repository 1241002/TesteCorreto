package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Barraca {
    private String nome;
    private Instituicao instituicao;
    private List<Voluntario> voluntarios;
    private List<StockProdutos> stock;

    public Barraca(String nome, Instituicao instituicao) {
        this.nome = nome;
        this.instituicao = instituicao;
        this.voluntarios = new ArrayList<>();
        this.stock = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public List<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    public List<StockProdutos> getStock() {
        return stock;
    }

    public void setStock(List<StockProdutos> stock) {
        this.stock = stock;
    }

    public StockProdutos getStockProduto(String nomeProduto) {
        for (StockProdutos sp : stock) {
            if (sp.getNome().equalsIgnoreCase(nomeProduto)) {
                return sp;
            }
        }
        return null;
    }

    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (!this.instituicao.equals(voluntario.getInstituicao())) {
            System.out.println("Erro: O voluntário não pertence à mesma instituição da barraca.");
            return false;
        }

        if (voluntario.getBarracaAssociada() != null) {
            System.out.println("Erro: O voluntário já está associado a outra barraca.");
            return false;
        }

        voluntarios.add(voluntario);
        voluntario.setBarracaAssociada(this);
        return true;
    }

    public void adicionarStock(String nomeProduto, double precoUnitario, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        for (StockProdutos sp : stock) {
            if (sp.getNome().equalsIgnoreCase(nomeProduto)) {
                sp.setQuantidade(sp.getQuantidade() + quantidade);
                return;
            }
        }
        StockProdutos novoProduto = new StockProdutos(nomeProduto, precoUnitario, quantidade);
        stock.add(novoProduto);
    }

    public double exportarVendas() {
        double totalVendas = 0.0;
        for (Voluntario v : voluntarios) {
            if (v instanceof VoluntarioVendas) {
                VoluntarioVendas vv = (VoluntarioVendas) v;
                for (VendaProdutos venda : vv.getTodasVendas()) {
                    totalVendas += venda.getValorTotal();
                }
            }
        }
        return totalVendas;
    }

    public int exportarStockTotal() {
        int totalStock = 0;
        for (StockProdutos sp : stock) {
            totalStock += sp.getQuantidade();
        }
        return totalStock;
    }

    private int calcularTotalProdutos() {
        return exportarStockTotal();
    }

    public String classificar() {
        int stockFinal = exportarStockTotal();
        if (stockFinal > 100) {
            return "Bronze";
        } else if (stockFinal >= 50) {
            return "Prata";
        } else {
            return "Ouro";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Barraca: ").append(nome);
        sb.append("\nInstituição: ").append(instituicao.getNome());
        sb.append("\nStock:\n");
        if (stock.isEmpty()) {
            sb.append("\tNenhum produto em stock\n");
        } else {
            for (StockProdutos sp : stock) {
                sb.append("\t- ").append(sp.getNome()).append(": ").append(sp.getQuantidade())
                        .append(" unidades, Preço: ").append(sp.getPrecoUnitario()).append("€\n");
            }
        }
        return sb.toString();
    }
}