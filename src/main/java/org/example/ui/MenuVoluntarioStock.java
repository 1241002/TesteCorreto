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
            System.out.println("Voluntário sem barraca associada.");
            return;
        }

        List<Produto> produtosDisponiveis = voluntario.getInstituicao().getLstProdutos();
        if (produtosDisponiveis.isEmpty()) {
            System.out.println("Nenhum produto registrado na instituição.");
            return;
        }

        Utils.apresentaLista(produtosDisponiveis, "Produtos disponíveis:");
        Produto produtoSelecionado = (Produto) Utils.selecionaObject(produtosDisponiveis);
        if (produtoSelecionado == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        int quantidade = Utils.readIntFromConsole("Quantidade: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        voluntario.adicionarProdutoAoStock(produtoSelecionado.getNome(), produtoSelecionado.getPrecoUnitario(), quantidade);
        System.out.println("Produto " + produtoSelecionado.getNome() + " adicionado ao stock.");
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
            System.out.println("Voluntário sem barraca associada.");
            return;
        }

        List<Produto> produtosDisponiveis = voluntario.getInstituicao().getLstProdutos();
        if (produtosDisponiveis.isEmpty()) {
            System.out.println("Nenhum produto registrado na instituição.");
            return;
        }

        Utils.apresentaLista(produtosDisponiveis, "Produtos disponíveis:");
        Produto produtoSelecionado = (Produto) Utils.selecionaObject(produtosDisponiveis);
        if (produtoSelecionado == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        int quantidade = Utils.readIntFromConsole("Quantidade a adicionar: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        voluntario.reporProduto(produtoSelecionado.getNome(), quantidade);
        System.out.println("Estoque de " + produtoSelecionado.getNome() + " atualizado.");
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