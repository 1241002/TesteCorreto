package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.utils.Data;
import java.time.LocalDate;
import org.example.utils.Data;

public class EscalaDiaria {

    private Data data;
    private List<Barraca> barracas;
    private double vendasTotais;  // Agora temos um único valor para vendas totais
    private int estoqueFinal;

    public EscalaDiaria(Data data) {
        this.data = data;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.estoqueFinal = 0;
    }

    public Data getData() {
        return data;
    }

    public List<Barraca> getBarracas() {
        return barracas;
    }

    public double getVendasTotais() {
        return vendasTotais;
    }

    public int getEstoqueFinal() {
        return estoqueFinal;
    }

    // Atualiza o valor das vendas totais
    public void adicionarVendaTotal(double vendas) {
        this.vendasTotais += vendas;
    }

    // Atualiza o valor do estoque final
    public void adicionarEstoqueFinal(int estoque) {
        this.estoqueFinal += estoque;
    }



    public void adicionarBarraca(Barraca b) {
        barracas.add(b);
    }


    public static EscalaDiaria criarEscalaHoje() {
        LocalDate hoje = LocalDate.now();
        Data dataHoje = new Data(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth());
        return new EscalaDiaria(dataHoje);
    }





    @Override
    public String toString() {
        return "\nEscalaDiaria [" +
                "Data=" + data +
                ", Nº Barracas=" + barracas.size() +
                ", Total Vendas=" + vendasTotais + "€" +
                ", Estoque Final=" + estoqueFinal +
                ']';
    }
}

