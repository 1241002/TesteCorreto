package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FederacaoTest {

    @Test
    void adicionarBarraca() {
        // Create objects for the test
        Federacao federacao = new Federacao("Feira"); // Create federacao
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        Barraca barraca = new Barraca("Barraca 1", instituicao); // Create barraca

        // Check initial state
        assertEquals(0, federacao.getTodasBarracas().size()); // Check if list starts empty

        // Test adding a barraca
        federacao.adicionarBarraca(barraca); // Add barraca to federacao
        assertEquals(1, federacao.getTodasBarracas().size()); // Check if 1 barraca is in the list
        assertEquals(barraca, federacao.getTodasBarracas().get(0)); // Check if the right barraca was added

        // Test adding a different barraca
        Barraca barraca2 = new Barraca("Barraca 2", instituicao); // Create another barraca
        federacao.adicionarBarraca(barraca2); // Add second barraca
        assertEquals(2, federacao.getTodasBarracas().size()); // Check if 2 barracas are in the list
    }

    @Test
    void adicionarProduto() {
        // Create objects for the test
        Federacao federacao = new Federacao("Feira"); // Create federacao
        Produto produto = new Produto("Cachorro Quente", 2.5); // Create product

        // Check initial state
        assertEquals(0, federacao.getListaProdutos().size()); // Check if list starts empty

        // Test adding a product
        federacao.adicionarProduto(produto); // Add product to federacao
        assertEquals(1, federacao.getListaProdutos().size()); // Check if 1 product is in the list
        assertEquals(produto, federacao.getListaProdutos().get(0)); // Check if the right product was added
    }

    @Test
    void adicionarInstituicao() {
        // Create objects for the test
        Federacao federacao = new Federacao("Feira"); // Create federacao
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        Barraca barraca = new Barraca("Barraca 1", instituicao); // Create barraca
        instituicao.adicionarBarraca(barraca); // Add barraca to institution

        // Check initial state
        assertEquals(0, federacao.getInstituicoes().size()); // Check if institution list starts empty
        assertEquals(0, federacao.getTodasBarracas().size()); // Check if barraca list starts empty

        // Test adding an institution
        federacao.adicionarInstituicao(instituicao); // Add institution to federacao
        assertEquals(1, federacao.getInstituicoes().size()); // Check if 1 institution is in the list
        assertEquals(instituicao, federacao.getInstituicoes().get(0)); // Check if the right institution was added
        assertEquals(federacao, instituicao.getFederacao()); // Check if institution is linked to federacao
        assertEquals(1, federacao.getTodasBarracas().size()); // Check if institution's barraca was added
        assertEquals(barraca, federacao.getTodasBarracas().get(0)); // Check if the right barraca was added
    }

    @Test
    void validarLoginAdministrador() {
        // Create objects for the test
        Federacao federacao = new Federacao("Feira"); // Create federacao
        Administrador admin = new Administrador("Ana", 1001, "senha", "Informática"); // Create admin

        // Verify admin properties
        assertEquals("Ana", admin.getNome()); // Check admin name
        assertEquals(1001, admin.getNumero()); // Check admin number
        assertEquals("Informática", admin.getCurso()); // Check admin course
        assertEquals("senha", admin.getSenha()); // Check admin password

        // Check initial state
        assertEquals(0, federacao.getAdministradores().size()); // Check if admin list starts empty
        assertFalse(federacao.validarLoginAdministrador("Ana", 1001, "senha", "Informática")); // Check login fails with no admins

        // Test adding an admin
        federacao.adicionarAdministrador(admin); // Add admin to federacao
        assertEquals(1, federacao.getAdministradores().size()); // Check if 1 admin is in the list
        assertEquals(admin, federacao.getAdministradores().get(0)); // Check if the right admin was added

        // Test validating login
        assertTrue(federacao.validarLoginAdministrador("Ana", 1001, "senha", "Informática")); // Check if login works
        assertFalse(federacao.validarLoginAdministrador("Ana", 1001, "wrong", "Informática")); // Check if wrong password fails
    }

    @Test
    void buscarVoluntarioPorNumeroAluno() {
        // Create objects for the test
        Federacao federacao = new Federacao("Feira"); // Create federacao
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        VoluntarioStock voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer
        instituicao.adicionarVoluntario(voluntario); // Add volunteer to institution
        federacao.adicionarInstituicao(instituicao); // Add institution to federacao

        // Test searching for a volunteer
        Voluntario found = federacao.buscarVoluntarioPorNumeroAluno(12345); // Search for volunteer
        assertEquals(voluntario, found); // Check if the right volunteer was found

        // Test searching for a non-existent volunteer
        Voluntario notFound = federacao.buscarVoluntarioPorNumeroAluno(99999); // Search for non-existent volunteer
        assertNull(notFound); // Check if null is returned
    }
}