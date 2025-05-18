package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a federação responsável por gerir instituições, barracas, produtos,
 * administradores, escalas e voluntários.
 */
public class Federacao implements Comparable<Federacao>, Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private final List<Produto> lstProdutos;
    private final List<Instituicao> instituicoes;
    private EscalaDiaria escalaAtual;
    private final List<Barraca> todasBarracas;
    private final List<EscalaDiaria> escalas;
    private final List<Administrador> administradores;

    /**
     * Construtor que inicializa a federação com um nome.
     *
     * @param nome Nome da federação.
     */
    public Federacao(String nome) {
        this.nome = nome;
        this.lstProdutos = new ArrayList<>();
        this.instituicoes = new ArrayList<>();
        this.todasBarracas = new ArrayList<>();
        this.escalas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.escalaAtual = null;
    }

    /**
     * Construtor padrão que inicializa uma federação com nome vazio.
     */
    public Federacao() {
        this("");
    }

    /**
     * Construtor de cópia que cria uma nova federação copiando os dados de outra.
     *
     * @param outra Outra federação a ser copiada.
     */
    public Federacao(Federacao outra) {
        this.nome = outra.nome;
        this.lstProdutos = new ArrayList<>(outra.lstProdutos);
        this.instituicoes = new ArrayList<>(outra.instituicoes);
        this.todasBarracas = new ArrayList<>(outra.todasBarracas);
        this.escalas = new ArrayList<>(outra.escalas);
        this.administradores = new ArrayList<>(outra.administradores);
        this.escalaAtual = outra.escalaAtual;
    }

    // Getters

    /**
     * Obtém o nome da federação.
     *
     * @return Nome da federação.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Obtém uma cópia da lista de produtos da federação.
     *
     * @return Lista de produtos.
     */
    public List<Produto> getListaProdutos() {
        return new ArrayList<>(this.lstProdutos);
    }

    /**
     * Obtém uma cópia da lista de instituições da federação.
     *
     * @return Lista de instituições.
     */
    public List<Instituicao> getInstituicoes() {
        return new ArrayList<>(this.instituicoes);
    }

    /**
     * Obtém a escala diária atual da federação.
     *
     * @return Escala diária atual.
     */
    public EscalaDiaria getEscalaAtual() {
        return this.escalaAtual;
    }

    /**
     * Obtém uma cópia da lista de todas as barracas da federação.
     *
     * @return Lista de barracas.
     */
    public List<Barraca> getTodasBarracas() {
        return new ArrayList<>(this.todasBarracas);
    }

    /**
     * Obtém uma cópia da lista de todas as escalas diárias da federação.
     *
     * @return Lista de escalas diárias.
     */
    public List<EscalaDiaria> getEscalas() {
        return new ArrayList<>(this.escalas);
    }

    /**
     * Obtém uma cópia da lista de administradores da federação.
     *
     * @return Lista de administradores.
     */
    public List<Administrador> getAdministradores() {
        return new ArrayList<>(this.administradores);
    }

    // Setters

    /**
     * Define o nome da federação.
     *
     * @param nome Novo nome da federação.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Define a escala diária atual da federação.
     *
     * @param escalaAtual Nova escala diária atual.
     */
    public void setEscalaAtual(EscalaDiaria escalaAtual) {
        this.escalaAtual = escalaAtual;
    }

    // Operações

    /**
     * Adiciona um administrador à federação, se ainda não existir.
     *
     * @param admin Administrador a adicionar.
     */
    public void adicionarAdministrador(Administrador admin) {
        if (admin != null && !administradores.contains(admin)) {
            this.administradores.add(admin);
        }
    }

    /**
     * Valida as credenciais de login de um administrador.
     *
     * @param nome Nome do administrador.
     * @param numero Número do administrador.
     * @param senha Senha do administrador.
     * @param curso Curso do administrador.
     * @return True se as credenciais forem válidas, falso caso contrário.
     */
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

    /**
     * Valida as credenciais de login de um voluntário (stock ou vendas).
     *
     * @param nome Nome do voluntário.
     * @param numero Número do voluntário.
     * @param senha Senha do voluntário.
     * @param curso Curso do voluntário.
     * @param isVendas True se for voluntário de vendas, falso se for de stock.
     * @return True se as credenciais forem válidas, falso caso contrário.
     */
    public boolean validarLoginVoluntario(String nome, int numero, String senha, String curso, boolean isVendas) {
        Voluntario voluntario = this.buscarVoluntarioPorNumeroAluno(numero);
        return voluntario != null &&
                ((isVendas && voluntario instanceof VoluntarioVendas) || (!isVendas && voluntario instanceof VoluntarioStock)) &&
                voluntario.getNome().equals(nome) &&
                voluntario.getSenha().equals(senha) &&
                voluntario.getCurso().equals(curso);
    }

    /**
     * Adiciona um produto à lista de produtos da federação, se ainda não existir.
     *
     * @param produto Produto a adicionar.
     */
    public void adicionarProduto(Produto produto) {
        if (produto != null && !listaContemProduto(produto.getNome())) {
            this.lstProdutos.add(new Produto(produto));
        }
    }

    /**
     * Verifica se a lista de produtos contém um produto com o nome especificado.
     *
     * @param nomeProduto Nome do produto a verificar.
     * @return True se existir produto com o nome, falso caso contrário.
     */
    public boolean listaContemProduto(String nomeProduto) {
        if (nomeProduto == null) return false;
        for (Produto p : this.lstProdutos) {
            if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adiciona uma instituição à federação, associando suas barracas.
     *
     * @param instituicao Instituição a adicionar.
     */
    public void adicionarInstituicao(Instituicao instituicao) {
        if (instituicao != null && !listaContemInstituicao(instituicao.getNome())) {
            this.instituicoes.add(instituicao);
            instituicao.setFederacao(this);
            for (Barraca barraca : instituicao.getBarracas()) {
                this.adicionarBarraca(barraca);
            }
        }
    }

    /**
     * Adiciona uma barraca à federação, se ainda não existir.
     *
     * @param barraca Barraca a adicionar.
     */
    public void adicionarBarraca(Barraca barraca) {
        if (barraca != null && !todasBarracas.contains(barraca)) {
            this.todasBarracas.add(barraca);
        }
    }

    /**
     * Verifica se a lista de instituições contém uma instituição com o nome especificado.
     *
     * @param nomeInstituicao Nome da instituição a verificar.
     * @return True se existir instituição com o nome, falso caso contrário.
     */
    public boolean listaContemInstituicao(String nomeInstituicao) {
        if (nomeInstituicao == null) return false;
        for (Instituicao i : this.instituicoes) {
            if (i.getNome().equalsIgnoreCase(nomeInstituicao)) {
                return true;
            }
        }
        return false;
    }

    // Busca de voluntários

    /**
     * Busca voluntário de vendas pelo número do aluno.
     *
     * @param numeroAluno Número do aluno.
     * @return Voluntário de vendas correspondente ou null.
     */
    public VoluntarioVendas buscarVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : this.instituicoes) {
            VoluntarioVendas v = inst.getVoluntarioVendasPorNumeroAluno(numeroAluno);
            if (v != null) return v;
        }
        return null;
    }

    /**
     * Busca voluntário de stock pelo número do aluno.
     *
     * @param numeroAluno Número do aluno.
     * @return Voluntário de stock correspondente ou null.
     */
    public VoluntarioStock buscarVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : this.instituicoes) {
            VoluntarioStock v = inst.getVoluntarioStockPorNumeroAluno(numeroAluno);
            if (v != null) return v;
        }
        return null;
    }

    /**
     * Busca voluntário (vendas ou stock) pelo número do aluno.
     *
     * @param numeroAluno Número do aluno.
     * @return Voluntário correspondente ou null.
     */
    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Instituicao inst : this.instituicoes) {
            Voluntario v = inst.buscarVoluntarioPorNumeroAluno(numeroAluno);
            if (v != null) return v;
        }
        return null;
    }

    /**
     * Adiciona uma nova escala diária à federação, se ainda não existir.
     *
     * @param escala Escala a adicionar.
     */
    public void adicionarEscala(EscalaDiaria escala) {
        if (escala != null && !escalas.contains(escala)) {
            this.escalas.add(escala);
        }
    }

    /**
     * Procura uma escala pela data.
     *
     * @param data Data da escala (string).
     * @return Escala correspondente ou null se não encontrar.
     */
    public EscalaDiaria buscarEscalaPorData(String data) {
        if (data == null) return null;
        for (EscalaDiaria escala : this.escalas) {
            if (escala.getData().toString().equals(data)) {
                return escala;
            }
        }
        return null;
    }

    /**
     * Compara esta federação com outra pela ordem lexicográfica do nome.
     *
     * @param outra Outra federação para comparar.
     * @return Resultado da comparação.
     */
    @Override
    public int compareTo(Federacao outra) {
        return this.nome.compareToIgnoreCase(outra.nome);
    }

    /**
     * Verifica se esta federação é igual a outro objeto (baseado no nome).
     *
     * @param o Objeto a comparar.
     * @return True se forem iguais, falso caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Federacao)) return false;
        Federacao that = (Federacao) o;
        return this.nome.equals(that.nome);
    }

    /**
     * Gera uma representação em string com informações básicas da federação.
     *
     * @return String com informações da federação.
     */
    @Override
    public String toString() {
        return "Federacao{" +
                "nome='" + nome + '\'' +
                ", numAdministradores=" + administradores.size() +
                ", numProdutos=" + lstProdutos.size() +
                ", numInstituicoes=" + instituicoes.size() +
                ", numBarracas=" + todasBarracas.size() +
                ", numEscalas=" + escalas.size() +
                '}';
    }
}
