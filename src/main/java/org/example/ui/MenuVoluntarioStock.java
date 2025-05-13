package org.example.ui;

import org.example.model.Federacao;
import org.example.model.VoluntarioStock;
import org.example.model.Barraca;
import org.example.model.StockProdutos;
import org.example.utils.Utils;

import java.io.IOException;
import java.util.List;

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

            if (opcao.equals("1")) {
                adicionarStock();
            } else if (opcao.equals("2")) {
                verStockAtual();
            } else if (!opcao.equals("0")) {
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

        Barraca barraca = voluntario.getBarracaAssociada();
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

        List<StockProdutos> stock = barraca.getStock();

        System.out.println("### Stock atual da barraca " + barraca.getNome() + " ###");

        if (stock.isEmpty()) {
            System.out.println("Ainda não há produtos registados.");
        } else {
            for (StockProdutos sp : stock) {
                System.out.println(sp.getNome() + ": " + sp.getQuantidade() + " unidades");
            }
        }
    }
}
