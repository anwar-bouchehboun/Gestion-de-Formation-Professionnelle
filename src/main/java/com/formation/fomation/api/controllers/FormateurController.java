package com.formation.fomation.api.controllers;


import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formateur;
import com.formation.fomation.api.services.FormateurServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/formateurs")
@RequiredArgsConstructor
public class FormateurController {

    private final FormateurServices formateurServices;


    @PostMapping
    public Formateur createFormateur(@Valid @RequestBody Formateur formateur){
        log.info("Création d'une nouvelle Formateur : {}", formateur);
        Formateur createdFormateur = formateurServices.createFormateur(formateur);
        log.info("Formateur créée avec succès : {}", createdFormateur);
        return createdFormateur;
    }
    @GetMapping
    public List<FormateurDto> getAllFormateur(){

        List<FormateurDto> formateur=formateurServices.getAllFormateur();

        return formateur;
    }

    @GetMapping("/{id}")
    public FormateurDto getFormateurById(@PathVariable Long id) {
        Optional<Formateur> formateur = formateurServices.getById(id);
        if (formateur.isPresent()) {
            log.info("Fromateurs trouvée avec l'id: {}", id);
       return       FormateurDto.findById(formateur.get());

        }
        log.info("Fromateurs trouvée avec l'id: {}", id);
        return null;
    }

    @PatchMapping("/{id}")
    public Formateur updateFormateur(@PathVariable Long id, @Valid @RequestBody Formateur formateur) {
        formateur.setId(id);
        log.info("Formateur Update information ");
        return formateurServices.updateFormateur(formateur);
    }
    @DeleteMapping("/{id}")
    public void deleteFormateur(@PathVariable Long id) {
        formateurServices.deleteFormateur(id);
        log.info("Classe supprimer avec succès : {}", id);

    }
}
