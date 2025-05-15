package org.example.model;

public abstract class Voluntario implements Comparable<Voluntario> {
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

    public Voluntario() {
        this.nome = "";
        this.numeroAluno = 0;
        this.instituicao = null;
        this.curso = "";
        this.senha = "";
        this.barracaAssociada = null;
    }

    public Voluntario(Voluntario outro) {
        this.nome = outro.nome;
        this.numeroAluno = outro.numeroAluno;
        this.instituicao = outro.instituicao;
        this.curso = outro.curso;
        this.senha = outro.senha;
        this.barracaAssociada = outro.barracaAssociada;
    }

    public String getNome() {
        return this.nome;
    }

    public int getNumeroAluno() {
        return this.numeroAluno;
    }

    public String getCurso() {
        return this.curso;
    }

    public String getSenha() {
        return this.senha;
    }

    public Barraca getBarracaAssociada() {
        return this.barracaAssociada;
    }

    public Instituicao getInstituicao() {
        return this.instituicao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroAluno(int numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setBarracaAssociada(Barraca barraca) {
        this.barracaAssociada = barraca;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public int compareTo(Voluntario outro) {
        int numeroCompare = Integer.compare(this.numeroAluno, outro.numeroAluno);
        if (numeroCompare != 0) return numeroCompare;
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario that = (Voluntario) o;
        return this.numeroAluno == that.numeroAluno &&
                this.nome.equals(that.nome) &&
                this.instituicao.equals(that.instituicao);
    }

    @Override
    public String toString() {
        return "Voluntario{nome='" + nome + "', numeroAluno=" + numeroAluno + ", curso='" + curso + "', senha=[protegida], barracaAssociada=" +
                (barracaAssociada == null ? "N/A" : barracaAssociada.getNome()) + ", instituicao=" +
                (instituicao == null ? "N/A" : instituicao.getNome()) + "}";
    }
}