package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Federacao {
    private String nome;
    private final List<Produto> lstProdutos;
    private final List<Instituicao> instituicoes;
    private EscalaDiaria escalaAtual;
    private final List<Barraca> todasBarracas;
    private final List<EscalaDiaria> escalas;

    public Federacao(String nome) {
        this.nome = nome;
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
        this.todasBarracas = new ArrayList<>();
        this.escalas = new ArrayList<>();
    }

    // Produtos
    public boolean adicionarProduto(Produto produto) {
        if (!listaContemProduto(produto.getNome())) {
            lstProdutos.add(new Produto(produto));
            // Propagar o produto para todas as instituições
            for (Instituicao instituicao : instituicoes) {
                if (!instituicao.getLstProdutos().stream().anyMatch(p -> p.getNome().equalsIgnoreCase(produto.getNome()))) {
                    instituicao.getLstProdutos().add(new Produto(produto));
                }
            }
            return true;
        }
        return false;
    }

    public boolean listaContemProduto(String nomeProduto) {
        for (Produto p : lstProdutos) {
            if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                return true;
            }
        }
        return false;
    }

    public List<Produto> getListaProdutos() {
        return new ArrayList<>(lstProdutos);
    }

    // Instituições
    public boolean adicionarInstituicao(Instituicao instituicao) {
        if (!instituicaoExiste(instituicao.getNome())) {
            instituicoes.add(instituicao);
            // Adicionar todos os produtos existentes à nova instituição
            for (Produto produto : lstProdutos) {
                instituicao.getLstProdutos().add(new Produto(produto));
            }
            return true;
        }
        return false;
    }

    public List<Instituicao> getInstituicoes() {
        return new ArrayList<>(instituicoes);
    }

    private boolean instituicaoExiste(String nome) {
        for (Instituicao i : instituicoes) {
            if (i.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
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
        for (Instituicao instituicao : instituicoes) {
            for (Voluntario voluntario : instituicao.getListaVoluntarios()) {
                if (voluntario instanceof VoluntarioStock && voluntario.getNumeroAluno() == numeroAluno) {
                    return (VoluntarioStock) voluntario;
                }
            }
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
        return new ArrayList<>(escalas);
    }

    public void adicionarEscala(EscalaDiaria escala) {
        if (!escalas.contains(escala)) {
            escalas.add(escala);
        }
    }

    public EscalaDiaria buscarEscalaPorData(String data) {
        for (EscalaDiaria escala : escalas) {
            if (escala.getData().toString().equals(data)) {
                return escala;
            }
        }
        return null;
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

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Federação: " + nome + "\n");

        sb.append("Produtos:\n");
        if (lstProdutos.isEmpty()) {
            sb.append("\tNenhum\n");
        } else {
            for (Produto p : lstProdutos) {
                sb.append("\t- ").append(p.toString()).append("\n");
            }
        }

        sb.append("Instituições:\n");
        if (instituicoes.isEmpty()) {
            sb.append("\tNenhuma\n");
        } else {
            for (Instituicao i : instituicoes) {
                sb.append("\t- ").append(i.getNome()).append("\n");
            }
        }

        sb.append("Número de escalas: ").append(escalas.size()).append("\n");

        return sb.toString();
    }
}