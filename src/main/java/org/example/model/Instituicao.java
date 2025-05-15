package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Instituicao implements Comparable<Instituicao> {
    // Variáveis de instância
    private String nome;
    private final List<Voluntario> listaVoluntarios;
    private final List<Produto> lstProdutos;
    private final List<Barraca> barracas;
    private final List<EscalaDiaria> escalasDiarias;
    private Federacao federacao;

    // Construtor completo
    public Instituicao(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
        this.listaVoluntarios = new ArrayList<>();
        this.lstProdutos = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.escalasDiarias = new ArrayList<>();
        this.federacao = null;
    }

    // Construtor vazio
    public Instituicao() {
        this.nome = "";
        this.listaVoluntarios = new ArrayList<>();
        this.lstProdutos = new ArrayList<>();
        this.barracas = new ArrayList<>();
        this.escalasDiarias = new ArrayList<>();
        this.federacao = null;
    }

    // Construtor de cópia
    public Instituicao(Instituicao outra) {
        this.nome = outra.nome;
        this.listaVoluntarios = new ArrayList<>(outra.listaVoluntarios);
        this.lstProdutos = new ArrayList<>(outra.lstProdutos);
        this.barracas = new ArrayList<>(outra.barracas);
        this.escalasDiarias = new ArrayList<>(outra.escalasDiarias);
        this.federacao = outra.federacao; // Referência partilhada
    }

    // Seletores
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

    // Modificadores
    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public void setFederacao(Federacao federacao) {
        this.federacao = federacao;
    }

    public void setEscalasDiarias(List<EscalaDiaria> escalasDiarias) {
        if (escalasDiarias == null) {
            throw new IllegalArgumentException("Lista de escalas não pode ser nula");
        }
        this.escalasDiarias.clear();
        this.escalasDiarias.addAll(escalasDiarias);
    }

    public void setBarracas(List<Barraca> barracas) {
        if (barracas == null) {
            throw new IllegalArgumentException("Lista de barracas não pode ser nula");
        }
        this.barracas.clear();
        this.barracas.addAll(barracas);
    }

    // Métodos de instância
    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (voluntario == null) {
            throw new IllegalArgumentException("Voluntário não pode ser nulo");
        }
        if (!this.listaContemVoluntario(voluntario.getNumeroAluno())) {
            this.listaVoluntarios.add(voluntario);
            return true;
        }
        return false;
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

    public boolean adicionarBarraca(Barraca barraca) {
        if (barraca == null) {
            throw new IllegalArgumentException("Barraca não pode ser nula");
        }
        if (!this.barracas.contains(barraca)) {
            this.barracas.add(barraca);
            if (this.federacao != null) {
                this.federacao.adicionarBarraca(barraca);
            }
            return true;
        }
        return false;
    }

    public void adicionarEscalaDiaria(EscalaDiaria escala) {
        if (escala == null) {
            throw new IllegalArgumentException("Escala não pode ser nula");
        }
        if (!this.escalasDiarias.contains(escala)) {
            this.escalasDiarias.add(escala);
        }
    }

    // Implementação de Comparable (ordena por nome)
    @Override
    public int compareTo(Instituicao outra) {
        if (this.nome == null && outra.nome == null) return 0;
        if (this.nome == null) return -1;
        if (outra.nome == null) return 1;
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instituicao)) return false;
        Instituicao that = (Instituicao) o;
        if (this.nome == null) {
            return that.nome == null;
        } else if (that.nome == null) {
            return false;
        } else {
            return this.nome.equalsIgnoreCase(that.nome);
        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Instituicao{" +
                "nome='" + (nome == null ? "" : nome) + '\'' +
                ", numVoluntarios=" + listaVoluntarios.size() +
                ", numProdutos=" + lstProdutos.size() +
                ", numBarracas=" + barracas.size() +
                ", numEscalas=" + escalasDiarias.size() +
                '}';
    }
}