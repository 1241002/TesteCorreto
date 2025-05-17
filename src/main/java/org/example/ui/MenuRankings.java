package org.example.ui;

import org.example.model.*;
import org.example.utils.Utils;

import java.io.Serializable;
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
        if (federacao == null) {
            throw new IllegalArgumentException("Federação não pode ser nula");
        }
        this.federacao = federacao;
    }

    /**
     * Executa o menu de rankings, apresentando as escalas disponíveis e permitindo
     * ao utilizador escolher uma escala para visualização dos rankings.
     */
    public void run() {
        try {
            List<EscalaDiaria> escalas = federacao.getEscalas();
            if (escalas == null || escalas.isEmpty()) {
                System.out.println("Não existem escalas disponíveis.");
                return;
            }

            System.out.println("Escolha uma escala pela data:");
            for (int i = 0; i < escalas.size(); i++) {
                System.out.println((i + 1) + ". " + escalas.get(i).getData());
            }

            String opcaoStr = Utils.readLineFromConsole("Opção: ");
            if (opcaoStr == null || opcaoStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Opção não pode ser vazia");
            }
            int opcao = Integer.parseInt(opcaoStr);
            if (opcao < 1 || opcao > escalas.size()) {
                throw new IllegalArgumentException("Opção inválida. Escolha um número entre 1 e " + escalas.size() + ".");
            }

            EscalaDiaria escalaSelecionada = escalas.get(opcao - 1);
            if (escalaSelecionada == null) {
                throw new IllegalStateException("Escala selecionada é inválida.");
            }

            mostrarVoluntariosOrdenados(escalaSelecionada);
            mostrarBarracasPorCategoria(escalaSelecionada);
        } catch (NumberFormatException e) {
            System.out.println("Erro: A opção deve ser um número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        Utils.readLineFromConsole("Pressione Enter para continuar...");
    }

    /**
     * Mostra os voluntários de cada barraca ordenados pelo número de aluno.
     * Para voluntários do tipo VoluntarioVendas, exibe também a sua categoria.
     *
     * @param escala A escala diária para obter as barracas e voluntários.
     */
    private void mostrarVoluntariosOrdenados(EscalaDiaria escala) {
        try {
            if (escala == null || escala.getBarracas() == null) {
                System.out.println("Escala ou barracas inválidas.");
                return;
            }

            System.out.println("\n--- Voluntários por barraca (ordenados por nº de aluno) ---");
            for (Barraca b : escala.getBarracas()) {
                if (b == null) {
                    System.out.println("Barraca inválida encontrada.");
                    continue;
                }
                System.out.println("Barraca: " + b.getNome());
                List<Voluntario> voluntarios = new ArrayList<>(b.getVoluntarios());
                if (voluntarios == null || voluntarios.isEmpty()) {
                    System.out.println("\tNenhum voluntário registrado.");
                    continue;
                }
                Collections.sort(voluntarios, Comparator.comparingInt(Voluntario::getNumeroAluno));

                for (Voluntario v : voluntarios) {
                    if (v == null) {
                        System.out.println("\tVoluntário inválido encontrado.");
                        continue;
                    }
                    System.out.println("\t" + v.getNome() + " (nº " + v.getNumeroAluno() + ")");
                    if (v instanceof VoluntarioVendas) {
                        VoluntarioVendas vendedor = (VoluntarioVendas) v;
                        String categoria = vendedor.verificarCategoria();
                        if (categoria == null) {
                            throw new ExcecaoVoluntarioVendasNaoExistente("Categoria inválida para voluntário de vendas: " + v.getNome());
                        }
                        System.out.println("\t\t" + categoria);
                    }
                }
            }
        } catch (ExcecaoVoluntarioVendasNaoExistente e) {
            System.out.println("Erro no voluntário de vendas: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao exibir voluntários: " + e.getMessage());
        }
    }

    /**
     * Mostra as barracas agrupadas por categoria (Ouro, Prata, Bronze),
     * ordenadas por total de vendas de forma decrescente, e exibe o stock final.
     *
     * @param escala A escala diária que contém as barracas a classificar.
     */
    private void mostrarBarracasPorCategoria(EscalaDiaria escala) {
        try {
            if (escala == null || escala.getBarracas() == null) {
                System.out.println("Escala ou barracas inválidas.");
                return;
            }

            System.out.println("\n--- Barracas agrupadas por categoria ---");
            List<Barraca> ouro = new ArrayList<>();
            List<Barraca> prata = new ArrayList<>();
            List<Barraca> bronze = new ArrayList<>();

            for (Barraca b : escala.getBarracas()) {
                if (b == null) {
                    System.out.println("Barraca inválida encontrada.");
                    continue;
                }
                String categoria = b.classificar();
                if (categoria == null) {
                    System.out.println("Categoria inválida para barraca: " + b.getNome());
                    continue;
                }
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
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao exibir categorias: " + e.getMessage());
        }
    }

    /**
     * Exibe as barracas de uma categoria específica, ordenadas por vendas decrescentes.
     *
     * @param nomeCategoria Nome da categoria (ex.: "Ouro").
     * @param barracas      Lista de barracas pertencentes à categoria.
     */
    private void mostrarCategoria(String nomeCategoria, List<Barraca> barracas) {
        try {
            if (barracas == null) {
                System.out.println("Lista de barracas inválida para categoria: " + nomeCategoria);
                return;
            }
            Collections.sort(barracas, Comparator.comparingDouble(Barraca::exportarVendas).reversed());
            System.out.println("\nCategoria: " + nomeCategoria);
            if (barracas.isEmpty()) {
                System.out.println("\tNenhuma barraca nesta categoria.");
            }
            for (Barraca b : barracas) {
                if (b == null) {
                    System.out.println("\tBarraca inválida encontrada.");
                    continue;
                }
                System.out.printf("\tBarraca: %s | Stock final: %d unidades\n", b.getNome(), b.exportarStockTotal());
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado ao exibir categoria " + nomeCategoria + ": " + e.getMessage());
        }
    }
}
