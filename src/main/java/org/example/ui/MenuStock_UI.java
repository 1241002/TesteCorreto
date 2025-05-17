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

    /**
     * Instância da federação que contém as barracas e produtos.
     */
    private Federacao federacao;

    /**
     * Opção escolhida pelo utilizador no menu.
     */
    private String opcao;

    /**
     * Construtor que recebe a federação onde as operações serão realizadas.
     *
     * @param federacao Instância da federação com barracas e produtos.
     */
    public MenuStock_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o menu principal de gestão de stock, exibindo opções ao utilizador
     * até que este escolha sair.
     */
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

    /**
     * Adiciona um novo produto ao stock de uma barraca selecionada pelo utilizador.
     * Verifica se o produto já não existe no stock antes de adicionar.
     */
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

        List<Produto> produtosDisponiveis = barracaSelecionada.getInstituicao().getLstProdutos();
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

        for (StockProdutos sp : barracaSelecionada.getStock()) {
            if (sp.getNome().equals(produtoSelecionado.getNome())) {
                System.out.println("Produto já existe no stock. Use a opção de repor stock.");
                return;
            }
        }

        int quantidade = Utils.readIntFromConsole("Quantidade: ");
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero.");
            return;
        }

        StockProdutos novoStock = new StockProdutos(produtoSelecionado.getNome(), produtoSelecionado.getPrecoUnitario(), quantidade);
        barracaSelecionada.adicionarStock(novoStock);
        System.out.println("Produto " + produtoSelecionado.getNome() + " adicionado ao stock da barraca " + barracaSelecionada.getNome() + ".");
    }

    /**
     * Repor o stock de um produto já existente numa barraca.
     * Se o produto não existir no stock, adiciona-o como novo.
     */
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

        List<Produto> produtosDisponiveis = barracaSelecionada.getInstituicao().getLstProdutos();
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

        boolean encontrado = false;
        for (StockProdutos sp : barracaSelecionada.getStock()) {
            if (sp.getNome().equals(produtoSelecionado.getNome())) {
                sp.setQuantidade(sp.getQuantidade() + quantidade);
                encontrado = true;
                System.out.println("Estoque de " + produtoSelecionado.getNome() + " atualizado com " + quantidade + " unidades na barraca " + barracaSelecionada.getNome() + ".");
                break;
            }
        }
        if (!encontrado) {
            StockProdutos novoStock = new StockProdutos(produtoSelecionado.getNome(), produtoSelecionado.getPrecoUnitario(), quantidade);
            barracaSelecionada.adicionarStock(novoStock);
            System.out.println("Produto " + produtoSelecionado.getNome() + " adicionado ao stock da barraca " + barracaSelecionada.getNome() + " com " + quantidade + " unidades.");
        }
    }

    /**
     * Mostra o stock atual de produtos de uma barraca selecionada pelo utilizador.
     */
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
