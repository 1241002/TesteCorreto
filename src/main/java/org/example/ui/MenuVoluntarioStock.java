package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.List;

/**
 * Classe responsável pelo menu interativo para voluntários do stock,
 * permitindo adicionar novos produtos ao stock, repor stock existente e visualizar o stock atual.
 */
public class MenuVoluntarioStock {
    private Federacao federacao;
    private String opcao;

    /**
     * Construtor que recebe a federação para gerir os voluntários e instituições.
     *
     * @param federacao Instância da federação que contém as instituições e voluntários.
     */
    public MenuVoluntarioStock(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Método principal que executa o menu, mostrando opções até o utilizador escolher sair.
     */
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

    /**
     * Permite ao voluntário adicionar um novo produto ao stock da sua barraca.
     * Solicita nome, preço unitário e quantidade diretamente, sem depender da instituição.
     */
    private void adicionarNovoProduto() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

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
            System.out.println("Nome do produto não pode ser vazio.");
            return;
        }

        double precoUnitario = Utils.readDoubleFromConsole("Preço unitário: ");
        if (precoUnitario <= 0) {
            System.out.println("Preço deve ser maior que zero.");
            return;
        }

        int quantidade = Utils.readIntFromConsole("Quantidade: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        // Verificar se o produto já existe no estoque
        for (StockProdutos sp : barraca.getStock()) {
            if (sp.getNome().equals(nomeProduto)) {
                System.out.println("Produto já existe no stock. Use a opção de repor stock.");
                return;
            }
        }

        StockProdutos novoStock = new StockProdutos(nomeProduto, precoUnitario, quantidade);
        barraca.adicionarStock(novoStock);
        System.out.println("Produto " + nomeProduto + " adicionado ao stock da barraca " + barraca.getNome() + ".");
    }

    /**
     * Permite ao voluntário repor a quantidade de um produto já existente no stock da sua barraca.
     * Lista os produtos já no estoque da barraca.
     */
    private void reporStock() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("Voluntário sem barraca associada.");
            return;
        }

        List<StockProdutos> stock = barraca.getStock();
        if (stock.isEmpty()) {
            System.out.println("Nenhum produto registrado no stock da barraca.");
            return;
        }

        Utils.apresentaLista(stock, "Produtos no stock:");
        StockProdutos produtoSelecionado = (StockProdutos) Utils.selecionaObject(stock);
        if (produtoSelecionado == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        int quantidade = Utils.readIntFromConsole("Quantidade a adicionar: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        produtoSelecionado.setQuantidade(produtoSelecionado.getQuantidade() + quantidade);
        System.out.println("Estoque de " + produtoSelecionado.getNome() + " atualizado na barraca " + barraca.getNome() + ".");
    }

    /**
     * Mostra o stock atual da barraca associada ao voluntário identificado pelo número de aluno.
     * Exibe nome, quantidade e preço unitário de cada produto em stock.
     */
    private void verStockAtual() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("Voluntário sem barraca associada.");
            return;
        }

        List<StockProdutos> stock = barraca.getStock();
        System.out.println("### Stock da barraca " + barraca.getNome() + " ###");
        if (stock.isEmpty()) {
            System.out.println("Sem produtos registados.");
        } else {
            for (StockProdutos sp : stock) {
                System.out.println(sp.getNome() + ": " + sp.getQuantidade() + " unidades, Preço: " + sp.getPrecoUnitario() + "€");
            }
        }
    }
}