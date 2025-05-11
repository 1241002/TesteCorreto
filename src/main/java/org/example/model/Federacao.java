//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Federacao {
    private String nome;
    private final List<Produto> lstProdutos;
    private final List<Instituicao> instituicoes;
    private EscalaDiaria escalaAtual;
    private final List<Barraca> todasBarracas = new ArrayList<>();


    public Federacao(String nome) {
        this.nome = nome;
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
    }

    // Adicionar Produto
    public boolean adicionarProduto(Produto produto) {
        if (!listaContemProduto(produto.getNome())) {
            lstProdutos.add(new Produto(produto));
            return true;
        }
        return false;
    }

    // Verifica se produto existe
    public boolean listaContemProduto(String nomeProduto) {
        for (Produto produto : lstProdutos) {
            if (produto.getNome().equals(nomeProduto)) {
                return true;
            }
        }
        return false;
    }

    // Retorna lista de produtos (cópia para segurança)
    public List<Produto> getLstProdutos() {
        return new ArrayList<>(lstProdutos);
    }


    // Adicionar Instituicao
    public boolean adicionarInstituicao(Instituicao instituicao) {
        if (!instituicaoExiste(instituicao.getNome())) {
            instituicoes.add(instituicao);
            return true;
        }
        return false;
    }

    public List<Instituicao> getInstituicoes() {
        return instituicoes;
    }

    public boolean listaContemInstituicao(String nomeInstituicao) {
        for (Instituicao inst : instituicoes) {
            if (inst.getNome().equalsIgnoreCase(nomeInstituicao)) {
                return true;
            }
        }
        return false;
    }

    // Verifica se Instituição existe por nome
    private boolean instituicaoExiste(String nome) {
        for (Instituicao inst : instituicoes) {
            if (inst.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    // Retorna voluntário de vendas (busca em todas as instituições)
    public VoluntarioVendas buscarVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Instituicao instituicao : instituicoes) {
            VoluntarioVendas voluntario = instituicao.getVoluntarioVendasPorNumeroAluno(numeroAluno);
            if (voluntario != null) {
                return voluntario;
            }
        }
        return null;
    }

    // Retorna voluntário de stock
    public VoluntarioStock buscarVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Instituicao instituicao : instituicoes) {
            VoluntarioStock voluntario = instituicao.getVoluntarioStockPorNumeroAluno(numeroAluno);
            if (voluntario != null) {
                return voluntario;
            }
        }
        return null;
    }

    // Retorna qualquer voluntário
    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Instituicao instituicao : instituicoes) {
            Voluntario voluntario = instituicao.getVoluntarioPorNumeroAluno(numeroAluno);
            if (voluntario != null) {
                return voluntario;
            }
        }
        return null;
    }

    public List<Barraca> getTodasBarracas() {
        return todasBarracas;
    }

    public EscalaDiaria getEscalaAtual() {
        return escalaAtual;
    }

    public void setEscalaAtual(EscalaDiaria escalaAtual) {
        this.escalaAtual = escalaAtual;
    }


    // Getter do nome
    public String getNome() {
        return nome;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Federacao: ");
        sb.append("nome='").append(this.nome).append("\n");
        sb.append("Lista de Produtos:");
        if (this.lstProdutos.isEmpty()) {
            sb.append(" (VAZIA)\n");
        } else {
            for(Produto produto : this.lstProdutos) {
                sb.append("\n\t- ").append(produto);
            }
        }

        return sb.toString();
    }
}
