package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.List;

/**
 * Classe que implementa o menu para gerir voluntários,
 * permitindo adicionar voluntários de stock e de vendas
 * às instituições da federação.
 */
public class MenuVoluntarios {
    private Federacao federacao;
    private String opcao;

    /**
     * Construtor da classe MenuVoluntarios.
     *
     * @param federacao Objeto Federacao que contém as instituições e voluntários.
     */
    public MenuVoluntarios(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o loop do menu, mostrando as opções ao utilizador
     * e respondendo às escolhas feitas até que o utilizador escolha sair.
     */
    public void run() {
        do {
            System.out.println("###### MENU VOLUNTÁRIOS #####");
            System.out.println("1. Adicionar Voluntário de Stock");
            System.out.println("2. Adicionar Voluntário de Vendas");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                adicionarVoluntarioStock();
            } else if (opcao.equals("2")) {
                adicionarVoluntarioVendas();
            }
        } while (!opcao.equals("0"));
    }

    /**
     * Adiciona um voluntário do tipo Stock a uma instituição da federação.
     * <p>
     * Realiza validação do número do aluno e do nome do voluntário, permite
     * escolher a instituição, verifica se o voluntário já existe, e adiciona-o
     * à lista da instituição escolhida.
     * </p>
     * <p>
     * Em caso de entrada inválida, exibe mensagens de erro apropriadas.
     * </p>
     */
    private void adicionarVoluntarioStock() {
        try {
            String numStr = Utils.readLineFromConsole("Número do aluno: ");
            if (!numStr.matches("\\d+")) {
                throw new ExcecaoNumeroAluno();
            }
            int numeroAluno = Integer.parseInt(numStr);

            String nome = Utils.readLineFromConsole("Nome do voluntário: ");
            if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                throw new ExcecaoNome();
            }

            String curso = Utils.readLineFromConsole("Curso do voluntário: ");
            String senha = Utils.readLineFromConsole("Senha do voluntário: ");

            Instituicao instituicaoEscolhida = escolherInstituicao();
            if (instituicaoEscolhida == null) {
                System.out.println("Operação cancelada.");
                return;
            }

            if (instituicaoEscolhida.listaContemVoluntario(numeroAluno)) {
                System.out.println("Voluntário com esse número já existe na instituição.");
                return;
            }

            VoluntarioStock voluntario = new VoluntarioStock(nome, numeroAluno, instituicaoEscolhida, curso, senha);
            instituicaoEscolhida.adicionarVoluntario(voluntario);

            System.out.println("Voluntário de Stock adicionado com sucesso à instituição " + instituicaoEscolhida.getNome());
        } catch (ExcecaoNumeroAluno | ExcecaoNome e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Adiciona um voluntário do tipo Vendas a uma instituição da federação.
     * <p>
     * Realiza validação do número do aluno e do nome do voluntário, permite
     * escolher a instituição, verifica se o voluntário já existe, e adiciona-o
     * à lista da instituição escolhida.
     * </p>
     * <p>
     * Em caso de entrada inválida, exibe mensagens de erro apropriadas.
     * </p>
     */
    private void adicionarVoluntarioVendas() {
        try {
            String numStr = Utils.readLineFromConsole("Número do aluno: ");
            if (!numStr.matches("\\d+")) {
                throw new ExcecaoNumeroAluno();
            }
            int numeroAluno = Integer.parseInt(numStr);

            String nome = Utils.readLineFromConsole("Nome do voluntário: ");
            if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                throw new ExcecaoNome();
            }

            String curso = Utils.readLineFromConsole("Curso do voluntário: ");
            String senha = Utils.readLineFromConsole("Senha do voluntário: ");

            Instituicao instituicaoEscolhida = escolherInstituicao();
            if (instituicaoEscolhida == null) {
                System.out.println("Operação cancelada.");
                return;
            }

            if (instituicaoEscolhida.listaContemVoluntario(numeroAluno)) {
                System.out.println("Voluntário com esse número já existe na instituição.");
                return;
            }

            VoluntarioVendas voluntario = new VoluntarioVendas(nome, numeroAluno, instituicaoEscolhida, curso, senha);
            instituicaoEscolhida.adicionarVoluntario(voluntario);

            System.out.println("Voluntário de Vendas adicionado com sucesso à instituição " + instituicaoEscolhida.getNome());
        } catch (ExcecaoNumeroAluno | ExcecaoNome e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Apresenta uma lista das instituições registadas na federação e permite
     * que o utilizador escolha uma instituição para associar ao voluntário.
     *
     * @return A instituição escolhida pelo utilizador, ou null se a escolha for cancelada
     *         ou se não houver instituições registadas.
     */
    private Instituicao escolherInstituicao() {
        List<Instituicao> instituicoes = federacao.getInstituicoes();
        if (instituicoes.isEmpty()) {
            System.out.println("Não existem instituições registadas.");
            return null;
        }

        System.out.println("Escolha a instituição:");
        for (int i = 0; i < instituicoes.size(); i++) {
            System.out.println((i + 1) + ". " + instituicoes.get(i).getNome());
        }

        int opcaoInstituicao = Utils.readIntFromConsole("Opção (0 para cancelar): ");
        if (opcaoInstituicao < 1 || opcaoInstituicao > instituicoes.size()) {
            return null;
        }

        return instituicoes.get(opcaoInstituicao - 1);
    }
}
