package org.example.model;

/**
 * Representa um voluntário, com dados pessoais e associação a uma barraca e instituição.
 * Classe abstrata para ser estendida por tipos concretos de voluntários.
 */
public abstract class Voluntario implements Comparable<Voluntario> {
    private String nome;
    private int numeroAluno;
    private String curso;
    private String senha;
    private Barraca barracaAssociada;
    private Instituicao instituicao;

    /**
     * Construtor completo.
     *
     * @param nome Nome do voluntário.
     * @param numeroAluno Número do aluno.
     * @param instituicao Instituição associada.
     * @param curso Curso do voluntário.
     * @param senha Senha de acesso.
     */
    public Voluntario(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.instituicao = instituicao;
        this.curso = curso;
        this.senha = senha;
        this.barracaAssociada = null;
    }

    /** Construtor por omissão. */
    public Voluntario() {
        this.nome = "";
        this.numeroAluno = 0;
        this.instituicao = null;
        this.curso = "";
        this.senha = "";
        this.barracaAssociada = null;
    }

    /**
     * Construtor de cópia.
     *
     * @param outro Outro voluntário para copiar os dados.
     */
    public Voluntario(Voluntario outro) {
        this.nome = outro.nome;
        this.numeroAluno = outro.numeroAluno;
        this.instituicao = outro.instituicao;
        this.curso = outro.curso;
        this.senha = outro.senha;
        this.barracaAssociada = outro.barracaAssociada;
    }

    /** Retorna o nome do voluntário. */
    public String getNome() {
        return this.nome;
    }

    /** Retorna o número do aluno do voluntário. */
    public int getNumeroAluno() {
        return this.numeroAluno;
    }

    /** Retorna o curso do voluntário. */
    public String getCurso() {
        return this.curso;
    }

    /** Retorna a senha do voluntário. */
    public String getSenha() {
        return this.senha;
    }

    /** Retorna a barraca associada ao voluntário, ou null se nenhuma. */
    public Barraca getBarracaAssociada() {
        return this.barracaAssociada;
    }

    /** Retorna a instituição associada ao voluntário, ou null se nenhuma. */
    public Instituicao getInstituicao() {
        return this.instituicao;
    }

    /** Define o nome do voluntário. */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** Define o número do aluno do voluntário. */
    public void setNumeroAluno(int numeroAluno) {
        this.numeroAluno = numeroAluno;
    }

    /** Define o curso do voluntário. */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /** Define a senha do voluntário. */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /** Define a barraca associada ao voluntário. */
    public void setBarracaAssociada(Barraca barraca) {
        this.barracaAssociada = barraca;
    }

    /** Define a instituição associada ao voluntário. */
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    /**
     * Compara voluntários pelo número de aluno e depois pelo nome.
     */
    @Override
    public int compareTo(Voluntario outro) {
        int numeroCompare = Integer.compare(this.numeroAluno, outro.numeroAluno);
        if (numeroCompare != 0) return numeroCompare;
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    /**
     * Verifica igualdade entre voluntários considerando número do aluno, nome e instituição.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario that = (Voluntario) o;
        return this.numeroAluno == that.numeroAluno &&
                this.nome.equals(that.nome) &&
                this.instituicao.equals(that.instituicao);
    }

    /**
     * Representação textual do voluntário, ocultando a senha e tratando null.
     */
    @Override
    public String toString() {
        return "Voluntario{nome='" + nome + "', numeroAluno=" + numeroAluno + ", curso='" + curso + "', senha=[protegida], barracaAssociada=" +
                (barracaAssociada == null ? "N/A" : barracaAssociada.getNome()) + ", instituicao=" +
                (instituicao == null ? "N/A" : instituicao.getNome()) + "}";
    }
}
