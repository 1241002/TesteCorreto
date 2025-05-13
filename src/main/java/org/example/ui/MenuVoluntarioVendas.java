package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;
import java.io.IOException;

public class MenuVoluntarioVendas {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarioVendas(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
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
        int numeroAluno = Utils.readIntFromConsole("Número do aluno do voluntário: ");
        VoluntarioVendas voluntario = federacao.buscarVoluntarioVendasPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("O voluntário não está associado a nenhuma barraca.");
            return;
        }

        String nomeProduto = Utils.readLineFromConsole("Nome do produto a vender: ");
        int quantidade = Utils.readIntFromConsole("Quantidade vendida: ");

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        boolean encontrado = false;

        for (StockProdutos stock : barraca.getStock()) {
            if (stock.getNome().equalsIgnoreCase(nomeProduto)) {
                encontrado = true;

                if (stock.getQuantidade() >= quantidade) {
                    double preco = stock.getPrecoUnitario();
                    stock.setQuantidade(stock.getQuantidade() - quantidade);
                    voluntario.registarVenda(nomeProduto, quantidade, preco);
                    System.out.printf("Venda registada: %s - %d x %.2f€\n", nomeProduto, quantidade, preco);
                } else {
                    System.out.println("Quantidade insuficiente em stock.");
                }

                break;
            }
        }

        if (!encontrado) {
            System.out.println("Produto não encontrado.");
        }
    }

    private void verTotalVendas() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno do voluntário: ");
        VoluntarioVendas voluntario = federacao.buscarVoluntarioVendasPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        double total = voluntario.getTotalVendas();
        System.out.println("Total de vendas: " + String.format("%.2f€", total));
    }
}
