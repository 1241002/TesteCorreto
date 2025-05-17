package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Produto;
import org.example.utils.Utils;

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
        this.federacao = federacao;
    }

    /**
     * Executa o processo de registo de um novo produto.
     * Solicita os dados, apresenta-os para confirmação e guarda o produto se confirmado.
     */
    public void run() {
        System.out.println("Novo Produto:");

        Produto novoProduto = introduzDados();
        if (novoProduto == null) {
            System.out.println("Operação cancelada devido a dados inválidos.");
            return;
        }

        apresentaDados(novoProduto);

        if (Utils.confirma("Confirma os dados? (S/N)")) {
            federacao.adicionarProduto(novoProduto);
            System.out.println("Dados do produto guardados com sucesso.");
        }
    }

    /**
     * Solicita ao utilizador a introdução dos dados do produto: nome e preço.
     * Faz validações básicas dos dados, retornando null se inválidos.
     *
     * @return Produto criado com os dados introduzidos ou null se dados inválidos.
     */
    private static Produto introduzDados() {
        String nome = Utils.readLineFromConsole("Introduza o nome do produto: ");
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome do produto não pode ser vazio.");
            return null;
        }

        double preco = Utils.readDoubleFromConsole("Introduza o preço: ");
        if (preco <= 0) {
            System.out.println("Preço deve ser maior que zero.");
            return null;
        }

        return new Produto(nome, preco);
    }

    /**
     * Apresenta os dados do produto para revisão pelo utilizador.
     *
     * @param produto Produto cujos dados serão apresentados.
     */
    private void apresentaDados(Produto produto) {
        System.out.printf("Produto: %s, Preço: %.2f€%n", produto.getNome(), produto.getPrecoUnitario());
    }
}
