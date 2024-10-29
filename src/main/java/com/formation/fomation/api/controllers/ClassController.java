package com.formation.fomation.api.controllers;

import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.services.ClassServices;
import com.formation.fomation.api.utilitaire.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassServices classServices;
    @PostMapping
    public Classe createClasse(@Valid @RequestBody Classe classes) {
        log.info("Création d'une nouvelle classe : {}", classes);
        Classe createdClasse = classServices.createClasse(classes);
        log.info("Classe créée avec succès : {}", createdClasse);
        return createdClasse;

    }

    @PutMapping("/{id}")
    public Classe updateClasse(@PathVariable Long id, @Valid @RequestBody Classe classes) {
        classes.setId(id);
        return classServices.updateClasse(classes);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClasse(@PathVariable Long id) {
        classServices.deleteClasse(id);
        log.info("Classe supprimer avec succès : {}", id);

    }

    @GetMapping
    public List<ClassDto> getAllClasses() {
        List<ClassDto> getAllClass= classServices.getAllClasse();;
        return getAllClass.stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClassDto getClasseById(@PathVariable Long id) {
        Optional<Classe> classe = classServices.getById(id);
        if (classe.isPresent()) {
            log.info("Classe trouvée avec l'id: {}", id);
      return    ClassDto.FindById(classe.get());

        }
        log.info("Classe ne  trouvée  pas avec l'id: {}", id);
       return null ;
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ClassDto> getAllClassesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ClassDto> pageClasses = classServices.getAllClassesWithPagination(pageable);
        
        return new PageResponse<>(
            pageClasses.getContent(),
            pageClasses.getNumber(),
            pageClasses.getTotalElements(),
            pageClasses.getTotalPages()
        );
    }

    @GetMapping("/search/{numSalle}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassDto> searchByNumSalle(@PathVariable String numSalle) {
        List<ClassDto> classes = classServices.searchByNumSalle(numSalle);
        if (classes == null || classes.isEmpty()) {
            log.info("Aucune classe trouvée avec le numéro de salle: {}", numSalle);
            return Collections.emptyList();
        }
        log.info("Classes trouvées avec le numéro de salle {}: {}", numSalle, classes.size());
        return classes;
    }
}
