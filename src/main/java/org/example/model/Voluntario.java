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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroAluno() {
        return numeroAluno;
    }

    public void setNumeroAluno(int numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    public Federacao getFederacao() {
        return federacao;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }
}
