package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.List;

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
        try {
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

            // Verificar se o produto já existe no stock
            for (StockProdutos sp : barracaSelecionada.getStock()) {
                if (sp.getNome().equalsIgnoreCase(produtoSelecionado.getNome())) {
                    throw new IllegalStateException("Produto já existe no stock. Use a opção de repor stock.");
                }
            }

            int quantidade = Utils.readIntFromConsole("Quantidade: ");
            if (quantidade <= 0) {
                throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
            }

            StockProdutos novoStock = new StockProdutos(produtoSelecionado.getNome(), produtoSelecionado.getPrecoUnitario(), quantidade);
            barracaSelecionada.adicionarStock(novoStock);
            System.out.println("Produto " + produtoSelecionado.getNome() + " adicionado ao stock da barraca " + barracaSelecionada.getNome() + ".");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void reporStock() {
        try {
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
                throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
            }

            boolean encontrado = false;
            for (StockProdutos sp : barracaSelecionada.getStock()) {
                if (sp.getNome().equalsIgnoreCase(produtoSelecionado.getNome())) {
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
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void verStock() {
        try {
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
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}