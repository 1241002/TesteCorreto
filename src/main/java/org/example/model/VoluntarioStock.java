package org.example.model;

public class VoluntarioStock extends Voluntario {
    // Construtor completo
    public VoluntarioStock(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        super(nome, numeroAluno, instituicao, curso, senha);
    }

    // Construtor vazio
    public VoluntarioStock() {
        super();
    }

    // Construtor de cópia
    public VoluntarioStock(VoluntarioStock outro) {
        super(outro);
        if (outro == null) {
            throw new IllegalArgumentException("VoluntárioStock não pode ser nulo");
        }
    }

    // Métodos de instância
    public void adicionarProdutoAoStock(String nomeProduto, double precoUnitario, int quantidade) {
        if (nomeProduto == null) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo");
        }
        Barraca barraca = this.getBarracaAssociada();
        if (barraca == null) {
            throw new IllegalStateException("Voluntário não está associado a nenhuma barraca");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        Produto produto = this.buscarProdutoPorNome(nomeProduto);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não registrado na instituição");
        }
        if (Double.compare(precoUnitario, produto.getPrecoUnitario()) != 0) {
            throw new IllegalArgumentException("Preço unitário não corresponde ao registrado no produto");
        }
        for (StockProdutos sp : barraca.getStock()) {
            if (sp.getNome() != null && sp.getNome().equalsIgnoreCase(nomeProduto)) {
                throw new IllegalStateException("Produto já existe no stock; use a opção de repor stock");
            }
        }
        barraca.adicionarStock(new StockProdutos(nomeProduto, precoUnitario, quantidade));
    }

    public void reporProduto(String nomeProduto, int quantidade) {
        if (nomeProduto == null) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo");
        }
        Barraca barraca = this.getBarracaAssociada();
        if (barraca == null) {
            throw new IllegalStateException("Voluntário não está associado a nenhuma barraca");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        Produto produto = this.buscarProdutoPorNome(nomeProduto);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não registrado na instituição");
        }
        boolean encontrado = false;
        for (StockProdutos sp : barraca.getStock()) {
            if (sp.getNome() != null && sp.getNome().equalsIgnoreCase(nomeProduto)) {
                sp.setQuantidade(sp.getQuantidade() + quantidade);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            barraca.adicionarStock(new StockProdutos(nomeProduto, produto.getPrecoUnitario(), quantidade));
        }
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
        if (!(o instanceof VoluntarioStock)) return false;
        return super.equals(o); // Reutiliza equals da superclasse
    }

    // Método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VoluntarioStock{");
        sb.append(super.toString());
        Barraca barraca = this.getBarracaAssociada();
        if (barraca != null) {
            sb.append(", barracaAssociada=").append(barraca.getNome());
        } else {
            sb.append(", barracaAssociada=N/A");
        }
        sb.append('}');
        return sb.toString();
    }
}