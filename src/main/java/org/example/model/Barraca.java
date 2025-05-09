package org.example.model;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Barraca {
    private String nome;
    private Instituicao instituicao;  // Alteração: Usando Instituicao em vez de String
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

    public Instituicao getInstituicao() {  // Alteração: Metodo que retorna Instituicao
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
        // Verifica se a instituição do voluntário é a mesma da barraca
        if (!this.instituicao.equals(voluntario.getInstituicao())) {
            System.out.println("O voluntário não pertence à mesma instituição da barraca.");
            return false;
        }

        // Verifica se o voluntário já está associado a outra barraca
        if (voluntario.getBarracaAssociada() != null) {
            System.out.println("O voluntário já está associado a outra barraca.");
            return false;
        }

        // Adiciona o voluntário à lista da barraca
        voluntarios.add(voluntario);
        voluntario.setBarracaAssociada(this);  // Associa a barraca ao voluntário

        // A verificação de pelo menos 2 voluntários só deve ocorrer após o segundo voluntário ser adicionado
        return true; // Retorna true, sem verificar o número de voluntários agora
    }



    public void adicionarStock(String nomeProduto, int quantidade) {
        // Verifica se o produto já existe no estoque
        for (StockProdutos sp : stock) {
            if (sp.getNome().equals(nomeProduto)) {
                // Se o produto já existe, apenas atualiza a quantidade
                sp.setQuantidade(sp.getQuantidade() + quantidade);
                System.out.println("Produto " + nomeProduto + " atualizado no estoque.");
                return;
            }
        }

        // Se o produto não existe no estoque, adiciona um novo
        StockProdutos novoProduto = new StockProdutos(nomeProduto, 0, quantidade);  // preço é zero, pois não foi solicitado
        stock.add(novoProduto);
        System.out.println("Produto " + nomeProduto + " adicionado ao estoque.");
    }


}

