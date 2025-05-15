package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.Utils;

public class MenuInicial_UI {
    private Federacao federacao;
    private String opcao;

    public MenuInicial_UI(Federacao federacao) {
        this.federacao = federacao;
    }

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

    private boolean realizarLoginAdministrador() {
        System.out.println("\n### Login Administrador ###");
        String nome = Utils.readLineFromConsole("Nome: ");
        int numero = Utils.readIntFromConsole("Número: ");
        String senha = Utils.readLineFromConsole("Senha: ");
        String curso = Utils.readLineFromConsole("Curso: ");

        return federacao.validarLoginAdministrador(nome, numero, senha, curso);
    }

    private boolean realizarLoginVoluntario(boolean isVendas) {
        System.out.println("\n### Login Voluntário ###");
        String nome = Utils.readLineFromConsole("Nome: ");
        int numero = Utils.readIntFromConsole("Número de aluno: ");
        String senha = Utils.readLineFromConsole("Senha: ");
        String curso = Utils.readLineFromConsole("Curso: ");

        return federacao.validarLoginVoluntario(nome, numero, senha, curso, isVendas);
    }
}