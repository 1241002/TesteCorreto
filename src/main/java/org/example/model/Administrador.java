package org.example.model;

public class Administrador implements Comparable<Administrador> {
    // Variáveis de instância
    private String nome;
    private int numero;
    private String senha;
    private String curso;

    // Construtor completo
    public Administrador(String nome, int numero, String senha, String curso) {
        this.nome = nome;
        this.numero = numero;
        this.senha = senha;
        this.curso = curso;
    }

    // Construtor vazio
    public Administrador() {
        this.nome = "";
        this.numero = 0;
        this.senha = "";
        this.curso = "";
    }

    // Construtor de cópia
    public Administrador(Administrador admin) {
        this.nome = admin.nome;
        this.numero = admin.numero;
        this.senha = admin.senha;
        this.curso = admin.curso;
    }

    // Seletores
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

    // Modificadores
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrador)) return false;
        Administrador that = (Administrador) o;
        return this.numero == that.numero &&
                (this.nome == null ? that.nome == null : this.nome.equalsIgnoreCase(that.nome)) &&
                (this.curso == null ? that.curso == null : this.curso.equalsIgnoreCase(that.curso)) &&
                (this.senha == null ? that.senha == null : this.senha.equals(that.senha));
    }

    // Implementação de Comparable (ordena por número)
    @Override
    public int compareTo(Administrador outro) {
        return Integer.compare(this.numero, outro.numero);
    }

    // Método toString
    @Override
    public String toString() {
        return "Administrador{" +
                "nome='" + nome + '\'' +
                ", numero=" + numero +
                ", curso='" + curso + '\'' +
                ", senha='[protegida]'" +
                '}';
    }
}