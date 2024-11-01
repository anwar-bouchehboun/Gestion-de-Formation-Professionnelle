package com.formation.fomation.api.controllers;

import com.formation.fomation.api.exceptions.ClasseNotFoundException;
import com.formation.fomation.api.exceptions.ClassSucesseExption;
import com.formation.fomation.api.models.dto.ClassDto;
import com.formation.fomation.api.models.entity.Classe;
import com.formation.fomation.api.services.ClassServices;
import com.formation.fomation.api.utilitaire.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
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

    @Operation(summary = "Créer une nouvelle classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe créée avec succès", content = @Content(schema = @Schema(implementation = Classe.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation")
    })
    @PostMapping
    public Classe createClasse(@Valid @RequestBody Classe classes) {
        log.info("Création d'une nouvelle classe : {}", classes);
        Classe createdClasse = classServices.createClasse(classes);
        log.info("Classe créée avec succès : {}", createdClasse);
        return createdClasse;
    }

    @Operation(summary = "Mettre à jour une classe existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe mise à jour avec succès", content = @Content(schema = @Schema(implementation = Classe.class))),
            @ApiResponse(responseCode = "404", description = "Classe non trouvée"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation")
    })
    @PutMapping("/{id}")
    public Classe updateClasse(@PathVariable Long id, @Valid @RequestBody Classe classes) {
        Optional<Classe> check = classServices.getById(id);
        if (check.isPresent()) {
            classes.setId(id);
            Classe updatedClasse = classServices.updateClasse(classes);
            log.info("Classe mise à jour avec succès : {}", updatedClasse);
            return updatedClasse;
        } else {
            log.error("Aucune classe trouvée avec le numéro de CLASS: {}", id);
            throw new ClasseNotFoundException("Aucune classe trouvée avec le numéro de CLASS: " + id);
        }
    }

    @Operation(summary = "Supprimer une classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Classe non trouvée")
    })
    @DeleteMapping("/{id}")
    public void deleteClasse(@PathVariable Long id) {
        Optional<Classe> check = classServices.getById(id);
        if (check.isPresent()) {
            classServices.deleteClasse(id);
            log.info("Classe supprimée avec succès : {}", id);
            throw new ClassSucesseExption("Classe supprimée avec succès : " + id);
        } else {
            log.error("Aucune classe trouvée avec le numéro de CLASS: {}", id);
            throw new ClasseNotFoundException("Aucune classe trouvée avec le numéro de CLASS: " + id);
        }
    }

    @Operation(summary = "Obtenir toutes les classes")
    @ApiResponse(responseCode = "200", description = "Liste de toutes les classes", content = @Content(schema = @Schema(implementation = ClassDto.class)))
    @GetMapping
    public List<ClassDto> getAllClasses() {
        try {
            List<ClassDto> getAllClass = classServices.getAllClasse();
            return getAllClass.stream().collect(Collectors.toList());
        } catch (ClasseNotFoundException e) {
            log.error("Classe non trouvée avec l'ID: {}", e.getClass());
            throw e;
        }
    }

    @Operation(summary = "Obtenir une classe par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe trouvée", content = @Content(schema = @Schema(implementation = ClassDto.class))),
            @ApiResponse(responseCode = "404", description = "Classe non trouvée")
    })
    @GetMapping("/{id}")
    public ClassDto getClasseById(@PathVariable Long id) {
        try {
            return classServices.getById(id)
                    .map(ClassDto::FindById)
                    .orElseThrow(() -> new ClasseNotFoundException("Classe avec l'ID " + id + " non trouvée."));
        } catch (ClasseNotFoundException e) {
            log.error("Classe non trouvée avec l'ID: {}", id);
            throw e;
        }
    }

    @Operation(summary = "Obtenir toutes les classes paginées")
    @ApiResponse(responseCode = "200", description = "Liste paginée de toutes les classes")
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

    @Operation(summary = "Rechercher une classe par numéro de salle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des classes correspondant au numéro de salle", content = @Content(schema = @Schema(implementation = ClassDto.class))),
            @ApiResponse(responseCode = "404", description = "Salle non trouvée")
    })
    @GetMapping("/search/{numSalle}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassDto> searchByNumSalle(@PathVariable String numSalle) {
        List<ClassDto> classes = classServices.searchByNumSalle(numSalle);
        if (classes == null || classes.isEmpty()) {
            log.info("Aucune classe trouvée avec le numéro de salle: {}", numSalle);
            throw new ClasseNotFoundException("Aucune classe trouvée avec le numéro de salle: " + numSalle);
        }
        log.info("Classes trouvées avec le numéro de salle {}: {}", numSalle, classes.size());
        return classes;
    }
}
