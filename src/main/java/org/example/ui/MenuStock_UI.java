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
        if (federacao == null) {
            throw new IllegalArgumentException("Federação não pode ser nula");
        }
        this.federacao = federacao;
        this.opcao = "";
    }

    public void run() {
        do {
            try {
                System.out.println("\n###### MENU GERENCIAR STOCK #####");
                System.out.println("1. Adicionar novo produto ao stock");
                System.out.println("2. Repor stock existente");
                System.out.println("3. Ver stock de uma barraca");
                System.out.println("0. Voltar");

                opcao = Utils.readLineFromConsole("Escolha uma opção: ");
                if (opcao == null) {
                    throw new IllegalArgumentException("Opção não pode ser nula");
                }

                switch (opcao.trim()) {
                    case "1":
                        adicionarNovoProduto();
                        break;
                    case "2":
                        reporStock();
                        break;
                    case "3":
                        verStock();
                        break;
                    case "0":
                        System.out.println("A voltar ao menu anterior...");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha 0, 1, 2 ou 3.");
                }

                if (!opcao.trim().equals("0")) {
                    Utils.readLineFromConsole("Pressione Enter para continuar...");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro de entrada: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        } while (!opcao.trim().equals("0"));
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

            String nomeProduto = Utils.readLineFromConsole("Nome do produto: ");
            if (nomeProduto == null || nomeProduto.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do produto não pode ser vazio");
            }

            // Verificar se o produto existe na federação
            if (!federacao.listaContemProduto(nomeProduto)) {
                throw new ExcecaoProdutoNaoExistente("Produto '" + nomeProduto + "' não registrado na federação");
            }

            String precoStr = Utils.readLineFromConsole("Preço unitário: ");
            if (precoStr == null || precoStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Preço unitário não pode ser vazio");
            }
            double precoUnitario = Double.parseDouble(precoStr);
            if (precoUnitario <= 0) {
                throw new ExcecaoPreco();
            }

            String quantidadeStr = Utils.readLineFromConsole("Quantidade: ");
            if (quantidadeStr == null || quantidadeStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Quantidade não pode ser vazia");
            }
            int quantidade = Integer.parseInt(quantidadeStr);
            if (quantidade <= 0) {
                throw new ExcecaoNumeroVendasProdutos();
            }

            for (StockProdutos sp : barracaSelecionada.getStock()) {
                if (sp.getNome().equalsIgnoreCase(nomeProduto)) {
                    throw new ExcecaoProdutoNaoExistente("Produto '" + nomeProduto + "' já existe no stock da barraca. Use a opção de repor stock.");
                }
            }

            StockProdutos novoStock = new StockProdutos(nomeProduto, precoUnitario, quantidade);
            barracaSelecionada.adicionarStock(novoStock);
            System.out.println("Produto " + nomeProduto + " adicionado ao stock da barraca " + barracaSelecionada.getNome() + ".");
        } catch (ExcecaoPreco e) {
            System.out.println("Erro no preço: " + e.getMessage());
        } catch (ExcecaoNumeroVendasProdutos e) {
            System.out.println("Erro na quantidade: " + e.getMessage());
        } catch (ExcecaoProdutoNaoExistente e) {
            System.out.println("Erro no produto: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Preço ou quantidade deve ser um valor numérico válido");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
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

            String quantidadeStr = Utils.readLineFromConsole("Quantidade a adicionar: ");
            if (quantidadeStr == null || quantidadeStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Quantidade não pode ser vazia");
            }
            int quantidade = Integer.parseInt(quantidadeStr);
            if (quantidade <= 0) {
                throw new ExcecaoNumeroVendasProdutos();
            }

            produtoSelecionado.setQuantidade(produtoSelecionado.getQuantidade() + quantidade);
            System.out.println("Estoque de " + produtoSelecionado.getNome() + " atualizado com " + quantidade + " unidades na barraca " + barracaSelecionada.getNome() + ".");
        } catch (ExcecaoNumeroVendasProdutos e) {
            System.out.println("Erro na quantidade: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Quantidade deve ser um valor numérico válido");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
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
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
    }
}