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

import java.util.List;
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
    public void getAllClasse_ShouldReturnAllClasses() {
     /*   Classe classe1 = new Classe();
        classe1.setNom("Classe 1");
        classe1.setNumSalle("B201");
        classeRepository.save(classe1);

        Classe classe2 = new Classe();
        classe2.setNom("Classe 2");
        classe2.setNumSalle("B202");
        classeRepository.save(classe2);

        List<Classe> result = classServices.getAllClasse();

        assertEquals(2, result.size());*/
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
