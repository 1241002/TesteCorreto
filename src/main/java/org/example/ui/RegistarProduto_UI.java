package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Produto;
import org.example.utils.Utils;

public class RegistarProduto_UI {
    private Federacao federacao;

    public RegistarProduto_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        System.out.println("Novo Produto:");

        Produto novoProduto = introduzDados();
        if (novoProduto == null) {
            System.out.println("Operação cancelada devido a dados inválidos.");
            return;
        }

        apresentaDados(novoProduto);

        if (Utils.confirma("Confirma os dados? (S/N)")) {
            if (federacao.adicionarProduto(novoProduto)) {
                System.out.println("Dados do produto guardados com sucesso.");
            } else {
                System.out.println("Não foi possível guardar os dados do produto. Verifique se o produto já existe.");
            }
        }
    }

    private static Produto introduzDados() {
        String nome = Utils.readLineFromConsole("Introduza o nome do produto: ");
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: Nome do produto não pode ser vazio.");
            return null;
        }

        double preco = Utils.readDoubleFromConsole("Introduza o preço: ");
        if (preco <= 0) {
            System.out.println("Erro: Preço deve ser maior que zero.");
            return null;
        }

        return new Produto(nome, (float) preco);
    }

    private void apresentaDados(Produto produto) {
        System.out.printf("Produto: %s, Preço: %.2f€%n", produto.getNome(), produto.getPrecoUnitario());
    }
}