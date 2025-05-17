package org.example.ui;

import org.example.model.*;
import org.example.utils.Data;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pelo menu de gestão das escalas diárias dentro da federação.
 * Permite criar novas escalas, terminar o dia (finalizar e guardar dados da escala atual)
 * e visualizar escalas anteriores.
 *
 * Este menu interage com a instância de {@link Federacao} para manipular as escalas.
 */
public class MenuEscalaDiaria {

    /**
     * Referência à federação que contém todas as escalas e barracas.
     */
    private Federacao federacao;

    /**
     * Lista das escalas diárias já existentes na federação.
     */
    private List<EscalaDiaria> escalas;

    /**
     * Construtor que inicializa o menu com a federação dada.
     *
     * @param federacao Instância da federação onde as escalas serão geridas.
     */
    public MenuEscalaDiaria(Federacao federacao) {
        this.federacao = federacao;
        this.escalas = federacao.getEscalas();
    }

    /**
     * Método principal que exibe o menu interativo no console,
     * lê as opções do utilizador e executa as ações correspondentes.
     *
     * Opções disponíveis:
     * 1 - Criar uma nova escala com data definida pelo utilizador.
     * 2 - Terminar o dia, finalizando a escala atual e guardando dados.
     * 3 - Visualizar as escalas anteriores já criadas.
     * 0 - Sair do menu.
     */
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

    /**
     * Finaliza o dia atual, atualizando a escala em curso com os totais
     * de vendas e o stock final de cada barraca.
     * Caso não exista uma escala atual, exibe uma mensagem de aviso.
     */
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

    /**
     * Cria uma nova escala diária com data definida pelo utilizador.
     * Inicialmente, adiciona todas as barracas da federação à nova escala,
     * registra a escala na federação e define esta nova escala como a escala atual.
     */
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

    /**
     * Lista todas as escalas diárias existentes, exibindo a data e as informações
     * das barracas para cada escala.
     * Caso não haja escalas registradas, informa o utilizador.
     */
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

    /**
     * Exibe informações detalhadas de cada barraca para uma dada escala diária,
     * incluindo o total de vendas e o stock final.
     *
     * @param escala A escala diária cujas barracas serão exibidas.
     */
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
