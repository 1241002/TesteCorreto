package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma instituição participante na federação.
 * Cada instituição tem voluntários, barracas, produtos e escalas diárias associadas.
 */
public class Instituicao implements Comparable<Instituicao> {
    private String nome;
    private final List<Voluntario> listaVoluntarios;
    private final List<Produto> lstProdutos;
    private final List<Barraca> barracas;
    private final List<EscalaDiaria> escalasDiarias;
    private Federacao federacao;

    /**
     * Construtor completo com nome.
     * @param nome Nome da instituição.
     */
    public Instituicao(String nome) {
        this.nome = nome;
        this.listaVoluntarios = new ArrayList<>();
        this.lstProdutos = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.escalasDiarias = new ArrayList<>();
        this.federacao = null;
    }

    /**
     * Construtor por omissão.
     */
    public Instituicao() {
        this("");
    }

    /**
     * Construtor de cópia.
     * @param outra Instituição a copiar.
     */
    public Instituicao(Instituicao outra) {
        this.nome = outra.nome;
        this.listaVoluntarios = new ArrayList<>(outra.listaVoluntarios);
        this.lstProdutos = new ArrayList<>(outra.lstProdutos);
        this.barracas = new ArrayList<>(outra.barracas);
        this.escalasDiarias = new ArrayList<>(outra.escalasDiarias);
        this.federacao = outra.federacao;
    }

    // Getters

    public String getNome() {
        return this.nome;
    }

    public List<Voluntario> getListaVoluntarios() {
        return new ArrayList<>(this.listaVoluntarios);
    }

    public List<Produto> getLstProdutos() {
        return new ArrayList<>(this.lstProdutos);
    }

    public List<Barraca> getBarracas() {
        return new ArrayList<>(this.barracas);
    }

    public List<EscalaDiaria> getEscalasDiarias() {
        return new ArrayList<>(this.escalasDiarias);
    }

    public Federacao getFederacao() {
        return this.federacao;
    }

    // Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }

    // Operações

    /**
     * Adiciona um voluntário à instituição.
     * @param voluntario Voluntário a adicionar.
     */
    public void adicionarVoluntario(Voluntario voluntario) {
        this.listaVoluntarios.add(voluntario);
    }

    /**
     * Verifica se já existe um voluntário com o número de aluno indicado.
     * @param numeroAluno Número do aluno.
     * @return true se já existe, false caso contrário.
     */
    public boolean listaContemVoluntario(int numeroAluno) {
        for (Voluntario voluntario : this.listaVoluntarios) {
            if (voluntario.getNumeroAluno() == numeroAluno) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procura um voluntário pelo número de aluno.
     * @param numeroAluno Número do aluno.
     * @return Voluntário correspondente ou null.
     */
    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Voluntario v : this.listaVoluntarios) {
            if (v.getNumeroAluno() == numeroAluno) {
                return v;
            }
        }
        return null;
    }

    /**
     * Retorna o voluntário de vendas com o número de aluno indicado.
     */
    public VoluntarioVendas getVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : this.listaVoluntarios) {
            if (voluntario instanceof VoluntarioVendas && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioVendas) voluntario;
            }
        }
        return null;
    }

    /**
     * Retorna o voluntário de stock com o número de aluno indicado.
     */
    public VoluntarioStock getVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : this.listaVoluntarios) {
            if (voluntario instanceof VoluntarioStock && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioStock) voluntario;
            }
        }
        return null;
    }

    /**
     * Adiciona uma barraca à instituição e à federação (se existir).
     * @param barraca Barraca a adicionar.
     */
    public void adicionarBarraca(Barraca barraca) {
        this.barracas.add(barraca);
        if (this.federacao != null) {
            this.federacao.adicionarBarraca(barraca);
        }
    }

    /**
     * Adiciona uma escala diária à instituição.
     * @param escala Escala a adicionar.
     */
    public void adicionarEscalaDiaria(EscalaDiaria escala) {
        this.escalasDiarias.add(escala);
    }

    // Comparable

    @Override
    public int compareTo(Instituicao outra) {
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    // Equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instituicao)) return false;
        Instituicao that = (Instituicao) o;
        return this.nome.equals(that.nome);
    }

    // toString

    @Override
    public String toString() {
        return "Instituicao{nome='" + nome + "', " +
                "numVoluntarios=" + listaVoluntarios.size() + ", " +
                "numProdutos=" + lstProdutos.size() + ", " +
                "numBarracas=" + barracas.size() + ", " +
                "numEscalas=" + escalasDiarias.size() + "}";
    }
}
