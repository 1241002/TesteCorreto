package org.example.ui;

import org.example.model.Federacao;
import org.example.model.VoluntarioStock;
import org.example.model.Barraca;
import org.example.model.StockProdutos;
import org.example.utils.Utils;

import java.io.IOException;
import java.util.Map;

public class MenuVoluntarioStock {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarioStock(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("\n###### MENU VOLUNTÁRIO STOCK #####");
            System.out.println("1. Adicionar stock");
            System.out.println("2. Ver stock atual");
            System.out.println("0. Voltar");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    adicionarStock();
                    break;
                case "2":
                    verStockAtual();
                    break;
                case "0":
                    System.out.println("A voltar ao menu anterior...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }

    private void adicionarStock() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno do voluntário: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada(); // Obtém a barraca associada ao voluntário
        if (barraca == null) {
            System.out.println("Este voluntário não está associado a nenhuma barraca.");
            return;
        }

        String produto = Utils.readLineFromConsole("Nome do produto: ");
        int quantidade = Utils.readIntFromConsole("Quantidade a adicionar: ");

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        // Chama o metodo da Barraca para adicionar o produto ao stock
        barraca.adicionarStock(produto, quantidade);
        System.out.println("Stock adicionado com sucesso.");
    }


    private void verStockAtual() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno do voluntário: ");
        VoluntarioStock voluntario = federacao.buscarVoluntarioStockPorNumeroAluno(numeroAluno);

        if (voluntario == null) {
            System.out.println("Voluntário não encontrado.");
            return;
        }

        Barraca barraca = voluntario.getBarracaAssociada();
        if (barraca == null) {
            System.out.println("Este voluntário não está associado a nenhuma barraca.");
            return;
        }

        System.out.println("### Stock atual da barraca " + barraca.getNome() + " ###");
        for (StockProdutos sp : barraca.getStock()) {  // Alteração aqui: Usando a lista de StockProdutos
            System.out.println(sp.getNome() + ": " + sp.getQuantidade() + " unidades");
        }
    }

}
