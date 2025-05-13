package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.List;

public class MenuVoluntarioStock {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarioStock(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        do {
            System.out.println("\n###### MENU VOLUNTÁRIO STOCK #####");
            System.out.println("1. Adicionar novo produto ao stock");
            System.out.println("2. Repor stock existente");
            System.out.println("3. Ver stock atual");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                adicionarNovoProduto();
            } else if (opcao.equals("2")) {
                reporStock();
            } else if (opcao.equals("3")) {
                verStockAtual();
            } else if (!opcao.equals("0")) {
                System.out.println("Opção inválida!");
            }

        } while (!opcao.equals("0"));
    }

    private void adicionarNovoProduto() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("Voluntário sem barraca.");
            return;
        }

        String nome = Utils.readLineFromConsole("Nome do produto: ");
        double preco = Utils.readDoubleFromConsole("Preço unitário: ");
        int quantidade = Utils.readIntFromConsole("Quantidade: ");

        if (preco <= 0 || quantidade < 0) {
            System.out.println("Dados inválidos.");
            return;
        }

        List<StockProdutos> stock = barraca.getStock();
        for (int i = 0; i < stock.size(); i++) {
            if (stock.get(i).getNome().equalsIgnoreCase(nome)) {
                System.out.println("Produto já existe.");
                return;
            }
        }

        StockProdutos novo = new StockProdutos(nome, preco, quantidade);
        voluntario.adicionarProdutoAoStock(novo);
        barraca.adicionarStock(nome, preco, quantidade);
        System.out.println("Produto adicionado.");
    }

    private void reporStock() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("Voluntário sem barraca.");
            return;
        }

        String nome = Utils.readLineFromConsole("Nome do produto: ");
        int quantidade = Utils.readIntFromConsole("Quantidade a adicionar: ");

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        voluntario.reporProduto(nome, quantidade);
    }

    private void verStockAtual() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("Voluntário sem barraca.");
            return;
        }

        List<StockProdutos> stock = barraca.getStock();
        System.out.println("### Stock da barraca " + barraca.getNome() + " ###");

        if (stock.isEmpty()) {
            System.out.println("Sem produtos registados.");
        } else {
            for (int i = 0; i < stock.size(); i++) {
                StockProdutos sp = stock.get(i);
                System.out.println(sp.getNome() + ": " + sp.getQuantidade() + " unidades, Preço: " + sp.getPrecoUnitario() + "€");
            }
        }
    }
}
