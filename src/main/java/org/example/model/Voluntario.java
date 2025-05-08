package org.example.model;

public abstract class Voluntario implements IVendasVoluntarios {
    private String nome;
    private int numeroAluno;
    private Instituicao instituicao;

    private static final String NOME_POR_OMISSAO = "Sem nome";
    private static final int NUMERO_ALUNO_POR_OMISSAO = 0;
    private static final Instituicao INSTITUICAO_POR_OMISSAO = new Instituicao("");

    public Voluntario(String nome, int numeroAluno, Instituicao instituicao) {
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.instituicao = instituicao;
    }

    public Voluntario() {
        this.nome = NOME_POR_OMISSAO;
        this.numeroAluno = NUMERO_ALUNO_POR_OMISSAO;
        this.instituicao = INSTITUICAO_POR_OMISSAO;

    }

    public Voluntario(Voluntario v) {
        this.nome = v.nome;
        this.numeroAluno = v.numeroAluno;
        this.instituicao = v.instituicao;
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

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Voluntario: ");
        sb.append("\nNome='").append(nome);
        sb.append("\nNumero de Aluno=").append(numeroAluno);
        sb.append("\nInstituicao").append(instituicao);
        sb.append("");
        return sb.toString();
    }

}
