package org.example.ui;

import org.example.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.example.utils.Utils;

public class MenuDados_UI {
    private Federacao federacao;

    public MenuDados_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    public void mostrarTodosOsDados() {
        System.out.println("###### VISUALIZAÇÃO DE TODOS OS DADOS #####");

        // 1. Administradores (ordenados por nome)
        System.out.println("\n=== Administradores ===");
        List<Administrador> administradores = new ArrayList<>(federacao.getAdministradores());
        if (administradores.isEmpty()) {
            System.out.println("Nenhum administrador registrado.");
        } else {
            Collections.sort(administradores, Comparator.comparing(Administrador::getNome, String.CASE_INSENSITIVE_ORDER));
            for (Administrador admin : administradores) {
                String curso = admin.getCurso() != null ? admin.getCurso() : "N/A";
                System.out.printf("- Nome: %s, Número: %d, Curso: %s%n",
                        admin.getNome(), admin.getNumero(), curso);
            }
        }

        // 2. Instituições (ordenadas por nome)
        System.out.println("\n=== Instituições ===");
        List<Instituicao> instituicoes = new ArrayList<>(federacao.getInstituicoes());
        if (instituicoes.isEmpty()) {
            System.out.println("Nenhuma instituição registrada.");
        } else {
            Collections.sort(instituicoes, Comparator.comparing(Instituicao::getNome, String.CASE_INSENSITIVE_ORDER));
            for (Instituicao inst : instituicoes) {
                System.out.printf("- Nome: %s, Barracas: %d, Voluntários: %d%n",
                        inst.getNome(), inst.getBarracas().size(), inst.getListaVoluntarios().size());
            }
        }

        // 3. Barracas (ordenadas por nome)
        System.out.println("\n=== Barracas ===");
        List<Barraca> barracas = new ArrayList<>(federacao.getTodasBarracas());
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca registrada.");
        } else {
            Collections.sort(barracas, Comparator.comparing(Barraca::getNome, String.CASE_INSENSITIVE_ORDER));
            for (Barraca barraca : barracas) {
                String instituicaoNome = barraca.getInstituicao() != null ? barraca.getInstituicao().getNome() : "N/A";
                System.out.printf("- Nome: %s, Instituição: %s, Estoque Total: %d, Vendas: %.2f€, Categoria: %s%n",
                        barraca.getNome(), instituicaoNome, barraca.exportarStockTotal(),
                        barraca.exportarVendas(), barraca.classificar());
            }
        }

        // 4. Voluntários (ordenados por número de aluno, depois por nome)
        System.out.println("\n=== Voluntários ===");
        List<Voluntario> voluntarios = new ArrayList<>();
        for (Instituicao inst : instituicoes) {
            voluntarios.addAll(inst.getListaVoluntarios());
        }
        if (voluntarios.isEmpty()) {
            System.out.println("Nenhum voluntário registrado.");
        } else {
            Collections.sort(voluntarios, Comparator.comparingInt(Voluntario::getNumeroAluno)
                    .thenComparing(Voluntario::getNome, String.CASE_INSENSITIVE_ORDER));
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
        List<Produto> produtos = new ArrayList<>(federacao.getListaProdutos());
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto registrado.");
        } else {
            Collections.sort(produtos, Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER));
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
                List<StockProdutos> stock = new ArrayList<>(barraca.getStock());
                if (stock.isEmpty()) {
                    System.out.println("\tSem produtos em estoque.");
                } else {
                    Collections.sort(stock, Comparator.comparing(StockProdutos::getNome, String.CASE_INSENSITIVE_ORDER));
                    for (StockProdutos sp : stock) {
                        System.out.printf("\t- Produto: %s, Quantidade: %d, Preço: %.2f€%n",
                                sp.getNome(), sp.getQuantidade(), sp.getPrecoUnitario());
                    }
                }
            }
        }

        // 7. Escalas Diárias (ordenadas por data)
        System.out.println("\n=== Escalas Diárias ===");
        List<EscalaDiaria> escalas = new ArrayList<>(federacao.getEscalas());
        if (escalas.isEmpty()) {
            System.out.println("Nenhuma escala diária registrada.");
        } else {
            Collections.sort(escalas, Comparator.comparing(escala -> escala.getData().toString()));
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
            List<Barraca> ouro = new ArrayList<>();
            List<Barraca> prata = new ArrayList<>();
            List<Barraca> bronze = new ArrayList<>();
            for (Barraca barraca : barracas) {
                String categoria = barraca.classificar();
                if (categoria.equals("Ouro")) ouro.add(barraca);
                else if (categoria.equals("Prata")) prata.add(barraca);
                else bronze.add(barraca);
            }
            String[] categorias = {"Ouro", "Prata", "Bronze"};
            for (String categoria : categorias) {
                List<Barraca> barracasCategoria = categoria.equals("Ouro") ? ouro : categoria.equals("Prata") ? prata : bronze;
                if (!barracasCategoria.isEmpty()) {
                    System.out.println("Categoria: " + categoria);
                    Collections.sort(barracasCategoria, Comparator.comparingDouble(Barraca::exportarVendas).reversed());
                    for (Barraca barraca : barracasCategoria) {
                        System.out.printf("  Barraca: %s (Vendas Totais: %.2f€)%n", barraca.getNome(), barraca.exportarVendas());
                        List<VendaProdutos> vendas = new ArrayList<>();
                        for (Voluntario v : barraca.getVoluntarios()) {
                            if (v instanceof VoluntarioVendas) {
                                vendas.addAll(((VoluntarioVendas) v).getTodasVendas());
                            }
                        }
                        if (vendas.isEmpty()) {
                            System.out.println("\tSem vendas registradas.");
                        } else {
                            Collections.sort(vendas, Comparator.comparing(VendaProdutos::getNomeProduto, String.CASE_INSENSITIVE_ORDER));
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
        Utils.readLineFromConsole("");
    }
}