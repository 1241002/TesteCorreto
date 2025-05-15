package org.example.model;

public class VoluntarioStock extends Voluntario {
    public VoluntarioStock(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        super(nome, numeroAluno, instituicao, curso, senha);
    }

    public VoluntarioStock() {
        super();
    }

    public VoluntarioStock(VoluntarioStock outro) {
        super(outro);
    }

    public void adicionarProdutoAoStock(String nomeProduto, double precoUnitario, int quantidade) {
        Barraca barraca = this.getBarracaAssociada();
        barraca.adicionarStock(new StockProdutos(nomeProduto, precoUnitario, quantidade));
    }

    public void reporProduto(String nomeProduto, int quantidade) {
        Barraca barraca = this.getBarracaAssociada();
        double precoUnitario = 0.0;
        for (Produto produto : this.getInstituicao().getLstProdutos()) {
            if (produto.getNome().equals(nomeProduto)) {
                precoUnitario = produto.getPrecoUnitario();
                break;
            }
        }
        barraca.adicionarStock(new StockProdutos(nomeProduto, precoUnitario, quantidade));
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "VoluntarioStock{" + super.toString() + "}";
    }
}