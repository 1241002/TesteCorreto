package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.utils.Data;

public class EscalaDiaria implements Comparable<EscalaDiaria> {
    private Data data;
    private List<Barraca> barracas;
    private double vendasTotais;
    private int stockFinal;

    public EscalaDiaria(Data data) {
        this.data = data;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinal = 0;
    }

    public EscalaDiaria() {
        this.data = null;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinal = 0;
    }

    public EscalaDiaria(EscalaDiaria outra) {
        this.data = outra.data;
        this.barracas = new ArrayList<>(outra.barracas);
        this.vendasTotais = outra.vendasTotais;
        this.stockFinal = outra.stockFinal;
    }

    public Data getData() {
        return this.data;
    }

    public List<Barraca> getBarracas() {
        return new ArrayList<>(this.barracas);
    }

    public double getVendasTotais() {
        return this.vendasTotais;
    }

    public int getStockFinal() {
        return this.stockFinal;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void adicionarVendaTotal(double vendas) {
        this.vendasTotais += vendas;
    }

    public void adicionarStockFinal(int stock) {
        this.stockFinal += stock;
    }

    public void adicionarBarraca(Barraca b) {
        this.barracas.add(b);
    }

    public static EscalaDiaria criarEscalaHoje() {
        Data dataHoje = new Data(2025, 5, 16); // Hardcoded for simplicity
        return new EscalaDiaria(dataHoje);
    }

    @Override
    public int compareTo(EscalaDiaria outra) {
        return this.data.toString().compareTo(outra.data.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EscalaDiaria)) return false;
        EscalaDiaria that = (EscalaDiaria) o;
        return this.data.equals(that.data);
    }

    @Override
    public String toString() {
        return "EscalaDiaria{" +
                "data=" + data +
                ", numBarracas=" + barracas.size() +
                ", vendasTotais=" + String.format("%.2f", vendasTotais) + "€" +
                ", stockFinal=" + stockFinal +
                '}';
    }
}