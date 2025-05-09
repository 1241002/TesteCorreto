package org.example.model;
import java.time.LocalDate;
import java.util.List;

public class EscalaDiaria {
    private LocalDate data; // Data da escala
    private List<Barraca> barracas; // Lista de barracas com voluntários

    public EscalaDiaria(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public List<Barraca> getBarracas() {
        return barracas;
    }

    public void setBarracas(List<Barraca> barracas) {
        this.barracas = barracas;
    }
}

