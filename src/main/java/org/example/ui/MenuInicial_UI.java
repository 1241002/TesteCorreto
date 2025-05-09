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
            System.out.println("###### MENU #####");
            System.out.println("1. Administrador");
            System.out.println("2. Voluntário de Stock");
            System.out.println("3. Voluntário de Vendas");
            System.out.println("4. Gerenciar Barracas");
            System.out.println("5. Gerenciar Voluntários");
            System.out.println("6. Gerenciar Instituições");
            System.out.println("0. Sair");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                MenuAdministrador ui = new MenuAdministrador(federacao);
                ui.run();
            } else if (opcao.equals("2")) {
                MenuVoluntarioStock ui = new MenuVoluntarioStock(federacao);
                ui.run();
            } else if (opcao.equals("3")) {
                MenuVoluntarioVendas ui = new MenuVoluntarioVendas(federacao);
                ui.run();
            } else if (opcao.equals("4")) {
                MenuBarraca ui = new MenuBarraca(federacao);
                ui.run();
            } else if (opcao.equals("5")) {
                MenuVoluntarios ui = new MenuVoluntarios(federacao);
                ui.run();
            } else if (opcao.equals("6")) {
                MenuInstituicao ui = new MenuInstituicao(federacao);
                ui.run();
            }

        } while (!opcao.equals("0"));
    }
}
