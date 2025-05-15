package org.example.ui;

import org.example.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class MenuDados_UI {
    private Federacao federacao;

    public MenuDados_UI(Federacao federacao) {
        this.federacao = Objects.requireNonNull(federacao, "Federacao cannot be null");
    }

    public void mostrarTodosOsDados() {
        System.out.println("###### VISUALIZAÇÃO DE TODOS OS DADOS #####");

        // 1. Administradores (ordenados por nome)
        System.out.println("\n=== Administradores ===");
        List<Administrador> administradores = federacao.getAdministradores().stream()
                .distinct()
                .sorted(Comparator.comparing(Administrador::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        if (administradores.isEmpty()) {
            System.out.println("Nenhum administrador registrado.");
        } else {
            for (Administrador admin : administradores) {
                System.out.printf("- Nome: %s, Número: %d, Curso: %s%n",
                        admin.getNome(), admin.getNumero(), admin.getCurso() != null ? admin.getCurso() : "N/A");
            }
        }

        // 2. Instituições (ordenadas por nome)
        System.out.println("\n=== Instituições ===");
        List<Instituicao> instituicoes = federacao.getInstituicoes().stream()
                .distinct()
                .sorted(Comparator.comparing(Instituicao::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        if (instituicoes.isEmpty()) {
            System.out.println("Nenhuma instituição registrada.");
        } else {
            for (Instituicao inst : instituicoes) {
                System.out.printf("- Nome: %s, Barracas: %d, Voluntários: %d%n",
                        inst.getNome(), inst.getBarracas().size(), inst.getListaVoluntarios().size());
            }
        }

        // 3. Barracas (ordenadas por nome)
        System.out.println("\n=== Barracas ===");
        List<Barraca> barracas = federacao.getTodasBarracas().stream()
                .distinct()
                .sorted(Comparator.comparing(Barraca::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca registrada.");
        } else {
            for (Barraca barraca : barracas) {
                String instituicaoNome = barraca.getInstituicao() != null ? barraca.getInstituicao().getNome() : "N/A";
                System.out.printf("- Nome: %s, Instituição: %s, Estoque Total: %d, Vendas: %.2f€, Categoria: %s%n",
                        barraca.getNome(), instituicaoNome, barraca.exportarStockTotal(),
                        barraca.exportarVendas(), barraca.classificar());
            }
        }

        // 4. Voluntários (ordenados por número de aluno, depois por nome)
        System.out.println("\n=== Voluntários ===");
        List<Voluntario> voluntarios = instituicoes.stream()
                .flatMap(inst -> inst.getListaVoluntarios().stream())
                .distinct()
                .sorted(Comparator.comparingInt(Voluntario::getNumeroAluno)
                        .thenComparing(Voluntario::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        if (voluntarios.isEmpty()) {
            System.out.println("Nenhum voluntário registrado.");
        } else {
            for (Voluntario voluntario : voluntarios) {
                String tipo = (voluntario instanceof VoluntarioStock) ? "Stock" : "Vendas";
                String barraca = voluntario.getBarracaAssociada() != null ? voluntario.getBarracaAssociada().getNome() : "Nenhuma";
                String instituicao = voluntario.getInstituicao() != null ? voluntario.getInstituicao().getNome() : "N/A";
                System.out.printf("- Nome: %s, Número: %d, Tipo: %s, Barraca: %s, Instituição: %s%n",
                        voluntario.getNome(), voluntario.getNumeroAluno(), tipo, barraca, instituicao);
            }
        }

        // 5. Produtos (ordenados por nome)
        System.out.println("\n=== Produtos ===");
        List<Produto> produtos = federacao.getListaProdutos().stream()
                .distinct()
                .sorted(Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto registrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.printf("- Nome: %s, Preço: %.2f€%n",
                        produto.getNome(), produto.getPrecoUnitario());
            }
        }

        // 6. Estoques por Barraca (barracas ordenadas por nome, produtos por nome)
        System.out.println("\n=== Estoques por Barraca ===");
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca para exibir estoques.");
        } else {
            for (Barraca barraca : barracas) {
                System.out.println("Barraca: " + barraca.getNome());
                List<StockProdutos> stock = barraca.getStock().stream()
                        .distinct()
                        .sorted(Comparator.comparing(StockProdutos::getNome, String.CASE_INSENSITIVE_ORDER))
                        .collect(Collectors.toList());
                if (stock.isEmpty()) {
                    System.out.println("\tSem produtos em estoque.");
                } else {
                    for (StockProdutos sp : stock) {
                        System.out.printf("\t- Produto: %s, Quantidade: %d, Preço: %.2f€%n",
                                sp.getNome(), sp.getQuantidade(), sp.getPrecoUnitario());
                    }
                }
            }
        }

        // 7. Escalas Diárias (ordenadas por data)
        System.out.println("\n=== Escalas Diárias ===");
        List<EscalaDiaria> escalas = federacao.getEscalas().stream()
                .distinct()
                .sorted(Comparator.comparing(escala -> escala.getData().toString()))
                .collect(Collectors.toList());
        if (escalas.isEmpty()) {
            System.out.println("Nenhuma escala diária registrada.");
        } else {
            for (EscalaDiaria escala : escalas) {
                System.out.printf("- Data: %s, Barracas: %d, Vendas Totais: %.2f€, Estoque Final: %d%n",
                        escala.getData().toString(), escala.getBarracas().size(),
                        escala.getVendasTotais(), escala.getStockFinal());
            }
        }

        // 8. Vendas por Barraca (agrupadas por categoria, ordenadas por vendas decrescentes)
        System.out.println("\n=== Vendas por Barraca (Agrupadas por Categoria) ===");
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca para exibir vendas.");
        } else {
            Map<String, List<Barraca>> barracasPorCategoria = barracas.stream()
                    .collect(Collectors.groupingBy(Barraca::classificar));
            String[] categorias = {"Ouro", "Prata", "Bronze"};
            for (String categoria : categorias) {
                List<Barraca> barracasCategoria = barracasPorCategoria.getOrDefault(categoria, new ArrayList<>());
                if (!barracasCategoria.isEmpty()) {
                    System.out.println("Categoria: " + categoria);
                    barracasCategoria.sort(Comparator.comparingDouble(Barraca::exportarVendas).reversed());
                    for (Barraca barraca : barracasCategoria) {
                        System.out.printf("  Barraca: %s (Vendas Totais: %.2f€)%n", barraca.getNome(), barraca.exportarVendas());
                        List<VendaProdutos> vendas = barraca.getVoluntarios().stream()
                                .filter(v -> v instanceof VoluntarioVendas)
                                .flatMap(v -> ((VoluntarioVendas) v).getTodasVendas().stream())
                                .distinct()
                                .sorted(Comparator.comparing(VendaProdutos::getNomeProduto, String.CASE_INSENSITIVE_ORDER))
                                .collect(Collectors.toList());
                        if (vendas.isEmpty()) {
                            System.out.println("\tSem vendas registradas.");
                        } else {
                            for (VendaProdutos venda : vendas) {
                                System.out.printf("\t- Produto: %s, Quantidade: %d, Valor Total: %.2f€%n",
                                        venda.getNomeProduto(), venda.getQuantidade(), venda.getValorTotal());
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\nFim da visualização. Pressione Enter para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignorar
        }
    }
}