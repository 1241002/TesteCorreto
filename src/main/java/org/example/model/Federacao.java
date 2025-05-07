package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Federacao {
    private String nome;
    private final List<Voluntario> listaVoluntarios; // Lista de voluntários
    private final List<Produto> lstProdutos;

    public Federacao(String nome) {
        this.nome = nome;
        this.listaVoluntarios = new ArrayList<>();  // Inicializando a lista de voluntários
        this.lstProdutos = new ArrayList<>();
    }

    // Metodo para adicionar um voluntário à lista (agora usando o número de aluno como int)
    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (!listaContemVoluntario(voluntario.getNumeroAluno())) {  // Verifica com base no número
            listaVoluntarios.add(voluntario);
            return true;
        } else {
            return false;
        }
    }

    // Verificar se o voluntário já está na lista, usando o número de aluno como int
    public boolean listaContemVoluntario(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario.getNumeroAluno() == numeroAluno) {  // Comparando número de aluno (agora int)
                return true;
            }
        }
        return false;
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

    public VoluntarioVendas getVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario instanceof VoluntarioVendas && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioVendas) voluntario;
            }
        }
        return null;
    }


    // Completar com outras funcionalidades

    @Override
    public String toString() {
        return "Federacao{" +
                "nome='" + nome + '\'' +
                ", total de voluntários=" + listaVoluntarios.size() +
                ", total de produtos=" + lstProdutos.size() +
                '}';
    }

}
