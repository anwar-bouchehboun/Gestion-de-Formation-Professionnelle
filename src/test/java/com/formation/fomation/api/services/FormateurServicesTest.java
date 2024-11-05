package com.formation.fomation.api.services;


import com.formation.fomation.api.models.dto.ApprenantDto;
import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formateur;
import com.formation.fomation.api.models.entity.Formation;
import com.formation.fomation.api.repositories.FormateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class FormateurServicesTest {

    @Autowired
    private FormateurServices formateurServices;

    @Autowired
    private FormateurRepository formateurRepository;
    @BeforeEach
    public void setUp() {
        formateurRepository.deleteAll();
    }

    @Test
    public void testCreateApprenant() {
        Formateur formateur = new Formateur();
        formateur.setNom("test");
        formateur.setPrenom("azerty");
        formateur.setEmail("a@gmail.com");
        formateur.setSpecialite("Phyton");
        Classe c= new Classe();
        c.setId(1L);
        formateur.setClasse(c);
        Formateur save= formateurServices.createFormateur(formateur);

        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
        assertThat(save.getNom()).isEqualTo("test");
    }

    @Test
    public void testGetAllFormateur() {
        Formateur formateur = new Formateur();
        formateur.setNom("test");
        formateur.setPrenom("azerty");
        formateur.setEmail("a@gmail.com");
        formateur.setSpecialite("Phyton");
        Classe c= new Classe();
        c.setId(1L);
        formateur.setClasse(c);
        Formateur savedApprenant = formateurServices.createFormateur(formateur);
        List<FormateurDto> formateurs = formateurServices.getAllFormateur();
        assertThat(formateurs).isNotNull();
        assertThat(formateurs).isNotEmpty();
    }
    @Test
    public void testGetById() {
        Formateur formateur = new Formateur();
        formateur.setNom("test");
        formateur.setPrenom("azerty");
        formateur.setEmail("a@gmail.com");
        formateur.setSpecialite("Phyton");
        Classe c= new Classe();
        c.setId(1L);
        formateur.setClasse(c);
        Formateur savedApprenant = formateurServices.createFormateur(formateur);

        Optional<Formateur> formateur1 = formateurServices.getById(savedApprenant.getId());

        assertThat(formateur1).isPresent();
        assertThat(formateur1.get().getNom()).isEqualTo("test");
    }


}
