package org.example.model;

public class VoluntarioStock extends Voluntario {
    public VoluntarioStock(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        super(nome, numeroAluno, instituicao, curso, senha);
    }

    public VoluntarioStock(VoluntarioStock vs) {
        super(vs.getNome(), vs.getNumeroAluno(), vs.getInstituicao(), vs.getCurso(), vs.getSenha());
    }

    public VoluntarioStock() {
        super("", 0, null, "", "");
    }

    public void adicionarProdutoAoStock(String nomeProduto, double precoUnitario, int quantidade) {
        Barraca barraca = getBarracaAssociada();
        if (barraca == null) {
            throw new IllegalStateException("Voluntário não está associado a nenhuma barraca.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        Produto produto = buscarProdutoPorNome(nomeProduto);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não registrado na instituição.");
        }
        if (Double.compare(precoUnitario, produto.getPrecoUnitario()) != 0) {
            throw new IllegalArgumentException("Preço unitário não corresponde ao registrado no produto.");
        }
        for (StockProdutos sp : barraca.getStock()) {
            if (sp.getNome().equalsIgnoreCase(nomeProduto)) {
                throw new IllegalStateException("Produto já existe no stock. Use a opção de repor stock.");
            }
        }
        barraca.adicionarStock(new StockProdutos(nomeProduto, precoUnitario, quantidade));
        System.out.println("Produto " + nomeProduto + " adicionado ao stock da barraca " + barraca.getNome() + ".");
    }

    public void reporProduto(String nomeProduto, int quantidade) {
        Barraca barraca = getBarracaAssociada();
        if (barraca == null) {
            throw new IllegalStateException("Voluntário não está associado a nenhuma barraca.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        Produto produto = buscarProdutoPorNome(nomeProduto);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não registrado na instituição.");
        }
        boolean encontrado = false;
        for (StockProdutos sp : barraca.getStock()) {
            if (sp.getNome().equalsIgnoreCase(nomeProduto)) {
                sp.setQuantidade(sp.getQuantidade() + quantidade);
                encontrado = true;
                System.out.println("Estoque de " + nomeProduto + " atualizado com " + quantidade + " unidades na barraca " + barraca.getNome() + ".");
                break;
            }
        }
        if (!encontrado) {
            barraca.adicionarStock(new StockProdutos(nomeProduto, produto.getPrecoUnitario(), quantidade));
            System.out.println("Produto " + nomeProduto + " adicionado ao stock da barraca " + barraca.getNome() + " com " + quantidade + " unidades.");
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Voluntario de Stock: ").append(super.toString());
        Barraca barraca = getBarracaAssociada();
        if (barraca != null) {
            sb.append("\nBarraca Associada: ").append(barraca.getNome());
        }
        return sb.toString();
    }
}