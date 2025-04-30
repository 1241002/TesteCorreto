package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.Utils;

import java.io.IOException;

public class MenuAdministrador {
    private Federacao federacao;
    private String opcao;

    public MenuAdministrador(Federacao federacao)
    {
        this.federacao = federacao;
    }

    public void run() throws IOException
    {
        do
        {
            System.out.println("###### MENU #####");
            System.out.println("1. Registar Produto");
            // Completar com mais opções
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if( opcao.equals("1") )
            {
                RegistarProduto_UI ui = new RegistarProduto_UI(federacao);
                ui.run();
            }
        }
        while (!opcao.equals("0") );
    }
}
