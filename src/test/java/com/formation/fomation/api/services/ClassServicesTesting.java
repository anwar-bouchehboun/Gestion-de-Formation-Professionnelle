package com.formation.fomation.api.services;

import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.repositories.ClasseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class ClassServicesTesting {

    @Autowired
    private ClassServices classServices;

    @Autowired
    private ClasseRepository classeRepository;

    @Test
    public void createClasse_ShouldSaveAndReturnClasse() {
        Classe classe = new Classe();
        classe.setNom("Test Classe");
        classe.setNumSalle("A101");

        Classe result = classServices.createClasse(classe);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("Test Classe", result.getNom());
        assertEquals("A101", result.getNumSalle());
    }


    @Test
    public void deleteClasse_ShouldRemoveClasse() {
        // Arrange
        Classe classe = new Classe();
        classe.setNom("Test Classe");
        classe.setNumSalle("A101");
        Classe savedClasse = classeRepository.save(classe);

        // Act
        classServices.deleteClasse(savedClasse.getId());

        // Assert
        Optional<Classe> deletedClasse = classeRepository.findById(savedClasse.getId());
        assertFalse(deletedClasse.isPresent());
    }
}
