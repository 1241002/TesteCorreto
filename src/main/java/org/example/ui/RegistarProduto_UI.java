package org.example.ui;

import org.example.model.ExcecaoPreco;
import org.example.model.ExcecaoProdutoNaoExistente;
import org.example.model.Federacao;
import org.example.model.Produto;
import org.example.utils.Utils;
import org.example.model.ExcecaoNome;

/**
 * Interface de utilizador para registar um novo produto na federação.
 * Permite ao utilizador introduzir os dados do produto, validar as entradas,
 * apresentar os dados para confirmação e, caso confirmado, adicionar o produto
 * à federação.
 */
public class RegistarProduto_UI {
    private Federacao federacao;

    /**
     * Construtor que inicializa o interface com uma instância da federação.
     *
     * @param federacao Instância da federação onde o produto será registado.
     * @throws IllegalArgumentException se a federação for nula.
     */
    public RegistarProduto_UI(Federacao federacao) {
        if (federacao == null) {
            throw new IllegalArgumentException("Federação não pode ser nula");
        }
        this.federacao = federacao;
    }

    /**
     * Executa o processo completo de registo de um produto.
     * Solicita o nome e preço do produto, valida-os, verifica se o produto já existe,
     * apresenta os dados para confirmação e, se confirmado, adiciona o produto à federação.
     * Trata exceções específicas para informar o utilizador adequadamente.
     */
    public void run() {
        try {
            System.out.println("\nNovo Produto:");

            // Solicita nome do produto e valida
            String nome = Utils.readLineFromConsole("Introduza o nome do produto: ");
            if (nome == null || nome.trim().isEmpty()) {
                System.out.println("Nome do produto não pode ser vazio");
                return;
            }
            if (!nome.matches("[\\p{L} \\-']+")) {
                throw new ExcecaoNome();
            }

            // Verifica se produto já existe
            if (federacao.listaContemProduto(nome)) {
                System.out.println("Produto '" + nome + "' já existe na federação.");
                return;
            }

            // Solicita preço do produto e valida
            String precoStr = Utils.readLineFromConsole("Introduza o preço: ");
            if (precoStr == null || precoStr.trim().isEmpty()) {
                System.out.println("Preço não pode ser vazio");
                return;
            }
            double preco = Double.parseDouble(precoStr);
            if (preco <= 0) {
                throw new ExcecaoPreco();
            }

            Produto novoProduto = new Produto(nome, preco);

            apresentaDados(novoProduto);

            if (Utils.confirma("Confirma os dados? (S/N)")) {
                federacao.adicionarProduto(novoProduto);
                System.out.println("Dados do produto guardados com sucesso.");
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (ExcecaoPreco e) {
            System.out.println("Erro no preço: " + e.getMessage());
        } catch (ExcecaoNome e) {
            System.out.println("Erro no nome do produto: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Erro: Preço deve ser um valor numérico válido");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        Utils.readLineFromConsole("Pressione Enter para continuar...");
    }

    /**
     * Apresenta os dados do produto ao utilizador para revisão.
     *
     * @param produto Produto cujos dados serão apresentados.
     */
    private void apresentaDados(Produto produto) {
        if (produto != null) {
            System.out.printf("Produto: %s, Preço: %.2f€%n", produto.getNome(), produto.getPrecoUnitario());
        }
    }
}
