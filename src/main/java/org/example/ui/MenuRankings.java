package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

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

        for (Barraca b : escala.getBarracas()) {
            System.out.println("Barraca: " + b.getNome());

            // Garantir que estamos lidando com todos os tipos de voluntários (de vendas ou de estoque)
            List<Voluntario> todosVoluntarios = b.getVoluntarios().stream()
                    .sorted(Comparator.comparingInt(Voluntario::getNumeroAluno)) // Ordena por número de aluno
                    .collect(Collectors.toList());

            // Iterar sobre os voluntários ordenados
            for (Voluntario v : todosVoluntarios) {
                System.out.println("\t" + v.getNome() + " (nº " + v.getNumeroAluno() + ")");

                // Se for voluntário de vendas, mostra a classificação
                if (v instanceof IVendasVoluntarios vendedor) {
                    System.out.print("\t\t");
                    vendedor.verificarEExibirCategoria();
                }
            }
        }
    }


    private void mostrarBarracasAgrupadasPorCategoria(EscalaDiaria escala) {
        System.out.println("\n--- Vendas das barracas agrupadas por categoria (ordem decrescente) ---");

        Map<String, List<Barraca>> categorias = new HashMap<>();
        categorias.put("Ouro", new ArrayList<>());
        categorias.put("Prata", new ArrayList<>());
        categorias.put("Bronze", new ArrayList<>());

        for (Barraca b : escala.getBarracas()) {
            String cat = b.classificar();
            categorias.get(cat).add(b);
        }

        for (String categoria : List.of("Ouro", "Prata", "Bronze")) {
            List<Barraca> lista = categorias.get(categoria).stream()
                    .sorted(Comparator.comparingDouble(Barraca::exportarVendas).reversed())
                    .collect(Collectors.toList());

            System.out.println("\nCategoria: " + categoria);
            for (Barraca b : lista) {
                System.out.printf("\tBarraca: %s | Vendas: %.2f€\n", b.getNome(), b.exportarVendas());
            }
        }
    }
}
