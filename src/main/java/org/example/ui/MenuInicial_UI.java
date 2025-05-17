package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.PersistenciaDados;
import org.example.utils.Utils;

import java.io.IOException;

/**
 * Classe responsável por apresentar o menu inicial da aplicação,
 * onde o utilizador pode escolher entre os diferentes tipos de acesso:
 * Administrador, Voluntário de Stock, Voluntário de Vendas, ou sair da aplicação.
 *
 * Também gere o processo de login para cada tipo de utilizador e a persistência dos dados.
 */
public class MenuInicial_UI {

    /**
     * Referência à federação que contém os dados e métodos para validação de login.
     */
    private Federacao federacao;

    /**
     * Opção escolhida pelo utilizador no menu.
     */
    private String opcao;

    /**
     * Construtor que inicializa o menu com a instância da federação.
     *
     * @param federacao Instância da federação usada para validação e gestão.
     */
    public MenuInicial_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o ciclo principal do menu inicial, apresentando as opções ao utilizador,
     * lendo a escolha, realizando os respetivos logins e encaminhando para os menus
     * correspondentes conforme o tipo de utilizador.
     *
     * Carrega os dados ao iniciar e salva após alterações ou ao sair.
     *
     * O ciclo termina quando o utilizador escolhe sair (opção "0").
     */
    public void run() {
        // Carregar dados existentes, se disponíveis
        try {
            Federacao dadosCarregados = PersistenciaDados.carregarDados();
            if (dadosCarregados != null) {
                this.federacao = dadosCarregados;
                System.out.println("Dados carregados com sucesso.");
            } else {
                System.out.println("Nenhum dado encontrado. Iniciando com nova federação.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            System.out.println("Iniciando com nova federação.");
        }

        do {
            System.out.println("###### MENU INICIAL #####");
            System.out.println("1. Administrador");
            System.out.println("2. Voluntário de Stock");
            System.out.println("3. Voluntário de Vendas");
            System.out.println("0. Sair");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    if (realizarLoginAdministrador()) {
                        new MenuAdministrador(federacao).run();
                        salvarDados(); // Salvar após alterações administrativas
                    } else {
                        System.out.println("Login inválido. Verifique nome, número, senha ou curso e tente novamente.");
                    }
                    break;
                case "2":
                    if (realizarLoginVoluntario(false)) {
                        new MenuVoluntarioStock(federacao).run();
                        salvarDados(); // Salvar após alterações de stock
                    } else {
                        System.out.println("Login inválido. Verifique nome, número, senha, curso ou tipo de voluntário e tente novamente.");
                    }
                    break;
                case "3":
                    if (realizarLoginVoluntario(true)) {
                        new MenuVoluntarioVendas(federacao).run();
                        salvarDados(); // Salvar após alterações de vendas
                    } else {
                        System.out.println("Login inválido. Verifique nome, número, senha, curso ou tipo de voluntário e tente novamente.");
                    }
                    break;
                case "0":
                    salvarDados(); // Salvar antes de sair
                    System.out.println("A sair do menu.");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("0"));
    }

    /**
     * Salva os dados da federação no arquivo.
     */
    private void salvarDados() {
        try {
            PersistenciaDados.salvarDados(federacao);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            // Exibir a stack trace para depuração (remover em produção)
            e.printStackTrace();
        }
    }

    /**
     * Lê os dados do administrador (nome, número, senha, curso) via consola e
     * valida o login junto da federação.
     *
     * @return true se o login for válido, false caso contrário.
     */
    private boolean realizarLoginAdministrador() {
        System.out.println("\n### Login Administrador ###");
        String nome = Utils.readLineFromConsole("Nome: ");
        int numero = Utils.readIntFromConsole("Número: ");
        String senha = Utils.readLineFromConsole("Senha: ");
        String curso = Utils.readLineFromConsole("Curso: ");

        return federacao.validarLoginAdministrador(nome, numero, senha, curso);
    }

    /**
     * Lê os dados do voluntário (nome, número de aluno, senha, curso) via consola e
     * valida o login junto da federação, diferenciando se é voluntário de vendas ou stock.
     *
     * @param isVendas true se for voluntário de vendas, false se for de stock.
     * @return true se o login for válido, false caso contrário.
     */
    private boolean realizarLoginVoluntario(boolean isVendas) {
        System.out.println("\n### Login Voluntário ###");
        String nome = Utils.readLineFromConsole("Nome: ");
        int numero = Utils.readIntFromConsole("Número de aluno: ");
        String senha = Utils.readLineFromConsole("Senha: ");
        String curso = Utils.readLineFromConsole("Curso: ");

        return federacao.validarLoginVoluntario(nome, numero, senha, curso, isVendas);
    }
}