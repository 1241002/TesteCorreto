package org.example.model;

import org.example.utils.Utils;

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

    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (!this.instituicao.equals(voluntario.getInstituicao())) {
            System.out.println("O voluntário não pertence à mesma instituição da barraca.");
            return false;
        }

        if (voluntario.getBarracaAssociada() != null) {
            System.out.println("O voluntário já está associado a outra barraca.");
            return false;
        }

        voluntarios.add(voluntario);
        voluntario.setBarracaAssociada(this);
        return true;
    }

    public void adicionarStock(String nomeProduto, int quantidade) {
        for (StockProdutos sp : stock) {
            if (sp.getNome().equals(nomeProduto)) {
                sp.setQuantidade(sp.getQuantidade() + quantidade);
                System.out.println("Produto " + nomeProduto + " atualizado no estoque.");
                return;
            }
        }

        StockProdutos novoProduto = new StockProdutos(nomeProduto, 0, quantidade);
        stock.add(novoProduto);
        System.out.println("Produto " + nomeProduto + " adicionado ao estoque.");
    }

    public double exportarVendas() {
        double totalVendas = 0.0;

        for (Voluntario v : voluntarios) {
            if (v instanceof VoluntarioVendas) {
                VoluntarioVendas vv = (VoluntarioVendas) v;
                for (VendaProdutos venda : vv.getTodasVendas()) {
                    totalVendas += venda.getValorTotal(); // Somar o valor de cada venda
                }
            }
        }

        return totalVendas;
    }

    public int exportarStockTotal() {
        int totalStock = 0;

        // Para pegar o total de stock final, soma a quantidade de todos os produtos no stock
        for (StockProdutos sp : stock) {
            totalStock += sp.getQuantidade();
        }

        return totalStock;
    }

    private int calcularTotalProdutos() {
        int total = 0;
        for (StockProdutos sp : stock) {
            total += sp.getQuantidade();
        }
        return total;
    }

    public String classificar() {
        int stockFinal = exportarStockTotal();
        if (stockFinal > 100) {
            return "Bronze";
        } else if (stockFinal >= 50 && stockFinal <= 100) {
            return "Prata";
        } else {
            return "Ouro";
        }
    }
}
