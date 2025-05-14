package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VoluntarioVendas extends Voluntario implements IVendasVoluntarios {
    private List<VendaProdutos> vendasProdutos;

    public VoluntarioVendas(String nome, int numeroAluno, Instituicao instituicao) {
        super(nome, numeroAluno, instituicao);
        this.vendasProdutos = new ArrayList<>();
    }

    public VoluntarioVendas(VoluntarioVendas vv) {
        super(vv);
        this.vendasProdutos = new ArrayList<>(vv.vendasProdutos);
    }

    public VoluntarioVendas() {
        super();
        this.vendasProdutos = new ArrayList<>();
    }

    public boolean registarVenda(String nomeProduto, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return false;
        }

        Barraca barraca = getBarracaAssociada();
        if (barraca == null) {
            System.out.println("O voluntário não está associado a nenhuma barraca.");
            return false;
        }

        for (StockProdutos stock : barraca.getStock()) {
            if (stock.getNome().equalsIgnoreCase(nomeProduto)) {
                if (stock.reduzirQuantidade(quantidade)) {
                    vendasProdutos.add(new VendaProdutos(nomeProduto, quantidade, stock.getPrecoUnitario()));
                    System.out.printf("Venda registada: %s - %d x %.2f€\n", nomeProduto, quantidade, stock.getPrecoUnitario());
                    return true;
                } else {
                    System.out.println("Quantidade insuficiente em stock.");
                    return false;
                }
            }
        }
        System.out.println("Produto não encontrado no estoque.");
        return false;
    }

    // Mantido para compatibilidade, mas não será usado diretamente
    public void registarVenda(String nomeProduto, int quantidade, double precoUnitario) {
        if (quantidade > 0 && precoUnitario > 0.0) {
            vendasProdutos.add(new VendaProdutos(nomeProduto, quantidade, precoUnitario));
        }
    }

    private Produto buscarProdutoPorNome(String nomeProduto) {
        for (Produto produto : getInstituicao().getLstProdutos()) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }

    public boolean removerUltimaVenda() {
        if (!vendasProdutos.isEmpty()) {
            vendasProdutos.remove(vendasProdutos.size() - 1);
            return true;
        }
        return false;
    }

    public double getTotalVendas() {
        double total = 0.0;
        for (VendaProdutos venda : vendasProdutos) {
            total += venda.getValorTotal();
        }
        return total;
    }

    public List<VendaProdutos> getTodasVendas() {
        return new ArrayList<>(vendasProdutos);
    }

    public void limparVendas() {
        vendasProdutos.clear();
    }

    public String getCategoria() {
        double total = getTotalVendas();
        if (total < 500) {
            return "Bronze";
        } else if (total <= 1000) {
            return "Prata";
        } else {
            return "Ouro";
        }
    }

    public void verificarEExibirCategoria() {
        System.out.println("Voluntário: " + getNome()
                + ", Total de Vendas: " + String.format("%.2f", getTotalVendas()) + "€"
                + ", Classificação: " + getCategoria());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("VoluntarioVendas: ").append(super.toString());
        sb.append("Total de Vendas: ").append(String.format("%.2f€", getTotalVendas())).append("\n");
        for (VendaProdutos v : vendasProdutos) {
            sb.append(" - ").append(v.toString()).append("\n");
        }
        return sb.toString();
    }
}