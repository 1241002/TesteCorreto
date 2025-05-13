package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.utils.Data;
import java.time.LocalDate;

public class EscalaDiaria {

    private Data data;
    private List<Barraca> barracas;
    private double vendasTotais;  // Agora temos um único valor para vendas totais
    private int stockFinal;

    public EscalaDiaria(Data data) {
        this.data = data;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinal = 0;
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

    public int getStockFinal() {
        return stockFinal;
    }

    public void adicionarVendaTotal(double vendas) {
        this.vendasTotais += vendas;
    }

    public void adicionarStockFinal(int stock) {
        this.stockFinal += stock;
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
        return "EscalaDiaria [" +
                "Data=" + data +
                ", Nº Barracas=" + barracas.size() +
                ", Total Vendas=" + vendasTotais + "€" +
                ", Stock Final=" + stockFinal +
                ']';
    }
}