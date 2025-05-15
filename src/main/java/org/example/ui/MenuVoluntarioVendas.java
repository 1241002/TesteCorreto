package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

public class MenuVoluntarioVendas {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarioVendas(Federacao federacao) {
        this.federacao = federacao;
    }

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