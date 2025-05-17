package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.List;

/**
 * Interface de utilizador para gerir o stock de produtos nas barracas da federação.
 * Permite adicionar novos produtos ao stock, repor stock existente e visualizar o stock
 * de uma barraca específica.
 */
public class MenuStock_UI {

    private Federacao federacao;
    private String opcao;

    public MenuStock_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        do {
            System.out.println("\n###### MENU GERENCIAR STOCK #####");
            System.out.println("1. Adicionar novo produto ao stock");
            System.out.println("2. Repor stock existente");
            System.out.println("3. Ver stock de uma barraca");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                adicionarNovoProduto();
            } else if (opcao.equals("2")) {
                reporStock();
            } else if (opcao.equals("3")) {
                verStock();
            } else if (!opcao.equals("0")) {
                System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void adicionarNovoProduto() {
        List<Barraca> barracas = federacao.getTodasBarracas();
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca registrada.");
            return;
        }

        Utils.apresentaLista(barracas, "Barracas disponíveis:");
        Barraca barracaSelecionada = (Barraca) Utils.selecionaObject(barracas);
        if (barracaSelecionada == null) {
            System.out.println("Operação cancelada.");
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

        for (StockProdutos sp : barracaSelecionada.getStock()) {
            if (sp.getNome().equals(nomeProduto)) {
                System.out.println("Produto já existe no stock. Use a opção de repor stock.");
                return;
            }
        }

        StockProdutos novoStock = new StockProdutos(nomeProduto, precoUnitario, quantidade);
        barracaSelecionada.adicionarStock(novoStock);
        System.out.println("Produto " + nomeProduto + " adicionado ao stock da barraca " + barracaSelecionada.getNome() + ".");
    }

    private void reporStock() {
        List<Barraca> barracas = federacao.getTodasBarracas();
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca registrada.");
            return;
        }

        Utils.apresentaLista(barracas, "Barracas disponíveis:");
        Barraca barracaSelecionada = (Barraca) Utils.selecionaObject(barracas);
        if (barracaSelecionada == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        List<StockProdutos> stock = barracaSelecionada.getStock();
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
        System.out.println("Estoque de " + produtoSelecionado.getNome() + " atualizado com " + quantidade + " unidades na barraca " + barracaSelecionada.getNome() + ".");
    }

    private void verStock() {
        List<Barraca> barracas = federacao.getTodasBarracas();
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca registrada.");
            return;
        }

        Utils.apresentaLista(barracas, "Barracas disponíveis:");
        Barraca barracaSelecionada = (Barraca) Utils.selecionaObject(barracas);
        if (barracaSelecionada == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        List<StockProdutos> stock = barracaSelecionada.getStock();
        System.out.println("### Stock da barraca " + barracaSelecionada.getNome() + " ###");
        if (stock.isEmpty()) {
            System.out.println("Sem produtos registados.");
        } else {
            for (StockProdutos sp : stock) {
                System.out.println(sp.getNome() + ": " + sp.getQuantidade() + " unidades, Preço: " + sp.getPrecoUnitario() + "€");
            }
        }
    }
}