package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Federacao implements Comparable<Federacao> {
    private String nome;
    private final List<Produto> lstProdutos;
    private final List<Instituicao> instituicoes;
    private EscalaDiaria escalaAtual;
    private final List<Barraca> todasBarracas;
    private final List<EscalaDiaria> escalas;
    private final List<Administrador> administradores;

    public Federacao(String nome) {
        this.nome = nome;
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
        this.todasBarracas = new ArrayList<>();
        this.escalas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.escalaAtual = null;
    }

    public Federacao() {
        this.nome = "";
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
        this.todasBarracas = new ArrayList<>();
        this.escalas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.escalaAtual = null;
    }

    public Federacao(Federacao outra) {
        this.nome = outra.nome;
        this.lstProdutos = new ArrayList<>(outra.lstProdutos);
        this.instituicoes = new ArrayList<>(outra.instituicoes);
        this.todasBarracas = new ArrayList<>(outra.todasBarracas);
        this.escalas = new ArrayList<>(outra.escalas);
        this.administradores = new ArrayList<>(outra.administradores);
        this.escalaAtual = outra.escalaAtual;
    }

    public String getNome() {
        return this.nome;
    }

    public List<Produto> getListaProdutos() {
        return new ArrayList<>(this.lstProdutos);
    }

    public List<Instituicao> getInstituicoes() {
        return new ArrayList<>(this.instituicoes);
    }

    public EscalaDiaria getEscalaAtual() {
        return this.escalaAtual;
    }

    public List<Barraca> getTodasBarracas() {
        return new ArrayList<>(this.todasBarracas);
    }

    public List<EscalaDiaria> getEscalas() {
        return new ArrayList<>(this.escalas);
    }

    public List<Administrador> getAdministradores() {
        return new ArrayList<>(this.administradores);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEscalaAtual(EscalaDiaria escalaAtual) {
        this.escalaAtual = escalaAtual;
    }

    public void adicionarAdministrador(Administrador admin) {
        this.administradores.add(admin);
    }

    public boolean validarLoginAdministrador(String nome, int numero, String senha, String curso) {
        for (Administrador admin : this.administradores) {
            if (admin.getNumero() == numero &&
                    admin.getNome().equals(nome) &&
                    admin.getSenha().equals(senha) &&
                    admin.getCurso().equals(curso)) {
                return true;
            }
        }
        return false;
    }

    public boolean validarLoginVoluntario(String nome, int numero, String senha, String curso, boolean isVendas) {
        Voluntario voluntario = this.buscarVoluntarioPorNumeroAluno(numero);
        if (voluntario != null &&
                ((isVendas && voluntario instanceof VoluntarioVendas) || (!isVendas && voluntario instanceof VoluntarioStock)) &&
                voluntario.getNome().equals(nome) &&
                voluntario.getSenha().equals(senha) &&
                voluntario.getCurso().equals(curso)) {
            return true;
        }
        return false;
    }

    public void adicionarProduto(Produto produto) {
        this.lstProdutos.add(new Produto(produto));
    }

    public boolean listaContemProduto(String nomeProduto) {
        for (Produto p : this.lstProdutos) {
            if (p.getNome().equals(nomeProduto)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarInstituicao(Instituicao instituicao) {
        this.instituicoes.add(instituicao);
        instituicao.setFederacao(this);
        for (Barraca barraca : instituicao.getBarracas()) {
            this.adicionarBarraca(barraca);
        }
    }

    public void adicionarBarraca(Barraca barraca) {
        this.todasBarracas.add(barraca);
    }

    public boolean listaContemInstituicao(String nomeInstituicao) {
        for (Instituicao i : this.instituicoes) {
            if (i.getNome().equals(nomeInstituicao)) {
                return true;
            }
        }
        return false;
    }

    public VoluntarioVendas buscarVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : this.instituicoes) {
            VoluntarioVendas v = inst.getVoluntarioVendasPorNumeroAluno(numeroAluno);
            if (v != null) {
                return v;
            }
        }
        return null;
    }

    public VoluntarioStock buscarVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : this.instituicoes) {
            VoluntarioStock v = inst.getVoluntarioStockPorNumeroAluno(numeroAluno);
            if (v != null) {
                return v;
            }
        }
        return null;
    }

    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : this.instituicoes) {
            Voluntario v = inst.buscarVoluntarioPorNumeroAluno(numeroAluno);
            if (v != null) {
                return v;
            }
        }
        return null;
    }

    public void adicionarEscala(EscalaDiaria escala) {
        this.escalas.add(escala);
    }

    public EscalaDiaria buscarEscalaPorData(String data) {
        for (EscalaDiaria escala : this.escalas) {
            if (escala.getData().toString().equals(data)) {
                return escala;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Federacao outra) {
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Federacao)) return false;
        Federacao that = (Federacao) o;
        return this.nome.equals(that.nome);
    }

    @Override
    public String toString() {
        return "Federacao{nome='" + nome + "', " +
                "numAdministradores=" + administradores.size() + ", " +
                "numProdutos=" + lstProdutos.size() + ", " +
                "numInstituicoes=" + instituicoes.size() + ", " +
                "numBarracas=" + todasBarracas.size() + ", " +
                "numEscalas=" + escalas.size() + "}";
    }
}