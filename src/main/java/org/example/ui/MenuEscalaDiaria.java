package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MenuEscalaDiaria {
    private Federacao federacao;
    private List<EscalaDiaria> escalas;

    public MenuEscalaDiaria(Federacao federacao) {
        this.federacao = federacao;
        this.escalas = federacao.getEscalas();
    }

    public void run() {
        String opcao;
        do {
            System.out.println("\n--- Menu Escala Diária ---");
            System.out.println("1. Criar nova escala (data definida)");
            System.out.println("2. Terminar dia");
            System.out.println("3. Ver escalas anteriores");
            System.out.println("0. Sair");
            opcao = Utils.readLineFromConsole("Escolha: ");

            switch (opcao) {
                case "1":
                    criarNovaEscala();
                    break;
                case "2":
                    terminarDia();
                    break;
                case "3":
                    listarEscalas();
                    break;
                case "0":
                    System.out.println("A sair do menu.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    private void terminarDia() {
        EscalaDiaria escalaAtual = federacao.getEscalaAtual();
        if (escalaAtual == null) {
            System.out.println("Não há escala atual para terminar.");
            return;
        }

        for (Barraca barraca : escalaAtual.getBarracas()) {
            double totalVendas = barraca.exportarVendas();
            int stockFinal = barraca.exportarStockTotal();
            escalaAtual.adicionarVendaTotal(totalVendas);
            escalaAtual.adicionarStockFinal(stockFinal);
        }

        System.out.println("Dia terminado e dados guardados na escala do dia: " + escalaAtual.getData());
    }

    private void criarNovaEscala() {
        Data novaData = Utils.readDateFromConsole("Insira a data da nova escala (formato DD-MM-AAAA): ");
        EscalaDiaria novaEscala = new EscalaDiaria(novaData);

        for (Barraca b : federacao.getTodasBarracas()) {
            novaEscala.adicionarBarraca(b);
        }

        federacao.adicionarEscala(novaEscala);
        federacao.setEscalaAtual(novaEscala);
        System.out.println("Nova escala criada para a data: " + novaData);
    }

    private void listarEscalas() {
        if (escalas.isEmpty()) {
            System.out.println("Não há escalas anteriores.");
        } else {
            for (EscalaDiaria escala : escalas) {
                System.out.println("Escala para a data: " + escala.getData());
                exibirInformacoesBarracas(escala);
            }
        }
    }

    private void exibirInformacoesBarracas(EscalaDiaria escala) {
        for (Barraca b : escala.getBarracas()) {
            double totalVendas = b.exportarVendas();
            int stockFinal = b.exportarStockTotal();
            System.out.println("\tBarraca: " + b.getNome());
            System.out.println("\tTotal de vendas: " + totalVendas + "€");
            System.out.println("\tStock final: " + stockFinal);
        }
        System.out.println();
    }
}