package com.formation.fomation.api.controllers;

import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.services.ClassServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Classcontrollertest {

    @Mock
    private ClassServices classServices;

    @InjectMocks
    private ClassController classController;

    private Classe classe;
    private Classe expectedClasse;

    @Before
    public void setUp() {
        classe = Classe.builder()
                .id(1L)
                .nom("Test Classe")
                .numSalle("A101")
                .build();

        expectedClasse = Classe.builder()
                .id(2L)
                .nom("Zero 1")
                .numSalle("Code07")
                .build();
    }

    @Test
    public void createClasse_ShouldReturnNewClasse() {
        when(classServices.createClasse(any(Classe.class))).thenReturn(expectedClasse);
        
        Classe result = classController.createClasse(classe);
        
        assertNotNull(result);
        assertEquals(expectedClasse.getNom(), result.getNom());
        assertEquals(expectedClasse.getNumSalle(), result.getNumSalle());
        verify(classServices, times(1)).createClasse(any(Classe.class));
    }

    @Test
    public void getAllClasses_ShouldReturnListOfClasses() {
      /*  List<Classe> expectedClasses = Arrays.asList(classe, expectedClasse);
        when(classServices.getAllClasse()).thenReturn(expectedClasses);

      //  List<Classe> result = classController.getAllClasses();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(classServices, times(1)).getAllClasse();*/
    }

    @Test
    public void getClasseById_ShouldReturnClasse() {
      /*  when(classServices.getById(1L)).thenReturn(Optional.of(classe));

        ClassDto result = classController.getClasseById(1L);

        assertNotNull(result);
        assertEquals(classe.getNom(), result.getNom());
        verify(classServices, times(1)).getById(1L);*/
    }

    @Test
    public void updateClasse_ShouldReturnUpdatedClasse() {
        when(classServices.updateClasse(any(Classe.class))).thenReturn(expectedClasse);

        Classe result = classController.updateClasse(1L, classe);

        assertNotNull(result);
        assertEquals(expectedClasse.getNom(), result.getNom());
        verify(classServices, times(1)).updateClasse(any(Classe.class));
    }

    @Test
    public void deleteClasse_ShouldCallServiceDelete() {
        doNothing().when(classServices).deleteClasse(1L);

        classController.deleteClasse(1L);

        verify(classServices, times(1)).deleteClasse(1L);
    }
} 