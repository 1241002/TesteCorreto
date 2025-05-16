package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BarracaTest {

    @Test
    void adicionarVoluntario() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        Barraca barraca = new Barraca("Barraca 1", instituicao); // Create barraca
        Voluntario voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer (use VoluntarioStock)

        // Test adding a volunteer
        boolean added = barraca.adicionarVoluntario(voluntario); // Try to add volunteer
        assertTrue(added); // Check if adding worked
        assertEquals(1, barraca.getVoluntarios().size()); // Check if 1 volunteer is in the list
        assertEquals(voluntario, barraca.getVoluntarios().get(0)); // Check if the right volunteer was added
        assertEquals(barraca, voluntario.getBarracaAssociada()); // Check if volunteer is linked to barraca

        // Test adding the same volunteer again (should fail)
        boolean addedAgain = barraca.adicionarVoluntario(voluntario); // Try to add same volunteer
        assertFalse(addedAgain); // Check if adding failed
        assertEquals(1, barraca.getVoluntarios().size()); // Check if list still has only 1 volunteer
    }

    @Test
    void adicionarStock() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        Barraca barraca = new Barraca("Barraca 1", instituicao); // Create barraca
        StockProdutos stock = new StockProdutos("Cachorro Quente", 2.5, 10); // Create stock item

        // Test adding stock
        barraca.adicionarStock(stock); // Add stock to barraca
        assertEquals(1, barraca.getStock().size()); // Check if 1 item is in stock
        assertEquals(stock, barraca.getStock().get(0)); // Check if the right item was added
        assertEquals(10, barraca.exportarStockTotal()); // Check if total stock is 10
    }
}