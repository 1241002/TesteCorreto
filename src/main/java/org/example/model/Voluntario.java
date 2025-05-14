package org.example.model;

public abstract class Voluntario {
    private String nome;
    private int numeroAluno;
    private Instituicao instituicao;
    private Barraca barracaAssociada;
    private String curso; // Novo atributo

    private static final String NOME_POR_OMISSAO = "Sem nome";
    private static final int NUMERO_ALUNO_POR_OMISSAO = 0;
    private static final Instituicao INSTITUICAO_POR_OMISSAO = new Instituicao("");
    private static final String CURSO_POR_OMISSAO = "Sem curso";

    public Voluntario(String nome, int numeroAluno, Instituicao instituicao, String curso) {
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.instituicao = instituicao;
        this.curso = curso;
        this.barracaAssociada = null;
    }

    public Voluntario() {
        this.nome = NOME_POR_OMISSAO;
        this.numeroAluno = NUMERO_ALUNO_POR_OMISSAO;
        this.instituicao = INSTITUICAO_POR_OMISSAO;
        this.curso = CURSO_POR_OMISSAO;
        this.barracaAssociada = null;
    }

    public Voluntario(Voluntario v) {
        this.nome = v.nome;
        this.numeroAluno = v.numeroAluno;
        this.instituicao = v.instituicao;
        this.curso = v.curso;
        this.barracaAssociada = v.barracaAssociada;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getNumeroAluno() { return numeroAluno; }
    public void setNumeroAluno(int numeroAluno) { this.numeroAluno = numeroAluno; }
    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) { this.instituicao = instituicao; }
    public Barraca getBarracaAssociada() { return barracaAssociada; }
    public void setBarracaAssociada(Barraca barracaAssociada) { this.barracaAssociada = barracaAssociada; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Voluntario: ");
        sb.append("\nNome='").append(nome);
        sb.append("\nNumero de Aluno=").append(numeroAluno);
        sb.append("\nCurso='").append(curso);
        sb.append("\nInstituicao=").append(instituicao);
        sb.append("\nBarraca Associada=").append(barracaAssociada != null ? barracaAssociada.getNome() : "Nenhuma");
        return sb.toString();
    }
}