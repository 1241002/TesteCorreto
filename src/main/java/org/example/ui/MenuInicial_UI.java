package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.Utils;

/**
 * Classe responsável por apresentar o menu inicial da aplicação,
 * onde o utilizador pode escolher entre os diferentes tipos de acesso:
 * Administrador, Voluntário de Stock, Voluntário de Vendas, ou sair da aplicação.
 *
 * Também gere o processo de login para cada tipo de utilizador.
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
     * O ciclo termina quando o utilizador escolhe sair (opção "0").
     */
    public void run() {
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
                    } else {
                        System.out.println("Login inválido. Verifique nome, número, senha ou curso e tente novamente.");
                    }
                    break;
                case "2":
                    if (realizarLoginVoluntario(false)) {
                        new MenuVoluntarioStock(federacao).run();
                    } else {
                        System.out.println("Login inválido. Verifique nome, número, senha, curso ou tipo de voluntário e tente novamente.");
                    }
                    break;
                case "3":
                    if (realizarLoginVoluntario(true)) {
                        new MenuVoluntarioVendas(federacao).run();
                    } else {
                        System.out.println("Login inválido. Verifique nome, número, senha, curso ou tipo de voluntário e tente novamente.");
                    }
                    break;
                case "0":
                    System.out.println("A sair do menu.");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("0"));
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
