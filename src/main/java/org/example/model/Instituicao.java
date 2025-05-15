package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Instituicao implements Comparable<Instituicao> {
    private String nome;
    private final List<Voluntario> listaVoluntarios;
    private final List<Produto> lstProdutos;
    private final List<Barraca> barracas;
    private final List<EscalaDiaria> escalasDiarias;
    private Federacao federacao;

    public Instituicao(String nome) {
        this.nome = nome;
        this.listaVoluntarios = new ArrayList<>();
        this.lstProdutos = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.escalasDiarias = new ArrayList<>();
        this.federacao = null;
    }

    public Instituicao() {
        this.nome = "";
        this.listaVoluntarios = new ArrayList<>();
        this.lstProdutos = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.escalasDiarias = new ArrayList<>();
        this.federacao = null;
    }

    public Instituicao(Instituicao outra) {
        this.nome = outra.nome;
        this.listaVoluntarios = new ArrayList<>(outra.listaVoluntarios);
        this.lstProdutos = new ArrayList<>(outra.lstProdutos);
        this.barracas = new ArrayList<>(outra.barracas);
        this.escalasDiarias = new ArrayList<>(outra.escalasDiarias);
        this.federacao = outra.federacao;
    }

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }

    public void adicionarVoluntario(Voluntario voluntario) {
        this.listaVoluntarios.add(voluntario);
    }

    public boolean listaContemVoluntario(int numeroAluno) {
        for (Voluntario voluntario : this.listaVoluntarios) {
            if (voluntario.getNumeroAluno() == numeroAluno) {
                return true;
            }
        }
        return false;
    }

    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Voluntario v : this.listaVoluntarios) {
            if (v.getNumeroAluno() == numeroAluno) {
                return v;
            }
        }
        return null;
    }

    public VoluntarioVendas getVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : this.listaVoluntarios) {
            if (voluntario instanceof VoluntarioVendas && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioVendas) voluntario;
            }
        }
        return null;
    }

    public VoluntarioStock getVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : this.listaVoluntarios) {
            if (voluntario instanceof VoluntarioStock && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioStock) voluntario;
            }
        }
        return null;
    }

    public void adicionarBarraca(Barraca barraca) {
        this.barracas.add(barraca);
        if (this.federacao != null) {
            this.federacao.adicionarBarraca(barraca);
        }
    }

    public void adicionarEscalaDiaria(EscalaDiaria escala) {
        this.escalasDiarias.add(escala);
    }

    @Override
    public int compareTo(Instituicao outra) {
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instituicao)) return false;
        Instituicao that = (Instituicao) o;
        return this.nome.equals(that.nome);
    }

    @Override
    public String toString() {
        return "Instituicao{nome='" + nome + "', " +
                "numVoluntarios=" + listaVoluntarios.size() + ", " +
                "numProdutos=" + lstProdutos.size() + ", " +
                "numBarracas=" + barracas.size() + ", " +
                "numEscalas=" + escalasDiarias.size() + "}";
    }
}