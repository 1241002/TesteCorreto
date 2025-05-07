package org.example.ui;

import org.example.model.Federacao;
import org.example.model.VoluntarioVendas;

public class Main {
    public static void main(String[] args) {
        try {
            // Construção da federação
            Federacao fap = new Federacao("Federação Académica do Porto");
            System.out.println(fap);

            // Criação da Federação X
            Federacao FederaçãoX = new Federacao("Federação X");

            // Criando voluntários de vendas
            VoluntarioVendas voluntario1 = new VoluntarioVendas("João Silva", 123456789, FederaçãoX);
            VoluntarioVendas voluntario2 = new VoluntarioVendas("Maria Oliveira", 987654321, FederaçãoX);

            // Adicionando voluntários à federação
            FederaçãoX.adicionarVoluntario(voluntario1);
            FederaçãoX.adicionarVoluntario(voluntario2);


            // Iniciar menu após inicialização dos dados
            MenuInicial_UI uiMenu = new MenuInicial_UI(FederaçãoX);  // Passando a federação correta
            uiMenu.run();

            System.out.println(FederaçãoX); // Mostrar estado final da federação
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
