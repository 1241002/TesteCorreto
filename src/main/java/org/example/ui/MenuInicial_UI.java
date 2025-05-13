package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.Utils;
import java.io.IOException;

public class MenuInicial_UI {
    private Federacao federacao;
    private String opcao;

    public MenuInicial_UI(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("###### MENU INICIAL #####");
            System.out.println("1. Administrador");
            System.out.println("2. Voluntário de Stock");
            System.out.println("3. Voluntário de Vendas");
            System.out.println("0. Sair");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    new MenuAdministrador(federacao).run();
                    break;
                case "2":
                    new MenuVoluntarioStock(federacao).run();
                    break;
                case "3":
                    new MenuVoluntarioVendas(federacao).run();
                    break;
                case "0":
                    System.out.println("A sair do menu.");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("0"));
    }
}
