/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.example.ui;

//import model.ViagensTop;
//import utils.Utils;

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
            }
        }
        while (!opcao.equals("0"));
    }
}
