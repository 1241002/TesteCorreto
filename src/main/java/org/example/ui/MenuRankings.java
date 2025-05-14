package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MenuRankings {
    private Federacao federacao;

    public MenuRankings(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        List<EscalaDiaria> escalas = federacao.getEscalas();

        if (escalas.isEmpty()) {
            System.out.println("Não existem escalas disponíveis.");
            return;
        }

        System.out.println("Escolha uma escala pela data:");
        for (int i = 0; i < escalas.size(); i++) {
            System.out.println((i + 1) + " - " + escalas.get(i).getData());
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

    private void mostrarVoluntariosOrdenados(EscalaDiaria escala) {
        System.out.println("\n--- Voluntários por barraca (ordenados por nº de aluno) ---");

        for (Barraca b : escala.getBarracas()) {
            System.out.println("Barraca: " + b.getNome());

            List<Voluntario> voluntarios = new ArrayList<>(b.getVoluntarios());

            Collections.sort(voluntarios, new Comparator<Voluntario>() {
                public int compare(Voluntario v1, Voluntario v2) {
                    return Integer.compare(v1.getNumeroAluno(), v2.getNumeroAluno());
                }
            });

            for (Voluntario v : voluntarios) {
                System.out.println("\t" + v.getNome() + " (nº " + v.getNumeroAluno() + ")");
                if (v instanceof IVendasVoluntarios) {
                    IVendasVoluntarios vendedor = (IVendasVoluntarios) v;
                    System.out.print("\t\t");
                    vendedor.verificarEExibirCategoria();
                }
            }
        }
    }

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

    private void mostrarCategoria(String nomeCategoria, List<Barraca> barracas) {
        Collections.sort(barracas, new Comparator<Barraca>() {
            public int compare(Barraca b1, Barraca b2) {
                return Double.compare(b2.exportarVendas(), b1.exportarVendas());
            }
        });

        System.out.println("\nCategoria: " + nomeCategoria);
        for (Barraca b : barracas) {
            System.out.printf("\tBarraca: %s | Stock final: %d unidades\n", b.getNome(), b.exportarStockTotal());
        }
    }
}