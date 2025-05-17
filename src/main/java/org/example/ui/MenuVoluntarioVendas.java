package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

/**
 * Classe que implementa o menu interativo para os voluntários responsáveis pelas vendas,
 * permitindo fazer vendas e consultar o total de vendas realizadas por um voluntário.
 */
public class MenuVoluntarioVendas {
    private Federacao federacao;
    private String opcao;

    /**
     * Construtor que recebe a federação para gerir voluntários e as respetivas barracas.
     *
     * @param federacao Instância da federação que contém os voluntários.
     */
    public MenuVoluntarioVendas(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Método principal que apresenta o menu e executa as ações até o utilizador escolher sair.
     */
    public void run() {
        do {
            System.out.println("\n###### MENU VOLUNTÁRIO VENDAS #####");
            System.out.println("1. Fazer venda");
            System.out.println("2. Ver total de vendas de um voluntário");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                fazerVenda();
            } else if (opcao.equals("2")) {
                verTotalVendas();
            } else if (!opcao.equals("0")) {
                System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    /**
     * Permite ao voluntário registrar uma venda de um produto.
     * Valida se o voluntário e a barraca existem, e verifica a validade dos dados fornecidos.
     */
    private void fazerVenda() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioVendas voluntario = federacao.buscarVoluntarioVendasPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("Voluntário sem barraca.");
            return;
        }

        String nomeProduto = Utils.readLineFromConsole("Nome do produto: ");
        if (nomeProduto == null || nomeProduto.isEmpty()) {
            System.out.println("Nome do produto inválido.");
            return;
        }

        int quantidade = Utils.readIntFromConsole("Quantidade vendida: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        voluntario.registarVenda(nomeProduto, quantidade);
        System.out.println("Venda registrada com sucesso.");
    }

    /**
     * Mostra o total acumulado de vendas efetuadas por um voluntário.
     */
    private void verTotalVendas() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioVendas voluntario = federacao.buscarVoluntarioVendasPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        double total = voluntario.getTotalVendas();
        System.out.println("Total de vendas: " + total + "€");
    }
}
