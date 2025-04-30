package org.example.ui;

import org.example.model.Federacao;
import org.example.utils.Utils;

import java.io.IOException;

public class MenuVoluntarioStock {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarioStock(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("###### MENU #####");
            System.out.println("1. Adicionar Stock");
            // Completar
            System.out.println("0. Voltar");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                // Completar
                System.out.println("Selecionou a opção: Adicionar stock");
            }
        }
        while (!opcao.equals("0"));
    }
}

