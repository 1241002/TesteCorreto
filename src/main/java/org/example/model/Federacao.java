package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Federacao {
    private String nome;
    private final List<Produto> lstProdutos;
    // Completar

    public Federacao(String nome) {
        this.nome = nome;
        this.lstProdutos = new ArrayList<>();
    }

    public boolean adicionarProduto(Produto produto) {
        if (!listaContemProduto(produto.getNome())) {
            lstProdutos.add(new Produto(produto));
            return true;
        } else {
            return false;
        }
    }

    public boolean listaContemProduto(String nomeProduto) {
        for(Produto produto : lstProdutos){
            if(produto.getNome().equals(nomeProduto)){
                return true;
            }
        }
        return false;
    }

    // Completar com outras funcionalidades


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Federacao: ");
        sb.append("nome='").append(nome).append("\n");
        sb.append("Lista de Produtos:");
        if (lstProdutos.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for (Produto produto : lstProdutos) {
                sb.append("\n\t- ").append(produto);
            }
        }
        // Completar
        return sb.toString();
    }
}
    
    
