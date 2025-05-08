package org.example.model;

public class Voluntario {
    private String nome;
    private int numeroAluno;
    private Federacao federacao;

    public Voluntario(String nome, int numeroAluno, Federacao federacao) {
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.federacao = federacao;
    }
}
