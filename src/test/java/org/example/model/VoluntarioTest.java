package org.example.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VoluntarioTest {

    @Test
    void setNome() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        VoluntarioStock voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer

        // Test setting a new name
        voluntario.setNome("Maria"); // Change name to Maria
        assertEquals("Maria", voluntario.getNome()); // Check if name is now Maria
    }

    @Test
    void setNumeroAluno() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        VoluntarioStock voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer

        // Test setting a new student number
        voluntario.setNumeroAluno(67890); // Change number to 67890
        assertEquals(67890, voluntario.getNumeroAluno()); // Check if number is now 67890
    }

    @Test
    void setCurso() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        VoluntarioStock voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer

        // Test setting a new course
        voluntario.setCurso("Matemática"); // Change course to Matemática
        assertEquals("Matemática", voluntario.getCurso()); // Check if course is now Matemática
    }

    @Test
    void setSenha() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        VoluntarioStock voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer

        // Test setting a new password
        voluntario.setSenha("novaSenha"); // Change password to novaSenha
        assertEquals("novaSenha", voluntario.getSenha()); // Check if password is now novaSenha
    }

    @Test
    void setBarracaAssociada() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create institution
        Barraca barraca = new Barraca("Barraca 1", instituicao); // Create barraca
        VoluntarioStock voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer

        // Test setting a barraca
        voluntario.setBarracaAssociada(barraca); // Link volunteer to barraca
        assertEquals(barraca, voluntario.getBarracaAssociada()); // Check if barraca is linked
    }

    @Test
    void setInstituicao() {
        // Create objects for the test
        Instituicao instituicao = new Instituicao("Inst A"); // Create first institution
        Instituicao novaInstituicao = new Instituicao("Inst B"); // Create second institution
        VoluntarioStock voluntario = new VoluntarioStock("João", 12345, instituicao, "Informática", "senha"); // Create volunteer

        // Test setting a new institution
        voluntario.setInstituicao(novaInstituicao); // Change to new institution
        assertEquals(novaInstituicao, voluntario.getInstituicao()); // Check if institution is now Inst B
    }
}