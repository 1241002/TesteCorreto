package org.example.ui;

import org.example.model.ExcecaoPreco;
import org.example.model.ExcecaoProdutoNaoExistente;
import org.example.model.Federacao;
import org.example.model.Produto;
import org.example.utils.Utils;
import org.example.model.ExcecaoNome; // Importação da nova exceção

/**
 * Interface de utilizador para registar um novo produto na federação.
 * Permite introduzir os dados do produto, apresentar para confirmação e, se confirmado,
 * adicionar o produto à lista da federação.
 */
public class RegistarProduto_UI {
    private Federacao federacao;

    /**
     * Construtor que recebe a instância da federação onde o produto será registado.
     *
     * @param federacao Instância da federação para adicionar o produto.
     */
    public RegistarProduto_UI(Federacao federacao) {
        if (federacao == null) {
            throw new IllegalArgumentException("Federação não pode ser nula");
        }
        this.federacao = federacao;
    }

    /**
     * Executa o processo de registo de um novo produto.
     * Solicita os dados, apresenta-os para confirmação e guarda o produto se confirmado.
     */
    public void run() {
        try {
            System.out.println("\nNovo Produto:");
            Produto novoProduto = introduzDados();
            if (novoProduto == null) {
                System.out.println("Operação cancelada devido a dados inválidos.");
                return;
            }

            apresentaDados(novoProduto);

            // Verificação de produto existente antes da confirmação
            if (federacao.listaContemProduto(novoProduto.getNome())) {
                throw new ExcecaoProdutoNaoExistente("Produto '" + novoProduto.getNome() + "' já existe na federação");
            }

            // Trecho preservado exatamente como especificado
            if (Utils.confirma("Confirma os dados? (S/N)")) {
                federacao.adicionarProduto(novoProduto);
                System.out.println("Dados do produto guardados com sucesso.");
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (ExcecaoPreco e) {
            System.out.println("Erro no preço: " + e.getMessage());
        } catch (ExcecaoProdutoNaoExistente e) {
            System.out.println("Erro no produto: " + e.getMessage());
        } catch (ExcecaoNome e) { // Tratamento da nova exceção
            System.out.println("Erro no nome do produto: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        Utils.readLineFromConsole("Pressione Enter para continuar...");
    }

    /**
     * Solicita ao utilizador a introdução dos dados do produto: nome e preço.
     * Faz validações dos dados, lançando exceções para entradas inválidas.
     *
     * @return Produto criado com os dados introduzidos ou null se o utilizador cancelar.
     * @throws ExcecaoPreco Se o preço for inválido.
     * @throws ExcecaoNome Se o nome do produto for inválido.
     */
    private Produto introduzDados() throws ExcecaoPreco, ExcecaoNome {
        try {
            String nome = Utils.readLineFromConsole("Introduza o nome do produto: ");
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do produto não pode ser vazio");
            }
            // Verificação do nome: apenas letras e espaços são permitidos
            if (!nome.matches("[a-zA-Z\\s]+")) {
                throw new ExcecaoNome();
            }

            String precoStr = Utils.readLineFromConsole("Introduza o preço: ");
            if (precoStr == null || precoStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Preço não pode ser vazio");
            }
            double preco = Double.parseDouble(precoStr);
            if (preco <= 0) {
                throw new ExcecaoPreco();
            }

            return new Produto(nome, preco);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Preço deve ser um valor numérico válido");
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    /**
     * Apresenta os dados do produto para revisão pelo utilizador.
     *
     * @param produto Produto cujos dados serão apresentados.
     */
    private void apresentaDados(Produto produto) {
        if (produto != null) {
            System.out.printf("Produto: %s, Preço: %.2f€%n", produto.getNome(), produto.getPrecoUnitario());
        }
    }
}