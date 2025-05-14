package org.example.ui;

import org.example.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuDados_UI {
    private Federacao federacao;

    public MenuDados_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    public void mostrarTodosOsDados() {
        System.out.println("###### VISUALIZAÇÃO DE TODOS OS DADOS #####");

        // 1. Administradores
        System.out.println("\n=== Administradores ===");
        List<Administrador> administradores = federacao.getAdministradores();
        if (administradores.isEmpty()) {
            System.out.println("Nenhum administrador registrado.");
        } else {
            for (Administrador admin : administradores) {
                System.out.printf("- Nome: %s, Número: %d, Curso: %s%n",
                        admin.getNome(), admin.getNumero(), admin.getCurso());
            }
        }

        // 2. Instituições
        System.out.println("\n=== Instituições ===");
        List<Instituicao> instituicoes = federacao.getInstituicoes();
        if (instituicoes.isEmpty()) {
            System.out.println("Nenhuma instituição registrada.");
        } else {
            for (Instituicao inst : instituicoes) {
                System.out.printf("- Nome: %s, Barracas: %d, Voluntários: %d%n",
                        inst.getNome(), inst.getBarracas().size(), inst.getListaVoluntarios().size());
            }
        }

        // 3. Barracas
        System.out.println("\n=== Barracas ===");
        List<Barraca> barracas = federacao.getTodasBarracas();
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca registrada.");
        } else {
            for (Barraca barraca : barracas) {
                System.out.printf("- Nome: %s, Instituição: %s, Estoque Total: %d, Vendas: %.2f€, Categoria: %s%n",
                        barraca.getNome(), barraca.getInstituicao().getNome(),
                        barraca.exportarStockTotal(), barraca.exportarVendas(), barraca.classificar());
            }
        }

        // 4. Voluntários (ordenados alfabeticamente)
        System.out.println("\n=== Voluntários ===");
        List<String> voluntariosInfo = new ArrayList<>();
        for (Instituicao inst : instituicoes) {
            for (Voluntario voluntario : inst.getListaVoluntarios()) {
                String tipo = (voluntario instanceof VoluntarioStock) ? "Stock" : "Vendas";
                String barraca = voluntario.getBarracaAssociada() != null ? voluntario.getBarracaAssociada().getNome() : "Nenhuma";
                voluntariosInfo.add(String.format("Nome: %s, Número: %d, Tipo: %s, Barraca: %s, Instituição: %s",
                        voluntario.getNome(), voluntario.getNumeroAluno(), tipo, barraca, inst.getNome()));
            }
        }
        if (voluntariosInfo.isEmpty()) {
            System.out.println("Nenhum voluntário registrado.");
        } else {
            Collections.sort(voluntariosInfo, String.CASE_INSENSITIVE_ORDER);
            for (String info : voluntariosInfo) {
                System.out.println("- " + info);
            }
        }

        // 5. Produtos
        System.out.println("\n=== Produtos ===");
        List<Produto> produtos = federacao.getListaProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto registrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.printf("- Nome: %s, Preço: %.2f€%n",
                        produto.getNome(), produto.getPrecoUnitario());
            }
        }

        // 6. Estoques
        System.out.println("\n=== Estoques por Barraca ===");
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca para exibir estoques.");
        } else {
            for (Barraca barraca : barracas) {
                System.out.println("Barraca: " + barraca.getNome());
                List<StockProdutos> stock = barraca.getStock();
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

        // 7. Escalas Diárias
        System.out.println("\n=== Escalas Diárias ===");
        List<EscalaDiaria> escalas = federacao.getEscalas();
        if (escalas.isEmpty()) {
            System.out.println("Nenhuma escala diária registrada.");
        } else {
            for (EscalaDiaria escala : escalas) {
                System.out.printf("- Data: %s, Barracas: %d, Vendas Totais: %.2f€, Estoque Final: %d%n",
                        escala.getData().toString(), escala.getBarracas().size(),
                        escala.getVendasTotais(), escala.getStockFinal());
            }
        }

        // 8. Vendas por Barraca
        System.out.println("\n=== Vendas por Barraca ===");
        if (barracas.isEmpty()) {
            System.out.println("Nenhuma barraca para exibir vendas.");
        } else {
            for (Barraca barraca : barracas) {
                System.out.println("Barraca: " + barraca.getNome());
                List<VendaProdutos> vendas = new ArrayList<>();
                for (Voluntario v : barraca.getVoluntarios()) {
                    if (v instanceof VoluntarioVendas) {
                        vendas.addAll(((VoluntarioVendas) v).getTodasVendas());
                    }
                }
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

        System.out.println("\nFim da visualização. Pressione Enter para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignorar
        }
    }
}