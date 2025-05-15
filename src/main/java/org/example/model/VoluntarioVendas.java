package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VoluntarioVendas extends Voluntario implements IVendasVoluntarios {
    private final List<VendaProdutos> vendasProdutos;

    public VoluntarioVendas(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        super(nome, numeroAluno, instituicao, curso, senha);
        this.vendasProdutos = new ArrayList<>();
    }

    public VoluntarioVendas() {
        super();
        this.vendasProdutos = new ArrayList<>();
    }

    public VoluntarioVendas(VoluntarioVendas outro) {
        super(outro);
        this.vendasProdutos = new ArrayList<>(outro.vendasProdutos);
    }

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

    public void removerUltimaVenda() {
        if (!this.vendasProdutos.isEmpty()) {
            VendaProdutos ultimaVenda = this.vendasProdutos.remove(this.vendasProdutos.size() - 1);
            Barraca barraca = this.getBarracaAssociada();
            barraca.adicionarStock(new StockProdutos(ultimaVenda.getNomeProduto(), ultimaVenda.getValorTotal() / ultimaVenda.getQuantidade(), ultimaVenda.getQuantidade()));
        }
    }

    public double getTotalVendas() {
        double total = 0.0;
        for (VendaProdutos venda : this.vendasProdutos) {
            total += venda.getValorTotal();
        }
        return total;
    }

    public List<VendaProdutos> getTodasVendas() {
        return new ArrayList<>(this.vendasProdutos);
    }

    public void limparVendas() {
        this.vendasProdutos.clear();
    }

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
        // Returns string for caller to handle
    }

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
        return "VoluntarioVendas{" + super.toString() + ", totalVendas=" + String.format("%.2f€", this.getTotalVendas()) +
                ", vendas=" + (this.vendasProdutos.isEmpty() ? "nenhuma" : this.vendasProdutos.size() + " vendas") + "}";
    }
}