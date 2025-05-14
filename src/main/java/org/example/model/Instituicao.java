package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Instituicao {
    private String nome;
    private final List<Voluntario> listaVoluntarios;
    private final List<Produto> lstProdutos;
    private List<Barraca> barracas;
    private List<EscalaDiaria> escalasDiarias;
    private Federacao federacao;

    public Instituicao(String nome) {
        this.nome = nome;
        this.listaVoluntarios = new ArrayList<>();
        this.lstProdutos = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.escalasDiarias = new ArrayList<>();
        this.federacao = null;
    }

    public List<EscalaDiaria> getEscalasDiarias() {
        return escalasDiarias;
    }

    public void setEscalasDiarias(List<EscalaDiaria> escalasDiarias) {
        this.escalasDiarias = escalasDiarias;
    }

    public void setBarracas(List<Barraca> barracas) {
        this.barracas = barracas;
    }

    public List<Barraca> getBarracas() {
        return this.barracas;
    }

    public List<Produto> getLstProdutos() {
        return lstProdutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Voluntario> getListaVoluntarios() {
        return listaVoluntarios;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }

    public void adicionarEscalaDiaria(EscalaDiaria escala) {
        escalasDiarias.add(escala);
    }

    public boolean adicionarBarraca(Barraca barraca) {
        if (!getBarracas().contains(barraca)) {
            getBarracas().add(barraca);
            // Notificar a federação, se existir
            if (federacao != null) {
                federacao.adicionarBarraca(barraca);
            }
            return true;
        }
        return false;
    }

    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (!listaContemVoluntario(voluntario.getNumeroAluno())) {
            listaVoluntarios.add(voluntario);
            return true;
        } else {
            return false;
        }
    }

    public boolean listaContemVoluntario(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario.getNumeroAluno() == numeroAluno) {
                return true;
            }
        }
        return false;
    }

    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Voluntario v : listaVoluntarios) {
            if (v.getNumeroAluno() == numeroAluno) {
                return v;
            }
        }
        return null;
    }

    public VoluntarioVendas getVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario instanceof VoluntarioVendas && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioVendas) voluntario;
            }
        }
        return null;
    }

    public VoluntarioStock getVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario instanceof VoluntarioStock && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioStock) voluntario;
            }
        }
        return null;
    }

    public Voluntario getVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario.getNumeroAluno() == numeroAluno) {
                return voluntario;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Instituicao{" +
                "nome='" + nome + '\'' +
                ", listaVoluntarios=" + listaVoluntarios +
                ", lstProdutos=" + lstProdutos +
                '}';
    }
}