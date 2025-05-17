package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Menu para exibir rankings relacionados às escalas diárias da federação.
 * Permite ao utilizador selecionar uma escala pela data, visualizar os voluntários
 * ordenados por número de aluno em cada barraca, e ver as barracas agrupadas por categorias
 * (Ouro, Prata, Bronze) com os respetivos stocks finais e vendas.
 */
public class MenuRankings {

    /**
     * Instância da federação que contém as escalas, barracas e voluntários.
     */
    private Federacao federacao;

    /**
     * Construtor que inicializa o menu com a federação fornecida.
     *
     * @param federacao A federação usada para obter escalas e dados relacionados.
     */
    public MenuRankings(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o menu de rankings, apresentando as escalas disponíveis e permitindo
     * ao utilizador escolher uma escala para visualização dos rankings.
     */
    public void run() {
        List<EscalaDiaria> escalas = federacao.getEscalas();

        if (escalas.isEmpty()) {
            System.out.println("Não existem escalas disponíveis.");
            return;
        }

        System.out.println("Escolha uma escala pela data:");
        for (int i = 0; i < escalas.size(); i++) {
            System.out.println((i + 1) + ". " + escalas.get(i).getData());
        }

        int opcao = Utils.readIntFromConsole("Opção: ");
        if (opcao < 1 || opcao > escalas.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        EscalaDiaria escalaSelecionada = escalas.get(opcao - 1);

        mostrarVoluntariosOrdenados(escalaSelecionada);
        mostrarBarracasPorCategoria(escalaSelecionada);
    }

    /**
     * Mostra os voluntários de cada barraca ordenados pelo número de aluno.
     * Para voluntários do tipo VoluntarioVendas, exibe também a sua categoria.
     *
     * @param escala A escala diária para obter as barracas e voluntários.
     */
    private void mostrarVoluntariosOrdenados(EscalaDiaria escala) {
        System.out.println("\n--- Voluntários por barraca (ordenados por nº de aluno) ---");

        for (Barraca b : escala.getBarracas()) {
            System.out.println("Barraca: " + b.getNome());
            List<Voluntario> voluntarios = new ArrayList<>(b.getVoluntarios());
            Collections.sort(voluntarios, Comparator.comparingInt(Voluntario::getNumeroAluno));

            for (Voluntario v : voluntarios) {
                System.out.println("\t" + v.getNome() + " (nº " + v.getNumeroAluno() + ")");
                if (v instanceof VoluntarioVendas) {
                    VoluntarioVendas vendedor = (VoluntarioVendas) v;
                    System.out.println("\t\t" + vendedor.verificarCategoria());
                }
            }
        }
    }

    /**
     * Mostra as barracas agrupadas por categoria (Ouro, Prata, Bronze),
     * ordenadas por total de vendas de forma decrescente, e exibe o stock final.
     *
     * @param escala A escala diária que contém as barracas a classificar.
     */
    private void mostrarBarracasPorCategoria(EscalaDiaria escala) {
        System.out.println("\n--- Barracas agrupadas por categoria ---");

        List<Barraca> ouro = new ArrayList<>();
        List<Barraca> prata = new ArrayList<>();
        List<Barraca> bronze = new ArrayList<>();

        for (Barraca b : escala.getBarracas()) {
            String categoria = b.classificar();
            if (categoria.equals("Ouro")) {
                ouro.add(b);
            } else if (categoria.equals("Prata")) {
                prata.add(b);
            } else {
                bronze.add(b);
            }
        }

        mostrarCategoria("Ouro", ouro);
        mostrarCategoria("Prata", prata);
        mostrarCategoria("Bronze", bronze);
    }

    /**
     * Exibe as barracas de uma categoria específica, ordenadas por vendas decrescentes.
     *
     * @param nomeCategoria Nome da categoria (ex.: "Ouro").
     * @param barracas Lista de barracas pertencentes à categoria.
     */
    private void mostrarCategoria(String nomeCategoria, List<Barraca> barracas) {
        Collections.sort(barracas, Comparator.comparingDouble(Barraca::exportarVendas).reversed());
        System.out.println("\nCategoria: " + nomeCategoria);
        for (Barraca b : barracas) {
            System.out.printf("\tBarraca: %s | Stock final: %d unidades\n", b.getNome(), b.exportarStockTotal());
        }
    }
}
