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
    private final List<Administrador> administradores;

    public Federacao(String nome) {
        this.nome = nome;
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
        this.todasBarracas = new ArrayList<>();
        this.escalas = new ArrayList<>();
        this.administradores = new ArrayList<>();
    }

    public boolean adicionarAdministrador(Administrador admin) {
        if (!administradorExiste(admin.getNumero())) {
            administradores.add(admin);
            return true;
        }
        return false;
    }

    private boolean administradorExiste(int numero) {
        for (Administrador admin : administradores) {
            if (admin.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }

    public boolean validarLoginAdministrador(String nome, int numero, String senha, String curso) {
        for (Administrador admin : administradores) {
            if (admin.getNumero() == numero &&
                    admin.getNome().equalsIgnoreCase(nome) &&
                    admin.getSenha().equals(senha) &&
                    admin.getCurso().equalsIgnoreCase(curso)) {
                return true;
            }
        }
        return false;
    }

    public boolean validarLoginVoluntario(String nome, int numero, String senha, String curso, Class<?> tipoVoluntario) {
        Voluntario voluntario = buscarVoluntarioPorNumeroAluno(numero);
        if (voluntario != null &&
                tipoVoluntario.isInstance(voluntario) &&
                voluntario.getNome().equalsIgnoreCase(nome) &&
                voluntario.getSenha().equals(senha) &&
                voluntario.getCurso().equalsIgnoreCase(curso)) {
            return true;
        }
        return false;
    }

    public boolean adicionarProduto(Produto produto) {
        if (!listaContemProduto(produto.getNome())) {
            lstProdutos.add(new Produto(produto));
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

    public boolean adicionarInstituicao(Instituicao instituicao) {
        if (!instituicaoExiste(instituicao.getNome())) {
            instituicoes.add(instituicao);
            for (Produto produto : lstProdutos) {
                instituicao.getLstProdutos().add(new Produto(produto));
            }
            // Configurar a referência à federação na instituição
            instituicao.setFederacao(this);
            // Adicionar barracas existentes da instituição
            for (Barraca barraca : instituicao.getBarracas()) {
                adicionarBarraca(barraca);
            }
            return true;
        }
        return false;
    }

    public boolean adicionarBarraca(Barraca barraca) {
        if (!todasBarracas.contains(barraca)) {
            todasBarracas.add(barraca);
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

    public List<Barraca> getTodasBarracas() {
        return new ArrayList<>(todasBarracas);
    }

    public String getNome() {
        return nome;
    }

    public List<Administrador> getAdministradores() {
        return new ArrayList<>(administradores);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Federação: " + nome + "\n");

        sb.append("Administradores:\n");
        if (administradores.isEmpty()) {
            sb.append("\tNenhum\n");
        } else {
            for (Administrador admin : administradores) {
                sb.append("\t- ").append(admin.toString()).append("\n");
            }
        }

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