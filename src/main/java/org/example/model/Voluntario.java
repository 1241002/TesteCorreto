package org.example.model;

import java.util.Objects;

public abstract class Voluntario {
    private String nome;
    private int numeroAluno;
    private String curso;
    private String senha;
    private Barraca barracaAssociada;
    private Instituicao instituicao;

    public Voluntario(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.instituicao = instituicao;
        this.curso = curso;
        this.senha = senha;
        this.barracaAssociada = null;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroAluno() {
        return numeroAluno;
    }

    public String getCurso() {
        return curso;
    }

    public String getSenha() {
        return senha;
    }

    public Barraca getBarracaAssociada() {
        return barracaAssociada;
    }

    public void setBarracaAssociada(Barraca barraca) {
        this.barracaAssociada = barraca;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario that = (Voluntario) o;
        return numeroAluno == that.numeroAluno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroAluno);
    }

    @Override
    public String toString() {
        return "Voluntario{" +
                "nome='" + nome + '\'' +
                ", numeroAluno=" + numeroAluno +
                ", curso='" + curso + '\'' +
                '}';
    }
}