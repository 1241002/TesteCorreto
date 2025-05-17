package org.example.ui;

import org.example.model.Federacao;
import org.example.model.IVendasVoluntarios;
import org.example.utils.Utils;

import java.io.Serializable;

/**
 * Classe que representa o menu do administrador,
 * permitindo gerir produtos, barracas, voluntários, instituições,
 * escalas diárias, stock, rankings e visualização de dados.
 */
public class MenuAdministrador {
    private Federacao federacao;
    private String opcao;

    /**
     * Construtor do menu administrador.
     *
     * @param federacao Federação onde serão feitas as gestões.
     */
    public MenuAdministrador(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o menu principal do administrador,
     * repetindo até o utilizador escolher sair (opção 0).
     */
    public void run() {
        do {
            System.out.println("\n###### MENU ADMINISTRADOR #####");
            System.out.println("1. Gerenciar Produtos");
            System.out.println("2. Gerenciar Barracas");
            System.out.println("3. Gerenciar Voluntários");
            System.out.println("4. Gerenciar Instituições");
            System.out.println("5. Gerenciar Escalas Diárias");
            System.out.println("6. Gerenciar Stock");
            System.out.println("7. Ver Rankings");
            System.out.println("8. Ver Lista de Todos os Dados");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    new RegistarProduto_UI(federacao).run();
                    break;
                case "2":
                    new MenuBarraca(federacao).run();
                    break;
                case "3":
                    new MenuVoluntarios(federacao).run();
                    break;
                case "4":
                    new MenuInstituicao(federacao).run();
                    break;
                case "5":
                    new MenuEscalaDiaria(federacao).run();
                    break;
                case "6":
                    new MenuStock_UI(federacao).run();
                    break;
                case "7":
                    new MenuRankings(federacao).run();
                    break;
                case "8":
                    new MenuDados_UI(federacao).mostrarTodosOsDados();
                    break;
                case "0":
                    System.out.println("A voltar ao menu inicial...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            // Sugestão para melhorar UX: pedir ao utilizador para pressionar Enter para continuar
            if (!opcao.equals("0")) {
                Utils.readLineFromConsole("Pressione Enter para continuar...");
                // Poderias tentar limpar o ecrã aqui, se quiseres (mas Java puro não tem forma direta)
            }
        } while (!opcao.equals("0"));
    }
}
