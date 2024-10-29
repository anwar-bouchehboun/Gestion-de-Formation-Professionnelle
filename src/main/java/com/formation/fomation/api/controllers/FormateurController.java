package com.formation.fomation.api.controllers;


import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.models.entity.Formateur;
import com.formation.fomation.api.services.FormateurServices;
import com.formation.fomation.api.utilitaire.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
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
    @GetMapping("/search/{numSalle}")
    @ResponseStatus(HttpStatus.OK)
    public List<FormateurDto> searchByNumSalle(@PathVariable String numSalle) {
        List<FormateurDto> formateurDtoList = formateurServices.searchByNumSalle(numSalle);
        if (formateurDtoList == null || formateurDtoList.isEmpty()) {
            log.info("Aucune classe trouvée avec le numéro de salle: {}", numSalle);
            return Collections.emptyList();
        }
        log.info("Classes trouvées avec le numéro de salle {}: {}", numSalle, formateurDtoList.size());
        return formateurDtoList;
    }
    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<FormateurDto> getAllClassesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FormateurDto> pageClasses = formateurServices.getAllClassesWithPagination(pageable);

        return new PageResponse<>(
                pageClasses.getContent(),
                pageClasses.getNumber(),
                pageClasses.getTotalElements(),
                pageClasses.getTotalPages()
        );
    }
}
