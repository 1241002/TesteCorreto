package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuEscalaDiaria {

    private Federacao federacao;  // Adicione a variável federacao
    private List<EscalaDiaria> escalas;
    private EscalaDiaria escalaAtual;
    private List<Barraca> barracas;

    // Construtor para receber a federação
    public MenuEscalaDiaria(Federacao federacao) {
        this.federacao = federacao;  // Inicializa a federação
        this.escalas = new ArrayList<>();
        this.barracas = federacao.getTodasBarracas();  // Usando o metodo correto
        this.escalaAtual = federacao.getEscalaAtual();  // Usando o metodo correto

        if (this.escalaAtual != null) {
            escalas.add(this.escalaAtual);  // Garante que a escala do dia atual é adicionada
        }
    }

    // Novo metodo run()
    public void run() {
        mostrarMenu();
    }

    public void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n--- Menu Escala Diária ---");
            System.out.println("1. Criar nova escala (data definida)");
            System.out.println("2. Terminar dia");
            System.out.println("3. Ver escalas anteriores");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

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
        // Simula guardar dados das barracas
        for (Barraca barraca : barracas) {
            double totalVendas = barraca.exportarVendas();  // Pega o valor total de vendas
            int stockFinal = barraca.exportarStockTotal();  // Pega o stock final (quantidade total de produtos)

            // Adiciona as informações de vendas e estoque na escala atual
            escalaAtual.adicionarVendaTotal(totalVendas);  // Metodo para somar as vendas totais
            escalaAtual.adicionarEstoqueFinal(stockFinal);  // Metodo para adicionar o estoque final
        }

        System.out.println("Dia terminado e dados guardados na escala do dia: " + escalaAtual.getData());
    }

    private void criarNovaEscala() {
        Data novaData = Utils.readDateFromConsole("Insere a data da nova escala (formato DD-MM-AAAA):");
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
            for (EscalaDiaria e : escalas) {
                System.out.println("Escala para a data: " + e.getData());

                for (Barraca b : e.getBarracas()) {
                    double totalVendas = b.exportarVendas();  // Supondo que exportarVendas() retorne o total de vendas
                    int stockFinal = b.exportarStockTotal();  // Supondo que exportarStockTotal() retorne o estoque final

                    // Exibindo as informações da barraca
                    System.out.println("\tBarraca: " + b.getNome());
                    System.out.println("\tTotal de vendas: " + totalVendas);
                    System.out.println("\tEstoque final: " + stockFinal);
                }
                System.out.println();  // Linha em branco entre as escalas
            }
        }
    }

}
