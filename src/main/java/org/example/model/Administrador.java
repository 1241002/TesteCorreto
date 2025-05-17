package org.example.model;

/**
 * Representa um administrador da aplicação.
 * Um administrador tem nome, número de aluno, curso e senha.
 * Pode ser comparado por nome (ordem alfabética).
 */
public class Administrador implements Comparable<Administrador> {

    // Variáveis de instância
    private String nome;
    private int numero;
    private String senha;
    private String curso;

    /**
     * Construtor completo.
     *
     * @param nome  Nome do administrador.
     * @param numero Número do administrador (ID ou número de aluno).
     * @param senha  Senha do administrador.
     * @param curso  Curso do administrador.
     */
    public Administrador(String nome, int numero, String senha, String curso) {
        this.nome = nome;
        this.numero = numero;
        this.senha = senha;
        this.curso = curso;
    }

    /**
     * Construtor por omissão (sem dados).
     */
    public Administrador() {
        this.nome = "";
        this.numero = 0;
        this.senha = "";
        this.curso = "";
    }

    /**
     * Construtor de cópia.
     *
     * @param admin Administrador a copiar.
     */
    public Administrador(Administrador admin) {
        this.nome = admin.nome;
        this.numero = numero;
        this.senha = admin.senha;
        this.curso = admin.curso;
    }

    /**
     * Obtém o nome do administrador.
     *
     * @return Nome do administrador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o número do administrador.
     *
     * @return Número do administrador.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtém a senha do administrador.
     *
     * @return Senha do administrador.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Obtém o curso do administrador.
     *
     * @return Curso do administrador.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Define o nome do administrador.
     *
     * @param nome Novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define o número do administrador.
     *
     * @param numero Novo número.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Define a senha do administrador.
     *
     * @param senha Nova senha.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Define o curso do administrador.
     *
     * @param curso Novo curso.
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }

    /**
     * Compara dois administradores para verificar igualdade.
     *
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrador)) return false;
        Administrador that = (Administrador) o;
        return this.numero == that.numero &&
                this.nome.equals(that.nome) &&
                this.curso.equals(that.curso) &&
                this.senha.equals(that.senha);
    }

    /**
     * Compara dois administradores por nome, ignorando maiúsculas/minúsculas.
     *
     * @param outro Outro administrador a comparar.
     * @return Valor da comparação (positivo, negativo ou 0).
     */
    @Override
    public int compareTo(Administrador outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    /**
     * Representação textual do administrador.
     *
     * @return String com os dados do administrador.
     */
    @Override
    public String toString() {
        return "Administrador{" +
                "nome='" + nome + '\'' +
                ", numero=" + numero +
                ", curso='" + curso + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
