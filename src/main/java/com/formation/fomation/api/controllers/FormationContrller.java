package com.formation.fomation.api.controllers;

import com.formation.fomation.api.exceptions.ClassSucesseExption;
import com.formation.fomation.api.exceptions.ClasseNotFoundException;
import com.formation.fomation.api.models.dto.FormationDto;
import com.formation.fomation.api.models.entity.Formation;
import com.formation.fomation.api.services.FormationServices;
import com.formation.fomation.api.utilitaire.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationContrller {

    private final FormationServices formationServices;

    @Operation(summary = "Créer une nouvelle formation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formation créée avec succès", 
                    content = @Content(schema = @Schema(implementation = Formation.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation")

    })
    @PostMapping
    public Formation createFormation(@Valid @RequestBody Formation formation) {
        log.info("Création d'une nouvelle formation : {}", formation);
        Formation createdFormation = formationServices.createFormateur(formation);
        log.info("Formation créée avec succès : {}", createdFormation);
        return createdFormation;
    }

    @Operation(summary = "Obtenir toutes les formations")
    @ApiResponse(responseCode = "200", description = "Liste de toutes les formations", 
            content = @Content(schema = @Schema(implementation = FormationDto.class)))
    @GetMapping
    public List<FormationDto> getAllFormations() {
        return formationServices.getAllFormation();
    }

    @Operation(summary = "Obtenir une formation par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formation trouvée"),
            @ApiResponse(responseCode = "404", description = "Formation non trouvée")
    })
    @GetMapping("/{id}")
    public FormationDto getFormationById(@PathVariable Long id) {
        try {
            return formationServices.getById(id)
                    .map(FormationDto::findById)
                    .orElseThrow(() -> new ClasseNotFoundException("Formation avec l'ID " + id + " non trouvée."));
        } catch (ClasseNotFoundException e) {
            log.error("Formation non trouvée avec l'ID: {}", id);
            throw e;
        }
    }

    @Operation(summary = "Mettre à jour une formation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Formation mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Formation non trouvée"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation")

    })
    @PutMapping("/{id}")
    public void updateFormation(@PathVariable Long id, @Valid @RequestBody Formation formation) {
        Optional<Formation> check = formationServices.getById(id);
        if (check.isPresent()) {
            formation.setId(id);
          formationServices.updateFormateur(formation);
            throw new ClassSucesseExption("Formation Moidfier avec succès : " + id);

        } else {
            throw new ClasseNotFoundException("Aucune formation trouvée avec l'ID: " + id);
        }
    }

    @Operation(summary = "Supprimer une formation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Formation supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Formation non trouvée")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFormation(@PathVariable Long id) {
        Optional<Formation> check = formationServices.getById(id);
        if (check.isPresent()) {
            formationServices.deleteFormation(id);
            log.info("Formation supprimée avec succès : {}", id);
            throw new ClassSucesseExption("Formation Supprimer avec succès : " + id);
        } else {
            throw new ClasseNotFoundException("Aucune formation trouvée avec l'ID: " + id);
        }
    }

    @Operation(summary = "Rechercher des formations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des formations trouvées"),
            @ApiResponse(responseCode = "404", description = "Aucune formation trouvée")
    })
    @GetMapping("/search/{nom}")
    public List<FormationDto> searchFormations(@PathVariable String nom) {
        List<FormationDto> formations = formationServices.searchByName(nom);
        if (formations == null || formations.isEmpty()) {
            throw new ClasseNotFoundException("Aucune formation trouvée avec le nom: " + nom);
        }
        return formations;
    }

    @Operation(summary = "Obtenir les formations paginées")
    @ApiResponse(responseCode = "200", description = "Liste paginée des formations")
    @GetMapping("/page")
    public PageResponse<FormationDto> getAllFormationsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<FormationDto> pageFormations = formationServices.getAllClassesWithPagination(pageable);

        return new PageResponse<>(
            pageFormations.getContent(),
            pageFormations.getNumber(),
            pageFormations.getTotalElements(),
            pageFormations.getTotalPages()
        );
    }

    @Operation(summary = "Obtenir les détails des formations")
    @ApiResponse(responseCode = "200", description = "Détails des formations", 
            content = @Content(schema = @Schema(implementation = FormationDto.class)))
    @GetMapping("/details")
    public List<FormationDto> getFormationDetails() {
        return formationServices.getDetails();
    }
}
