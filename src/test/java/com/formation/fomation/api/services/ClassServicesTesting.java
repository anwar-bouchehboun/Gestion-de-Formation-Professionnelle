package com.formation.fomation.api.services;

import com.formation.fomation.api.models.dto.ClassDto;
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
    public void deleteClasse_ShouldRemoveClasse() {
        Classe classe = new Classe();
        classe.setNom("Test Classe");
        classe.setNumSalle("A101");
        Classe savedClasse = classeRepository.save(classe);

        classServices.deleteClasse(savedClasse.getId());

        Optional<Classe> deletedClasse = classeRepository.findById(savedClasse.getId());
        assertFalse(deletedClasse.isPresent());
    }

    @Test
    public void updateClasseDetails() {
        Classe classe = new Classe();
        classe.setNom("Original Classe");
        classe.setNumSalle("B202");
        Classe savedClasse = classServices.createClasse(classe);

        savedClasse.setNom("Updated Classe");
        savedClasse.setNumSalle("C303");
        Classe updatedClasse = classServices.updateClasse(savedClasse);

        assertNotNull(updatedClasse);
        assertEquals("Updated Classe", updatedClasse.getNom());
        assertEquals("C303", updatedClasse.getNumSalle());
    }

    @Test
    public void getAllClasse_AllClasses() {
        // Create and save a couple of classes
        Classe classe1 = new Classe();
        classe1.setNom("Classe 1");
        classe1.setNumSalle("A101");
        classServices.createClasse(classe1);

        Classe classe2 = new Classe();
        classe2.setNom("Classe 2");
        classe2.setNumSalle("B202");
        classServices.createClasse(classe2);

        List<ClassDto> classes = classServices.getAllClasse();

        assertNotNull(classes);
    }

    @Test
    public void getById_ShouldReturnClasse() {
        Classe classe = new Classe();
        classe.setNom("Test Classe");
        classe.setNumSalle("A101");
        Classe savedClasse = classServices.createClasse(classe);

        Optional<Classe> retrievedClasse = classServices.getById(savedClasse.getId());

        assertTrue(retrievedClasse.isPresent());
        assertEquals("Test Classe", retrievedClasse.get().getNom());
        assertEquals("A101", retrievedClasse.get().getNumSalle());
    }
}
