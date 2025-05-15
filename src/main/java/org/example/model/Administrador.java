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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrador)) return false;
        Administrador that = (Administrador) o;
        return numero == that.numero &&
                nome.equalsIgnoreCase(that.nome) &&
                curso.equalsIgnoreCase(that.curso);
    }

    @Override
    public int hashCode() {
        int result = nome.toLowerCase().hashCode();
        result = 31 * result + numero;
        result = 31 * result + curso.toLowerCase().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nome='" + nome + '\'' +
                ", numero=" + numero +
                ", curso='" + curso + '\'' +
                '}';
    }
}