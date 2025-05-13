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

    public boolean adicionarVoluntario(Voluntario voluntario) {
        // Valida se o voluntário pertence à mesma instituição
        if (!this.instituicao.equals(voluntario.getInstituicao())) {
            System.out.println("Erro: O voluntário não pertence à mesma instituição da barraca.");
            return false;
        }

        // Valida se o voluntário já está associado a outra barraca
        if (voluntario.getBarracaAssociada() != null) {
            System.out.println("Erro: O voluntário já está associado a outra barraca.");
            return false;
        }

        voluntarios.add(voluntario);
        voluntario.setBarracaAssociada(this);
        return true;
    }

    public void adicionarStock(String nomeProduto, int quantidade) {
        // Tenta atualizar o stock existente ou adicionar um novo produto
        for (StockProdutos sp : stock) {
            if (sp.getNome().equals(nomeProduto)) {
                sp.setQuantidade(sp.getQuantidade() + quantidade);
                return;
            }
        }

        // Se o produto não existir no stock, adiciona um novo
        StockProdutos novoProduto = new StockProdutos(nomeProduto, 0, quantidade);
        stock.add(novoProduto);
    }

    public double exportarVendas() {
        double totalVendas = 0.0;

        // Somando o valor das vendas de todos os voluntários do tipo VoluntarioVendas
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

        // Somando o total de produtos no stock
        for (StockProdutos sp : stock) {
            totalStock += sp.getQuantidade();
        }

        return totalStock;
    }

    // Calcula o total de produtos no stock (sem duplicação com exportarStockTotal)
    private int calcularTotalProdutos() {
        int total = 0;
        for (StockProdutos sp : stock) {
            total += sp.getQuantidade();
        }
        return total;
    }

    // Classifica a barraca com base no stock final
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
}
