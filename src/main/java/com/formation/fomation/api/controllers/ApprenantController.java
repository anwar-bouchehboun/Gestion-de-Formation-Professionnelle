package com.formation.fomation.api.controllers;

import com.formation.fomation.api.exceptions.ClassSucesseExption;
import com.formation.fomation.api.exceptions.ClasseNotFoundException;
import com.formation.fomation.api.models.dto.ApprenantDto;
import com.formation.fomation.api.models.dto.FormateurDto;
import com.formation.fomation.api.models.entity.Apprenant;
import com.formation.fomation.api.services.ApprenantServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/apprenants")
@RequiredArgsConstructor
public class ApprenantController {

    private final ApprenantServices apprenantServices;

    @Operation(summary = "Créer une nouvelle Apprenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apprenant créée avec succès", content = @Content(schema = @Schema(implementation = ApprenantDto.class))),
            @ApiResponse(responseCode = "400", description = "Erreur de validation")

    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Apprenant createApprenant(@Valid @RequestBody Apprenant apprenant) {
        log.info("Creating new apprenant: {}", apprenant);
        return apprenantServices.createApprenant(apprenant);
    }

    @Operation(summary = "Mettre à jour une Apprenant existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apprenant mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Apprenant non trouvée"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation")

    })
    @PutMapping("/{id}")
    public void updateApprenant(@PathVariable Long id, @Valid @RequestBody Apprenant apprenant) {
        apprenant.setId(id);
        apprenantServices.updateApprenant(apprenant);
        log.info("Updated apprenant with ID: {}", id);
        throw new ClassSucesseExption("Apprenant Moidfier avec succès : " + id);

    }

    @Operation(summary = "Supprimer une Apprenant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Apprenant supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "supprimer non trouvée")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApprenant(@PathVariable Long id) {
        apprenantServices.deleteApprenant(id);
        log.info("Deleted apprenant with ID: {}", id);
        throw new ClassSucesseExption("Apprenant supprimée avec succès : " + id);

    }

    @Operation(summary = "Obtenir toutes les Apprenant")
    @ApiResponse(responseCode = "200", description = "Liste de toutes les classes", content = @Content(schema = @Schema(implementation = ApprenantDto.class)))
    @GetMapping
    public List<ApprenantDto> getAllApprenants() {
        return apprenantServices.getAllApprenant();
    }

    @Operation(summary = "Obtenir une Apprenant par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apprenant trouvée", content = @Content(schema = @Schema(implementation = ApprenantDto.class))),
            @ApiResponse(responseCode = "404", description = "Apprenant non trouvée")
    })
    @GetMapping("/{id}")
    public ApprenantDto getApprenantById(@PathVariable Long id) {
        Optional<Apprenant> apprenant = apprenantServices.getById(id);
        return apprenant.map(ApprenantDto::findById)
                        .orElseThrow(() -> new ClasseNotFoundException("Apprenant not found with ID: " + id));
    }

    @Operation(summary = "Obtenir les Apprenants paginées")
    @ApiResponse(responseCode = "200", description = "Liste paginée des formations")
    @GetMapping("/page")
    public Page<ApprenantDto> getAllApprenantsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return apprenantServices.getAllClassesWithPagination(pageable);
    }

    @Operation(summary = "Rechercher une Apprenant par numSalle de salle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des Apprenant correspondant au nom de salle", content = @Content(schema = @Schema(implementation = ApprenantDto.class))),
            @ApiResponse(responseCode = "404", description = "Salle  non trouvée")
    })
    @GetMapping("/search/{numSalle}")
    public List<ApprenantDto> searchApprenantsByNumSalle(@PathVariable String numSalle) {
        return apprenantServices.searchByNumSalle(numSalle);
    }
}
