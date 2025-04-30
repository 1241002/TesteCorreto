package org.example.ui;

import org.example.model.Federacao;

public class Main {
    public static void main(String[] args) {
        try {
            // Construção da empresa
            Federacao fap = new Federacao("Federação Académica do Porto");
            System.out.println(fap);

            MenuInicial_UI uiMenu = new MenuInicial_UI(fap);
            uiMenu.run();

            System.out.println(fap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
