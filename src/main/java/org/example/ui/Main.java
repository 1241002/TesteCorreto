package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Instituicao;
import org.example.model.VoluntarioVendas;

public class Main {
    public static void main(String[] args) {
        try {
            // Construção da federação
            Federacao fap = new Federacao("Federação Académica do Porto");
            System.out.println(fap);

            // Iniciar menu após inicialização dos dados
            MenuInicial_UI uiMenu = new MenuInicial_UI(fap);  // Passando a federação correta
            uiMenu.run();

            System.out.println(fap); // Mostrar estado final da federação
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
