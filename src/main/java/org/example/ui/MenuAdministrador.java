package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.Utils;
import java.io.IOException;

public class MenuAdministrador {
    private Federacao federacao;
    private String opcao;

    public MenuAdministrador(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("###### MENU ADMINISTRADOR #####");
            System.out.println("1. Gerenciar Produtos");
            System.out.println("2. Gerenciar Barracas");
            System.out.println("3. Gerenciar Voluntários");
            System.out.println("4. Gerenciar Instituições");
            System.out.println("5. Gerenciar Escalas Diárias");
            System.out.println("6. Ver Rankings");
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
                    new MenuRankings(federacao).run();
                    break;
                case "0":
                    System.out.println("A voltar ao menu inicial...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("0"));
    }
}
