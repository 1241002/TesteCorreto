package org.example.model;

public abstract class Voluntario implements Comparable<Voluntario> {
    // Variáveis de instância
    private String nome;
    private int numeroAluno;
    private String curso;
    private String senha;
    private Barraca barracaAssociada;
    private Instituicao instituicao;

    // Construtor completo
    public Voluntario(String nome, int numeroAluno, Instituicao instituicao, String curso, String senha) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        if (numeroAluno <= 0) {
            throw new IllegalArgumentException("Número de aluno deve ser positivo");
        }
        if (instituicao == null) {
            throw new IllegalArgumentException("Instituição não pode ser nula");
        }
        if (curso == null) {
            throw new IllegalArgumentException("Curso não pode ser nulo");
        }
        if (senha == null) {
            throw new IllegalArgumentException("Senha não pode ser nula");
        }
        this.nome = nome;
        this.numeroAluno = numeroAluno;
        this.instituicao = instituicao;
        this.curso = curso;
        this.senha = senha;
        this.barracaAssociada = null;
    }

    // Construtor vazio
    public Voluntario() {
        this.nome = "";
        this.numeroAluno = 0;
        this.instituicao = null;
        this.curso = "";
        this.senha = "";
        this.barracaAssociada = null;
    }

    // Construtor de cópia
    public Voluntario(Voluntario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Voluntário não pode ser nulo");
        }
        this.nome = outro.nome;
        this.numeroAluno = outro.numeroAluno;
        this.instituicao = outro.instituicao; // Referência partilhada (agregação)
        this.curso = outro.curso;
        this.senha = outro.senha;
        this.barracaAssociada = outro.barracaAssociada; // Referência partilhada
    }

    // Seletores
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

    // Modificadores
    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public void setNumeroAluno(int numeroAluno) {
        if (numeroAluno <= 0) {
            throw new IllegalArgumentException("Número de aluno deve ser positivo");
        }
        this.numeroAluno = numeroAluno;
    }

    public void setCurso(String curso) {
        if (curso == null) {
            throw new IllegalArgumentException("Curso não pode ser nulo");
        }
        this.curso = curso;
    }

    public void setSenha(String senha) {
        if (senha == null) {
            throw new IllegalArgumentException("Senha não pode ser nula");
        }
        this.senha = senha;
    }

    public void setBarracaAssociada(Barraca barraca) {
        this.barracaAssociada = barraca;
    }

    public void setInstituicao(Instituicao instituicao) {
        if (instituicao == null) {
            throw new IllegalArgumentException("Instituição não pode ser nula");
        }
        this.instituicao = instituicao;
    }

    // Implementação de Comparable (ordena por numeroAluno)
    @Override
    public int compareTo(Voluntario outro) {
        return Integer.compare(this.numeroAluno, outro.numeroAluno);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario that = (Voluntario) o;
        return this.numeroAluno == that.numeroAluno &&
                (this.nome == null ? that.nome == null : this.nome.equalsIgnoreCase(that.nome)) &&
                (this.instituicao == null ? that.instituicao == null : this.instituicao.equals(that.instituicao));
    }

    // Método toString
    @Override
    public String toString() {
        return "Voluntario{" +
                "nome='" + (nome == null ? "" : nome) + '\'' +
                ", numeroAluno=" + numeroAluno +
                ", curso='" + (curso == null ? "" : curso) + '\'' +
                ", senha=[protegida]" +
                ", barracaAssociada=" + (barracaAssociada == null ? "N/A" : barracaAssociada.getNome()) +
                ", instituicao=" + (instituicao == null ? "N/A" : instituicao.getNome()) +
                '}';
    }
}