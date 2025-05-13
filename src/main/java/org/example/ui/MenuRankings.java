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
        mostrarBarracasAgrupadasPorCategoria(escalaSelecionada);
    }

    private void mostrarVoluntariosOrdenados(EscalaDiaria escala) {
        System.out.println("\n--- Voluntários por barraca (ordenados por número de aluno) ---");

        List<Barraca> barracas = escala.getBarracas();

        for (Barraca b : barracas) {
            System.out.println("Barraca: " + b.getNome());

            List<Voluntario> voluntarios = new ArrayList<>(b.getVoluntarios());

            // Ordenar por número de aluno
            Collections.sort(voluntarios, new Comparator<Voluntario>() {
                @Override
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

    private void mostrarBarracasAgrupadasPorCategoria(EscalaDiaria escala) {
        System.out.println("\n--- Vendas das barracas agrupadas por categoria (ordem decrescente) ---");

        List<Barraca> ouro = new ArrayList<>();
        List<Barraca> prata = new ArrayList<>();
        List<Barraca> bronze = new ArrayList<>();

        List<Barraca> barracas = escala.getBarracas();

        for (Barraca b : barracas) {
            String categoria = b.classificar();
            if (categoria.equals("Ouro")) {
                ouro.add(b);
            } else if (categoria.equals("Prata")) {
                prata.add(b);
            } else if (categoria.equals("Bronze")) {
                bronze.add(b);
            }
        }

        ordenarEBarracas("Ouro", ouro);
        ordenarEBarracas("Prata", prata);
        ordenarEBarracas("Bronze", bronze);
    }

    private void ordenarEBarracas(String categoria, List<Barraca> lista) {
        // Ordena por vendas decrescentes
        Collections.sort(lista, new Comparator<Barraca>() {
            @Override
            public int compare(Barraca b1, Barraca b2) {
                return Double.compare(b2.exportarVendas(), b1.exportarVendas());
            }
        });

        System.out.println("\nCategoria: " + categoria);
        for (Barraca b : lista) {
            System.out.printf("\tBarraca: %s | Vendas: %.2f€\n", b.getNome(), b.exportarVendas());
        }
    }
}
