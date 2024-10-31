package com.formation.fomation.api.services;

import com.formation.fomation.api.models.dto.ApprenantDto;
import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formation;
import com.formation.fomation.api.repositories.ApprenantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ApprenantServices.class)
public class ApprenantServicesTest {

    @Autowired
    private ApprenantServices apprenantServices;

    @Autowired
    private ApprenantRepository apprenantRepository;

    @BeforeEach
    public void setUp() {
        apprenantRepository.deleteAll();
    }

    @Test
    public void testCreateApprenant() {
        Apprenant apprenant = new Apprenant();
        apprenant.setNom("test");
        apprenant.setPrenom("azerty");
        apprenant.setEmail("a@gmail.com");
        apprenant.setNiveau("A11");
        Formation f= new Formation();
        f.setId(1L);
        Classe c= new Classe();
        c.setId(97L);
        apprenant.setFormation(f);
        apprenant.setClasse(c);
        Apprenant savedApprenant = apprenantServices.createApprenant(apprenant);

        assertThat(savedApprenant).isNotNull();
        assertThat(savedApprenant.getId()).isNotNull();
        assertThat(savedApprenant.getNom()).isEqualTo("test");
    }

    @Test
    public void testGetAllApprenant() {

        List<ApprenantDto> apprenants = apprenantServices.getAllApprenant();

    }
    @Test
    public void testGetById() {
        Apprenant apprenant = new Apprenant();
        apprenant.setNom("khalid");
        apprenant.setNiveau("A11");
        Formation f= new Formation();
        f.setId(1L);
        Classe c= new Classe();
        c.setId(97L);
        apprenant.setFormation(f);
        apprenant.setClasse(c);
        Apprenant savedApprenant = apprenantServices.createApprenant(apprenant);

        Optional<Apprenant> foundApprenant = apprenantServices.getById(savedApprenant.getId());

        assertThat(foundApprenant).isPresent();
        assertThat(foundApprenant.get().getNom()).isEqualTo("khalid");
    }


} 