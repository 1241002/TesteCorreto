package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VoluntarioVendas extends Voluntario implements IVendasVoluntarios {
    // Variáveis de instância
    private final List<VendaProdutos> vendasProdutos;

    // Construtor completo
    public VoluntarioVendas(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        super(nome, numeroAluno, instituicao, curso, senha);
        this.vendasProdutos = new ArrayList<>();
    }

    // Construtor vazio
    public VoluntarioVendas() {
        super();
        this.vendasProdutos = new ArrayList<>();
    }

    // Construtor de cópia
    public VoluntarioVendas(VoluntarioVendas outro) {
        super(outro);
        if (outro == null) {
            throw new IllegalArgumentException("VoluntárioVendas não pode ser nulo");
        }
        this.vendasProdutos = new ArrayList<>(outro.vendasProdutos);
    }

    // Métodos de instância
    public boolean registarVenda(String nomeProduto, int quantidade) {
        if (nomeProduto == null) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        Barraca barraca = this.getBarracaAssociada();
        if (barraca == null) {
            throw new IllegalStateException("Voluntário não está associado a nenhuma barraca");
        }
        Produto produto = this.buscarProdutoPorNome(nomeProduto);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não registrado na instituição");
        }
        for (StockProdutos stock : barraca.getStock()) {
            if (stock.getNome() != null && stock.getNome().equalsIgnoreCase(nomeProduto)) {
                if (Double.compare(stock.getPrecoUnitario(), produto.getPrecoUnitario()) != 0) {
                    throw new IllegalArgumentException("Preço unitário do estoque não corresponde ao registrado no produto");
                }
                if (barraca.reduzirStock(nomeProduto, quantidade)) {
                    double valorTotal = quantidade * stock.getPrecoUnitario();
                    this.vendasProdutos.add(new VendaProdutos(nomeProduto, quantidade, valorTotal));
                    return true;
                } else {
                    throw new IllegalStateException("Quantidade insuficiente em stock");
                }
            }
        }
        throw new IllegalArgumentException("Produto não encontrado no estoque da barraca");
    }

    public boolean removerUltimaVenda() {
        if (!this.vendasProdutos.isEmpty()) {
            VendaProdutos ultimaVenda = this.vendasProdutos.remove(this.vendasProdutos.size() - 1);
            Barraca barraca = this.getBarracaAssociada();
            if (barraca != null) {
                barraca.adicionarStock(new StockProdutos(ultimaVenda.getNomeProduto(), ultimaVenda.getValorTotal() / ultimaVenda.getQuantidade(), ultimaVenda.getQuantidade()));
            }
            return true;
        }
        return false;
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
        String categoriaInfo = this.verificarCategoria();
        // Como System.out.println não está nos tópicos, retornamos a string
        // A interface pode esperar que o método seja void, então deixamos a lógica aqui
        // Se necessário, a string pode ser usada pelo chamador
    }

    public String verificarCategoria() {
        return "Voluntário: " + this.getNome() +
                ", Total de Vendas: " + String.format("%.2f€", this.getTotalVendas()) +
                ", Classificação: " + this.getCategoria();
    }

    private Produto buscarProdutoPorNome(String nomeProduto) {
        if (nomeProduto == null || this.getInstituicao() == null) {
            return null;
        }
        for (Produto produto : this.getInstituicao().getLstProdutos()) {
            if (produto.getNome() != null && produto.getNome().equalsIgnoreCase(nomeProduto)) {
                return produto;
            }
        }
        return null;
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoluntarioVendas)) return false;
        VoluntarioVendas that = (VoluntarioVendas) o;
        return super.equals(o) && this.vendasProdutos.equals(that.vendasProdutos);
    }

    // Método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VoluntarioVendas{");
        sb.append(super.toString());
        sb.append(", totalVendas=").append(String.format("%.2f€", this.getTotalVendas()));
        sb.append(", vendas=");
        if (this.vendasProdutos.isEmpty()) {
            sb.append("nenhuma");
        } else {
            sb.append(this.vendasProdutos.size()).append(" vendas");
        }
        sb.append('}');
        return sb.toString();
    }
}