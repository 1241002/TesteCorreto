package org.example.model;

import java.util.Objects;

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

    public int getNumeroAluno() {
        return numeroAluno;
    }

    public Federacao getFederacao() {
        return federacao;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Voluntario that = (Voluntario) o;
        return Objects.equals(nome, that.nome) && Objects.equals(numeroAluno, that.numeroAluno) && Objects.equals(federacao, that.federacao);
    }

    @Override
    public String toString() {
        return "Voluntario{" +
                "nome='" + nome + '\'' +
                ", numeroAluno=" + numeroAluno +
                '}';
    }

}
