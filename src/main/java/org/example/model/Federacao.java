package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Federacao {
    private String nome;
    private final List<Produto> lstProdutos = new ArrayList<>();
    private final List<Instituicao> instituicoes = new ArrayList<>();
    private EscalaDiaria escalaAtual;
    private final List<Barraca> todasBarracas = new ArrayList<>();
    private final List<EscalaDiaria> escalas = new ArrayList<>();

    public Federacao(String nome) {
        this.nome = nome;
    }

    // Produtos
    public boolean adicionarProduto(Produto produto) {
        if (!listaContemProduto(produto.getNome())) {
            lstProdutos.add(new Produto(produto));
            return true;
        }
        return false;
    }

    public boolean listaContemProduto(String nomeProduto) {
        return lstProdutos.stream().anyMatch(p -> p.getNome().equalsIgnoreCase(nomeProduto));
    }

    public List<Produto> getLstProdutos() {
        return Collections.unmodifiableList(lstProdutos);
    }

    // Instituições
    public boolean adicionarInstituicao(Instituicao instituicao) {
        if (!instituicaoExiste(instituicao.getNome())) {
            instituicoes.add(instituicao);
            return true;
        }
        return false;
    }

    public List<Instituicao> getInstituicoes() {
        return Collections.unmodifiableList(instituicoes);
    }

    private boolean instituicaoExiste(String nome) {
        return instituicoes.stream().anyMatch(i -> i.getNome().equalsIgnoreCase(nome));
    }

    public boolean listaContemInstituicao(String nomeInstituicao) {
        return instituicaoExiste(nomeInstituicao);
    }

    // Procurar voluntários
    public VoluntarioVendas buscarVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : instituicoes) {
            VoluntarioVendas v = inst.getVoluntarioVendasPorNumeroAluno(numeroAluno);
            if (v != null) return v;
        }
        return null;
    }

    public VoluntarioStock buscarVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : instituicoes) {
            VoluntarioStock v = inst.getVoluntarioStockPorNumeroAluno(numeroAluno);
            if (v != null) return v;
        }
        return null;
    }

    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : instituicoes) {
            Voluntario v = inst.getVoluntarioPorNumeroAluno(numeroAluno);
            if (v != null) return v;
        }
        return null;
    }

    // Escalas
    public List<EscalaDiaria> getEscalas() {
        return Collections.unmodifiableList(escalas);
    }

    public void adicionarEscala(EscalaDiaria escala) {
        if (!escalas.contains(escala)) {
            escalas.add(escala);
        }
    }

    public EscalaDiaria buscarEscalaPorData(String data) {
        return escalas.stream()
                .filter(escala -> escala.getData().toString().equals(data))
                .findFirst()
                .orElse(null);
    }

    public EscalaDiaria getEscalaAtual() {
        return escalaAtual;
    }

    public void setEscalaAtual(EscalaDiaria escalaAtual) {
        this.escalaAtual = escalaAtual;
    }

    // Barracas
    public List<Barraca> getTodasBarracas() {
        return todasBarracas;
    }

    // Nome
    public String getNome() {
        return nome;
    }

    // toString para debug
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Federação: ").append(nome).append("\n");

        sb.append("Produtos: ");
        sb.append(lstProdutos.isEmpty() ? "Nenhum\n" : lstProdutos.stream().map(Produto::toString).collect(Collectors.joining("\n\t- ")));

        sb.append("\nInstituições: ");
        sb.append(instituicoes.isEmpty() ? "Nenhuma\n" : instituicoes.stream().map(Instituicao::getNome).collect(Collectors.joining("\n\t- ")));

        sb.append("\nNúmero de escalas: ").append(escalas.size());

        return sb.toString();
    }
}
