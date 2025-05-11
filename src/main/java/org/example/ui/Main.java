package org.example.ui;

import org.example.model.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Construção da federação
            Federacao fap = new Federacao("Federação Académica do Porto");
            System.out.println(fap);

            // Criar escala do dia automaticamente
            EscalaDiaria escalaAtual = EscalaDiaria.criarEscalaHoje();
            fap.setEscalaAtual(escalaAtual);

            // Iniciar menu principal
            MenuInicial_UI uiMenu = new MenuInicial_UI(fap);
            uiMenu.run();

            System.out.println(fap); // Mostrar estado final da federação
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
