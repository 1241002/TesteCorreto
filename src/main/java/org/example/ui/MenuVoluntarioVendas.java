package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

/**
 * Classe responsável pelo menu interativo para voluntários das vendas,
 * permitindo registar vendas e consultar o total de vendas de um voluntário.
 */
public class MenuVoluntarioVendas {
    private Federacao federacao;
    private String opcao;

    /**
     * Construtor que recebe a federação para gerir voluntários e instituições.
     *
     * @param federacao Instância da federação que contém as instituições e voluntários.
     */
    public MenuVoluntarioVendas(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Método principal que executa o menu, mostrando opções até o utilizador escolher sair.
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
     * Permite ao voluntário registar uma venda.
     * Solicita número do aluno, valida existência do voluntário e barraca,
     * depois solicita nome do produto e quantidade vendida com validação.
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
            System.out.println("Voluntário sem barraca associada.");
            return;
        }

        String nomeProduto = Utils.readLineFromConsole("Nome do produto: ");
        if (nomeProduto == null || nomeProduto.trim().isEmpty()) {
            System.out.println("Nome do produto inválido.");
            return;
        }

        // Validação manual da quantidade com exceção personalizada
        int quantidade;
        try {
            String quantidadeInput = Utils.readLineFromConsole("Quantidade vendida: ");
            if (!quantidadeInput.matches("\\d+")) {
                throw new ExecaoQuantidade("Quantidade inválida. Deve ser um número inteiro positivo.");
            }
            quantidade = Integer.parseInt(quantidadeInput);
            if (quantidade <= 0) {
                throw new ExecaoQuantidade("Quantidade deve ser maior que zero.");
            }
        } catch (ExecaoQuantidade e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Erro inesperado ao ler a quantidade.");
            return;
        }

        voluntario.registarVenda(nomeProduto, quantidade);
        System.out.println("Venda registrada com sucesso.");
    }

    /**
     * Mostra o total acumulado de vendas de um voluntário, identificado pelo número de aluno.
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
