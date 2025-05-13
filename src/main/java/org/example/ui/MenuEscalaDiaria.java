package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEscalaDiaria {

    private Federacao federacao;
    private List<EscalaDiaria> escalas;
    private EscalaDiaria escalaAtual;
    private List<Barraca> barracas;

    // Construtor para receber a federação
    public MenuEscalaDiaria(Federacao federacao) {
        this.federacao = federacao;
        this.escalas = new ArrayList<>();
        this.barracas = federacao.getTodasBarracas();
        this.escalaAtual = federacao.getEscalaAtual();

        if (this.escalaAtual != null) {
            escalas.add(this.escalaAtual);  // Garante que a escala do dia atual é adicionada
        }
    }

    public void run() {
        mostrarMenu();
    }

    private void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Escala Diária ---");
            System.out.println("1. Criar nova escala (data definida)");
            System.out.println("2. Terminar dia");
            System.out.println("3. Ver escalas anteriores");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = lerInteiro(sc);

            switch (opcao) {
                case 1 -> criarNovaEscala();
                case 2 -> terminarDia();
                case 3 -> listarEscalas();
                case 0 -> System.out.println("A sair do menu.");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void terminarDia() {
        if (escalaAtual == null) {
            System.out.println("Não há escala atual para terminar.");
            return;
        }

        // Simula guardar dados das barracas
        for (Barraca barraca : barracas) {
            double totalVendas = barraca.exportarVendas();
            int stockFinal = barraca.exportarStockTotal();

            // Adiciona as informações de vendas e stock na escala atual
            escalaAtual.adicionarVendaTotal(totalVendas);
            escalaAtual.adicionarStockFinal(stockFinal);
        }

        System.out.println("Dia terminado e dados guardados na escala do dia: " + escalaAtual.getData());
    }

    private void criarNovaEscala() {
        Data novaData = Utils.readDateFromConsole("Insira a data da nova escala (formato DD-MM-AAAA): ");
        EscalaDiaria novaEscala = new EscalaDiaria(novaData);

        // Adiciona todas as barracas existentes à nova escala
        for (Barraca b : federacao.getTodasBarracas()) {
            novaEscala.adicionarBarraca(b);
        }

        escalas.add(novaEscala);
        escalaAtual = novaEscala;

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

            // Exibindo as informações da barraca
            System.out.println("\tBarraca: " + b.getNome());
            System.out.println("\tTotal de vendas: " + totalVendas);
            System.out.println("\tStock final: " + stockFinal);
        }
        System.out.println();  // Linha em branco entre as escalas
    }

    // Função para ler inteiros e garantir que a entrada seja válida
    private int lerInteiro(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Entrada inválida. Por favor, insira um número: ");
            sc.next(); // limpar o buffer
        }
        return sc.nextInt();
    }
}
