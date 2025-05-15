package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Federacao implements Comparable<Federacao> {
    // Variáveis de instância
    private String nome;
    private final List<Produto> lstProdutos;
    private final List<Instituicao> instituicoes;
    private EscalaDiaria escalaAtual;
    private final List<Barraca> todasBarracas;
    private final List<EscalaDiaria> escalas;
    private final List<Administrador> administradores;

    // Construtor completo
    public Federacao(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
        this.todasBarracas = new ArrayList<>();
        this.escalas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.escalaAtual = null;
    }

    // Construtor vazio
    public Federacao() {
        this.nome = "";
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
        this.todasBarracas = new ArrayList<>();
        this.escalas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.escalaAtual = null;
    }

    // Construtor de cópia
    public Federacao(Federacao outra) {
        this.nome = outra.nome;
        this.lstProdutos = new ArrayList<>(outra.lstProdutos);
        this.instituicoes = new ArrayList<>(outra.instituicoes);
        this.todasBarracas = new ArrayList<>(outra.todasBarracas);
        this.escalas = new ArrayList<>(outra.escalas);
        this.administradores = new ArrayList<>(outra.administradores);
        this.escalaAtual = outra.escalaAtual; // Referência partilhada
    }

    // Seletores
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

    // Modificadores
    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
        this.nome = nome;
    }

    public void setEscalaAtual(EscalaDiaria escalaAtual) {
        this.escalaAtual = escalaAtual;
    }

    // Métodos de instância
    public boolean adicionarAdministrador(Administrador admin) {
        if (admin == null) {
            throw new IllegalArgumentException("Administrador não pode ser nulo");
        }
        if (!this.administradorExiste(admin.getNumero())) {
            this.administradores.add(admin);
            return true;
        }
        return false;
    }

    private boolean administradorExiste(int numero) {
        for (Administrador admin : this.administradores) {
            if (admin.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }

    public boolean validarLoginAdministrador(String nome, int numero, String senha, String curso) {
        if (nome == null || senha == null || curso == null) {
            return false;
        }
        for (Administrador admin : this.administradores) {
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
        if (nome == null || senha == null || curso == null || tipoVoluntario == null) {
            return false;
        }
        Voluntario voluntario = this.buscarVoluntarioPorNumeroAluno(numero);
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
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (!this.listaContemProduto(produto.getNome())) {
            this.lstProdutos.add(new Produto(produto));
            for (Instituicao instituicao : this.instituicoes) {
                boolean produtoExiste = false;
                for (Produto p : instituicao.getLstProdutos()) {
                    if (p.getNome().equalsIgnoreCase(produto.getNome())) {
                        produtoExiste = true;
                        break;
                    }
                }
                if (!produtoExiste) {
                    instituicao.getLstProdutos().add(new Produto(produto));
                }
            }
            return true;
        }
        return false;
    }

    public boolean listaContemProduto(String nomeProduto) {
        if (nomeProduto == null) {
            return false;
        }
        for (Produto p : this.lstProdutos) {
            if (p.getNome() != null && p.getNome().equalsIgnoreCase(nomeProduto)) {
                return true;
            }
        }
        return false;
    }

    public boolean adicionarInstituicao(Instituicao instituicao) {
        if (instituicao == null) {
            throw new IllegalArgumentException("Instituição não pode ser nula");
        }
        if (!this.instituicaoExiste(instituicao.getNome())) {
            this.instituicoes.add(instituicao);
            for (Produto produto : this.lstProdutos) {
                instituicao.getLstProdutos().add(new Produto(produto));
            }
            instituicao.setFederacao(this);
            for (Barraca barraca : instituicao.getBarracas()) {
                this.adicionarBarraca(barraca);
            }
            return true;
        }
        return false;
    }

    public boolean adicionarBarraca(Barraca barraca) {
        if (barraca == null) {
            throw new IllegalArgumentException("Barraca não pode ser nula");
        }
        if (!this.todasBarracas.contains(barraca)) {
            this.todasBarracas.add(barraca);
            return true;
        }
        return false;
    }

    private boolean instituicaoExiste(String nome) {
        if (nome == null) {
            return false;
        }
        for (Instituicao i : this.instituicoes) {
            if (i.getNome() != null && i.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean listaContemInstituicao(String nomeInstituicao) {
        return this.instituicaoExiste(nomeInstituicao);
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
        for (Instituicao instituicao : this.instituicoes) {
            for (Voluntario voluntario : instituicao.getListaVoluntarios()) {
                if (voluntario instanceof VoluntarioStock && voluntario.getNumeroAluno() == numeroAluno) {
                    return (VoluntarioStock) voluntario;
                }
            }
        }
        return null;
    }

    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : this.instituicoes) {
            Voluntario v = inst.getVoluntarioPorNumeroAluno(numeroAluno);
            if (v != null) {
                return v;
            }
        }
        return null;
    }

    public void adicionarEscala(EscalaDiaria escala) {
        if (escala == null) {
            throw new IllegalArgumentException("Escala não pode ser nula");
        }
        if (!this.escalas.contains(escala)) {
            this.escalas.add(escala);
        }
    }

    public EscalaDiaria buscarEscalaPorData(String data) {
        if (data == null) {
            return null;
        }
        for (EscalaDiaria escala : this.escalas) {
            if (escala.getData() != null && escala.getData().toString().equals(data)) {
                return escala;
            }
        }
        return null;
    }

    // Implementação de Comparable (ordena por nome)
    @Override
    public int compareTo(Federacao outra) {
        if (this.nome == null && outra.nome == null) return 0;
        if (this.nome == null) return -1;
        if (outra.nome == null) return 1;
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Federacao)) return false;
        Federacao that = (Federacao) o;
        return (this.nome == null ? that.nome == null : this.nome.equalsIgnoreCase(that.nome));
    }

    // Método toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Federacao: " + (nome == null ? "N/A" : nome) + "\n");
        sb.append("Administradores: ").append(this.administradores.size()).append("\n");
        sb.append("Produtos: ").append(this.lstProdutos.size()).append("\n");
        sb.append("Instituicoes: ").append(this.instituicoes.size()).append("\n");
        sb.append("Barracas: ").append(this.todasBarracas.size()).append("\n");
        sb.append("Escalas: ").append(this.escalas.size()).append("\n");
        return sb.toString();
    }
}