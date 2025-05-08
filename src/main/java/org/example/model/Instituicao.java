package org.example.model;


public class Instituicao {
    private String nome;

    public Instituicao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Instituicao");
        sb.append("\nNome: ").append(nome);
        sb.append("");
        return sb.toString();
    }
}
