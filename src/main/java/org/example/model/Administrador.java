package org.example.model;

public class Administrador {
    private String nome;
    private int numero;
    private String senha;
    private String curso;

    public Administrador(String nome, int numero, String senha, String curso) {
        this.nome = nome;
        this.numero = numero;
        this.senha = senha;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public String getSenha() {
        return senha;
    }

    public String getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Número: " + numero + ", Curso: " + curso;
    }
}